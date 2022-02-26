package com.nithi.hawksbuziness.ui.home;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/nithi/hawksbuziness/ui/home/HomeViemodel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/nithi/hawksbuziness/repositarory/BusinessRepository;", "(Lcom/nithi/hawksbuziness/repositarory/BusinessRepository;)V", "_response", "Landroidx/lifecycle/MutableLiveData;", "Lcom/nithi/hawksbuziness/utill/ResponceState;", "Lcom/nithi/hawksbuziness/model/web/Web;", "get_response", "()Landroidx/lifecycle/MutableLiveData;", "getRepository", "()Lcom/nithi/hawksbuziness/repositarory/BusinessRepository;", "response", "Landroidx/lifecycle/LiveData;", "getResponse", "()Landroidx/lifecycle/LiveData;", "getWeb", "Lkotlinx/coroutines/Job;", "app_debug"})
public final class HomeViemodel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nithi.hawksbuziness.repositarory.BusinessRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.web.Web>> _response = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.web.Web>> response = null;
    
    @javax.inject.Inject()
    public HomeViemodel(@org.jetbrains.annotations.NotNull()
    com.nithi.hawksbuziness.repositarory.BusinessRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.nithi.hawksbuziness.repositarory.BusinessRepository getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.web.Web>> get_response() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.nithi.hawksbuziness.utill.ResponceState<com.nithi.hawksbuziness.model.web.Web>> getResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job getWeb() {
        return null;
    }
}