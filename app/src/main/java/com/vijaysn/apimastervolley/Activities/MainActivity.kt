 package com.vijaysn.apimastervolley.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.vijaysn.apimastervolley.Activities.Model.GetResponse
import com.vijaysn.apimastervolley.Activities.Model.PostResponse
import com.vijaysn.apimastervolley.Activities.Model.PutResponse
import com.vijaysn.apimastervolley.Handlers.ServiceInteraction
import com.vijaysn.apimastervolley.Handlers.ServiceInteractionListener
import com.vijaysn.apimastervolley.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

 class MainActivity : AppCompatActivity() {

     val API_URL = "https://reqres.in/api/users"

    //region Activity Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialButtonSetup()
    }
    //endregion

     //region Initial View - Setup
     fun initialButtonSetup() {
         getAPIButton.setOnClickListener{
             getAPICall()
         }
         postAPIButton.setOnClickListener {
             postAPICall()
         }
         putAPIButton.setOnClickListener {
             putAPICall()
         }
         deleteAPIButton.setOnClickListener {
             deleteAPICall()
         }
     }
     //endregion

     //region Get API Call
     fun getAPICall(){
         try {
             ServiceInteraction.getAPICall(API_URL,true,this, object : ServiceInteractionListener {
                 override fun onError(message: String) {
                     Log.d("Get-Error",message)
                 }

                 override fun onResponse(response: JSONObject) {
                     handleGetResponse(response)
                 }
             })
         } catch (e: Exception) {
             Log.d("Get-Exception", e.toString())
         }
     }
     fun handleGetResponse(response:JSONObject) {
         try {
             val getResponse = GetResponse.fromJson(response.toString())
             processGetResponse(response = getResponse)
         } catch(e: Exception) {
             Log.d("Get-Parse-Exception",e.toString())
         }
     }
     fun processGetResponse(response:GetResponse?) {
         if (response != null) {
             Toast.makeText(this,"Successful - Get",Toast.LENGTH_SHORT).show()
         }
     }
     //endregion

     //region Post API Call
     fun postAPICall() {
         val jsonRequestObject = JSONObject()
         jsonRequestObject.put("Model","Rajesh")
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

     //region Put API Call
     fun putAPICall() {
         val jsonRequestObject = JSONObject()
         jsonRequestObject.put("Model","Rajesh")
         try {
             ServiceInteraction.putAPICall(API_URL,true, this, jsonRequestObject, object : ServiceInteractionListener {
                 override fun onError(message: String) {
                     Log.d("Put-Error", message)
                 }

                 override fun onResponse(response: JSONObject) {
                     handlePutResponse(response)
                 }
             })
         } catch (e: Exception) {
             Log.d("Put-Exception", e.toString())
         }
     }
     fun handlePutResponse(response:JSONObject) {
         try {
             val putResponse = PutResponse.fromJson(response.toString())
             processPutResponse(response = putResponse)
         } catch(e: Exception) {
             Log.d("Put-Parse-Exception",e.toString())
         }
     }
     fun processPutResponse(response:PutResponse?) {
         if (response != null) {
             Toast.makeText(this,"Successful - Put",Toast.LENGTH_SHORT).show()
         }
     }
     //endregion

     //region Delete API Call
     fun deleteAPICall() {
         val jsonRequestObject = JSONObject()
         jsonRequestObject.put("Model","Rajesh")
         try {
             ServiceInteraction.deleteAPICall(API_URL,true, this, jsonRequestObject, object : ServiceInteractionListener {
                 override fun onError(message: String) {
                     Log.d("Delete-Call", message)
                 }

                 override fun onResponse(response: JSONObject) {
                     handleDeleteResponse(response)
                 }
             })
         } catch (e: Exception) {
             Log.d("Delete-Call", e.toString())
         }
     }
     fun handleDeleteResponse(response:JSONObject) {
         Toast.makeText(this,"Successful-Delete Call",Toast.LENGTH_SHORT).show()
     }
     //endregion

}
