fun main() {
    val limit_day: Int = 150_000
    val limit_month: Int = 600_000
    val curent_amount: Int = 76000
    val prev_amounts_in_day: Int = 150_000
    val prev_amounts_in_mounth: Int = 600_000
    val cards_type: String = "Mastercard" //"Visa" "Мир"


    if ((curent_amount + prev_amounts_in_day) <= limit_day && (curent_amount + prev_amounts_in_mounth) <= limit_month) {
        val realTax = getTax(cards_type, prev_amounts_in_mounth, curent_amount)
        if (realTax >= 0) {
            println("Комиссия составит: $realTax")
        } else {
            println("Ошибка данных. Операция отклонена!")
        }
    } else {
        println("Превышены лимиты. Операция отклонена!")
    }


}

fun get_tax_mastercard(prev_amounts_in_mounth: Int = 0, curent_amount: Int): Int {
    val exemption = 75_000
    return if (curent_amount + prev_amounts_in_mounth <= exemption) {
        0
    } else ((curent_amount + prev_amounts_in_mounth - exemption) * 0.006 + 20).toInt()
}

fun get_tax_visa(curent_amount: Int): Int {
    val tax: Float = 0.0075F
    val minTax: Int = 35
    return if ((curent_amount * tax) > 35) {
        curent_amount * tax
    } else {
        minTax
    }.toInt()
}

fun get_tax_mir(): Int {
    return 0
}

fun getTax(
    cards_type: String = "Мир",
    prev_amounts_in_mounth: Int = 0,
    curent_amount: Int
) = when (cards_type) {
    "Mastercard" -> get_tax_mastercard(prev_amounts_in_mounth, curent_amount)
    "Visa" -> get_tax_visa(curent_amount)
    "Мир" -> get_tax_mir()
    else -> {
        -1
    }
}







