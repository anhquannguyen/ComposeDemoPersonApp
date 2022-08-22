package com.example.jetpackcomposedemo.data

data class RowModel(
    val label: String,
    val content: String,
    val shouldShowButton: Boolean = false,
    val action: (() -> Unit)? = null
)
