package com.bivalibrary.app.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.bivalibrary.app.data.DataOrException
import com.bivalibrary.app.models.MProducts
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireSearchRepository @Inject constructor(private val querySearch:Query) {

    suspend fun getSearchResultFromFirebase(): DataOrException<List<MProducts>, Boolean, Exception>{

        val dataOrException = DataOrException<List<MProducts>, Boolean, Exception>()

        try {

            dataOrException.data = querySearch.get().await().map { documentSnapshot ->

                documentSnapshot.toObject(MProducts::class.java)
            }

        }catch (ex: FirebaseFirestoreException){
            dataOrException.e = ex
        }
        return dataOrException
    }
}