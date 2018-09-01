package whompum.com.calitvremote.Networking.Data

import whompum.com.calitvremote.Networking.Model.Reference

interface OnFailureListener {
    fun onFailure(item: Reference, exception: Exception)
}