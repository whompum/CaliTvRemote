package whompum.com.calitvremote.TvItem

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import whompum.com.calitvremote.QueryableFragment
import whompum.com.calitvremote.R
import whompum.com.calitvremote.TvItem.Adapter.TvItemAdapter

open class TvItemFragment: QueryableFragment() {

    companion object {

        val LAYOUT = R.layout.price_item
        fun newInstance(args: Bundle, queryables: List<String>): Fragment{
            val frag = TvItemFragment()
            frag.arguments = args
            frag.queryables = queryables

            return frag
        }

    }

    private val itemAdapter = TvItemAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Fetch the node from the argument
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(LAYOUT, container, false)

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}