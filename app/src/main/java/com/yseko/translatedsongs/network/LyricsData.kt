package com.yseko.translatedsongs.network

import com.squareup.moshi.Json

data class ResponseLyrics (
    val message: MessageLyrics
    )

data class MessageLyrics(
    val body: BodyDataLyrics
)

data class BodyDataLyrics(
    val lyrics: Lyrics
)

data class Lyrics(
    @Json(name="lyrics_id") val lyricsId: String,
    @Json(name="lyrics_body") val lyricsBody: String
)

data class ResponseTracks(
    val message: MessageTracks
)

data class MessageTracks(
    val body: BodyDataTracks
)

data class BodyDataTracks(
    @Json(name="track_list") val trackList: List<TrackObj>
)

data class TrackObj(
    val track: Track
)

data class Track(
    @Json(name="commontrack_id") val commonTrackId: String,
    @Json(name="track_name") val trackName: String,
    @Json(name="artist_name") val artistName: String
)