import kotlin.jvm.Throws
import kotlin.math.*

class DecimalConverter(val binaryStringLength: Int) {
    @Throws(IllegalArgumentException::class)
    fun toUnsigned(value: Int) = toUnsigned(value, binaryStringLength)

    @Throws(IllegalArgumentException::class)
    fun fromUnsigned(value: String): Int {
        val result = value.toIntOrNull(2) ?: throw IllegalArgumentException()
        require(result >= 0)

        return result
    }

    @Throws(IllegalArgumentException::class)
    fun toSignedWithSignBit(value: Int): String {
        val signBit = if (value < 0) "1" else "0"
        val result = signBit + toUnsigned(abs(value), binaryStringLength - 1)
        require(result.length == binaryStringLength)

        return result
    }

    @Throws(IllegalArgumentException::class)
    fun fromSignedWithSignBit(value: String): Int {
        require(value.length >= 2)
        return fromUnsigned(value.drop(1)) * if (value[0] == '0') 1 else -1
    }

    @Throws(IllegalArgumentException::class)
    fun toSignedWithShift128(value: Int): String = toUnsigned(value + 128)

    @Throws(IllegalArgumentException::class)
    fun fromSignedWithShift128(value: String) = fromUnsigned(value) - 128

    @Throws(IllegalArgumentException::class)
    fun toSignedWithShift127(value: Int): String = toUnsigned(value + 127)

    @Throws(IllegalArgumentException::class)
    fun fromSignedWithShift127(value: String) = fromUnsigned(value) - 127

    @Throws(IllegalArgumentException::class)
    fun toSignedTwosComplement(value: Int): String =
        Integer.toBinaryString(value).padStart(binaryStringLength, '0').takeLast(binaryStringLength)

    @Throws(IllegalArgumentException::class)
    fun fromSignedTwosComplement(value: String) = if (value.first() == '1') {
        value.padStart(32, '1')
    } else {
        value
    }.toLongOrNull(2)?.toInt() ?: throw IllegalArgumentException()

    @Throws(IllegalArgumentException::class)
    fun toSignedOnesComplement(value: Int): String = toSignedTwosComplement(value + if (value < 0) -1 else 0)

    @Throws(IllegalArgumentException::class)
    fun fromSignedOnesComplement(value: String): Int {
        val result = fromSignedTwosComplement(value)
        return if (result >= 0) result else result + 1
    }

    @Throws(IllegalArgumentException::class)
    fun toSignedAlternating(value: Int): String {
        val signBit = if (value < 0) "1" else "0"
        val result = toUnsigned(abs(value) + if (value < 0) -1 else 0, binaryStringLength - 1) + signBit
        require(result.length == binaryStringLength)

        return result
    }

    @Throws(IllegalArgumentException::class)
    fun fromSignedAlternating(value: String): Int {
        require(value.length >= 2)
        val result = fromUnsigned(value.dropLast(1))
        val signBit = value.last() == '1'
        return if (signBit) -result - 1 else result
    }

    @Throws(IllegalArgumentException::class)
    fun toSignedBaseNegativeTwo(value: Int): String {
        for (mask in 0 until (1 shl binaryStringLength)) {
            var result = 0
            for (i in 0 until binaryStringLength) {
                result += (mask and (1 shl i)) * if (i % 2 == 0) 1 else -1
            }
            if (result == value) {
                return toUnsigned(mask)
            }
        }
        throw IllegalArgumentException("Couldn't convert $value")
    }

    @Throws(IllegalArgumentException::class)
    fun fromSignedBaseNegativeTwo(value: String): Int =
        value.reversed().mapIndexed { index, c -> (if (c == '1') 1 else 0) * (-2).pow(index) }.sum()

    @Throws(IllegalArgumentException::class)
    fun toSignedSymmetrical(value: Int, base: Int): String {
        fun forEachNumberInBase(
            base: Int,
            iteration: Int = 0,
            number: MutableList<Int> = mutableListOf(),
            callable: (MutableList<Int>) -> Boolean
        ): MutableList<Int>? {
            if (iteration == binaryStringLength) {
                return if (callable(number)) number else null
            }

            val range = base / 2
            for (i in -range..range) {
                number.add(i)
                val result = forEachNumberInBase(base, iteration + 1, number, callable)
                if (result != null) return result
                number.removeLast()
            }

            return null
        }

        require(base % 2 == 1)

        val result = forEachNumberInBase(base) { numberList ->
            val number = numberList.reduceIndexed { index, acc, i -> acc + base.pow(index) * i }
            value == number
        }

        require(result != null)

        return result.reversed().map { if (it >= 0) it.toString() else 'z' + it + 1 }.joinToString(separator = "")
    }

    @Throws(IllegalArgumentException::class)
    fun fromSignedSymmetrical(value: String, base: Int) = value.reversed().mapIndexed { index, c ->
        if (c.isDigit()) {
            (c - '0') * base.pow(index)
        } else {
            (c.code - 'z'.code - 1) * base.pow(index)
        }
    }.sum()

    /**
     * @param n positive integer
     */
    fun Int.pow(n: Int): Int {
        require(n >= 0)

        var result = 1
        repeat(n) {
            result *= this
        }
        return result
    }

    companion object {
        @Throws(IllegalArgumentException::class)
        private fun toUnsigned(value: Int, binaryStringLength: Int): String {
            require(value >= 0) { "Value needs to be positive" }
            require(binaryStringLength > 0) { "Can not fit result in this binary string length" }

            val result = Integer.toBinaryString(value).padStart(binaryStringLength, '0')
            require(result.length == binaryStringLength)

            return result
        }
    }
}