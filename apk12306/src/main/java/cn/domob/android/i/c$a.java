package cn.domob.android.i;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class c$a
{
  private static final a a = new a();
  private static final long e = 600000L;
  private static final int f = 120000;
  private static final int g = 1200000;
  private static final int h = 120000;
  private Location b;
  private int c = -1;
  private final boolean d = true;
  
  private Location a(Context paramContext)
  {
    Context localContext = paramContext.getApplicationContext();
    this.c = 2;
    if (localContext == null)
    {
      paramContext = null;
      return paramContext;
    }
    boolean bool2;
    try
    {
      bool2 = a.a(localContext, "android.permission.ACCESS_FINE_LOCATION");
      if ((bool2) || (a.a(localContext, "android.permission.ACCESS_COARSE_LOCATION")))
      {
        paramContext = (LocationManager)localContext.getSystemService("location");
        if (paramContext != null)
        {
          Iterator localIterator = paramContext.getProviders(true).iterator();
          while (localIterator.hasNext())
          {
            Location localLocation = paramContext.getLastKnownLocation((String)localIterator.next());
            if ((localLocation != null) && (a(localLocation, this.b)))
            {
              this.b = localLocation;
              continue;
              paramContext = this.b;
            }
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      c.i().a(paramContext);
    }
    for (;;)
    {
      break;
      if ((this.b == null) || (System.currentTimeMillis() > this.b.getTime() + 300000L)) {
        a(paramContext, localContext);
      }
      if (this.b == null)
      {
        boolean bool1 = paramContext.isProviderEnabled("network");
        if ((paramContext == null) || ((!bool1) && (!bool2)) || ((!bool1) && (bool2) && (!paramContext.isProviderEnabled("gps"))))
        {
          this.c = 0;
          continue;
          this.c = 1;
        }
      }
    }
  }
  
  private String a(Location paramLocation)
  {
    String str = null;
    if (paramLocation != null)
    {
      str = paramLocation.getLatitude() + "," + paramLocation.getLongitude();
      c.i().b(c.class.getSimpleName(), "User coordinates are " + str);
    }
    return str;
  }
  
  private void a(LocationManager paramLocationManager, Context paramContext)
  {
    if (paramLocationManager == null) {
      return;
    }
    for (;;)
    {
      Object localObject;
      b localb;
      try
      {
        localObject = new android/location/Criteria;
        ((Criteria)localObject).<init>();
        ((Criteria)localObject).setAltitudeRequired(false);
        ((Criteria)localObject).setBearingRequired(false);
        ((Criteria)localObject).setSpeedRequired(false);
        ((Criteria)localObject).setCostAllowed(false);
        ((Criteria)localObject).setAccuracy(2);
        Iterator localIterator = paramLocationManager.getProviders((Criteria)localObject, true).iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        localObject = (String)localIterator.next();
        localb = new cn/domob/android/i/c$a$b;
        localb.<init>(this, paramLocationManager);
        f localf = c.i();
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        localf.b((String)localObject + " start to listener position");
        paramLocationManager.requestLocationUpdates((String)localObject, 0L, 0.0F, localb, paramContext.getMainLooper());
        if (((String)localObject).equals("network"))
        {
          a(paramLocationManager, localb, 1200000, (String)localObject);
          continue;
        }
      }
      catch (Exception paramLocationManager)
      {
        c.i().a(paramLocationManager);
        break;
      }
      finally {}
      if (((String)localObject).equals("gps")) {
        a(paramLocationManager, localb, 120000, (String)localObject);
      }
    }
  }
  
  private void a(final LocationManager paramLocationManager, final LocationListener paramLocationListener, int paramInt, final String paramString)
  {
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        paramLocationManager.removeUpdates(paramLocationListener);
        c.i().b(paramString + " stop listening position");
      }
    }, paramInt);
  }
  
  private boolean a(Location paramLocation1, Location paramLocation2)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramLocation2 == null) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      l1 = System.currentTimeMillis() - paramLocation1.getTime();
      long l2 = System.currentTimeMillis() - paramLocation2.getTime();
      if (l1 <= 600000L)
      {
        bool1 = bool2;
        if (l2 > 600000L) {}
      }
      else
      {
        if ((l1 <= 600000L) || (l2 > 600000L)) {
          break;
        }
        bool1 = false;
      }
    }
    long l1 = paramLocation1.getTime() - paramLocation2.getTime();
    int j;
    label103:
    int k;
    if (l1 > 120000L)
    {
      j = 1;
      if (l1 >= -120000L) {
        break label150;
      }
      k = 1;
      label115:
      if (l1 <= 0L) {
        break label156;
      }
    }
    label150:
    label156:
    for (int i = 1;; i = 0)
    {
      bool1 = bool2;
      if (j != 0) {
        break;
      }
      if (k == 0) {
        break label161;
      }
      bool1 = false;
      break;
      j = 0;
      break label103;
      k = 0;
      break label115;
    }
    label161:
    int m = (int)(paramLocation1.getAccuracy() - paramLocation2.getAccuracy());
    if (m > 0)
    {
      j = 1;
      label181:
      if (m >= 0) {
        break label266;
      }
      k = 1;
      label189:
      if (m <= 200) {
        break label272;
      }
    }
    label266:
    label272:
    for (m = 1;; m = 0)
    {
      boolean bool3 = a(paramLocation1.getProvider(), paramLocation2.getProvider());
      bool1 = bool2;
      if (k != 0) {
        break;
      }
      if (i != 0)
      {
        bool1 = bool2;
        if (j == 0) {
          break;
        }
      }
      if ((i != 0) && (m == 0))
      {
        bool1 = bool2;
        if (bool3) {
          break;
        }
      }
      bool1 = false;
      break;
      j = 0;
      break label181;
      k = 0;
      break label189;
    }
  }
  
  private boolean a(String paramString1, String paramString2)
  {
    boolean bool = true;
    if (paramString1 == null) {
      if (paramString2 != null) {}
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      if (paramString2 != null) {
        bool = paramString1.equals(paramString2);
      }
    }
  }
  
  private static a b()
  {
    return a;
  }
  
  private int c()
  {
    String str;
    int i;
    if (this.b != null)
    {
      str = this.b.getProvider();
      c.i().b("This location is obtained via " + str);
      if (str != null) {
        if (str.equals("network")) {
          i = 1;
        }
      }
    }
    for (;;)
    {
      return i;
      if (str.equals("gps")) {
        i = 0;
      } else if (str.equals("passive")) {
        i = 2;
      } else {
        i = 3;
      }
    }
  }
  
  private int d()
  {
    if (this.b == null) {}
    for (int i = 0;; i = (int)this.b.getAccuracy())
    {
      c.i().b("location accuracy is " + i + " meters");
      return i;
    }
  }
  
  private int e()
  {
    switch (this.c)
    {
    }
    for (;;)
    {
      return this.c;
      c.i().b("Location can not be obtained due to USER_CLOSE");
      continue;
      c.i().b("Location can not be obtained due to NO_PERSSION");
      continue;
      c.i().b("Location can not be obtained due to NO_AVAILABLE_LOCATION");
    }
  }
  
  private long f()
  {
    long l1;
    if (this.b != null)
    {
      l1 = this.b.getTime();
      long l2 = (System.currentTimeMillis() - l1) / 1000L;
      c.i().b(c.class.getSimpleName(), String.format("The location is %s minutes %s seconds ago acquired", new Object[] { String.valueOf(l2 / 60L), String.valueOf(l2 % 60L) }));
    }
    for (;;)
    {
      return l1;
      l1 = 0L;
    }
  }
  
  private class a
  {
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    
    private a() {}
  }
  
  private class b
    implements LocationListener
  {
    public LocationManager a;
    
    b(LocationManager paramLocationManager)
    {
      this.a = paramLocationManager;
    }
    
    public void onLocationChanged(Location paramLocation)
    {
      paramLocation = paramLocation.getProvider();
      if ((paramLocation != null) && (!paramLocation.equals("network")))
      {
        c.i().b(paramLocation + " get location successfully, and remove the listener");
        this.a.removeUpdates(this);
      }
      for (;;)
      {
        return;
        c.i().b(paramLocation + " get location successfully, do not remove the listener");
      }
    }
    
    public void onProviderDisabled(String paramString) {}
    
    public void onProviderEnabled(String paramString) {}
    
    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  }
  
  private class c
  {
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    
    private c() {}
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\cn\domob\android\i\c$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */