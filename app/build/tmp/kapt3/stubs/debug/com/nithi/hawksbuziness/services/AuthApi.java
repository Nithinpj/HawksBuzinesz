package com.nithi.hawksbuziness.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\t"}, d2 = {"Lcom/nithi/hawksbuziness/services/AuthApi;", "", "getContries", "Lcom/nithi/hawksbuziness/model/country/Country;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLanguages", "Lcom/nithi/hawksbuziness/model/languages/Language;", "getWeb", "Lcom/nithi/hawksbuziness/model/web/Web;", "app_debug"})
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
}