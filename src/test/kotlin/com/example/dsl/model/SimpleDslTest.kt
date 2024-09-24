package com.example.dsl.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class SimpleDslTest {

  private val departureTime = LocalDateTime.now()
  private val arrivalTime = departureTime.plusHours(5)

  @Test
  internal fun `test dsl`() {
    val actualSchedule = AirportSchedule.Builder()
      .schedule {
        flight {
          number("BR-3453")
          departureTime(departureTime)
          departureAirport("MSQ")
          arrivalTime(arrivalTime)
          arrivalAirport("DBX")
          crew {
            crewItem {
              firstName("Dan")
              lastName("Wolf")
              position(CrewPosition.CAPTAIN)
            }
            crewItem {
              firstName("Mike")
              lastName("Lewis")
              position(CrewPosition.CO_PILOT)
            }
            crewItem {
              firstName("Alisa")
              lastName("Mays")
              position(CrewPosition.STEWARDESS)
            }
          }
        }
        flight {
          number("BR-812")
          departureTime(departureTime)
          departureAirport("MSQ")
          arrivalTime(arrivalTime)
          arrivalAirport("VNO")
          crew {
            crewItem {
              firstName("Lucas")
              lastName("Eder")
              position(CrewPosition.CAPTAIN)
            }
            crewItem {
              firstName("Matt")
              lastName("Donner")
              position(CrewPosition.CO_PILOT)
            }
            crewItem {
              firstName("Margaret")
              lastName("Lawson")
              position(CrewPosition.STEWARDESS)
            }
            crewItem {
              firstName("Orel")
              lastName("Dandy")
              position(CrewPosition.STEWARDESS)
            }
          }
        }
      }
    val expectedSchedule = buildExpectedSchedule()
    assertEquals(expectedSchedule, actualSchedule)
  }

  private fun buildExpectedSchedule(): AirportSchedule {
    return AirportSchedule.Builder()
      .flights(
        flights = mutableSetOf(
          Flight.Builder()
            .number("BR-3453")
            .departureTime(departureTime)
            .departureAirport("MSQ")
            .arrivalTime(arrivalTime)
            .arrivalAirport("DBX")
            .crew(
              setOf(
                CrewItem.Builder()
                  .firstName("Dan")
                  .lastName("Wolf")
                  .position(CrewPosition.CAPTAIN)
                  .build(),
                CrewItem.Builder()
                  .firstName("Mike")
                  .lastName("Lewis")
                  .position(CrewPosition.CO_PILOT)
                  .build(),
                CrewItem.Builder()
                  .firstName("Alisa")
                  .lastName("Mays")
                  .position(CrewPosition.STEWARDESS)
                  .build()
              )
            )
            .build(),
          Flight.Builder()
            .number("BR-812")
            .departureTime(departureTime)
            .departureAirport("MSQ")
            .arrivalTime(arrivalTime)
            .arrivalAirport("VNO")
            .crew(
              setOf(
                CrewItem.Builder()
                  .firstName("Lucas")
                  .lastName("Eder")
                  .position(CrewPosition.CAPTAIN)
                  .build(),
                CrewItem.Builder()
                  .firstName("Matt")
                  .lastName("Donner")
                  .position(CrewPosition.CO_PILOT)
                  .build(),
                CrewItem.Builder()
                  .firstName("Margaret")
                  .lastName("Lawson")
                  .position(CrewPosition.STEWARDESS)
                  .build(),
                CrewItem.Builder()
                  .firstName("Orel")
                  .lastName("Dandy")
                  .position(CrewPosition.STEWARDESS)
                  .build()
              )
            )
            .build()
        )
      )
      .build()
  }
}


