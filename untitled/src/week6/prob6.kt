package week6

open class Shape(protected val x : Int, protected val y: Int) {}

class Rectangle(x:Int, y:Int, private val width:Int, private val height:Int) : Shape(x,y) {

    fun displayRectangle(){
        println("[Rectangle] x=$x y=$y width=$width height=$height")
    }
}
class Circle(x:Int, y:Int, private val radius : Int) : Shape(x,y) {

    fun displayCircle(){
        println("[Circle] x=$x y=$y radius=$radius")
    }
}

fun main() {
    val r1 = Rectangle(1,2,3,4);
    val r2 = Rectangle(2,3,4,5)
    val r3 = Rectangle(3,4,5,6)
    val c1 = Circle(3,4,5)
    val c2 = Circle(4,5,6)
    val c3 = Circle(5,6,7)

    val shapes:Array<Shape> = arrayOf(r1,r2,r3,c1,c2,c3)
    for (s in shapes) {
        if (s is Rectangle)
            s.displayRectangle()
        else if (s is Circle)
            s.displayCircle()
    }

}