package com.ramcosta.samples.destinationstodosample.commons.composables

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.samples.destinationstodosample.commons.DrawerContent
import com.ramcosta.samples.destinationstodosample.ui.screens.NavGraphs
import com.ramcosta.samples.destinationstodosample.ui.screens.destinations.Destination
import com.ramcosta.samples.destinationstodosample.ui.screens.destinations.DirectionDestination
import com.ramcosta.samples.destinationstodosample.ui.screens.navDestination
import com.ramcosta.samples.destinationstodosample.ui.screens.startDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyDrawer(
    destination: Destination,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    NavGraphs.root.destinations
        .filterIsInstance<DirectionDestination>()
        .sortedBy { if (it == NavGraphs.root.startRoute.startDestination) 0 else 1 }
        .forEach {
            it.DrawerContent(
                isSelected = it == destination,
                onDestinationClick = { clickedDestination ->
                    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED
                        && navController.currentBackStackEntry?.navDestination != clickedDestination
                    ) {
                        navController.navigateTo(clickedDestination)
                        coroutineScope.launch { scaffoldState.drawerState.close() }
                    }
                }
            )
        }
}