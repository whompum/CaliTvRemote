package whompum.com.calitvremote.Util

import android.support.v4.view.ViewPager

abstract class SimplePageChangeListener: ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(p0: Int) {

    }

    abstract override fun onPageScrolled(p0: Int, p1: Float, p2: Int);

    abstract override fun onPageSelected(p0: Int)
}