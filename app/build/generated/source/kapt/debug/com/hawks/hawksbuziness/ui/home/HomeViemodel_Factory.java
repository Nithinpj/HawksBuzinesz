// Generated by Dagger (https://dagger.dev).
package com.hawks.hawksbuziness.ui.home;

import com.hawks.hawksbuziness.local.dao.CategoryDao;
import com.hawks.hawksbuziness.repositarory.BusinessRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class HomeViemodel_Factory implements Factory<HomeViemodel> {
  private final Provider<BusinessRepository> repositoryProvider;

  private final Provider<CategoryDao> daoProvider;

  public HomeViemodel_Factory(Provider<BusinessRepository> repositoryProvider,
      Provider<CategoryDao> daoProvider) {
    this.repositoryProvider = repositoryProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public HomeViemodel get() {
    return newInstance(repositoryProvider.get(), daoProvider.get());
  }

  public static HomeViemodel_Factory create(Provider<BusinessRepository> repositoryProvider,
      Provider<CategoryDao> daoProvider) {
    return new HomeViemodel_Factory(repositoryProvider, daoProvider);
  }

  public static HomeViemodel newInstance(BusinessRepository repository, CategoryDao dao) {
    return new HomeViemodel(repository, dao);
  }
}