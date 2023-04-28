package com.shoppy.shopkart.screens.cart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shoppy.shopkart.data.DataOrException
import com.shoppy.shopkart.models.MCart
import com.shoppy.shopkart.repository.FireCartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(private val cartRepository: FireCartRepository): ViewModel() {

    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    val fireCart: MutableState<DataOrException<List<MCart>, Boolean, Exception>> =
        mutableStateOf(DataOrException(listOf(), true, Exception("")))

    init {
        getCartFromFireBase()
//        getPrice()
    }

    private fun getCartFromFireBase() {

        viewModelScope.launch {

//            fireCart.value.loading = true
            fireCart.value = cartRepository.getCartFromFireBase()

            if (!fireCart.value.data.isNullOrEmpty()) fireCart.value.loading = false
        }
//        Log.d("FIREDATA", "getRefrigeratorFromFB: ${fireCart.value.data?.toList()}")
    }

    //counter:MutableState<Int>
    fun updateCounter(productTitle: String, updatedVal: Int) {
        viewModelScope.launch {

            val db = FirebaseFirestore.getInstance()

            db.collection("Cart").document(userId + productTitle).update("item_count", updatedVal)
        }
    }

    fun deleteItem(productTitle: String) {
        viewModelScope.launch {

            val db = FirebaseFirestore.getInstance()

            db.collection("Cart").document(userId + productTitle).delete()
        }
    }

    fun sumValues(prices: List<Int>,totalAmount:(Int) -> Unit){

        viewModelScope.launch {

            delay(100)
            totalAmount(prices.sum())

//            Log.d("TOTAL", "sumValues: $totalAmount")
        }

    }
}