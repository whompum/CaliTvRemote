package whompum.com.calitvremote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.SparseArray

import whompum.com.calitvremote.Util.FragmentTabAdapter
import whompum.com.calitvremote.Rentals.RentalsFragment
import whompum.com.calitvremote.TvItem.TvItemFragment

import kotlinx.android.synthetic.main.main.*
import whompum.com.calitvremote.Util.SimplePageChangeListener
import whompum.com.calitvremote.Util.TabItem

class MainActivity : AppCompatActivity(){

    val FRAG_SIZE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        id_pager.adapter = FragmentAdapter(
                fetchFragments(),
                supportFragmentManager
        )

        val tabs = SparseArray<TabItem>()

        for(n in 0 until 3)
            tabs.put(n, populateTabByPosition(n))

        local_tab_pager.adapter = FragmentTabAdapter(tabs)

        id_pager.addOnPageChangeListener(object : SimplePageChangeListener(){
            override fun onPageSelected(position: Int) {
                local_tab_pager.currentItem = position
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                //tab_pager.scrollX = p2
            }
        })

    }

    protected fun populateTabByPosition(position: Int): TabItem?{

        if(position == 0) return TabItem(R.drawable.icon_email, getString(R.string.string_shelf))
        if(position == 1) return TabItem(R.drawable.icon_bike, getString(R.string.string_rentals))
        if(position == 2) return TabItem(R.drawable.icon_people, getString(R.string.string_contact))

        return null
    }

    protected fun populateFragmentByPosition(position: Int): Fragment?{
        if(position == 0) return TvItemFragment.newInstance(Bundle())
        if(position == 1) return RentalsFragment.newInstance(null)
        if(position == 2) return ContactFragment.newInstance(null)

        return null
    }

    private fun fetchFragments(): List<Fragment>{

        val data = ArrayList<Fragment>()

        for(n in 0 until FRAG_SIZE)
            data.add(n, populateFragmentByPosition(n)!!)

        return data
    }

}
