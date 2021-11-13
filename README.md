# kotest-property-arbs

![master](https://github.com/kotest/kotest-property-arbs/workflows/master/badge.svg)
[<img src="https://img.shields.io/maven-central/v/io.kotest.extensions/kotest-property-arbs.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Choplite)
[<img src="https://img.shields.io/nexus/s/https/oss.sonatype.org/io.kotest.extensions/kotest-property-arbs.svg?label=latest%20snapshot&style=plastic"/>](https://oss.sonatype.org/content/repositories/snapshots/io.kotest.extensions/kotest-property-arbs)


A collection of Kotest `Arb`'s for many different domains, such as stock prices, brand names, wine reviews, countries, tube stations and so on.

Many of these arb's take real data sets and combine them in random ways to increase the number of samples. For example, the `FullName` arb takes a real set of first and last names and combines them randomly to produce full names.


| Arb  	| Details  	|
|---	|---	|
| Arb.firstName() | Produces random english or hispanic first names |
| Arb.lastName() | Produces random last names based on US census data |
| Arb.name() | Produces random first and last names |
|	|	|
| Arb.stockExchanges() | Produces random stock exchanges, eg `New York Stock Exchange / NYSE / US` |

| Arb.domain() | Produces random domain names, eg `www.wibble.co.uk` |
| Arb.country() | Produces random country objects, eg `Botswana / BW / Africa` |
| Arb.continent() | Produces random continents from the list of seven |
| Arb.zipcode() | Random zipcodes from 01000 to 99999, without validating they are exant |

| Arb.harryPotterCharacter() | Combines wine producer and adds in random review scores and usernames |
|	|	|
| Arb.color() | Produces random named colours, eg, midnight blue |
| Arb.brand() | Produces random brand names, eg Betty Crocker |
| Arb.products() | Produces random google product categories, eg `Furniture > Office Furniture > Desks` |
|	|	|
| Arb.vineyards()	| Produces random vineyard names, eg `Château Montus Prestige` |
| Arb.wineRegions()	| Produces a random wine region, eg `Chassagne-Montrachet` |
| Arb.wines() | Combines several wine details producers to return full wine objects |
| Arb.wineReviews()  | Combines wine producer and adds in random review scores and usernames |
|	|	|
| Arb.station() | Produces randomly selected London underground tube stations |
| Arb.journey() | Generates random journeys from a randomly selected start and end underground station |
| Arb.airport() | Random real world airport with IATA code |
| Arb.airline() | Random real world airline |
| Arb.airJourney() | Random airtrips between two airports with an airline and times |
| | |
| Arb.cluedoSuspects() | Clue/Cluedo suspects, eg `Professor Plum` |
| Arb.cluedoWeapons() | Clue/Cluedo weapons, eg `Lead piping` |
| Arb.cluedoLocations() | Clue/Cluedo locations, eg `Ballroom` |
| Arb.cluedoAccusation() | Clue/Cluedo accusations, eg, `Mrs White / Billiards Room / Rope` |
| Arb.monopolyProperty() | Random (US version) monopoly property with rent, price and color |
