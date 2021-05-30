package com.sophie.bitbucketrepo.owner

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sophie.bitbucketrepo.R
import com.sophie.bitbucketrepo.databinding.OwnerListItemViewBinding
import com.sophie.bitbucketrepo.json_schema.Value

class OwnerAdapter(private val context: Context, private val clickListener: OwnerListener)
    : RecyclerView.Adapter<OwnerAdapter.OwnerViewHolder>(){

    var data = listOf<Value>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        return OwnerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        val item = data[position]
        holder.bind(clickListener, item)
        Glide
            .with(context).
            load(item.links?.avatar?.href)
            .apply(RequestOptions().override(600, 200))
            .into(holder.avatarView);
    }

    override fun getItemCount(): Int = data.size

    class OwnerViewHolder private constructor(private val binding: OwnerListItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind (clickListener: OwnerListener, item: Value) {
            binding.value = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        val avatarView: ImageView = itemView.findViewById(R.id.avatar)

        companion object {
            fun from(parent: ViewGroup): OwnerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OwnerListItemViewBinding.inflate(layoutInflater, parent, false)

                return OwnerViewHolder(binding)
            }
        }
    }
}

class OwnerListener(val clickListener: (id: String) -> Unit) {
    fun onClick(value: Value) = clickListener(value.uuid!!)
}
