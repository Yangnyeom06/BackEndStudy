package study.study.post.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.study.common.dto.CustomUser
import study.study.member.dto.MemberDtoRequest
import study.study.post.dto.PostRequest
import study.study.post.dto.PostResponse
import study.study.post.entity.PostEntities
import study.study.post.repository.PostRepository

@Transactional
@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun createPost(authorId: Long, request: PostRequest, memberDtoRequest: MemberDtoRequest): PostResponse {
        val post = PostEntities(title = request.title, content = request.content, authorId = authorId, authorName = memberDtoRequest.name)
        val saved = postRepository.save(post)
        return toResponse(saved)
    }

    fun updatePost(postId: Long, authorId: Long, request: PostRequest): PostResponse {
        val post = postRepository.findById(postId).orElseThrow { IllegalArgumentException("게시글 없음") }

        if (post.authorId != authorId) throw IllegalAccessException("작성자만 수정 가능")

        post.update(request.title, request.content)
        return toResponse(post)
    }

    fun deletePost(postId: Long, authorId: Long) {
        val post = postRepository.findById(postId).orElseThrow { IllegalArgumentException("게시글 없음") }

        if (post.authorId != authorId) throw IllegalAccessException("작성자만 삭제 가능")

        postRepository.delete(post)
    }

    private fun toResponse(post: PostEntities): PostResponse = PostResponse(
        id = post.id,
        title = post.title,
        content = post.content,
        authorId = post.authorId,
        authorName = post.authorName,
        createdAt = post.createdAt.toString(),
        updatedAt = post.modifiedAt.toString()
    )
}
