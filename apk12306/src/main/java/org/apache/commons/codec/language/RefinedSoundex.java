package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class RefinedSoundex
  implements StringEncoder
{
  public static final RefinedSoundex US_ENGLISH = new RefinedSoundex();
  private static final char[] US_ENGLISH_MAPPING = "01360240043788015936020505".toCharArray();
  public static final String US_ENGLISH_MAPPING_STRING = "01360240043788015936020505";
  private final char[] soundexMapping;
  
  public RefinedSoundex()
  {
    this.soundexMapping = US_ENGLISH_MAPPING;
  }
  
  public RefinedSoundex(String paramString)
  {
    this.soundexMapping = paramString.toCharArray();
  }
  
  public RefinedSoundex(char[] paramArrayOfChar)
  {
    this.soundexMapping = new char[paramArrayOfChar.length];
    System.arraycopy(paramArrayOfChar, 0, this.soundexMapping, 0, paramArrayOfChar.length);
  }
  
  public int difference(String paramString1, String paramString2)
    throws EncoderException
  {
    return SoundexUtils.difference(this, paramString1, paramString2);
  }
  
  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (!(paramObject instanceof String)) {
      throw new EncoderException("Parameter supplied to RefinedSoundex encode is not of type java.lang.String");
    }
    return soundex((String)paramObject);
  }
  
  public String encode(String paramString)
  {
    return soundex(paramString);
  }
  
  char getMappingCode(char paramChar)
  {
    char c;
    if (!Character.isLetter(paramChar)) {
      c = '\000';
    }
    for (paramChar = c;; paramChar = c)
    {
      return paramChar;
      c = this.soundexMapping[(Character.toUpperCase(paramChar) - 'A')];
    }
  }
  
  public String soundex(String paramString)
  {
    if (paramString == null) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      paramString = SoundexUtils.clean(paramString);
      if (paramString.length() != 0)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(paramString.charAt(0));
        int j = 42;
        int i = 0;
        if (i < paramString.length())
        {
          char c = getMappingCode(paramString.charAt(i));
          if (c == j) {}
          for (;;)
          {
            i++;
            break;
            if (c != 0) {
              localStringBuffer.append(c);
            }
            j = c;
          }
        }
        paramString = localStringBuffer.toString();
      }
    }
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\org\apache\commons\codec\language\RefinedSoundex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */