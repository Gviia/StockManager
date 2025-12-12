package domain

data class PortfolioItem(
    val stock: Stock,
    var quantity: Long,
    var avgBuyPrice: Long
)
