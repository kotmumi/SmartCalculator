//package calculator

import java.math.BigInteger

const val EXIT = "/exit"
const val HELP = "/help"
const val BYE = "Bye!"

val regexVariableAssign = "\\s*[a-zA-Z]+\\s*=\\s*[+-]*[()a-zA-Z\\d]+\\s*([+/*-]+\\s[+-]*[()a-zA-Z\\d])*".toRegex()
val regexVariableAssignSecond = "([a-zA-Z]+|[+-]*\\d+)\\s*(\\s+[+/*-]+\\s+([a-zA-Z]+|[+-]*\\d+))*".toRegex()
val regexSearchVariable = "\\s*[a-zA-Z]+\\s*".toRegex()
val regexCalculation = "([()\\sa-zA-Z]+|[+-]*[()\\s\\d]+)(\\s+([+-]+||[/*])\\s+([()\\sa-zA-Z]+|[()\\s\\d]+))*".toRegex()
//val regexSearchDigit = "[+-]*\\d+".toRegex()
val regexSplit = "\\s*=\\s*".toRegex()
val regexCommand = "/.*".toRegex()
val variable = mutableMapOf<String, BigInteger?>()

fun main() {
    inputNumber()
}

fun inputNumber () {

    while (true) {
        val input = readLine()!!.replace("(","( ").replace(")"," )")
        when {
            input.matches(regexVariableAssign)-> {
                try {
                    variableCount(input)
                } catch (e: NullPointerException) {
                    println("Invalid identifier")
                }
                continue
            }
            input.matches(regexSearchVariable)  -> {
                if (variable.containsKey(input.replace(" ",""))) println(variable[input.replace(" ","")]) else println("Unknown variable")
                continue
            }
            input.matches(regexCalculation) -> {
                try {
                    println(simpleCount(input))
                } catch (e: java.lang.ArithmeticException) {
                    println("Invalid expression")
                    continue
                }
                continue
            }
            input == EXIT -> {
                print(BYE)
                return
            }
            input == HELP -> {
                hint()
                continue
            }
            input.matches(regexCommand) -> {
                println("Unknown command")
                continue
            }
            input.matches("\\s*".toRegex()) -> {
                continue
            }
            else -> {
                println("Invalid expression")
                continue
            }
        }
    }
}

fun variableCount (input: String): String {
    val inputList = input.split(regexSplit).toMutableList()
    inputList[0] = inputList.first().replace(" ","")
    if (inputList.last().matches(regexVariableAssignSecond)) {
        variable[inputList.first()] = try {
            simpleCount(inputList.last())
        }catch (e: java.lang.Exception) {
            null
        } as BigInteger
    } else println("Invalid assignment")
    //println(variable[inputList.first()])
    return variable[inputList.first()].toString()
}

fun simpleCount(input: String): BigInteger {
    val list = input.split(' ').toMutableList()
    if (list.size > 2) {
        for (i in 1..list.lastIndex step 2) {
            var count = 0
            var countSecond = 0
            for (x in list[i]) {
                if (x == '-') count++
            }
            for (x in list[i]) {
                if (x == '+') countSecond++
            }
            if (count % 2 == 0 && count != 0) {
                list.removeAt(i)
                list.add(i, "+")
            } else if(count % 2 != 0 && count != 0) {
                list.removeAt(i)
                list.add(i, "-")
            }
            if (countSecond != 0) {
                list.removeAt(i)
                list.add(i,"+")
            }
        }
    }
    val postFixEx = GetPostFixExpression(list).getPostFixEx()
    return   calcPostFis(postFixEx)
}

//fun calculation (list: MutableList<String>): Int {
//    var sum = 0
//    when {
//        list.first().matches(regexSearchDigit) -> sum = list.first().toInt()
//        list.first().matches(regexSearchVariable) -> if(variable.containsKey(list.first())) sum = variable[list.first()]!! else println("Invalid identifier")
//    }
//    for (i in 1..list.lastIndex step 2) {
//        when {
//            list[i] == "+" -> sum += checkVariable(list[i+1])
//            list[i] == "-" -> sum -= checkVariable(list[i+1])
//        }
//    }
//    return sum
//}
//
//
//fun checkVariable (string: String) :BigInteger {
//    when {
//        string.matches(regexSearchVariable) -> if (variable.containsKey(string)) return variable[string]!!.toBigInteger() else println("Unknown variable")
//
//        string.matches(regexSearchDigit) -> return string.toBigInteger()
//    }
//    return BigInteger.ZERO
//}


fun calcPostFis(expression: MutableList<String>): BigInteger {
    val stack = mutableListOf<BigInteger>()
    if (expression.isNotEmpty()) {
        for (item in expression) {
            when {
                Regex("[+-]*\\d+").containsMatchIn(item) -> stack.add(item.toBigInteger())

                Regex("[a-zA-Z]+").containsMatchIn(item) -> {
                    if (variable.containsKey(item)) stack.add(variable[item]!!) else println("Unknown variable")
                }//stack.add(checkVariable(item))

                item == "+" -> {
                    stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] + stack.last()
                    stack.removeAt(stack.lastIndex)
                }

                item == "*" -> {
                    stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] * stack.last()
                    stack.removeAt(stack.lastIndex)
                }

                item == "/" -> {
                    stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] / stack.last()
                    stack.removeAt(stack.lastIndex)
                }

                item == "-" -> {
                    stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] - stack.last()
                    stack.removeAt(stack.lastIndex)
                }
            }

        }
        return stack.first()
    } else return BigInteger.ZERO/ BigInteger.ZERO
}

class GetPostFixExpression(private val expression: MutableList<String>) {
    var countOpenBracket = 0
    var countCloseBracket = 0
    private var stack =mutableListOf<String>()
    private var queue = mutableListOf<String>()
    fun getPostFixEx(): MutableList<String> {

        expression.forEach {
            when {
                //if input expression "(" make PUSH
                it =="(" -> {
                    push(it)
                    countOpenBracket++
                }
                //if input expression ")" make POP
                it == ")" -> {
                    if(expression.contains("(")) pop()
                    countCloseBracket++
                }

                //if input expression digit, add to queue
                Regex("[+-]*\\d+").containsMatchIn(it) -> addQueue(it)
                Regex("[a-zA-Z]+").containsMatchIn(it) -> {
                    if(variable.containsKey(it)) addQueue(variable[it]!!.toString()) else {
                        println("Unknown variable")
                        return queue
                    }
                }
                //if input expression + or -
                Regex("[+-]").containsMatchIn(it) -> {
                    // check if stack empty or top stack "(" , make PUSH
                    if (stack.isEmpty() || stack.last() == "(") {
                        push(it)
                        // if top stack operator with high priority make POP then PUSH
                    } else if (stack.last().contains(Regex("[/*]"))) {
                        pop()
                        push(it)
                        //other make PUSH
                    } else {
                        addQueue(stack.last())
                        stack[stack.lastIndex] = it
                    }
                }

                //if input expression * or /
                Regex("[*/]").containsMatchIn(it) -> {
                    // check if in top stack expression equal priority to make POP
                    if(stack.isNotEmpty() && (stack.last() == "*" || stack.last() == "/")) pop()
                    push(it)
                }
            }
        }
        if (stack.isNotEmpty()) {
            for (i in stack.lastIndex downTo 0) {
                if (stack[i] != "(") {
                    addQueue(stack[i])
                }
            }
        }
        //println("Postfix expression: ${queue.joinToString(" ")}")
        if (countCloseBracket != countOpenBracket) queue.clear()
        return queue
    }

    private fun pop() {
        Loop@ for(i in stack.lastIndex downTo 0) {
            if (stack[i] == "(") {
                stack[i] = " "
                break@Loop
            }
            addQueue(stack[i])
            stack[i] = " "
        }
        stack.removeIf { it == " "}
    }

    private fun addQueue(item: String) {
        queue.add(item)
    }

    private fun push(item: String) {
        stack.add(item)
    }
}
fun hint () {
    println("- Enter data in the form :" +
            "\t*[number||variable _+ || - || / || * || () _ number||variable ...]" +
            " without [ ] where _ is a space \n" +
            "- To exit the calculator, type /exit")
}
