package com.rehlat.rehlatsample.products

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rehlat.rehlatsample.R
import com.rehlat.rehlatsample.databinding.FragmentProductDetailsBinding
import com.rehlat.rehlatsample.models.ProductDataClass

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //receiving data from productRecyclerAdapter
        val product =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("product_metadata", ProductDataClass::class.java)!!
            } else {
                arguments?.getParcelable("product_metadata")!!
            }

        binding.tvName.text = product.name
        binding.tvCreatedAt.text = product.created_at
        binding.tvPrice.text = product.price
        binding.tvUid.text = product.uid

        val imageUrlThumbnail = product.image_urls.toString()
        val imageUrl = imageUrlThumbnail.subSequence(1, imageUrlThumbnail.length - 1).toString()

        //Using glide to display image from URL
        Glide
            .with(requireContext())
            .load(imageUrl)
            .placeholder(ResourcesCompat.getDrawable(resources, R.drawable.iv_placeholder, null))
            .error(ResourcesCompat.getDrawable(resources, R.drawable.baseline_image_not_supported_24, null))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()
            .into(binding.ivProductImage)

        //setting image ID as content description
        binding.ivProductImage.contentDescription = product.image_ids.toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}