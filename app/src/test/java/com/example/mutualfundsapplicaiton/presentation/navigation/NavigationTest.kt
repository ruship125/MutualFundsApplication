package com.example.mutualfundsapplicaiton.presentation.navigation

import androidx.navigation.NavController
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class NavigationTest {

    private val navController: NavController = mockk(relaxed = true)

    @Test
    fun `navigateToFundInfo correctly forms route and calls navigate`() {
        val schemeCode = 123
        val expectedRoute = Screen.FundsInfoScreen.route + "/$schemeCode"

        navigateToFundInfo(navController, schemeCode)

        verify { navController.navigate(expectedRoute) }
    }
}
