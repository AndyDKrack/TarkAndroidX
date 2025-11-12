package im.vector.app.push.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import dagger.hilt.android.AndroidEntryPoint;
import im.vector.app.core.di.ActiveSessionHolder;
import im.vector.app.core.pushers.FcmHelper;
import im.vector.app.core.pushers.PushParser;
import im.vector.app.core.pushers.PushersManager;
import im.vector.app.core.pushers.UnifiedPushHelper;
import im.vector.app.core.pushers.VectorPushHandler;
import im.vector.app.features.mdm.MdmData;
import im.vector.app.features.mdm.MdmService;
import im.vector.app.features.settings.VectorPreferences;
import org.matrix.android.sdk.api.logger.LoggerTag;
import timber.log.Timber;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00105\u001a\u000206H\u0016J\u0010\u00107\u001a\u0002062\u0006\u00108\u001a\u000209H\u0016J\u0010\u0010:\u001a\u0002062\u0006\u0010;\u001a\u00020<H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001e\u0010)\u001a\u00020*8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001e\u0010/\u001a\u0002008\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104\u00a8\u0006="}, d2 = {"Lim/vector/app/push/fcm/VectorFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "activeSessionHolder", "Lim/vector/app/core/di/ActiveSessionHolder;", "getActiveSessionHolder", "()Lim/vector/app/core/di/ActiveSessionHolder;", "setActiveSessionHolder", "(Lim/vector/app/core/di/ActiveSessionHolder;)V", "fcmHelper", "Lim/vector/app/core/pushers/FcmHelper;", "getFcmHelper", "()Lim/vector/app/core/pushers/FcmHelper;", "setFcmHelper", "(Lim/vector/app/core/pushers/FcmHelper;)V", "mdmService", "Lim/vector/app/features/mdm/MdmService;", "getMdmService", "()Lim/vector/app/features/mdm/MdmService;", "setMdmService", "(Lim/vector/app/features/mdm/MdmService;)V", "pushParser", "Lim/vector/app/core/pushers/PushParser;", "getPushParser", "()Lim/vector/app/core/pushers/PushParser;", "setPushParser", "(Lim/vector/app/core/pushers/PushParser;)V", "pushersManager", "Lim/vector/app/core/pushers/PushersManager;", "getPushersManager", "()Lim/vector/app/core/pushers/PushersManager;", "setPushersManager", "(Lim/vector/app/core/pushers/PushersManager;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "unifiedPushHelper", "Lim/vector/app/core/pushers/UnifiedPushHelper;", "getUnifiedPushHelper", "()Lim/vector/app/core/pushers/UnifiedPushHelper;", "setUnifiedPushHelper", "(Lim/vector/app/core/pushers/UnifiedPushHelper;)V", "vectorPreferences", "Lim/vector/app/features/settings/VectorPreferences;", "getVectorPreferences", "()Lim/vector/app/features/settings/VectorPreferences;", "setVectorPreferences", "(Lim/vector/app/features/settings/VectorPreferences;)V", "vectorPushHandler", "Lim/vector/app/core/pushers/VectorPushHandler;", "getVectorPushHandler", "()Lim/vector/app/core/pushers/VectorPushHandler;", "setVectorPushHandler", "(Lim/vector/app/core/pushers/VectorPushHandler;)V", "onDestroy", "", "onMessageReceived", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "vector-app_gplayDebug"})
public final class VectorFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @javax.inject.Inject()
    public im.vector.app.core.pushers.FcmHelper fcmHelper;
    @javax.inject.Inject()
    public im.vector.app.features.settings.VectorPreferences vectorPreferences;
    @javax.inject.Inject()
    public im.vector.app.core.di.ActiveSessionHolder activeSessionHolder;
    @javax.inject.Inject()
    public im.vector.app.core.pushers.PushersManager pushersManager;
    @javax.inject.Inject()
    public im.vector.app.core.pushers.PushParser pushParser;
    @javax.inject.Inject()
    public im.vector.app.core.pushers.VectorPushHandler vectorPushHandler;
    @javax.inject.Inject()
    public im.vector.app.core.pushers.UnifiedPushHelper unifiedPushHelper;
    @javax.inject.Inject()
    public im.vector.app.features.mdm.MdmService mdmService;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    public VectorFirebaseMessagingService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.pushers.FcmHelper getFcmHelper() {
        return null;
    }
    
    public final void setFcmHelper(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.pushers.FcmHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.features.settings.VectorPreferences getVectorPreferences() {
        return null;
    }
    
    public final void setVectorPreferences(@org.jetbrains.annotations.NotNull()
    im.vector.app.features.settings.VectorPreferences p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.di.ActiveSessionHolder getActiveSessionHolder() {
        return null;
    }
    
    public final void setActiveSessionHolder(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.di.ActiveSessionHolder p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.pushers.PushersManager getPushersManager() {
        return null;
    }
    
    public final void setPushersManager(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.pushers.PushersManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.pushers.PushParser getPushParser() {
        return null;
    }
    
    public final void setPushParser(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.pushers.PushParser p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.pushers.VectorPushHandler getVectorPushHandler() {
        return null;
    }
    
    public final void setVectorPushHandler(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.pushers.VectorPushHandler p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.core.pushers.UnifiedPushHelper getUnifiedPushHelper() {
        return null;
    }
    
    public final void setUnifiedPushHelper(@org.jetbrains.annotations.NotNull()
    im.vector.app.core.pushers.UnifiedPushHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.features.mdm.MdmService getMdmService() {
        return null;
    }
    
    public final void setMdmService(@org.jetbrains.annotations.NotNull()
    im.vector.app.features.mdm.MdmService p0) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onNewToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    @java.lang.Override()
    public void onMessageReceived(@org.jetbrains.annotations.NotNull()
    com.google.firebase.messaging.RemoteMessage message) {
    }
}