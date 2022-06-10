package br.com.projeto.sportnews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import br.com.projeto.sportnews.NewsApplication
import br.com.projeto.sportnews.R
import br.com.projeto.sportnews.databinding.FragmentNewsBinding
import br.com.projeto.sportnews.domain.New
import br.com.projeto.sportnews.ui.adapter.NewsAdapter
import br.com.projeto.sportnews.ui.dashboard.FavoriteViewModelFactory
import br.com.projeto.sportnews.ui.dashboard.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.runBlocking


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    private val viewModel: FavoritesViewModel by activityViewModels {
        FavoriteViewModelFactory(
            (activity?.application as NewsApplication).db.getDAO()
        )
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadNews(homeViewModel)

        configSwipe(homeViewModel)

        return root
    }

    private fun configSwipe(homeViewModel: NewsViewModel) {
        binding.newsSwipe.setOnRefreshListener {
            loadNews(homeViewModel)
        }
    }

    private fun loadNews(homeViewModel: NewsViewModel) {

        binding.newsSwipe.isRefreshing = true

        homeViewModel.news.observe(viewLifecycleOwner) {

            binding.newsSwipe.isRefreshing = false
            binding.newsRecyclerview.setHasFixedSize(true)
            Log.i("TESTE", it.toString())
            val adapter = NewsAdapter(it)

            adapter.listenerFavorite = { new: New, view: ImageButton ->
                runBlocking {
                    Log.i("VALUE", "loadNews: " + viewModel.hasNew(new))
                    if(viewModel.hasNew(new) > 0) {
                        viewModel.removeNew(new)
                        Snackbar.make(binding.root,getString(R.string.remove_new_msg), 4000).show()
                        view.setColorFilter(null)

                    } else {
                        viewModel.saveNew(new)
                        Snackbar.make(binding.root,R.string.add_favorite_msg, 4000).show()
                        view.setColorFilter(android.R.color.holo_blue_light)
                    }
                }

            }
            binding.newsRecyclerview.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}