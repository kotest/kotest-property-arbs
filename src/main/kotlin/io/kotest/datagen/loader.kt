package io.kotest.datagen

fun loadResource(resource: String) = object {}.javaClass.getResourceAsStream(resource)

fun loadResourceAsLines(resource: String) = loadResource(resource).bufferedReader().readLines()