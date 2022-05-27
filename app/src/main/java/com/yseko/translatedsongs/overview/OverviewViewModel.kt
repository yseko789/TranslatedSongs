package com.yseko.translatedsongs.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.yseko.translatedsongs.network.MusixApi
import com.yseko.translatedsongs.network.ResponseLyrics
import com.yseko.translatedsongs.network.ResponseTracks
import com.yseko.translatedsongs.network.Track
import java.lang.Exception

class OverviewViewModel: ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status
    private val _response = MutableLiveData<ResponseTracks>()
    val response: LiveData<ResponseTracks> = _response


    init{
        getLyrics()
    }

    private fun getSongs(){

    }

    private fun getLyrics(){
        viewModelScope.launch {
            try{
//                _response.value = MusixApi.retrofitService.getLyrics("5920049", "6ce318495d8c4889125fdf7c3965c1d9")
                _response.value = MusixApi.retrofitService.searchTrack("earfquake", "6ce318495d8c4889125fdf7c3965c1d9")
                _status.value = _response.value!!.message.body.trackList.toString()
//                _status.value = _response.value!!.message.body.lyrics.lyricsBody.toString()
            }catch (e: Exception){
                _status.value = "Failure: ${e.message}"
            }
        }

    }

    private fun searchTracks(){
        
    }
}