package com.example.tutorialhachinet.model.characters


data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)