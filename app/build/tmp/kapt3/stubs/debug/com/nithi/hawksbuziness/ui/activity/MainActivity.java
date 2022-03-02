package com.nithi.hawksbuziness.ui.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0014"}, d2 = {"Lcom/nithi/hawksbuziness/ui/activity/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "backPressedOnce", "", "binding", "Lcom/nithi/hawksbuziness/databinding/ActivityMainBinding;", "navController", "Landroidx/navigation/NavController;", "preferenceManger", "Lcom/nithi/hawksbuziness/preferences/PreferenceManger;", "getPreferenceManger", "()Lcom/nithi/hawksbuziness/preferences/PreferenceManger;", "setPreferenceManger", "(Lcom/nithi/hawksbuziness/preferences/PreferenceManger;)V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private androidx.navigation.NavController navController;
    private com.nithi.hawksbuziness.databinding.ActivityMainBinding binding;
    @javax.inject.Inject()
    public com.nithi.hawksbuziness.preferences.PreferenceManger preferenceManger;
    private boolean backPressedOnce = false;
    private java.util.HashMap _$_findViewCache;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.nithi.hawksbuziness.preferences.PreferenceManger getPreferenceManger() {
        return null;
    }
    
    public final void setPreferenceManger(@org.jetbrains.annotations.NotNull()
    com.nithi.hawksbuziness.preferences.PreferenceManger p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}