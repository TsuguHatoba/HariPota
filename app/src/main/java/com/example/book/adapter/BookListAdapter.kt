package com.example.book.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.book.data.PotterBook
import com.example.book.data.PotterBookItem
import com.example.book.databinding.ListViewItemBinding

class BookListAdapter(private val clickListener: BookListener) :
    ListAdapter<PotterBookItem, BookListAdapter.BookViewHolder>(DiffCallback)
{
    class BookViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: BookListener, book: PotterBookItem){
            binding.book = book
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PotterBookItem>(){
        override fun areItemsTheSame(oldItem: PotterBookItem, newItem: PotterBookItem): Boolean {
            return oldItem.patronus == newItem.patronus
        }

        override fun areContentsTheSame(oldItem: PotterBookItem, newItem: PotterBookItem): Boolean {
            return oldItem.gender == newItem.gender
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int){
        val book = getItem(position)
        holder.bind(clickListener, book)
    }



}

class BookListener(val clickListener: (book: PotterBookItem ) -> Unit){
    fun onClick(book: PotterBookItem) = clickListener(book)
}