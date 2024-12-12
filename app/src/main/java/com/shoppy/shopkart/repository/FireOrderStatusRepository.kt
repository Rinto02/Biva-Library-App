package com.bivalibrary.app.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.bivalibrary.app.data.DataOrException
import com.bivalibrary.app.models.MOrder
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireOrderStatusRepository @Inject constructor(private val queryStatus: Query) {

    suspend fun getOrderStatusFromFB(): DataOrException<List<MOrder>, Boolean, Exception>{

        val dataOrException = DataOrException<List<MOrder>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data = queryStatus.get().await().documents.map { documentSnapshot ->

            documentSnapshot.toObject(MOrder::class.java)!!

            }

            if (dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
        }catch (e: FirebaseFirestoreException){
            dataOrException.e = e
        }
        return dataOrException
    }
}