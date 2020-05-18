 package com.vijaysn.apimastervolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.vijaysn.apimastervolley.Handlers.ServiceInteraction
import com.vijaysn.apimastervolley.Handlers.ServiceInteractionListener
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
                     Log.d("Get-Call",message)
                 }

                 override fun onResponse(response: JSONObject) {
                     handleGetResponse(response)
                 }
             })
         } catch (e: Exception) {
             Log.d("Get-Call", e.toString())
         }
     }
     fun handleGetResponse(response: JSONObject) {
         //processGetResponse(response)
         Log.d("Get-Call",response.toString())
         Toast.makeText(this,"Successful-Get Call",Toast.LENGTH_SHORT).show()
     }
     //fun processGetResponse(response:GetResponse) {
     //
     //}
     //endregion

     //region Post API Call
     fun postAPICall() {
         val jsonRequestObject = JSONObject()
         jsonRequestObject.put("Model","Rajesh")
         try {
             ServiceInteraction.postAPICall(API_URL,false, this, jsonRequestObject, object : ServiceInteractionListener {
                 override fun onError(message: String) {
                     Log.d("Post-Call", message)
                 }

                 override fun onResponse(response: JSONObject) {
                     handlePostResponse(response)
                 }
             })
         } catch (e: Exception) {
             Log.d("Put-Call", e.toString())
         }
     }
     fun handlePostResponse(response: JSONObject) {
         //processPostResponse(response)
         Log.d("Post-Call",response.toString())
         Toast.makeText(this,"Successful-Post Call",Toast.LENGTH_SHORT).show()
     }
     //fun processPostResponse(response:GetResponse) {
     //
     //}
     //endregion

     //region Put API Call
     fun putAPICall() {
         val jsonRequestObject = JSONObject()
         jsonRequestObject.put("Model","Rajesh")
         try {
             ServiceInteraction.putAPICall(API_URL,false, this, jsonRequestObject, object : ServiceInteractionListener {
                 override fun onError(message: String) {
                     Log.d("Put-Call", message)
                 }

                 override fun onResponse(response: JSONObject) {
                     handlePutResponse(response)
                 }
             })
         } catch (e: Exception) {
             Log.d("Put-Call", e.toString())
         }
     }
     fun handlePutResponse(response: JSONObject) {
         //processGetResponse(response)
         Toast.makeText(this,"Successful-Put Call",Toast.LENGTH_SHORT).show()
     }
     //fun processGetResponse(response:GetResponse) {
     //
     //}
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
