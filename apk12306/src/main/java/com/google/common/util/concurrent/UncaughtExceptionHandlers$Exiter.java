package com.google.common.util.concurrent;

import com.bangcle.andjni.JniLib;
import com.google.common.annotations.VisibleForTesting;
import java.util.logging.Logger;

@VisibleForTesting
final class UncaughtExceptionHandlers$Exiter
  implements Thread.UncaughtExceptionHandler
{
  private static final Logger logger = Logger.getLogger(Exiter.class.getName());
  private final Runtime runtime;
  
  static
  {
    JniLib.a(Exiter.class, 912);
  }
  
  UncaughtExceptionHandlers$Exiter(Runtime paramRuntime)
  {
    this.runtime = paramRuntime;
  }
  
  public native void uncaughtException(Thread paramThread, Throwable paramThrowable);
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\com\google\common\util\concurrent\UncaughtExceptionHandlers$Exiter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */