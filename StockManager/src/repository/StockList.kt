package repository

import domain.Stock

object StockList {

    private val stocks = mutableListOf<Stock>()

    init {
        loadDefaultStocks()
    }

    private fun loadDefaultStocks() {
        stocks.clear()
        stocks.add(Stock("삼성전자", 70000))
        stocks.add(Stock("카카오", 55000))
        stocks.add(Stock("네이버", 180000))
        stocks.add(Stock("현대차", 190000))
    }

    fun getStocks(): List<Stock> = stocks

    fun findStock(name: String): Stock? =
        stocks.find { it.name == name }

    fun addStock(name: String, initialPrice: Long) {
        stocks.add(Stock(name, initialPrice))
    }

    fun reset() {
        // 테스트할 때 기본 종목 상태로 되돌리기
        loadDefaultStocks()
    }
}