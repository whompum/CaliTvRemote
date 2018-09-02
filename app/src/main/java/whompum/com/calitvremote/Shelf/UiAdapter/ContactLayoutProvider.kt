package whompum.com.calitvremote.Shelf.UiAdapter

import android.support.annotation.LayoutRes
import com.californiadreamshostel.firebaseclient.Reference
import whompum.com.calitvremote.UiAdapter.ReferenceLayoutProvider
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Shelf.Model.ShelfItem
import whompum.com.calitvremote.Shelf.Model.ShelfItemHeader

class ContactLayoutProvider: ReferenceLayoutProvider() {

    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_HEADER = 1
    }

    override fun initializeItemTypes(): HashSet<Int> {
        val itemTypes = HashSet<Int>()

        itemTypes.add(TYPE_ITEM)
        itemTypes.add(TYPE_HEADER)

        return itemTypes
    }

    @LayoutRes
    override fun getLayout(viewType: Int): Int {

        if(viewType == TYPE_ITEM)
            return R.layout.price_item_view

        if(viewType == TYPE_HEADER)
            return R.layout.price_item_header

        return -1
    }

    override fun getViewtype(ref: Reference): Int {

        if(ref is ShelfItemHeader)
            return TYPE_HEADER

        if(ref is ShelfItem)
            return TYPE_ITEM

        return super.getViewtype(ref)
    }
}