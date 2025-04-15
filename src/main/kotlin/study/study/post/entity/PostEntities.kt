package study.study.post.entity

import Timestamped
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import study.study.common.dto.CustomUser
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
class PostEntities(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    var authorId: Long,

    @Column(nullable = false)
    var authorName: String,
) : Timestamped() {

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
