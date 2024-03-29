// Generated by Dagger (https://dagger.dev).
package com.hawks.hawksbuziness.di;

import com.hawks.hawksbuziness.services.AuthApi;
import com.hawks.hawksbuziness.services.RemoteDatasource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class Networkmodule_ProvideServiceFactory implements Factory<AuthApi> {
  private final Provider<RemoteDatasource> remoteDatasourceProvider;

  public Networkmodule_ProvideServiceFactory(Provider<RemoteDatasource> remoteDatasourceProvider) {
    this.remoteDatasourceProvider = remoteDatasourceProvider;
  }

  @Override
  public AuthApi get() {
    return provideService(remoteDatasourceProvider.get());
  }

  public static Networkmodule_ProvideServiceFactory create(
      Provider<RemoteDatasource> remoteDatasourceProvider) {
    return new Networkmodule_ProvideServiceFactory(remoteDatasourceProvider);
  }

  public static AuthApi provideService(RemoteDatasource remoteDatasource) {
    return Preconditions.checkNotNullFromProvides(Networkmodule.INSTANCE.provideService(remoteDatasource));
  }
}
