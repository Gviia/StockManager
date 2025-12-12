// service/PortfolioService.kt

package service

import domain.User
import provider.PriceProvider
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * UI에 표시할 포트폴리오 평가 결과를 담는 DTO
 */
data class PortfolioValuation(
    val stockName: String,
    val quantity: Long,
    val averagePrice: BigDecimal, // 매입 평균 단가
    val currentPrice: BigDecimal, // 현재 시장 가격
    val totalCost: BigDecimal,    // 총 매입 금액 (평단가 * 수량)
    val valuation: BigDecimal,    // 총 평가 금액 (현재가 * 수량)
    val profitLoss: BigDecimal,   // 평가 손익 (Valuation - TotalCost)
    val profitRate: BigDecimal    // 수익률 (%)
)

class PortfolioService(
    private val priceProvider: PriceProvider
) {

      //사용자의 포트폴리오를 평가하고 종목별 수익률 정보를 반환합니다.

    fun getPortfolioValuation(user: User): List<PortfolioValuation> {

        return user.portfolio.items.values.map { item ->
            // 현재 가격 조회
            val currentPrice = priceProvider.getPrice(item.stock)

            // 1. 총 매입 금액 및 총 평가 금액 계산
            val totalCost = item.averagePrice.multiply(BigDecimal(item.quantity))
            val valuation = currentPrice.multiply(BigDecimal(item.quantity))

            // 2. 평가 손익 및 수익률 계산
            val profitLoss = valuation.subtract(totalCost)

            // 수익률 계산: (평가 손익 / 총 매입 금액) * 100
            val profitRate: BigDecimal = if (totalCost.compareTo(BigDecimal.ZERO) == 0) {
                BigDecimal.ZERO // 0으로 나누는 것 방지
            } else {
                profitLoss.divide(totalCost, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal(100))
                    .setScale(2, RoundingMode.HALF_UP) // % 표시를 위해 소수점 2자리로 포맷
            }

            // DTO로 변환
            PortfolioValuation(
                stockName = item.stock.name,
                quantity = item.quantity,
                averagePrice = item.averagePrice,
                currentPrice = currentPrice,
                totalCost = totalCost,
                valuation = valuation,
                profitLoss = profitLoss,
                profitRate = profitRate
            )
        }.toList()
    }
}