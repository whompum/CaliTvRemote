package whompum.com.calitvremote.Account

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.login_screen.*
import whompum.com.calitvremote.R

class Login(val auth: FirebaseAuth = FirebaseAuth.getInstance()): AppCompatActivity() {

    val onLoginSuccess = fun (@NonNull _: AuthResult){
        notifyMsg("I've successfully logged you in")
        finish()
    }

    val onfailedLogin = fun (@NonNull e: Exception){
        notifyMsg(e.localizedMessage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        id_local_login.setOnClickListener(onLogin())
    }

    private fun onLogin(): (View) -> Unit{
        return {
            //First check if email / password fields are good
            //Next check if user is already logged in
            //If not, check if credentials match an online user
            //If a user is logged in, do nothing.
            //Else if user isn't logged in, the fields are okay, and the credentials match, log in.

            if(isInputBad()) //If the input fields have bad input (empty or something)
                notifyBadInput()
            else if(auth.currentUser != null) //If the user is already signed in.
                notifyLoggedIn()
            else
                signin(id_local_email_editor.ttS(), id_local_password_editor.ttS()) //None two above conditions were meet so we're good to sign the user in
        }
    }

    private fun signin(e: String, p: String){
        auth.signInWithEmailAndPassword(e, p)
                .addOnSuccessListener(onLoginSuccess)
                .addOnFailureListener(onfailedLogin)
    }

    private fun isInputBad(): Boolean{
        //IF fields aren't empty
        return TextUtils.isEmpty(id_local_email_editor.ttS()) or
               TextUtils.isEmpty(id_local_password_editor.ttS())
    }

    private fun notifyBadInput(){
        notifyMsg("The email or password musn't be empty")
    }

    private fun notifyLoggedIn(){
        notifyMsg("You're already logged in!")
    }

    private fun notifyMsg(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun EditText.ttS(): String{
        if(text == null)
            return " "

        return text.toString()
    }


}
