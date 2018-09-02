package whompum.com.calitvremote.UiAdapter

import android.support.annotation.IdRes

interface OnValueChanged {
    fun onValueChange(newValue: String, @IdRes id: Int, position: Int)
}