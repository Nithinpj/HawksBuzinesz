package com.nithi.hawksbuziness.services

import com.nithi.hawksbuziness.model.country.Country
import com.nithi.hawksbuziness.model.languages.Language
import com.nithi.hawksbuziness.model.profile.profile
import com.nithi.hawksbuziness.model.shops.Shops
import com.nithi.hawksbuziness.model.ticket.Ticket
import com.nithi.hawksbuziness.model.web.Web
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @GET("common/getcountries")
    suspend fun getContries(): Country

    @GET("common/getlanguages")
    suspend fun getLanguages(): Language

    @GET("common/getweburl")
    suspend fun getWeb(): Web

    @FormUrlEncoded
    @POST("common/getprofile")
    suspend fun getProfile(@Field("user_id") id: String): profile

    @FormUrlEncoded
    @POST("common/raiseticket")
    suspend fun raiseTicket(
        @Field("user_id") id: String,
        @Field("title") title: String,
        @Field("message") message: String
    ): Ticket

    @FormUrlEncoded
    @POST("common/getshops")
    suspend fun getShops(@Field("user_id")user_id:String):Shops
}