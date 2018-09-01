package whompum.com.calitvremote.Shelf.UiAdapter

import android.support.annotation.NonNull
import android.view.View
import whompum.com.calitvremote.Networking.Model.Reference
import whompum.com.calitvremote.Networking.Model.ReferenceItem
import whompum.com.calitvremote.Networking.UiAdapter.*

class ShelfListAdapter(d: List<Reference>,
                       @NonNull l: ReferenceLayoutProvider): ObservableListAdapter(d, l) {

    override fun getHolder(layout: View): ObservableReferenceViewHolder {
        return ShelfItemHolder(layout)
    }

    /*
     * This method is only used if we're searching for data in a group
     */
    override fun findChildById(group: ReferenceItem, id: Int) = null
}