package service

import domain.*
import provider.PriceProvider


class PortfolioService(
    private val priceProvider: PriceProvider
) {

    fun calculateCurrentValue(item: PortfolioItem): Long {
        val current = priceProvider.getPrice(item.stock)
        return current * item.quantity
    }

    fun calculateProfitLoss(item: PortfolioItem): Long {
        val current = priceProvider.getPrice(item.stock)
        return (current - item.avgBuyPrice) * item.quantity
    }

    fun calculateTotalValuation(portfolio: Portfolio): Long =
        portfolio.items.sumOf { calculateCurrentValue(it) }

    fun calculateReturnRate(portfolio: Portfolio): Double {
        val invested = portfolio.items.sumOf { it.quantity * it.avgBuyPrice }
        if (invested == 0L) return 0.0
        val current = calculateTotalValuation(portfolio)
        return ((current - invested).toDouble() / invested * 100.0)
    }
}