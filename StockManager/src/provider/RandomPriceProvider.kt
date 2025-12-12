package provider
import domain.Stock

class RandomPriceProvider : PriceProvider {

    private val priceMap = mutableMapOf<String, Long>()

    override fun getPrice(stock: Stock): Long {

        val current = priceMap.getOrPut(stock.name) {
            stock.initialPrice
        }
        val percent = (-50..50).random()
        var newPrice = current + (current * percent / 100)

        if (newPrice < 1) newPrice = 0
        priceMap[stock.name] = newPrice
        return newPrice
    }
}