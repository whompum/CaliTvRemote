package whompum.com.calitvremote.Rentals.UiAdapter

import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem
import whompum.com.calitvremote.UiAdapter.ReferenceLayoutProvider
import whompum.com.calitvremote.R.*

class RentalsLayoutProvider: ReferenceLayoutProvider() {

    companion object {
        const val TYPE_ITEM = 0
    }

    override fun initializeItemTypes(): HashSet<Int> {
        val types = HashSet<Int>()

        types.add(TYPE_ITEM)

        return types
    }

    override fun getLayout(viewType: Int): Int {
        return layout.rentals_item_view
    }

    override fun getViewtype(ref: Reference): Int {

        if(ref is ReferenceItem)
            return TYPE_ITEM

        return super.getViewtype(ref)
    }
}