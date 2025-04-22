package study.study.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import study.study.post.entity.PostEntities
import java.time.LocalDateTime

data class PostRequest(
    @field:NotBlank(message = "제목은 공백일 수 없습니다.")
    @field:NotNull(message = "제목은 필수 입력 사항입니다.")
    @JsonProperty("title")
    private val _title: String,

    @field:NotBlank(message = "내용은 공백일 수 없습니다.")
    @field:NotNull(message = "내용은 필수 입력 사항입니다.")
    @JsonProperty("content")
    private val _content: String,

) {
    val title: String
        get() = _title
    val content: String
        get() = _content
}

data class PostResponse(
    val Id: Long,
    val userId: Long,
    val userLoginId: String,
    val userName : String,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime
) {
    companion object {
        fun from(post: PostEntities): PostResponse {
            return PostResponse(
                Id = post.id,
                userId = post.authorId,
                userLoginId = post.autherLoginId,
                userName = post.authorName,
                title = post.title,
                content = post.content,
                createdAt = post.createdAt,
                modifiedAt = post.modifiedAt
            )
        }
    }
}