package week42

import java.util.Scanner

class Rectangle {
    var width: Int
    var height: Int

    constructor() {
        width = 1
        height = 1
    }

    constructor(width: Int, height: Int) {
        if (width > 0)
            this.width = width
        else {
            println("폭이 1로 초기화 되었습니다.")
            this.width = 1
        }
        if (height > 0)
            this.height = height
        else {
            println("높이가 1로 초기화 되었습니다")
            this.height = 1
        }
    }

    fun calcArea() : Int {
        return width * height
    }
    }

fun main() {
    val rectangle: Rectangle = Rectangle()

    print("사각형의 폭을 입력하세요: ")
    val width = Scanner(System.`in`).nextInt()
    print("사각형의 높이를 입력하세요: ")
    val height = Scanner(System.`in`).nextInt()


    val rectangle2: Rectangle = Rectangle(width, height)


    val area = rectangle2.calcArea()

    println("폭이 ${rectangle2.width}이고 높이가 ${rectangle2.height}인 사각형의 넓이는 ${area}입니다")
}