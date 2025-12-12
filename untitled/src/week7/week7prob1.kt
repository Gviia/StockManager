package week7

interface Shape{
    val x : Int
    val y : Int
    fun display()
}

class Rectangle(override val x:Int, override val y:Int, val width:Int, val height:Int)
    :Shape{

    override fun display() {
        println("[Rectangle] x=$x y=$y width=$width height=$height")
    }
}

class Circle(override val x:Int, override val y:Int, val radius:Int)
    :Shape{

    override fun display() {
        println("[Circle] x=$x y=$y radius=$radius")
    }
}

fun main() {
    val r1 = Rectangle(1, 2, 3, 4)
    val r2 = Rectangle(2, 3, 4, 5)
    val r3 = Rectangle(1, 2, 3, 4)

    val c1 = Circle(3, 4, 5)
    val c2 = Circle(4, 5, 6)
    val c3 = Circle(4, 5, 6)

    val shapes: Array<Shape> = arrayOf(r1, r2, r3, c1, c2, c3)
    for (s in shapes) {
        s.display()
    }
}