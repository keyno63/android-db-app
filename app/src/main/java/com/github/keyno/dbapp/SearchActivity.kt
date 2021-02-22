package com.github.keyno.dbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val intent = getIntent()
        val message = intent.extras?.getString("SEARCH_RESULT")?:""
        editTextTextPersonName.setText(message)
    }
}