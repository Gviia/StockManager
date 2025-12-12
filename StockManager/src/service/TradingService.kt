package service

import domain.*
import provider.PriceProvider
import util.toWon

class TradingService(
    private val priceProvider: PriceProvider
) {

    fun buy(user: User, stock: Stock, qty: Long): TradeResult {
        val price = priceProvider.getPrice(stock)
        val totalCost = price * qty

        if (user.cash < totalCost) {
            return TradeResult(
                success = false,
                message = "매수 실패: 필요한 금액 ${totalCost.toWon()}, 보유 금액 ${user.cash.toWon()}"
            )
        }

        user.cash -= totalCost
        user.portfolio.addOrUpdateItem(stock, qty, price)

        return TradeResult(
            success = true,
            message = "${stock.name} ${qty}주 매수 완료! " +
                    "단가: ${price.toWon()} / 총액: ${totalCost.toWon()} / 남은 잔액: ${user.cash.toWon()}"
        )
    }

    fun sell(user: User, stock: Stock, qty: Long): TradeResult {
        val item = user.portfolio.items.find { it.stock.name == stock.name }
            ?: return TradeResult(
                success = false,
                message = "매도 실패: 보유하지 않은 종목입니다 (${stock.name})"
            )

        if (item.quantity < qty) {
            return TradeResult(
                success = false,
                message = "매도 실패: 보유 수량 부족 (보유 ${item.quantity}주, 요청 ${qty}주)"
            )
        }

        val price = priceProvider.getPrice(stock)
        val revenue = price * qty

        user.cash += revenue
        item.quantity -= qty
        user.portfolio.removeItemIfEmpty(stock)

        return TradeResult(
            success = true,
            message = "${stock.name} ${qty}주 매도 완료! " +
                    "단가: ${price.toWon()} / 총액: ${revenue.toWon()} / 남은 잔액: ${user.cash.toWon()}"
        )
    }
}
