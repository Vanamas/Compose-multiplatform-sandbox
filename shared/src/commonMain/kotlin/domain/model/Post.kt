package domain.model

data class Post(
    val id: Long,
    val title: String,
    val body: String? = null,
    val userId: Int? = null
)