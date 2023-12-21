package com.scafisystems.myecommerce.presentation.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.scafisystems.myecommerce.presentation.view.fragment.AllOrdersFragment
import com.scafisystems.myecommerce.presentation.view.fragment.HomeFragment
import com.scafisystems.myecommerce.presentation.view.fragment.NewOrderFragment
import com.scafisystems.myecommerce.presentation.view.fragment.OrderViewFragment
import java.util.Stack

class OrderNavigationManager(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {

    private val fragmentStack: Stack<Fragment> = Stack()

    fun navigateTo(destination: Destinations) {
        val fragment = createFragmentForDestination(destination)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        fragmentStack.push(fragment)
    }

    fun navigateBack() {
        if (fragmentStack.size > 1) {
            fragmentStack.pop()
            fragmentManager.popBackStack()
        }
    }

    private fun createFragmentForDestination(destination: Destinations): Fragment {
        return when (destination) {
            Destinations.HOME -> HomeFragment()
            Destinations.NEW_ORDER -> NewOrderFragment()
            Destinations.ALL_ORDERS -> AllOrdersFragment()
            Destinations.VIEW_ORDER -> OrderViewFragment()
        }
    }
}