package provider

import domain.Stock
import java.math.BigDecimal
interface PriceProvider {
    fun getPrice(stock: Stock): BigDecimal
}
