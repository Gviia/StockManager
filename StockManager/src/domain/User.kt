package domain

import java.math.BigDecimal // BigDecimal import 추가

data class User(
    val userId: String,
    val name: String,
    var cash: BigDecimal,
    val portfolio: Portfolio = Portfolio()
) {
    //  cash를 BigDecimal로 초기화하는 보조 생성자 추가
    constructor(userId: String, name: String, initialCash: Long) :
            this(userId, name, BigDecimal(initialCash))
}