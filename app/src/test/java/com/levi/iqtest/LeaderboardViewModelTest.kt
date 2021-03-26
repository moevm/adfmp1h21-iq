package com.levi.iqtest

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.levi.iqtest.ui.trainer.TrainerViewModel
import junit.framework.TestCase
import org.junit.Assert

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeaderboardViewModelTest {
    @Test
    fun getScoreboard() {
        val application = ApplicationProvider.getApplicationContext() as Application
        val viewModel: LeaderboardViewModel = LeaderboardViewModel(application)
        val mode = 1
        viewModel.loadData(mode)
        Assert.assertNotNull(viewModel.scoreboard.value)
    }
}