package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiarielinus.countries.presentation.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchPane(
    setSearchMode: (ListMode) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
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

        Spacer(modifier = Modifier.height(16.dp))
        var text by remember {
            mutableStateOf("")
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = text,
            onValueChange = {
                text = it
                setSearchMode(ListMode.Search(it))
            },
            shape = RoundedCornerShape(4.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()}),
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = Axiforma,
                fontWeight = FontWeight.W300,
                letterSpacing = (-0.3).sp,
                lineHeight = 24.67.sp,
            ),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .size(16.dp)
                        .weight(1f),
                    tint = Gray500,
                )
            },
            placeholder = {
                Text(
                    text = "Search Country",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(9f).align(CenterHorizontally),
                    fontSize = 16.sp,
                    fontFamily = Axiforma,
                    fontWeight = FontWeight.W300,
                    letterSpacing = (-0.3).sp,
                    lineHeight = 24.67.sp,
                    color = Gray500
                )
            }
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