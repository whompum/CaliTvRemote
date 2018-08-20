package whompum.com.calitvremote

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import whompum.com.calitvremote.TvItem.Adapter.TvItemAdapter

class ContactFragment: Fragment() {

    companion object {

        val LAYOUT = R.layout.contact_layout
        fun newInstance(args: Bundle?): Fragment{
            val frag = ContactFragment()
            frag.arguments = args

            return frag
        }

    }

    private val itemAdapter = TvItemAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(LAYOUT, container, false)

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}