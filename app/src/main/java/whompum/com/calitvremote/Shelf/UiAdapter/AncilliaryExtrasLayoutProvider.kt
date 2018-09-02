package whompum.com.calitvremote.Shelf.UiAdapter

import com.californiadreamshostel.firebaseclient.Reference
import whompum.com.calitvremote.UiAdapter.ReferenceLayoutProvider
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Shelf.Model.ShelfExtrasItem
import whompum.com.calitvremote.Shelf.Model.ShelfItem
import whompum.com.calitvremote.Shelf.Model.ShelfItemHeader

class AncilliaryExtrasLayoutProvider: ReferenceLayoutProvider() {

    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_EXTRAS = 1
        const val TYPE_HEADER = 2
    }

    override fun initializeItemTypes(): HashSet<Int> {
        val itemTypes = HashSet<Int>()

        itemTypes.add(TYPE_ITEM)
        itemTypes.add(TYPE_EXTRAS)
        itemTypes.add(TYPE_HEADER)

        return itemTypes
    }

    override fun getViewtype(ref: Reference): Int {

        if(ref is ShelfItem)
            return TYPE_ITEM

        if(ref is ShelfItemHeader)
            return TYPE_HEADER

        if(ref is ShelfExtrasItem)
            return TYPE_EXTRAS

        return super.getViewtype(ref)
    }

    override fun getLayout(viewType: Int): Int {

        if(viewType == TYPE_ITEM)
            return R.layout.price_item_view

        if(viewType == TYPE_HEADER)
            return R.layout.price_item_header

        if(viewType == TYPE_EXTRAS)
            return R.layout.price_item_extras_view

        return -1
    }

}