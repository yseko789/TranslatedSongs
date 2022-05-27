package com.yseko.translatedsongs.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.musixmatch.com/ws/1.1/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MusixService {
    @GET("track.lyrics.get")
    suspend fun getLyrics(
        @Query("commontrack_id") commontrackId: String,
        @Query("apikey") apikey: String
    ): ResponseLyrics

    @GET("track.search")
    suspend fun searchTrack(
        @Query("q_track") qTrack: String,
        @Query("apikey") apikey: String
    ): ResponseTracks
}

object MusixApi{
    val retrofitService: MusixService by lazy{
        retrofit.create(MusixService::class.java)
    }
}