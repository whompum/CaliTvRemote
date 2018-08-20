package whompum.com.calitvremote.Rentals

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import whompum.com.calitvremote.QueryableFragment

import whompum.com.calitvremote.R

class RentalsFragment: QueryableFragment() {

    companion object {

        val LAYOUT = R.layout.rentals_layout
        fun newInstance(args: Bundle?, queryables: List<String>): Fragment{
            val frag = RentalsFragment()
            frag.arguments = args
            frag.queryables = queryables

            return frag
        }

    }

    private val itemAdapter = RentalsItemAdapter(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(LAYOUT, container, false)

        layout.findViewById<FloatingActionButton>(R.id.local_id_fab) .setOnClickListener{

        }

    return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}