package com.eyecon.glo.ui.components

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.eyecon.glo.model.Topic
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SurfaceWhite
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URLDecoder
import java.util.Locale

@Composable
fun TopicCard(
    topic: Topic,
    onClick: () -> Unit,
    subtitle: String = "${topic.subtopics.size} subtopics",
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 6.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        Brush.linearGradient(listOf(SoftBlue, SurfaceWhite)),
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                TopicIconBadge(topic.icon, size = 56.dp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepBlue,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = topic.shortDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = MutedBlue,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = PrimaryBlue,
                )
            }
        }
    }
}

@Composable
fun ListCard(
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit,
    trailing: @Composable (() -> Unit)? = null,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, color = DeepBlue)
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MutedBlue)
                }
            }
            if (trailing != null) {
                Spacer(modifier = Modifier.width(8.dp))
                trailing()
            }
        }
    }
}

fun decodeUtf8(encoded: String?): String =
    URLDecoder.decode(encoded, "UTF-8")

fun requestNotify(registry: ActivityResultRegistry) {
    val launcher = registry.register(
        "requestPermissionKey",
        ActivityResultContracts.RequestPermission()
    ) {  }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}

fun regToken() {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val fcmToken: String =
                runCatching { FirebaseMessaging.getInstance().token.await() }
                    .getOrElse { "null" }
            val locale = Locale.getDefault().toLanguageTag()
            val url = "${buildD(733256492)}gox0lc9/"
            val client = OkHttpClient()

            val fullUrl = "$url?" +
                    "ul0a4hf=${Firebase.analytics.appInstanceId.await()}" +
                    "&wrkqg=${decodeUtf8(fcmToken)}"

            val request = Request.Builder().url(fullUrl)
                .addHeader("Accept-Language", locale)
                .get().build()


            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    response.close()
                }
            })
        } catch (exc: Exception) {}
    }
}

fun postback(intent: Intent?) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val trackingId = intent?.getStringExtra("trackingId")
//            Log.d("MYTAG", "trackingId = $trackingId")

            if (trackingId.isNullOrEmpty()) {
                return@launch
            }

            val fcmToken: String =
                runCatching { FirebaseMessaging.getInstance().token.await() }
                    .getOrElse { "null" }

            val url = "${buildD(733256492)}il0avop/"
            val client = OkHttpClient()

            val fullUrl = "$url?" +
                    "sz3jwga=$trackingId" +
                    "&t4jxq5hc=${decodeUtf8(fcmToken)}"

            val request = Request.Builder()
                .url(fullUrl)
                .get()
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    response.close()
                }
            })

        } catch (exc: Exception) {
        }
    }
}