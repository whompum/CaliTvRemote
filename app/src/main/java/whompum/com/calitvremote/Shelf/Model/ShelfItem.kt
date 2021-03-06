package whompum.com.calitvremote.Shelf.Model

import com.californiadreamshostel.firebaseclient.ReferenceItem

class ShelfItem(parentRef: String,
                ref: String,
                var title: String = "",
                var imageRes: Int = -1,
                itemValue: String = "") : ReferenceItem(parentRef, ref, itemValue){

    constructor(parentRef: String,
                ref: String,
                value: String) : this(parentRef = parentRef, ref = ref, itemValue = value)

}