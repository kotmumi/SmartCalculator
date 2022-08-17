//package calculator
//
//const val EXIT = "/exit"
//const val HELP = "/help"
//const val BYE = "Bye!"


//val regexVariableAssign = "[a-zA-Z]+\\s*=\\s*[a-zA-Z\\d]+\\s*([+\\-]+\\s[a-zA-Z\\d])*".toRegex()
//val regexVariableAssignSecond = "([a-zA-Z]+|\\d+)\\s*(\\s+[+/*-]+\\s+([a-zA-Z]+|\\d+))*".toRegex()
//val regexSearchVariable = "[a-zA-Z]+\\s*".toRegex()
//val regexCalculation = "([a-zA-Z]+|\\d+)(\\s+[+\\-]+\\s+([a-zA-Z]+|\\d+))*".toRegex()
//val regexSearchDigit = "\\d+".toRegex()
//val regexSplit = "\\s*=\\s*".toRegex()
//val regexCommand = "/.*".toRegex()
//val variable = mutableMapOf<String, Int>()

fun main() {
}

//fun inputNumber () {
//
//    while (true) {
//        val input = readLine()!!
//        when {
//            input.matches(regexVariableAssign)-> {
//                variableCount(input)
//                continue
//            }
//            input.matches(regexSearchVariable)  -> {
//                if (variable.containsKey(input)) println(variable[input]) else println("Unknown variable")
//                continue
//            }
//            input.matches(regexCalculation) -> {
//                println(simpleCount(input))
//                continue
//            }
//            input == EXIT -> {
//                print(BYE)
//                return
//            }
//            input == HELP -> {
//                hint()
//                continue
//            }
//            input.matches(regexCommand) -> {
//                println("Unknown command")
//                continue
//            }
//            input.matches("\\s*".toRegex()) -> {
//                continue
//            }
//            else -> {
//                println("Invalid identifier")
//                continue
//            }
//        }
//    }
//}

//fun variableCount (input: String): MutableList <String>  {
//    val inputList = input.split(regexSplit).toMutableList()
//    if (inputList.last().matches(regexVariableAssignSecond)) {
//        variable[inputList.first()] = simpleCount(inputList.last())
//        //println(variable[inputList.first()])
//    } else println("Invalid assignment")
//    return inputList
//}


//fun simpleCount(input: String): Int {
//    val list = input.split(' ').toMutableList()
//    if (list.size > 2) {
//        for (i in 1..list.lastIndex step 2) {
//            var count = 0
//            for (x in list[i]) {
//                if (x == '-') count++
//            }
//            if (count % 2 == 0) {
//                list.removeAt(i)
//                list.add(i, "+")
//            } else {
//                list.removeAt(i)
//                list.add(i, "-")
//            }
//        }
//    }
//    return calculation(list)
//}
//fun calculation (list: MutableList<String>): Int {
//    var sum = 0
//    when {
//        list.first().matches(regexSearchDigit) -> sum = list.first().toInt()
//
//        list.first().matches(regexSearchVariable) -> if(variable.containsKey(list.first())) sum = variable[list.first()]!! else println("Invalid identifier")
//
//        //   else -> println("Invalid identifier")
//
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
//fun checkVariable (string: String) :Int {
//    when {
//        string.matches(regexSearchVariable) -> if (variable.containsKey(string)) return variable[string]!! else println("Unknown variable")
//
//        string.matches(regexSearchDigit) -> return string.toInt()
//    }
//    return 0
//}

//fun hint () {
//    println("- Enter data in the form [number _+ or - _ number ...] without [ ] where _ is a space \n" +
//            "- To exit the calculator, type /exit")
//}

