package study.study.common.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUser(
    val userId: Long,
    val userName: String,
    userLoginId: String,
    password: String,
    authorities: Collection<GrantedAuthority>
) : User(userLoginId, password, authorities)