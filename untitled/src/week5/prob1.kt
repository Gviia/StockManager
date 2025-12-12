package week5

import java.util.UUID
import java.time.LocalTime


class Account(bank:String) {

    private val id = UUID.randomUUID().toString()
    private var balance = 0
    private var transferFee : Int = 0
    private val bankType : String = bank
    private var leastTransfer : Int = 1000
    private val maxTransfers : Int
        get() = when(bankType){
            "Hana" -> 5000000
            "Woori" -> 3000000
            "IBK" -> 2000000
            else -> 1000000

        }
    private val openBank = LocalTime.of(9, 0)
    private val closeBank = LocalTime.of(23, 0)
    private fun isBankOpen() = LocalTime.now() in openBank..closeBank

    fun myBalance(): Int{
        return balance
    }
    fun myBank(): String{
        return bankType
    }

    fun withdraw(amount:Int): Unit{
        if (amount>balance)
            println("잔액이 부족합니다")
        else{
            balance -= amount
            println("${this.bankType}으로부터 ${amount}원이 출금되어 잔액은 ${balance}원 입니다.")}
    }

    fun deposit(amount:Int): Unit{
        balance += amount
        println("${this.bankType}에 ${amount}원이 입금되어 잔액은 ${balance}원 입니다.")
    }

    fun transferTo(accA: Account, amount:Int){

        if (!isBankOpen()){
            println("현재는 영업시간이 아닙니다.")
            return
        }

        if (accA.bankType != this.bankType) {
            transferFee = 100
        } else {
            transferFee = 0
        }
        if (leastTransfer < amount) {

            if (amount > maxTransfers) {
                println("송금 실패: ${bankType} 한도(${maxTransfers}원)를 초과했습니다")
                return
            }

            if (this.myBalance() >= amount+transferFee) {
                this.withdraw(amount+transferFee)
                accA.deposit(amount)
                println("${this.bankType}에서 ${accA.bankType}에 ${amount}원이 송금되었습니다")
                println("송금 수수료는 ${transferFee}원입니다")
            } else {
                println("송금 실패: 잔액 부족")
            }
        } else{
            println("최소 송금 금액보다 금액이 작습니다")
        }
    }
}

fun main(){

    val account1 = Account("Hana")
    account1.deposit(10000)
    val account2 = Account("Hana")
    account2.deposit(10000)

    account1.transferTo(account2,   500)

    println("${account1.myBank()} 잔액 : ${account1.myBalance()}원, ${account2.myBank()} 잔액 : ${account2.myBalance()}원")

}