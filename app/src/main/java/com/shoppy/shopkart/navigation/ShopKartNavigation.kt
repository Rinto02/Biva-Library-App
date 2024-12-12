package com.bivalibrary.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bivalibrary.app.screens.ForgotPasswordScreen
import com.bivalibrary.app.screens.SplashScreen
import com.bivalibrary.app.screens.checkout.OrderSuccessScreen
import com.bivalibrary.app.screens.checkout.address.AddressScreen
import com.bivalibrary.app.screens.checkout.address.EditAddressScreen
import com.bivalibrary.app.screens.checkout.ordersummary.OrderSummaryScreen
import com.bivalibrary.app.screens.checkout.payment.PaymentScreen
import com.bivalibrary.app.screens.login.LoginScreen
import com.bivalibrary.app.screens.login.LoginScreen2
import com.bivalibrary.app.screens.mainscreenholder.MainScreenHolder
import com.bivalibrary.app.screens.myprofile.MyProfileScreen
import com.bivalibrary.app.screens.register.RegisterScreen

@Composable
fun BivaLibraryNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreens.SplashScreen.name){
        composable(NavScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(NavScreens.LoginScreen.name){
            LoginScreen2(navController = navController)
        }

        composable(NavScreens.RegisterScreen.name){
            RegisterScreen(navController = navController)
        }

        composable(NavScreens.MainScreenHolder.name){
            MainScreenHolder(navController = navController)
        }

        composable(NavScreens.ForgotPasswordScreen.name) {
            ForgotPasswordScreen(navHostController = navController)
        }

    }

}