package com.redbite.samples

import com.redbite.samples.Person.DateOfBirth
import com.redbite.samples.Person.MaritalStatus.MARRIED
import com.redbite.samples.Person.MaritalStatus.WIDOWED
import org.junit.jupiter.api.Test
import java.util.Optional
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SerializerTest {

    private val serializer = Serializer()

    @Test
    fun `deserializes person`() {
        val person = serializer.deserialize(
            """
                {
                  "id" : "0221979d-4196-4c0e-aec1-090199d3a9c7",
                  "firstName" : "Jane",
                  "lastName" : "Smith",
                  "dateOfBirth" : {
                    "year" : 1980, 
                    "month" : 12 ,
                    "day" : 18 
                  },
                  "maritalStatus" : "MARRIED",
                  "spouseId" : "c07118bb-dd2d-4647-b70b-e72173d5653c"
                }
            """.trimIndent()
        )

        assertNotNull(person)
        assertEquals("0221979d-4196-4c0e-aec1-090199d3a9c7", person.id?.get())
        assertEquals("Jane", person.firstName?.get())
        assertEquals("Smith", person.lastName?.get())
        assertEquals(1980, person.dateOfBirth?.get()?.year)
        assertEquals(12, person.dateOfBirth?.get()?.month)
        assertEquals(18, person.dateOfBirth?.get()?.day)
        assertEquals(MARRIED, person.maritalStatus?.get())
        assertEquals("c07118bb-dd2d-4647-b70b-e72173d5653c", person.spouseId?.get())
    }

    @Test
    fun `deserializes absent field`() {
        val person = serializer.deserialize(
            """
                {
                  "lastName" : "Smith",
                  "maritalStatus" : "MARRIED",
                  "spouseId" : "c07118bb-dd2d-4647-b70b-e72173d5653c"
                }
            """.trimIndent()
        )

        assertNull(person.id)
        assertNull(person.firstName)
        assertNull(person.dateOfBirth)
    }

    @Test
    fun `deserializes explicitly null field`() {
        val person = serializer.deserialize(
            """
                {
                  "maritalStatus" : "WIDOWED",
                  "spouseId" : null
                }
            """.trimIndent()
        )

        assertTrue(person.spouseId!!.isEmpty)
    }

    @Test
    fun `serialises person`() {
        assertEquals(
            """
                {
                  "id" : "0221979d-4196-4c0e-aec1-090199d3a9c7",
                  "firstName" : "Jane",
                  "lastName" : "Smith",
                  "dateOfBirth" : {
                    "year" : 1980,
                    "month" : 12,
                    "day" : 18
                  },
                  "maritalStatus" : "MARRIED",
                  "spouseId" : "c07118bb-dd2d-4647-b70b-e72173d5653c"
                }
            """.trimIndent(),
            serializer.serialize(
                Person(
                    id = Optional.of("0221979d-4196-4c0e-aec1-090199d3a9c7"),
                    firstName = Optional.of("Jane"),
                    lastName = Optional.of("Smith"),
                    dateOfBirth = Optional.of(DateOfBirth(1980, 12, 18)),
                    maritalStatus = Optional.of(MARRIED),
                    spouseId = Optional.of("c07118bb-dd2d-4647-b70b-e72173d5653c")
                )
            )
        )
    }

    @Test
    fun `serialises absent field`() {
        assertEquals(
            """
                {
                  "lastName" : "Smith",
                  "maritalStatus" : "MARRIED",
                  "spouseId" : "c07118bb-dd2d-4647-b70b-e72173d5653c"
                }
            """.trimIndent(),
            serializer.serialize(
                Person(
                    lastName = Optional.of("Smith"),
                    maritalStatus = Optional.of(MARRIED),
                    spouseId = Optional.of("c07118bb-dd2d-4647-b70b-e72173d5653c")
                )
            )
        )
    }

    @Test
    fun `serialises explicitly null field`() {
        assertEquals(
            """
                {
                  "lastName" : "Williams",
                  "maritalStatus" : "WIDOWED",
                  "spouseId" : null
                }
            """.trimIndent(),
            serializer.serialize(
                Person(
                    lastName = Optional.of("Williams"),
                    maritalStatus = Optional.of(WIDOWED),
                    spouseId = Optional.empty()
                )
            )
        )
    }
}