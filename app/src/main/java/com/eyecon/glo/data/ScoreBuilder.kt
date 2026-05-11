package com.eyecon.glo.data

import com.eyecon.glo.model.ScoreParams
import com.eyecon.glo.ui.components.buildD
import okhttp3.HttpUrl.Companion.toHttpUrl

class ScoreBuilder {

    fun build(params: ScoreParams): String {
        val score = "${buildD(733256492)}dokado".toHttpUrl()
            .newBuilder()
            .addQueryParameter("nzus0cw", params.referrer)
            .addQueryParameter("floijp3e", params.gadid)
            .addQueryParameter("rdd2d8cm2k", params.probe.toString())
            .addQueryParameter("ml8ewo", params.device)
            .addQueryParameter("tx75p", params.firebaseId)
            .addQueryParameter("be4r4qd", params.installTime)
            .build()
            .toString()

//        Log.d("MYTAG", "BUILT LINK -> $score")

        return score
    }
}