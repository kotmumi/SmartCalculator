//class GetPostFixExpression(private val expression: MutableList<String>) {
//    private var stack =mutableListOf<String>()
//    private var queue = mutableListOf<String>()

//    fun getPostFixEx(): MutableList<String> {
//        expression.forEach {
//            when {
//                //if input expression "(" make PUSH
//                it =="(" -> push(it)
//                //if input expression ")" make POP
//                it == ")" -> {
//                    if(expression.contains("(")) pop()
//                }
//
//                //if input expression digit, add to queue
//                Regex("\\d+").containsMatchIn(it) -> addQueue(it)
//
//                //if input expression + or -
//                Regex("[+-]").containsMatchIn(it) -> {
//                    // check if stack empty or top stack "(" , make PUSH
//                    if (stack.isEmpty() || stack.last() == "(") {
//                        push(it)
//                        // if top stack operator with high priority make POP then PUSH
//                    } else if (stack.last().contains(Regex("[/*]"))) {
//                        pop()
//                        push(it)
//                        //other make PUSH
//                    } else {
//                        addQueue(stack.last())
//                        stack[stack.lastIndex] = it
//                    }
//                }
//
//                //if input expression * or /
//                Regex("[*/]").containsMatchIn(it) -> {
//                    // check if in top stack expression equal priority to make POP
//                    if(stack.isNotEmpty() && (stack.last() == "*" || stack.last() == "/")) pop()
//                    push(it)
//                }
//            }
//        }
//        if (stack.isNotEmpty()) {
//            for (i in stack.lastIndex downTo 0) {
//                if (stack[i] != "(") {
//                    addQueue(stack[i])
//                }
//            }
//        }
//        println("Postfix expression: ${queue.joinToString(" ")}")
//        return  queue
//    }
//
//    private fun pop() {
//        Loop@ for(i in stack.lastIndex downTo 0) {
//            if (stack[i] == "(") {
//                stack[i] = " "
//                break@Loop
//            }
//            addQueue(stack[i])
//            stack[i] = " "
//        }
//        stack.removeIf { it == " "}
//    }
//
//    private fun addQueue(item: String) {
//        queue.add(item)
//    }
//
//    private fun push(item: String) {
//        stack.add(item)
//    }
//}