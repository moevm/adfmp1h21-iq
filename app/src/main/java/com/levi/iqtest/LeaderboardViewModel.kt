package com.levi.iqtest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreboardEntry(val name: String, val score: Int, val time:Long){}

class LeaderboardViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    val scoreboard = MutableLiveData<List<ScoreboardEntry>>()
    init{

    }
    fun loadData(mode: Int){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("sharedPrefs",
            Context.MODE_PRIVATE
        )
        val map =sharedPreferences.getStringSet("scoreboard", mutableSetOf<String>())
        val data = map?.toList()?.filter { it->it.startsWith(if(mode==0) "train" else "test") } ?.map {
            val token = it.split('|')
            ScoreboardEntry(token[2], token[3].toInt(),token[4].toLong())
        }
        Log.i("Debug", map.size.toString())
        Log.i("Debug", data?.size.toString())
        scoreboard.value = data!!
    }
}