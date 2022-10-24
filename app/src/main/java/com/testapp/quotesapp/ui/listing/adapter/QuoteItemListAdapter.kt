package com.testapp.quotesapp.ui.listing

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.testapp.quotesapp.databinding.ItemListContentBinding
import com.testapp.quotesapp.ui.model.QuotesModelItem

class QuoteItemListAdapter
internal constructor(
    onAdapterClickListener: QuoteAdapterClickListener
) : RecyclerView.Adapter<QuoteItemListAdapter.SimpleQuoteViewHolder>() {

    private var classScopedItemClickListener: QuoteAdapterClickListener = onAdapterClickListener
    private var passedList:List<QuotesModelItem> = listOf()
    init {
        this.classScopedItemClickListener = onAdapterClickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleQuoteViewHolder {
        val binding =
            ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleQuoteViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SimpleQuoteViewHolder, position: Int) {

        //val currentItem = differ.currentList[position]
        val currentItem = passedList[position]
        holder.authorNameView.text = currentItem.author
        holder.authorQuoteView.text = currentItem.text

        holder.editBtn.setOnClickListener {
            classScopedItemClickListener.copyUrlEvent(currentItem)
        }

        holder.deleteBtn.setOnClickListener {
            classScopedItemClickListener.deleteUrlEvent(currentItem)
        }
    }

    override fun getItemCount(): Int {
        //return differ.currentList.size
        return passedList.size
    }

    inner class SimpleQuoteViewHolder(binding: ItemListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val authorNameView: TextView = binding.authorNameText
        val authorQuoteView: TextView = binding.authorQuote
        val deleteBtn: ImageView = binding.delIcon
        val editBtn: ImageView = binding.editIcon


    }

    fun updateInsideAdapter(itemList: List<QuotesModelItem>){
        this.passedList = itemList
        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private val differCallback = object : DiffUtil.ItemCallback<QuotesModelItem>() {
        override fun areItemsTheSame(oldItem: QuotesModelItem, newItem: QuotesModelItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: QuotesModelItem,
            newItem: QuotesModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    //val differ = AsyncListDiffer(this, differCallback)


}

interface QuoteAdapterClickListener {
    fun copyUrlEvent(quotesModelItem: QuotesModelItem)
    fun deleteUrlEvent(quotesModelItem: QuotesModelItem)
}