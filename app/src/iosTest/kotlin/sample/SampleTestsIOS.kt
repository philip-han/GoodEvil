package sample

import kotlin.native.concurrent.freeze
import kotlin.native.concurrent.isFrozen
import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTestsIOS {

    var testField: TestData? = null

    @Test
    fun testHello() {
        testField = TestData(null)
        val testField1 = TestData(testField)
        val testField2 = TestData(testField1)
        val testField3 = TestData(testField2)
        testField1.freeze()
        assertTrue(testField1.isFrozen)
        assertTrue(testField2.isFrozen)
        assertTrue(testField3.isFrozen)
        assertTrue(!testField.isFrozen)
    }
}

data class TestData(val testData: TestData?)