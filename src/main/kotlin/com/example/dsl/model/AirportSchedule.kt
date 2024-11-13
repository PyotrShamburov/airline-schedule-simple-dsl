package com.example.dsl.model

class AirportSchedule private constructor(
  private val flights: Set<Flight>,
) {

  data class Builder(
    private val flights: MutableSet<Flight> = mutableSetOf(),
  ) {

    fun schedule(setup: Builder.() -> Unit) = apply(setup).build()
    fun flights(flights: Set<Flight>) = apply { this.flights.addAll(flights) }

    fun flight(setup: Flight.Builder.() -> Unit) =
      Flight.Builder()
        .apply(setup)
        .build()
        .apply { flights.add(this) }

    fun build() = AirportSchedule(flights = this.flights)
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as AirportSchedule

    if (flights != other.flights) return false

    return true
  }

  override fun hashCode(): Int {
    return flights.hashCode()
  }

  override fun toString(): String {
    return "AirportSchedule(flights=$flights)"
  }
}
