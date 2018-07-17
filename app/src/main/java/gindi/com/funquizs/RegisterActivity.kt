package gindi.com.funquizs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.auth0.android.jwt.JWT
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    var queue: RequestQueue? = null

    var getAllQuizsUrl = "https://funquizs.herokuapp.com/register/user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //pila de volley
        queue = Volley.newRequestQueue(this)

        create.setOnClickListener {

            var nombre = name.text.toString()
            var email = mail.text.toString()
            var pass = password.text.toString()
            var repeatPass = passwordRepeat.text.toString()

            if (pass != repeatPass){
                sms.text = "Las contraseñas deben ser iguales"
            }else if(nombre.isEmpty()){
                name.error = "Campo obligatorio"
            }else if(email.isEmpty()){
                mail.error = "Campo obligatorio"
            }else if(pass.isEmpty()){
                password.error = "Campo obligatorio"
            }else if(repeatPass.isEmpty()){
                passwordRepeat.error = "Campo obligatorio"
            }else{
                createAccount(nombre,email,pass)
            }
        }

        cancel.setOnClickListener {
            var i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    fun createAccount(name:String,email:String,pass:String){

        var body = "{\"name\":\"${name}\",\"email\":\"${email}\",\"role\":\"client\",\"password\":\"${pass}\"}"

        var req = JsonObjectRequest(Request.Method.POST, getAllQuizsUrl, body, Response.Listener<JSONObject>() {

            Log.d("salida", it.toString())

            var status = it.getString("status")

            if (status == "200"){
                var i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            }else{
                sms.text = "Error"
            }



        }, Response.ErrorListener {
            Log.d("salida", it.toString())
            sms.text = "Ups, hubo un error al intentar crear la cuenta"
        })

        queue!!.add(req)

    }

    private fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
