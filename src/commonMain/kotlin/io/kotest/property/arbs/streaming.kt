package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.generated.streamingServicesData

fun Arb.Companion.streamingService(): Arb<String> = Arb.of(streamingServicesData)
