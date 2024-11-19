package com.example.unitconverter

enum class ConversionUnitLength(private val toBaseFactor: Double) {
    KILOMETER(1000.0),
    METER(1.0),
    CENTIMETER(0.01),
    MILLIMETER(0.001),
    MILE(1609.34),
    YARD(0.9144),
    FOOT(0.3048),
    INCH(0.0254);

    // Convert a value from this unit to the base unit (meters)
    fun convertToBase(value: Double): Double = value * toBaseFactor
    // Convert a value from the base unit (meters) to this unit
    fun convertFromBase(value: Double): Double = value / toBaseFactor

}