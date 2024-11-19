package com.example.unitconverter.weightconverter

enum class ConversionUnitWeight(private val toBaseFactor: Double) {
    KILOGRAM(1000.0),
    GRAM(1.0),
    MILLIGRAM(0.001),
    STONE(6350.29),
    POUND(453.592),
    OUNCE(28.3495);

    // Convert a value from this unit to the base unit (meters)
    fun convertToBase(value: Double): Double = value * toBaseFactor
    // Convert a value from the base unit (meters) to this unit
    fun convertFromBase(value: Double): Double = value / toBaseFactor
}