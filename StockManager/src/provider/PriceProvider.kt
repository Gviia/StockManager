package provider

import domain.Stock

interface PriceProvider {
    fun getPrice(stock: Stock): Long
}
