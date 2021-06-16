package com.google.codelab.musiclibrary.model

data class SearchResponse (
    val tracks: Tracks,
    val artists: Artists
)

data class Artists (
    val hits: List<ArtistsHit>
)

data class ArtistsHit (
    val artist: HitArtist
)

data class HitArtist (
    val avatar: String?,
    val id: String,
    val name: String?,
    val verified: Boolean?,
    val adamid: String?
)

data class Tracks (
    val hits: List<TracksHit>
)

data class TracksHit (
    val track: Track
)

data class Track (
    val layout: String?,
    val type: String?,
    val key: String?,
    val title: String?,
    val subtitle: String?,
    val share: Share?,
    val images: TrackImages?,
    val hub: Hub?,
    val artists: List<ArtistElement>,
    val url: String
)

data class ArtistElement (
    val id: String,
    val adamid: String?
)

data class Hub (
    val type: String?,
    val image: String?,
    val actions: List<Action>,
    val options: List<Option>,
    val providers: List<Provider>,
    val explicit: Boolean?,
    val displayname: String?
)

data class Action (
    val name: String? = null,
    val type: String?,
    val id: String? = null,
    val uri: String? = null
)

data class Option (
    val caption: String?,
    val actions: List<Action>,
    val beacondata: Beacondata,
    val image: String?,
    val type: String?,
    val listcaption: String?,
    val overflowimage: String?,
    val colouroverflowimage: Boolean?,
    val providername: String?
)

data class Beacondata (
    val type: String?,
    val providername: String?
)

data class Provider (
    val caption: String?,
    val images: ProviderImages,
    val actions: List<Action>,
    val type: String?
)

data class ProviderImages (
    val overflow: String?,
    val default: String?
)

data class TrackImages (
    val background: String?,
    val coverart: String?,
    val coverarthq: String?,
    val joecolor: String?
)

data class Share (
    val subject: String?,
    val text: String?,
    val href: String?,
    val image: String?,
    val twitter: String?,
    val html: String?,
    val avatar: String?,
    val snapchat: String?
)
