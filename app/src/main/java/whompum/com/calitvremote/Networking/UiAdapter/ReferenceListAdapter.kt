package whompum.com.calitvremote.Networking.UiAdapter

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import whompum.com.calitvremote.Networking.Model.Reference

abstract class ReferenceListAdapter(var data: List<Reference> = ArrayList(),
                                    @NonNull val layoutProvider: ReferenceLayoutProvider):
        RecyclerView.Adapter<ReferenceViewHolder>() {


    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ReferenceViewHolder {
        return makeHolder(LayoutInflater.from(container.context).inflate(layoutProvider.getLayout(viewType), container, false))
    }

    override fun onBindViewHolder(holder: ReferenceViewHolder, index: Int) {
        holder.bind(data[index], getItemViewType(index))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return layoutProvider.getViewtype(data[position])
    }

    fun getItemAt(pos: Int) = if(pos < itemCount && pos > -1) data[pos] else null

    /*
    fun swapDataset(data: List<Reference>){
        this.data = data
        notifyDataSetChanged()
    }
    */

    protected abstract fun makeHolder(layout: View): ReferenceViewHolder

}