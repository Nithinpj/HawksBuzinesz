package com.hawks.hawksbuziness;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.hawks.hawksbuziness.databinding.ActivityMainBindingImpl;
import com.hawks.hawksbuziness.databinding.ActivityReferalBindingImpl;
import com.hawks.hawksbuziness.databinding.ActivitySignupBindingImpl;
import com.hawks.hawksbuziness.databinding.AppBarBackBindingImpl;
import com.hawks.hawksbuziness.databinding.BottomsheetLoginBindingImpl;
import com.hawks.hawksbuziness.databinding.FragmentAddUpdateBindingImpl;
import com.hawks.hawksbuziness.databinding.FragmentHomeBindingImpl;
import com.hawks.hawksbuziness.databinding.FragmentProfileFragmentBindingImpl;
import com.hawks.hawksbuziness.databinding.FragmentSettingsBindingImpl;
import com.hawks.hawksbuziness.databinding.FragmentSignUpBindingImpl;
import com.hawks.hawksbuziness.databinding.FragmentSupportBindingImpl;
import com.hawks.hawksbuziness.databinding.LayoutShopsBindingImpl;
import com.hawks.hawksbuziness.databinding.ProfileItemsBindingImpl;
import com.hawks.hawksbuziness.databinding.ShopFragmentBindingImpl;
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

  private static final int LAYOUT_ACTIVITYSIGNUP = 3;

  private static final int LAYOUT_APPBARBACK = 4;

  private static final int LAYOUT_BOTTOMSHEETLOGIN = 5;

  private static final int LAYOUT_FRAGMENTADDUPDATE = 6;

  private static final int LAYOUT_FRAGMENTHOME = 7;

  private static final int LAYOUT_FRAGMENTPROFILEFRAGMENT = 8;

  private static final int LAYOUT_FRAGMENTSETTINGS = 9;

  private static final int LAYOUT_FRAGMENTSIGNUP = 10;

  private static final int LAYOUT_FRAGMENTSUPPORT = 11;

  private static final int LAYOUT_LAYOUTSHOPS = 12;

  private static final int LAYOUT_PROFILEITEMS = 13;

  private static final int LAYOUT_SHOPFRAGMENT = 14;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(14);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.activity_referal, LAYOUT_ACTIVITYREFERAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.activity_signup, LAYOUT_ACTIVITYSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.app_bar_back, LAYOUT_APPBARBACK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.bottomsheet_login, LAYOUT_BOTTOMSHEETLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.fragment_add_update, LAYOUT_FRAGMENTADDUPDATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.fragment_profile_fragment, LAYOUT_FRAGMENTPROFILEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.fragment_settings, LAYOUT_FRAGMENTSETTINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.fragment_sign_up, LAYOUT_FRAGMENTSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.fragment_support, LAYOUT_FRAGMENTSUPPORT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.layout_shops, LAYOUT_LAYOUTSHOPS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.profile_items, LAYOUT_PROFILEITEMS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.hawks.hawksbuziness.R.layout.shop_fragment, LAYOUT_SHOPFRAGMENT);
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
        case  LAYOUT_ACTIVITYSIGNUP: {
          if ("layout/activity_signup_0".equals(tag)) {
            return new ActivitySignupBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_signup is invalid. Received: " + tag);
        }
        case  LAYOUT_APPBARBACK: {
          if ("layout/app_bar_back_0".equals(tag)) {
            return new AppBarBackBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for app_bar_back is invalid. Received: " + tag);
        }
        case  LAYOUT_BOTTOMSHEETLOGIN: {
          if ("layout/bottomsheet_login_0".equals(tag)) {
            return new BottomsheetLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for bottomsheet_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADDUPDATE: {
          if ("layout/fragment_add_update_0".equals(tag)) {
            return new FragmentAddUpdateBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_add_update is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILEFRAGMENT: {
          if ("layout/fragment_profile_fragment_0".equals(tag)) {
            return new FragmentProfileFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSETTINGS: {
          if ("layout/fragment_settings_0".equals(tag)) {
            return new FragmentSettingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_settings is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(23);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "adapter");
      sKeys.put(2, "address");
      sKeys.put(3, "branch");
      sKeys.put(4, "click");
      sKeys.put(5, "contactNo");
      sKeys.put(6, "data");
      sKeys.put(7, "email");
      sKeys.put(8, "facebook");
      sKeys.put(9, "gstn");
      sKeys.put(10, "head_office");
      sKeys.put(11, "instagram");
      sKeys.put(12, "linkedin");
      sKeys.put(13, "multistore");
      sKeys.put(14, "multistore_name");
      sKeys.put(15, "office_contact");
      sKeys.put(16, "person_incharge");
      sKeys.put(17, "private_limited");
      sKeys.put(18, "shop");
      sKeys.put(19, "shop_name");
      sKeys.put(20, "trade_mark");
      sKeys.put(21, "webisite");
      sKeys.put(22, "whatsapp");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(14);

    static {
      sKeys.put("layout/activity_main_0", com.hawks.hawksbuziness.R.layout.activity_main);
      sKeys.put("layout/activity_referal_0", com.hawks.hawksbuziness.R.layout.activity_referal);
      sKeys.put("layout/activity_signup_0", com.hawks.hawksbuziness.R.layout.activity_signup);
      sKeys.put("layout/app_bar_back_0", com.hawks.hawksbuziness.R.layout.app_bar_back);
      sKeys.put("layout/bottomsheet_login_0", com.hawks.hawksbuziness.R.layout.bottomsheet_login);
      sKeys.put("layout/fragment_add_update_0", com.hawks.hawksbuziness.R.layout.fragment_add_update);
      sKeys.put("layout/fragment_home_0", com.hawks.hawksbuziness.R.layout.fragment_home);
      sKeys.put("layout/fragment_profile_fragment_0", com.hawks.hawksbuziness.R.layout.fragment_profile_fragment);
      sKeys.put("layout/fragment_settings_0", com.hawks.hawksbuziness.R.layout.fragment_settings);
      sKeys.put("layout/fragment_sign_up_0", com.hawks.hawksbuziness.R.layout.fragment_sign_up);
      sKeys.put("layout/fragment_support_0", com.hawks.hawksbuziness.R.layout.fragment_support);
      sKeys.put("layout/layout_shops_0", com.hawks.hawksbuziness.R.layout.layout_shops);
      sKeys.put("layout/profile_items_0", com.hawks.hawksbuziness.R.layout.profile_items);
      sKeys.put("layout/shop_fragment_0", com.hawks.hawksbuziness.R.layout.shop_fragment);
    }
  }
}
