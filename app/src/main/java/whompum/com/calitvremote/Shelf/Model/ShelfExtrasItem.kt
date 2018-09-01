package whompum.com.calitvremote.Shelf.Model

import whompum.com.calitvremote.Networking.Model.ReferenceItem

class ShelfExtrasItem (parentRef: String,
                      ref: String,
                      var extrasTitle: String = "",
                      itemValue: String = ""):  ReferenceItem(parentRef, ref, itemValue){

    constructor(parentRef: String, ref: String, value: String): this(parentRef = parentRef, ref = ref, itemValue = value)

}