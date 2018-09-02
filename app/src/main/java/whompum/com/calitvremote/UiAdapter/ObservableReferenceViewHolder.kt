package whompum.com.calitvremote.UiAdapter

import android.view.View
import android.widget.TextView

/*
    Registers TextWatchers on all wanted items to observe

    NOTE: this class isn't meant to be used by a client,
    but subclassed instead

    Subclasses should unbind, and rebind the listener on any view whose state might change
    during the binding phase of the adapter.

 */
abstract class ObservableReferenceViewHolder
protected constructor(val content: View,
                      private vararg val observables: Int): ReferenceViewHolder(content){

    private val listeners = HashMap<Int, ReferenceTextWatcher>(observables.size)
    private val views = HashSet<TextView>()

    private var textChangeListener: OnValueChanged? = null

    init{ operate(initializeListeners()) }

    public fun setValueChangeListener(l: OnValueChanged){
        textChangeListener = l
    }

    private fun onTextChanged(): (String, Int)-> Unit{
        return{ newText, id ->
             textChangeListener?.onValueChange(newText, id, adapterPosition)
        }
    }

    private fun operate(operation: (TextView, Int) -> Unit){
        for(id in observables){
            val view = content.findViewById<View>(id)
                if(view is TextView)
                    operation(view, id)
        }
    }

    private fun initializeListeners(): (TextView, Int) -> Unit{
        return {
            v, id ->

            if(!views.contains(v))
                views.add(v)

            val listener = ReferenceTextWatcher(id, onTextChanged())
            listeners.put(id, listener)

            v.addTextChangedListener(listener)
        }
    }

    protected fun unbind(){
        for(view in views)
            unbindListener(view, view.id)
    }

    protected fun rebind(){
        for(view in views)
            bindListener(view, view.id)
    }

    private fun bindListener(view: TextView, id: Int){
        view.addTextChangedListener(listeners[id])
    }

    private fun unbindListener(view: TextView, id: Int){
        view.removeTextChangedListener(listeners[id])
    }

}