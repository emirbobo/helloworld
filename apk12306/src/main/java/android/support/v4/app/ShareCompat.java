package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class ShareCompat
{
  public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
  public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
  private static ShareCompatImpl IMPL;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 16) {
      IMPL = new ShareCompatImplJB();
    }
    for (;;)
    {
      return;
      if (Build.VERSION.SDK_INT >= 14) {
        IMPL = new ShareCompatImplICS();
      } else {
        IMPL = new ShareCompatImplBase();
      }
    }
  }
  
  public static void configureMenuItem(Menu paramMenu, int paramInt, IntentBuilder paramIntentBuilder)
  {
    paramMenu = paramMenu.findItem(paramInt);
    if (paramMenu == null) {
      throw new IllegalArgumentException("Could not find menu item with id " + paramInt + " in the supplied menu");
    }
    configureMenuItem(paramMenu, paramIntentBuilder);
  }
  
  public static void configureMenuItem(MenuItem paramMenuItem, IntentBuilder paramIntentBuilder)
  {
    IMPL.configureMenuItem(paramMenuItem, paramIntentBuilder);
  }
  
  public static ComponentName getCallingActivity(Activity paramActivity)
  {
    ComponentName localComponentName2 = paramActivity.getCallingActivity();
    ComponentName localComponentName1 = localComponentName2;
    if (localComponentName2 == null) {
      localComponentName1 = (ComponentName)paramActivity.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
    }
    return localComponentName1;
  }
  
  public static String getCallingPackage(Activity paramActivity)
  {
    String str2 = paramActivity.getCallingPackage();
    String str1 = str2;
    if (str2 == null) {
      str1 = paramActivity.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
    }
    return str1;
  }
  
  public static class IntentBuilder
  {
    private Activity mActivity;
    private ArrayList<String> mBccAddresses;
    private ArrayList<String> mCcAddresses;
    private CharSequence mChooserTitle;
    private Intent mIntent;
    private ArrayList<Uri> mStreams;
    private ArrayList<String> mToAddresses;
    
    private IntentBuilder(Activity paramActivity)
    {
      this.mActivity = paramActivity;
      this.mIntent = new Intent().setAction("android.intent.action.SEND");
      this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", paramActivity.getPackageName());
      this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", paramActivity.getComponentName());
      this.mIntent.addFlags(524288);
    }
    
    private void combineArrayExtra(String paramString, ArrayList<String> paramArrayList)
    {
      String[] arrayOfString2 = this.mIntent.getStringArrayExtra(paramString);
      if (arrayOfString2 != null) {}
      for (int i = arrayOfString2.length;; i = 0)
      {
        String[] arrayOfString1 = new String[paramArrayList.size() + i];
        paramArrayList.toArray(arrayOfString1);
        if (arrayOfString2 != null) {
          System.arraycopy(arrayOfString2, 0, arrayOfString1, paramArrayList.size(), i);
        }
        this.mIntent.putExtra(paramString, arrayOfString1);
        return;
      }
    }
    
    private void combineArrayExtra(String paramString, String[] paramArrayOfString)
    {
      Intent localIntent = getIntent();
      String[] arrayOfString2 = localIntent.getStringArrayExtra(paramString);
      if (arrayOfString2 != null) {}
      for (int i = arrayOfString2.length;; i = 0)
      {
        String[] arrayOfString1 = new String[paramArrayOfString.length + i];
        if (arrayOfString2 != null) {
          System.arraycopy(arrayOfString2, 0, arrayOfString1, 0, i);
        }
        System.arraycopy(paramArrayOfString, 0, arrayOfString1, i, paramArrayOfString.length);
        localIntent.putExtra(paramString, arrayOfString1);
        return;
      }
    }
    
    public static IntentBuilder from(Activity paramActivity)
    {
      return new IntentBuilder(paramActivity);
    }
    
    public IntentBuilder addEmailBcc(String paramString)
    {
      if (this.mBccAddresses == null) {
        this.mBccAddresses = new ArrayList();
      }
      this.mBccAddresses.add(paramString);
      return this;
    }
    
    public IntentBuilder addEmailBcc(String[] paramArrayOfString)
    {
      combineArrayExtra("android.intent.extra.BCC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder addEmailCc(String paramString)
    {
      if (this.mCcAddresses == null) {
        this.mCcAddresses = new ArrayList();
      }
      this.mCcAddresses.add(paramString);
      return this;
    }
    
    public IntentBuilder addEmailCc(String[] paramArrayOfString)
    {
      combineArrayExtra("android.intent.extra.CC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder addEmailTo(String paramString)
    {
      if (this.mToAddresses == null) {
        this.mToAddresses = new ArrayList();
      }
      this.mToAddresses.add(paramString);
      return this;
    }
    
    public IntentBuilder addEmailTo(String[] paramArrayOfString)
    {
      combineArrayExtra("android.intent.extra.EMAIL", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder addStream(Uri paramUri)
    {
      Uri localUri = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
      if (localUri == null) {}
      for (paramUri = setStream(paramUri);; paramUri = this)
      {
        return paramUri;
        if (this.mStreams == null) {
          this.mStreams = new ArrayList();
        }
        if (localUri != null)
        {
          this.mIntent.removeExtra("android.intent.extra.STREAM");
          this.mStreams.add(localUri);
        }
        this.mStreams.add(paramUri);
      }
    }
    
    public Intent createChooserIntent()
    {
      return Intent.createChooser(getIntent(), this.mChooserTitle);
    }
    
    Activity getActivity()
    {
      return this.mActivity;
    }
    
    public Intent getIntent()
    {
      int i = 1;
      if (this.mToAddresses != null)
      {
        combineArrayExtra("android.intent.extra.EMAIL", this.mToAddresses);
        this.mToAddresses = null;
      }
      if (this.mCcAddresses != null)
      {
        combineArrayExtra("android.intent.extra.CC", this.mCcAddresses);
        this.mCcAddresses = null;
      }
      if (this.mBccAddresses != null)
      {
        combineArrayExtra("android.intent.extra.BCC", this.mBccAddresses);
        this.mBccAddresses = null;
      }
      if ((this.mStreams != null) && (this.mStreams.size() > 1))
      {
        boolean bool = this.mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
        if ((i == 0) && (bool))
        {
          this.mIntent.setAction("android.intent.action.SEND");
          if ((this.mStreams == null) || (this.mStreams.isEmpty())) {
            break label219;
          }
          this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.mStreams.get(0));
          label155:
          this.mStreams = null;
        }
        if ((i != 0) && (!bool))
        {
          this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
          if ((this.mStreams == null) || (this.mStreams.isEmpty())) {
            break label231;
          }
          this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mStreams);
        }
      }
      for (;;)
      {
        return this.mIntent;
        i = 0;
        break;
        label219:
        this.mIntent.removeExtra("android.intent.extra.STREAM");
        break label155;
        label231:
        this.mIntent.removeExtra("android.intent.extra.STREAM");
      }
    }
    
    public IntentBuilder setChooserTitle(int paramInt)
    {
      return setChooserTitle(this.mActivity.getText(paramInt));
    }
    
    public IntentBuilder setChooserTitle(CharSequence paramCharSequence)
    {
      this.mChooserTitle = paramCharSequence;
      return this;
    }
    
    public IntentBuilder setEmailBcc(String[] paramArrayOfString)
    {
      this.mIntent.putExtra("android.intent.extra.BCC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder setEmailCc(String[] paramArrayOfString)
    {
      this.mIntent.putExtra("android.intent.extra.CC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder setEmailTo(String[] paramArrayOfString)
    {
      if (this.mToAddresses != null) {
        this.mToAddresses = null;
      }
      this.mIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder setHtmlText(String paramString)
    {
      this.mIntent.putExtra("android.intent.extra.HTML_TEXT", paramString);
      if (!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
        setText(Html.fromHtml(paramString));
      }
      return this;
    }
    
    public IntentBuilder setStream(Uri paramUri)
    {
      if (!this.mIntent.getAction().equals("android.intent.action.SEND")) {
        this.mIntent.setAction("android.intent.action.SEND");
      }
      this.mStreams = null;
      this.mIntent.putExtra("android.intent.extra.STREAM", paramUri);
      return this;
    }
    
    public IntentBuilder setSubject(String paramString)
    {
      this.mIntent.putExtra("android.intent.extra.SUBJECT", paramString);
      return this;
    }
    
    public IntentBuilder setText(CharSequence paramCharSequence)
    {
      this.mIntent.putExtra("android.intent.extra.TEXT", paramCharSequence);
      return this;
    }
    
    public IntentBuilder setType(String paramString)
    {
      this.mIntent.setType(paramString);
      return this;
    }
    
    public void startChooser()
    {
      this.mActivity.startActivity(createChooserIntent());
    }
  }
  
  public static class IntentReader
  {
    private static final String TAG = "IntentReader";
    private Activity mActivity;
    private ComponentName mCallingActivity;
    private String mCallingPackage;
    private Intent mIntent;
    private ArrayList<Uri> mStreams;
    
    private IntentReader(Activity paramActivity)
    {
      this.mActivity = paramActivity;
      this.mIntent = paramActivity.getIntent();
      this.mCallingPackage = ShareCompat.getCallingPackage(paramActivity);
      this.mCallingActivity = ShareCompat.getCallingActivity(paramActivity);
    }
    
    public static IntentReader from(Activity paramActivity)
    {
      return new IntentReader(paramActivity);
    }
    
    public ComponentName getCallingActivity()
    {
      return this.mCallingActivity;
    }
    
    public Drawable getCallingActivityIcon()
    {
      Object localObject1 = null;
      if (this.mCallingActivity == null) {}
      for (;;)
      {
        return (Drawable)localObject1;
        Object localObject2 = this.mActivity.getPackageManager();
        try
        {
          localObject2 = ((PackageManager)localObject2).getActivityIcon(this.mCallingActivity);
          localObject1 = localObject2;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Log.e("IntentReader", "Could not retrieve icon for calling activity", localNameNotFoundException);
        }
      }
    }
    
    public Drawable getCallingApplicationIcon()
    {
      Object localObject1 = null;
      if (this.mCallingPackage == null) {}
      for (;;)
      {
        return (Drawable)localObject1;
        Object localObject2 = this.mActivity.getPackageManager();
        try
        {
          localObject2 = ((PackageManager)localObject2).getApplicationIcon(this.mCallingPackage);
          localObject1 = localObject2;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Log.e("IntentReader", "Could not retrieve icon for calling application", localNameNotFoundException);
        }
      }
    }
    
    public CharSequence getCallingApplicationLabel()
    {
      Object localObject1 = null;
      if (this.mCallingPackage == null) {}
      for (;;)
      {
        return (CharSequence)localObject1;
        Object localObject2 = this.mActivity.getPackageManager();
        try
        {
          localObject2 = ((PackageManager)localObject2).getApplicationLabel(((PackageManager)localObject2).getApplicationInfo(this.mCallingPackage, 0));
          localObject1 = localObject2;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Log.e("IntentReader", "Could not retrieve label for calling application", localNameNotFoundException);
        }
      }
    }
    
    public String getCallingPackage()
    {
      return this.mCallingPackage;
    }
    
    public String[] getEmailBcc()
    {
      return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
    }
    
    public String[] getEmailCc()
    {
      return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
    }
    
    public String[] getEmailTo()
    {
      return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
    }
    
    public String getHtmlText()
    {
      String str2 = this.mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
      String str1 = str2;
      CharSequence localCharSequence;
      if (str2 == null)
      {
        localCharSequence = getText();
        if (!(localCharSequence instanceof Spanned)) {
          break label38;
        }
        str1 = Html.toHtml((Spanned)localCharSequence);
      }
      for (;;)
      {
        return str1;
        label38:
        str1 = str2;
        if (localCharSequence != null) {
          str1 = ShareCompat.IMPL.escapeHtml(localCharSequence);
        }
      }
    }
    
    public Uri getStream()
    {
      return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
    }
    
    public Uri getStream(int paramInt)
    {
      if ((this.mStreams == null) && (isMultipleShare())) {
        this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
      }
      if (this.mStreams != null) {}
      for (Uri localUri = (Uri)this.mStreams.get(paramInt);; localUri = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM"))
      {
        return localUri;
        if (paramInt != 0) {
          break;
        }
      }
      throw new IndexOutOfBoundsException("Stream items available: " + getStreamCount() + " index requested: " + paramInt);
    }
    
    public int getStreamCount()
    {
      if ((this.mStreams == null) && (isMultipleShare())) {
        this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
      }
      int i;
      if (this.mStreams != null) {
        i = this.mStreams.size();
      }
      for (;;)
      {
        return i;
        if (this.mIntent.hasExtra("android.intent.extra.STREAM")) {
          i = 1;
        } else {
          i = 0;
        }
      }
    }
    
    public String getSubject()
    {
      return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
    }
    
    public CharSequence getText()
    {
      return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
    }
    
    public String getType()
    {
      return this.mIntent.getType();
    }
    
    public boolean isMultipleShare()
    {
      return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
    }
    
    public boolean isShareIntent()
    {
      String str = this.mIntent.getAction();
      if (("android.intent.action.SEND".equals(str)) || ("android.intent.action.SEND_MULTIPLE".equals(str))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean isSingleShare()
    {
      return "android.intent.action.SEND".equals(this.mIntent.getAction());
    }
  }
  
  static abstract interface ShareCompatImpl
  {
    public abstract void configureMenuItem(MenuItem paramMenuItem, ShareCompat.IntentBuilder paramIntentBuilder);
    
    public abstract String escapeHtml(CharSequence paramCharSequence);
  }
  
  static class ShareCompatImplBase
    implements ShareCompat.ShareCompatImpl
  {
    private static void withinStyle(StringBuilder paramStringBuilder, CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      if (paramInt1 < paramInt2)
      {
        char c = paramCharSequence.charAt(paramInt1);
        if (c == '<') {
          paramStringBuilder.append("&lt;");
        }
        for (;;)
        {
          paramInt1++;
          break;
          if (c == '>')
          {
            paramStringBuilder.append("&gt;");
          }
          else if (c == '&')
          {
            paramStringBuilder.append("&amp;");
          }
          else if ((c > '~') || (c < ' '))
          {
            paramStringBuilder.append("&#" + c + ";");
          }
          else if (c == ' ')
          {
            while ((paramInt1 + 1 < paramInt2) && (paramCharSequence.charAt(paramInt1 + 1) == ' '))
            {
              paramStringBuilder.append("&nbsp;");
              paramInt1++;
            }
            paramStringBuilder.append(' ');
          }
          else
          {
            paramStringBuilder.append(c);
          }
        }
      }
    }
    
    public void configureMenuItem(MenuItem paramMenuItem, ShareCompat.IntentBuilder paramIntentBuilder)
    {
      paramMenuItem.setIntent(paramIntentBuilder.createChooserIntent());
    }
    
    public String escapeHtml(CharSequence paramCharSequence)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      withinStyle(localStringBuilder, paramCharSequence, 0, paramCharSequence.length());
      return localStringBuilder.toString();
    }
  }
  
  static class ShareCompatImplICS
    extends ShareCompat.ShareCompatImplBase
  {
    public void configureMenuItem(MenuItem paramMenuItem, ShareCompat.IntentBuilder paramIntentBuilder)
    {
      ShareCompatICS.configureMenuItem(paramMenuItem, paramIntentBuilder.getActivity(), paramIntentBuilder.getIntent());
      if (shouldAddChooserIntent(paramMenuItem)) {
        paramMenuItem.setIntent(paramIntentBuilder.createChooserIntent());
      }
    }
    
    boolean shouldAddChooserIntent(MenuItem paramMenuItem)
    {
      if (!paramMenuItem.hasSubMenu()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  static class ShareCompatImplJB
    extends ShareCompat.ShareCompatImplICS
  {
    public String escapeHtml(CharSequence paramCharSequence)
    {
      return ShareCompatJB.escapeHtml(paramCharSequence);
    }
    
    boolean shouldAddChooserIntent(MenuItem paramMenuItem)
    {
      return false;
    }
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\android\support\v4\app\ShareCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */