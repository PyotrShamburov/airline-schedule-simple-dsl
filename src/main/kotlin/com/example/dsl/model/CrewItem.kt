package com.example.dsl.model

class CrewItem private constructor(
  private val firstName: String?,
  private val lastName: String?,
  private val position: CrewPosition?
) {

  data class Builder(
    private var firstName: String? = null,
    private var lastName: String? = null,
    private var position: CrewPosition? = null
  ) {

    fun firstName(firstName: String) = apply { this.firstName = firstName }
    fun lastName(lastName: String) = apply { this.lastName = lastName }
    fun position(position: CrewPosition) = apply { this.position = position }
    fun build() = CrewItem(
      firstName = firstName,
      lastName = lastName,
      position = position
    )
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as CrewItem

    if (firstName != other.firstName) return false
    if (lastName != other.lastName) return false
    if (position != other.position) return false

    return true
  }

  override fun hashCode(): Int {
    var result = firstName.hashCode()
    result = 31 * result + lastName.hashCode()
    result = 31 * result + position.hashCode()
    return result
  }

  override fun toString(): String {
    return "CrewItem(firstName='$firstName'," +
        "lastName='$lastName'," +
        "position=$position)"
  }
}
