# kotest-datagen

[![Build Status](https://travis-ci.org/kotest/kotest-datagen.svg?branch=master)](https://travis-ci.org/kotest/kotest-datagen)

Fake data producers for use in tests (or in your applications)


| Producer  	| Details  	|
|---	|---	|
| FirstNameProducer | Produces random english or hispanic first names |
| LastNameProducer | Products random last names based on US census data |
| VineyardProducer	| Produces random vineyard names, eg `Château Montus Prestige` |
| RegionProducer	| Produces a random wine region, eg `Chassagne-Montrachet` |
| WineProducer | Combines several wine details producers to return full wine objects |
| WineReviewProducer | Combines wine producer and adds in random review scores and usernames |