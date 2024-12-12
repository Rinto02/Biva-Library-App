package com.bivalibrary.app.screens.employee.orderstatus

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.bivalibrary.app.data.DataOrException
import com.bivalibrary.app.models.MOrder
import com.bivalibrary.app.repository.FireOrderStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderStatusEmpViewModel @Inject constructor(private val fireOrderStatusRepository: FireOrderStatusRepository): ViewModel() {

    val fireStatus: MutableState<DataOrException<List<MOrder>, Boolean, Exception>> = mutableStateOf(
        DataOrException(listOf(), false, Exception(""))
    )

    val db = FirebaseFirestore.getInstance()

    init {
        getOrderStatusFromFB()
    }

    private fun getOrderStatusFromFB(){
        viewModelScope.launch {

            fireStatus.value = fireOrderStatusRepository.getOrderStatusFromFB()
        }
    }

    fun markOnTheWay(userId: String,product_title: String,success:() -> Unit){
        viewModelScope.launch {
            db.collection("Orders").document(userId + product_title).update("delivery_status","On The Way").addOnSuccessListener {
                success()
            }
        }
    }

    fun markDelivered(userId: String,product_title: String,success:() -> Unit){
        viewModelScope.launch {
            db.collection("Orders").document(userId + product_title).update("delivery_status","Delivered").addOnSuccessListener {
                success()
            }
        }
    }
}