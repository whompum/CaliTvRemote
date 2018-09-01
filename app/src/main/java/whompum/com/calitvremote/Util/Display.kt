package whompum.com.calitvremote.Util

import android.content.Context
import android.widget.Toast
import whompum.com.calitvremote.R

class Display {

    companion object {
        fun display(ctx: Context, msg: String){
            val t = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT)
            t.view?.setBackgroundResource(R.drawable.background_round_rect_orange)

            t.show()
        }
    }

}