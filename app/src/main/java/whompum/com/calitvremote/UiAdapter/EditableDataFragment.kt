package whompum.com.calitvremote.UiAdapter

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.californiadreamshostel.firebaseclient.*
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Util.Animation
import whompum.com.calitvremote.Util.BottomMarginListDecorator
import whompum.com.calitvremote.Util.Display
import whompum.com.calitvremote.Util.ListUtils

/*
 * Displays a list of editable Reference objects
 * and that delegates the changing of data to proper APIs
 */
abstract class EditableDataFragment: Fragment(), OnSuccessListener, OnFailureListener {

    companion object {
        const val LIST_ID = R.id.id_editable_list
        const val FAB_ID = R.id.id_editable_save_fab
    }

    //Controllers that reference and provide data from a resource
    protected val controllers = HashSet<FirebaseController>(1)

    protected lateinit var listAdapter: ObservableListAdapter

    private lateinit var list: RecyclerView //Bound from R.id.editable_list
    private lateinit var saveFab: FloatingActionButton //Bound from R.id.save_state_fab


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchResources().forEach {
            val controller = FirebaseController(it, onDataChange())
            controller.setAdapterReferenceFactory(getReferenceConvertorFactory(it))
            controllers.add(controller)
        }

        listAdapter = initializeAdapter()
        listAdapter.setChangeClient { toggleSaveFab()/*Called each time a value change is detected*/ }
    }

    override fun onStart() {
        super.onStart()
        controllers.forEach {
            it.register()
            it.onWriteCompleteListener = this
            it.onWriteFailureListener = this
        }
    }

    override fun onPause() {
        super.onPause()
        listAdapter.clearCache()
    }

    override fun onStop() {
        super.onStop()
        controllers.forEach { it.unRegister() }
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val content = inflater.inflate(fetchLayout(), container, false)

        list = content.findViewById(LIST_ID)
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(BottomMarginListDecorator(context?.resources?.getDimensionPixelSize(R.dimen.dimen_padding_ver_base)!!))


        saveFab = content.findViewById(FAB_ID)
        saveFab.scaleY = 0F
        saveFab.scaleX = 0F
        saveFab.setOnClickListener{ saveChanges() }

        return content
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(!this::listAdapter.isInitialized)
            return

        list.adapter = listAdapter
    }

    override fun onSuccess(item: Reference) {
        Display.display(context!!, "Success")
        handleControllerResponse()
    }

    override fun onFailure(item: Reference, exception: Exception) {
        Display.display(context!!, exception.localizedMessage)
        handleControllerResponse()
    }

    @CallSuper
    protected open fun handleControllerResponse(){
        listAdapter.clearCache()
        toggleSaveFab()
    }

    /*
     * Returns a list of paths we want to query from
     */
    protected abstract fun fetchResources(): Set<String>

    /*
     * Returns the layout ID clients wish to use
     */
    @LayoutRes
    protected abstract fun fetchLayout(): Int


    protected abstract fun getReferenceConvertorFactory(controllerRef: String):
            (String, String, String) -> ReferenceItem

    /*
     * Fetches the root item of the reference item.
     *
     * I.E. This will return on of the values returned
     * from fetchResources.
     *
     */
    protected abstract fun getReferenceRoot(ref: String): String?


    /*
     * Initializes the adapter.
     * Subclasses will want a specific implementation of ObservableListAdapter
     */
    protected abstract fun initializeAdapter(): ObservableListAdapter


    protected open fun onDataChange(): (ReferenceItem, Int) -> Unit{
        return{ groupItem, deltaType ->

            if(deltaType == SimpleEventListener.ADDED) {
                if (ListUtils.addItem(groupItem, listAdapter.data))
                    listAdapter.notifyItemInserted(listAdapter.data.lastIndex)
            }

            else if(deltaType == SimpleEventListener.CHANGE) {
                if (ListUtils.updateGroupChildren(groupItem, listAdapter.data))
                    listAdapter.notifyDataSetChanged()
            }

            else if(deltaType == SimpleEventListener.REMOVED) {
                val positionRemoved = ListUtils.deleteItem(groupItem, listAdapter.data)
                   if(positionRemoved > -1)
                       listAdapter.notifyItemRemoved(positionRemoved)
            }

        }
    }


    /*
     * Sends save requests to the server interface
     */
    protected open fun saveChanges() {
        for (a in listAdapter.changedReferences) {
            if (a.hasChildren()){
                a.getChildren().forEach {
                    if (it.hasChanged())
                        controllers.find { it.group == getReferenceRoot(a.reference) }?.saveChanges(it)
                }
            }else controllers.find { it.group == getReferenceRoot(a.reference) }?.saveChanges(a)
        }

    }

    private fun toggleSaveFab(){
        val isVisible = saveFab.scaleX != 0F && saveFab.scaleY != 0F

        if(listAdapter.changedReferences.isEmpty() && isVisible)//HIDE
            Animation.toggleDisplay(saveFab, false)

        else if(listAdapter.changedReferences.isNotEmpty() && !isVisible) //SHOW
            Animation.toggleDisplay(saveFab, true)
    }

    protected fun fetchControllerForResource(ref: String) = controllers.find { it.group == getReferenceRoot(ref) }

    protected fun saveNew(ref: ReferenceItem){
        fetchControllerForResource(ref.reference)?.saveNew(ref)
    }

}