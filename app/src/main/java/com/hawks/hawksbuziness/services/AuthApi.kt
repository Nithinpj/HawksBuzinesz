package com.hawks.hawksbuziness.services

import com.hawks.hawksbuziness.model.category.Categories
import com.hawks.hawksbuziness.model.country.Country
import com.hawks.hawksbuziness.model.languages.Language
import com.hawks.hawksbuziness.model.otp.send.Sendotp
import com.hawks.hawksbuziness.model.otp.verify.VerifyOtp
import com.hawks.hawksbuziness.model.places.Places
import com.hawks.hawksbuziness.model.profile.profile
import com.hawks.hawksbuziness.model.shops.ShopDetails
import com.hawks.hawksbuziness.model.shop.add.AddShop
import com.hawks.hawksbuziness.model.shop.update.UpdateData
import com.hawks.hawksbuziness.model.shops.Shops
import com.hawks.hawksbuziness.model.ticket.Ticket
import com.hawks.hawksbuziness.model.web.Web
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("common/getshop")
    suspend fun getShop(@Field("shop_id")user_id:String): ShopDetails

    @GET("common/getcategories")
    suspend fun getCategories():Categories

    @GET("common/getplaces")
    suspend fun getPlaces():Places

    @POST("common/addbusiness")
    suspend fun addBusiness(@Body hashMap: HashMap<String,String>):AddShop

    @POST("common/updatebusiness")
    suspend fun updateBusiness(@Body hashMap: HashMap<String,String>):UpdateData

    @POST("common/updateprofile")
    suspend fun updateProfile(@Body hashMap: HashMap<String,String>):profile

    @FormUrlEncoded
    @POST("common/sendotp")
    suspend fun sendOtp(@Field("mobile")mobile:String,@Field("otp_via")type:String):Sendotp
    @FormUrlEncoded
    @POST("common/verifyotp")
    suspend fun verifyOtp(@Field("user_id")user_id:String,@Field("otp")otp:String):VerifyOtp
}