package cn.domob.wall.core;

import cn.domob.wall.core.bean.AdInfo;
import cn.domob.wall.core.bean.ControlInfo;
import cn.domob.wall.core.c.d.a;
import cn.domob.wall.core.c.e;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class b$1
  extends Thread
{
  b$1(b paramb) {}
  
  public void run()
  {
    try
    {
      if (!cn.domob.wall.core.c.a.a)
      {
        localObject = new cn/domob/wall/core/c/a;
        ((cn.domob.wall.core.c.a)localObject).<init>(this.a);
        ((cn.domob.wall.core.c.a)localObject).a();
      }
      Object localObject = new java/util/HashMap;
      ((HashMap)localObject).<init>();
      ((HashMap)localObject).put("details", "0");
      cn.domob.wall.core.c.d locald = new cn/domob/wall/core/c/d;
      locald.<init>(this.a);
      d.a local1 = new cn/domob/wall/core/b$1$1;
      local1.<init>(this);
      locald.a(local1, (HashMap)localObject);
      return;
    }
    catch (cn.domob.wall.core.e.a locala)
    {
      for (;;)
      {
        b.j().d("Config request is ongoing, Thread will sleep 1 seconds, and then restart to request");
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        b.j().a(localException);
        b.a(this.a, false);
      }
    }
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\cn\domob\wall\core\b$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */