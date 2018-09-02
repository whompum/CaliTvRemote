package whompum.com.calitvremote.Slides.UiAdapter

import com.californiadreamshostel.firebaseclient.Reference
import whompum.com.calitvremote.UiAdapter.ReferenceLayoutProvider
import whompum.com.calitvremote.R

class SlideLayoutProvider: ReferenceLayoutProvider() {

    companion object {
        const val TYPE_ITEM = 1
    }

    override fun initializeItemTypes(): HashSet<Int> {
        val itemTypes = HashSet<Int>()

        itemTypes.add(TYPE_ITEM)

        return itemTypes
    }

    override fun getLayout(viewType: Int) = R.layout.slide_item_view

    override fun getViewtype(ref: Reference) = TYPE_ITEM
}