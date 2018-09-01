package whompum.com.calitvremote.Networking.UiAdapter

import android.support.annotation.IdRes
import android.support.annotation.NonNull
import android.util.Log
import android.view.View
import whompum.com.calitvremote.Networking.Model.Reference
import whompum.com.calitvremote.Networking.Model.ReferenceItem

abstract class ObservableListAdapter(data: List<Reference> = ArrayList(),
                                     @NonNull val l: ReferenceLayoutProvider): ReferenceListAdapter(data, l), OnValueChanged{

    val changedReferences = HashSet<ReferenceItem>()
    private var changeClient = fun (_: Set<Reference>) {}

    fun setChangeClient(l: (Set<Reference>) -> Unit){
        this.changeClient = l
    }

    override fun makeHolder(layout: View): ReferenceViewHolder {
        val holder = getHolder(layout)
        holder.setValueChangeListener(this)

        return holder
    }

    /*
     * This method will set the actual value of the ReferenceItem
     * that had changed, then store the representing TESTReference object
     * of the item (from the adapter) into a local cache of changed items.
     * Then, if notifies the client.
     *
     */
    override fun onValueChange(newValue: String, /* is an ID resource :/ */ @IdRes id: Int, position: Int) {

        /*
         * Changes the actual value of the item that the user requested to change.
         * Then update local cache, and notify clients of the change.
         *
         * Find the item that had changed using the ID value.
         * The ID is a res ID of the EditText that had changed.
         * Subclasses are responsible for knowing the id's of their editors.
         */

        var baseItem = data[position]

        if(baseItem !is ReferenceItem)
            return

        var item: ReferenceItem? = baseItem

        if(baseItem.hasChildren()) //Find the child using the ID of the editor.
            item = findChildById(baseItem, id)

        item?.value = newValue

        //If item has changed, AND isn't documented:
        if(item?.hasChanged()!! && !changedReferences.contains(baseItem))
            changedReferences.add(baseItem)

        //Else if the Item hasn't Changed, BUT is documented:
        else if(!item.hasChanged() && changedReferences.contains(baseItem))
            changedReferences.remove(baseItem)

        changeClient(changedReferences)
    }

    abstract fun getHolder(layout: View): ObservableReferenceViewHolder
    abstract fun findChildById(group: ReferenceItem, @IdRes id: Int): ReferenceItem?

    fun clearCache(){
        changedReferences.clear()
    }

}