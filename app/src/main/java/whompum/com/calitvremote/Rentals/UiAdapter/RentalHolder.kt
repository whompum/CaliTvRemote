package whompum.com.calitvremote.Rentals.UiAdapter

import android.view.View
import whompum.com.calitvremote.Networking.UiAdapter.ObservableReferenceViewHolder
import whompum.com.calitvremote.Networking.Data.DataSchema
import whompum.com.calitvremote.Networking.Model.ReferenceItem
import whompum.com.calitvremote.R

class RentalHolder(v: View,
                   private val longClickListener: (Int) -> Unit): ObservableReferenceViewHolder(v, R.id.local_three_hour_value, R.id.local_full_day_value) {

    init {
        v.setOnLongClickListener {
            longClickListener(adapterPosition)
            true
        }
    }


    override fun bindRoot(ref: ReferenceItem, dataType: Int) {
        setValue(R.id.local_rental_title, ref.reference)
    }

    override fun bindItem(ref: ReferenceItem, dataType: Int) {

        unbind()

        val reference = ref.reference

        val value = ref.value

        if(reference == DataSchema.RENTAL_THREE_HOURS)
            setValue(R.id.local_three_hour_value, value)

        if(reference == DataSchema.RENTAL_FULL_DAY)
            setValue(R.id.local_full_day_value, value)

        rebind()

    }

}