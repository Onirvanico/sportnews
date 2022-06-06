package br.com.projeto.sportnews.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.projeto.sportnews.databinding.NewItemBinding
import br.com.projeto.sportnews.domain.New
import com.squareup.picasso.Picasso

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
            Picasso.get().let {
                it.load(new.image)
                    .into(binding.imageNewItem)
            }
            configOpenLinkButton(new)
        }

        private fun configOpenLinkButton(new: New) {
            binding.openLinkButtonNewItem.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(new.link)
                }
                it.context.startActivity(intent)
            }
        }
    }

}