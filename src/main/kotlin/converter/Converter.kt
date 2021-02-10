package converter

import converter.MeasureUnit.*
import java.util.Scanner


class Converter(private val fromValue: Double, fromUnitText: String, toUnitText: String) {
    private val fromUnit: MeasureUnit = fromString(fromUnitText)
    private val toUnit: MeasureUnit = fromString(toUnitText)
    private val convertedValue = convertToAny(fromValue, fromUnit, toUnit)

    private fun fromString(text: String): MeasureUnit {
        return when (text) {
            "m", "meter", "meters" -> Meters
            "km", "kilometer", "kilometers" -> Kilometers
            "cm", "centimeter", "centimeters" -> Centimeters
            "mm", "millimeter", "millimeters" -> Millimeters
            "mi", "mile", "miles" -> Miles
            "yd", "yard", "yards" -> Yards
            "ft", "foot", "feet" -> Feet
            "in", "inch", "inches" -> Inches

            "g", "gram","grams" -> Grams
            "kg", "kilogram", "kilograms" -> Kilograms
            "mg", "milligram", "milligrams" -> Milligrams
            "lb", "pound", "pounds" -> Pounds
            "oz", "ounce", "ounces" -> Ounces

            "c", "degree celsius", "degrees celsius", "dc", "celsius" -> Celsius
            "f", "degree fahrenheit", "degrees fahrenheit", "df", "fahrenheit" -> Fahrenheit
            "k", "kelvin", "kelvins" -> Kelvins

            else -> Error
        }
    }

    private fun convertToSI(fromValue: Double, fromUnit: MeasureUnit): Double {
        return fromValue * fromUnit.multiplier + fromUnit.shift
    }

    private fun convertToAny(fromValue: Double, fromUnit: MeasureUnit, toUnit: MeasureUnit): Double {
        return (convertToSI(fromValue, fromUnit) - toUnit.shift) / toUnit.multiplier
    }

    override fun toString(): String {
        var from = fromUnit.fullPlural
        var to = toUnit.fullPlural
        return if (fromUnit == Error || toUnit == Error || fromUnit.unitType != toUnit.unitType) {
            "Conversion from $from to $to is impossible"
        } else if (fromUnit.unitType != "Temperature" && fromValue <= 0.0) {
            "${fromUnit.unitType} shouldn't be negative."
        } else {
            if (fromValue == 1.0) from = fromUnit.fullSingular
            if (convertedValue == 1.0) to = toUnit.fullSingular
            "$fromValue $from is $convertedValue $to"
        }
    }
}

fun runConverter(sin: Scanner) {
    fun readMeasure(): String {
        var word = sin.next()
        if (word.toLowerCase() == "degree" || word.toLowerCase() == "degrees") {
            word += " " + sin.next()
        }
        return word.toLowerCase()
    }

    while (true) {
        print("Enter what you want to convert (or exit): ")
        val valueStr = sin.next()
        if (valueStr == "exit")
            break

        val valueDouble: Double = valueStr.toDouble()
        val fromMeasure: String = readMeasure()
        sin.next()
        val toMeasure: String = readMeasure()

        val conv = Converter(valueDouble, fromMeasure, toMeasure)
        println(conv)
        println()
    }
}

