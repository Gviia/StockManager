// domain/TradeResult.kt (최종 수정)
package domain

import java.math.BigDecimal

data class TradeResult(
    val success: Boolean,
    val message: String,
    val amount: BigDecimal = BigDecimal.ZERO,
    val remainingCash: BigDecimal = BigDecimal.ZERO
)