package whompum.com.calitvremote.Slides.UiAdapter

import android.graphics.Color
import android.os.Build
import android.support.annotation.ColorRes
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.californiadreamshostel.firebaseclient.DataSchema
import com.californiadreamshostel.firebaseclient.ReferenceItem
import whompum.com.calitvremote.UiAdapter.ReferenceViewHolder
import whompum.com.calitvremote.R
import whompum.com.calitvremote.Slides.SlideItem
import whompum.com.calitvremote.Util.OnItemSelected


class SlideItemHolder(v: View): ReferenceViewHolder(v){

    //Called when this item was chosen
    var onItemSelected: OnItemSelected<Int>? = null

    //Called when the duration item was clicked
    var onDurationSelected: OnItemSelected<Int>? = null

    init {
        layout.setOnClickListener{
            onItemSelected?.onItemSelected(adapterPosition)
        }

        layout.findViewById<View>(R.id.id_local_duration_container).setOnClickListener {
            onDurationSelected?.onItemSelected(adapterPosition)
        }

    }

    /*
     * Adjust Styling
     */
    override fun bindRoot(ref: ReferenceItem, dataType: Int) {
        //Should be a SelecteableItem

        if(ref.parent != DataSchema.SLIDES) //The root has to be the actual slide object
            return

        ref as SlideItem

        layout.findViewById<TextView>(R.id.id_local_slide_title)
                .text = ref.reference

            if(ref.isActive())
                styleForSelected()
            else
                styleForUnselected()

    }


    override fun bindItem(ref: ReferenceItem, dataType: Int) {

        if(ref.reference == DataSchema.SLIDES_DURATION)
            layout.findViewById<TextView>(R.id.id_local_slide_duration)
                    .text = getDisplayTime(ref.value)

        else if(ref.reference == DataSchema.SLIDES_POSITION)
            layout.findViewById<TextView>(R.id.id_local_slide_position)
                    .text = ref.value

    }

    private fun getDisplayTime(time: String) = "$time Seconds"

    private fun styleForSelected(){
        (layout as CardView).setCardBackgroundColor(resolveColor(R.color.colorPrimary))
        colorText(Color.WHITE , layout, 0, {true})

        layout.findViewById<ImageView>(R.id.local_id_clock)
                .setImageResource(R.drawable.icon_clock_white)
    }

    private fun styleForUnselected(){
        (layout as CardView).setCardBackgroundColor(Color.WHITE)
        colorText(resolveColor(R.color.color_dark), layout, 0, {true})

        layout.findViewById<ImageView>(R.id.local_id_clock)
                .setImageResource(R.drawable.icon_clock_dark)
    }

    private fun resolveColor(@ColorRes clrRes: Int): Int{
        if(Build.VERSION.SDK_INT >= 23)
            return layout.context.resources.getColor(clrRes, null)
        else
            return layout.context.resources.getColor(clrRes)
    }

    /*
     * Applies a general highlight to all Textviews in the hierarchy.
     */
    private fun colorText(color: Int, c: ViewGroup, i: Int, /*Should Colorize?*/ colorPredicate: (Int) -> Boolean){

        if(i == c.childCount)
            return

        val child = c.getChildAt(i)

        if(child is ViewGroup)
            colorText(color, child, 0, colorPredicate)

        if(child is TextView && colorPredicate(child.id))
            child.setTextColor(color)

        colorText(color, c, i+1, colorPredicate     )
    }

}
