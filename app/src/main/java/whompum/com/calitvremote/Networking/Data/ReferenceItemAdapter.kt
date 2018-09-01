package whompum.com.calitvremote.Networking.Data

import com.google.firebase.database.DataSnapshot
import whompum.com.calitvremote.Networking.Model.ReferenceItem

class ReferenceItemAdapter(val group: String, var itemFactory: ((String, String, String) -> ReferenceItem)? = null) {

    companion object {
        const val ROOT = "Root"
    }

    fun getData(data: DataSnapshot): ReferenceItem?{

        if(!data.exists())
            return null

        val parentRef = data.ref.parent?.key
        val key = data.key!!
        val value = data.value

        val item = getItem((parentRef) ?: ROOT, key, "" )

        for(child in data.children) {
            val childItem = getData(child)
                if(childItem != null)
                    item.addChild(childItem)
        }

        if(item.getChildren().isEmpty() && value != null) //Is a raw reference
            item.value = value.toString()

        return item
    }

    /*
     * Allows subclasses to provide a domain specific implementation
     */
    fun getItem(pK: String, k: String, v: String): ReferenceItem{
        return (itemFactory?.invoke(pK, k, v)) ?: ReferenceItem(pK, k, v)
    }



}




