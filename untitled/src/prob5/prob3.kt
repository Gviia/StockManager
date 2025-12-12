package prob5

import java.util.UUID

class Account(bank:String) {

    private var balance = 0
    private var userid = UUID.randomUUID().toString()

    fun mybalance(): Int{
        return balance
    }

    fun withdraw(amount:Int) {
        if (amount > balance){
            println("잔액이 부족합니다")
        }
        else{
            balance -= amount
            println("${amount}원이 출금되어 잔액은 ${balance}원 입니다.")
        }
    }

    fun deposit(amount:Int) {
        balance += amount
        println("${amount}원이 입금되어 잔액은 ${balance}원 입니다.")
    }


}

fun main(){

    val account1 = Account("Hana")
    account1.deposit(10000)
    val account2 = Account("Woori")
    account2.deposit(50000)










    

}


