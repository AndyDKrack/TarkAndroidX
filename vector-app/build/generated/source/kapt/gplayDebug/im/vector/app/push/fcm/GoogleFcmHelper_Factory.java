package im.vector.app.push.fcm;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import im.vector.app.core.dispatchers.CoroutineDispatchers;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;

@ScopeMetadata
@QualifierMetadata({
    "dagger.hilt.android.qualifiers.ApplicationContext",
    "im.vector.app.core.di.DefaultPreferences"
})
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class GoogleFcmHelper_Factory implements Factory<GoogleFcmHelper> {
  private final Provider<Context> contextProvider;

  private final Provider<SharedPreferences> sharedPrefsProvider;

  private final Provider<CoroutineScope> appScopeProvider;

  private final Provider<CoroutineDispatchers> coroutineDispatchersProvider;

  public GoogleFcmHelper_Factory(Provider<Context> contextProvider,
      Provider<SharedPreferences> sharedPrefsProvider, Provider<CoroutineScope> appScopeProvider,
      Provider<CoroutineDispatchers> coroutineDispatchersProvider) {
    this.contextProvider = contextProvider;
    this.sharedPrefsProvider = sharedPrefsProvider;
    this.appScopeProvider = appScopeProvider;
    this.coroutineDispatchersProvider = coroutineDispatchersProvider;
  }

  @Override
  public GoogleFcmHelper get() {
    return newInstance(contextProvider.get(), sharedPrefsProvider.get(), appScopeProvider.get(), coroutineDispatchersProvider.get());
  }

  public static GoogleFcmHelper_Factory create(Provider<Context> contextProvider,
      Provider<SharedPreferences> sharedPrefsProvider, Provider<CoroutineScope> appScopeProvider,
      Provider<CoroutineDispatchers> coroutineDispatchersProvider) {
    return new GoogleFcmHelper_Factory(contextProvider, sharedPrefsProvider, appScopeProvider, coroutineDispatchersProvider);
  }

  public static GoogleFcmHelper newInstance(Context context, SharedPreferences sharedPrefs,
      CoroutineScope appScope, CoroutineDispatchers coroutineDispatchers) {
    return new GoogleFcmHelper(context, sharedPrefs, appScope, coroutineDispatchers);
  }
}
