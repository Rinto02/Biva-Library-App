package com.bivalibrary.app.network

import com.bivalibrary.app.BivaLibraryUtils
import com.bivalibrary.app.models.PushNotificationData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Singleton

interface NotificationApi {

//    @Singleton
    @Headers("Authorization: key = ${BivaLibraryUtils.SERVER_KEY}", "Content-Type: ${BivaLibraryUtils.CONTENT_TYPE}")
    @POST("fcm/send")
    suspend fun postNotification( @Body notification: PushNotificationData): Response<ResponseBody>
}