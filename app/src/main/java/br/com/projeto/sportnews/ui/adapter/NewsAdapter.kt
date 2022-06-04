package br.com.projeto.sportnews.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.projeto.sportnews.databinding.NewItemBinding
import br.com.projeto.sportnews.domain.New

class NewsAdapter(val news: List<New>) : RecyclerView.Adapter<NewsAdapter.NewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewItemBinding.inflate(inflater, parent, false)
        return NewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        holder.bindViews(news[position])
    }

    override fun getItemCount() = news.size

    class NewHolder(val binding: NewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(new: New) {
            binding.titleNewItem.text = new.title
            binding.descriptionNewItem.text = new.description
        }
    }

}