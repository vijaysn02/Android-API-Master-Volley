package com.vijaysn.apimastervolley.Activities.Model

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class GetResponse (
    val page: Long,

    @Json(name = "per_page")
    val perPage: Long,

    val total: Long,

    @Json(name = "total_pages")
    val totalPages: Long,

    val data: List<Datum>,
    val ad: Ad
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<GetResponse>(json)
    }
}

data class Ad (
    val company: String,
    val url: String,
    val text: String
)

data class Datum (
    val id: Long,
    val email: String,

    @Json(name = "first_name")
    val firstName: String,

    @Json(name = "last_name")
    val lastName: String,

    val avatar: String
)
