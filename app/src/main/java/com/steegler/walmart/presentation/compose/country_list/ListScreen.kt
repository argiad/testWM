package com.steegler.walmart.presentation.compose.country_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.steegler.walmart.data.remote.dto.Currency
import com.steegler.walmart.data.remote.dto.Language
import com.steegler.walmart.domain.model.Country
import com.steegler.walmart.presentation.ResultState

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: CountriesListViewModel = hiltViewModel()
) {

    val state = viewModel.countries.value
    CountryList(state = state)
}

@Composable
fun CountryList(state: ResultState) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(state.itemsList) { country ->
                    CountryItem(country = country)
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "${country.name} , ${country.region}")
                Text(text = country.capital)

            }

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = country.code
                )
                AsyncImage(
                    model = country.flag,
                    contentDescription = "flag",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun ComposablePreview() {
    CountryList(state = ResultState(false, listOf(getMockCountry(), getMockCountry(), getMockCountry())))
//    CountryItem(getMockCountry())
}

// Mock country
private fun getMockCountry(): Country {
    return Country(
        capital = "Kabul",
        code = "AF",
        currency = Currency(
            code = "AFN",
            name = "Afghan afghani",
            symbol = "؋"
        ),
        flag = "https://restcountries.eu/data/afg.svg",
        language = Language(
            code = "ps",
            name = "Pashto"
        ),
        name = "Afghanistan",
        region = "AS"
    )
}
