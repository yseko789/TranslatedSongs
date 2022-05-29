package com.yseko.translatedsongs.overview

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.yseko.translatedsongs.BuildConfig
import com.yseko.translatedsongs.network.*
import kotlinx.coroutines.launch
import java.lang.Exception

class PickSongViewModel: ViewModel() {
    private var _output = MutableLiveData<String>()
    val output: LiveData<String> = _output

    private var _temp = MutableLiveData<String>()
    val temp: LiveData<String> = _temp

    private val apikey = ""

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
                _temp.value = _responseLyrics.value!!.message.body.lyrics.lyricsBody
//                _output.value = _responseLyrics.value!!.message.body.lyrics.lyricsBody
                setupTranslator()
            }catch (e: Exception){
                _output.value = "Failure: ${e.message}"
            }
        }

    }

    fun searchTracks(qTrack: String, qArtist: String){
        viewModelScope.launch {
            try{
                _responseTracks.value = MusixApi.retrofitService.searchTrack(qTrack, qArtist, "desc", apikey)
                _output.value = _responseTracks.value!!.message.body.trackList.toString()
                _trackList.value = responseTracks.value!!.message.body.trackList
            }catch (e: Exception){
                _output.value = "Failure: ${e.message}"
            }
        }
    }

    private fun setupTranslator(){
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()

        val translatorEnglishSpanish = Translation.getClient(options)

        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        translatorEnglishSpanish.downloadModelIfNeeded(conditions)

        translatorEnglishSpanish.translate(temp.value.toString().dropLast(70))
            .addOnSuccessListener {
                _temp.value = it
            }
            .addOnFailureListener{
                _output.value = "fail"
            }

        val optionsTwo = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorSpanishEnglish = Translation.getClient(optionsTwo)

        val conditionsTwo = DownloadConditions.Builder()
            .requireWifi()
            .build()

        translatorSpanishEnglish.downloadModelIfNeeded(conditionsTwo)

        translatorSpanishEnglish.translate(temp.value.toString().dropLast(70))
            .addOnSuccessListener {
                _output.value = it
            }
            .addOnFailureListener{
                _output.value = "fail"
            }

    }
}