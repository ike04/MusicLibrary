package com.google.codelab.musiclibrary.model

data class ArtistTracks(
    val tracks: List<TopTrack>
)

data class TopTrack(
    val layout: String? = null,
    val type: String? = null,
    val key: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val share: TopShare? = null,
    val images: TrackImages? = null,
    val hub: TopHub? = null,
    val artists: List<TopArtist>? = null,
    val url: String? = null
)

data class TopArtist(
    val id: String? = null,
    val adamid: String? = null
)

data class TopHub(
    val type: String? = null,
    val image: String? = null,
    val actions: List<TopAction>? = null,
    val options: List<TopOption>? = null,
    val providers: List<TopProvider>? = null,
    val explicit: Boolean? = null,
    val displayname: String? = null
)

data class TopAction(
    val name: String? = null,
    val type: String? = null,
    val id: String? = null,
    val uri: String? = null
)

data class TopOption(
    val caption: String? = null,
    val actions: List<TopAction>? = null,
    val beacondata: TopBeacondata? = null,
    val image: String? = null,
    val type: String? = null,
    val listcaption: String? = null,
    val overflowimage: String? = null,
    val colouroverflowimage: Boolean? = null,
    val providername: String? = null
)

data class TopBeacondata(
    val type: String? = null,
    val providername: String? = null
)

data class TopProvider(
    val caption: String? = null,
    val images: TopProviderImages? = null,
    val actions: List<TopAction>? = null,
    val type: String? = null
)

data class TopProviderImages(
    val overflow: String? = null,
    val default: String? = null
)

data class TopTrackImages(
    val background: String? = null,
    val coverart: String? = null,
    val coverarthq: String? = null,
    val joecolor: String? = null
)

data class TopShare(
    val subject: String? = null,
    val text: String? = null,
    val href: String? = null,
    val image: String? = null,
    val twitter: String? = null,
    val html: String? = null,
    val avatar: String? = null,
    val snapchat: String? = null
)


