package com.arrownock.im.callback;

import com.arrownock.exception.ArrownockException;

public abstract interface IAnIMGetTopicListCallback
{
  public abstract void onError(ArrownockException paramArrownockException);
  
  public abstract void onSuccess(AnIMGetTopicListCallbackData paramAnIMGetTopicListCallbackData);
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\com\arrownock\im\callback\IAnIMGetTopicListCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */