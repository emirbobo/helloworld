package cn.domob.android.i;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public final class i
{
  private static f a = new f(i.class.getSimpleName());
  private static String b = "01";
  
  private static byte a(int paramInt, byte[] paramArrayOfByte)
  {
    if (paramInt >= paramArrayOfByte.length) {
      paramInt = 0;
    }
    int j;
    for (int i = paramInt;; j = paramInt)
    {
      return i;
      paramInt = paramArrayOfByte[paramInt];
    }
  }
  
  public static a a(String paramString1, String paramString2, String paramString3)
  {
    a locala = new a();
    try
    {
      paramString1 = paramString1.getBytes("UTF-8");
      paramString2 = paramString2.getBytes("UTF-8");
      byte[] arrayOfByte = paramString3.getBytes("UTF-8");
      paramString3 = new byte[10];
      int i = 3;
      for (int j = paramString1.length - 1; j >= paramString1.length - 4; j--)
      {
        paramString3[i] = ((byte)paramString1[j]);
        i--;
      }
      i = paramString2.length;
      paramString3[4] = ((byte)(byte)((0xFF00 & i) >> 8));
      paramString3[5] = ((byte)(byte)(i & 0xFF));
      paramString2 = a(arrayOfByte, paramString3);
      paramString1 = new java/lang/String;
      paramString1.<init>(paramString2);
      paramString2 = a(paramString1);
      long l = System.currentTimeMillis();
      locala.a(String.valueOf(l));
      i = (int)(Math.random() * 2.147483647E9D);
      locala.b(String.valueOf(i));
      paramString1 = a(i ^ (int)(l / 1000L));
      for (i = 0; i < paramString1.length; i++) {
        paramString2[(3 - i)] = ((byte)paramString1[i]);
      }
      paramString1 = a(paramString2);
      paramString2 = new java/lang/StringBuilder;
      paramString2.<init>();
      locala.c(b + b(paramString1));
    }
    catch (UnsupportedEncodingException paramString1)
    {
      for (;;)
      {
        a.a(paramString1);
      }
    }
    return locala;
  }
  
  private static byte[] a(int paramInt)
  {
    return new byte[] { (byte)(paramInt & 0xFF), (byte)((0xFF00 & paramInt) >> 8), (byte)((0xFF0000 & paramInt) >> 16), (byte)((0xFF000000 & paramInt) >> 24) };
  }
  
  private static byte[] a(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      if (paramString.length() != 0) {
        break label19;
      }
      localObject1 = localObject2;
    }
    for (;;)
    {
      return (byte[])localObject1;
      try
      {
        label19:
        localObject1 = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject1).reset();
        ((MessageDigest)localObject1).update(paramString.getBytes("UTF-8"));
        localObject1 = ((MessageDigest)localObject1).digest();
      }
      catch (Exception paramString)
      {
        localObject1 = localObject2;
      }
    }
  }
  
  private static byte[] a(byte[] paramArrayOfByte)
  {
    for (int i = 4; i <= paramArrayOfByte.length - 4; i += 4) {
      for (int j = 3; j >= 0; j--) {
        paramArrayOfByte[(i + 3 - j)] = ((byte)(byte)(paramArrayOfByte[(3 - j)] ^ a(i + 3 - j, paramArrayOfByte)));
      }
    }
    return paramArrayOfByte;
  }
  
  private static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    for (int i = 3; i < paramArrayOfByte1.length + 3; i += 4)
    {
      if (i < 4) {
        for (j = 3; j >= 0; j--) {
          paramArrayOfByte2[(9 - j)] = a(i - j, paramArrayOfByte1);
        }
      }
      for (int j = 3; j >= 0; j--) {
        paramArrayOfByte2[(9 - j)] = ((byte)(byte)(paramArrayOfByte2[(9 - j)] ^ a(i - j, paramArrayOfByte1)));
      }
    }
    return paramArrayOfByte2;
  }
  
  private static String b(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      String str1 = str2;
      if (str2.length() == 1) {
        str1 = "0" + str2;
      }
      localStringBuilder.append(str1);
    }
    return localStringBuilder.toString().toUpperCase().trim();
  }
  
  public static class a
  {
    private String a = "";
    private String b = "";
    private String c = "";
    
    public String a()
    {
      return this.a;
    }
    
    public void a(String paramString)
    {
      this.a = paramString;
    }
    
    public String b()
    {
      return this.b;
    }
    
    public void b(String paramString)
    {
      this.b = paramString;
    }
    
    public String c()
    {
      return this.c;
    }
    
    public void c(String paramString)
    {
      this.c = paramString;
    }
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\cn\domob\android\i\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */