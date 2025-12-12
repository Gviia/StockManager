package domain

import java.math.BigDecimal
import java.math.RoundingMode

class Portfolio {

    val items: MutableMap<String, PortfolioItem> = mutableMapOf()

    fun addOrUpdateItem(stock: Stock, newQty: Long, newPrice: BigDecimal) {
        val stockKey = stock.name

        if (items.containsKey(stockKey)) {
            val existingItem = items[stockKey]!!

            // 1. 기존 총 매입 금액
            val existingTotalCost = existingItem.averagePrice.multiply(BigDecimal(existingItem.quantity))

            // 2. 신규 매수 총 비용
            val newTotalCost = newPrice.multiply(BigDecimal(newQty))

            // 3. 총 수량과 총 비용 계산
            val totalQuantity = existingItem.quantity + newQty
            val totalCost = existingTotalCost.add(newTotalCost)

            // 4. 새로운 평균 단가 계산 (소수점 2자리까지 반올림)
            existingItem.averagePrice = totalCost.divide(
                BigDecimal(totalQuantity),
                // 소수점 4자리까지 계산 (평단가는 정확해야 함)
                4,
                java.math.RoundingMode.HALF_UP
            )
            existingItem.quantity = totalQuantity
        } else {
            // 처음 매수하는 경우
            items[stockKey] = PortfolioItem(stock, newQty, newPrice)
        }
    }

    // [매도 시 사용] 수량 차감 및 필요 시 항목 삭제
    fun subtractQuantity(stockName: String, qty: Long) {
        val item = items[stockName]
        if (item != null) {
            item.quantity -= qty
            if (item.quantity <= 0) {
                items.remove(stockName)
            }
        }
    }
}