package domain.usecase

import domain.repository.PostRepository

/**
 * Use case for getting all posts.
 *
 * @author Martin Vana
 */
class GetPostsUseCase(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(forceReload: Boolean = false) = postRepository.getPosts(forceReload)
}