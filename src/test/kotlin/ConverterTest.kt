import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ConverterTest {
    @Test
    fun decimalToUnsigned() {
        val converter = DecimalConverter(4)
        assertEquals("0000", converter.toUnsigned(0))
        assertEquals("0001", converter.toUnsigned(1))
        assertEquals("0010", converter.toUnsigned(2))
        assertEquals("0011", converter.toUnsigned(3))
        assertEquals("0100", converter.toUnsigned(4))
        assertEquals("0101", converter.toUnsigned(5))
    }

    @Test
    fun decimalToSignedWithSignBit() {
        val converter = DecimalConverter(4)
        assertEquals("0000", converter.toSignedWithSignBit(0))
        assertEquals("0001", converter.toSignedWithSignBit(1))
        assertEquals("0010", converter.toSignedWithSignBit(2))
        assertEquals("0011", converter.toSignedWithSignBit(3))
        assertEquals("0100", converter.toSignedWithSignBit(4))
        assertEquals("0101", converter.toSignedWithSignBit(5))

        assertEquals("1001", converter.toSignedWithSignBit(-1))
        assertEquals("1010", converter.toSignedWithSignBit(-2))
        assertEquals("1011", converter.toSignedWithSignBit(-3))
        assertEquals("1100", converter.toSignedWithSignBit(-4))
        assertEquals("1101", converter.toSignedWithSignBit(-5))

        assertThrows<IllegalStateException> { converter.toSignedWithSignBit(8) }
    }

    @Test
    fun decimalToSignedWithShift128() {
        val converter = DecimalConverter(8)
        assertEquals("10000000", converter.toSignedWithShift128(0))
        assertEquals("10000001", converter.toSignedWithShift128(1))
        assertEquals("10000010", converter.toSignedWithShift128(2))
        assertEquals("10000011", converter.toSignedWithShift128(3))
        assertEquals("10000100", converter.toSignedWithShift128(4))
        assertEquals("10000101", converter.toSignedWithShift128(5))

        assertEquals("01111111", converter.toSignedWithShift128(-1))
        assertEquals("01111110", converter.toSignedWithShift128(-2))
        assertEquals("01111101", converter.toSignedWithShift128(-3))
        assertEquals("01111100", converter.toSignedWithShift128(-4))
        assertEquals("01111011", converter.toSignedWithShift128(-5))
    }

    @Test
    fun decimalToSignedWithShift127() {
        val converter = DecimalConverter(8)
        assertEquals("01111111", converter.toSignedWithShift127(0))
        assertEquals("10000000", converter.toSignedWithShift127(1))
        assertEquals("10000001", converter.toSignedWithShift127(2))
        assertEquals("10000010", converter.toSignedWithShift127(3))
        assertEquals("10000011", converter.toSignedWithShift127(4))
        assertEquals("10000100", converter.toSignedWithShift127(5))

        assertEquals("01111110", converter.toSignedWithShift127(-1))
        assertEquals("01111101", converter.toSignedWithShift127(-2))
        assertEquals("01111100", converter.toSignedWithShift127(-3))
        assertEquals("01111011", converter.toSignedWithShift127(-4))
        assertEquals("01111010", converter.toSignedWithShift127(-5))
    }

    @Test
    fun decimalToSignedTwosComplement() {
        val converter = DecimalConverter(8)
        assertEquals("00000000", converter.toSignedTwosComplement(0))
        assertEquals("00000001", converter.toSignedTwosComplement(1))
        assertEquals("00000010", converter.toSignedTwosComplement(2))
        assertEquals("00000011", converter.toSignedTwosComplement(3))
        assertEquals("00000100", converter.toSignedTwosComplement(4))
        assertEquals("00000101", converter.toSignedTwosComplement(5))

        assertEquals("11111111", converter.toSignedTwosComplement(-1))
        assertEquals("11111110", converter.toSignedTwosComplement(-2))
        assertEquals("11111101", converter.toSignedTwosComplement(-3))
        assertEquals("11111100", converter.toSignedTwosComplement(-4))
        assertEquals("11111011", converter.toSignedTwosComplement(-5))
    }

    @Test
    fun decimalToSignedOnesComplement() {
        val converter = DecimalConverter(8)
        assertEquals("00000000", converter.toSignedOnesComplement(0))
        assertEquals("00000001", converter.toSignedOnesComplement(1))
        assertEquals("00000010", converter.toSignedOnesComplement(2))
        assertEquals("00000011", converter.toSignedOnesComplement(3))
        assertEquals("00000100", converter.toSignedOnesComplement(4))
        assertEquals("00000101", converter.toSignedOnesComplement(5))

        assertEquals("11111110", converter.toSignedOnesComplement(-1))
        assertEquals("11111101", converter.toSignedOnesComplement(-2))
        assertEquals("11111100", converter.toSignedOnesComplement(-3))
        assertEquals("11111011", converter.toSignedOnesComplement(-4))
        assertEquals("11111010", converter.toSignedOnesComplement(-5))
    }

    @Test
    fun decimalToSignedAlternating() {
        val converter = DecimalConverter(8)
        assertEquals("00000000", converter.toSignedAlternating(0))
        assertEquals("00000010", converter.toSignedAlternating(1))
        assertEquals("00000100", converter.toSignedAlternating(2))
        assertEquals("00000110", converter.toSignedAlternating(3))
        assertEquals("00001000", converter.toSignedAlternating(4))
        assertEquals("00001010", converter.toSignedAlternating(5))

        assertEquals("00000001", converter.toSignedAlternating(-1))
        assertEquals("00000011", converter.toSignedAlternating(-2))
        assertEquals("00000101", converter.toSignedAlternating(-3))
        assertEquals("00000111", converter.toSignedAlternating(-4))
        assertEquals("00001001", converter.toSignedAlternating(-5))
    }
}