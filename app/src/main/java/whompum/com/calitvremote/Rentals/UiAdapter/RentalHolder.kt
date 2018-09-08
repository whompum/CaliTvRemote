package whompum.com.calitvremote.Rentals.UiAdapter

import android.util.Log
import android.view.View
import com.californiadreamshostel.firebaseclient.DataSchema
import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem
import whompum.com.calitvremote.UiAdapter.ObservableReferenceViewHolder
import whompum.com.calitvremote.R

class RentalHolder(v: View,
                   private val longClickListener: (Int) -> Unit): ObservableReferenceViewHolder(v, R.id.local_three_hour_value, R.id.local_full_day_value) {

    init {
        v.setOnLongClickListener {
            longClickListener(adapterPosition)
            true
        }
    }

    override fun bind(ref: Reference, dataType: Int) {
        super.bind(ref, dataType)
        setValue(R.id.local_rental_title, ref.reference)
    }

    override fun bindRoot(ref: ReferenceItem, dataType: Int) {
        setValue(R.id.local_rental_title, ref.reference)
    }

    override fun bindItem(ref: ReferenceItem, dataType: Int) {

        unbind()

        val reference = ref.reference
        val value = ref.value

        Log.i("RENTAL_ISSUE", "REFERENCE: $reference")
        Log.i("RENTAL_ISSUE", "VALUE: $value")

        if(reference == DataSchema.RENTAL_THREE_HOURS)
            setValue(R.id.local_three_hour_value, value)

        if(reference == DataSchema.RENTAL_FULL_DAY)
            setValue(R.id.local_full_day_value, value)

        rebind()

    }

}