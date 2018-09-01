package whompum.com.calitvremote.Networking.UiAdapter

import android.support.annotation.IdRes

interface OnValueChanged {
    fun onValueChange(newValue: String, @IdRes id: Int, position: Int)
}