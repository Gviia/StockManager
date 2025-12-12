package problem2

import problem1.determineyear
import java.util.Scanner

fun lastdate(year: Int, month:Int): Int {
    return when (month){
        1,3,5,7,8,10,12 -> 31
        4,6,9,11 -> 30
        2 -> if (determineyear(year)) 29
        else 28
        else -> 0}
}


fun main(){

    println("연도를 입력하십시오:")
    val year : Int = Scanner(System.`in`).nextInt()
    println("월을 입력하십시오:")
    val month : Int = Scanner(System.`in`).nextInt()

    if (lastdate(year,month)!=0)
        println("${year}년 ${month}월의 마지막 날은 ${lastdate(year, month)}입니다")
    else
        println("유효한 달이 아닙니다")
}
