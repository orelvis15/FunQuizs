package gindi.com.funquizs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Response
import org.json.JSONObject
import android.widget.Toast



class ProblemsActivity : AppCompatActivity() {

    var problems:ArrayList<Problems> = ArrayList()
    var problemsAdapter:ProblemsAdapter? = null

    var queue: RequestQueue? = null

    var getAllQuizsUrl = "https://funquizs.herokuapp.com/quizs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //configurando la toolbar
        val myToolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(myToolbar)

        //esconder el titulo
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        //mostrar boton back
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        myToolbar.setNavigationOnClickListener {
            finish()
        }

        //pila de volley
        queue = Volley.newRequestQueue(this)

        //Layout para el recyclerview
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        problemsList.layoutManager = layoutManager

       /* problems.add(Problems("Problema 1","El principe y el mendigo","Problema 1","Problema 1"))
        problems.add(Problems("Problema 2","Quien suma mas","Problema 1","Problema 1"))
        problems.add(Problems("Problema 1","Despues de ti","Problema 1","Problema 1"))
        problems.add(Problems("Problema 1","Antes de la noche","Problema 1","Problema 1"))
        problems.add(Problems("Problema 1","Reclamo","Problema 1","Problema 1"))
        problems.add(Problems("Problema 1","Un pedazo de pan","Problema 1","Problema 1"))
        problems.add(Problems("Problema 1","Y cuanto le quedaba","Problema 1","Problema 1"))
        problems.add(Problems("Problema 1","Quiero mi queso","Problema 1","Problema 1"))
        problems.add(Problems("Problema 1","Problema de matematicas","Problema 1","Problema 1"))
*/


    }

    override fun onResume() {
        super.onResume()

        var req = JsonObjectRequest(Request.Method.GET, getAllQuizsUrl,com.android.volley.Response.Listener<JSONObject>() {

            var jsonArray = it.getJSONArray("data")

            for (i in 0..jsonArray.length()-1){
                var id = jsonArray.getJSONObject(i).getString("_id")
                var name = jsonArray.getJSONObject(i).getString("name")
                var title = jsonArray.getJSONObject(i).getString("title")
                var content = jsonArray.getJSONObject(i).getString("content")
                var answer = jsonArray.getJSONObject(i).getString("answer")
                var answerType = jsonArray.getJSONObject(i).getString("answerType")
                var updateAt = ""//jsonArray.getJSONObject(i).getString("updatedAt")

                problems.add(Problems(id,name,title,content,answer,answerType,updateAt))

            }

            problemsAdapter = ProblemsAdapter(problems)

            problemsList.adapter = problemsAdapter
            problemsList.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))


            //Log.d("salida",it.toString())

        },com.android.volley.Response.ErrorListener {
            Log.d("salida",it.toString())


        })

        queue!!.add(req)

    }

}
