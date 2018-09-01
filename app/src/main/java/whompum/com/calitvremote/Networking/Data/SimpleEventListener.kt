package whompum.com.calitvremote.Networking.Data

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

abstract class SimpleEventListener: ValueEventListener, ChildEventListener {

    companion object {
        const val CHANGE = 0
        const val REMOVED = 1
        const val ADDED = 2
        const val MOVED = 3
    }

    abstract fun onEvent(snapshot: DataSnapshot,deltaType: Int)
    override fun onDataChange(p0: DataSnapshot) {onEvent(p0, CHANGE)}
    override fun onChildMoved(p0: DataSnapshot, p1: String?) {onEvent(p0, MOVED)}
    override fun onChildChanged(p0: DataSnapshot, p1: String?) {onEvent(p0, CHANGE)}
    override fun onChildAdded(p0: DataSnapshot, p1: String?) {onEvent(p0, ADDED)}
    override fun onChildRemoved(p0: DataSnapshot) { onEvent(p0, REMOVED) }
}