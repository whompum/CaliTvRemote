package whompum.com.calitvremote.Networking.Data

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import whompum.com.calitvremote.Networking.Model.ReferenceItem

open class FirebaseController(val group: String,
                              var eventListener: (ReferenceItem, Int) -> Unit): SimpleEventListener(){

    companion object {

        const val ILLEGAL_CHARACTERS = "[\\.\\$\\#\\[\\] ]"

        fun cleanDirtyReference(input: String) = input.replace(ILLEGAL_CHARACTERS.toRegex(), "")
    }

    val reference = FirebaseDatabase.getInstance().reference.child(group)
    val adapter = ReferenceItemAdapter(group)

    var onCancelledListener = fun (error: DatabaseError) {}
    var onWriteCompleteListener: OnSuccessListener? = null
    var onWriteFailureListener: OnFailureListener? = null

    override fun onEvent(snapshot: DataSnapshot, deltaType: Int) {
        //Parse into usable ReferenceItem/Group objects
        //Then give the data to the client

        //Fetch a ReferenceGroup object to return
        if(!snapshot.exists())
            return

        val item = adapter.getData(snapshot)

        if(item != null)
            eventListener(adapter.getData(snapshot)!!, deltaType)
    }

    override fun onCancelled(error: DatabaseError) {
        onCancelledListener(error)
    }


    fun saveNew(item: ReferenceItem){
        save(item) {true}
    }

    fun saveChanges(item: ReferenceItem){
        save(item) { it.hasChanged() }
    }

    private fun save(item: ReferenceItem,
                     savePredicate: (ReferenceItem) -> Boolean){

        //Only works with Top-Level items.

        var ref = reference.child(item.reference)

        if(item.parent != group)
            ref = reference.child(item.parent).child(item.reference)

            ref.setValue(item.value).addOnCompleteListener {
                if(it.isSuccessful)
                    onWriteCompleteListener?.onSuccess(item)

                if(!it.isSuccessful || it.exception != null)
                    onWriteFailureListener?.onFailure(item, it.exception!!)
            }

        if(item.hasChildren()) //Save all the children
            for(child in item.getChildren())
                if(savePredicate(child))
                    saveNew(child)

    }

    //Only works for Top Level References.
    fun delete(ref: ReferenceItem){
        reference.child(ref.reference).removeValue().addOnCompleteListener(object: OnCompleteListener<Void> {
            override fun onComplete(task: Task<Void>) {
                if(task.isSuccessful)
                    onWriteCompleteListener?.onSuccess(ref)

                if(!task.isSuccessful || task.exception != null)
                    onWriteFailureListener?.onFailure(ref, task.exception!!)
            }
        })
    }

    open fun register(){
        reference.addChildEventListener(this)
    }

    fun unRegister(){
        reference.removeEventListener(this as ChildEventListener)
    }

    fun setAdapterReferenceFactory( factory: (String, String, String) -> ReferenceItem ){
        adapter.itemFactory = factory
    }

}