package com.example.tutorialhachinet.model.characters


data class Character(
    val id: Int,
    val comics: Comics,
    val description: String,
    val events: Events,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    var thumbnail: Thumbnail,
    val urls: List<Url>
) {
    fun getImageUrl(): String {
        return thumbnail.path + thumbnail.extension
    }
}