package com.github.keyno.dbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.keyno.dbapp.db.DbUtil
import kotlinx.android.synthetic.main.activity_another.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
    }

    fun onParallelButtonAction() {
        GlobalScope.launch(Dispatchers.Main) {
            val db = DbUtil()
            async(Dispatchers.Default) {
                db.get()
            }.await().let{
                textView3.setText(it)
            }
        }
    }
}