package com.example.jetpackcomposedemo.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.jetpackcomposedemo.Person
import com.example.jetpackcomposedemo.R
import com.example.jetpackcomposedemo.data.PersonData

@Composable
fun Details(navHostController: NavHostController, id: String) {
    val person = PersonData.getById(id.toInt())
    val infoMap = mapOf(
        Pair("Username", person.username),
        Pair("Gender", person.gender),
        Pair("Birthday", person.dateOfBirth),
        Pair("Skills", person.employment.keySkill),
        Pair("Credit Card", person.creditCard.ccNumber),
        Pair("Social Insurance Number", person.socialInsuranceNumber),
        Pair("Address", "See address here >")
    )
    val onInfoClick: (String) -> Unit = {
        if (it == "Address") {
            navHostController.navigate("details/address/${person.id}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderDetail(person)
        Divider(
            Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color.LightGray)
        )
        BodyDetail(infoMap, onInfoClick)
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsPreview() {
    Details(rememberNavController(), "2309")
}

@OptIn(ExperimentalUnitApi::class)
@Composable
private fun HeaderDetail(person: Person) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(person.avatar),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(40.dp))
                .padding(1.dp)
                .clip(RoundedCornerShape(40.dp))
        )
        Text(
            person.fullName(),
            maxLines = 1,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            style = TextStyle(fontWeight = FontWeight.Normal),
        )
        Text(person.email, maxLines = 1, style = TextStyle(Color.Gray))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(person.employment.title, style = TextStyle(color = Color.Green))
            Divider(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .background(Color.Gray)
                    .size(1.dp, 10.dp)
            )
            Text(person.phoneNumber, maxLines = 1, style = TextStyle(color = Color.Green))
        }
    }
}

@Composable
private fun BodyDetail(info: Map<String, String>, onClick: (String) -> Unit) {
    Column {
        for (i in info) {
            RowInfo(i.key, i.value, onClick)
        }
    }
}

@Composable
private fun RowInfo(label: String, content: String, onClick: (String) -> Unit) {
    Column(Modifier.clickable(onClick = {
        onClick(label)
    })) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(text = label, maxLines = 1, style = TextStyle(color = Color.Gray))
            Text(text = content, maxLines = 1)
        }
        Divider()

    }
}