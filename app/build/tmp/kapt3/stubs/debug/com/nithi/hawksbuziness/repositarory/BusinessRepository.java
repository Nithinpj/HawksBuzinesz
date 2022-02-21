package com.nithi.hawksbuziness.repositarory;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/nithi/hawksbuziness/repositarory/BusinessRepository;", "", "authApi", "Lcom/nithi/hawksbuziness/services/AuthApi;", "(Lcom/nithi/hawksbuziness/services/AuthApi;)V", "getData", "Lkotlinx/coroutines/flow/Flow;", "Lcom/nithi/hawksbuziness/utill/ResponceState;", "Lcom/nithi/hawksbuziness/model/SignUp;", "app_debug"})
public final class BusinessRepository {
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
}