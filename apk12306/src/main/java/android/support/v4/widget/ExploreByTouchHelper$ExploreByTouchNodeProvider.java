package android.support.v4.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;

class ExploreByTouchHelper$ExploreByTouchNodeProvider
  extends AccessibilityNodeProviderCompat
{
  private ExploreByTouchHelper$ExploreByTouchNodeProvider(ExploreByTouchHelper paramExploreByTouchHelper) {}
  
  public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
  {
    return ExploreByTouchHelper.access$100(this.this$0, paramInt);
  }
  
  public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    return ExploreByTouchHelper.access$200(this.this$0, paramInt1, paramInt2, paramBundle);
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\android\support\v4\widget\ExploreByTouchHelper$ExploreByTouchNodeProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */