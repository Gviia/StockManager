// service/TradingService.kt (수정)
package service

import domain.*
import provider.PriceProvider
import java.math.BigDecimal

class TradingService(
    private val priceProvider: PriceProvider
) {
    fun buy(user: User, stock: Stock, qty: Long): TradeResult {
        // 1. 현재 가격 조회 및 비용 계산 (BigDecimal)
        val price = priceProvider.getPrice(stock) // PriceProvider도 BigDecimal 반환하도록 수정 필요
        val totalCost = price.multiply(BigDecimal(qty))

        // 2. 잔액 확인 (User.cash는 BigDecimal로 가정)
        if (user.cash.compareTo(totalCost) < 0) {
            return TradeResult(success = false, message = "잔액 부족: 필요한 금액 $totalCost")
        }

        // 3. 거래 실행 (현금 차감)
        user.cash = user.cash.subtract(totalCost)

        // 4. 포트폴리오 갱신 (평균 단가 계산 로직 포함)
        user.portfolio.addOrUpdateItem(stock, qty, price)

        return TradeResult(success = true, message = "${stock.name} ${qty}주 매수 완료!")
    }

    fun sell(user: User, stock: Stock, qty: Long): TradeResult {
        val item = user.portfolio.items[stock.name]

        // 1. 보유 여부 및 수량 확인
        if (item == null || item.quantity < qty) {
            val owned = item?.quantity ?: 0L
            return TradeResult(success = false, message = "매도 실패: 보유 수량 부족 (보유: $owned 주, 요청: $qty 주)")
        }

        // 2. 가격 조회 및 수익 계산 (BigDecimal)
        val price = priceProvider.getPrice(stock)
        val revenue = price.multiply(BigDecimal(qty))

        // 3. 거래 실행 (현금 증가)
        user.cash = user.cash.add(revenue)

        // 4. 포트폴리오 갱신 (수량 차감)
        user.portfolio.subtractQuantity(stock.name, qty)

        return TradeResult(success = true, message = "${stock.name} ${qty}주 매도 완료!")
    }
}