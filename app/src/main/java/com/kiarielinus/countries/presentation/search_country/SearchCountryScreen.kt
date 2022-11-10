package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiarielinus.countries.ui.theme.*

@Composable
fun SearchCountryScreen() {
    Column(modifier = Modifier.padding(24.dp)) {
        SearchPane()
        Spacer(modifier = Modifier.height(16.dp))
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
    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween){
        ConfigButton(
            text = language,
            imgVector = Icons.Outlined.Language
        ) {onLangChange()}
        ConfigButton(
            text = "Filter",
            imgVector = Icons.Outlined.FilterAlt
        ) {onFilterChange()}
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
fun CountryItem() {
    
}

@Preview(
    showBackground = true
)
@Composable
fun SearchPanePrev() {
    SearchCountryScreen()
}

//@Preview
//@Composable
//fun ConfigBtnPrev() {
//    ConfigButton(text = "Filter", imgVector = Icons.Outlined.FilterAlt){}
//}