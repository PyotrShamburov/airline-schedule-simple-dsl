package com.example.dsl.model

import java.time.LocalDateTime

class Flight private constructor(
  private val number: String?,
  private val departureTime: LocalDateTime?,
  private val arrivalTime: LocalDateTime?,
  private val departureAirport: String?,
  private val arrivalAirport: String?,
  private val crew: Set<CrewItem>? = emptySet()
) {

  data class Builder(
    private var number: String? = null,
    private var departureTime: LocalDateTime? = null,
    private var arrivalTime: LocalDateTime? = null,
    private var departureAirport: String? = null,
    private var arrivalAirport: String? = null,
    private val crew: MutableSet<CrewItem>? = mutableSetOf()
  ) {

    fun number(number: String) = apply { this.number = number }
    fun departureTime(departureTime: LocalDateTime) = apply { this.departureTime = departureTime }
    fun arrivalTime(arrivalTime: LocalDateTime) = apply { this.arrivalTime = arrivalTime }
    fun departureAirport(departureAirport: String) = apply { this.departureAirport = departureAirport }
    fun arrivalAirport(arrivalAirport: String) = apply { this.arrivalAirport = arrivalAirport }
    fun crew(setup: Builder.() -> Unit) = apply(setup).build()
    fun crew(crew: Set<CrewItem>) = apply { this.crew?.addAll(crew) }
    fun crewItem(setup: CrewItem.Builder.() -> Unit) = CrewItem.Builder()
      .apply(setup)
      .build()
      .apply { crew?.add(this) }

    fun build() = Flight(
      number = number,
      departureTime = departureTime,
      arrivalTime = arrivalTime,
      departureAirport = departureAirport,
      arrivalAirport = arrivalAirport,
      crew = crew
    )
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Flight

    if (number != other.number) return false
    if (departureTime != other.departureTime) return false
    if (arrivalTime != other.arrivalTime) return false
    if (departureAirport != other.departureAirport) return false
    if (arrivalAirport != other.arrivalAirport) return false
    if (crew != other.crew) return false

    return true
  }

  override fun hashCode(): Int {
    var result = number.hashCode()
    result = 31 * result + departureTime.hashCode()
    result = 31 * result + arrivalTime.hashCode()
    result = 31 * result + departureAirport.hashCode()
    result = 31 * result + arrivalAirport.hashCode()
    result = 31 * result + crew.hashCode()
    return result
  }

  override fun toString(): String {
    return "Flight(number='$number'," +
        " departureTime=$departureTime," +
        " arrivalTime=$arrivalTime," +
        " departureAirport='$departureAirport'," +
        " arrivalAirport='$arrivalAirport'," +
        " crew=$crew)"
  }
}
