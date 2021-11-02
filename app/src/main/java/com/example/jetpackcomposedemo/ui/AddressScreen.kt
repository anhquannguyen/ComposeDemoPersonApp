package com.example.jetpackcomposedemo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposedemo.data.PersonData

@Preview(showBackground = true)
@Composable
fun AddressScreen(id: String = "2309") {
    val address = PersonData.getById(id.toInt()).address
    Column(Modifier.fillMaxSize()) {
        RowInfo("Address", "${address.streetName}, ${address.streetAddress}")
        RowInfo("City", address.city)
        RowInfo("State", address.state)
        RowInfo("Country", address.country)
    }
}

@Composable
private fun RowInfo(label: String, content: String) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(text = label, style = TextStyle(color = Color.Gray))
            Text(text = content)
        }
        Divider()

    }
}