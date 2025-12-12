package controller

import service.StockService

class StockController(
    private val stockService: StockService
) {
    // StockService에서 가져온 목록 그대로 반환
    fun showStockList(): List<StockService.StockInfo> {
        return stockService.getAllStocks()
    }
}