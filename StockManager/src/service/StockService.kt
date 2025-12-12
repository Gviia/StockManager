package service

import provider.PriceProvider
import repository.StockList
// 나중에 우리 코드 합칠때  현재가를 어디서 받아올건지 모르겠지만 만약 어디선가 변경이 생기면 변경해야함

class StockService(
    private val priceProvider: PriceProvider
) {

    data class StockInfo(
        val name: String,                       // 종목명
        val currentPrice: Long,                 // 현재가
        val changeRate: Double = 0.0            // 변동률
    )

    // 변동률 포함 주식 목록 조회
    fun getAllStocks(): List<StockInfo> {
        return StockList.getStocks().map { stock ->
            // PriceProvider는 RandomPriceProvider를 사용
            val current = priceProvider.getPrice(stock)   // newPrice
            val percent = (current - stock.initialPrice) * 100.0 / stock.initialPrice  // 변동률 계산
            // 현재가 - 주식의 초기값으로 계산해서 사용이 이 기능이

            StockInfo(
                name = stock.name,
                currentPrice = current,
                changeRate = percent
            )
        }
    }
}