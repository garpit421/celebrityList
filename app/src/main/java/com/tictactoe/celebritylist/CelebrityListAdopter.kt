package com.tictactoe.celebritylist

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.celebrity_row.view.*

class CelebrityListAdopter: BaseAdapter {

    private var celebrityDatabase: CelebrityDatabase? = null

    private var context: Context? = null

    constructor(context: Context){
        celebrityDatabase = CelebrityDatabase()
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var celebrity: Celebrity = celebrityDatabase?.celebrityList?.get(position)
        ?: Celebrity("No Actot","No Description",R.drawable.placeholder,false)
        var celebrityView: View
        var layoutInflator: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(celebrity.isAlive == true){
            celebrityView = layoutInflator.inflate(R.layout.celebrity_row,null)
        }else{
        celebrityView = layoutInflator.inflate(R.layout.celebrity_row,null)
        }

        celebrityView.actorimage.setImageResource(celebrity?.image ?: R.drawable.placeholder)
        celebrityView.lblActorName.setText(celebrity.name)
        celebrityView.lblActorDes.setText(celebrity.des)

        celebrityView.setOnClickListener{
            // this operation execute when imae is clicked
            val actorBioIntent = Intent(context, BioActivity::class.java)
            // we specify the key for the second activity like name,description,image
            actorBioIntent.putExtra(BioActivity.ACTOR_NAME,celebrity.name)
            actorBioIntent.putExtra(BioActivity.ACTOR_DES,celebrity.des)
            actorBioIntent.putExtra(BioActivity.ACTOR_IMAGE,celebrity.image)
            startActivity(context!!, actorBioIntent,null)          // !! not required

        }

        celebrityView.setOnLongClickListener{
            showDialog(position)
            return@setOnLongClickListener true
        }

        return celebrityView

    }

    override fun getItem(position: Int): Any {
        return celebrityDatabase?.celebrityList?.get(position) ?:
                Celebrity("No Actot","No Description",R.drawable.placeholder,false)
      }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
     }

    override fun getCount(): Int {
        return celebrityDatabase?.celebrityList?.size?:0
        // this is alvis operater ?:0 for null condition it put zero
     }

    // create alert for long press
    private  fun  showDialog(position: Int){
        val alertDialog: AlertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle("Message")
        alertDialog.setMessage("What would you like to do?")

        // create button this
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Delete",{
            dialog: DialogInterface?, which: Int ->

            celebrityDatabase?.celebrityList?.removeAt(position)

            this@CelebrityListAdopter.notifyDataSetChanged()

        })


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Duplicate",{
                dialog: DialogInterface?, which: Int ->

            celebrityDatabase?.celebrityList?.add(position,celebrityDatabase?.celebrityList?.get(position)
            ?: Celebrity("No Actor","No Des",R.drawable.placeholder,false))
            this.notifyDataSetChanged()   // this for notification


        })

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Cancel",{
                dialog: DialogInterface?, which: Int ->

            alertDialog.dismiss()

        })

        alertDialog.setCancelable(true)
        alertDialog.show()

    }

}