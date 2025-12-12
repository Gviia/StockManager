package domain

data class User(
    val userId: String,
    val name: String,
    var cash: Long,
    val portfolio: Portfolio = Portfolio()
)
