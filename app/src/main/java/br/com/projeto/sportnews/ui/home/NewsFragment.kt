package br.com.projeto.sportnews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.projeto.sportnews.databinding.FragmentNewsBinding
import br.com.projeto.sportnews.retrofit.SportNewsRetrofit
import br.com.projeto.sportnews.ui.adapter.NewsAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        homeViewModel.news.observe(viewLifecycleOwner) {
            binding.newsRecyclerview.setHasFixedSize(true)
            Log.i("TESTE", it.toString())
            val adapter = NewsAdapter(it)
            binding.newsRecyclerview.adapter = adapter
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}