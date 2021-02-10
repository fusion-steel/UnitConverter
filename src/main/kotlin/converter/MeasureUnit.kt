package converter

enum class MeasureUnit(val short: String, val fullSingular: String,
                       val fullPlural: String, val multiplier: Double,
                       val shift: Double = 0.0, val unitType: String = "Length") {
    // Length Units
    Meters("m", "meter", "meters", 1.0),
    Kilometers("km", "kilometer", "kilometers", 1000.0),
    Centimeters("cm", "centimeter", "centimeters", 0.01),
    Millimeters("mm", "millimeter", "millimeters", 0.001),
    Miles("mi", "mile", "miles", 1609.35),
    Yards("yd", "yard", "yards", 0.9144),
    Feet("ft", "foot", "feet", 0.3048),
    Inches("in", "inch", "inches", 0.0254),

    // Mass Units
    Grams("g", "gram","grams", 1.0, unitType = "Weight"),
    Kilograms("kg", "kilogram", "kilograms", 1000.0, unitType = "Weight"),
    Milligrams("mg", "milligram", "milligrams", 0.001, unitType = "Weight"),
    Pounds("lb", "pound", "pounds", 453.592, unitType = "Weight"),
    Ounces("oz", "ounce", "ounces", 28.3495, unitType = "Weight"),

    // Temperature Units
    Celsius("c", "degree Celsius", "degrees Celsius", 1.0, 0.0, "Temperature"),
    Fahrenheit("f", "degree Fahrenheit", "degrees Fahrenheit", 5 / 9.0, -32.0 * 5 / 9.0, "Temperature"),
    Kelvins("k", "kelvin", "kelvins", 1.0, -273.15, "Temperature"),

    Error("e", "error", "???", Double.NaN, unitType = "Error");

    override fun toString(): String {
        return super.name.toLowerCase()
    }
}
