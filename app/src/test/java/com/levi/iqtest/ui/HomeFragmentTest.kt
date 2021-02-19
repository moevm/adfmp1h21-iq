package com.levi.iqtest.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.levi.iqtest.R

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.levi.iqtest.ui.home.HomeFragment

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{
    @Test
    fun navigation() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
//        navController.setViewModelStore(ViewModelStore())
        navController.setGraph(R.navigation.mobile_navigation)

          val scenario = launchFragmentInContainer {
            HomeFragment().also { fragment ->
                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        navController.navigate(R.id.navigation_home)
        onView(withId(R.id.btnToAbout)).perform(click())
        assertTrue(navController.currentDestination?.id==R.id.aboutFragment)

        navController.navigate(R.id.navigation_home)
        onView(withId(R.id.btnLeaderboard)).perform(click())
        assertTrue(navController.currentDestination?.id==R.id.leaderboardFragment)

        navController.navigate(R.id.navigation_home)
        onView(withId(R.id.btnToTesting)).perform(click())
        assertTrue(navController.currentDestination?.id==R.id.trainerFragment)

        navController.navigate(R.id.navigation_home)
        onView(withId(R.id.btnToTraining)).perform(click())
        assertTrue(navController.currentDestination?.id==R.id.trainerFragment)
    }
}
