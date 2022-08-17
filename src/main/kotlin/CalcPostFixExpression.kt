class CalcPostFixExpression (private val expression: MutableList<String>) {
    private val stack = mutableListOf<Int>()

    fun calcPostFis(): Int {
        for (item in expression) {
            when {
                Regex("\\d+").containsMatchIn(item) -> stack.add(item.toInt())

                Regex("[a-zA-Z]+\\s*").containsMatchIn(item) -> {

                }

                item == "+" -> {
                    stack[stack.lastIndex -1] = stack[stack.lastIndex - 1] + stack.last()
                    stack.removeAt(stack.lastIndex)
                }

                item == "*" -> {
                    stack[stack.lastIndex -1] = stack[stack.lastIndex - 1] * stack.last()
                    stack.removeAt(stack.lastIndex)
                }

                item == "/" -> {
                    stack[stack.lastIndex -1] = stack[stack.lastIndex - 1] / stack.last()
                    stack.removeAt(stack.lastIndex)
                }

                item == "-" -> {
                    stack[stack.lastIndex -1] = stack[stack.lastIndex - 1] - stack.last()
                    stack.removeAt(stack.lastIndex)
                }
            }
        }
        return stack.first()
    }
}