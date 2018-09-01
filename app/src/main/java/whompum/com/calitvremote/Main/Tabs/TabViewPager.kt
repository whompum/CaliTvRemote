package whompum.com.calitvremote.Main.Tabs

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class TabViewPager(ctx: Context, set: AttributeSet): ViewPager(ctx, set){
    override fun onTouchEvent(ev: MotionEvent?) = false
}