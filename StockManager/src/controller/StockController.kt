package controller
import repository.StockList
import service.StockService
import service.StockInfo

class StockController(
    private val stockService: StockService,
    private val stockList: StockList
) {

    fun showStockList(): List<StockInfo> { // ⬅️ StockService. 제거
        val allStocks = stockList.getStocks()
        return stockService.getAllStocks(allStocks)
    }
}