package com.github.keyno.dbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.keyno.dbapp.db.DbUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonStart(view: View?) {
        val intent = Intent(this, SubActivity::class.java)
        startActivity(intent)
    }

    fun onButtonSearch(view: View?) {
        val intent = Intent(this, SearchActivity::class.java)

        val text = get()

        intent.putExtra("SEARCH_RESULT", text)
        startActivity(intent)
    }

    fun onOtherView(view: View?) {
        val intent = Intent(this, AnotherActivity::class.java)
        startActivity(intent)
    }

    fun onParallelButtonAction() {
        val intent = Intent(this, SearchActivity::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val db = DbUtil()
            async(Dispatchers.Default) {
                db.get()
            }.await().let{
                intent.putExtra("SEARCH_RESULT", it)
                startActivity(intent)
            }
        }
    }

    private fun get(): String? {
        try {
            val c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sandbox",
                "root",
                "root"
            )
            val stmt = c.createStatement()
            val result = stmt.executeQuery("SELECT * FROM person");
            val sb = StringBuffer()
            while (result.next()) {
                sb.append(result.getInt(1).toString() + result.getString(4))
            }
            return sb.toString()
        }catch (e: Exception){
            return e.stackTraceToString()
        }
    }
}