package study.study.member.repository

import study.study.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import study.study.member.entity.MemberRole
import study.study.common.status.DormitoryType

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?

    fun findAllByDormitoryType(dormitoryType: DormitoryType) : List<Member>
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long>