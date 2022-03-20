package com.hawks.hawksbuziness.databinding;
import com.hawks.hawksbuziness.R;
import com.hawks.hawksbuziness.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSupportBindingImpl extends FragmentSupportBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbar, 4);
        sViewsWithIds.put(R.id.animationView, 5);
        sViewsWithIds.put(R.id.title, 6);
        sViewsWithIds.put(R.id.message, 7);
        sViewsWithIds.put(R.id.submit, 8);
    }
    // views
    // variables
    // values
    // listeners
    private OnClickListenerImpl mClickDialAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mClickWhatsappAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mClickEmailAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentSupportBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentSupportBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.airbnb.lottie.LottieAnimationView) bindings[5]
            , (android.view.View) bindings[4]
            , (com.google.android.material.button.MaterialButton) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (com.google.android.material.button.MaterialButton) bindings[3]
            , (android.widget.EditText) bindings[7]
            , (com.google.android.material.button.MaterialButton) bindings[8]
            , (android.widget.EditText) bindings[6]
            , (com.google.android.material.button.MaterialButton) bindings[1]
            );
        this.call.setTag(null);
        this.coordinator.setTag(null);
        this.email.setTag(null);
        this.whatsapp.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.click == variableId) {
            setClick((com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClick(@Nullable com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.click);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        android.view.View.OnClickListener clickDialAndroidViewViewOnClickListener = null;
        android.view.View.OnClickListener clickWhatsappAndroidViewViewOnClickListener = null;
        com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler click = mClick;
        android.view.View.OnClickListener clickEmailAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (click != null) {
                    // read click::dial
                    clickDialAndroidViewViewOnClickListener = (((mClickDialAndroidViewViewOnClickListener == null) ? (mClickDialAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClickDialAndroidViewViewOnClickListener).setValue(click));
                    // read click::whatsapp
                    clickWhatsappAndroidViewViewOnClickListener = (((mClickWhatsappAndroidViewViewOnClickListener == null) ? (mClickWhatsappAndroidViewViewOnClickListener = new OnClickListenerImpl1()) : mClickWhatsappAndroidViewViewOnClickListener).setValue(click));
                    // read click::email
                    clickEmailAndroidViewViewOnClickListener = (((mClickEmailAndroidViewViewOnClickListener == null) ? (mClickEmailAndroidViewViewOnClickListener = new OnClickListenerImpl2()) : mClickEmailAndroidViewViewOnClickListener).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.call.setOnClickListener(clickDialAndroidViewViewOnClickListener);
            this.email.setOnClickListener(clickEmailAndroidViewViewOnClickListener);
            this.whatsapp.setOnClickListener(clickWhatsappAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler value;
        public OnClickListenerImpl setValue(com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.dial(arg0); 
        }
    }
    public static class OnClickListenerImpl1 implements android.view.View.OnClickListener{
        private com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler value;
        public OnClickListenerImpl1 setValue(com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.whatsapp(arg0); 
        }
    }
    public static class OnClickListenerImpl2 implements android.view.View.OnClickListener{
        private com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler value;
        public OnClickListenerImpl2 setValue(com.hawks.hawksbuziness.ui.support.SupportFragment.ClickHandler value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.email(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): click
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}