package gindi.com.funquizs

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_dash_board_adtivity.*

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_adtivity)

        toolBar.inflateMenu(R.menu.menu)

        toolBar.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.profile->{
                    Log.d("salida","Perfil")
                }
                R.id.closeSeccion->{
                    Log.d("salida","Cerrar Cession")
                    closeCession()
                }
            }

            return@setOnMenuItemClickListener true
        }

        problems.setOnClickListener {
            var i  = Intent(this,ProblemsActivity::class.java)
            startActivity(i)
        }
    }



    fun closeCession(){
        var prefer = getSharedPreferences("preferences",0)
        var edit = prefer.edit()
        edit.putString("token","")
        edit.commit()

        var i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}
