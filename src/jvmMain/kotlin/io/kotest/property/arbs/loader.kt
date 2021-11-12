package io.kotest.property.arbs

import java.io.InputStream

fun loadResource(resource: String): InputStream = object {}.javaClass.getResourceAsStream(resource)

fun loadResourceAsLines(resource: String) = loadResource(resource).bufferedReader().readLines()
