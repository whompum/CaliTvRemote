package whompum.com.calitvremote.TvItem.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class TvItemBaseAdapter(var items: List<TvItemAdapterBinder>?): RecyclerView.Adapter<TvItemBaseAdapter.Holder>() {

    override fun onCreateViewHolder(container: ViewGroup, position: Int): Holder {
         return makeHolder(
                 LayoutInflater.from(container.context)
                         .inflate(getLayout(position), container, false)
         )
    }

    override fun getItemCount(): Int {
        if(items === null) return 0

        return items?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items?.get(position)!!)
    }

    override fun getItemViewType(position: Int): Int {
        val type = getItemViewType(items!![position])

        return type
    }

    protected abstract fun getLayout(viewType: Int): Int
    protected abstract fun makeHolder(layout: View): Holder
    protected abstract fun getItemViewType(item: TvItemAdapterBinder) : Int

    public fun swapDataset(items: List<TvItemAdapterBinder>){
        this.items = items
        notifyDataSetChanged()
    }

    abstract inner class Holder(val content: View): RecyclerView.ViewHolder(content){
        abstract fun bind(data: TvItemAdapterBinder)
    }

}