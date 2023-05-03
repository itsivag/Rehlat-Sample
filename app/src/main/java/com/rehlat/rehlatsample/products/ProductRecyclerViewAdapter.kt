package com.rehlat.rehlatsample.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.card.MaterialCardView
import com.rehlat.rehlatsample.R
import com.rehlat.rehlatsample.models.ProductDataClass

class ProductRecyclerViewAdapter(
    private val context: Context?,
    private val productList: ArrayList<ProductDataClass>,
    private val findNavController: NavController
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val cvProduct: MaterialCardView = itemView.findViewById(R.id.cvProduct)
        val tvProductCreatedAt: TextView = itemView.findViewById(R.id.tvProductCreatedAt)
        val ivProductThumbnail: ImageView = itemView.findViewById(R.id.ivProductThumbnail)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//inflating recyclerView item layout
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.product_recycler_view_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.tvProductName.text = currentItem.name
        holder.tvProductPrice.text = currentItem.price
        holder.tvProductCreatedAt.text = currentItem.created_at
        holder.cvProduct.setOnClickListener {
            //creating a bundle to be passed to product details fragment
            val bundle = Bundle().apply {
                putParcelable("product_metadata", productList[position])
            }
            //moving to product details fragment on cardView click
            findNavController.navigate(
                R.id.action_ProductListingFragment_to_ProductDetailsFragment,
                bundle
            )
        }

        val imageUrlThumbnail = currentItem.image_urls_thumbnails.toString()
        val imageUrl = imageUrlThumbnail.subSequence(1, imageUrlThumbnail.length - 1).toString()

        //Using glide to display image from URL
        Glide
            .with(context!!)
            .load(imageUrl)
            .placeholder(ResourcesCompat.getDrawable(context.resources, R.drawable.iv_placeholder, null))
            .error(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.baseline_image_not_supported_24,
                    null
                )
            )
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()
            .into(holder.ivProductThumbnail)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
