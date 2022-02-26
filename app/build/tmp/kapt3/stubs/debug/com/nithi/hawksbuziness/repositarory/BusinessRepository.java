package com.nithi.hawksbuziness.repositarory;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\u0006J\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/nithi/hawksbuziness/repositarory/BusinessRepository;", "Lcom/nithi/hawksbuziness/utill/BaseApiResponse;", "authApi", "Lcom/nithi/hawksbuziness/services/AuthApi;", "(Lcom/nithi/hawksbuziness/services/AuthApi;)V", "getCountries", "Lkotlinx/coroutines/flow/Flow;", "Lcom/nithi/hawksbuziness/utill/ResponceState;", "Lcom/nithi/hawksbuziness/model/country/Country;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getData", "Lcom/nithi/hawksbuziness/model/SignUp;", "getLanguages", "Lcom/nithi/hawksbuziness/model/languages/Language;", "getWebData", "Lcom/nithi/hawksbuziness/model/web/Web;", "app_debug"})
public final class BusinessRepository extends com.nithi.hawksbuziness.utill.BaseApiResponse {
    private final com.nithi.hawksbuziness.services.AuthApi authApi = null;
    
    @javax.inject.Inject()
    public BusinessRepository(@org.jetbrains.annotations.NotNull()
    com.nithi.hawksbuziness.services.AuthApi authApi) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.SignUp>> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCountries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.country.Country>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLanguages(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.languages.Language>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWebData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.web.Web>>> continuation) {
        return null;
    }
}