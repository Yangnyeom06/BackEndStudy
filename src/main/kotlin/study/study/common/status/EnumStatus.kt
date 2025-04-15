package study.study.common.status

/*
enum class Gender(val desc: String) {
    MAN("남"),
    WOMAN("여")
}
*/

enum class DormitoryType(val desc: String) {
    GOUN_A("고운A"),
    GOUN_B("고운B"),
    GOUN_C("고운C"),
    GYEONGSANG_11("경상11"),
    GYEONGSANG_12("경상12"),
    GYEONGSANG_13("경상13"),
    GYEONGSANG_14("경상14")
}

enum class ResultCode(val msg: String) {
    SUCCESS("정상처리 되었습니다."),
    ERROR("에러가 발생했습니다.")
}

enum class ROLE {
    MEMBER
}