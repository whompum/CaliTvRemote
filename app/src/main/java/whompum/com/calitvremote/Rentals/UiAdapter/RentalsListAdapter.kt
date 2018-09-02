package whompum.com.calitvremote.Rentals.UiAdapter

import android.view.View

import com.californiadreamshostel.firebaseclient.DataSchema
import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem

import whompum.com.calitvremote.UiAdapter.ReferenceLayoutProvider
import whompum.com.calitvremote.UiAdapter.ObservableListAdapter
import whompum.com.calitvremote.UiAdapter.ObservableReferenceViewHolder
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Rentals.RentalsItem

class RentalsListAdapter (d: List<RentalsItem>, l: ReferenceLayoutProvider): ObservableListAdapter(d,l){

    var onItemSelectedListener = fun (ref: Reference){}

    override fun getHolder(layout: View): ObservableReferenceViewHolder {
        return RentalHolder(layout, { i -> onItemSelectedListener( getItemAt(i)!! )})
    }

    override fun findChildById(group: ReferenceItem, id: Int): ReferenceItem? {

        when(id){
            R.id.local_three_hour_value -> return group.findChildBy(DataSchema.RENTAL_THREE_HOURS)
            R.id.local_full_day_value -> return group.findChildBy(DataSchema.RENTAL_FULL_DAY)
        }

        return null
    }

}