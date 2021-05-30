package com.sophie.bitbucketrepo.owner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sophie.bitbucketrepo.R
import com.sophie.bitbucketrepo.json_shema.Value

class OwnerAdapter(private val context: Context): RecyclerView.Adapter<OwnerAdapter.OwnerViewHolder>(){

    var data = listOf<Value>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.owner_list_item_view, parent, false)
        return OwnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        val item = data[position]
        holder.displayNameTextView.text = item.owner?.display_name
        holder.dateTextView.text = item.created_on.toString()
        holder.typeTextView.text = item.owner?.type
        Glide
            .with(context).
            load(item.links?.avatar?.href)
            .apply(RequestOptions().override(600, 200))
            .into(holder.avatarView);
    }

    fun ImageView.load(imageAddress: String) {
        Glide.with(this)
            .load(imageAddress)
            .into(this)
    }

    override fun getItemCount(): Int = data.size

    class OwnerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val displayNameTextView: TextView = itemView.findViewById(R.id.display_name_text_view)
        val typeTextView: TextView = itemView.findViewById(R.id.type_text_view)
        val dateTextView: TextView = itemView.findViewById(R.id.date_text_view)
        val avatarView: ImageView = itemView.findViewById(R.id.avatar)
    }
}

//class OwnerListener(val clickListener: (id: Long) -> Unit) {
//    fun onClick(value: Value) = clickListener(value.uuid)
//}
