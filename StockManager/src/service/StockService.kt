package service

import domain.Stock
import provider.PriceProvider
import java.math.BigDecimal
import java.math.RoundingMode

// UI에 표시할 주식 정보를 담는 DTO (Data Transfer Object)
data class StockInfo(
    val name: String,
    val currentPrice: BigDecimal,
    val changeRate: BigDecimal
)


  //주식 목록 조회 및 표시 관련 비즈니스 로직을 담당합니다.
  //주식의 현재가와 전일가를 비교하여 변동률을 계산합니다.

class StockService(
    private val priceProvider: PriceProvider
) {
    fun getAllStocks(stocks: List<Stock>): List<StockInfo> {
        return stocks.map { stock ->

            // 1. 현재 가격 조회 (PriceProvider는 BigDecimal 반환)
            val current = priceProvider.getPrice(stock)

            // 2. 변동률 계산의 기준이 되는 전일가(Previous Price)
            val previous = stock.previousPrice

            // 3. 변동률 계산: ((현재가 - 전일가) / 전일가) * 100
            val percent: BigDecimal = if (previous.compareTo(BigDecimal.ZERO) == 0) {
                // 전일가가 0이면 변동률 0으로 처리 (0으로 나누는 것 방지)
                BigDecimal.ZERO
            } else {
                current.subtract(previous) // 현재가 - 전일가 (변동액)
                    .divide(previous, 4, RoundingMode.HALF_UP) // 변동액 / 전일가 (변동률, 소수점 4자리까지 계산)
                    .multiply(BigDecimal(100)) // 100을 곱하여 %로 변환
                    .setScale(2, RoundingMode.HALF_UP) // % 표기를 위해 소수점 2자리로 포맷
            }


            StockInfo(
                name = stock.name,
                currentPrice = current,
                changeRate = percent
            )
        }.toList()
    }

    fun calculateChangeRate(currentPrice: BigDecimal, previousPrice: BigDecimal): BigDecimal {
        return if (previousPrice.compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal.ZERO
        } else {
            currentPrice.subtract(previousPrice)
                .divide(previousPrice, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP)
        }
    }
}