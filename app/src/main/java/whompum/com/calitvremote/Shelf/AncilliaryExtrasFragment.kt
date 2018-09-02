package whompum.com.calitvremote.Shelf

import android.support.v4.app.Fragment
import com.californiadreamshostel.firebaseclient.DataSchema
import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Shelf.Model.ShelfExtrasItem
import whompum.com.calitvremote.Shelf.Model.ShelfItem
import whompum.com.calitvremote.Shelf.Model.ShelfItemHeader
import whompum.com.calitvremote.Shelf.UiAdapter.AncilliaryExtrasLayoutProvider

class AncilliaryExtrasFragment: ShelfFragment() {

    companion object {
        fun newInstance(): Fragment {
            return AncilliaryExtrasFragment()
        }
    }

    override fun initializeAdapterData(): List<Reference> {
        val data = ArrayList<Reference>()

        data.add(ShelfItemHeader(DataSchema.ANCILLIARY))
        data.add(ShelfItem(DataSchema.ANCILLIARY_GROUP, DataSchema.ANCILLIARY_ONE, "Lock", R.drawable.icon_lock))
        data.add(ShelfItem(DataSchema.ANCILLIARY_GROUP, DataSchema.ANCILLIARY_TWO, "Laundry", R.drawable.icon_laundry_machine))
        data.add(ShelfItem(DataSchema.ANCILLIARY_GROUP, DataSchema.ANCILLIARY_THREE, "Towel", R.drawable.icon_towel))

        data.add(ShelfItemHeader(DataSchema.EXTRAS))
        data.add(ShelfExtrasItem(DataSchema.EXTRAS_GROUP, DataSchema.EXTRAS_BREAKFAST, extrasTitle =  "Breakfast Time"))
        data.add(ShelfExtrasItem(DataSchema.EXTRAS_GROUP, DataSchema.EXTRAS_CHECKIN, extrasTitle ="Checkin"))
        data.add(ShelfExtrasItem(DataSchema.EXTRAS_GROUP, DataSchema.EXTRAS_CHECKOUT, extrasTitle = "Checkout"))

        return data
    }

    override fun fetchLayoutProvider() = AncilliaryExtrasLayoutProvider()

    override fun fetchResources(): Set<String> {
        val queryables = HashSet<String>()
        queryables.add(DataSchema.ANCILLIARY_GROUP)
        queryables.add(DataSchema.EXTRAS_GROUP)

        return queryables
    }

    override fun getReferenceConvertorFactory(controllerRef: String): (String, String, String) -> ReferenceItem {

        if(controllerRef == DataSchema.EXTRAS_GROUP)
            return {parent, key, value ->
                ShelfExtrasItem(parent, key, value)
            }

        return super.getReferenceConvertorFactory(controllerRef)
    }

    override fun getReferenceRoot(ref: String): String? {
        if(ref.contains(DataSchema.ANCILLIARY.toLowerCase()))
            return DataSchema.ANCILLIARY_GROUP

        else if(ref.contains(DataSchema.EXTRAS.toLowerCase()))
            return DataSchema.EXTRAS_GROUP

        return null
    }
}