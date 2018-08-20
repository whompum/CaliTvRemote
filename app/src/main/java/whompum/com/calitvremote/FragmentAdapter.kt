package whompum.com.calitvremote

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

class FragmentAdapter(var data: List<Fragment>, fm: FragmentManager): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        Log.i("TEST", "Estoy Aqui")
        return data[position]
    }

    override fun getCount(): Int {
        return data.size
    }

}