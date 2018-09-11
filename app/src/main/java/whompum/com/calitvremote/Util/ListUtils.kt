package whompum.com.calitvremote.Util

import android.util.Log
import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem

class ListUtils {

    companion object {

        /*
         * Called when data already exists in the list
         * and we just  want to update its items with more current values
         */

        fun updateItem(changedItem: ReferenceItem, list: List<Reference>): Boolean{
              //Find the item we want to update.
            val subject = list.find { it.reference == changedItem.reference }

            if(subject != null && subject is ReferenceItem){
                //Check if we're updating a group
                if(changedItem.hasChildren()){
                    for(child in changedItem.getChildren()){
                    //Check if the subject has the children the changedItem has. If not, simply inject them
                        if( !subject.hasChild(child.reference) ) {
                            subject.addChild(child)
                        }else{ //Simply update each child item
                            val subjectChild = subject.findChildBy(child.reference)
                            subjectChild?.value = child.value
                            subjectChild?.initializeValue = child.initializeValue
                        }
                    }
                }else{
                    subject.value = changedItem.value
                    subject.initializeValue = changedItem.value
                }
            }

            return true
        }

        //Set initial value too

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

        fun deleteItem(ref: Reference, data: List<Reference>): Int{

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