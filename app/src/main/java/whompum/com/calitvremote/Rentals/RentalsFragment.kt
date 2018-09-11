package whompum.com.calitvremote.Rentals

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.californiadreamshostel.firebaseclient.DataSchema
import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem
import whompum.com.calitvremote.UiAdapter.EditableDataFragment
import whompum.com.calitvremote.UiAdapter.ObservableListAdapter
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Rentals.UiAdapter.RentalsLayoutProvider
import whompum.com.calitvremote.Rentals.UiAdapter.RentalsListAdapter

class RentalsFragment: EditableDataFragment(){

    companion object {
        fun newInstance(): Fragment {
            return RentalsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = super.onCreateView(inflater, container, savedInstanceState)

        layout!!.findViewById<FloatingActionButton>(R.id.id_fab).setOnClickListener{
            NewRentalDialog(activity as Context, onItemCreated()).show()
        }

        return layout
    }

    override fun fetchResources(): Set<String> {
        val resources = HashSet<String>()
        resources.add(DataSchema.RENTALS)

        return resources
    }

    @LayoutRes
    override fun fetchLayout(): Int {
        return R.layout.rentals_layout
    }

    override fun getReferenceRoot(ref: String): String {
        return DataSchema.RENTALS
    }

    override fun initializeAdapter(): ObservableListAdapter {
        val adapter = RentalsListAdapter(ArrayList(), RentalsLayoutProvider())
        adapter.onItemSelectedListener = onItemSelected()

        return adapter
    }

    override fun getReferenceConvertorFactory(controllerRef: String): (String, String, String) -> ReferenceItem {
        return {parentRef, ref, value ->
            RentalsItem(parentRef, ref, value)
        }
    }

    //Invoked by the adapter after an item has been selected (long press)
    private fun onItemSelected(): (ref: Reference) -> Unit{

        return {ref->

            if(ref is RentalsItem) {

                val msg = getString(R.string.string_delete_confirmation_body) + " ${ref.reference}"

                AlertDialog //Make a separate class
                        .Builder(context)
                        .setTitle(getString(R.string.string_delete_confirmation_header))
                        .setMessage(msg)
                        .setCancelable(true)
                        .setPositiveButton(android.R.string.yes, object: DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, position: Int) {
                                controllers.find { it.group == DataSchema.RENTALS }?.delete(ref)
                            }
                        }).show()
            }

        }

    }

    //Called after a new Rental item is created.
    private fun onItemCreated(): (t: RentalsItem) -> Unit{
        return {
            saveNew(it)
        }
    }
}