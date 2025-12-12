package domain

class Portfolio(
    val items: MutableList<PortfolioItem> = mutableListOf()
) {
    fun addOrUpdateItem(stock: Stock, qty: Long, avgPrice: Long) {
        val found = items.find { it.stock.name == stock.name }

        if (found == null) {
            items.add(PortfolioItem(stock, qty, avgPrice))
        } else {
            val totalQty = found.quantity + qty
            val totalCost = (found.quantity * found.avgBuyPrice) + (qty * avgPrice)
            found.quantity = totalQty
            found.avgBuyPrice = totalCost / totalQty
        }
    }

    fun removeItemIfEmpty(stock: Stock) {
        items.removeIf { it.stock.name == stock.name && it.quantity <= 0 }
    }
}
