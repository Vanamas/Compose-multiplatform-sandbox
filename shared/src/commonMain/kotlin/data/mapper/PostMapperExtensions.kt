package data.mapper

import data.model.PostDto
import domain.model.Post

/**
 * Mapper extensions of [PostDto], [Post] and [PostEntity] models.
 *
 * @author Martin Vana
 */


fun PostDto.toModel() = Post(id, title, body, userId)

fun Post.toDto() = PostDto(id, title, body, userId)
