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
        toChosenBase(updater())
    } catch (e: Exception) {
        e.toString();
    }

    private fun toChosenBase(str: String) =
        if (isBaseBinary.value) {
            str
        } else {
            val decimal: Int = str.toInt(2)
            decimal.toString(16)
        }


    private fun String.toBinary() =
        if (isBaseBinary.value) {
            if (this.length < 8) {
                ""
            } else {
                this
            }
        } else {
            if (this.length < 2) {
                ""
            } else {
                val decimal: Int = this.toInt(16)
                decimal.toString(2)
            }
        }

    private fun checkForExit(str: String) = str.toBinary().isNotEmpty()

    fun updateDataBy(codec: EncodingType) {
        val decimalUpdateNumber = when (codec) {
            EncodingType.Decimal -> decimal_State.value.toIntOrNull()
            EncodingType.NoSign -> if (checkForExit(noSign_State.value)) decimalConverter.fromUnsigned(noSign_State.value.toBinary()) else null
            EncodingType.BitForSign -> if (checkForExit(bitForSign_State.value)) decimalConverter.fromSignedWithSignBit(bitForSign_State.value.toBinary()) else null
            EncodingType.Skip128 -> if (checkForExit(skip128_State.value)) decimalConverter.fromSignedWithShift128(skip128_State.value.toBinary()) else null
            EncodingType.Skip127 -> if (checkForExit(skip127_State.value)) decimalConverter.fromSignedWithShift127(skip127_State.value.toBinary()) else null
            EncodingType.SupplementTo2 -> if (checkForExit(supplementTo2_State.value)) decimalConverter.fromSignedTwosComplement(supplementTo2_State.value.toBinary()) else null
            EncodingType.SupplementTo1 -> if (checkForExit(supplementTo1_State.value)) decimalConverter.fromSignedOnesComplement(supplementTo1_State.value.toBinary()) else null
            EncodingType.Alternation -> if (checkForExit(alternation_State.value)) decimalConverter.fromSignedAlternating(alternation_State.value.toBinary()) else null
            EncodingType.BaseMinus2 -> if (checkForExit(baseMinus2_State.value)) decimalConverter.fromSignedBaseNegativeTwo(baseMinus2_State.value.toBinary()) else null
            EncodingType.Symmetric -> if (checkForExit(symmetric_State.value)) decimalConverter.fromSignedSymmetrical(symmetric_State.value.toBinary(), 3) else null
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