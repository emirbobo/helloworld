package com.google.common.collect;

import com.bangcle.andjni.JniLib;

class CustomConcurrentHashMap$Segment$ExpirationQueue$2
  extends AbstractLinkedIterator<CustomConcurrentHashMap.ReferenceEntry<K, V>>
{
  static
  {
    JniLib.a(2.class, 407);
  }
  
  CustomConcurrentHashMap$Segment$ExpirationQueue$2(CustomConcurrentHashMap.Segment.ExpirationQueue paramExpirationQueue, CustomConcurrentHashMap.ReferenceEntry paramReferenceEntry)
  {
    super(paramReferenceEntry);
  }
  
  protected native CustomConcurrentHashMap.ReferenceEntry<K, V> computeNext(CustomConcurrentHashMap.ReferenceEntry<K, V> paramReferenceEntry);
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\com\google\common\collect\CustomConcurrentHashMap$Segment$ExpirationQueue$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */