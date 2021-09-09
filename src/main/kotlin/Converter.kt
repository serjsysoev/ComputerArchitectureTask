import kotlin.jvm.Throws
import kotlin.math.abs

class DecimalConverter(val binaryStringLength: Int) {
    @Throws(IllegalStateException::class)
    fun toUnsigned(value: Int) = toUnsigned(value, binaryStringLength)

    @Throws(IllegalStateException::class)
    fun toSignedWithSignBit(value: Int): String {
        val signBit = if (value < 0) "1" else "0"
        val result = signBit + toUnsigned(abs(value), binaryStringLength - 1)
        check(result.length == binaryStringLength)

        return result
    }

    @Throws(IllegalStateException::class)
    fun toSignedWithShift128(value: Int): String = toUnsigned(value + 128)

    @Throws(IllegalStateException::class)
    fun toSignedWithShift127(value: Int): String = toUnsigned(value + 127)

    @Throws(IllegalStateException::class)
    fun toSignedTwosComplement(value: Int): String =
        Integer.toBinaryString(value).padStart(binaryStringLength, '0').takeLast(binaryStringLength)

    @Throws(IllegalStateException::class)
    fun toSignedOnesComplement(value: Int): String = toSignedTwosComplement(value + if (value < 0) -1 else 0)

    @Throws(IllegalStateException::class)
    fun toSignedAlternating(value: Int): String {
        val signBit = if (value < 0) "1" else "0"
        val result = toUnsigned(abs(value) + if (value < 0) -1 else 0, binaryStringLength - 1) + signBit
        check(result.length == binaryStringLength)

        return result
    }

    companion object {
        @Throws(IllegalStateException::class)
        private fun toUnsigned(value: Int, binaryStringLength: Int): String {
            check(value >= 0) { "Value needs to be positive" }
            check(binaryStringLength > 0) { "Can not fit result in this binary string length" }

            val result = Integer.toBinaryString(value).padStart(binaryStringLength, '0')
            check(result.length == binaryStringLength)

            return result
        }
    }
}