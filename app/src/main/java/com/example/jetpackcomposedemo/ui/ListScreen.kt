package com.example.jetpackcomposedemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.jetpackcomposedemo.data.PersonData
import com.example.jetpackcomposedemo.Person
import com.example.jetpackcomposedemo.R

@Composable
fun ListScreen(navController: NavHostController) {
    //List data
    val persons = PersonData.persons

    //LazyColumn is the RecyclerView of Compose
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(items = persons, itemContent = {
            // Item of list
            PersonItem(it) {
                navController.navigate("details/${it.id}")
            }
        })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PersonItem(person: Person, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Image(
                painter = rememberImagePainter(person.avatar),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(50.dp)
                    .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(25.dp))
                    .padding(1.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
            Column {
                Text(text = person.fullName(), maxLines = 1)
                Text(text = person.employment.title, maxLines = 1)
            }
        }
    }
}