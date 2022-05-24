package com.example.penmina.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.penmina.ui.theme.MintDark
import com.example.penmina.ui.theme.MintLight
import com.example.penmina.ui.theme.White
import com.example.penmina.util.route.FirstLayerRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.CoroutineScope
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FirstLayerNav(scope: CoroutineScope) {
    /*
    This layer navigation is used to prevent blinking background when navigation navigated.
    This navigation consist of "Blue" and "White" surface.
     */

    val navController = rememberAnimatedNavController()
    val context = LocalContext.current
    val exitAnim = scaleOut(tween(500), targetScale = 2f) + fadeOut(tween(500))
    val enterAnim = fadeIn(tween(1000))

    AnimatedNavHost(navController = navController, startDestination = FirstLayerRoute.Blue.route) {
        composable(route = FirstLayerRoute.Blue.route, exitTransition = { exitAnim }) {
            FirstLayerBlue(navController = navController, scope = scope)
        }
        composable(FirstLayerRoute.White.route, enterTransition = { enterAnim }) {
            FirstLayerWhite(navController = navController, scope = scope)
        }
    }
}

@Composable
fun FirstLayerBlue(navController: NavController, scope: CoroutineScope) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.background(
                Brush.verticalGradient(
                    Arrays.asList(
                        MintLight,
                        MintDark
                    )
                )
            )
        ) {
            SecondLayerSplashNav(firstLayerController = navController, scope = scope)
        }
    }
}

@Composable
fun FirstLayerWhite(navController: NavController, scope: CoroutineScope) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.background(White)) {

        }
    }
}
