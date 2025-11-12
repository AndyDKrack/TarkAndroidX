package im.vector.app.push.fcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.messaging.FirebaseMessaging;
import dagger.hilt.android.qualifiers.ApplicationContext;
import im.vector.app.core.di.ActiveSessionHolder;
import im.vector.app.core.di.DefaultPreferences;
import im.vector.app.core.dispatchers.CoroutineDispatchers;
import im.vector.app.core.pushers.FcmHelper;
import im.vector.app.core.pushers.PushersManager;
import im.vector.lib.strings.CommonStrings;
import timber.log.Timber;
import javax.inject.Inject;

/**
 * This class store the FCM token in SharedPrefs and ensure this token is retrieved.
 * It has an alter ego in the fdroid variant.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB+\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lim/vector/app/push/fcm/GoogleFcmHelper;", "Lim/vector/app/core/pushers/FcmHelper;", "context", "Landroid/content/Context;", "sharedPrefs", "Landroid/content/SharedPreferences;", "appScope", "Lkotlinx/coroutines/CoroutineScope;", "coroutineDispatchers", "Lim/vector/app/core/dispatchers/CoroutineDispatchers;", "(Landroid/content/Context;Landroid/content/SharedPreferences;Lkotlinx/coroutines/CoroutineScope;Lim/vector/app/core/dispatchers/CoroutineDispatchers;)V", "scope", "checkPlayServices", "", "ensureFcmTokenIsRetrieved", "", "pushersManager", "Lim/vector/app/core/pushers/PushersManager;", "registerPusher", "getFcmToken", "", "isFirebaseAvailable", "onEnterBackground", "activeSessionHolder", "Lim/vector/app/core/di/ActiveSessionHolder;", "onEnterForeground", "storeFcmToken", "token", "Companion", "vector-app_gplayDebug"})
public final class GoogleFcmHelper implements im.vector.app.core.pushers.FcmHelper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPrefs = null;
    @org.jetbrains.annotations.NotNull()
    private final im.vector.app.core.dispatchers.CoroutineDispatchers coroutineDispatchers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_KEY_FCM_TOKEN = "FCM_TOKEN";
    @org.jetbrains.annotations.NotNull()
    public static final im.vector.app.push.fcm.GoogleFcmHelper.Companion Companion = null;
    
    @javax.inject.Inject()
    public GoogleFcmHelper(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @im.vector.app.core.di.DefaultPreferences()
    @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPrefs, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope appScope, @org.jetbrains.annotations.NotNull()
    im.vector.app.core.dispatchers.CoroutineDispatchers coroutineDispatchers) {
        super();
    }
    
    @java.lang.Override()
    public boolean isFirebaseAvailable() {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getFcmToken() {
        return null;
    }
    
    @java.lang.Override()
    public void storeFcmToken(@org.jetbrains.annotations.Nullable()
    java.lang.String token) {
    }
    
    @java.lang.Override()
    public void ensureFcmTokenIsRetrieved(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.pushers.PushersManager pushersManager, boolean registerPusher) {
    }
    
    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private final boolean checkPlayServices(android.content.Context context) {
        return false;
    }
    
    @java.lang.Override()
    public void onEnterForeground(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.di.ActiveSessionHolder activeSessionHolder) {
    }
    
    @java.lang.Override()
    public void onEnterBackground(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.di.ActiveSessionHolder activeSessionHolder) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lim/vector/app/push/fcm/GoogleFcmHelper$Companion;", "", "()V", "PREFS_KEY_FCM_TOKEN", "", "vector-app_gplayDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}