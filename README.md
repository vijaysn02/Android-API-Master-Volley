# API Master - Volley

## Introduction:

This project is created to understand the working of API Master - Volley and also to have a ready made component for integration in the projects.

If you want to implement it straight away, you can make copy the handler in the project and jump to the Usage part.

---------------------------------------------------------------------------------------------------

## Installation:

Include the below dependences in build.gradle for implementing Volley and Klaxon library in your project.

```
implementation 'com.android.volley:volley:1.1.1'
implementation 'com.beust:klaxon:5.0.1'
```


----------------------------------------------------------------------------------------------------

## Configuration:

Make sure to include below permissions in Android manifest file

```
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
```


----------------------------------------------------------------------------------------------------

## Coding Part - Handler:

There are two important section of this handler. (i) Service Interaction and Interface Listener

### Service Interaction - GET

```
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
```

### Service Interaction - POST

```
fun postAPICall(url: String,foregroundAPICall: Boolean, context: Context, requestBody: String, listener: ServiceInteractionListener) {
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
                    return requestBody.toByteArray(charset("utf-8"))
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
```

### Service Interaction - PUT

```
fun putAPICall(url: String,foregroundAPICall: Boolean, context: Context, requestBody: String, listener: ServiceInteractionListener) {
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
                    return requestBody.toByteArray(charset("utf-8"))
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
```

### Service Interaction - DELETE

```
fun deleteAPICall(url: String,foregroundAPICall: Boolean, context: Context, requestBody: String, listener: ServiceInteractionListener) {
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
                    return requestBody.toByteArray(charset("utf-8"))
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
```

### Interface Listener

```
    interface ServiceInteractionListener {
        fun onError(message: String)
        fun onResponse(response: JSONObject)
    }
```


----------------------------------------------------------------------------------------------------

## Helper Part

### Loader is used for showing the activity indicator (or) progress bar while doing API calls.
### NetworkReachability Handler is used for checking Internet connectivity.

----------------------------------------------------------------------------------------------------

## Usage Part

### Invoke the below set of functions for handling and processing API calls. Given the example of Post Request.
### Check out more implementation in MainActivity.kt

```
fun postAPICall() {

 val userRequest = UserRequest("Rajesh","Model")
 val jsonRequestObject = userRequest.toJson()

 try {
     ServiceInteraction.postAPICall(API_URL,false, this, jsonRequestObject, object : ServiceInteractionListener {
         override fun onError(message: String) {
             Log.d("Post-Error", message)
         }

         override fun onResponse(response: JSONObject) {
             handlePostResponse(response)
         }
     })
 } catch (e: Exception) {
     Log.d("Post-Exception", e.toString())
 }
}
fun handlePostResponse(response:JSONObject) {
 try {
     val loginResponse = PostResponse.fromJson(response.toString())
     processPostResponse(response = loginResponse)
 } catch(e: Exception) {
     Log.d("Post-Parse-Exception",e.toString())
 }
}
fun processPostResponse(response:PostResponse?) {
 if (response != null) {
     Toast.makeText(this,"Successful - Post",Toast.LENGTH_SHORT).show()
 }
}
//endregion
```


### Check out my Post about API Master - Volley : [API Master - Volley](https://vijaysn.com/2020/04/23/ios-av-player/)
