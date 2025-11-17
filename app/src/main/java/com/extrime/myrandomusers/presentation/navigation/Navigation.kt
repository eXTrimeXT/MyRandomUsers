package com.extrime.myrandomusers.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.extrime.myrandomusers.domain.model.User
import com.extrime.myrandomusers.presentation.detail.UserDetailScreen
import com.extrime.myrandomusers.presentation.list.UserListScreen
import com.extrime.myrandomusers.presentation.main.MainScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(
            "userList/{gender}/{nationality}",
            arguments = listOf(
                navArgument("gender") { type = NavType.StringType },
                navArgument("nationality") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val gender = backStackEntry.arguments?.getString("gender") ?: ""
            val nationality = backStackEntry.arguments?.getString("nationality") ?: ""

            UserListScreen(
                navController = navController,
                selectedGender = gender,
                selectedNationality = nationality
            )
        }
        composable(
            "userDetail/{userData}",
            arguments = listOf(navArgument("userData") { type = NavType.StringType })
        ) { backStackEntry ->
            val userData = backStackEntry.arguments?.getString("userData") ?: ""
            val decodedData = URLDecoder.decode(userData, StandardCharsets.UTF_8.toString())
            val userParts = decodedData.split(",")

            if (userParts.size == 10) {
                val user = User(
                    id = userParts[0],
                    firstName = userParts[1],
                    lastName = userParts[2],
                    gender = userParts[3],
                    nationality = userParts[4],
                    age = userParts[5].toInt(),
                    birthDate = userParts[6],
                    phone = userParts[7],
                    email = userParts[8],
                    photoUrl = userParts[9]
                )

                UserDetailScreen(
                    navController = navController,
                    user = user
                )
            }
        }
    }
}