package controller

import domain.User
import repository.StockList
import service.TradingService

class TradingController(
    private val tradingService: TradingService
) {

    fun buyStock(user: User, stockName: String, qty: Long): String {
        val stock = StockList.findStock(stockName)
            ?: return "해당 종목을 찾을 수 없습니다: $stockName"

        val result = tradingService.buy(user, stock, qty)
        return result.message
    }

    fun sellStock(user: User, stockName: String, qty: Long): String {
        val stock = StockList.findStock(stockName)
            ?: return "해당 종목을 찾을 수 없습니다: $stockName"

        val result = tradingService.sell(user, stock, qty)
        return result.message
    }
}