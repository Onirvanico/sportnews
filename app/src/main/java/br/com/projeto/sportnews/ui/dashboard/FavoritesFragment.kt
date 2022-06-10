package br.com.projeto.sportnews.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import br.com.projeto.sportnews.NewsApplication
import br.com.projeto.sportnews.R
import br.com.projeto.sportnews.databinding.FragmentFavoritesBinding
import br.com.projeto.sportnews.domain.New
import br.com.projeto.sportnews.ui.adapter.NewsAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    lateinit var stub: ViewStub

    private val viewModel: FavoritesViewModel by activityViewModels {
        FavoriteViewModelFactory(
            (activity?.application as NewsApplication).db.getDAO()
        )
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getFavorites()

        loadFavorites()

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        stub = _binding?.root!!.findViewById(R.id.no_list_stub)

        return root
    }

    private fun loadFavorites() {
        activity?.let {
            viewModel.state.observe(it) { state ->
                when (state) {
                    is FavoritesViewModel.State.Loading -> {

                    }

                    is FavoritesViewModel.State.Failure -> {

                    }

                    is FavoritesViewModel.State.Success -> {
                        if(state.result.size > 0) configAdapter(state)
                        else showEmptyList()
                    }
                }
            }
        }
    }

    private fun configAdapter(state: FavoritesViewModel.State.Success) {
        _binding?.favoritesRecyclerview?.setHasFixedSize(true)
        val adapter = NewsAdapter(state.result)
        _binding?.favoritesRecyclerview?.adapter = adapter
        configFavoriteButton(adapter)
    }

    private fun configFavoriteButton(adapter: NewsAdapter) {
        adapter.listenerFavorite = { new: New, imageButton: ImageButton ->
            viewModel.removeNew(new)
        }
    }

    private fun showEmptyList() {
        stub.visibility = View.VISIBLE
        _binding?.favoritesRecyclerview?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        loadFavorites()
    }
}