package whompum.com.calitvremote.Rentals

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.californiadreamshostel.firebaseclient.DataSchema
import com.californiadreamshostel.firebaseclient.FirebaseController

import whompum.com.calitvremote.R


class NewRentalDialog(ctx: Context, val client: (RentalsItem)->Unit): Dialog(ctx, R.style.StyleDialog) {

    companion object {
        const val LAYOUT = R.layout.new_rentals_item_layout
    }


    private val titleEditor: EditText
    private val threeHourEditor: EditText
    private val fullDayEditor: EditText

    init {

        val content = LayoutInflater.from(ctx).inflate(LAYOUT, null)

        titleEditor = content.findViewById(R.id.local_rental_title)
        titleEditor.isEnabled = true

        threeHourEditor = content.findViewById(R.id.local_three_hour_value)
        fullDayEditor = content.findViewById(R.id.local_full_day_value)

        //Set b4 i adjust the width
        setContentView(content)

        val metrics = DisplayMetrics()

        window?.windowManager?.defaultDisplay?.getMetrics(metrics)

        window?.attributes?.width = Math.max(metrics.widthPixels,
                ctx.resources.getDimensionPixelSize(R.dimen.dimen_max_dialog_width))

        content.findViewById<TextView>(R.id.id_save)
                .setOnClickListener{
                    if(!TextUtils.isEmpty(titleEditor.text.toString())){ //Also check for title validity
                        client( makeRentals() )
                        dismiss()
                    }
                }

    }


    private fun makeRentals(): RentalsItem{

        var reference = titleEditor.text.toString()
        val threeHours = threeHourEditor.text.toString()
        val fullDay = fullDayEditor.text.toString()

        val cleanedReference = FirebaseController.cleanDirtyReference(reference)

        if(cleanedReference != reference)
            Toast.makeText(titleEditor.context, " (\".\" \"$\" \"#\" \"[\" \"]\") Are not allowed. I've removed" +
                    " them for you",
                    Toast.LENGTH_LONG).show()

        val item = RentalsItem(DataSchema.RENTALS, cleanedReference)


        item.addChild(RentalsItem(cleanedReference, DataSchema.RENTAL_THREE_HOURS, threeHours))
        item.addChild(RentalsItem(cleanedReference, DataSchema.RENTAL_FULL_DAY, fullDay))

        return item
    }




}
















