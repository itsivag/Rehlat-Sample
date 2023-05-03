package com.rehlat.rehlatsample.products

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.rehlat.rehlatsample.R
import com.rehlat.rehlatsample.api.ApiInterface
import com.rehlat.rehlatsample.api.ApiUtils
import com.rehlat.rehlatsample.databinding.FragmentProductListingBinding
import com.rehlat.rehlatsample.models.ProductDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ProductListingFragment : Fragment() {
    //creating a ArrayList to store data from retrofit
    private lateinit var productList: ArrayList<ProductDataClass>
    private var _binding: FragmentProductListingBinding? = null
    private val binding get() = _binding!!
    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productList = arrayListOf()

        //creating a coroutine
        lifecycle.coroutineScope.launch(Dispatchers.IO) {
            try {
                val res = ApiUtils.getInstance().create(ApiInterface::class.java)
                res.getData().body()?.results?.iterator()?.forEach { product ->
                    if (!productList.contains(product))
                        productList.add(product)
                    // the flag is set true to indicate data loading is complete
                    isDataLoaded = true
                }

                // handling the UI thread sequentially
                withContext(Dispatchers.Main) {
                    binding.circularProgressIndicator.visibility = View.GONE
                    binding.rvProductRecyclerView.adapter?.notifyItemInserted(0)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.i("sivag", e.toString())
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initializing recyclerView for product's list
        val rv = binding.rvProductRecyclerView
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter =
            ProductRecyclerViewAdapter(context, productList, findNavController())
        //progress
        binding.circularProgressIndicator.visibility = View.VISIBLE

        //inflating menu on button click
        binding.btSortBy.setOnClickListener {
            showMenu(binding.btSortBy, R.menu.sort_by_menu)
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->

            //displaying selected sort in textView
            binding.tvSortBy.text = menuItem.title
            // Respond to menu item click.
            when (menuItem.itemId) {
                R.id.action_price_sort_ascending -> {
                    productList.sortBy { it.price?.replace("AED ", "")?.toInt() }
                    binding.rvProductRecyclerView.adapter?.notifyItemRangeChanged(
                        0,
                        productList.size
                    )
                    true
                }

                R.id.action_price_sort_descending -> {
                    productList.sortByDescending { it.price?.replace("AED ", "")?.toInt() }
                    binding.rvProductRecyclerView.adapter?.notifyItemRangeChanged(
                        0,
                        productList.size
                    )
                    true
                }

                R.id.action_date_sort_new_to_old -> {
                    productList.sortByDescending { it.created_at }
                    binding.rvProductRecyclerView.adapter?.notifyItemRangeChanged(
                        0,
                        productList.size
                    )
                    true
                }

                R.id.action_date_sort_old_to_new -> {
                    productList.sortBy { it.created_at }
                    binding.rvProductRecyclerView.adapter?.notifyItemRangeChanged(
                        0,
                        productList.size
                    )
                    true
                }

                else -> {
                    false
                }
            }
        }
        // Show the popup menu.
        popup.show()
    }

    override fun onResume() {
        super.onResume()
        //checking the flag to hide progress when coming from any fragment
        if (isDataLoaded) {
            binding.circularProgressIndicator.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}