# kotest-property-arbs

![master](https://github.com/kotest/kotest-property-arbs/workflows/master/badge.svg)
[<img src="https://img.shields.io/maven-central/v/io.kotest/kotest-property-arbs.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Ckotest-property-arbs)
[<img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fcentral.sonatype.com%2Frepository%2Fmaven-snapshots%2Fio%2Fkotest%2Fkotest-property-arbs%2Fmaven-metadata.xml"/>](https://central.sonatype.com/repository/maven-snapshots/io/kotest/kotest-property-arbs/maven-metadata.xml)

A collection of `Arb`'s that work with [Kotest property testing](https://kotest.io/docs/proptest/property-based-testing.html) for many different domains, such as stock prices, brand names, wine reviews, countries, tube stations and so on.

See [full docs](https://kotest.io/docs/proptest/property-test-extra-arbs.html) at the main Kotest site.

## Available arbs

| Arb | Description |
|---|---|
| `Arb.addresses()` | Generates a postal address with street, city, country, and postcode. |
| `Arb.airJourney()` | Generates a flight as a departure airport, arrival airport, and airline. |
| `Arb.airline()` | Generates a real-world airline with name and IATA code. |
| `Arb.airport()` | Generates a real-world airport with name, IATA/ICAO codes, and location. |
| `Arb.boardGame()` | Generates a board game with name, publisher, type, and recommended player count. |
| `Arb.brand()` | Generates a real-world product brand name. |
| `Arb.cars()` | Generates a car manufacturer name. |
| `Arb.chessMove()` | Generates a chess move from a piece, source square, and destination square. |
| `Arb.chessPiece()` | Generates a chess piece (king, queen, rook, bishop, knight, pawn). |
| `Arb.chessSquare()` | Generates a chess board square in algebraic notation (e.g. `e4`). |
| `Arb.cluedoAccusations()` | Generates a Cluedo accusation combining suspect, weapon, and location. |
| `Arb.cluedoLocations()` | Generates a Cluedo location (Ballroom, Library, Kitchen, ...). |
| `Arb.cluedoSuspects()` | Generates a Cluedo suspect (Colonel Mustard, Miss Scarlett, ...). |
| `Arb.cluedoWeapons()` | Generates a Cluedo weapon (Revolver, Dagger, Spanner, ...). |
| `Arb.color()` | Generates a colour name. |
| `Arb.continent()` | Generates one of the seven continents. |
| `Arb.country()` | Generates a country with ISO codes and continent. |
| `Arb.domain()` | Generates a plausible internet domain name with configurable length. |
| `Arb.firstName()` | Generates a first name. |
| `Arb.googleTaxonomy()` | Generates a Google Product Taxonomy category string. |
| `Arb.greekIsland()` | Generates a Greek island with capital and the seas it borders. |
| `Arb.harryPotterCharacter()` | Generates a Harry Potter character from first and last names. |
| `Arb.iceCreamFlavors()` | Generates an ice-cream flavour. |
| `Arb.iceCreams()` | Generates an ice cream as a combination of flavour, scoops, and cone. |
| `Arb.lastName()` | Generates a last name. |
| `Arb.logins()` | Generates a login with a username and password. |
| `Arb.movie()` | Generates a well-known film title. |
| `Arb.movieRole()` | Generates a famous movie character/role name. |
| `Arb.movieStar()` | Generates the name of a well-known movie star. |
| `Arb.movieStarRole()` | Generates a (star, movie, role) triple. |
| `Arb.name()` | Generates a full name composed of a random first and last name. |
| `Arb.products()` | Generates a product with SKU, GTIN, brand, colour, and taxonomy. |
| `Arb.stockExchanges()` | Generates a stock exchange with name, symbol, and country. |
| `Arb.streamingService()` | Generates the name of a video streaming service (Netflix, Hulu, Disney+, ...). |
| `Arb.transactions()` | Generates a financial transaction with date, amount, and currency. |
| `Arb.tubeJourney()` | Generates a London Underground journey between two stations. |
| `Arb.tubeStation()` | Generates a London Underground station with zone and line counts. |
| `Arb.ukPrimeMinister()` | Generates a UK Prime Minister with party and years in office. |
| `Arb.usernames()` | Generates a plausible username string. |
| `Arb.vineyards()` | Generates a vineyard name. |
| `Arb.wineRegions()` | Generates a wine region. |
| `Arb.wineReviews()` | Generates a wine review combining a wine with a score and tasting notes. |
| `Arb.wineries()` | Generates a winery name. |
| `Arb.wines()` | Generates a wine combining vineyard, region, winery, and variety. |
| `Arb.wineVarities()` | Generates a wine variety (Cabernet, Merlot, Riesling, ...). |
| `Arb.zipcodes()` | Generates a 5-digit US zip code as a string. |
