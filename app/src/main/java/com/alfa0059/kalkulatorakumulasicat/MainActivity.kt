package com.alfa0059.kalkulatorakumulasicat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alfa0059.kalkulatorakumulasicat.navigation.SetupNavGraph
import com.alfa0059.kalkulatorakumulasicat.ui.theme.KalkulatorAkumulasiCatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorAkumulasiCatTheme {
                SetupNavGraph()
            }
        }
    }
}
