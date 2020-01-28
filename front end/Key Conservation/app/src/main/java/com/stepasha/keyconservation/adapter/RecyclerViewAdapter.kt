package com.stepasha.keyconservation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stepasha.keyconservation.R
import com.stepasha.keyconservation.model.Campaign

class RecyclerViewAdapter(private var campaigns: MutableList<Campaign>?) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return campaigns!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val currentCampaign = campaigns?.get(position)

        // If the url link is longer than 10, then get the image from the url. Else use a default image.
        val imageSuffix = currentCampaign?.banner_image.toString()
        if ((currentCampaign?.banner_image.toString().endsWith("jpeg")) ||
            (currentCampaign?.banner_image.toString().endsWith("jpg")) ||
            (currentCampaign?.banner_image.toString().endsWith("png")) ||
            (currentCampaign?.banner_image.toString().contains("auto"))
        ) {
            Picasso.get().load(currentCampaign?.banner_image).into(holder.image)
        }



        holder.mapAddress.text = currentMap?.address
        holder.description.text = currentMap?.description
        holder.postdate.text = currentMap?.posteddate
        holder.additionalInfo.text = currentMap?.additionalInfo


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView? = itemView.iv_property_image
        val mapAddress: TextView = itemView.vAddress
        val description: TextView = itemView.vDescription
        val postdate: TextView = itemView.vPostDate
        val additionalInfo: TextView = itemView.vAdditionalInfo


        //   fun cardViewDeleteOnLongPress(itemPosition: Int) {
        //       maps?.removeAt(itemPosition)
        //       updateMap(maps)
        //   }
    }

    fun updateMap(newList: ArrayList<Map>?) {
        maps = newList
        notifyDataSetChanged()
    }

    //fun deleteProperty(id: Int){
    //    val call: Call<Void> = ServiceBuilder.create().deleteProperty(LoginActivity.token, id)
    //    call.enqueue(object: Callback<Void> {
    //        override fun onFailure(call: Call<Void>, t: Throwable) {
    //            Log.i("Add Property", "OnFailure ${t.message}")
    //        }
//
    //        override fun onResponse(call: Call<Void>, response: Response<Void>) {
    //            if(response.isSuccessful){
    //                Log.i("Delete Property", "OnResponseSuccess ${response.message()}")
//
    //            }
    //            else{
    //                Log.i("Add Property", "OnResponseFailure ${response.errorBody()}")
    //            }
    //        }
//
    //    })
    //}


}