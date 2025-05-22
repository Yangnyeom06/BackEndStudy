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
    /**
     * 게시글 작성
     */
    fun createPost(autherId: Long, authorLoginId: String, authorName: String, request: PostRequest): PostResponse {
        val post = PostEntities(title = request.title, content = request.content, authorId = autherId, autherLoginId = authorLoginId, authorName = authorName)
        val saved = postRepository.save(post)
        return PostResponse.from(saved)
    }

    /**
     * 게시글 수정
     */
    fun updatePost(postId: Long, authorId: Long, request: PostRequest): PostResponse {
        val post = postRepository.findById(postId).orElseThrow { IllegalArgumentException("게시글 없음") }

        if (post.authorId != authorId) throw IllegalAccessException("작성자만 수정 가능")

        post.update(request.title, request.content)
        return PostResponse.from(post)
    }

    /**
     * 게시글 삭제
     */
    fun deletePost(postId: Long, authorId: Long) {
        val post = postRepository.findById(postId).orElseThrow { IllegalArgumentException("게시글 없음") }

        if (post.authorId != authorId) throw IllegalAccessException("작성자만 삭제 가능")

        postRepository.delete(post)
    }

    /**
     * 모든 게시글 조회
     */
    fun getAllPosts(): List<PostEntities> {
        return postRepository.findAll()
    }

    /**
     * 게시글 조회
     */
    fun getPost(postId: Long): PostEntities {
        val post = postRepository.findById(postId).orElseThrow { IllegalArgumentException("게시글 없음") }
        return post
    }
}