@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.saw4ukapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(){
    Scaffold {
        Text("SearchScreen", modifier = Modifier.padding(24.dp))
    }
}