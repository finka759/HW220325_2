fun main() {
    val limitDay: Int = 150_000
    val limitMonth: Int = 600_000
    val curentAmount: Int = 76000
    val prevAmountsInDay: Int = 15_000
    val prevAmountsInMounth: Int = 60_000
    val cardsType: String = "Mastercard" //"Visa" "Мир"


    if ((curentAmount + prevAmountsInDay) <= limitDay && (curentAmount + prevAmountsInMounth) <= limitMonth) {
        val realTax = getTax(cardsType, prevAmountsInMounth, curentAmount)
        if (realTax >= 0) {
            println("Комиссия составит: $realTax")
        } else {
            println("Ошибка данных. Операция отклонена!")
        }
    } else {
        println("Превышены лимиты. Операция отклонена!")
    }


}

fun getTaxMastercard(prevAmountsInMounth: Int = 0, curentAmount: Int): Int {
    val exemption = 75_000
    return if (curentAmount + prevAmountsInMounth <= exemption) {
        0
    } else ((curentAmount + prevAmountsInMounth - exemption) * 0.006 + 20).toInt()
}

fun getTaxVisa(curentAmount: Int): Int {
    val tax: Float = 0.0075F
    val minTax: Int = 35
    return if ((curentAmount * tax) > 35) {
        curentAmount * tax
    } else {
        minTax
    }.toInt()
}

fun getTaxMir(): Int {
    return 0
}

fun getTax(
    cardsType: String = "Мир",
    prevAmountsInMounth: Int = 0,
    curentAmount: Int
) = when (cardsType) {
    "Mastercard" -> getTaxMastercard(prevAmountsInMounth, curentAmount)
    "Visa" -> getTaxVisa(curentAmount)
    "Мир" -> getTaxMir()
    else -> {
        -1
    }
}







