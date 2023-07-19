package data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: Long,
    val title: String,
    val body: String? = null,
    val userId: Int? = null
)
