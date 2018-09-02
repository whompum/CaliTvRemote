package whompum.com.calitvremote.UiAdapter

import android.support.annotation.LayoutRes

import com.californiadreamshostel.firebaseclient.Reference

abstract class ReferenceLayoutProvider{

    companion object {
        const val TYPE_NOTHING = -1
    }

    private val itemTypes = initializeItemTypes()

    abstract fun initializeItemTypes(): HashSet<Int>

    @LayoutRes
    abstract fun getLayout(viewType: Int): Int

    open fun getViewtype(ref: Reference) = TYPE_NOTHING

}