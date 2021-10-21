const val MASTER_CARD = "MasterCard"
const val MAESTRO = "Maestro"
const val VISA = "Visa"
const val MIR = "Mir"
const val VK_PAY = "VK Pay"

//var amountPreviousTransfers = 0
//var transferAmountNow = 76_000_000
fun main() {
    calculateComission(VISA, transferAmountNow = 7_600_000)
}

fun calculateComission(cardType: String, amountPreviousTransfers: Int = 0, transferAmountNow: Int) {
    val result = when (cardType) {
        MASTER_CARD -> comissionMasterCardAndMaestro(amountPreviousTransfers, transferAmountNow)
        MAESTRO -> comissionMasterCardAndMaestro(amountPreviousTransfers, transferAmountNow)
        VISA -> comissionVisaAndMir(transferAmountNow)
        MIR -> comissionVisaAndMir(transferAmountNow)
        else -> 0
    }
    return println("Комиссия составит $result копеек")
}

fun comissionMasterCardAndMaestro(amountPreviousTransfers: Int, transferAmountNow: Int): Int {
    val comission = when (amountPreviousTransfers) {
        in 0..75_000 -> 0
        else -> (procent(transferAmountNow, 0.6) + 2_000).toInt()
    }
    return comission
}

fun comissionVisaAndMir(transferAmountNow: Int): Int {
    val comission = when (procent(transferAmountNow, 0.75)) {
        in 0.0..3500.0 -> 3500
        else -> procent(transferAmountNow, 0.75).toInt()
    }
    return comission
}

fun procent(integer: Int, procent: Double): Double {
    val result = (integer * procent) / 100
    return result
}