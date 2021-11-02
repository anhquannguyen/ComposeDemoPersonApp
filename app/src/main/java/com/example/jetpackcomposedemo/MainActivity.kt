package com.example.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposedemo.ui.AddressScreen
import com.example.jetpackcomposedemo.ui.Details
import com.example.jetpackcomposedemo.ui.ListScreen
import com.example.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PersonApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonApp() {
    val navController = rememberNavController()
    // Save current screen to prevent lost data
    // And restore after configuration changes
    val navState = rememberSaveable(saver = saver()) {
        mutableStateOf(AppScreens.Persons)
    }
    navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
        navState.value = getScreen(destination.route)
    }
    Scaffold(
        topBar = { TopBar(navController, navState.value) }
    ) {
        // Navigation graphs
        NavHost(
            navController = navController,
            startDestination = AppScreens.Persons.route
        ) {
            composable(AppScreens.Persons.route) {
                ListScreen(navController)
            }
            composable(AppScreens.Details.route) {
                val id = it.arguments?.getString("id", "") ?: ""
                Details(navController, id)
            }
            composable(AppScreens.Address.route) {
                val id = it.arguments?.getString("id", "") ?: ""
                AddressScreen(id)
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavHostController, screen: AppScreens) {
    TopAppBar(title = {
        Text(text = screen.title)
    }, navigationIcon =
    if (screen.route == AppScreens.Persons.route) {
        null
    } else {
        {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(painterResource(id = R.drawable.ic_back), null)
            }
        }
    })
}

private fun saver() = Saver<MutableState<AppScreens>, String>(save = { state ->
    state.value.route
}, restore = {
    mutableStateOf(getScreen(it))
})

private fun getScreen(route: String?): AppScreens {
    if (route.isNullOrEmpty())
        return AppScreens.Persons
    return when (route) {
        AppScreens.Persons.route -> AppScreens.Persons
        AppScreens.Details.route -> AppScreens.Details
        else -> AppScreens.Address
    }
}
