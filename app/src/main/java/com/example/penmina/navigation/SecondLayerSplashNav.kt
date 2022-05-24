package com.example.penmina.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.penmina.R
import com.example.penmina.services.MyButton
import com.example.penmina.ui.theme.MintDark
import com.example.penmina.ui.theme.MintLight
import com.example.penmina.ui.theme.White
import com.example.penmina.util.route.FirstLayerRoute
import com.example.penmina.util.route.SecondLayerSplashRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

val Context.myDataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SecondLayerSplashNav(firstLayerController: NavController, scope: CoroutineScope) {
    val secondLayerNavController = rememberAnimatedNavController()
    val context = LocalContext.current

    AnimatedNavHost(
        navController = secondLayerNavController,
        startDestination = SecondLayerSplashRoute.Splash.route
    ) {
        composable(
            route = SecondLayerSplashRoute.Splash.route,
            exitTransition = {
                scaleOut(tween(800), targetScale = 2f) + fadeOut(tween(800))
            }
        ) {
            SecondLayerSplash(
                firstLayerController = firstLayerController,
                secondLayerController = secondLayerNavController,
                scope = scope
            )
        }
        composable(
            route = SecondLayerSplashRoute.Onboard.route,
            enterTransition = { fadeIn(tween(1000)) }
        ) {
            SecondLayerOnboard(
                firstLayerController = firstLayerController,
                secondLayerController = secondLayerNavController,
                scope = scope,
                context = context
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SecondLayerSplash(
    firstLayerController: NavController,
    secondLayerController: NavController,
    scope: CoroutineScope
) {
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.background(
                Brush.verticalGradient(
                    Arrays.asList(
                        MintLight,
                        MintDark
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.padding(all = 72.dp),
                painter = painterResource(id = R.drawable.ic_splash_logo),
                contentDescription = "Splash logo"
            )
        }
    }
    LaunchedEffect(key1 = secondLayerController, scope) {
        this.launch {
            delay(1500)
            if (isFirstTime(context).first()) {
                secondLayerController.navigate(SecondLayerSplashRoute.Onboard.route) {
                    popUpTo(route = SecondLayerSplashRoute.Splash.route) { inclusive = true }
                }
            } else {
                firstLayerController.navigate(FirstLayerRoute.White.route) {
                    popUpTo(route = FirstLayerRoute.Blue.route) { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun SecondLayerOnboard(
    firstLayerController: NavController,
    secondLayerController: NavController,
    scope: CoroutineScope,
    context: Context
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.background(
                Brush.verticalGradient(
                    Arrays.asList(
                        MintLight,
                        MintDark
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_onboard_people),
                        contentDescription = "People vector"
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        "Mitigasi Bencana itu Penting!",
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontSize = 20.sp,
                        color = White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Pelajari lebih lanjut mengenai mitigasi bencana meliputi tsunami hingga gempa bumi",
                        fontFamily = FontFamily(Font(R.font.poppins_reguler)),
                        fontSize = 15.sp,
                        color = White,
                        textAlign = TextAlign.Center
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    MyButton(
                        modifier = Modifier
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp,
                                start = 32.dp,
                                end = 32.dp
                            )
                            .fillMaxWidth(),
                        onClick = {
                            scope.launch {
                                setFirstTime(context = context, false)
                            }
                            firstLayerController.navigate(FirstLayerRoute.White.route) {
                                popUpTo(route = FirstLayerRoute.Blue.route) { inclusive = true }
                            }
                        }
                    ) {
                        Text(
                            text = "Mulai Sekarang",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 16.sp,
                            color = MintDark,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = "Apa Saja Fitur Kita?",
                            fontFamily = FontFamily(Font(R.font.poppins_light)),
                            color = White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

fun isFirstTime(context: Context): Flow<Boolean> {
    val firstTimeState: Flow<Boolean> = context.myDataStore.data.map { pref ->
        pref[booleanPreferencesKey("isFirstTime")] ?: true
    }
    return firstTimeState;
}

suspend fun setFirstTime(context: Context, value: Boolean) {
    context.myDataStore.edit { pref ->
        pref[booleanPreferencesKey("isFirstTime")] = value
    }
}

class pp:ViewModel(){

}