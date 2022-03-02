package com.nithi.hawksbuziness.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J/\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0013\u001a\u00020\n2\b\b\u0001\u0010\u0014\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/nithi/hawksbuziness/services/AuthApi;", "", "getContries", "Lcom/nithi/hawksbuziness/model/country/Country;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLanguages", "Lcom/nithi/hawksbuziness/model/languages/Language;", "getProfile", "Lcom/nithi/hawksbuziness/model/profile/profile;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getShops", "Lcom/nithi/hawksbuziness/model/shops/Shops;", "user_id", "getWeb", "Lcom/nithi/hawksbuziness/model/web/Web;", "raiseTicket", "Lcom/nithi/hawksbuziness/model/ticket/Ticket;", "title", "message", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AuthApi {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "common/getcountries")
    public abstract java.lang.Object getContries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nithi.hawksbuziness.model.country.Country> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "common/getlanguages")
    public abstract java.lang.Object getLanguages(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nithi.hawksbuziness.model.languages.Language> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "common/getweburl")
    public abstract java.lang.Object getWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nithi.hawksbuziness.model.web.Web> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "common/getprofile")
    @retrofit2.http.FormUrlEncoded()
    public abstract java.lang.Object getProfile(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "user_id")
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nithi.hawksbuziness.model.profile.profile> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "common/raiseticket")
    @retrofit2.http.FormUrlEncoded()
    public abstract java.lang.Object raiseTicket(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "user_id")
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "title")
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "message")
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nithi.hawksbuziness.model.ticket.Ticket> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "common/getshops")
    @retrofit2.http.FormUrlEncoded()
    public abstract java.lang.Object getShops(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "user_id")
    java.lang.String user_id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nithi.hawksbuziness.model.shops.Shops> continuation);
}