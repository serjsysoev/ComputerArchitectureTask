package GUI

import DecimalConverter
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

enum class EncodingType {
    Decimal,
    NoSign,
    BitForSign,
    Skip128,
    Skip127,
    SupplementTo2,
    SupplementTo1,
    Alternation,
    BaseMinus2,
    Symmetric
}

class DataHandler {
    /**
    Десятичный
    Без знака
    Бит под знак
    Со сдвигом 128
    Со сдвигом 127
    С дополнением до 2
    С дополнением до 1
    С чередованием
    Основание -2
    Симметричная
     */
    val decimalConverter = DecimalConverter(8)

    val decimal_State: MutableState<String> = mutableStateOf("")
    val noSign_State: MutableState<String> = mutableStateOf("")
    val bitForSign_State: MutableState<String> = mutableStateOf("")
    val skip128_State: MutableState<String> = mutableStateOf("")
    val skip127_State: MutableState<String> = mutableStateOf("")
    val supplementTo2_State: MutableState<String> = mutableStateOf("")
    val supplementTo1_State: MutableState<String> = mutableStateOf("")
    val alternation_State: MutableState<String> = mutableStateOf("")
    val baseMinus2_State: MutableState<String> = mutableStateOf("")
    val symmetric_State: MutableState<String> = mutableStateOf("")

    fun

    fun updateDataBy(codec: EncodingType) {
        val decimalUpdateNumber = when(codec) {
            EncodingType.Decimal -> decimal_State.value.toIntOrNull()
            EncodingType.NoSign -> TODO()
            EncodingType.BitForSign -> TODO()
            EncodingType.Skip128 -> TODO()
            EncodingType.Skip127 -> TODO()
            EncodingType.SupplementTo2 -> TODO()
            EncodingType.SupplementTo1 -> TODO()
            EncodingType.Alternation -> TODO()
            EncodingType.BaseMinus2 -> TODO()
            EncodingType.Symmetric -> TODO()
        } ?: return
        decimal_State.value = decimalUpdateNumber.toString()
        noSign_State.value = "0b${decimalConverter.toUnsigned(decimalUpdateNumber)}"
        noSign_State.value = "0b${decimalConverter.toUnsigned(decimalUpdateNumber)}"
        bitForSign_State.value = "0b${decimalConverter.toSignedWithSignBit(decimalUpdateNumber)}"
        skip128_State.value = "0b${decimalConverter.toSignedWithShift128(decimalUpdateNumber)}"
        skip127_State.value = "0b${decimalConverter.toSignedWithShift127(decimalUpdateNumber)}"
        supplementTo2_State.value = "0b${decimalConverter.toSignedTwosComplement(decimalUpdateNumber)}"
        supplementTo1_State.value = "0b${decimalConverter.toSignedOnesComplement(decimalUpdateNumber)}"
        alternation_State.value = "0b${decimalConverter.toSignedAlternating(decimalUpdateNumber)}"
        baseMinus2_State.value = "0b${decimalConverter.toUnsigned(decimalUpdateNumber)}"
        symmetric_State.value = "0b${decimalConverter.toUnsigned(decimalUpdateNumber)}"
    }
}