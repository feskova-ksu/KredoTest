package com.example.kredotest.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kredotest.tools.animation.ScaleTransitionDirection
import com.example.kredotest.tools.animation.scaleIntoContainer
import com.example.kredotest.tools.animation.scaleOutOfContainer

fun NavGraphBuilder.composableWithAnimation(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLink: List<NavDeepLink> = emptyList(),
    content: @Composable() (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    this.composable(
        route,
        enterTransition = {
            scaleIntoContainer()
        },
        exitTransition = {
            scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
        },
        popEnterTransition = {
            scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
        },
        popExitTransition = {
            scaleOutOfContainer()
        },
        content = content, arguments = arguments, deepLinks = deepLink
    )
}