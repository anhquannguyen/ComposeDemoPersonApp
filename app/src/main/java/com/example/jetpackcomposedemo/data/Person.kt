package com.example.jetpackcomposedemo
import com.google.gson.annotations.SerializedName


class PersonResponse : ArrayList<Person>()

data class Person(
    @SerializedName("address")
    val address: Address,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("credit_card")
    val creditCard: CreditCard,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("employment")
    val employment: Employment,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("social_insurance_number")
    val socialInsuranceNumber: String,
    @SerializedName("subscription")
    val subscription: Subscription,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("username")
    val username: String
) {
    fun fullName() = "$firstName $lastName"
}

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street_address")
    val streetAddress: String,
    @SerializedName("street_name")
    val streetName: String,
    @SerializedName("zip_code")
    val zipCode: String
)

data class CreditCard(
    @SerializedName("cc_number")
    val ccNumber: String
)

data class Employment(
    @SerializedName("key_skill")
    val keySkill: String,
    @SerializedName("title")
    val title: String
)

data class Subscription(
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("plan")
    val plan: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("term")
    val term: String
)

data class Coordinates(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)