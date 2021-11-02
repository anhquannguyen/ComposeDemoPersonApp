package com.example.jetpackcomposedemo

sealed class AppScreens(val title: String, val route: String) {
    object Persons : AppScreens("Persons", "persons")
    object Details : AppScreens("Details", "details/{id}")
    object Address : AppScreens("Address", "details/address/{id}")
}