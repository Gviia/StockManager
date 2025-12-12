package domain

import java.math.BigDecimal

data class Stock(
    val name: String,
    var currentPrice: BigDecimal,
    var previousPrice: BigDecimal = BigDecimal.ZERO,
    val code: String = name.take(3).uppercase()
)
