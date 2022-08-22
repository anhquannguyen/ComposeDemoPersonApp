package com.example.jetpackcomposedemo.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.jetpackcomposedemo.data.RowModel
import com.example.jetpackcomposedemo.data.PersonData

@Composable
fun Details(navHostController: NavHostController, id: String) {
    val person = PersonData.getById(id.toInt())
    val rows = setOf(
        RowModel(stringResource(id = R.string.username), person.username),
        RowModel(stringResource(id = R.string.gender), person.gender),
        RowModel(stringResource(id = R.string.birthday), person.dateOfBirth),
        RowModel(stringResource(id = R.string.skills), person.employment.keySkill),
        RowModel(stringResource(id = R.string.credit_card), person.creditCard.ccNumber),
        RowModel(
            stringResource(id = R.string.insurance_number),
            person.socialInsuranceNumber
        ),
        RowModel(
            stringResource(id = R.string.address),
            person.seeFullAddress(),
            true
        ) {
            navHostController.navigate("details/address/${person.id}")
        },
    )

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
        BodyDetail(rows)
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
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
private fun BodyDetail(info: Set<RowModel>) {
    Column {
        for (i in info) {
            RowInfo(i.label, i.content, i.shouldShowButton, i.action ?: {})
        }
    }
}

@Composable
private fun RowInfo(
    label: String,
    content: String,
    shouldShowButton: Boolean,
    rowDidTap: () -> Unit
) {
    Column(Modifier.clickable(onClick = {
        rowDidTap()
    })) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.95f)) {
                Text(text = label, maxLines = 1, style = TextStyle(color = Color.Gray))
                Text(text = content, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            if (shouldShowButton) {
                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "",
                )
            }
        }
        Divider()

    }
}