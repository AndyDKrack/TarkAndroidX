package im.vector.app.features.debug.features;

import android.os.Bundle;
import android.view.View;
import dagger.hilt.android.AndroidEntryPoint;
import im.vector.app.core.platform.VectorBaseActivity;
import im.vector.app.databinding.FragmentGenericRecyclerBinding;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u0002H\u0016J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u001cH\u0014R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006 "}, d2 = {"Lim/vector/app/features/debug/features/DebugFeaturesSettingsActivity;", "Lim/vector/app/core/platform/VectorBaseActivity;", "Lim/vector/app/databinding/FragmentGenericRecyclerBinding;", "()V", "controller", "Lim/vector/app/features/debug/features/FeaturesController;", "getController", "()Lim/vector/app/features/debug/features/FeaturesController;", "setController", "(Lim/vector/app/features/debug/features/FeaturesController;)V", "debugFeatures", "Lim/vector/app/features/debug/features/DebugVectorFeatures;", "getDebugFeatures", "()Lim/vector/app/features/debug/features/DebugVectorFeatures;", "setDebugFeatures", "(Lim/vector/app/features/debug/features/DebugVectorFeatures;)V", "debugFeaturesStateFactory", "Lim/vector/app/features/debug/features/DebugFeaturesStateFactory;", "getDebugFeaturesStateFactory", "()Lim/vector/app/features/debug/features/DebugFeaturesStateFactory;", "setDebugFeaturesStateFactory", "(Lim/vector/app/features/debug/features/DebugFeaturesStateFactory;)V", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "getBinding", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "vector-app_gplayDebug"})
public final class DebugFeaturesSettingsActivity extends im.vector.app.core.platform.VectorBaseActivity<im.vector.app.databinding.FragmentGenericRecyclerBinding> {
    @javax.inject.Inject()
    public im.vector.app.features.debug.features.DebugVectorFeatures debugFeatures;
    @javax.inject.Inject()
    public im.vector.app.features.debug.features.DebugFeaturesStateFactory debugFeaturesStateFactory;
    @javax.inject.Inject()
    public im.vector.app.features.debug.features.FeaturesController controller;
    
    public DebugFeaturesSettingsActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.features.debug.features.DebugVectorFeatures getDebugFeatures() {
        return null;
    }
    
    public final void setDebugFeatures(@org.jetbrains.annotations.NotNull()
    im.vector.app.features.debug.features.DebugVectorFeatures p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.features.debug.features.DebugFeaturesStateFactory getDebugFeaturesStateFactory() {
        return null;
    }
    
    public final void setDebugFeaturesStateFactory(@org.jetbrains.annotations.NotNull()
    im.vector.app.features.debug.features.DebugFeaturesStateFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final im.vector.app.features.debug.features.FeaturesController getController() {
        return null;
    }
    
    public final void setController(@org.jetbrains.annotations.NotNull()
    im.vector.app.features.debug.features.FeaturesController p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public im.vector.app.databinding.FragmentGenericRecyclerBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View getRootView() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
}