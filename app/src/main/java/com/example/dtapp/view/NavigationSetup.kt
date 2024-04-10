package com.example.dtapp.view

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.dtapp.navigation.RootNavHost
import kotlinx.coroutines.launch

@Composable
fun NavigationSetup() {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }

    ModalNavigationDrawer(drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            NavigationDrawer(onDestinationClicked = { route ->
                scope.launch {
                    drawerState.close()
                }

                navController.navigate(route) {
                    launchSingleTop = true
                }
            })
        }
    ) {
        RootNavHost(navController = navController, openDrawer = openDrawer)
    }
}