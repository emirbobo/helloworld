package com.google.common.collect;

import com.bangcle.andjni.JniLib;
import java.util.Collection;

class Multimaps$MapMultimap$AsMapEntries$1$1
  extends AbstractMapEntry<K, Collection<V>>
{
  static
  {
    JniLib.a(1.class, 657);
  }
  
  Multimaps$MapMultimap$AsMapEntries$1$1(Multimaps.MapMultimap.AsMapEntries.1 param1, Object paramObject) {}
  
  public native K getKey();
  
  public native Collection<V> getValue();
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\com\google\common\collect\Multimaps$MapMultimap$AsMapEntries$1$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */