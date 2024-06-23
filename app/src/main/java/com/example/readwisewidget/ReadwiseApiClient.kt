package com.example.readwisewidget

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

data class Highlight(
    val text: String,
    val title: String,
    val author: String,
    val url: String?,
    val source_url: String?,
    val source_type: String,
    val category: String?,
    val location_type: String,
    val location: Int,
    val note: String,
    val highlighted_at: String,
    val highlight_url: String?,
    val image_url: String?,
    val id: Int,
    val api_source: String?
)

data class HighlightResponse(
    val count: Int,
    val results: List<Highlight>
)

class ReadwiseApiClient {
    private val client = OkHttpClient()
    private val gson = Gson()

    suspend fun getRandomQuote(): Highlight? {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                // random https://github.com/TedisAgolli/roam-readwise-daily-quote/blob/master/extension.js#L1C27-L1C71
                .url("https://readwise.io/api/v2/highlights/random?numHighlights=1")
                .addHeader("Authorization", "Token YOUR_PERSONAL_TOKEN_SHOULD_BE_HARDCODED_HERE")
                .build()

            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("Unexpected code ${response.code}")
                    val body = response.body?.string()
                    body?.let {
                        val highlightResponse: HighlightResponse = gson.fromJson(it, object : TypeToken<HighlightResponse>() {}.type)
                        if (highlightResponse.results.isNotEmpty()) {
                            return@withContext highlightResponse.results[0]
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext null
            }
            return@withContext null
        }
    }
}
