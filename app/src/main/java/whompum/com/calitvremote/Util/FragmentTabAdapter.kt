package whompum.com.calitvremote.Util

import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import whompum.com.calitvremote.R

class FragmentTabAdapter(var data: SparseArray<TabItem>?): PagerAdapter() {

    companion object {
        val LAYOUT = R.layout.layout_tab_item
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layout = LayoutInflater.from(container.context)
                .inflate(LAYOUT, null, false)

        //Assign data based on position

        val item = data!![position]

        layout.findViewById<ImageView>(R.id.id_icon)
                .setImageResource(item.imageRes)

        layout.findViewById<TextView>(R.id.id_title)
                .text = item.title

        container.addView(layout)

        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, subject: Any): Boolean {
        return view === subject
    }

    override fun getCount(): Int {

        if(data === null) return 0

        return data!!.size()
    }

}