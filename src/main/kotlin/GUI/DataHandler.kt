package GUI

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

    fun updateDataBy(codec: EncodingType) {
        // TODO() update with codec as main
    }
}