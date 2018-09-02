package whompum.com.calitvremote.UiAdapter

import android.text.Editable
import android.text.TextWatcher

class ReferenceTextWatcher(val id: Int, val listener: (String, Int) -> Unit): TextWatcher {

    override fun afterTextChanged(editor: Editable?) {
        listener(editor?.toString()!!, id)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}