package whompum.com.calitvremote.Rentals

import android.view.View
import android.widget.TextView
import whompum.com.calitvremote.TvItem.Adapter.TvItemAdapterBinder
import whompum.com.calitvremote.TvItem.Adapter.TvItemBaseAdapter
import whompum.com.calitvremote.R

class RentalsItemAdapter(var data: List<TvItemAdapterBinder>?): TvItemBaseAdapter(data) {

    companion object {
        const val LAYOUT = R.layout.rentals_item_view
    }

    override fun getLayout(viewType: Int): Int {
        return LAYOUT
    }

    override fun makeHolder(layout: View): Holder {
        return RentalsHolder(layout)
    }

    override fun getItemViewType(item: TvItemAdapterBinder): Int {
        return LAYOUT
    }

    inner class RentalsHolder(private val rentalsContent: View) : Holder(rentalsContent){
        override fun bind(data: TvItemAdapterBinder) {
            data as RentalsItem

            val title = data.title
            val threeHours: String? = data.threeHoursPrice
            val fullDay: String? = data.fullDayPrice

            rentalsContent.findViewById<TextView>(R.id.local_rental_title)
                        .text = title

            if(threeHours != null)
                rentalsContent.findViewById<TextView>(R.id.local_three_hour_value)
                        .text = threeHours

            if(fullDay != null)
                rentalsContent.findViewById<TextView>(R.id.local_full_day_value)
                        .text = fullDay

        }
    }

}