package whompum.com.calitvremote.Slides

import com.californiadreamshostel.firebaseclient.DataSchema
import com.californiadreamshostel.firebaseclient.ReferenceItem

class SlideItem(parent: String, reference: String, value: String  = ""): ReferenceItem(parent, reference, value) {

    fun isActive() = findChildBy(DataSchema.SLIDES_ACTIVE)
            ?.value?.toInt() == DataSchema.SLIDE_ACTIVE_VALUE

    fun setActiveState(value: Int){
        findChildBy(DataSchema.SLIDES_ACTIVE)
                ?.value = value.toString()
    }

}