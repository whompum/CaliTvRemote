package whompum.com.calitvremote.Slides

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.MotionEvent
import android.view.View
import com.californiadreamshostel.firebaseclient.*

import kotlinx.android.synthetic.main.slides.*
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Slides.UiAdapter.Dragger
import whompum.com.calitvremote.Slides.UiAdapter.SlideLayoutProvider
import whompum.com.calitvremote.Slides.UiAdapter.SlideListAdapter

class RemoteController: AppCompatActivity(){


    //Callback invoked by the Adapter If the duration button is clicked
    private val onDurationTapped = fun (item : SlideItem){
        DurationDialog({
            item.findChildBy(DataSchema.SLIDES_DURATION)?.value = it.toString()
            //adapter.notifyDataSetChanged()
            controller.saveChanges(item)
        }, this).show()
    }

    private val onItemSelected = fun (item: SlideItem, itemUnselected: SlideItem?){
        if(itemUnselected != null)
            controller.saveChanges(itemUnselected)

        controller.saveChanges(item)

    }

    private val controller = FirebaseController(DataSchema.SLIDES) { item, type ->

        if (type == SimpleEventListener.ADDED || type == SimpleEventListener.CHANGE)
            adapter.addItem(item as SlideItem)

    }

    private lateinit var adapter: SlideListAdapter
    private lateinit var list: RecyclerView

    private val postSwapUpdator = PostSwapUpdator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.slides)

        controller.setAdapterReferenceFactory {parentRef, ref, value ->
            SlideItem(parentRef, ref, value)
        }

        controller.onWriteFailureListener = object: OnFailureListener {
            override fun onFailure(item: Reference, exception: Exception) {

            }
        }

        adapter = SlideListAdapter(ArrayList(), SlideLayoutProvider())
        adapter.onDurationClickListener = onDurationTapped
        adapter.onItemSelected = onItemSelected

        list = id_remote_list
        list.setOnTouchListener(postSwapUpdator)
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter

        ItemTouchHelper(Dragger(adapter.itemMoved())).attachToRecyclerView(list)

        adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
            override fun onItemRangeMoved(from: Int, to: Int, itemCount: Int) {
                postSwapUpdator.updatePositions(from, to)
            }
        })


        id_local_up.setOnClickListener{
            NavUtils.navigateUpFromSameTask(this)
        }

    }


    override fun onStart() {
        super.onStart()
        controller.register()
    }

    override fun onPause() {
        super.onPause()
        controller.unRegister()
    }

    inner class PostSwapUpdator: View.OnTouchListener{


        private var hasActiveUpdates = false
        private var updateItems = HashSet<SlideItem>()


        override fun onTouch(view: View?, event: MotionEvent?): Boolean {


            if( (event?.action == MotionEvent.ACTION_UP || event?.action == MotionEvent.ACTION_CANCEL) &&
                    hasActiveUpdates){

                //Simultaneously update the server, AND remove from the updateItems
                updateItems.removeAll {
                    controller.saveChanges(it)
                    true
                }
                hasActiveUpdates = false
            }


            return false
        }

        //Callback invoked by the AdapterObserver after a item swap event is detected
        val updatePositions = fun (from: Int, to: Int){

            //For each item within the range changed
            for(a in Math.min(from, to)..Math.max(from, to)) {
                val item = getAdapterItem(a)
                getPositionChild(item)?.value = a.toString() // Change position to Adapter Position

                if(!updateItems.contains(item))
                    updateItems.add(item)
            }
            hasActiveUpdates = true
        }

        private fun getAdapterItem(i: Int) = (adapter.getItemAt(i) as SlideItem)
        private fun getPositionChild(i: SlideItem) = i.findChildBy(DataSchema.SLIDES_POSITION)

    }


}






