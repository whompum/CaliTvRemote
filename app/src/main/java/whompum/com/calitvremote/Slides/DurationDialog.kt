package whompum.com.calitvremote.Slides

import android.app.Dialog
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import whompum.com.calitvremote.R

class DurationDialog(var d: (Int)->Unit?, ctx: Context): Dialog(ctx, R.style.StyleDialog){

    private val recycler: RecyclerView
    private val adapter = SecondsAdapter()
    private val secondDisplay: TextView

    init {

        val content = LayoutInflater.from(ctx)
                .inflate(R.layout.second_selector, null, false)

        secondDisplay = content.findViewById(R.id.local_second_highlighted)

        content.findViewById<View>(R.id.local_seconds_container)
                .setOnClickListener{
                    d(Integer.valueOf(secondDisplay.text.toString()))
                    dismiss()
                }

        recycler = content.findViewById(R.id.id_local_seconds_list)
        recycler.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
        recycler.addOnScrollListener(scrollListener())
        setContentView(content)

        window?.attributes?.width = ctx.resources.getDimensionPixelSize(R.dimen.dimen_max_dialog_width)
    }


    inner class scrollListener: RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            val cX = recyclerView.width*0.5F
            val cY = recyclerView.height*0.5F

            val item = recyclerView.findChildViewUnder(cX, cY)

            val adapterPos = recyclerView.findContainingViewHolder(item!!)?.adapterPosition

            val data = adapter.getDataAt(adapterPos!!)

            // val listData = adapter.getDataAt(recyclerView.getChildAdapterPosition(recyclerView.getChildAt(1)))

            val text = data.toString()

            secondDisplay.text  = text

        }
    }

    private inner class SecondsAdapter: RecyclerView.Adapter<SecondsAdapter.Holder>(){
        val BUFFER = -1

        val data = ArrayList<Int>(0)

        init {
           for(a in 0 until 92)
               if(a == 0 || a == 91)
                   data.add(BUFFER)
                else
                   data.add(a)
        }

        fun getDataAt(i: Int): Int{
            return data[i]
        }

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
            return Holder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.seconds_item_view, parent, false))
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: Holder, pos: Int) {
            holder.bind(data[pos])
        }

        open inner class Holder(val l: View): RecyclerView.ViewHolder(l){
            open fun bind(i :Int){
                if(i > BUFFER)
                    l.findViewById<TextView>(R.id.id_local_second_display)
                            .text = "$i"
            }
        }
    }

}