package whompum.com.calitvremote.Util

import android.view.View
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.animation.AnticipateInterpolator


class Animation {

    companion object {

        fun toggleDisplay(v: View, show: Boolean){

            var scale = 0F
            var visibility = View.INVISIBLE
            if(show) {
                scale = 1F
                visibility = View.VISIBLE
            }

            v.animate()
                .scaleY(scale)
                .scaleX(scale)
                .setDuration(350L)
                .setInterpolator(AnticipateInterpolator()).setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        v.visibility = visibility
                    }
                }).start()
        }

    }

}