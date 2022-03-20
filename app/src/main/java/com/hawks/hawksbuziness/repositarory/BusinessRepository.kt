package com.hawks.hawksbuziness.repositarory

import android.util.Log
import com.hawks.hawksbuziness.model.country.Country
import com.hawks.hawksbuziness.model.SignUp
import com.hawks.hawksbuziness.model.category.Categories
import com.hawks.hawksbuziness.model.languages.Language
import com.hawks.hawksbuziness.model.otp.send.Sendotp
import com.hawks.hawksbuziness.model.otp.verify.VerifyOtp
import com.hawks.hawksbuziness.model.places.Places
import com.hawks.hawksbuziness.model.profile.profile
import com.hawks.hawksbuziness.model.shop.ShopDetails
import com.hawks.hawksbuziness.model.shop.add.AddShop
import com.hawks.hawksbuziness.model.shop.update.UpdateData
import com.hawks.hawksbuziness.model.shops.Shops
import com.hawks.hawksbuziness.model.ticket.Ticket
import com.hawks.hawksbuziness.model.web.Web
import com.hawks.hawksbuziness.services.AuthApi
import com.hawks.hawksbuziness.utill.BaseApiResponse
import com.hawks.hawksbuziness.utill.ResponceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BusinessRepository @Inject constructor(private val authApi: AuthApi) : BaseApiResponse() {

    fun getData(): Flow<ResponceState<SignUp>> {
        return flow<ResponceState<SignUp>> {
            emit(ResponceState.Loading("Loading"))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCountries(): Flow<ResponceState<Country>> {
        return flow<ResponceState<Country>> {
            emit(ResponceState.Loading("Loadng"))
            emit(safeApi { authApi.getContries() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getLanguages(): Flow<ResponceState<Language>> {
        return flow<ResponceState<Language>> {
            emit(ResponceState.Loading("Loadng"))
            emit(safeApi { authApi.getLanguages() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getWebData(): Flow<ResponceState<Web>> {
        return flow<ResponceState<Web>> {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.getWeb() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProfile(id: String): Flow<ResponceState<profile>> {
        return flow {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi {
                authApi.getProfile(id)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun raiseTicket(
        id: String,
        title: String,
        message: String
    ): Flow<ResponceState<Ticket>> {
        return flow {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi {
                authApi.raiseTicket(id, title, message)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getShops(use_id: String): Flow<ResponceState<Shops>> {
        return flow<ResponceState<Shops>> {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.getShops(use_id) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getShop(shopId: String): Flow<ResponceState<ShopDetails>> {
        return flow<ResponceState<ShopDetails>> {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.getShop(shopId) })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getCategories(): Flow<ResponceState<Categories>> {

        return flow<ResponceState<Categories>> {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.getCategories() })

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPlaces(): Flow<ResponceState<Places>> {

        return flow<ResponceState<Places>> {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.getPlaces() })

        }.flowOn(Dispatchers.IO)
    }

    suspend fun addShop(hashMap: HashMap<String, String>): Flow<ResponceState<AddShop>> {
        return flow {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.addBusiness(hashMap) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateShop(hashMap: HashMap<String, String>): Flow<ResponceState<UpdateData>> {
        return flow {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.updateBusiness(hashMap) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun sendOtp(number: String): Flow<ResponceState<Sendotp>> {
        Log.e("TAG", "sendOtp: " + number)
        return flow {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.sendOtp(number, "MOBILE") })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun verifyOtp(otp: String, userId: String): Flow<ResponceState<VerifyOtp>> {
        return flow {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.verifyOtp(userId, otp) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateProfile(hashMap: HashMap<String, String>): Flow<ResponceState<profile>> {
        return flow<ResponceState<profile>> {
            emit(ResponceState.Loading("Loading"))
            emit(safeApi { authApi.updateProfile(hashMap) })
        }.flowOn(Dispatchers.IO)
    }

}
