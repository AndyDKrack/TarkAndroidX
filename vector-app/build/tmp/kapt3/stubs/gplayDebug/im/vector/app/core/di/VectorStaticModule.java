package im.vector.app.core.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import androidx.preference.PreferenceManager;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import im.vector.app.EmojiCompatWrapper;
import im.vector.app.EmojiSpanify;
import im.vector.app.SpaceStateHandler;
import im.vector.app.SpaceStateHandlerImpl;
import im.vector.app.config.Config;
import im.vector.app.core.debug.FlipperProxy;
import im.vector.app.core.device.DefaultGetDeviceInfoUseCase;
import im.vector.app.core.device.GetDeviceInfoUseCase;
import im.vector.app.core.dispatchers.CoroutineDispatchers;
import im.vector.app.core.error.DefaultErrorFormatter;
import im.vector.app.core.error.ErrorFormatter;
import im.vector.app.core.resources.BuildMeta;
import im.vector.app.core.utils.AndroidSystemSettingsProvider;
import im.vector.app.core.utils.SystemSettingsProvider;
import im.vector.app.features.analytics.AnalyticsTracker;
import im.vector.app.features.analytics.VectorAnalytics;
import im.vector.app.features.analytics.errors.ErrorTracker;
import im.vector.app.features.analytics.impl.DefaultVectorAnalytics;
import im.vector.app.features.analytics.metrics.VectorPlugins;
import im.vector.app.features.configuration.VectorCustomEventTypesProvider;
import im.vector.app.features.invite.AutoAcceptInvites;
import im.vector.app.features.invite.CompileTimeAutoAcceptInvites;
import im.vector.app.features.mdm.DefaultMdmService;
import im.vector.app.features.mdm.MdmData;
import im.vector.app.features.mdm.MdmService;
import im.vector.app.features.navigation.DefaultNavigator;
import im.vector.app.features.navigation.Navigator;
import im.vector.app.features.pin.PinCodeStore;
import im.vector.app.features.pin.SharedPrefPinCodeStore;
import im.vector.app.features.room.VectorRoomDisplayNameFallbackProvider;
import im.vector.app.features.settings.FontScalePreferences;
import im.vector.app.features.settings.FontScalePreferencesImpl;
import im.vector.app.features.settings.VectorPreferences;
import im.vector.app.features.ui.SharedPreferencesUiStateRepository;
import im.vector.app.features.ui.UiStateRepository;
import im.vector.application.BuildConfig;
import im.vector.application.R;
import im.vector.lib.core.utils.timer.Clock;
import im.vector.lib.core.utils.timer.DefaultClock;
import kotlinx.coroutines.DelicateCoroutinesApi;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.matrix.android.sdk.api.Matrix;
import org.matrix.android.sdk.api.MatrixConfiguration;
import org.matrix.android.sdk.api.SyncConfig;
import org.matrix.android.sdk.api.auth.AuthenticationService;
import org.matrix.android.sdk.api.auth.HomeServerHistoryService;
import org.matrix.android.sdk.api.raw.RawService;
import org.matrix.android.sdk.api.session.Session;
import org.matrix.android.sdk.api.session.sync.filter.SyncFilterParams;
import org.matrix.android.sdk.api.settings.LightweightSettingsStorage;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\u001a\u001a\u00020\u0004H\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u001f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010 \u001a\u00020!H\u0007J8\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0007J\b\u0010/\u001a\u000200H\u0007J\u0010\u00101\u001a\u0002022\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u00103\u001a\u0002042\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u00105\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\fH\u0007\u00a8\u00066"}, d2 = {"Lim/vector/app/core/di/VectorStaticModule;", "", "()V", "providesApplicationCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "providesAuthenticationService", "Lorg/matrix/android/sdk/api/auth/AuthenticationService;", "matrix", "Lorg/matrix/android/sdk/api/Matrix;", "providesBuildMeta", "Lim/vector/app/core/resources/BuildMeta;", "context", "Landroid/content/Context;", "providesContext", "application", "Landroid/app/Application;", "providesCoroutineDispatchers", "Lim/vector/app/core/dispatchers/CoroutineDispatchers;", "providesCurrentSession", "Lorg/matrix/android/sdk/api/session/Session;", "activeSessionHolder", "Lim/vector/app/core/di/ActiveSessionHolder;", "providesDefaultClock", "Lim/vector/lib/core/utils/timer/Clock;", "providesDefaultSharedPreferences", "Landroid/content/SharedPreferences;", "providesGlobalScope", "providesHomeServerHistoryService", "Lorg/matrix/android/sdk/api/auth/HomeServerHistoryService;", "providesLightweightSettingsStorage", "Lorg/matrix/android/sdk/api/settings/LightweightSettingsStorage;", "providesMatrix", "configuration", "Lorg/matrix/android/sdk/api/MatrixConfiguration;", "providesMatrixConfiguration", "vectorPreferences", "Lim/vector/app/features/settings/VectorPreferences;", "vectorRoomDisplayNameFallbackProvider", "Lim/vector/app/features/room/VectorRoomDisplayNameFallbackProvider;", "flipperProxy", "Lim/vector/app/core/debug/FlipperProxy;", "vectorPlugins", "Lim/vector/app/features/analytics/metrics/VectorPlugins;", "vectorCustomEventTypesProvider", "Lim/vector/app/features/configuration/VectorCustomEventTypesProvider;", "mdmService", "Lim/vector/app/features/mdm/MdmService;", "providesPhoneNumberUtil", "Lcom/google/i18n/phonenumbers/PhoneNumberUtil;", "providesRawService", "Lorg/matrix/android/sdk/api/raw/RawService;", "providesResources", "Landroid/content/res/Resources;", "providesSharedPreferences", "vector-app_gplayDebug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class VectorStaticModule {
    @org.jetbrains.annotations.NotNull()
    public static final im.vector.app.core.di.VectorStaticModule INSTANCE = null;
    
    private VectorStaticModule() {
        super();
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context providesContext(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final android.content.res.Resources providesResources(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences providesSharedPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final org.matrix.android.sdk.api.MatrixConfiguration providesMatrixConfiguration(@org.jetbrains.annotations.NotNull()
    im.vector.app.features.settings.VectorPreferences vectorPreferences, @org.jetbrains.annotations.NotNull()
    im.vector.app.features.room.VectorRoomDisplayNameFallbackProvider vectorRoomDisplayNameFallbackProvider, @org.jetbrains.annotations.NotNull()
    im.vector.app.core.debug.FlipperProxy flipperProxy, @org.jetbrains.annotations.NotNull()
    im.vector.app.features.analytics.metrics.VectorPlugins vectorPlugins, @org.jetbrains.annotations.NotNull()
    im.vector.app.features.configuration.VectorCustomEventTypesProvider vectorCustomEventTypesProvider, @org.jetbrains.annotations.NotNull()
    im.vector.app.features.mdm.MdmService mdmService) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final org.matrix.android.sdk.api.Matrix providesMatrix(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.matrix.android.sdk.api.MatrixConfiguration configuration) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final org.matrix.android.sdk.api.session.Session providesCurrentSession(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.di.ActiveSessionHolder activeSessionHolder) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final org.matrix.android.sdk.api.auth.AuthenticationService providesAuthenticationService(@org.jetbrains.annotations.NotNull()
    org.matrix.android.sdk.api.Matrix matrix) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final org.matrix.android.sdk.api.raw.RawService providesRawService(@org.jetbrains.annotations.NotNull()
    org.matrix.android.sdk.api.Matrix matrix) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final org.matrix.android.sdk.api.settings.LightweightSettingsStorage providesLightweightSettingsStorage(@org.jetbrains.annotations.NotNull()
    org.matrix.android.sdk.api.Matrix matrix) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final org.matrix.android.sdk.api.auth.HomeServerHistoryService providesHomeServerHistoryService(@org.jetbrains.annotations.NotNull()
    org.matrix.android.sdk.api.Matrix matrix) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineScope providesApplicationCoroutineScope() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.dispatchers.CoroutineDispatchers providesCoroutineDispatchers() {
        return null;
    }
    
    @dagger.Provides()
    @NamedGlobalScope()
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.DelicateCoroutinesApi.class})
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineScope providesGlobalScope() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.google.i18n.phonenumbers.PhoneNumberUtil providesPhoneNumberUtil() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.resources.BuildMeta providesBuildMeta(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @DefaultPreferences()
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences providesDefaultSharedPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final im.vector.lib.core.utils.timer.Clock providesDefaultClock() {
        return null;
    }
}