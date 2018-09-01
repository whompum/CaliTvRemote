package whompum.com.calitvremote.Main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.SparseArray

import whompum.com.calitvremote.Main.Tabs.FragmentTabAdapter
import whompum.com.calitvremote.Rentals.RentalsFragment

import kotlinx.android.synthetic.main.main.*
import whompum.com.calitvremote.Shelf.ContactFragment
import whompum.com.calitvremote.Account.Login
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Slides.RemoteController
import whompum.com.calitvremote.Util.SimplePageChangeListener
import whompum.com.calitvremote.Main.Tabs.TabItem
import whompum.com.calitvremote.Shelf.AncilliaryExtrasFragment
import whompum.com.calitvremote.Shelf.ShelfFragment

class MainActivity : AppCompatActivity(){

    val FRAG_SIZE = 3
    val DASHBOARD = 0
    val RENTALS = 1
    val CONTACT = 2

    private val tabs = fun (): SparseArray<TabItem>{
        val tabs = SparseArray<TabItem>(FRAG_SIZE)

        for(n in 0 until FRAG_SIZE) {

            var item: TabItem? = null

            if(n == DASHBOARD)
                item = TabItem(R.drawable.icon_dashboard, getString(R.string.string_shelf))

            if(n == RENTALS)
                item = TabItem(R.drawable.icon_bike, getString(R.string.string_rentals))

            if(n == CONTACT)
                item = TabItem(R.drawable.icon_people, getString(R.string.string_contact))

            tabs.put(n, item)
        }

        return tabs
    }

    private val fragments = fun (): ArrayList<Fragment>{
        val data = ArrayList<Fragment>()

        for(n in 0 until FRAG_SIZE){

            var fragment: Fragment? = null

            if(n == DASHBOARD)
                fragment = AncilliaryExtrasFragment.newInstance()

            if(n == RENTALS)
                fragment = RentalsFragment.newInstance()

            if(n == CONTACT)
                fragment = ContactFragment.newInstance()

            data.add(fragment!!)
        }

        return data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        local_tab_pager.adapter = FragmentTabAdapter(tabs())

        //Remove touch handling from the Tab view Pager, and instead allow the content to consume the touch.
        local_tab_pager.setOnTouchListener{_, event -> id_pager.onTouchEvent(event) }

        id_pager.adapter = FragmentAdapter(fragments(), supportFragmentManager)

        id_pager.addOnPageChangeListener(PageChangeListener())

        local_id_login.setOnClickListener{ launchActivity(Login::class.java) }

        id_local_remote.setOnClickListener{ launchActivity(RemoteController::class.java) }

    }

    private fun launchActivity(cls: Class<*>){
        startActivity(Intent(this, cls))
    }

    inner class PageChangeListener: SimplePageChangeListener(){
        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

        }

        override fun onPageSelected(position: Int) {
            local_tab_pager.currentItem = position
        }
    }


}
