package data.repository

import data.mapper.toModel
import data.source.remote.PostsApiSource
import domain.model.Post
import domain.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

/**
 * Post repository implementation.
 *
 * @author Martin Vana
 */
class PostRepositoryImpl(
    private val postsApiSource: PostsApiSource,
) : PostRepository {

    override suspend fun getPosts(forceReload: Boolean): List<Post> {
        return withContext(Dispatchers.IO) {
            postsApiSource.fetchPosts().map { it.toModel() }
        }
    }

    override suspend fun getPost(id: Long): Post =
        postsApiSource.fetchPost(id).toModel()

    override suspend fun createPost(post: Post): Long =
        1L //postsApiSource.createPost(post.toDto())

    override suspend fun updatePost(id: Long, post: Post) =
        Unit //postsApiSource.updatePost(post.id, post.toDto())

    override suspend fun deletePost(id: Long) =
        Unit //postsApiSource.deletePost(id)
}