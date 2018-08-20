package whompum.com.calitvremote

import android.support.v4.app.Fragment

abstract class QueryableFragment: Fragment(){
    protected lateinit var queryables: List<String>
}