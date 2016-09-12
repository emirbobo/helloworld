package com.worklight.androidgap.plugin;

import android.app.ProgressDialog;
import com.bangcle.andjni.JniLib;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class BusyIndicator
  extends CordovaPlugin
{
  public static final String ACTION_HIDE = "hide";
  public static final String ACTION_SHOW = "show";
  private boolean isShowing = false;
  private ProgressDialog spinnerDialog = null;
  
  static
  {
    JniLib.a(BusyIndicator.class, 1173);
  }
  
  public native boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException;
  
  public native void hide();
  
  public native boolean isShowing();
  
  public native void show(String paramString);
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\com\worklight\androidgap\plugin\BusyIndicator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */