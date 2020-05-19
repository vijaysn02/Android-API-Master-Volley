package com.vijaysn.apimastervolley.Activities.Model

import com.beust.klaxon.*
import org.json.JSONObject

private val klaxon = Klaxon()

data class UserRequest (
    val name: String,
    val job: String
) {
    public fun toJson() = klaxon.toJsonString(this)
    public fun toJsonObject() = klaxon.parse<JSONObject>(klaxon.toJsonString(this))

    companion object {
        public fun fromJson(json: String) = klaxon.parse<UserRequest>(json)
    }
}