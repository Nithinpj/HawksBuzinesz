package com.nithi.hawksbuziness;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.nithi.hawksbuziness.databinding.ActivityMainBindingImpl;
import com.nithi.hawksbuziness.databinding.ActivityReferalBindingImpl;
import com.nithi.hawksbuziness.databinding.FragmentProfileFragmentBindingImpl;
import com.nithi.hawksbuziness.databinding.FragmentSignUpBindingImpl;
import com.nithi.hawksbuziness.databinding.FragmentSupportBindingImpl;
import com.nithi.hawksbuziness.databinding.LayoutShopsBindingImpl;
import com.nithi.hawksbuziness.databinding.ProfileItemsBindingImpl;
import com.nithi.hawksbuziness.databinding.ShopFragmentBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_ACTIVITYREFERAL = 2;

  private static final int LAYOUT_FRAGMENTPROFILEFRAGMENT = 3;

  private static final int LAYOUT_FRAGMENTSIGNUP = 4;

  private static final int LAYOUT_FRAGMENTSUPPORT = 5;

  private static final int LAYOUT_LAYOUTSHOPS = 6;

  private static final int LAYOUT_PROFILEITEMS = 7;

  private static final int LAYOUT_SHOPFRAGMENT = 8;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(8);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.activity_referal, LAYOUT_ACTIVITYREFERAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.fragment_profile_fragment, LAYOUT_FRAGMENTPROFILEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.fragment_sign_up, LAYOUT_FRAGMENTSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.fragment_support, LAYOUT_FRAGMENTSUPPORT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.layout_shops, LAYOUT_LAYOUTSHOPS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.profile_items, LAYOUT_PROFILEITEMS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.nithi.hawksbuziness.R.layout.shop_fragment, LAYOUT_SHOPFRAGMENT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYREFERAL: {
          if ("layout/activity_referal_0".equals(tag)) {
            return new ActivityReferalBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_referal is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILEFRAGMENT: {
          if ("layout/fragment_profile_fragment_0".equals(tag)) {
            return new FragmentProfileFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSIGNUP: {
          if ("layout/fragment_sign_up_0".equals(tag)) {
            return new FragmentSignUpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sign_up is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSUPPORT: {
          if ("layout/fragment_support_0".equals(tag)) {
            return new FragmentSupportBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_support is invalid. Received: " + tag);
        }
        case  LAYOUT_LAYOUTSHOPS: {
          if ("layout/layout_shops_0".equals(tag)) {
            return new LayoutShopsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for layout_shops is invalid. Received: " + tag);
        }
        case  LAYOUT_PROFILEITEMS: {
          if ("layout/profile_items_0".equals(tag)) {
            return new ProfileItemsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for profile_items is invalid. Received: " + tag);
        }
        case  LAYOUT_SHOPFRAGMENT: {
          if ("layout/shop_fragment_0".equals(tag)) {
            return new ShopFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for shop_fragment is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "adapter");
      sKeys.put(2, "data");
      sKeys.put(3, "shop");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(8);

    static {
      sKeys.put("layout/activity_main_0", com.nithi.hawksbuziness.R.layout.activity_main);
      sKeys.put("layout/activity_referal_0", com.nithi.hawksbuziness.R.layout.activity_referal);
      sKeys.put("layout/fragment_profile_fragment_0", com.nithi.hawksbuziness.R.layout.fragment_profile_fragment);
      sKeys.put("layout/fragment_sign_up_0", com.nithi.hawksbuziness.R.layout.fragment_sign_up);
      sKeys.put("layout/fragment_support_0", com.nithi.hawksbuziness.R.layout.fragment_support);
      sKeys.put("layout/layout_shops_0", com.nithi.hawksbuziness.R.layout.layout_shops);
      sKeys.put("layout/profile_items_0", com.nithi.hawksbuziness.R.layout.profile_items);
      sKeys.put("layout/shop_fragment_0", com.nithi.hawksbuziness.R.layout.shop_fragment);
    }
  }
}
