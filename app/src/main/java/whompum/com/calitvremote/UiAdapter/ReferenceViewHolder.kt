package whompum.com.calitvremote.UiAdapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.californiadreamshostel.firebaseclient.Reference
import com.californiadreamshostel.firebaseclient.ReferenceItem

abstract class ReferenceViewHolder(val layout: View): RecyclerView.ViewHolder(layout) {

    fun bind(ref: Reference, dataType: Int){

        if(ref is ReferenceItem){
            if(ref.hasChildren()) {
                bindRoot(ref, dataType)
                ref.getChildren().forEach { bind(it, dataType) }
            }else bindItem(ref, dataType)

        }else
            bindUnknown(ref, dataType)

    }

    protected abstract fun bindItem(ref: ReferenceItem, dataType: Int)

    protected open fun bindRoot(ref: ReferenceItem, dataType: Int){}
    protected open fun bindUnknown(ref: Reference, dataType: Int){}

    protected fun setValue(id: Int, value: String){
        layout.findViewById<TextView>(id).text = value
    }

}