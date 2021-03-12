package com.bbk.kidinvestor.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bbk.kidinvestor.MainViewModel
import com.bbk.kidinvestor.MainViewModelFactory
import com.bbk.kidinvestor.databinding.FragmentSearchBinding
import com.bbk.kidinvestor.utils.Status

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainViewModel =
            ViewModelProvider(
                requireActivity(),
                MainViewModelFactory()
            ).get(MainViewModel::class.java)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StockAdapter(context, ArrayList())
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.recyclerView.adapter = adapter

        mainViewModel.stockList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        mainViewModel.getAllStockList().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                    binding.loading.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(activity, "${it.message}", Toast.LENGTH_LONG).show()
                    println("Error occured + ${it.message}")
                    binding.loading.visibility = View.GONE
                }
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}