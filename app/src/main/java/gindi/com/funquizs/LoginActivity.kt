package gindi.com.funquizs

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.auth0.android.jwt.JWT
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import android.util.Patterns
import android.text.TextUtils


class LoginActivity : AppCompatActivity() {

    var queue: RequestQueue? = null

    var getAllQuizsUrl = "https://funquizs.herokuapp.com/login"

    var prefer: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //pila de volley
        queue = Volley.newRequestQueue(this)

        prefer = getSharedPreferences("preferences", 0)

        btnLogin.setOnClickListener {

            if (!isEmailValid(email.text.toString())) {
                email.error = "Email invalido"
            } else if (password.text.isEmpty()) {
                password.error = "Campo obligatorio"
            } else {
                login()
            }
        }

        create.setOnClickListener {
            var i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun login() {
        var body = "{\"email\":\"${email.text}\",\"password\":\"${password.text}\"}"
        //var body = "{\"email\":\"ariel@gmail.com\",\"password\":\"Ariel\"}"

        var req = JsonObjectRequest(Request.Method.POST, getAllQuizsUrl, body, Response.Listener<JSONObject>() {

            Log.d("salida", it.toString())

            var token = it.getString("token")
            var jwt = JWT(token.toString())

            prefer = getSharedPreferences("preferences", 0)
            var edit = prefer!!.edit()
            edit.putString("name", jwt.getClaim("name").asString())
            edit.putString("email", jwt.getClaim("email").asString())
            edit.putString("token",token)

            if (remember.isChecked) {
                edit.putString("password", password.text.toString())
            }

            edit.commit()

            var i = Intent(this, DashBoardActivity::class.java)
            startActivity(i)
            finish()

        }, Response.ErrorListener {
            Log.d("salida", it.toString())

            sms.text = "Ups, hubo un problema en el login"

        })

        queue!!.add(req)
    }

    override fun onResume() {
        super.onResume()

        var pass = prefer!!.getString("password", null)

        if (pass != null) {
            password.setText(pass)

            var mail = prefer!!.getString("email", null)
            email.setText(mail)
        }
    }
}
