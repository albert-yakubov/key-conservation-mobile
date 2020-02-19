package com.stepasha.endangeredhaven.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.stepasha.endangeredhaven.ConnectActivity
import com.stepasha.endangeredhaven.LoginActivity
import com.stepasha.endangeredhaven.MapsActivity
import com.stepasha.endangeredhaven.R

import com.stepasha.endangeredhaven.model.Campaign
import com.stepasha.endangeredhaven.model.User
import com.stepasha.endangeredhaven.retrofit.ServiceBuilder
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewAdapter(private var campaigns: MutableList<Campaign>?) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()  {

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



      //  if (!LoginActivity.admins){
      //      holder.deleteButton.visibility = View.GONE
      //  }else if(LoginActivity.admins){
      //      holder.deleteButton.visibility =View.VISIBLE
      //  }


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
       // holder.eventDate?.text = currentCampaign?.created_at.toString()
        val myString = currentCampaign?.created_at.toString()
        val ms = "" +myString[0] + myString[1] + myString[2] + myString[3] + "/" + myString[4] + myString[5] + "/" + myString[6] + "0"
        holder.eventDate?.text = ms
        holder.username?.text = currentCampaign?.user?.username.toString()
        holder.title?.text = currentCampaign?.title.toString()
        if (LoginActivity.username4D == currentCampaign?.user?.username.toString()){
            holder.deleteButton.visibility = View.VISIBLE
        }else if(LoginActivity.username4D != currentCampaign?.user?.username.toString()){
            holder.deleteButton.visibility =View.GONE
        }
        holder.status?.text = currentCampaign?.banner_image.toString()
        val profilePic = currentCampaign?.user?.profilepicture.toString()
        if ((currentCampaign?.user?.profilepicture.toString().endsWith("jpeg")) ||
            (currentCampaign?.user?.profilepicture.toString().endsWith("jpg")) ||
            (currentCampaign?.user?.profilepicture.toString().endsWith("png")) ||
            (currentCampaign?.user?.profilepicture.toString().contains("auto"))
        ) {
            Picasso.get().load(currentCampaign?.user?.profilepicture).into(holder.profilepicture)
        }

        holder.profilepicture?.setOnClickListener {
          MapsActivity.mapUsern = currentCampaign?.user?.username ?: ""
            val intent = Intent(context, ConnectActivity::class.java)
            context?.startActivity(intent)

        }


        holder.bannerImage?.setOnClickListener {
            holder.bannerImage.visibility = View.GONE
            holder.myVideo?.visibility = View.VISIBLE
            holder.myVideo?.setVideoURI(uri)
            holder.myVideo?.start()
        }
        holder.myVideo?.setOnClickListener {
            holder.myVideo.stopPlayback()
            holder.myVideo.visibility = View.GONE
            holder.bannerImage?.visibility = View.VISIBLE

        }

        holder.deleteButton.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Confirmation")
            builder.setMessage("Are you sure you want to delete this property?")
            builder.setPositiveButton("YES") { dialogInterface, _ ->
                holder.cardViewDeleteOnLongPress(position)
                if (currentCampaign != null) {
                    deleteCampaign(currentCampaign.eventid!!)
                }
                Toast.makeText(
                    context,
                    "Property has been successfully deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.setNegativeButton("NO"){ dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            builder.show()
        }




    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deleteButton: ImageButton = itemView.imageButton
        val location: TextView? = itemView.textview_location
        val lat: TextView? = itemView.textview_lat
        val lon: TextView? = itemView.textview_lon
        val myVideo: VideoView? = itemView.view_myvideo
        val bannerImage: ImageView? = itemView.imageView_eventimage
        val eventName: TextView? = itemView.textview_eventname
        val eventDescription: TextView? = itemView.textview_eventdescription
        val eventDate: TextView? = itemView.textview_eventdate
        val username: TextView? = itemView.textview_username
        val profilepicture : CircleImageView? = itemView.textview_profilepicture
        val status: TextView? = itemView.textview_status
        val title: TextView? = itemView.textview_title


        fun cardViewDeleteOnLongPress(itemPosition: Int) {
            campaigns?.removeAt(itemPosition)
            updateCampaign(campaigns)
        }


    }

    fun updateCampaign(newList: MutableList<Campaign>?) {
        campaigns = newList
        notifyDataSetChanged()
    }


    fun deleteCampaign(eventid: Long){
        val call: Call<Void> = ServiceBuilder.create().deleteCampaign(eventid)
        call.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("Add Property", "OnFailure ${t.message}")
            }
//
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){


                    Log.i("Delete Property", "OnResponseSuccess ${response.message()}")
//
                }
                else{
                    Log.i("Add Property", "OnResponseFailure ${response.errorBody()}")
                }
            }
//
        })
    }




}