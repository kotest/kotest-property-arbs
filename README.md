# kotest-datagen

[![Build Status](https://travis-ci.org/kotest/kotest-datagen.svg?branch=master)](https://travis-ci.org/kotest/kotest-datagen)
[<img src="https://img.shields.io/nexus/s/https/oss.sonatype.org/io.kotest.datagen/kotest-datagen.svg?label=latest%20snapshot&style=plastic"/>](https://oss.sonatype.org/content/repositories/snapshots/io/kotest/datagen/)

Data producers for use in tests (or in your applications) that produce either randomized generated data (eg stock prices), or randomly selected values from existing datasets (brand names).
Many producers take real data sets and combine them in random ways to increase the number of samples. For example, first and last name producers are based on real datasets, but full name producers combine these in a random way. 


| Producer  	| Details  	|
|---	|---	|
| FirstNameProducer | Produces random english or hispanic first names |
| LastNameProducer | Produces random last names based on US census data |
| NameProducer | Produces random first and last names |
|	|	|
| StockExchangeProducer | Produces random stock exchanges, eg `New York Stock Exchange / NYSE / US` |
| DomainProducer | Produces random domain names, eg `www.wibble.co.uk` |
| CountryProducer | Produces random country objects, eg `Botswana / BW / Africa` |
| ContinentProducer | Produces random continents from the list of seven |
| HarryPotterCharacterProducer | Combines wine producer and adds in random review scores and usernames |
|	|	|
| ColorProducer | Produces random named colours, eg, midnight blue |
| BrandProducer | Produces random brand names, eg Betty Crocker |
| GoogleTaxonomyProducer | Produces random google product categories, eg `Furniture > Office Furniture > Desks` |
|	|	|
| VineyardProducer	| Produces random vineyard names, eg `Château Montus Prestige` |
| RegionProducer	| Produces a random wine region, eg `Chassagne-Montrachet` |
| WineProducer | Combines several wine details producers to return full wine objects |
| WineReviewProducer | Combines wine producer and adds in random review scores and usernames |
|	|	|
| tube.StationProducer | Produces randomly selected London underground tube stations |
| tube.JourneyProducer | Generates random journeys from a randomly selected start and end underground station |
