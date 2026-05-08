package io.kotest.property.arbs.politics

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of

data class UkPrimeMinister(
  val name: String,
  val party: String,
  val yearsInOffice: String,
) {
  companion object {
    val all = listOf(
      UkPrimeMinister("Robert Walpole", "Whig", "1721-1742"),
      UkPrimeMinister("William Pitt the Elder", "Whig", "1766-1768"),
      UkPrimeMinister("Lord North", "Tory", "1770-1782"),
      UkPrimeMinister("William Pitt the Younger", "Tory", "1783-1801, 1804-1806"),
      UkPrimeMinister("Spencer Perceval", "Tory", "1809-1812"),
      UkPrimeMinister("Lord Liverpool", "Tory", "1812-1827"),
      UkPrimeMinister("George Canning", "Tory", "1827"),
      UkPrimeMinister("Duke of Wellington", "Tory", "1828-1830"),
      UkPrimeMinister("Earl Grey", "Whig", "1830-1834"),
      UkPrimeMinister("Robert Peel", "Conservative", "1834-1835, 1841-1846"),
      UkPrimeMinister("Lord Melbourne", "Whig", "1835-1841"),
      UkPrimeMinister("Lord John Russell", "Whig", "1846-1852, 1865-1866"),
      UkPrimeMinister("Earl of Derby", "Conservative", "1852, 1858-1859, 1866-1868"),
      UkPrimeMinister("Lord Palmerston", "Liberal", "1855-1858, 1859-1865"),
      UkPrimeMinister("Benjamin Disraeli", "Conservative", "1868, 1874-1880"),
      UkPrimeMinister("William Ewart Gladstone", "Liberal", "1868-1874, 1880-1885, 1886, 1892-1894"),
      UkPrimeMinister("Lord Salisbury", "Conservative", "1885-1886, 1886-1892, 1895-1902"),
      UkPrimeMinister("Lord Rosebery", "Liberal", "1894-1895"),
      UkPrimeMinister("Arthur Balfour", "Conservative", "1902-1905"),
      UkPrimeMinister("Henry Campbell-Bannerman", "Liberal", "1905-1908"),
      UkPrimeMinister("H. H. Asquith", "Liberal", "1908-1916"),
      UkPrimeMinister("David Lloyd George", "Liberal", "1916-1922"),
      UkPrimeMinister("Bonar Law", "Conservative", "1922-1923"),
      UkPrimeMinister("Stanley Baldwin", "Conservative", "1923-1924, 1924-1929, 1935-1937"),
      UkPrimeMinister("Ramsay MacDonald", "Labour", "1924, 1929-1935"),
      UkPrimeMinister("Neville Chamberlain", "Conservative", "1937-1940"),
      UkPrimeMinister("Winston Churchill", "Conservative", "1940-1945, 1951-1955"),
      UkPrimeMinister("Clement Attlee", "Labour", "1945-1951"),
      UkPrimeMinister("Anthony Eden", "Conservative", "1955-1957"),
      UkPrimeMinister("Harold Macmillan", "Conservative", "1957-1963"),
      UkPrimeMinister("Alec Douglas-Home", "Conservative", "1963-1964"),
      UkPrimeMinister("Harold Wilson", "Labour", "1964-1970, 1974-1976"),
      UkPrimeMinister("Edward Heath", "Conservative", "1970-1974"),
      UkPrimeMinister("James Callaghan", "Labour", "1976-1979"),
      UkPrimeMinister("Margaret Thatcher", "Conservative", "1979-1990"),
      UkPrimeMinister("John Major", "Conservative", "1990-1997"),
      UkPrimeMinister("Tony Blair", "Labour", "1997-2007"),
      UkPrimeMinister("Gordon Brown", "Labour", "2007-2010"),
      UkPrimeMinister("David Cameron", "Conservative", "2010-2016"),
      UkPrimeMinister("Theresa May", "Conservative", "2016-2019"),
      UkPrimeMinister("Boris Johnson", "Conservative", "2019-2022"),
      UkPrimeMinister("Liz Truss", "Conservative", "2022"),
      UkPrimeMinister("Rishi Sunak", "Conservative", "2022-2024"),
      UkPrimeMinister("Keir Starmer", "Labour", "2024-"),
    )
  }
}

fun Arb.Companion.ukPrimeMinister() = Arb.of(UkPrimeMinister.all)
