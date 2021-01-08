package tabshifter.valueobjects

abstract class LayoutElement {
    var position: Position = Position(0, 0, 0, 0)
    abstract val size: Size
}

fun LayoutElement?.traverse(): Sequence<LayoutElement> = sequence {
    val element = this@traverse
    if (element != null) {
        yield(element!!)
        if (element is Split) {
            yieldAll(element.first.traverse())
            yieldAll(element.second.traverse())
        }
    }
}

data class Position(val fromX: Int, val fromY: Int, val toX: Int, val toY: Int) {
    fun withFromX(value: Int) = Position(value, fromY, toX, toY)
    fun withFromY(value: Int) = Position(fromX, value, toX, toY)
    fun withToX(value: Int) = Position(fromX, fromY, value, toY)
    fun withToY(value: Int) = Position(fromX, fromY, toX, value)

    override fun toString() = "($fromX->$toX, $fromY->$toY)"
}

data class Size(val width: Int, val height: Int) {
    override fun toString() = "Size{width=$width, height=$height}"
}