package com.google.codelab.musiclibrary.model

data class ChartResponse (
    val properties: ChartProperties? = null,
    val tracks: List<ChartTracks>? = null
)

class ChartProperties()

data class ChartTracks (
    val layout: String? = null,
    val type: String? = null,
    val key: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val share: ChartShare? = null,
    val images: Images? = null,
    val hub: ChartHub? = null,
    val artists: List<ChartArtist>? = null,
    val url: String? = null,
    val highlightsurls: ChartHighlightsurls? = null,
    val properties: ChartProperties? = null
)

data class ChartArtist (
    val follow: Follow? = null,
    val alias: String? = null,
    val id: String? = null
)

data class Follow (
    val followkey: String? = null
)

data class ChartHighlightsurls (
    val artisthighlightsurl: String? = null,
    val trackhighlighturl: String? = null,
    val relatedhighlightsurl: String? = null
)

data class ChartHub (
    val type: String? = null,
    val image: String? = null,
    val actions: List<ChartAction>? = null,
    val options: List<ChartOption>? = null,
    val explicit: Boolean? = null,
    val displayname: String? = null
)

data class ChartAction (
    val name: String? = null,
    val type: String? = null,
    val id: String? = null,
    val uri: String? = null
)

data class ChartOption (
    val caption: String? = null,
    val actions: List<ChartAction>? = null,
    val beacondata: ChartBeacondata? = null,
    val image: String? = null,
    val type: String? = null,
    val listcaption: String? = null,
    val overflowimage: String? = null,
    val colouroverflowimage: Boolean? = null,
    val providername: String? = null
)

data class ChartBeacondata (
    val type: String? = null,
    val providername: String? = null
)

data class Images (
    val background: String? = null,
    val coverart: String? = null,
    val coverarthq: String? = null,
    val joecolor: String? = null
)

data class ChartShare (
    val subject: String? = null,
    val text: String? = null,
    val href: String? = null,
    val image: String? = null,
    val twitter: String? = null,
    val html: String? = null,
    val avatar: String? = null,
    val snapchat: String? = null
)
