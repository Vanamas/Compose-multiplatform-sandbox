package domain.repository

import domain.model.Post

/**
 * Post repository.
 *
 * @author Martin Vana
 */
interface PostRepository {

    suspend fun getPosts(forceReload: Boolean = false): List<Post>

    suspend fun getPost(id: Long): Post

    suspend fun createPost(post: Post): Long

    suspend fun updatePost(id: Long, post: Post)

    suspend fun deletePost(id: Long)
}