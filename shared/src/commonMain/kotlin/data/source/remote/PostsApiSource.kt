package data.source.remote

import data.model.PostDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * Sample post API source.
 *
 * @author Martin Vana
 */
class PostsApiSource(private val client: HttpClient) {

    suspend fun fetchPosts(): List<PostDto> = client.get("posts").body()

    suspend fun fetchPost(id: Long): PostDto = client.get("posts/$id").body()
}