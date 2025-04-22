package study.study.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.post.entity.PostEntities

interface PostRepository : JpaRepository<PostEntities, Long>{
    fun findByTitle(title: String): PostEntities?
}