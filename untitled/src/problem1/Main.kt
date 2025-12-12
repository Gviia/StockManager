package problem1

import java.util.Scanner

fun determineyear(year : Int) : Boolean {
    return (year%400 == 0)|| (year%4==0 && year %100 !=0)

}


fun main() {

    println("연도를 입력하십시오:")
    val year : Int = Scanner(System.`in`).nextInt()

    if (determineyear(year))
        println("${year}는 윤년입니다")
    else
        println("${year}는 평년입니다")

}
