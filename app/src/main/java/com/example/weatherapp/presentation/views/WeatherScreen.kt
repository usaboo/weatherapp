package com.example.weatherapp.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.presentation.viewModels.WeatherScreenViewModel


@Composable
fun WeatherScreen(
    weatherScreenViewModel: WeatherScreenViewModel = hiltViewModel()
) {
    val state = weatherScreenViewModel.state.value
    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            state.data?.let { data ->
                Text("latitude" + data.latitude.toString())
                Text("longitude" + data.longitude.toString())
                for(i in 0..data.dailyData.sunset.lastIndex) {
                    Row() {
                        Text(data.dailyData.sunset[i])
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(data.dailyData.sunset[i])
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(data.dailyData.sunset[i])
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }
    }

}