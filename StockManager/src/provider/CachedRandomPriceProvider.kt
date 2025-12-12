package provider

import domain.Stock
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.ConcurrentHashMap

// PriceProvider 인터페이스를 구현하며 캐시와 갱신을 담당합니다.
class CachedRandomPriceProvider(
    // [의존성] 가격 변동 알고리즘을 제공하는 RandomPriceProvider에 의존
    private val randomPriceProvider: RandomPriceProvider
) : PriceProvider {

    // 캐시 저장소: Key=Stock.name, Value=BigDecimal 가격
    private val priceCache: MutableMap<String, BigDecimal> = ConcurrentHashMap()


    //[조회 책임]: 외부에서 주가를 조회할 때 사용됩니다. 캐시된 현재 가격을 반환합니다.

    override fun getPrice(stock: Stock): BigDecimal {
        // 캐시에 없으면 Stock 객체의 현재 가격(초기값)을 사용합니다.
        return priceCache.getOrPut(stock.name) {
            stock.currentPrice
        }
    }


    //[갱신 책임]: PriceUpdateService에 의해 1분마다 호출됩니다.

    fun updateAllPrices(stocks: List<Stock>) {
        stocks.forEach { stock ->
            val stockName = stock.name

            // 1. 캐시에서 현재 가격을 가져옵니다. (캐시에 없으면 Stock의 초기 가격을 사용)
            val currentCachedPrice = priceCache.getOrPut(stockName) { stock.currentPrice }

            // 2. 현재 캐시된 가격을 Stock 객체의 'previousPrice'로 저장합니다.
            stock.previousPrice = currentCachedPrice

            // 3. RandomPriceProvider에게 새로운 가격 계산을 위임합니다.
            val newPrice = randomPriceProvider.getUpdatedPrice(stock, currentCachedPrice)

            // 4. 캐시와 Stock 객체의 'currentPrice'를 새로운 가격으로 갱신합니다.
            priceCache[stockName] = newPrice
            stock.currentPrice = newPrice
        }
    }

}