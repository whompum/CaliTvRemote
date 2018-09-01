package whompum.com.calitvremote.Shelf.UiAdapter

import android.view.View
import android.widget.ImageView
import whompum.com.calitvremote.Networking.UiAdapter.ObservableReferenceViewHolder
import whompum.com.calitvremote.Networking.Model.Reference
import whompum.com.calitvremote.Networking.Model.ReferenceItem
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Shelf.Model.ShelfExtrasItem
import whompum.com.calitvremote.Shelf.Model.ShelfItem

class ShelfItemHolder(v: View):
        ObservableReferenceViewHolder(v, R.id.id_item_value) {

    override fun bindItem(ref: ReferenceItem, dataType: Int) {
        unbind()

        //Should be an element

        var title = ""

        if(ref is ShelfItem) {
            layout.findViewById<ImageView>(R.id.id_icon).setImageResource(ref.imageRes)
            title = ref.title
        }

        if(ref is ShelfExtrasItem)
            title = ref.extrasTitle

        setValue(R.id.id_item_title, title)

        setValue(R.id.id_item_value, ref.value)

        rebind()
    }

    override fun bindUnknown(ref: Reference, dataType: Int) {
        setValue(R.id.local_id_header, ref.reference)
    }
}