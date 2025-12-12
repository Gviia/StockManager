import domain.*
import provider.*
import service.TradingService
import controller.*

fun main() {
    val user = User("u1", "홍길동", 500000)
    val controller = TradingController(TradingService(CachedRandomPriceProvider()))

    println(controller.buyStock(user, "삼성전자", 3))
    println(controller.sellStock(user, "삼성전자", 1))
}
