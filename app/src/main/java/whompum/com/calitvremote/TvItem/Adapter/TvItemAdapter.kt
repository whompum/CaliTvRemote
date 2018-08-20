package whompum.com.calitvremote.TvItem.Adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import whompum.com.calitvremote.TvItem.Model.ExtrasTvItem
import whompum.com.calitvremote.TvItem.Model.TvItem
import whompum.com.calitvremote.TvItem.Model.TvItemHeader
import whompum.com.calitvremote.R

class TvItemAdapter(val data: List<TvItemAdapterBinder>?): TvItemBaseAdapter(data) {

    companion object {
        const val ITEM_DATA = 0
        const val ITEM_EXTRAS_DATA = 1
        const val ITEM_HEADER = 2
    }

    override fun getLayout(viewType: Int): Int {

        if(viewType == ITEM_EXTRAS_DATA) return R.layout.price_item_extras_view

        //Return an appropriate layout res based to accommodate the ItemType
        else if(viewType == ITEM_DATA) return R.layout.price_item_view

        return R.layout.price_item_header
    }

    override fun makeHolder(layout: View): Holder {
        return PriceItemHolder(layout)
    }

    override fun getItemViewType(item: TvItemAdapterBinder): Int {
        if(item is ExtrasTvItem)
            return ITEM_EXTRAS_DATA

        if(item is TvItem)
            return ITEM_DATA

        if(item is TvItemHeader)
            return ITEM_HEADER

        return -1
    }

    inner class PriceItemHolder(val layout: View): Holder(layout){
        override fun bind(data: TvItemAdapterBinder) {
            if(data is ExtrasTvItem)
                bindExtrasItem(data)
            if(data is TvItem)
                bindPriceItem(data)
            if(data is TvItemHeader)
                bindPriceItemHeader(data)
        }

        private fun bindPriceItem(data: TvItem){

            val title: String? = data.title
            val price: String? = data.price
            val iconRes = data.imageRes

            if(title != null)
                layout.findViewById<TextView>(R.id.id_title)
                        .text = title

            if(price!= null)
                layout.findViewById<TextView>(R.id.id_value)
                        .text = price

            if(iconRes != -1)
                layout.findViewById<ImageView>(R.id.id_icon)
                        .setImageResource(iconRes)

        }

        private fun bindExtrasItem(data: ExtrasTvItem){
            val title: String? = data.extrasTitle
            val price: String? = data.extrasPrice

            if(title != null)
                layout.findViewById<TextView>(R.id.id_title)
                        .text = title

            if(price!= null)
                layout.findViewById<TextView>(R.id.id_value)
                        .text = price

            Log.i("TEST","Binding extras")

        }

        private fun bindPriceItemHeader(data: TvItemHeader){
            layout.findViewById<TextView>(R.id.local_header)
                    .text = data.groupTitle
        }

    }

}