package domain

import java.math.BigDecimal

data class PortfolioItem(
    val stock: Stock,          // 보유 종목 정보
    var quantity: Long,        // 보유 수량
    var averagePrice: BigDecimal // 매입 평균 단가
)