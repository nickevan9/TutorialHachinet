package com.example.tutorialhachinet.model.characters

import com.google.gson.annotations.SerializedName

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @SerializedName("results")
    val characters: List<Character>,
    val total: Int
)