package domain.usecase

import domain.repository.PostRepository

/**
 * Use case for getting post by id.
 *
 * @author Martin Vana
 */
class GetPostUseCase(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(id: Long) = postRepository.getPost(id)
}