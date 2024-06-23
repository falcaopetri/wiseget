package com.example.readwisewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadwiseWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.readwise_widget)

        // Set up the refresh button to refresh the widget
        val intent = Intent(context, ReadwiseWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(appWidgetId))
        }

        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent)

        // Load quote data
        CoroutineScope(Dispatchers.IO).launch {
            val client = ReadwiseApiClient()
            val highlight = client.getRandomQuote()
            if (highlight != null) {
                withContext(Dispatchers.Main) {
                    views.setTextViewText(R.id.widget_text, highlight.text)
                    views.setTextViewText(R.id.widget_author, highlight.author)
                    views.setTextViewText(R.id.widget_title, highlight.title)

                    val appWidgetTarget = object : AppWidgetTarget(context, R.id.widget_image, views, appWidgetId) {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            super.onResourceReady(resource, transition)
                            appWidgetManager.updateAppWidget(appWidgetId, views)
                        }
                    }
                    Glide.with(context)
                        .asBitmap()
                        .load(highlight.image_url)
                        .into(appWidgetTarget)
                }
            }
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}
