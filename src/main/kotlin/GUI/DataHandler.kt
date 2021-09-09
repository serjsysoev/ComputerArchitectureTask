package GUI

import DecimalConverter
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.lang.Exception

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

    val isBaseBinary = mutableStateOf(true)

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

    private fun updateVariable(updater: () -> String) = try {
        "0b${updater()}";
    } catch (e: Exception) {
        e.toString();
    }

    fun String.dropPrefix() = if (this.length >= 2 && !this[1].isDigit()) this.drop(2) else this

    fun updateDataBy(codec: EncodingType) {
        val decimalUpdateNumber = when (codec) {
            EncodingType.Decimal -> decimal_State.value.toIntOrNull()
            EncodingType.NoSign -> decimalConverter.fromUnsigned(noSign_State.value.dropPrefix())
            EncodingType.BitForSign -> decimalConverter.fromSignedWithSignBit(bitForSign_State.value.dropPrefix())
            EncodingType.Skip128 -> decimalConverter.fromSignedWithShift128(skip128_State.value.dropPrefix())
            EncodingType.Skip127 -> decimalConverter.fromSignedWithShift127(skip127_State.value.dropPrefix())
            EncodingType.SupplementTo2 -> decimalConverter.fromSignedTwosComplement(supplementTo2_State.value.dropPrefix())
            EncodingType.SupplementTo1 -> decimalConverter.fromSignedOnesComplement(supplementTo1_State.value.dropPrefix())
            EncodingType.Alternation -> decimalConverter.fromSignedAlternating(alternation_State.value.dropPrefix())
            EncodingType.BaseMinus2 -> decimalConverter.fromSignedBaseNegativeTwo(baseMinus2_State.value.dropPrefix())
            EncodingType.Symmetric -> decimalConverter.fromSignedSymmetrical(symmetric_State.value.dropPrefix(), 3)
        } ?: return
        if (codec != EncodingType.Decimal) {
            decimal_State.value = decimalUpdateNumber.toString()
        }
        if (codec != EncodingType.NoSign) {
            noSign_State.value = updateVariable { decimalConverter.toUnsigned(decimalUpdateNumber) }
        }
        if (codec != EncodingType.BitForSign) {
            bitForSign_State.value = updateVariable { decimalConverter.toSignedWithSignBit(decimalUpdateNumber) }
        }
        if (codec != EncodingType.Skip128) {
            skip128_State.value = updateVariable { decimalConverter.toSignedWithShift128(decimalUpdateNumber) }
        }
        if (codec != EncodingType.Skip127) {
            skip127_State.value = updateVariable { decimalConverter.toSignedWithShift127(decimalUpdateNumber) }
        }
        if (codec != EncodingType.SupplementTo2) {
            supplementTo2_State.value = updateVariable { decimalConverter.toSignedTwosComplement(decimalUpdateNumber) }
        }
        if (codec != EncodingType.SupplementTo1) {
            supplementTo1_State.value = updateVariable { decimalConverter.toSignedOnesComplement(decimalUpdateNumber) }
        }
        if (codec != EncodingType.Alternation) {
            alternation_State.value = updateVariable { decimalConverter.toSignedAlternating(decimalUpdateNumber) }
        }
        if (codec != EncodingType.BaseMinus2) {
            baseMinus2_State.value = updateVariable { decimalConverter.toSignedBaseNegativeTwo(decimalUpdateNumber) }
        }
        if (codec != EncodingType.Symmetric) {
            symmetric_State.value = updateVariable { decimalConverter.toSignedSymmetrical(decimalUpdateNumber, 3) }
        }
    }

    fun clearCells() {
        decimal_State.value = "";
        noSign_State.value = "";
        bitForSign_State.value = "";
        skip128_State.value = "";
        skip127_State.value = "";
        supplementTo2_State.value = "";
        supplementTo1_State.value = "";
        alternation_State.value = "";
        baseMinus2_State.value = "";
        symmetric_State.value = "";
    }
}