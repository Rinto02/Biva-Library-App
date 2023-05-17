package com.shoppy.shopkart.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.shoppy.shopkart.data.DataOrException
import com.shoppy.shopkart.models.MProducts
import com.shoppy.shopkart.models.MSliders
import com.shoppy.shopkart.models.UserData
import com.shoppy.shopkart.navigation.BottomNavScreens
import com.shoppy.shopkart.repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fireRepositorySlider: FireRepository.FireRepositorySliders,
                                        private val fireRepository: FireRepository.FireRepositoryBestSeller,
                                        private val fireRepository2: FireRepository.FireRepositoryMobilePhones,
                                        private val fireRepository3: FireRepository.FireRepositoryTv,
                                        private val fireRepository4: FireRepository.FireRepositoryEarphones): ViewModel() {

    //data with wrapper DataOrException
    val fireDataSlider: MutableState<DataOrException<List<MSliders>, Boolean, Exception>> = mutableStateOf(DataOrException(listOf(), true, Exception("")))
    val fireDataBS: MutableState<DataOrException<List<MProducts>, Boolean, Exception>> = mutableStateOf(DataOrException(listOf(), true, Exception("")))
    val fireDataMP: MutableState<DataOrException<List<MProducts>, Boolean, Exception>> = mutableStateOf(DataOrException(listOf(), true, Exception("")))
    val fireDataTv: MutableState<DataOrException<List<MProducts>, Boolean, Exception>> = mutableStateOf(DataOrException(listOf(), true, Exception("")))
    val fireDataEp: MutableState<DataOrException<List<MProducts>, Boolean, Exception>> = mutableStateOf(DataOrException(listOf(), true, Exception("")))


    //Pull to Refresh
    private val _isLoading = mutableStateOf(false)
    val isLoading = _isLoading

    private val mAuth = FirebaseAuth.getInstance()

    val currentUser = mAuth.currentUser!!.uid

    init {
        getSlidersFromFB()
        getBestSellerFromFB()
        getMobilePhonesFromFB()
        getTvFromFB()
        getEarphonesFromFB()
//        delete()
    }

    fun getUserNameAndImage(profile_image: (String?) -> Unit,user: (String?) -> Unit) {

        FirebaseFirestore.getInstance().collection("Users").document(currentUser).get()
                .addOnSuccessListener { document ->
                    user(document.data!!.getValue("name").toString())
                    profile_image(document.data!!.getValue("profile_image").toString())
                }

//        user(mAuth.currentUser?.displayName)
//        profile_image(mAuth.currentUser?.photoUrl.toString())

    }

//    fun getSliders(except: (String) -> Unit,sliders: (List<Any?>) -> Unit) {
//
//        sliders(listOf(
//        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
//        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
//        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
//        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
//        ))
//
//        viewModelScope.launch {
//
//            try {
//                FirebaseFirestore.getInstance().collection("Sliders").document("sliders").get()
//                    .addOnSuccessListener { document ->
//                        sliders(document.data!!.values.toList())
//
////                        Log.d("TAGGS", "getSliders: ${document.toObject(MSliders::class.java)}")
//                    }
//            }catch (ex: Exception){
//                except(ex.message.toString())
//            }
//        }
//    }

    //Getting Sliders From Firebase
    private fun getSlidersFromFB(){

        viewModelScope.launch {
            fireDataSlider.value.loading = true
            fireDataSlider.value = fireRepositorySlider.getSlidersFromFB()

            if (!fireDataSlider.value.data.isNullOrEmpty()) fireDataSlider.value.loading = false

        }
//        Log.d("FIREDATA", "getRefrigeratorFromFB: ${fireDataRf.value.data?.toList()}")
    }

    //Getting Products from Firebase
    private fun getBestSellerFromFB(){

        viewModelScope.launch {
            fireDataBS.value.loading = true
            fireDataBS.value = fireRepository.getBestSellerFromFB()

            if (!fireDataBS.value.data.isNullOrEmpty()) fireDataBS.value.loading = false

        }
//        Log.d("FIREDATA", "getAllProductsFromFB: ${fireDataBS.value.data?.toList()}")
    }

    private fun getMobilePhonesFromFB(){

        viewModelScope.launch {
            fireDataMP.value.loading = true
            fireDataMP.value = fireRepository2.getMobilePhonesFromFB()

            if (!fireDataMP.value.data.isNullOrEmpty()) fireDataMP.value.loading = false

        }
    }

    private fun getTvFromFB(){

        viewModelScope.launch {
            fireDataTv.value.loading = true
            fireDataTv.value = fireRepository3.getTvFromFB()

            if (!fireDataTv.value.data.isNullOrEmpty()) fireDataTv.value.loading = false

        }
//        Log.d("FIREDATA", "getTvFromFB: ${fireDataTv.value.data?.toList()}")
    }

    private fun getEarphonesFromFB(){

        viewModelScope.launch {
            fireDataEp.value.loading = true
            fireDataEp.value = fireRepository4.getEarphonesFromFB()

            if (!fireDataEp.value.data.isNullOrEmpty()) fireDataEp.value.loading = false

        }
//        Log.d("FIREDATA", "getRefrigeratorFromFB: ${fireDataRf.value.data?.toList()}")
    }

    fun pullToRefresh(navHostController: NavHostController){

//        val db = FirebaseFirestore.getInstance().collection("BestSeller")
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000L)
            navHostController.popBackStack()
            navHostController.navigate(BottomNavScreens.Home.route)
            delay(1000L)
            _isLoading.value = false
        }
    }

//    fun shuffleList(list1: List<MProducts>, list2: List<MProducts>, list3: List<MProducts>, list4: List<MProducts>){
//        list1.shuffled()
//        list2.shuffled()
//        list3.shuffled()
//        list4.shuffled()
//    }

//    fun delete(){
//        FirebaseFirestore.getInstance().collection("Earphones").document("petiCqe14SQZBvbyjj4V").delete()
//    }
}