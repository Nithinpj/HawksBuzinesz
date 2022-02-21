package com.nithi.hawksbuziness.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001a\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u00062\b\b\u0001\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/nithi/hawksbuziness/di/SharedPreferenceModule;", "", "()V", "provideSessionManager", "Lcom/nithi/hawksbuziness/preferences/PreferenceManger;", "sharedPreferences", "Landroid/content/SharedPreferences;", "provideSharedPreference", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "app_debug"})
@dagger.Module()
public final class SharedPreferenceModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.nithi.hawksbuziness.di.SharedPreferenceModule INSTANCE = null;
    
    private SharedPreferenceModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    public final android.content.SharedPreferences provideSharedPreference(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.nithi.hawksbuziness.preferences.PreferenceManger provideSessionManager(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences) {
        return null;
    }
}