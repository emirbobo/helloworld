package com.google.common.collect;

import com.bangcle.andjni.JniLib;
import com.google.common.base.Predicate;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

class StandardTable$Column$KeySet
  extends AbstractSet<R>
{
  static
  {
    JniLib.a(KeySet.class, 758);
  }
  
  StandardTable$Column$KeySet(StandardTable.Column paramColumn) {}
  
  public native void clear();
  
  public native boolean contains(Object paramObject);
  
  public native boolean isEmpty();
  
  public native Iterator<R> iterator();
  
  public native boolean remove(Object paramObject);
  
  public native boolean removeAll(Collection<?> paramCollection);
  
  public native boolean retainAll(Collection<?> paramCollection);
  
  public native int size();
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\com\google\common\collect\StandardTable$Column$KeySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */