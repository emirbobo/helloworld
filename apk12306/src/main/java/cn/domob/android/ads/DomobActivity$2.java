package cn.domob.android.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import cn.domob.android.c.a;
import cn.domob.android.c.b;
import java.util.Hashtable;

class DomobActivity$2
  implements DialogInterface.OnClickListener
{
  DomobActivity$2(DomobActivity paramDomobActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = (a)a.a.get(DomobActivity.a(this.a));
    if (paramDialogInterface != null)
    {
      paramDialogInterface.a(true);
      paramDialogInterface = paramDialogInterface.a();
      if (paramDialogInterface != null) {
        paramDialogInterface.b();
      }
    }
    this.a.finish();
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\cn\domob\android\ads\DomobActivity$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */