package provider

import domain.Stock
import kotlin.random.Random

class CachedRandomPriceProvider(
    private val baseProvider: PriceProvider = RandomPriceProvider(),
    private val refreshIntervalMs: Long = 60_000L   // 1분(60,000ms)
) : PriceProvider {

    private val lastPrices = mutableMapOf<String, Long>()
    private val lastUpdateTimes = mutableMapOf<String, Long>()

    override fun getPrice(stock: Stock): Long {
        val now = System.currentTimeMillis()
        val lastTime = lastUpdateTimes[stock.name] ?: 0L

        // 1분 경과 → 새로운 가격 생성
        if (now - lastTime >= refreshIntervalMs) {
            val newPrice = baseProvider.getPrice(stock)
            lastPrices[stock.name] = newPrice
            lastUpdateTimes[stock.name] = now
            return newPrice
        }

        // 1분 이내 → 기존 가격 유지
        return lastPrices[stock.name]
            ?: baseProvider.getPrice(stock).also { price ->
                lastPrices[stock.name] = price
                lastUpdateTimes[stock.name] = now
            }
    }
}
