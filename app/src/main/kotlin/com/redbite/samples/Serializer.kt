package com.redbite.samples

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.fasterxml.jackson.module.kotlin.readValue

class Serializer {

    private val mapper = jacksonMapperBuilder()
        .addModule(Jdk8Module())
        .also {
            it.enable(SerializationFeature.INDENT_OUTPUT)
            it.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
        }.build()

    fun serialize(shadow: Person): String {
        return mapper.writeValueAsString(shadow)
    }

    fun deserialize(str: String): Person {
        return mapper.readValue(str)
    }
}