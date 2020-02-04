package com.stepasha.keyconservation.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.squareup.picasso.Picasso
import com.stepasha.keyconservation.LoginActivity
import com.stepasha.keyconservation.R

import com.stepasha.keyconservation.model.Campaign
import com.stepasha.keyconservation.model.User
import kotlinx.android.synthetic.main.item_view.view.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(private var campaigns: MutableList<Campaign>?) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var users: MutableList<User>? = null


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
        if (!LoginActivity.admins){
            holder.deleteButton.visibility = View.GONE
        }else if(LoginActivity.admins){
            holder.deleteButton.visibility =View.VISIBLE
        }


        //     val currentCampaignAuthor = users?.get(position)

        // If the url link is longer than 10, then get the image from the url. Else use a default image.
           val bannerImageSfx = currentCampaign?.banner_image.toString()
           if ((currentCampaign?.banner_image.toString().endsWith("jpeg")) ||
               (currentCampaign?.banner_image.toString().endsWith("jpg")) ||
               (currentCampaign?.banner_image.toString().endsWith("png")) ||
               (currentCampaign?.banner_image.toString().contains("auto"))
           ) {
               Picasso.get().load(currentCampaign?.banner_image).into(holder.bannerImage)
           }

        val eventPictireSfx = currentCampaign?.event_image.toString()
        val uri: Uri = Uri.parse(eventPictireSfx)
        Glide.with(context).load(uri).into(holder.bannerImage)
            //if ((currentCampaign?.event_image.toString().endsWith("jpeg")) ||
        //    (currentCampaign?.event_image.toString().endsWith("png")) ||
        //    (currentCampaign?.event_image.toString().endsWith("jpg")) ||
        //    (currentCampaign?.event_image.toString().contains("auto"))
        //) {
        //    Picasso.get().load(currentCampaign?.event_image).into(holder.bannerImage)
                    //}
        //holder.username?.text = currentCampaignAuthor?.username
        holder.location?.text = currentCampaign?.location
        holder.lat?.text = currentCampaign?.latitude.toString()
        holder.lon?.text = currentCampaign?.longitude.toString()
        holder.eventName?.text = currentCampaign?.event_name
        holder.eventDescription?.text = currentCampaign?.event_description
        holder.eventDate?.text = currentCampaign?.created_at.toString()

    //    holder.bannerImage?.setOnClickListener {

      //  holder.bannerImage.visibility = View.INVISIBLE

       //     holder.myVideo?.setVideoURI(uri)
      //      holder.myVideo?.start()
      //  }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deleteButton: ImageButton = itemView.imageButton
        val location: TextView? = itemView.textview_location
        val lat: TextView? = itemView.textview_lat
        val lon: TextView? = itemView.textview_lon
        val bannerImage: ImageView? = itemView.imageView_eventimage
      //  val myVideo: VideoView? = itemView.view_myvideo
        val eventName: TextView? = itemView.textview_eventname
        val eventDescription: TextView? = itemView.textview_eventdescription
        val eventDate: TextView? = itemView.textview_eventdate



        //   fun cardViewDeleteOnLongPress(itemPosition: Int) {
        //       maps?.removeAt(itemPosition)
        //       updateMap(maps)
        //   }
    }

    fun updateCampaign(newList: ArrayList<Campaign>?) {
        campaigns = newList
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