package com.tictactoe.celebritylist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var celebrityAdapter: CelebrityListAdopter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celebrityAdapter = CelebrityListAdopter(this@MainActivity)

        celebrityListView.adapter = celebrityAdapter
    }
}
