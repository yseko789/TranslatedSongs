package com.yseko.translatedsongs.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yseko.translatedsongs.BuildConfig
import com.yseko.translatedsongs.network.*
import kotlinx.coroutines.launch
import java.lang.Exception

class PickSongViewModel: ViewModel() {
    private var _output = MutableLiveData<String>()
    val output: LiveData<String> = _output



    private var _responseTracks = MutableLiveData<ResponseTracks>()
    val responseTracks: LiveData<ResponseTracks> = _responseTracks

    private var _responseLyrics = MutableLiveData<ResponseLyrics>()
    val responseLyrics: LiveData<ResponseLyrics> = _responseLyrics

    private var _trackList = MutableLiveData<List<TrackObj>>()
    val trackList: LiveData<List<TrackObj>> = _trackList




    //"5920049"
    fun getLyrics(commonTrackId: String){
        viewModelScope.launch {
            try{
                _responseLyrics.value = MusixApi.retrofitService.getLyrics(commonTrackId, apikey)
                _output.value = _responseLyrics.value!!.message.body.lyrics.lyricsBody.toString()
            }catch (e: Exception){
                _output.value = "Failure: ${e.message}"
            }
        }

    }

    fun searchTracks(qTrack: String, qArtist: String){
        viewModelScope.launch {
            try{
                _responseTracks.value = MusixApi.retrofitService.searchTrack(qTrack, qArtist, apikey)
                _output.value = _responseTracks.value!!.message.body.trackList.toString()
                _trackList.value = responseTracks.value!!.message.body.trackList
            }catch (e: Exception){
                _output.value = "Failure: ${e.message}"
            }
        }
    }
}