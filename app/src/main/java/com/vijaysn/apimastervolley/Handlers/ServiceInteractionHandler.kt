package com.vijaysn.apimastervolley.Handlers

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.android.volley.Request
import com.android.volley.Response
import com.vijaysn.apimastervolley.Helpers.Loader
import org.json.JSONObject
import java.io.UnsupportedEncodingException

//region Service Interaction - API Calls
class ServiceInteraction {

    companion object {

        fun getAPICall(url: String,foregroundAPICall:Boolean, context: Context, listener: ServiceInteractionListener) {
            try {

                if (foregroundAPICall) {
                    if (!Reachability.isInternetAvailableAcknowledge(
                            context
                        )
                    ) {
                        return
                    }
                    Loader.show(context)
                } else {
                    if (!Reachability.isInternetAvailable(
                            context
                        )
                    ) {
                        return
                    }
                }


                val queue = Volley.newRequestQueue(context)
                val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                    Response.Listener { response ->
                        Loader.hide(context)
                        listener.onResponse(response)
                    }, Response.ErrorListener { error ->
                        Loader.hide(context)
                        listener.onError(error.toString())
                    })
                queue.add(jsonObjectRequest)

            } catch (e: Exception) {
                Log.d("Volley-Get", e.toString())
            }
        }

        fun postAPICall(url: String,foregroundAPICall: Boolean, context: Context, requestBody: JSONObject, listener: ServiceInteractionListener) {
            try {

                if (foregroundAPICall) {
                    if (!Reachability.isInternetAvailableAcknowledge(
                            context
                        )
                    ) {
                        return
                    }
                    Loader.show(context)
                } else {
                    if (!Reachability.isInternetAvailable(
                            context
                        )
                    ) {
                        return
                    }
                }

                val queue = Volley.newRequestQueue(context)
                val jsonObjectRequest = object :
                    JsonObjectRequest(Request.Method.POST, url, null,
                        Response.Listener { response ->
                            Loader.hide(context)
                            listener.onResponse(response)
                        }, Response.ErrorListener { error ->
                            Loader.hide(context)
                            listener.onError(error.toString())
                        }) {

                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-Type"] = "application/json"
                        //headers[Constants.URLs.HEADER_KEY] = Constants.URLs.HEADER_VALUE
                        return headers
                    }



                    override fun getBody(): ByteArray? {
                        try {
                            val requestBodyString = requestBody.toString()
                            return requestBodyString.toByteArray(charset("utf-8"))
                        } catch (e: UnsupportedEncodingException) {
                            Log.d("APIHandlerVolley", e.toString())
                            return null
                        }
                    }
                }
                queue.add(jsonObjectRequest)
            } catch (e: Exception) {
                Log.d("Volley-Post", e.toString())
            }
        }

        fun putAPICall(url: String,foregroundAPICall: Boolean, context: Context, requestBody: JSONObject, listener: ServiceInteractionListener) {
            try {


                if (foregroundAPICall) {
                    if (!Reachability.isInternetAvailableAcknowledge(
                            context
                        )
                    ) {
                        return
                    }
                    Loader.show(context)
                } else {
                    if (!Reachability.isInternetAvailable(
                            context
                        )
                    ) {
                        return
                    }
                }

                val queue = Volley.newRequestQueue(context)
                val jsonObjectRequest = object :
                    JsonObjectRequest(Request.Method.PUT, url, null,
                        Response.Listener { response ->
                            Loader.hide(context)
                            listener.onResponse(response)
                        }, Response.ErrorListener { error ->
                            Loader.hide(context)
                            listener.onError(error.toString())
                        }) {

                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-Type"] = "application/json"
                        return headers
                    }

                    override fun getBody(): ByteArray? {
                        try {
                            val requestBodyString = requestBody.toString()
                            return requestBodyString.toByteArray(charset("utf-8"))
                        } catch (e: UnsupportedEncodingException) {
                            Log.d("APIHandlerVolley", e.toString())
                            return null
                        }
                    }
                }
                queue.add(jsonObjectRequest)
            } catch (e: Exception) {
                Log.d("Volley-Post", e.toString())
            }
        }

        fun deleteAPICall(url: String,foregroundAPICall: Boolean, context: Context, requestBody: JSONObject, listener: ServiceInteractionListener) {
            try {


                if (foregroundAPICall) {
                    if (!Reachability.isInternetAvailableAcknowledge(
                            context
                        )
                    ) {
                        return
                    }
                    Loader.show(context)
                } else {
                    if (!Reachability.isInternetAvailable(
                            context
                        )
                    ) {
                        return
                    }
                }

                val queue = Volley.newRequestQueue(context)
                val jsonObjectRequest = object :
                    JsonObjectRequest(Request.Method.DELETE, url, null,
                        Response.Listener { response ->
                            Loader.hide(context)
                            listener.onResponse(response)
                        }, Response.ErrorListener { error ->
                            Loader.hide(context)
                            listener.onError(error.toString())
                        }) {

                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-Type"] = "application/json"
                        return headers
                    }

                    override fun getBody(): ByteArray? {
                        try {
                            val requestBodyString = requestBody.toString()
                            return requestBodyString.toByteArray(charset("utf-8"))
                        } catch (e: UnsupportedEncodingException) {
                            Log.d("APIHandlerVolley", e.toString())
                            return null
                        }
                    }
                }
                queue.add(jsonObjectRequest)
            } catch (e: Exception) {
                Log.d("Volley-Post", e.toString())
            }
        }

    }

}
//endregion

//region Service Interaction Listener - Response and Message
interface ServiceInteractionListener {
    fun onError(message: String)
    fun onResponse(response: JSONObject)
}
//endregion