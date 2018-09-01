package whompum.com.calitvremote.Shelf

import android.support.v4.app.Fragment
import whompum.com.calitvremote.Shelf.Model.ShelfItem
import whompum.com.calitvremote.Shelf.Model.ShelfItemHeader

import whompum.com.calitvremote.Networking.Data.DataSchema
import whompum.com.calitvremote.Networking.Model.Reference
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Shelf.UiAdapter.ContactLayoutProvider

class ContactFragment: ShelfFragment() {

    companion object {
        fun newInstance(): Fragment{
            return ContactFragment()
        }

    }

    override fun initializeAdapterData(): List<Reference> {
        val data = ArrayList<Reference>()

        data.add(ShelfItemHeader(DataSchema.CONTACT))
        data.add(ShelfItem(DataSchema.CONTACT_GROUP, DataSchema.CONTACT_ONE, "Location", R.drawable.icon_location))
        data.add(ShelfItem(DataSchema.CONTACT_GROUP, DataSchema.CONTACT_TWO, "Phone", R.drawable.icon_phone))
        data.add(ShelfItem(DataSchema.CONTACT_GROUP, DataSchema.CONTACT_THREE, "Email", R.drawable.icon_email))

        data.add(ShelfItemHeader(DataSchema.SOCIAL))
        data.add(ShelfItem(DataSchema.SOCIAL_GROUP, DataSchema.SOCIAL_ONE, "Instagram", R.drawable.icon_facebook))
        data.add(ShelfItem(DataSchema.SOCIAL_GROUP, DataSchema.SOCIAL_TWO, "Facebook", R.drawable.icon_facebook))
        data.add(ShelfItem(DataSchema.SOCIAL_GROUP, DataSchema.SOCIAL_THREE, "Facebook-Group", R.drawable.icon_facebook_group))

        return data
    }

    override fun fetchLayoutProvider() = ContactLayoutProvider()

    override fun fetchResources(): Set<String> {
        val queryables = HashSet<String>()
        queryables.add(DataSchema.CONTACT_GROUP)
        queryables.add(DataSchema.SOCIAL_GROUP)
        return queryables
    }

    override fun getReferenceRoot(ref: String): String? {

        if(ref.contains(DataSchema.CONTACT.toLowerCase()))
            return DataSchema.CONTACT_GROUP

        else if(ref.contains(DataSchema.SOCIAL.toLowerCase()))
            return DataSchema.SOCIAL_GROUP

        return null
    }

}