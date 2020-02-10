package com.beyazidyargici.revolutcurrency.util

/**
 * Created by beyazid on 10/02/2020.
 * currency codes and names getting from iban.com/currency-codes
 */
enum class CurrencyEnum {
    AFN,
    ALL,
    ANG,
    AOA,
    ARS,
    AUD,
    AWG,
    AZN,
    BAM,
    BBD,
    BDT,
    BGN,
    BHD,
    BIF,
    BMD,
    BND,
    BOB,
    BRL,
    BSD,
    BTN,
    BWP,
    BYR,
    BZD,
    CAD,
    CDF,
    CHF,
    CLP,
    CNY,
    COP,
    CRC,
    CUP,
    CVE,
    CZK,
    DJF,
    DKK,
    DOP,
    DZD,
    EGP,
    ERN,
    ETB,
    EUR,
    FJD,
    FKP,
    GBP,
    GEL,
    GHS,
    GIP,
    GMD,
    GNF,
    GTQ,
    GYD,
    HKD,
    HNL,
    HRK,
    HTG,
    HUF,
    IDR,
    ILS,
    INR,
    IQD,
    IRR,
    ISK,
    JMD,
    JOD,
    JPY,
    KES,
    KGS,
    KHR,
    KPW,
    KRW,
    KWD,
    KZT,
    MNT,
    MXN,
    MYR,
    NGN,
    NOK,
    NPR,
    NZD,
    OMR,
    PAB,
    PEN,
    PHP,
    PLN,
    QAR,
    RON,
    RSD,
    RUB,
    RWF,
    SAR,
    SEK,
    SGD,
    THB,
    TJS,
    TMT,
    TND,
    TRY,
    UAH,
    UGX,
    USD,
    UYU,
    UZS,
    VEF,
    VND,
    VUV,
    WST,
    ZAR
}

fun getCurrencyNameByCode(currencyCode: String): String = when (currencyCode) {
    CurrencyEnum.EUR.name -> "Euro"
    CurrencyEnum.TRY.name -> "Turkish Lira"
    CurrencyEnum.AFN.name -> "Afghani"
    CurrencyEnum.ALL.name -> "Albanian Lek"
    CurrencyEnum.ANG.name -> "Netherlands Antillean Guilder"
    CurrencyEnum.AOA.name -> "Angolan Kwanza"
    CurrencyEnum.ARS.name -> "Argentine Peso"
    CurrencyEnum.AUD.name -> "Australian Dollar"
    CurrencyEnum.AWG.name -> "Aruban Florin"
    CurrencyEnum.AZN.name -> "Azerbaijanian Manat"
    CurrencyEnum.BAM.name -> "Convertible Mark"
    CurrencyEnum.BBD.name -> "Barbados Dollar"
    CurrencyEnum.BDT.name -> "Bangladeshi Taka"
    CurrencyEnum.BGN.name -> "Bulgarian Lev"
    CurrencyEnum.BHD.name -> "Bahraini Dinar"
    CurrencyEnum.BIF.name -> "Burundi Franc"
    CurrencyEnum.BMD.name -> "Bermudian Dollar"
    CurrencyEnum.BND.name -> "Brunei Dollar"
    CurrencyEnum.BOB.name -> "Boliviano	BOLIVI"
    CurrencyEnum.BRL.name -> "Brazilian Real"
    CurrencyEnum.BSD.name -> "Bahamian Dollar"
    CurrencyEnum.BTN.name -> "Bhutanese Ngultrum"
    CurrencyEnum.BWP.name -> "Botswanan Pula"
    CurrencyEnum.BYR.name -> "Belarussian Ruble"
    CurrencyEnum.BZD.name -> "Belize Dollar"
    CurrencyEnum.CAD.name -> "Canadian Dollar"
    CurrencyEnum.CDF.name -> "Congolese Franc"
    CurrencyEnum.CHF.name -> "Swiss Franc"
    CurrencyEnum.CLP.name -> "Chilean Peso"
    CurrencyEnum.CNY.name -> "Yuan Renminbi"
    CurrencyEnum.COP.name -> "Colombian Peso"
    CurrencyEnum.CRC.name -> "Costa Rican Colo"
    CurrencyEnum.CUP.name -> "Cuban Peso"
    CurrencyEnum.CVE.name -> "Cabo Verde Escudo"
    CurrencyEnum.CZK.name -> "Czech Koruna"
    CurrencyEnum.DJF.name -> "Djibouti Franc"
    CurrencyEnum.DKK.name -> "Danish Krone"
    CurrencyEnum.DOP.name -> "Dominican Peso"
    CurrencyEnum.DZD.name -> "Algerian Dinar"
    CurrencyEnum.EGP.name -> "Egyptian Pound"
    CurrencyEnum.ERN.name -> "Eritrean Nakfa"
    CurrencyEnum.ETB.name -> "Ethiopian Birr"
    CurrencyEnum.FJD.name -> "Fiji Dollar"
    CurrencyEnum.FKP.name -> "Falkland Islands Pound"
    CurrencyEnum.GBP.name -> "Pound Sterling"
    CurrencyEnum.GEL.name -> "Georgian Lari"
    CurrencyEnum.GHS.name -> "Ghana Cedi"
    CurrencyEnum.GIP.name -> "Gibraltar Pound"
    CurrencyEnum.GMD.name -> "Gambian Dalasi"
    CurrencyEnum.GNF.name -> "Guinea Franc"
    CurrencyEnum.GTQ.name -> "Guatemalan Quetzal"
    CurrencyEnum.GYD.name -> "Guyana Dollar"
    CurrencyEnum.HKD.name -> "Hong Kong Dollar"
    CurrencyEnum.HNL.name -> "Honduran Lempira"
    CurrencyEnum.HRK.name -> "Croatian Kuna"
    CurrencyEnum.HTG.name -> "Haitian Gourde"
    CurrencyEnum.HUF.name -> "Hungarian Forint"
    CurrencyEnum.IDR.name -> "Indonesian Rupiah"
    CurrencyEnum.ILS.name -> "New Israeli Sheqel"
    CurrencyEnum.INR.name -> "Indian Rupee"
    CurrencyEnum.IQD.name -> "Iraqi Dinar"
    CurrencyEnum.IRR.name -> "Iranian Rial"
    CurrencyEnum.ISK.name -> "Iceland Krona"
    CurrencyEnum.JMD.name -> "Jamaican Dollar"
    CurrencyEnum.JOD.name -> "Jordanian Dinar"
    CurrencyEnum.JPY.name -> "Japanese Yen"
    CurrencyEnum.KES.name -> "Kenyan Shilling"
    CurrencyEnum.KGS.name -> "Kyrgystani Som"
    CurrencyEnum.KHR.name -> "Cambodian Riel"
    CurrencyEnum.KPW.name -> "North Korean Won"
    CurrencyEnum.KRW.name -> "South Korean Won"
    CurrencyEnum.KWD.name -> "Kuwaiti Dinar"
    CurrencyEnum.KZT.name -> "Kazakhstani Tenge"
    CurrencyEnum.MNT.name -> "Mongolian Tugrik"
    CurrencyEnum.MXN.name -> "Mexican Peso"
    CurrencyEnum.MYR.name -> "Malaysian Ringgit"
    CurrencyEnum.NGN.name -> "Nigerian Naira"
    CurrencyEnum.NOK.name -> "Norwegian Krone"
    CurrencyEnum.NPR.name -> "Nepalese Rupee"
    CurrencyEnum.NZD.name -> "New Zealand Dolla"
    CurrencyEnum.OMR.name -> "Rial Omani"
    CurrencyEnum.PAB.name -> "Panamanian Balboa"
    CurrencyEnum.PEN.name -> "Peruvian Nuevo"
    CurrencyEnum.PHP.name -> "Philippine Peso"
    CurrencyEnum.PLN.name -> "Polish Zloty"
    CurrencyEnum.QAR.name -> "Qatari Rial"
    CurrencyEnum.RON.name -> "Romanian Leu"
    CurrencyEnum.RSD.name -> "Serbian Dinar"
    CurrencyEnum.RUB.name -> "Russian Ruble"
    CurrencyEnum.RWF.name -> "Rwanda Franc"
    CurrencyEnum.SAR.name -> "Saudi Riyal"
    CurrencyEnum.SEK.name -> "Swedish Krona"
    CurrencyEnum.SGD.name -> "Singapore Dollar"
    CurrencyEnum.THB.name -> "Thai Baht"
    CurrencyEnum.TJS.name -> "Tajikistani Somoni"
    CurrencyEnum.TMT.name -> "Turkmenistan New Manat"
    CurrencyEnum.TND.name -> "Tunisian Dinar"
    CurrencyEnum.UAH.name -> "Ukrainian Hryvnia"
    CurrencyEnum.UGX.name -> "Uganda Shilling"
    CurrencyEnum.USD.name -> "US Dollar"
    CurrencyEnum.UYU.name -> "Peso Uruguayo"
    CurrencyEnum.UZS.name -> "Uzbekistan Sum"
    CurrencyEnum.VEF.name -> "Venezuelan Bolivar"
    CurrencyEnum.VND.name -> "Vietnamese Dong"
    CurrencyEnum.VUV.name -> "Vanuatu Vatu"
    CurrencyEnum.WST.name -> "Samoan Tala"
    CurrencyEnum.ZAR.name -> "South African Rand"
    else -> ""
}