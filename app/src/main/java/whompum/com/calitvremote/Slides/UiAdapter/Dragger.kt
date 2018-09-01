package whompum.com.calitvremote.Slides.UiAdapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class Dragger(val a: (Int, Int) -> Boolean): ItemTouchHelper.Callback(){

    override fun getMovementFlags(parent: RecyclerView, holder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(ItemTouchHelper.UP xor ItemTouchHelper.DOWN, 0)
    }

    override fun onMove(parent: RecyclerView, obj: RecyclerView.ViewHolder, sbj: RecyclerView.ViewHolder): Boolean {
        return a(obj.adapterPosition, sbj.adapterPosition)
    }

    override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
    }

    override fun isLongPressDragEnabled() = true
    override fun isItemViewSwipeEnabled() = false
}