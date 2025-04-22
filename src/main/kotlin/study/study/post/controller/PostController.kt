package study.study.post.controller

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestParam
import study.study.common.dto.BaseResponse
import study.study.common.dto.CustomUser
import study.study.member.dto.MemberDtoRequest
import study.study.member.service.MemberService
import study.study.post.dto.PostRequest
import study.study.post.dto.PostResponse
import study.study.post.service.PostService
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private


@RequestMapping("/api/post")
@RestController
class PostController(
    private val postService: PostService,
    private val memberService: MemberService
) {

    @PostMapping
    fun createPost(@RequestBody @Valid request: PostRequest): BaseResponse<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val user = memberService.searchMyInfo(userId)
        val autherName = user.name
        val autherLoginId = user.loginId
        postService.createPost(userId, autherLoginId, autherName, request)
        return BaseResponse(message = "게시글이 작성되었습니다.")
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody request: PostRequest
    ): BaseResponse<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        postService.updatePost(userId, postId, request)
        return BaseResponse(message = "게시글이 수정되었습니다.")
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): BaseResponse<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        postService.deletePost(userId, postId)
        return BaseResponse(message = "게시글이 삭제되었습니다.")
    }

    @GetMapping
    fun getAllPosts(): BaseResponse<List<PostResponse>> {
        val posts = postService.getAllPosts()
        return BaseResponse(data = posts, message = "전체 게시글 조회 성공")
    }

    @GetMapping("/search")
    fun getPost(
        @RequestParam(required = false) postId: Long?,
        @RequestParam(required = false) postTitle: String?
    ): BaseResponse<PostResponse> {
        val post = postService.getPost(postId, postTitle)
        return BaseResponse(data = post, message = "게시글 검색 성공")
    }
}
