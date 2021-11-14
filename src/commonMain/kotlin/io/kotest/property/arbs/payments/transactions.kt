package io.kotest.property.arbs.payments

import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.enum
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbs.geo.Country
import io.kotest.property.arbs.geo.country
import io.kotest.property.kotlinx.datetime.datetime
import kotlinx.datetime.LocalDateTime

enum class CardType {
  Visa, Mastercard, Amex, Discover, Paypal, GooglePay, ApplePay
}

enum class TransactionType {
  Online, InStore, Recurring
}

data class Transaction(
  val date: LocalDateTime,
  val txType: TransactionType,
  val cardNumber: String,
  val cardType: CardType,
  val country: Country,
  val amount: Int,
)

fun Arb.Companion.transactions() = Arb.bind(
  Arb.datetime(),
  Arb.enum<TransactionType>(),
  Arb.enum<CardType>(),
  Arb.long(100000000000..10000000000000L),
  Arb.int(100..1000000),
  Arb.country(),
) { date, txType, cardType, number, amount, country ->
  Transaction(date, txType, number.toString().padStart(16, '4'), cardType, country, amount)
}
