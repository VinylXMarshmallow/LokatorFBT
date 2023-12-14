package eu.fundacjabt.lokator

import okhttp3.Call
import okhttp3.Callback
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response


class Http {
    private val client = OkHttpClient()

    fun HttpRequest() {
        val request = Request.Builder()
            .url("http://10.0.2.2:8000")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            println(response.body!!.string())
        }
    }
}