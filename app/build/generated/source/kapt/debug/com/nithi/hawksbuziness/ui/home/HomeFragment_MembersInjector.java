// Generated by Dagger (https://dagger.dev).
package com.nithi.hawksbuziness.ui.home;

import com.nithi.hawksbuziness.preferences.PreferenceManger;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class HomeFragment_MembersInjector implements MembersInjector<HomeFragment> {
  private final Provider<PreferenceManger> preferenceMangerProvider;

  public HomeFragment_MembersInjector(Provider<PreferenceManger> preferenceMangerProvider) {
    this.preferenceMangerProvider = preferenceMangerProvider;
  }

  public static MembersInjector<HomeFragment> create(
      Provider<PreferenceManger> preferenceMangerProvider) {
    return new HomeFragment_MembersInjector(preferenceMangerProvider);
  }

  @Override
  public void injectMembers(HomeFragment instance) {
    injectPreferenceManger(instance, preferenceMangerProvider.get());
  }

  @InjectedFieldSignature("com.nithi.hawksbuziness.ui.home.HomeFragment.preferenceManger")
  public static void injectPreferenceManger(HomeFragment instance,
      PreferenceManger preferenceManger) {
    instance.preferenceManger = preferenceManger;
  }
}