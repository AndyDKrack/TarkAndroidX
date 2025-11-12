package im.vector.app.core.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import im.vector.app.core.debug.FlipperProxy;
import im.vector.app.features.analytics.metrics.VectorPlugins;
import im.vector.app.features.configuration.VectorCustomEventTypesProvider;
import im.vector.app.features.mdm.MdmService;
import im.vector.app.features.room.VectorRoomDisplayNameFallbackProvider;
import im.vector.app.features.settings.VectorPreferences;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.matrix.android.sdk.api.MatrixConfiguration;

@ScopeMetadata
@QualifierMetadata
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
public final class VectorStaticModule_ProvidesMatrixConfigurationFactory implements Factory<MatrixConfiguration> {
  private final Provider<VectorPreferences> vectorPreferencesProvider;

  private final Provider<VectorRoomDisplayNameFallbackProvider> vectorRoomDisplayNameFallbackProvider;

  private final Provider<FlipperProxy> flipperProxyProvider;

  private final Provider<VectorPlugins> vectorPluginsProvider;

  private final Provider<VectorCustomEventTypesProvider> vectorCustomEventTypesProvider;

  private final Provider<MdmService> mdmServiceProvider;

  public VectorStaticModule_ProvidesMatrixConfigurationFactory(
      Provider<VectorPreferences> vectorPreferencesProvider,
      Provider<VectorRoomDisplayNameFallbackProvider> vectorRoomDisplayNameFallbackProvider,
      Provider<FlipperProxy> flipperProxyProvider, Provider<VectorPlugins> vectorPluginsProvider,
      Provider<VectorCustomEventTypesProvider> vectorCustomEventTypesProvider,
      Provider<MdmService> mdmServiceProvider) {
    this.vectorPreferencesProvider = vectorPreferencesProvider;
    this.vectorRoomDisplayNameFallbackProvider = vectorRoomDisplayNameFallbackProvider;
    this.flipperProxyProvider = flipperProxyProvider;
    this.vectorPluginsProvider = vectorPluginsProvider;
    this.vectorCustomEventTypesProvider = vectorCustomEventTypesProvider;
    this.mdmServiceProvider = mdmServiceProvider;
  }

  @Override
  public MatrixConfiguration get() {
    return providesMatrixConfiguration(vectorPreferencesProvider.get(), vectorRoomDisplayNameFallbackProvider.get(), flipperProxyProvider.get(), vectorPluginsProvider.get(), vectorCustomEventTypesProvider.get(), mdmServiceProvider.get());
  }

  public static VectorStaticModule_ProvidesMatrixConfigurationFactory create(
      Provider<VectorPreferences> vectorPreferencesProvider,
      Provider<VectorRoomDisplayNameFallbackProvider> vectorRoomDisplayNameFallbackProvider,
      Provider<FlipperProxy> flipperProxyProvider, Provider<VectorPlugins> vectorPluginsProvider,
      Provider<VectorCustomEventTypesProvider> vectorCustomEventTypesProvider,
      Provider<MdmService> mdmServiceProvider) {
    return new VectorStaticModule_ProvidesMatrixConfigurationFactory(vectorPreferencesProvider, vectorRoomDisplayNameFallbackProvider, flipperProxyProvider, vectorPluginsProvider, vectorCustomEventTypesProvider, mdmServiceProvider);
  }

  public static MatrixConfiguration providesMatrixConfiguration(VectorPreferences vectorPreferences,
      VectorRoomDisplayNameFallbackProvider vectorRoomDisplayNameFallbackProvider,
      FlipperProxy flipperProxy, VectorPlugins vectorPlugins,
      VectorCustomEventTypesProvider vectorCustomEventTypesProvider, MdmService mdmService) {
    return Preconditions.checkNotNullFromProvides(VectorStaticModule.INSTANCE.providesMatrixConfiguration(vectorPreferences, vectorRoomDisplayNameFallbackProvider, flipperProxy, vectorPlugins, vectorCustomEventTypesProvider, mdmService));
  }
}
