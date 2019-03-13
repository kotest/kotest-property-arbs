package io.kotlintest.datagen

@Suppress("MemberVisibilityCanBePrivate")
data class Country(val name: String,
                   val isoNumber3: Int,
                   val isoAlpha2: String,
                   val isoAlpha3: String,
                   val continent: Continent) {
  companion object {
    val Afghanistan = Country("Afghanistan", 4, "AF", "AFG", Continent.Asia)
    val Albania = Country("Albania", 8, "AL", "ALB", Continent.Europe)
    val Algeria = Country("Algeria", 12, "DZ", "DZA", Continent.Africa)
    val AmericanSamoa = Country("American Samoa", 16, "AS", "ASM", Continent.Oceania)
    val Andorra = Country("Andorra", 20, "AD", "AND", Continent.Europe)
    val Angola = Country("Angola", 24, "AO", "AGO", Continent.Africa)
    val Anguilla = Country("Anguilla", 660, "AI", "AIA", Continent.SouthAmerica)
    val Antarctica = Country("Antarctica", 0, "AQ", "", Continent.Antartica)
    val AntiguaBarbuda = Country("Antigua And Barbuda", 28, "AG", "ATG", Continent.SouthAmerica)
    val Argentina = Country("Argentina", 32, "AR", "ARG", Continent.SouthAmerica)
    val Armenia = Country("Armenia", 51, "AM", "ARM", Continent.Asia)
    val Aruba = Country("Aruba", 533, "AW", "ABW", Continent.SouthAmerica)
    val Australia = Country("Australia", 36, "AU", "AUS", Continent.Oceania)
    val Austria = Country("Austria", 40, "AT", "AUT", Continent.Europe)
    val Azerbaijan = Country("Azerbaijan", 31, "AZ", "AZE", Continent.Europe)
    val Bahamas = Country("Bahamas", 44, "BS", "BHS", Continent.SouthAmerica)
    val Bahrain = Country("Bahrain", 48, "BH", "BHR", Continent.Asia)
    val Bangladesh = Country("Bangladesh", 50, "BD", "BGD", Continent.Asia)
    val Barbados = Country("Barbados", 52, "BB", "BRB", Continent.SouthAmerica)
    val Belarus = Country("Belarus", 112, "BY", "BLR", Continent.Europe)
    val Belgium = Country("Belgium", 56, "BE", "BEL", Continent.Europe)
    val Belize = Country("Belize", 84, "BZ", "BLZ", Continent.SouthAmerica)
    val Benin = Country("Benin", 204, "BJ", "BEN", Continent.Africa)
    val Bermuda = Country("Bermuda", 60, "BM", "BMU", Continent.SouthAmerica)
    val Bhutan = Country("Bhutan", 64, "BT", "BTN", Continent.Asia)
    val Bolivia = Country("Bolivia", 68, "BO", "BOL", Continent.SouthAmerica)
    val BosniaHerzegovina = Country("Bosnia And Herzegovina", 70, "BA", "BIH", Continent.Europe)
    val Botswana = Country("Botswana", 72, "BW", "BWA", Continent.Africa)
    val BouvetIsland = Country("Bouvet Island", 0, "BV", "", Continent.Antartica)
    val Brazil = Country("Brazil", 76, "BR", "BRA", Continent.SouthAmerica)
    val BritishIndianOceanTerritory = Country("British Indian Ocean Territory", 0, "IO", "", Continent.Asia)
    val BruneiDarussalam = Country("Brunei Darussalam", 96, "BN", "BRN", Continent.Asia)
    val Bulgaria = Country("Bulgaria", 100, "BG", "BGR", Continent.Europe)
    val BurkinaFaso = Country("Burkina Faso", 854, "BF", "BFA", Continent.Africa)
    val Burundi = Country("Burundi", 108, "BI", "BDI", Continent.Africa)
    val Cambodia = Country("Cambodia", 116, "KH", "KHM", Continent.Asia)
    val Cameroon = Country("Cameroon", 120, "CM", "CMR", Continent.Africa)
    val Canada = Country("Canada", 124, "CA", "CAN", Continent.NorthAmerica)
    val CapeVerde = Country("Cape Verde", 132, "CV", "CPV", Continent.Africa)
    val CaymanIslands = Country("Cayman Islands", 136, "KY", "CYM", Continent.SouthAmerica)
    val CentralAfricanRepublic = Country("Central African Republic", 140, "CF", "CAF", Continent.Africa)
    val Chad = Country("Chad", 148, "TD", "TCD", Continent.Africa)
    val Chile = Country("Chile", 152, "CL", "CHL", Continent.SouthAmerica)
    val China = Country("China", 156, "CN", "CHN", Continent.Asia)
    val ChristmasIsland = Country("Christmas Island", 0, "CX", "", Continent.Oceania)
    val CocosIslands = Country("Cocos Islands", 0, "CC", "", Continent.Oceania)
    val Colombia = Country("Colombia", 170, "CO", "COL", Continent.SouthAmerica)
    val Comoros = Country("Comoros", 174, "KM", "COM", Continent.Africa)
    val Congo = Country("Congo", 178, "CG", "COG", Continent.Africa)
    val DemocraticRepublicCongo = Country("Democratic Republic Of The Congo", 180, "CD", "COD", Continent.Africa)
    val CookIslands = Country("Cook Islands", 184, "CK", "COK", Continent.Oceania)
    val CostaRica = Country("Costa Rica", 188, "CR", "CRI", Continent.SouthAmerica)
    val IvoryCoast = Country("Cote D'ivoire", 384, "CI", "CIV", Continent.Africa)
    val Croatia = Country("Croatia", 191, "HR", "HRV", Continent.Europe)
    val Cuba = Country("Cuba", 192, "CU", "CUB", Continent.SouthAmerica)
    val Cyprus = Country("Cyprus", 196, "CY", "CYP", Continent.Europe)
    val CzechRepublic = Country("Czech Republic", 203, "CZ", "CZE", Continent.Europe)
    val Denmark = Country("Denmark", 208, "DK", "DNK", Continent.Europe)
    val Djibouti = Country("Djibouti", 262, "DJ", "DJI", Continent.Africa)
    val Dominica = Country("Dominica", 212, "DM", "DMA", Continent.SouthAmerica)
    val DominicanRepublic = Country("Dominican Republic", 214, "DO", "DOM", Continent.SouthAmerica)
    val Ecuador = Country("Ecuador", 218, "EC", "ECU", Continent.SouthAmerica)
    val Egypt = Country("Egypt", 818, "EG", "EGY", Continent.Africa)
    val ElSalvador = Country("El Salvador", 222, "SV", "SLV", Continent.SouthAmerica)
    val EquatorialGuinea = Country("Equatorial Guinea", 226, "GQ", "GNQ", Continent.Africa)
    val Eritrea = Country("Eritrea", 232, "ER", "ERI", Continent.Africa)
    val Estonia = Country("Estonia", 233, "EE", "EST", Continent.Europe)
    val Ethiopia = Country("Ethiopia", 231, "ET", "ETH", Continent.Africa)
    val FalklandIslands = Country("Falkland Islands", 238, "FK", "FLK", Continent.SouthAmerica)
    val FaroeIslands = Country("Faroe Islands", 234, "FO", "FRO", Continent.Europe)
    val Fiji = Country("Fiji", 242, "FJ", "FJI", Continent.Oceania)
    val Finland = Country("Finland", 246, "FI", "FIN", Continent.Europe)
    val France = Country("France", 250, "FR", "FRA", Continent.Europe)
    val FrenchGuiana = Country("French Guiana", 254, "GF", "GUF", Continent.SouthAmerica)
    val FrenchPolynesia = Country("French Polynesia", 258, "PF", "PYF", Continent.Oceania)
    val FrenchSouthernTerritories = Country("French Southern Territories", 0, "TF", "", Continent.Antartica)
    val Gabon = Country("Gabon", 266, "GA", "GAB", Continent.Africa)
    val Gambia = Country("Gambia", 270, "GM", "GMB", Continent.Africa)
    val Georgia = Country("Georgia", 268, "GE", "GEO", Continent.Asia)
    val Germany = Country("Germany", 276, "DE", "DEU", Continent.Europe)
    val Ghana = Country("Ghana", 288, "GH", "GHA", Continent.Africa)
    val Gibraltar = Country("Gibraltar", 292, "GI", "GIB", Continent.Europe)
    val Greece = Country("Greece", 300, "GR", "GRC", Continent.Europe)
    val Greenland = Country("Greenland", 304, "GL", "GRL", Continent.Europe)
    val Grenada = Country("Grenada", 308, "GD", "GRD", Continent.SouthAmerica)
    val Guadeloupe = Country("Guadeloupe", 312, "GP", "GLP", Continent.SouthAmerica)
    val Guam = Country("Guam", 316, "GU", "GUM", Continent.Oceania)
    val Guatemala = Country("Guatemala", 320, "GT", "GTM", Continent.SouthAmerica)
    val Guinea = Country("Guinea", 324, "GN", "GIN", Continent.Africa)
    val GuineaBissau = Country("Guinea-bissau", 624, "GW", "GNB", Continent.Africa)
    val Guyana = Country("Guyana", 328, "GY", "GUY", Continent.SouthAmerica)
    val Haiti = Country("Haiti", 332, "HT", "HTI", Continent.SouthAmerica)
    val HeardMcdonald = Country("Heard Island And Mcdonald Islands", 0, "HM", "", Continent.Antartica)
    val Vatican = Country("Holy See (Vatican)", 336, "VA", "VAT", Continent.Europe)
    val Honduras = Country("Honduras", 340, "HN", "HND", Continent.SouthAmerica)
    val HongKong = Country("Hong Kong", 344, "HK", "HKG", Continent.Asia)
    val Hungary = Country("Hungary", 348, "HU", "HUN", Continent.Europe)
    val Iceland = Country("Iceland", 352, "IS", "ISL", Continent.Europe)
    val India = Country("India", 356, "IN", "IND", Continent.Asia)
    val Indonesia = Country("Indonesia", 360, "ID", "IDN", Continent.Asia)
    val Iran = Country("Iran", 364, "IR", "IRN", Continent.Asia)
    val Iraq = Country("Iraq", 368, "IQ", "IRQ", Continent.Asia)
    val Ireland = Country("Ireland", 372, "IE", "IRL", Continent.Europe)
    val Israel = Country("Israel", 376, "IL", "ISR", Continent.Asia)
    val Italy = Country("Italy", 380, "IT", "ITA", Continent.Europe)
    val Jamaica = Country("Jamaica", 388, "JM", "JAM", Continent.SouthAmerica)
    val Japan = Country("Japan", 392, "JP", "JPN", Continent.Asia)
    val Jordan = Country("Jordan", 400, "JO", "JOR", Continent.Asia)
    val Kazakhstan = Country("Kazakhstan", 398, "KZ", "KAZ", Continent.Asia)
    val Kenya = Country("Kenya", 404, "KE", "KEN", Continent.Africa)
    val Kiribati = Country("Kiribati", 296, "KI", "KIR", Continent.Oceania)
    val NorthKorea = Country("North Korea", 408, "KP", "PRK", Continent.Asia)
    val SouthKorea = Country("South Korea", 410, "KR", "KOR", Continent.Asia)
    val Kuwait = Country("Kuwait", 414, "KW", "KWT", Continent.Asia)
    val Kyrgyzstan = Country("Kyrgyzstan", 417, "KG", "KGZ", Continent.Asia)
    val Laos = Country("Laos", 418, "LA", "LAO", Continent.Asia)
    val Latvia = Country("Latvia", 428, "LV", "LVA", Continent.Europe)
    val Lebanon = Country("Lebanon", 422, "LB", "LBN", Continent.Asia)
    val Lesotho = Country("Lesotho", 426, "LS", "LSO", Continent.Africa)
    val Liberia = Country("Liberia", 430, "LR", "LBR", Continent.Africa)
    val Libya = Country("Libyan Arab Jamahiriya", 434, "LY", "LBY", Continent.Africa)
    val Liechtenstein = Country("Liechtenstein", 438, "LI", "LIE", Continent.Europe)
    val Lithuania = Country("Lithuania", 440, "LT", "LTU", Continent.Europe)
    val Luxembourg = Country("Luxembourg", 442, "LU", "LUX", Continent.Europe)
    val Macao = Country("Macao", 446, "MO", "MAC", Continent.Asia)
    val Macedonia = Country("Macedonia", 807, "MK", "MKD", Continent.Europe)
    val Madagascar = Country("Madagascar", 450, "MG", "MDG", Continent.Africa)
    val Malawi = Country("Malawi", 454, "MW", "MWI", Continent.Africa)
    val Malaysia = Country("Malaysia", 458, "MY", "MYS", Continent.Asia)
    val Maldives = Country("Maldives", 462, "MV", "MDV", Continent.Asia)
    val Mali = Country("Mali", 466, "ML", "MLI", Continent.Africa)
    val Malta = Country("Malta", 470, "MT", "MLT", Continent.Europe)
    val MarshallIslands = Country("Marshall Islands", 584, "MH", "MHL", Continent.Oceania)
    val Martinique = Country("Martinique", 474, "MQ", "MTQ", Continent.Oceania)
    val Mauritania = Country("Mauritania", 478, "MR", "MRT", Continent.Africa)
    val Mauritius = Country("Mauritius", 480, "MU", "MUS", Continent.Africa)
    val Mayotte = Country("Mayotte", 0, "YT", "", Continent.Africa)
    val Mexico = Country("Mexico", 484, "MX", "MEX", Continent.NorthAmerica)
    val Micronesia = Country("Micronesia", 583, "FM", "FSM", Continent.Oceania)
    val Moldova = Country("Moldova, Republic Of", 498, "MD", "MDA", Continent.Europe)
    val Monaco = Country("Monaco", 492, "MC", "MCO", Continent.Europe)
    val Mongolia = Country("Mongolia", 496, "MN", "MNG", Continent.Asia)
    val Montserrat = Country("Montserrat", 500, "MS", "MSR", Continent.SouthAmerica)
    val Morocco = Country("Morocco", 504, "MA", "MAR", Continent.Africa)
    val Mozambique = Country("Mozambique", 508, "MZ", "MOZ", Continent.Africa)
    val Myanmar = Country("Myanmar", 104, "MM", "MMR", Continent.Asia)
    val Namibia = Country("Namibia", 516, "NA", "NAM", Continent.Africa)
    val Nauru = Country("Nauru", 520, "NR", "NRU", Continent.Oceania)
    val Nepal = Country("Nepal", 524, "NP", "NPL", Continent.Asia)
    val Netherlands = Country("Netherlands", 528, "NL", "NLD", Continent.Europe)
    val NetherlandsAntilles = Country("Netherlands Antilles", 530, "AN", "ANT", Continent.SouthAmerica)
    val Caledonia = Country(" Caledonia", 540, "NC", "NCL", Continent.Oceania)
    val Zealand = Country(" Zealand", 554, "NZ", "NZL", Continent.Oceania)
    val Nicaragua = Country("Nicaragua", 558, "NI", "NIC", Continent.SouthAmerica)
    val Niger = Country("Niger", 562, "NE", "NER", Continent.Africa)
    val Nigeria = Country("Nigeria", 566, "NG", "NGA", Continent.Africa)
    val Niue = Country("Niue", 570, "NU", "NIU", Continent.Oceania)
    val NorfolkIsland = Country("Norfolk Island", 574, "NF", "NFK", Continent.Oceania)
    val NorthernMarianaIslands = Country("Northern Mariana Islands", 580, "MP", "MNP", Continent.Oceania)
    val Norway = Country("Norway", 578, "NO", "NOR", Continent.Europe)
    val Oman = Country("Oman", 512, "OM", "OMN", Continent.Asia)
    val Pakistan = Country("Pakistan", 586, "PK", "PAK", Continent.Asia)
    val Palau = Country("Palau", 585, "PW", "PLW", Continent.Oceania)
    val PalestinianTerritory = Country("Palestinian Territory", 0, "PS", "", Continent.Asia)
    val Panama = Country("Panama", 591, "PA", "PAN", Continent.SouthAmerica)
    val PapuaGuinea = Country("Papua  Guinea", 598, "PG", "PNG", Continent.Oceania)
    val Paraguay = Country("Paraguay", 600, "PY", "PRY", Continent.SouthAmerica)
    val Peru = Country("Peru", 604, "PE", "PER", Continent.SouthAmerica)
    val Philippines = Country("Philippines", 608, "PH", "PHL", Continent.Asia)
    val Pitcairn = Country("Pitcairn", 612, "PN", "PCN", Continent.Oceania)
    val Poland = Country("Poland", 616, "PL", "POL", Continent.Europe)
    val Portugal = Country("Portugal", 620, "PT", "PRT", Continent.Europe)
    val PuertoRico = Country("Puerto Rico", 630, "PR", "PRI", Continent.SouthAmerica)
    val Qatar = Country("Qatar", 634, "QA", "QAT", Continent.Asia)
    val Reunion = Country("Reunion", 638, "RE", "REU", Continent.Africa)
    val Romania = Country("Romania", 642, "RO", "ROM", Continent.Europe)
    val Russia = Country("Russian Federation", 643, "RU", "RUS", Continent.Europe)
    val Rwanda = Country("Rwanda", 646, "RW", "RWA", Continent.Africa)
    val SaintHelena = Country("Saint Helena", 654, "SH", "SHN", Continent.Africa)
    val SaintKittsNevis = Country("Saint Kitts And Nevis", 659, "KN", "KNA", Continent.SouthAmerica)
    val SaintLucia = Country("Saint Lucia", 662, "LC", "LCA", Continent.SouthAmerica)
    val SaintPierreMiquelon = Country("Saint Pierre And Miquelon", 666, "PM", "SPM", Continent.SouthAmerica)
    val SaintVincentGrenadines = Country("Saint Vincent And The Grenadines", 670, "VC", "VCT", Continent.SouthAmerica)
    val Samoa = Country("Samoa", 882, "WS", "WSM", Continent.Oceania)
    val SanMarino = Country("San Marino", 674, "SM", "SMR", Continent.Europe)
    val SaoTome = Country("Sao Tome And Principe", 678, "ST", "STP", Continent.Africa)
    val SaudiArabia = Country("Saudi Arabia", 682, "SA", "SAU", Continent.Asia)
    val Senegal = Country("Senegal", 686, "SN", "SEN", Continent.Africa)
    val Seychelles = Country("Seychelles", 690, "SC", "SYC", Continent.Africa)
    val SierraLeone = Country("Sierra Leone", 694, "SL", "SLE", Continent.Africa)
    val Singapore = Country("Singapore", 702, "SG", "SGP", Continent.Asia)
    val Slovakia = Country("Slovakia", 703, "SK", "SVK", Continent.Europe)
    val Slovenia = Country("Slovenia", 705, "SI", "SVN", Continent.Europe)
    val SolomonIslands = Country("Solomon Islands", 90, "SB", "SLB", Continent.Oceania)
    val Somalia = Country("Somalia", 706, "SO", "SOM", Continent.Africa)
    val SouthAfrica = Country("South Africa", 710, "ZA", "ZAF", Continent.Africa)
    val SouthGeorgiaSandwich = Country("South Georgia And The South Sandwich Islands", 0, "GS", "", Continent.Antartica)
    val Spain = Country("Spain", 724, "ES", "ESP", Continent.Europe)
    val SriLanka = Country("Sri Lanka", 144, "LK", "LKA", Continent.Asia)
    val Sudan = Country("Sudan", 736, "SD", "SDN", Continent.Africa)
    val Suriname = Country("Suriname", 740, "SR", "SUR", Continent.SouthAmerica)
    val SvalbardJanMayen = Country("Svalbard And Jan Mayen", 744, "SJ", "SJM", Continent.Europe)
    val Swaziland = Country("Swaziland", 748, "SZ", "SWZ", Continent.Africa)
    val Sweden = Country("Sweden", 752, "SE", "SWE", Continent.Europe)
    val Switzerland = Country("Switzerland", 756, "CH", "CHE", Continent.Europe)
    val Syria = Country("Syrian Arab Republic", 760, "SY", "SYR", Continent.Asia)
    val Taiwan = Country("Taiwan", 158, "TW", "TWN", Continent.Asia)
    val Tajikistan = Country("Tajikistan", 762, "TJ", "TJK", Continent.Asia)
    val Tanzania = Country("Tanzania, United Republic Of", 834, "TZ", "TZA", Continent.Africa)
    val Thailand = Country("Thailand", 764, "TH", "THA", Continent.Asia)
    val Timor = Country("Timor-leste", 0, "TL", "", Continent.Asia)
    val Togo = Country("Togo", 768, "TG", "TGO", Continent.Africa)
    val Tokelau = Country("Tokelau", 772, "TK", "TKL", Continent.Oceania)
    val Tonga = Country("Tonga", 776, "TO", "TON", Continent.Oceania)
    val TrinidadTobago = Country("Trinidad And Tobago", 780, "TT", "TTO", Continent.SouthAmerica)
    val Tunisia = Country("Tunisia", 788, "TN", "TUN", Continent.Africa)
    val Turkey = Country("Turkey", 792, "TR", "TUR", Continent.Europe)
    val Turkmenistan = Country("Turkmenistan", 795, "TM", "TKM", Continent.Asia)
    val TurksCaicosIslands = Country("Turks And Caicos Islands", 796, "TC", "TCA", Continent.SouthAmerica)
    val Tuvalu = Country("Tuvalu", 798, "TV", "TUV", Continent.Oceania)
    val Uganda = Country("Uganda", 800, "UG", "UGA", Continent.Africa)
    val Ukraine = Country("Ukraine", 804, "UA", "UKR", Continent.Europe)
    val UAE = Country("United Arab Emirates", 784, "AE", "ARE", Continent.Asia)
    val US = Country("United States", 840, "US", "USA", Continent.NorthAmerica)
    val USMinorOutlyingIslands = Country("United States Minor Outlying Islands", 0, "UM", "", Continent.SouthAmerica)
    val UK = Country("United Kingdom", 826, "GB", "GBR", Continent.Europe)
    val Uruguay = Country("Uruguay", 858, "UY", "URY", Continent.SouthAmerica)
    val Uzbekistan = Country("Uzbekistan", 860, "UZ", "UZB", Continent.Asia)
    val Vanuatu = Country("Vanuatu", 548, "VU", "VUT", Continent.Oceania)
    val Venezuela = Country("Venezuela", 862, "VE", "VEN", Continent.SouthAmerica)
    val Vietnam = Country("Vietnam", 704, "VN", "VNM", Continent.Asia)
    val BritishVirginIslands = Country("British Virgin Islands", 92, "VG", "VGB", Continent.SouthAmerica)
    val USVirginIslands = Country("US Virgin Islands", 850, "VI", "VIR", Continent.SouthAmerica)
    val WallisFutuna = Country("Wallis And Futuna", 876, "WF", "WLF", Continent.Oceania)
    val WesternSahara = Country("Western Sahara", 732, "EH", "ESH", Continent.Africa)
    val Yemen = Country("Yemen", 887, "YE", "YEM", Continent.Asia)
    val Yugoslavia = Country("Yugoslavia", 891, "YU", "YUG", Continent.Europe)
    val Zambia = Country("Zambia", 894, "ZM", "ZMB", Continent.Africa)
    val Zimbabwe = Country("Zimbabwe", 716, "ZW", "ZWE", Continent.Africa)

    val all = listOf(
        Afghanistan,
        Albania,
        Algeria,
        AmericanSamoa,
        Andorra,
        Angola,
        Anguilla,
        Antarctica,
        AntiguaBarbuda,
        Argentina,
        Armenia,
        Aruba,
        Australia,
        Austria,
        Azerbaijan,
        Bahamas,
        Bahrain,
        Bangladesh,
        Barbados,
        Belarus,
        Belgium,
        Belize,
        Benin,
        Bermuda,
        Bhutan,
        Bolivia,
        BosniaHerzegovina,
        Botswana,
        BouvetIsland,
        Brazil,
        BritishIndianOceanTerritory,
        BruneiDarussalam,
        Bulgaria,
        BurkinaFaso,
        Burundi,
        Cambodia,
        Cameroon,
        Canada,
        CapeVerde,
        CaymanIslands,
        CentralAfricanRepublic,
        Chad,
        Chile,
        China,
        ChristmasIsland,
        CocosIslands,
        Colombia,
        Comoros,
        Congo,
        DemocraticRepublicCongo,
        CookIslands,
        CostaRica,
        IvoryCoast,
        Croatia,
        Cuba,
        Cyprus,
        CzechRepublic,
        Denmark,
        Djibouti,
        Dominica,
        DominicanRepublic,
        Ecuador,
        Egypt,
        ElSalvador,
        EquatorialGuinea,
        Eritrea,
        Estonia,
        Ethiopia,
        FalklandIslands,
        FaroeIslands,
        Fiji,
        Finland,
        France,
        FrenchGuiana,
        FrenchPolynesia,
        FrenchSouthernTerritories,
        Gabon,
        Gambia,
        Georgia,
        Germany,
        Ghana,
        Gibraltar,
        Greece,
        Greenland,
        Grenada,
        Guadeloupe,
        Guam,
        Guatemala,
        Guinea,
        GuineaBissau,
        Guyana,
        Haiti,
        HeardMcdonald,
        Vatican,
        Honduras,
        HongKong,
        Hungary,
        Iceland,
        India,
        Indonesia,
        Iran,
        Iraq,
        Ireland,
        Israel,
        Italy,
        Jamaica,
        Japan,
        Jordan,
        Kazakhstan,
        Kenya,
        Kiribati,
        NorthKorea,
        SouthKorea,
        Kuwait,
        Kyrgyzstan,
        Laos,
        Latvia,
        Lebanon,
        Lesotho,
        Liberia,
        Libya,
        Liechtenstein,
        Lithuania,
        Luxembourg,
        Macao,
        Macedonia,
        Madagascar,
        Malawi,
        Malaysia,
        Maldives,
        Mali,
        Malta,
        MarshallIslands,
        Martinique,
        Mauritania,
        Mauritius,
        Mayotte,
        Mexico,
        Micronesia,
        Moldova,
        Monaco,
        Mongolia,
        Montserrat,
        Morocco,
        Mozambique,
        Myanmar,
        Namibia,
        Nauru,
        Nepal,
        Netherlands,
        NetherlandsAntilles,
        Caledonia,
        Zealand,
        Nicaragua,
        Niger,
        Nigeria,
        Niue,
        NorfolkIsland,
        NorthernMarianaIslands,
        Norway,
        Oman,
        Pakistan,
        Palau,
        PalestinianTerritory,
        Panama,
        PapuaGuinea,
        Paraguay,
        Peru,
        Philippines,
        Pitcairn,
        Poland,
        Portugal,
        PuertoRico,
        Qatar,
        Reunion,
        Romania,
        Russia,
        Rwanda,
        SaintHelena,
        SaintKittsNevis,
        SaintLucia,
        SaintPierreMiquelon,
        SaintVincentGrenadines,
        Samoa,
        SanMarino,
        SaoTome,
        SaudiArabia,
        Senegal,
        Seychelles,
        SierraLeone,
        Singapore,
        Slovakia,
        Slovenia,
        SolomonIslands,
        Somalia,
        SouthAfrica,
        SouthGeorgiaSandwich,
        Spain,
        SriLanka,
        Sudan,
        Suriname,
        SvalbardJanMayen,
        Swaziland,
        Sweden,
        Switzerland,
        Syria,
        Taiwan,
        Tajikistan,
        Tanzania,
        Thailand,
        Timor,
        Togo,
        Tokelau,
        Tonga,
        TrinidadTobago,
        Tunisia,
        Turkey,
        Turkmenistan,
        TurksCaicosIslands,
        Tuvalu,
        Uganda,
        Ukraine,
        UAE,
        US,
        USMinorOutlyingIslands,
        UK,
        Uruguay,
        Uzbekistan,
        Vanuatu,
        Venezuela,
        Vietnam,
        BritishVirginIslands,
        USVirginIslands,
        WallisFutuna,
        WesternSahara,
        Yemen,
        Yugoslavia,
        Zambia,
        Zimbabwe
    )
  }
}

class CountryProducer : Producer<Country> {
  override fun produce(): Country = Country.all.random()
}