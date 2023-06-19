package uz.gita.surviveexam.presentation.ui.screens.shop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.surviveexam.presentation.ui.screens.shop.page.cart.CartScreen
import uz.gita.surviveexam.presentation.ui.screens.shop.page.home.HomeScreen

class MainScreen : AndroidScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(tab = HomeScreen){
            Scaffold(content = {
                               Box(modifier = Modifier
                                   .fillMaxSize()
                                   .padding(it)){
                                   CurrentTab()
                               }
            },
            bottomBar = {
                NavigationBar(modifier = Modifier) {
                    TabNavigationItem(tab = HomeScreen)
                    TabNavigationItem(tab = CartScreen)
                }
            })
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}