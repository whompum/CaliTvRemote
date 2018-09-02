package whompum.com.calitvremote.Util

import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem

class ListUtils {

    companion object {

        /*
         * Called when data already exists in the list
         * and we just  want to update its items with more current values
         */

        fun updateGroupChildren(updates: ReferenceItem, list: List<Reference>): Boolean{

            var hasChanges = false

            if(updates.hasChildren())
                for(child in updates.getChildren())
                    hasChanges = updateItem(child, list)
            else hasChanges = updateItem(updates, list)


            return hasChanges
        }

        fun updateItem(item: ReferenceItem, list: List<Reference>): Boolean{
            val updateItem = list.find { it.reference == item.reference }

            var hasUpdates = false

            if(updateItem != null && updateItem is ReferenceItem) {
                updateItem.value = item.value; updateItem.initializeValue = item.value
                hasUpdates = true
            }

            return hasUpdates
        }

        /*
         * Attempts to add an item to the Adapter.
         * If the adapter already contains the item, it will
         * simple return false, and not add anything.
         */
        fun addItem(ref: Reference, data: List<Reference>): Boolean{
            data.forEach { //Data already exists in the list
                if(it.reference == ref.reference)
                    return false
            }

            if(data is ArrayList) { //If we got this far, then the data doesn't exist in the adapter
                data.add(ref)
                return true
            }

            return false
        }

        /*
         * Updates an entire group object in the adapter
         */
        fun updateGroupItem(group: Reference, list: List<Reference>): Int{

            //Find the child of the dataset whose ref matches the group ref
            for(a in 0 until list.size)
                if(list[a].reference == group.reference)
                    if(list is ArrayList) {
                        list[a] = group
                        return a
                    }

        return -1
        }

        public fun deleteItem(ref: Reference, data: List<Reference>): Int{

            for(a in 0 until data.size)
                if(data[a].reference == ref.reference)
                    if(data is ArrayList) {
                        data.removeAt(a)
                        return a
                    }
        return -1
        }

    }




}