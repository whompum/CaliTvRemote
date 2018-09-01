package whompum.com.calitvremote.Util

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

abstract class SimpleChildEventListener: ChildEventListener {

    abstract fun onChanged(snapshot: DataSnapshot)

    override fun onCancelled(p0: DatabaseError) {
    }

    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
    }

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        onChanged(p0)
    }

    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        onChanged(p0)
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
    }
}