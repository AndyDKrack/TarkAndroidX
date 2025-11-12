package im.vector.app.core.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import im.vector.app.core.resources.BuildMeta;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class VectorStaticModule_ProvidesBuildMetaFactory implements Factory<BuildMeta> {
  private final Provider<Context> contextProvider;

  public VectorStaticModule_ProvidesBuildMetaFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BuildMeta get() {
    return providesBuildMeta(contextProvider.get());
  }

  public static VectorStaticModule_ProvidesBuildMetaFactory create(
      Provider<Context> contextProvider) {
    return new VectorStaticModule_ProvidesBuildMetaFactory(contextProvider);
  }

  public static BuildMeta providesBuildMeta(Context context) {
    return Preconditions.checkNotNullFromProvides(VectorStaticModule.INSTANCE.providesBuildMeta(context));
  }
}
