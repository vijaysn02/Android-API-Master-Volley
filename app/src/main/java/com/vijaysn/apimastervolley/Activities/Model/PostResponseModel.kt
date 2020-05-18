package com.vijaysn.apimastervolley.Activities.Model

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class PostResponse (
    val id: String,
    val createdAt: String
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<PostResponse>(json)
    }
}
