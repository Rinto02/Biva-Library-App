package com.bivalibrary.app

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.ui.graphics.Color


object BivaLibraryUtils{

    //Colors
    val offWhite = Color(0xFFF5F5F5)
    val blue = Color(0xFF5588FF)
    val darkBlue = Color(0xFF1C0927)
    const val black = 0xFF23272a

    //Add your Google Web Client Id from firebase
    const val webClientId = "734277903802-4atlnncfq108jv4vomuvhpvh1uce1qtq.apps.googleusercontent.com"

    //FCM constants //Not used
    const val API_KEY = ""
    const val SERVER_KEY = ""
    const val BASE_URL = "https://fcm.googleapis.com/"
    const val CONTENT_TYPE = "application/json"
    val TOPIC = if (VERSION.SDK_INT >= VERSION_CODES.S) "Delivery Status" else "/topics/Delivery Status"
    const val RZP_API_KEY = ""
}
