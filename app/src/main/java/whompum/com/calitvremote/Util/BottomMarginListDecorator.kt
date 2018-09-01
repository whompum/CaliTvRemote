package whompum.com.calitvremote.Util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class BottomMarginListDecorator(val bO: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        //Check if view is last in the `list`
        if(view == parent.layoutManager?.findViewByPosition(parent.adapter?.itemCount!!.minus(1)))
            outRect.bottom = bO

    }
}