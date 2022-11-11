package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.kiarielinus.countries.presentation.ui.theme.*

@Composable
fun SearchCountryScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SearchPane()
        ConfigPane(
            language = "EN",
            onLangChange = { /*TODO*/ },
            onFilterChange = {}
        )
    }
}

@Composable
fun SearchPane() {
    Column {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(9f)
            ) {
                Text(text = "Explore", fontFamily = ElsieSwashCaps, fontSize = 24.sp)
                Text(text = ".", fontFamily = ElsieSwashCaps, fontSize = 24.sp, color = Orange)
            }
            Icon(
                imageVector = Icons.Outlined.LightMode,
                contentDescription = "",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        BasicTextField(
            value = "",
            onValueChange = {},
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Gray100,
                            RoundedCornerShape(4.dp)
                        )
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = Gray500
                        )
                    }
                    Text(
                        text = "Search Country",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(9f),
                        fontSize = 16.sp,
                        fontFamily = Axiforma,
                        fontWeight = FontWeight.W300,
                        letterSpacing = (-0.3).sp,
                        lineHeight = 24.67.sp,
                        color = Gray500
                    )
                }
                innerTextField()
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ConfigPane(
    language: String,
    onLangChange: () -> Unit,
    onFilterChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ConfigButton(
            text = language,
            imgVector = Icons.Outlined.Language
        ) { onLangChange() }
        ConfigButton(
            text = "Filter",
            imgVector = Icons.Outlined.FilterAlt
        ) { onFilterChange() }
    }
}

@Composable
fun ConfigButton(
    text: String,
    imgVector: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFFFFFF),
            contentColor = Color(0xFF000000)
        )
    ) {
        Icon(imageVector = imgVector, contentDescription = "Configuration Button Image")
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontFamily = Axiforma,
            fontWeight = FontWeight.W500,
            lineHeight = 19.25.sp,
            letterSpacing = (-0.3).sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CountryList(

) {
    LazyColumn{

    }
}

@Composable
fun CountryItem(
    flagImageUrl: String,
    countryName: String,
    countryCapital: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(model = flagImageUrl)
        val state = painter.state
        val painterOnFailure = rememberVectorPainter(image = Icons.Outlined.Flag)
        Image(
            painter = if (state is AsyncImagePainter.State.Success) painter else painterOnFailure,
            contentDescription = countryName,
            modifier = Modifier
                .size(40.dp)
                .background(shape = RoundedCornerShape(25), color = Gray500)
                .clip(RoundedCornerShape(25))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = countryName,
                fontFamily = Axiforma,
                fontSize = 14.sp,
                lineHeight = 22.18.sp,
                letterSpacing = (-0.3).sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400,
                color = Gray900
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = countryCapital,
                fontFamily = Axiforma,
                fontSize = 14.sp,
                lineHeight = 22.18.sp,
                letterSpacing = (-0.3).sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400,
                color = Gray500
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SearchPanePrev() {
    SearchCountryScreen()
}

@Preview(
    showBackground = true
)
@Composable
fun CountryItemPrev() {
    CountryItem(
        flagImageUrl = "che.ck",
        countryName = "Kenya",
        countryCapital = "Nairobi"
    )
}
//@Preview
//@Composable
//fun ConfigBtnPrev() {
//    ConfigButton(text = "Filter", imgVector = Icons.Outlined.FilterAlt){}
//}