package com.tictactoe.celebritylist

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bio.*

class BioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bio)

        var receiveData = intent.extras
        var actorName = receiveData.getString(ACTOR_NAME)
        var actorDes = receiveData.getString(ACTOR_DES)
        var actorImages = receiveData.getInt(ACTOR_IMAGE)

        bioActorImage.setImageResource(actorImages)
     //   bioActorImage.setImageResource()
        lblActorbio.text = "$actorName - $actorDes"

    }
    companion object {

        val ACTOR_NAME = "name"
        val ACTOR_DES = "des"
        val ACTOR_IMAGE = "image"
    }
}