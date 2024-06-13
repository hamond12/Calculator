class Calculator {
    private fun opPriority(op: Char): Int {
        return when (op) {
            '+', '-' -> 1
            'x', '÷' -> 2
            else -> 0
        }
    }

    private fun infixToPostfix(str: String): String {
        val num = StringBuilder()
        val stack = mutableListOf<Char>()

        for (i in str) {
            when {
                i.isDigit() || i == '.' -> num.append("$i")
                i == '(' -> stack.add(i)
                i == ')' -> {
                    while (stack.isNotEmpty() && stack.last() != '(') {
                        num.append(" ${stack.removeAt(stack.lastIndex)}")
                    }
                    stack.removeAt(stack.lastIndex)
                }
                i in "+-x÷" -> {
                    while (stack.isNotEmpty() && opPriority(i) <= opPriority(stack.last())) {
                        num.append(" ${stack.removeAt(stack.lastIndex)}")
                    }
                    stack.add(i)
                    num.append(" ")
                }
            }
        }

        while (stack.isNotEmpty()) {
            num.append(" ${stack.removeAt(stack.lastIndex)}")
        }

        return num.toString().trim()
    }

    private fun calculatePostfix(postfixEx: String): Double {
        val stack = mutableListOf<Double>()

        for (i in postfixEx.split(" ")) {
            if (i.matches(Regex("-?\\d+(\\.\\d+)?"))) stack.add(i.toDouble())
            else {
                val op1 = stack.removeAt(stack.lastIndex)
                val op2 = stack.removeAt(stack.lastIndex)
                when (i) {
                    "+" -> stack.add(op2 + op1)
                    "-" -> stack.add(op2 - op1)
                    "x" -> stack.add(op2 * op1)
                    "÷" -> stack.add(op2 / op1)
                }
            }
        }

        return stack.first()
    }

    fun calculate(ex: String): Any {
        val postfix = infixToPostfix(ex)
        val res = calculatePostfix(postfix)
        return if (res % 1.0 == 0.0) res.toInt() else res
    }
}

fun main() {
    val calculator = Calculator()

    print("수식을 입력하세요: ")
    val input = readln()

    val result = calculator.calculate(input)
    println("계산 결과: $result")
}