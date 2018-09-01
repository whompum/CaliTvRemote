package whompum.com.calitvremote.Slides.UiAdapter

import android.support.annotation.NonNull
import android.util.Log
import android.view.View
import whompum.com.calitvremote.Networking.Data.DataSchema
import whompum.com.calitvremote.Networking.UiAdapter.ReferenceLayoutProvider
import whompum.com.calitvremote.Networking.UiAdapter.ReferenceListAdapter
import whompum.com.calitvremote.Networking.UiAdapter.ReferenceViewHolder
import whompum.com.calitvremote.Slides.SlideItem
import whompum.com.calitvremote.Util.OnItemSelected
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class SlideListAdapter(private var items: ArrayList<SlideItem> = ArrayList(),
                       provider: ReferenceLayoutProvider): ReferenceListAdapter(items, provider){

    var onItemSelected = fun (itemSelected: SlideItem, itemUnselected: SlideItem?){}
    var onDurationClickListener = fun (item: SlideItem){}

    override fun makeHolder(layout: View): ReferenceViewHolder {
        val holder = SlideItemHolder(layout)

        holder.onItemSelected = ItemSelectedListener()
        holder.onDurationSelected = DurationClickListener()

        return holder
    }

    fun addItem(@NonNull item: SlideItem){
        //Append an item to the end of the list

        for(a in 0 until items.size) //Block duplicates; Reset data.
            if(items[a].reference == item.reference) {
                items[a] = item
                sort()
                notifyDataSetChanged()
                return
            }

        items.add(item) //Insert

        if(items.size > 1)
            sort()

        notifyDataSetChanged()

    }

    //Called by an ItemDecorator to move items around via drag
    fun itemMoved() : (Int, Int) -> Boolean{
        return {to, from ->
            //Keep the ordering $to THEN $from
            if (from < to) {
                for (i in from until to) {
                    Collections.swap(items, i, i + 1)
                }
            } else {
                for (i in from downTo to + 1) {
                    Collections.swap(items, i, i - 1)
                }
            }
            notifyItemMoved(from, to)
            true
        }
    }


    fun sort(){
        items.sortWith(object: Comparator<SlideItem>{
            override fun compare(slideOne: SlideItem?, slideTwo: SlideItem?): Int {
                return 1 //Sort Ascending Positioning.
            }
        })
    }

    private fun getItemPosition(i: SlideItem) = i.findChildBy(DataSchema.SLIDES_POSITION)?.value?.toInt()


    inner class ItemSelectedListener: OnItemSelected<Int> {
        override fun onItemSelected(position: Int) {

            val subject = items[position]
            var currentlySelected: SlideItem? = null

            if (subject.isActive()) //Item already selected. Nothing to do.
                return

            //First Reset every slide item
            items.onEach {
                if(it.isActive())
                    currentlySelected = it

                it.setActiveState(DataSchema.SLIDE_INACTIVE_VALUE)
            }

            //Then Set the appropriate item
            subject.setActiveState(DataSchema.SLIDE_ACTIVE_VALUE)

            notifyDataSetChanged()

            onItemSelected(subject, currentlySelected)
        }
    }

    inner class DurationClickListener: OnItemSelected<Int>{
        override fun onItemSelected(t: Int) {
            onDurationClickListener(items[t])
        }
    }

}