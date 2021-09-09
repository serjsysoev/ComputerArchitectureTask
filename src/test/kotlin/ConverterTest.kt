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
    fun unsignedToDecimal() {
        val converter = DecimalConverter(4)
        assertEquals(0, converter.fromUnsigned("0000"))
        assertEquals(1, converter.fromUnsigned("0001"))
        assertEquals(2, converter.fromUnsigned("0010"))
        assertEquals(3, converter.fromUnsigned("0011"))
        assertEquals(4, converter.fromUnsigned("0100"))
        assertEquals(5, converter.fromUnsigned("0101"))
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

        assertThrows<IllegalArgumentException> { converter.toSignedWithSignBit(8) }
    }

    @Test
    fun signedWithSignBitToDecimal() {
        val converter = DecimalConverter(4)
        assertEquals(0, converter.fromSignedWithSignBit("0000"))
        assertEquals(1, converter.fromSignedWithSignBit("0001"))
        assertEquals(2, converter.fromSignedWithSignBit("0010"))
        assertEquals(3, converter.fromSignedWithSignBit("0011"))
        assertEquals(4, converter.fromSignedWithSignBit("0100"))
        assertEquals(5, converter.fromSignedWithSignBit("0101"))

        assertEquals(-1, converter.fromSignedWithSignBit("1001"))
        assertEquals(-2, converter.fromSignedWithSignBit("1010"))
        assertEquals(-3, converter.fromSignedWithSignBit("1011"))
        assertEquals(-4, converter.fromSignedWithSignBit("1100"))
        assertEquals(-5, converter.fromSignedWithSignBit("1101"))
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
    fun signedWithShift128ToDecimal() {
        val converter = DecimalConverter(8)
        assertEquals(0, converter.fromSignedWithShift128("10000000"))
        assertEquals(1, converter.fromSignedWithShift128("10000001"))
        assertEquals(2, converter.fromSignedWithShift128("10000010"))
        assertEquals(3, converter.fromSignedWithShift128("10000011"))
        assertEquals(4, converter.fromSignedWithShift128("10000100"))
        assertEquals(5, converter.fromSignedWithShift128("10000101"))

        assertEquals(-1, converter.fromSignedWithShift128("01111111"))
        assertEquals(-2, converter.fromSignedWithShift128("01111110"))
        assertEquals(-3, converter.fromSignedWithShift128("01111101"))
        assertEquals(-4, converter.fromSignedWithShift128("01111100"))
        assertEquals(-5, converter.fromSignedWithShift128("01111011"))
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
    fun signedWithShift127ToDecimal() {
        val converter = DecimalConverter(8)
        assertEquals(0, converter.fromSignedWithShift127("01111111"))
        assertEquals(1, converter.fromSignedWithShift127("10000000"))
        assertEquals(2, converter.fromSignedWithShift127("10000001"))
        assertEquals(3, converter.fromSignedWithShift127("10000010"))
        assertEquals(4, converter.fromSignedWithShift127("10000011"))
        assertEquals(5, converter.fromSignedWithShift127("10000100"))

        assertEquals(-1, converter.fromSignedWithShift127("01111110"))
        assertEquals(-2, converter.fromSignedWithShift127("01111101"))
        assertEquals(-3, converter.fromSignedWithShift127("01111100"))
        assertEquals(-4, converter.fromSignedWithShift127("01111011"))
        assertEquals(-5, converter.fromSignedWithShift127("01111010"))
    }

    @Test
    fun decimalToSignedTwosComplement() {
        val converter = DecimalConverter(4)
        assertEquals("0000", converter.toSignedTwosComplement(0))
        assertEquals("0001", converter.toSignedTwosComplement(1))
        assertEquals("0010", converter.toSignedTwosComplement(2))
        assertEquals("0011", converter.toSignedTwosComplement(3))
        assertEquals("0100", converter.toSignedTwosComplement(4))
        assertEquals("0101", converter.toSignedTwosComplement(5))

        assertEquals("1111", converter.toSignedTwosComplement(-1))
        assertEquals("1110", converter.toSignedTwosComplement(-2))
        assertEquals("1101", converter.toSignedTwosComplement(-3))
        assertEquals("1100", converter.toSignedTwosComplement(-4))
        assertEquals("1011", converter.toSignedTwosComplement(-5))
    }

    @Test
    fun signedTwosComplementToDecimal() {
        val converter = DecimalConverter(8)
        assertEquals(0, converter.fromSignedTwosComplement("0000"))
        assertEquals(1, converter.fromSignedTwosComplement("0001"))
        assertEquals(2, converter.fromSignedTwosComplement("0010"))
        assertEquals(3, converter.fromSignedTwosComplement("0011"))
        assertEquals(4, converter.fromSignedTwosComplement("0100"))
        assertEquals(5, converter.fromSignedTwosComplement("0101"))

        assertEquals(-1, converter.fromSignedTwosComplement("1111"))
        assertEquals(-2, converter.fromSignedTwosComplement("1110"))
        assertEquals(-3, converter.fromSignedTwosComplement("1101"))
        assertEquals(-4, converter.fromSignedTwosComplement("1100"))
        assertEquals(-5, converter.fromSignedTwosComplement("1011"))
    }

    @Test
    fun decimalToSignedOnesComplement() {
        val converter = DecimalConverter(4)
        assertEquals("0000", converter.toSignedOnesComplement(0))
        assertEquals("0001", converter.toSignedOnesComplement(1))
        assertEquals("0010", converter.toSignedOnesComplement(2))
        assertEquals("0011", converter.toSignedOnesComplement(3))
        assertEquals("0100", converter.toSignedOnesComplement(4))
        assertEquals("0101", converter.toSignedOnesComplement(5))

        assertEquals("1110", converter.toSignedOnesComplement(-1))
        assertEquals("1101", converter.toSignedOnesComplement(-2))
        assertEquals("1100", converter.toSignedOnesComplement(-3))
        assertEquals("1011", converter.toSignedOnesComplement(-4))
        assertEquals("1010", converter.toSignedOnesComplement(-5))
    }

    @Test
    fun signedOnesComplementToDecimal() {
        val converter = DecimalConverter(8)
        assertEquals(0, converter.fromSignedOnesComplement("0000"))
        assertEquals(1, converter.fromSignedOnesComplement("0001"))
        assertEquals(2, converter.fromSignedOnesComplement("0010"))
        assertEquals(3, converter.fromSignedOnesComplement("0011"))
        assertEquals(4, converter.fromSignedOnesComplement("0100"))
        assertEquals(5, converter.fromSignedOnesComplement("0101"))

        assertEquals(-1, converter.fromSignedOnesComplement("1110"))
        assertEquals(-2, converter.fromSignedOnesComplement("1101"))
        assertEquals(-3, converter.fromSignedOnesComplement("1100"))
        assertEquals(-4, converter.fromSignedOnesComplement("1011"))
        assertEquals(-5, converter.fromSignedOnesComplement("1010"))
    }

    @Test
    fun decimalToSignedAlternating() {
        val converter = DecimalConverter(4)
        assertEquals("0000", converter.toSignedAlternating(0))
        assertEquals("0010", converter.toSignedAlternating(1))
        assertEquals("0100", converter.toSignedAlternating(2))
        assertEquals("0110", converter.toSignedAlternating(3))
        assertEquals("1000", converter.toSignedAlternating(4))
        assertEquals("1010", converter.toSignedAlternating(5))

        assertEquals("0001", converter.toSignedAlternating(-1))
        assertEquals("0011", converter.toSignedAlternating(-2))
        assertEquals("0101", converter.toSignedAlternating(-3))
        assertEquals("0111", converter.toSignedAlternating(-4))
        assertEquals("1001", converter.toSignedAlternating(-5))
    }

    @Test
    fun signedAlternatingToDecimal() {
        val converter = DecimalConverter(8)
        assertEquals(0, converter.fromSignedAlternating("0000"))
        assertEquals(1, converter.fromSignedAlternating("0010"))
        assertEquals(2, converter.fromSignedAlternating("0100"))
        assertEquals(3, converter.fromSignedAlternating("0110"))
        assertEquals(4, converter.fromSignedAlternating("1000"))
        assertEquals(5, converter.fromSignedAlternating("1010"))

        assertEquals(-1, converter.fromSignedAlternating("0001"))
        assertEquals(-2, converter.fromSignedAlternating("0011"))
        assertEquals(-3, converter.fromSignedAlternating("0101"))
        assertEquals(-4, converter.fromSignedAlternating("0111"))
        assertEquals(-5, converter.fromSignedAlternating("1001"))
    }

    @Test
    fun decimalToSignedBaseNegativeTwo() {
        val converter = DecimalConverter(4)
        assertEquals("0000", converter.toSignedBaseNegativeTwo(0))
        assertEquals("0001", converter.toSignedBaseNegativeTwo(1))
        assertEquals("0110", converter.toSignedBaseNegativeTwo(2))
        assertEquals("0111", converter.toSignedBaseNegativeTwo(3))
        assertEquals("0100", converter.toSignedBaseNegativeTwo(4))
        assertEquals("0101", converter.toSignedBaseNegativeTwo(5))

        assertEquals("0011", converter.toSignedBaseNegativeTwo(-1))
        assertEquals("0010", converter.toSignedBaseNegativeTwo(-2))
        assertEquals("1101", converter.toSignedBaseNegativeTwo(-3))
        assertEquals("1100", converter.toSignedBaseNegativeTwo(-4))
        assertEquals("1111", converter.toSignedBaseNegativeTwo(-5))
    }

    @Test
    fun signedBaseNegativeTwoToDecimal() {
        val converter = DecimalConverter(8)
        assertEquals(0, converter.fromSignedBaseNegativeTwo("0000"))
        assertEquals(1, converter.fromSignedBaseNegativeTwo("0001"))
        assertEquals(2, converter.fromSignedBaseNegativeTwo("0110"))
        assertEquals(3, converter.fromSignedBaseNegativeTwo("0111"))
        assertEquals(4, converter.fromSignedBaseNegativeTwo("0100"))
        assertEquals(5, converter.fromSignedBaseNegativeTwo("0101"))

        assertEquals(-1, converter.fromSignedBaseNegativeTwo("0011"))
        assertEquals(-2, converter.fromSignedBaseNegativeTwo("0010"))
        assertEquals(-3, converter.fromSignedBaseNegativeTwo("1101"))
        assertEquals(-4, converter.fromSignedBaseNegativeTwo("1100"))
        assertEquals(-5, converter.fromSignedBaseNegativeTwo("1111"))
    }

    @Test
    fun decimalToSignedSymmetrical() {
        val converter = DecimalConverter(4)
        assertEquals("0000", converter.toSignedSymmetrical(0, 3))
        assertEquals("0001", converter.toSignedSymmetrical(1, 3))
        assertEquals("001z", converter.toSignedSymmetrical(2, 3))
        assertEquals("0010", converter.toSignedSymmetrical(3, 3))
        assertEquals("0011", converter.toSignedSymmetrical(4, 3))
        assertEquals("01zz", converter.toSignedSymmetrical(5, 3))

        assertEquals("000z", converter.toSignedSymmetrical(-1, 3))
        assertEquals("00z1", converter.toSignedSymmetrical(-2, 3))
        assertEquals("00z0", converter.toSignedSymmetrical(-3, 3))
        assertEquals("00zz", converter.toSignedSymmetrical(-4, 3))
        assertEquals("0z11", converter.toSignedSymmetrical(-5, 3))
    }

    @Test
    fun signedSymmetricalToDecimal() {
        val converter = DecimalConverter(8)
        assertEquals(0, converter.fromSignedSymmetrical("0000", 3))
        assertEquals(1, converter.fromSignedSymmetrical("0001", 3))
        assertEquals(2, converter.fromSignedSymmetrical("001z", 3))
        assertEquals(3, converter.fromSignedSymmetrical("0010", 3))
        assertEquals(4, converter.fromSignedSymmetrical("0011", 3))
        assertEquals(5, converter.fromSignedSymmetrical("01zz", 3))

        assertEquals(-1, converter.fromSignedSymmetrical("000z", 3))
        assertEquals(-2, converter.fromSignedSymmetrical("00z1", 3))
        assertEquals(-3, converter.fromSignedSymmetrical("00z0", 3))
        assertEquals(-4, converter.fromSignedSymmetrical("00zz", 3))
        assertEquals(-5, converter.fromSignedSymmetrical("0z11", 3))
    }
}