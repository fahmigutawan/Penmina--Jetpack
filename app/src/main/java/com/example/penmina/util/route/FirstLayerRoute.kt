package com.example.penmina.util.route

sealed class FirstLayerRoute(val route:String){
    object Blue:FirstLayerRoute("firstlayer_blue")
    object White:FirstLayerRoute("firstlayer_white")
}
