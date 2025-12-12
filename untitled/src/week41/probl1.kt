package week41

import java.util.Scanner

class Rectangle {

    var width = 0
    var height = 0

    fun calcArea() : Int {
        return width * height
    }

}

fun main(){

        val rectangle: Rectangle = Rectangle()

        print("사각형의 폭을 입력하시오>>")
        val width = Scanner(System.`in`).nextInt()
        print("사각형의 높이를 입력하시오>>")
        val height = Scanner(System.`in`).nextInt()

        rectangle.width = width
        rectangle.height = height

        val area =rectangle.calcArea()

    print("폭이 ${rectangle.width}이고 높이가 ${rectangle.height}인 사각형의 넓이는 ${area}입니다. ")
}