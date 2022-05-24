package com.example.penmina.util.route

sealed class SecondLayerSplashRoute(val route:String){
    object Splash:SecondLayerSplashRoute("secondlayer_splash")
    object Onboard:SecondLayerSplashRoute("secondlayer_onboard")
}
