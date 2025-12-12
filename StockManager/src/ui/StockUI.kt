package ui

import controller.StockController
import util.toWon

class StockUI(
    private val controller: StockController
) {

    fun displayStockList() {
        println("=== 주식 목록 조회 ===")
        val list = controller.showStockList()

        list.forEach { info ->
            println(
                "종목: ${info.name} | " +
                        "현재가: ${info.currentPrice.toWon()} | " +
                        "변동률: ${"%.2f".format(info.changeRate)}%"
            )
        }
    }
}