package com.hotroso.autopay.workers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.hotroso.autopay.models.XInfo
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class PushingWorker(mContext: Context, workerParameters: WorkerParameters) : Worker(mContext, workerParameters) {
    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        val json = inputData.getString("xx_data")
        val info = Gson().fromJson(json, XInfo::class.java)

        Log.d("XXX", "${info.getPck()} :: ${info.getTitle()}")

        if (json != null) {
            httpPost("https://api.danhsach.tech/xnoti.php", json);
        }

        return Result.Success()
    }

    private fun httpPost(myUrl: String, jsonObject: String) {
        val url = URL(myUrl)
        // 1. create HttpURLConnection
        val conn = url.openConnection() as HttpsURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")

        // 3. add JSON content to POST request body
        val os = conn.outputStream
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(jsonObject)
        writer.flush()
        writer.close()
        os.close()

        // 4. make POST request to the given URL
        conn.connect()

        // 5. return response message
        conn.responseMessage + ""
    }

}