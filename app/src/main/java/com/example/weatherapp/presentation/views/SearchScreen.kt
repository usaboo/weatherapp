package com.example.weatherapp.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.presentation.Routes
import com.example.weatherapp.presentation.viewModels.SearchScreenViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {
    val state = searchScreenViewModel.state
    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TextField(
                value = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(15.dp))
                    .border(width = 5.dp, Color.Black),
                onValueChange = { input ->
                    text = input
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier.clickable(enabled = text.isNotBlank()) {
                            searchScreenViewModel.getCities(text)
                            text = ""
                        }
                    )
                }
            )

            Column() {
                state.value.data.forEach {
                    Text(
                        "${it.name}, ${it.country}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(Color.LightGray)
                            .border(
                                2.dp,
                                Color.Black,
                                RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                navController.navigate(Routes.WeatherPage.destination + "/${it.latitude}" + "/${it.longitude}")
                            }
                    )
                }
            }
        }
    }

}