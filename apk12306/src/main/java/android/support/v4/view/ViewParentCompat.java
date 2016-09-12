package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

public class ViewParentCompat
{
  static final ViewParentCompatImpl IMPL;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 14) {}
    for (IMPL = new ViewParentCompatICSImpl();; IMPL = new ViewParentCompatStubImpl()) {
      return;
    }
  }
  
  public static boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return IMPL.requestSendAccessibilityEvent(paramViewParent, paramView, paramAccessibilityEvent);
  }
  
  static class ViewParentCompatICSImpl
    extends ViewParentCompat.ViewParentCompatStubImpl
  {
    public boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      return ViewParentCompatICS.requestSendAccessibilityEvent(paramViewParent, paramView, paramAccessibilityEvent);
    }
  }
  
  static abstract interface ViewParentCompatImpl
  {
    public abstract boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent);
  }
  
  static class ViewParentCompatStubImpl
    implements ViewParentCompat.ViewParentCompatImpl
  {
    public boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      if (paramView == null) {}
      for (boolean bool = false;; bool = true)
      {
        return bool;
        ((AccessibilityManager)paramView.getContext().getSystemService("accessibility")).sendAccessibilityEvent(paramAccessibilityEvent);
      }
    }
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\android\support\v4\view\ViewParentCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */