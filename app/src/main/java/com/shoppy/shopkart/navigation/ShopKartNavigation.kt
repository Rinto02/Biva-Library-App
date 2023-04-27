package com.shoppy.shopkart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shoppy.shopkart.screens.details.DetailsScreen
import com.shoppy.shopkart.screens.AboutScreen
import com.shoppy.shopkart.screens.login.LoginScreen
import com.shoppy.shopkart.screens.register.RegisterScreen
import com.shoppy.shopkart.screens.SplashScreen
import com.shoppy.shopkart.screens.admin.AdminScreen
import com.shoppy.shopkart.screens.mainscreenholder.MainScreenHolder
import com.shoppy.shopkart.screens.mainscreenholder.MainScreenViewModel
import com.shoppy.shopkart.screens.myprofile.MyProfileScreen

@Composable
fun ShopKartNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreens.SplashScreen.name){
        composable(NavScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(NavScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }

        composable(NavScreens.RegisterScreen.name){
            RegisterScreen(navController = navController)
        }
//        var mainScreen = NavScreens.MainScreenHolder.name
//        composable("$mainScreen/{email}", arguments = listOf( navArgument("email"){
//            type = NavType.StringType
//        })){backstack ->
//            backstack.arguments?.getString("email").let {
//
//                MainScreenHolder(navController = navController, email = it.toString())
//            }

        composable(NavScreens.MainScreenHolder.name){
            MainScreenHolder(navController = navController)
        }

        composable(NavScreens.AdminScreen.name){
            AdminScreen(navController = navController)
        }

        composable(NavScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(NavScreens.MyProfile.name){
            MyProfileScreen(navController = navController)
        }

    }

}