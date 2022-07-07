package com.example.clonepaging.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clonepaging.R
import com.example.clonepaging.network.RickAndMortyList

class MyPagingAdapter : PagingDataAdapter<RickAndMortyList.CharacterData, MyPagingAdapter.MyViewHolder>(DiffUtilCallBack()) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imgRow: ImageView by lazy { itemView.findViewById<ImageView>(R.id.imgRow) }
        private val tvTitleRow: TextView by lazy { itemView.findViewById<TextView>(R.id.tvTitleRow) }
        private val tvSubTitle: TextView by lazy { itemView.findViewById<TextView>(R.id.tvSubTitle) }
        fun bind(characterData: RickAndMortyList.CharacterData?){
            Glide.with(itemView.context)
                .load((characterData?.image))
                .circleCrop()
                .into(imgRow)

            tvSubTitle.setText(characterData?.species)
            tvTitleRow.setText(characterData?.name)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false))
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<RickAndMortyList.CharacterData>() {
    override fun areItemsTheSame(oldItem: RickAndMortyList.CharacterData, newItem: RickAndMortyList.CharacterData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: RickAndMortyList.CharacterData, newItem: RickAndMortyList.CharacterData): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.species == newItem.species
    }

}
