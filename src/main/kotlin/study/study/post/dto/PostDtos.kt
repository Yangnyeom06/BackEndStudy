package study.study.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class PostRequest(
    @field:NotBlank(message = "제목은 공백일 수 없습니다.")
    @field:NotNull(message = "제목은 필수 입력 사항입니다.")
    @JsonProperty("title")
    private val _title: String,

    @field:NotBlank(message = "내용은 공백일 수 없습니다.")
    @field:NotNull(message = "내용은 필수 입력 사항입니다.")
    @JsonProperty("content")
    private val _content: String,

    @field:NotBlank(message = "내용은 공백일 수 없습니다.")
    @field:NotNull(message = "내용은 필수 입력 사항입니다.")
    @JsonProperty("authorName")
    private val _authorName: String,

) {
    val title: String
        get() = _title
    val content: String
        get() = _content
}

data class PostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val authorId: Long,
    val authorName: String,
    val createdAt: String,
    val updatedAt: String
)
