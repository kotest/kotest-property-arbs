# kotest-property-arbs

![master](https://github.com/kotest/kotest-property-arbs/workflows/master/badge.svg)
[<img src="https://img.shields.io/maven-central/v/io.kotest/kotest-property-arbs.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Ckotest-property-arbs)
[<img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fcentral.sonatype.com%2Frepository%2Fmaven-snapshots%2Fio%2Fkotest%2Fkotest-property-arbs%2Fmaven-metadata.xml"/>](https://central.sonatype.com/repository/maven-snapshots/io/kotest/kotest-property-arbs/maven-metadata.xml)

A collection of `Arb`'s that work with [Kotest property testing](https://kotest.io/docs/proptest/property-based-testing.html) for many different domains, such as stock prices, brand names, wine reviews, countries, tube stations and so on.

See [full docs](https://kotest.io/docs/proptest/property-test-extra-arbs.html) at the main Kotest site.

## Available arbs

| Arb | Description | Example |
|---|---|---|
| `Arb.addresses()` | Generates a postal address with street, city, country, and postcode. | `6406 BROWN BIRCH PARK` |
| `Arb.airJourney()` | Generates a flight as a departure airport, arrival airport, and airline. | `AirJourney(departure=Airport(name=Koumala Airport, ...), arrival=Airport(name=Agrinion Air Base, ...), distanceKm=337.07, airline=Airline(name=Omni Air Express))` |
| `Arb.airline()` | Generates a real-world airline with name and IATA code. | `Airline(name=MAXjet)` |
| `Arb.airport()` | Generates a real-world airport with name, IATA/ICAO codes, and location. | `Airport(name=Minsk National Airport, lat=53.8881, long=28.0400, country=BY, iataCode=MSQ)` |
| `Arb.boardGame()` | Generates a board game with name, publisher, type, and recommended player count. | `BoardGame(name=7 Wonders, publisher=Repos Production, type=Card drafting, recommendedPlayers=3-7)` |
| `Arb.brand()` | Generates a real-world product brand name. | `Brand(value=Kryptonics)` |
| `Arb.cars()` | Generates a car manufacturer name. | `Car(value=Citroën)` |
| `Arb.chessMove()` | Generates a chess move from a piece, source square, and destination square. | `ChessMove(from=D5, to=C2, capture=null)` |
| `Arb.chessPiece()` | Generates a chess piece (king, queen, rook, bishop, knight, pawn). | `ChessPiece(name=Knight, points=5)` |
| `Arb.chessSquare()` | Generates a chess board square in algebraic notation. | `B1` |
| `Arb.cluedoAccusations()` | Generates a Cluedo accusation combining suspect, weapon, and location. | `CluedoAccusation(suspect=CluedoSuspect(name=Professor Plum), weapon=CluedoWeapon(name=Spanner), location=CluedoLocation(name=Study), correctGuess=false)` |
| `Arb.cluedoLocations()` | Generates a Cluedo location (Ballroom, Library, Kitchen, ...). | `CluedoLocation(name=Ballroom)` |
| `Arb.cluedoSuspects()` | Generates a Cluedo suspect (Colonel Mustard, Miss Scarlett, ...). | `CluedoSuspect(name=Professor Plum)` |
| `Arb.cluedoWeapons()` | Generates a Cluedo weapon (Revolver, Dagger, Spanner, ...). | `CluedoWeapon(name=Candlestick)` |
| `Arb.color()` | Generates a colour name. | `Color(value=squash)` |
| `Arb.continent()` | Generates one of the seven continents. | `Europe` |
| `Arb.country()` | Generates a country with ISO codes and continent. | `Country(name=Macedonia, isoNumber3=807, isoAlpha2=MK, isoAlpha3=MKD, continent=Europe)` |
| `Arb.domain()` | Generates a plausible internet domain name with configurable length. | `Domain(value=cdn.nnj.ug)` |
| `Arb.firstName()` | Generates a first name. | `FirstName(name=Jerrod)` |
| `Arb.googleTaxonomy()` | Generates a Google Product Taxonomy category string. | `GoogleTaxonomy(value=Home & Garden > Bathroom Accessories > Toilet Brush Replacement Heads)` |
| `Arb.greekIsland()` | Generates a Greek island with capital and the seas it borders. | `GreekIsland(name=Skiathos, capital=Skiathos, seas=[Aegean Sea])` |
| `Arb.harryPotterCharacter()` | Generates a Harry Potter character from first and last names. | `Character(firstName=Cedric, lastName=Riddle)` |
| `Arb.iceCreamFlavors()` | Generates an ice-cream flavour. | `IceCreamFlavor(value=Frog Tracks)` |
| `Arb.iceCreams()` | Generates an ice cream as a combination of flavour, scoops, and cone. | `IceCreamServing(scoops=4, coneType=Waffle, size=Large, flavors=[Maple Pecan, Irish Cream, Georgia Peach, Lemon Coconut])` |
| `Arb.lastName()` | Generates a last name. | `LastName(name=KOLB)` |
| `Arb.logins()` | Generates a login with a username, IP, geolocation, and result. | `Login(timestamp=9099118923, username=Username(value=maxi_purple_beetle_2085), ipAddress=180.49.97.115, result=success)` |
| `Arb.movie()` | Generates a well-known film title. | `Movie(title=Fight Club)` |
| `Arb.movieRole()` | Generates a famous movie character/role name. | `MovieRole(name=László Tóth)` |
| `Arb.movieStar()` | Generates the name of a well-known movie star. | `MovieStar(name=Marlon Brando)` |
| `Arb.movieStarRole()` | Generates a (star, movie, role) triple. | `MovieStarRole(star=MovieStar(name=Samuel L. Jackson), movie=Movie(title=Eternal Sunshine of the Spotless Mind), role=MovieRole(name=Han Solo))` |
| `Arb.name()` | Generates a full name composed of a random first and last name. | `Name(first=FirstName(name=Elenor), last=LastName(name=WINGATE))` |
| `Arb.products()` | Generates a product with SKU, GTIN, brand, colour, and taxonomy. | `Product(sku=Sku(value=UXZ29548749), brand=Brand(value=Singing Machine), gtin=Gtin(value=359888366944), price=23743, color=Color(value=silver), name=zipped jacket, ...)` |
| `Arb.stockExchanges()` | Generates a stock exchange with name, symbol, and country. | `StockExchange(name=Saudi Stock Exchange, symbol=Tadawul, country=Country(name=Saudi Arabia, ...))` |
| `Arb.streamingService()` | Generates the name of a video streaming service. | `Tubi` |
| `Arb.transactions()` | Generates a financial transaction with date, amount, and card details. | `Transaction(date=2019-06-01T10:30:38, txType=Recurring, cardType=GooglePay, country=Country(name=Israel, ...), amount=566277)` |
| `Arb.tubeJourney()` | Generates a London Underground journey between two stations. | `Journey(start=Station(name=Bermondsey, zone=2.0, ...), end=Station(name=South Ealing, zone=3.0, ...), durationMinutes=19, farePence=620, method=Oyster)` |
| `Arb.tubeStation()` | Generates a London Underground station with zone and line counts. | `Station(id=130, name=Hounslow Central, zone=4.0, lines=1)` |
| `Arb.ukPrimeMinister()` | Generates a UK Prime Minister with party and years in office. | `UkPrimeMinister(name=Theresa May, party=Conservative, yearsInOffice=2016-2019)` |
| `Arb.usernames()` | Generates a plausible username string. | `Username(value=small_brown_pig_5883)` |
| `Arb.vineyards()` | Generates a vineyard name. | `Vineyard(value=Magnificat)` |
| `Arb.wineRegions()` | Generates a wine region. | `WineRegion(value=Vino Spumante)` |
| `Arb.wineReviews()` | Generates a wine review combining a wine with a score and reviewer. | `WineReview(wine=Wine(vineyard=..., variety=Chardonnay, winery=Prim Family, region=Alto Adige, year=1955), rating=1.31, name=...)` |
| `Arb.wineries()` | Generates a winery name. | `Winery(value=Dr. H. Thanisch (Erben Thanisch))` |
| `Arb.wines()` | Generates a wine combining vineyard, region, winery, and variety. | `Wine(vineyard=Vineyard(value=Trocken), variety=Chenin Blanc-Chardonnay, winery=Eleven Eleven, region=Vittoria, year=1928)` |
| `Arb.wineVarities()` | Generates a wine variety (Cabernet, Merlot, Riesling, ...). | `WineVariety(value=Nebbiolo)` |
| `Arb.zipcodes()` | Generates a 5-digit US zip code as a string. | `99636` |
