package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Brightness1
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiarielinus.countries.ui.theme.*

@Composable
fun SearchCountryScreen() {

}

@Composable
fun SearchPane() {
    Box(modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 16.dp)) {
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
                            modifier = Modifier.fillMaxWidth().background(Gray100,
                                RoundedCornerShape(4.dp))
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
                                letterSpacing = 0.3.sp,
                                color = Gray500
                            )
                        }
                        innerTextField()
                    },
                    modifier = Modifier.fillMaxWidth()
                )

        }
    }
}

@Preview(
//    showBackground = true
)
@Composable
fun SearchPanePrev() {
    SearchPane()
}