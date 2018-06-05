fun sum01(a: Int, b: Int): Int {
    return a + b
}

fun sum02(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun localVariables() {
    val a: Int = 1  // 立即赋值
    val b = 2   // 自动推断出 `Int` 类型
    val c: Int  // 如果没有初始值类型不能省略
    c = 3       // 明确赋值
    println("a = $a, b = $b, c = $c")
}

fun volatileVariables() {
    var x = 5 // 自动推断出 `Int` 类型
    x += 1
    println("x = $x")
}

fun stringTemplate() {
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}

fun conditionalExpressions(): Unit {
    fun maxOf(a: Int, b: Int) = if (a > b) a else b
    println("max of 0 and 42 is ${maxOf(0, 42)}")
}

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

//sampleStart
    // ……
    if (x == null) {
        println("Wrong number format in arg1: '$arg1'")
        return
    }
    if (y == null) {
        println("Wrong number format in arg2: '$arg2'")
        return
    }

    // 在空检测后，x 和 y 会自动转换为非空值
    println(x * y)
//sampleEnd
}

//sampleStart
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }

    if (obj !is String) return null

    // `obj` 在 `&&` 右边自动转换成 `String` 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}
//sampleEnd

fun loopExample() {
//sampleStart
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
//sampleEnd

    //sampleStart
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
    //sampleEnd

    //sampleStart
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
//sampleEnd
}

//sampleStart
fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }
//sampleEnd

fun main(args: Array<String>) {
    println("sum of 19 and 23 is ${sum01(19, 23)}")
    println("sum of 1 and 2 is ${sum02(1, 2)}")
    printSum(-1, 8)
    //条件变量
    localVariables()
    //可变变量
    volatileVariables()
    //字符串模板
    stringTemplate()
    //条件表达式
    conditionalExpressions()
    //使用可空值及 null 检测
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("99", "b")

    //使用类型检测及自动类型转换
    fun printLength(obj: Any) {
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
    }
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))

    //使用 for 循环
    loopExample()

//    使用 when 表达式
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))
}