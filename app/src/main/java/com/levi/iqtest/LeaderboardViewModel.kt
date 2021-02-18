package com.levi.iqtest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreboardEntry(val name: String, val score: Int){}

class LeaderboardViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    val scoreboard = MutableLiveData<List<ScoreboardEntry>>()
    init{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("sharedPrefs",
            Context.MODE_PRIVATE
        )
        val map =sharedPreferences.getStringSet("scoreboard", mutableSetOf<String>())
        val data = map?.toList()?.map {
            val token = it.split('|')
            ScoreboardEntry(token[1], token[2].toInt())
        }
        Log.i("Debug", map.size.toString())
        Log.i("Debug", data?.size.toString())
        scoreboard.value = data!!
    }
}