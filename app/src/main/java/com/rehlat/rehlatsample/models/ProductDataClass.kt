package com.rehlat.rehlatsample.models

import android.os.Parcel
import android.os.Parcelable

data class ProductDataClass(
    var created_at: String? = null,
    var image_ids: List<String>? = null,
    var image_urls: List<String>? = null,
    var image_urls_thumbnails: List<String>? = null,
    var name: String? = null,
    var price: String? = null,
    var uid: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(created_at)
        parcel.writeStringList(image_ids)
        parcel.writeStringList(image_urls)
        parcel.writeStringList(image_urls_thumbnails)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(uid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDataClass> {
        override fun createFromParcel(parcel: Parcel): ProductDataClass {
            return ProductDataClass(parcel)
        }

        override fun newArray(size: Int): Array<ProductDataClass?> {
            return arrayOfNulls(size)
        }
    }
}