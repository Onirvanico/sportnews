package br.com.projeto.sportnews.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import br.com.projeto.sportnews.databinding.NewItemBinding
import br.com.projeto.sportnews.domain.New
import com.squareup.picasso.Picasso

class NewsAdapter(val news: List<New>) : RecyclerView.Adapter<NewsAdapter.NewHolder>() {

    var listenerFavorite:(New, ImageButton) -> Unit = {new: New, view: ImageButton ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewItemBinding.inflate(inflater, parent, false)
        return NewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        holder.bindViews(news[position])
    }

    override fun getItemCount() = news.size

    inner class NewHolder(val binding: NewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(new: New) {
            binding.titleNewItem.text = new.title
            binding.descriptionNewItem.text = new.description

            Picasso.get().load(new.image)
                .into(binding.imageNewItem)

            configFavoriteButton(new)
            configShareButton(new)
            configOpenLinkButton(new)
        }

        private fun configFavoriteButton(new: New) {
            binding.favoriteButtonNewItem.setOnClickListener {
                listenerFavorite(new, binding.favoriteButtonNewItem)

            }
        }

        private fun configShareButton(new: New) {
            binding.shareButtonNewItem.setOnClickListener {
                val intent = Intent().apply {
                    type = "text/plain"
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, new.link)
                }

                it.context.startActivity(intent)
            }
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