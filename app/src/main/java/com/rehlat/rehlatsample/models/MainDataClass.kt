package com.rehlat.rehlatsample.models

data class MainDataClass(
    val pagination: PaginationDataClass,
    val results: List<ProductDataClass>
)