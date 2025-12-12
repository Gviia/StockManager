package problem3


import java.util.Scanner
import problem2.lastdate

fun validdates(year:Int, month: Int,date: Int):Boolean{

    return date <= lastdate(year, month)
}

fun sumofdates(year: Int , month:Int,date:Int):Int{
    var sum = 0

    for( i in 1 until month)
        sum +=  lastdate(year,i)

    sum += date

    return sum

}

fun calcdates(year:Int,month:Int,date:Int): Int {

    var total = 0

    total+= (year-1)*365
    total+= (year-1)/4-(year-1)/100+(year-1)/400
    total+= sumofdates(year,month,date)

    return total
}

fun main(){


    println("연도를 입력하십시오:")
    val year : Int = Scanner(System.`in`).nextInt()
    println("월을 입력하십시오:")
    val month : Int = Scanner(System.`in`).nextInt()
    println("일을 입력하십시오.:")
    val date : Int = Scanner(System.`in`).nextInt()

    if (month in 1..12 && validdates(year,month,date))
        println("기준일 (1년1월1일)부터 ${year}년 ${month}월 ${date}일까지의 총 일수는 ${calcdates(year,month,date)}입니다")
    else
        println("유효한 월 또는 일이 아닙니다")
}