package whompum.com.calitvremote.Shelf

import android.support.annotation.LayoutRes
import android.util.Log
import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem
import com.californiadreamshostel.firebaseclient.SimpleEventListener

import whompum.com.calitvremote.UiAdapter.EditableDataFragment
import whompum.com.calitvremote.UiAdapter.ReferenceLayoutProvider
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Shelf.Model.ShelfItem
import whompum.com.calitvremote.Shelf.UiAdapter.ShelfListAdapter
import whompum.com.calitvremote.Util.ListUtils

abstract class ShelfFragment: EditableDataFragment() {

    @LayoutRes
    override fun fetchLayout() = R.layout.list

    protected abstract fun initializeAdapterData(): List<Reference>
    protected abstract fun fetchLayoutProvider(): ReferenceLayoutProvider

    override fun initializeAdapter() = ShelfListAdapter(initializeAdapterData(), fetchLayoutProvider())

    override fun onDataChange(): (ReferenceItem, Int) -> Unit {
        return {group, type ->

            Log.i("CLIENT_TEST", "REF: ${group.reference}")

            if (type == SimpleEventListener.ADDED || type == SimpleEventListener.CHANGE)
                if (ListUtils.updateGroupChildren(group, listAdapter.data))
                    listAdapter.notifyDataSetChanged()

        }
    }

    override fun getReferenceConvertorFactory(controllerRef: String): (String, String, String) -> ReferenceItem {
        return{parent, ref, value ->
            ShelfItem(parent, ref, value)
        }
    }
}