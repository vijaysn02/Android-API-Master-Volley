package com.vijaysn.apimastervolley.Activities.Model

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class PutResponse (
    val updatedAt: String
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<PutResponse>(json)
    }
}