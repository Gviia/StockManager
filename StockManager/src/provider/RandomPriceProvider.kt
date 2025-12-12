package provider

import domain.Stock
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class RandomPriceProvider : PriceProvider {
    
    override fun getPrice(stock: Stock): BigDecimal {
        // CachedRandomPriceProvider를 사용할 경우, 이 메서드는 사실상 호출되지 않습니다.
        // 하지만 인터페이스 계약 이행을 위해 Stock의 현재 가격을 반환합니다.
        return stock.currentPrice
    }

    // 현재 가격을 인수로 받아 다음 가격을 계산하는 순수 변동 로직을 제공
    fun getUpdatedPrice(stock: Stock, currentPrice: BigDecimal): BigDecimal {
        // ... (이전에 정의했던 -50% ~ +50% 변동 로직)
        val randomFactor = Random.nextDouble(-0.5, 0.5)
        val changeFactor = BigDecimal.ONE.add(BigDecimal(randomFactor))

        var newPrice = currentPrice.multiply(changeFactor)

        if (newPrice.compareTo(BigDecimal.ONE) < 0) {
            newPrice = BigDecimal.ONE
        }

        return newPrice.setScale(2, RoundingMode.HALF_UP)
    }
}