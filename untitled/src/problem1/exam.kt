package problem1
import kotlin.random.Random

class Dice(){

    val a:Int= Random.nextInt(1,7)
    val b:Int= Random.nextInt(1,7)
}

class Result(val a:Int,val b:Int){

    val c=a+b

    fun showResult(){
        if (c>=10) println("You Win!")
        else println("You Lose!")
    }

}

fun main(){
    val attempt1 = Dice()
    val result1 = Result(attempt1.a,attempt1.b)
    result1.showResult()
}
