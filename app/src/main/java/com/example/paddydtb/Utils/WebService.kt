package com.example.paddydtb.Utils

import android.content.Context
import android.util.Log
import com.google.android.gms.common.ConnectionResult.NETWORK_ERROR
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

object WebService {

    val NETWORK_ERROR = "Network Error. Please check your network connection."
    val INVALID_RESPONSE = "An Invalid Response Received."

    public fun sendImage(
        context: Context, urlImage: String,
        onCompletion: (status: Boolean, message: String, body: String) -> Unit
    ) {

        val json = MediaType.get("application/json; charset=utf-8")

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()

        val jsonObject1 = JSONObject()
        try {
            jsonObject1.put(
                "img_url", urlImage
            )

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val body = RequestBody.create(json, jsonObject1.toString())

        val request = Request.Builder()
            .url("http://192.168.1.103:5000/predict")
            .post(body)
            .build()

        val call = client.newCall(request)


        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("HttpService", "onFailure() Request was: $request")
                e.printStackTrace()
                onCompletion(false, NETWORK_ERROR, NETWORK_ERROR)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                try {

                    Log.e("HttpService", "--> " + response)
                    if (response.code() == 200) {
                        Log.e("HttpService", "Success")
                        val jsonData = response.body()?.string()
                        Log.e("HttpService", jsonData)
                        //val jsonResponseArray = JSONArray(jsonData)
                        // Log.e("HttpService", jsonResponseArray.toString())
                        if (jsonData != null) {
                            onCompletion(true, "SUCCESS", jsonData)
                        }

                    } else {
                        Log.e("HttpService", "!200")
                        val jsonData = response.body()?.string()
                        Log.e("HttpService", jsonData)
                        // val jsonResponseObject = JSONObject(jsonData)
                        if (jsonData != null) {
                            onCompletion(false, "error", jsonData)
                        }

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onCompletion(false, INVALID_RESPONSE, INVALID_RESPONSE)
                }

            }
        })

    }

}