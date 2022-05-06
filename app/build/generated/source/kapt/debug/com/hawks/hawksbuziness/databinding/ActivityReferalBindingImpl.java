package com.hawks.hawksbuziness.databinding;
import com.hawks.hawksbuziness.R;
import com.hawks.hawksbuziness.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityReferalBindingImpl extends ActivityReferalBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.logo, 2);
        sViewsWithIds.put(R.id.language, 3);
        sViewsWithIds.put(R.id.terms, 4);
        sViewsWithIds.put(R.id.terms_text, 5);
        sViewsWithIds.put(R.id.submit, 6);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    private OnItemSelectedImpl mClickClickAndroidxDatabindingAdaptersAdapterViewBindingAdapterOnItemSelected;
    // Inverse Binding Event Handlers

    public ActivityReferalBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private ActivityReferalBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Spinner) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[2]
            , (com.google.android.material.button.MaterialButton) bindings[6]
            , (com.google.android.material.checkbox.MaterialCheckBox) bindings[4]
            , (android.widget.TextView) bindings[5]
            );
        this.country.setTag(null);
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.adapter == variableId) {
            setAdapter((android.widget.ArrayAdapter) variable);
        }
        else if (BR.click == variableId) {
            setClick((com.hawks.hawksbuziness.ui.activity.ReferalActivity.Clickhandler) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setAdapter(@Nullable android.widget.ArrayAdapter Adapter) {
        this.mAdapter = Adapter;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.adapter);
        super.requestRebind();
    }
    public void setClick(@Nullable com.hawks.hawksbuziness.ui.activity.ReferalActivity.Clickhandler Click) {
        this.mClick = Click;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
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
        androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected clickClickAndroidxDatabindingAdaptersAdapterViewBindingAdapterOnItemSelected = null;
        android.widget.ArrayAdapter<?> adapter = mAdapter;
        com.hawks.hawksbuziness.ui.activity.ReferalActivity.Clickhandler click = mClick;

        if ((dirtyFlags & 0x5L) != 0) {
        }
        if ((dirtyFlags & 0x6L) != 0) {



                if (click != null) {
                    // read click::click
                    clickClickAndroidxDatabindingAdaptersAdapterViewBindingAdapterOnItemSelected = (((mClickClickAndroidxDatabindingAdaptersAdapterViewBindingAdapterOnItemSelected == null) ? (mClickClickAndroidxDatabindingAdaptersAdapterViewBindingAdapterOnItemSelected = new OnItemSelectedImpl()) : mClickClickAndroidxDatabindingAdaptersAdapterViewBindingAdapterOnItemSelected).setValue(click));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            this.country.setAdapter(adapter);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.AdapterViewBindingAdapter.setOnItemSelectedListener(this.country, (androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected)clickClickAndroidxDatabindingAdaptersAdapterViewBindingAdapterOnItemSelected, (androidx.databinding.adapters.AdapterViewBindingAdapter.OnNothingSelected)null, (androidx.databinding.InverseBindingListener)null);
        }
    }
    // Listener Stub Implementations
    public static class OnItemSelectedImpl implements androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected{
        private com.hawks.hawksbuziness.ui.activity.ReferalActivity.Clickhandler value;
        public OnItemSelectedImpl setValue(com.hawks.hawksbuziness.ui.activity.ReferalActivity.Clickhandler value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onItemSelected(android.widget.AdapterView arg0, android.view.View arg1, int arg2, long arg3) {
            this.value.click(arg0, arg1, arg2, arg3); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): adapter
        flag 1 (0x2L): click
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}