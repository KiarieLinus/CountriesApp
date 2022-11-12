package com.kiarielinus.countries.presentation.country_details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kiarielinus.countries.presentation.search_country.SearchCountryViewModel
import com.kiarielinus.countries.presentation.ui.theme.Axiforma
import com.kiarielinus.countries.presentation.ui.theme.Gray100
import com.kiarielinus.countries.presentation.ui.theme.Gray200
import com.kiarielinus.countries.presentation.ui.theme.Gray900

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountryDetailsScreen(
    navController: NavController,
    viewModel: SearchCountryViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val state = viewModel.detailsState.value
        val countryInfo = state.countryInfo!!

        LazyColumn {
            stickyHeader {
                DetailsTopBar(
                    onBackClicked = { navController.popBackStack() },
                    countryName = countryInfo.name
                )
                FlagBanner(
                    flagUrl = countryInfo.flagUrl,
                    coatOfArmsUrl = countryInfo.coatOfArmsUrl,
                    countryName = countryInfo.name
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                DetailItem(label = "Population", detail = countryInfo.population.toString())
                DetailItem(label = "Continent", detail = countryInfo.continent.joinToString())
                DetailItem(label = "Capital", detail = countryInfo.capital)
                DetailItem(label = "UN Member State", detail = countryInfo.unMember.toString())
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                DetailItem(label = "Citizen Name", detail = countryInfo.demonyms)
                DetailItem(label = "Currency", detail = countryInfo.currencyName)
                DetailItem(label = "Currency Symbol", detail = countryInfo.currencySymbol)
                DetailItem(
                    label = "Country Domain",
                    detail = countryInfo.countryDomain.joinToString()
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                DetailItem(label = "Start of week", detail = countryInfo.startOfWeek)
                DetailItem(label = "Time zone", detail = countryInfo.timezone.joinToString())
                DetailItem(label = "Dialling Code", detail = countryInfo.diallingCode)
                DetailItem(label = "Driving side", detail = countryInfo.drivingSide)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FlagBanner(
    flagUrl: String,
    coatOfArmsUrl: String?,
    countryName: String
) {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = 2,
        state = pagerState
    ) { page ->
        when (page) {
            0 -> {
                AsyncImage(
                    model = flagUrl,
                    contentDescription = countryName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }
            1 -> {
                coatOfArmsUrl?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = countryName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                } ?: Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .height(200.dp)
                        .background(Gray900, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Flag,
                        contentDescription = "No Coat of Arms",
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.Center),
                        tint = Color(0xFF000000)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailsTopBar(
    onBackClicked: () -> Unit,
    countryName: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "nav Back",
            modifier = Modifier
                .width(14.4.dp)
                .height(17.6.dp)
                .weight(1f)
                .clickable { onBackClicked() },
            tint = if (isSystemInDarkTheme()) Gray100 else Gray900
        )
        Text(
            text = countryName,
            fontFamily = Axiforma,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center,
            lineHeight = 32.9.sp,
            modifier = Modifier.weight(9f),
            color = if (isSystemInDarkTheme()) Gray200 else Gray900
        )
    }
}

@Composable
fun DetailItem(
    label: String,
    detail: String
) {
    Row(modifier = Modifier.padding(start = 24.dp, bottom = 4.dp)) {
        Text(
            text = "$label: ",
            fontFamily = Axiforma,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 25.66.sp,
            color = if (isSystemInDarkTheme()) Gray100 else Gray900
        )
        Text(
            text = detail,
            fontFamily = Axiforma,
            fontWeight = FontWeight.W300,
            fontSize = 16.sp,
            lineHeight = 24.67.sp,
            color = if (isSystemInDarkTheme()) Gray100 else Gray900
        )
    }
}

@Preview
@Composable
fun DTB() {
    DetailsTopBar(onBackClicked = {}, countryName = "Andorra")
}