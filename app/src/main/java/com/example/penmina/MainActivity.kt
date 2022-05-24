package com.example.penmina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.penmina.navigation.FirstLayerNav
import com.example.penmina.ui.theme.JetpackPenminaTheme
import com.example.penmina.ui.theme.RedNormal
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackPenminaTheme {
                Surface {
                    val coroutineScope = rememberCoroutineScope()
                    FirstLayerNav(scope = coroutineScope)
                }
            }
        }
    }
}