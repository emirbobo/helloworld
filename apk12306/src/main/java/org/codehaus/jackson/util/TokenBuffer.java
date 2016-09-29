package org.codehaus.jackson.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.impl.JsonParserMinimalBase;
import org.codehaus.jackson.impl.JsonReadContext;
import org.codehaus.jackson.impl.JsonWriteContext;
import org.codehaus.jackson.io.SerializedString;

public class TokenBuffer
  extends JsonGenerator
{
  protected static final int DEFAULT_PARSER_FEATURES = ;
  protected int _appendOffset;
  protected boolean _closed;
  protected Segment _first;
  protected int _generatorFeatures;
  protected Segment _last;
  protected ObjectCodec _objectCodec;
  protected JsonWriteContext _writeContext;
  
  public TokenBuffer(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
    this._generatorFeatures = DEFAULT_PARSER_FEATURES;
    this._writeContext = JsonWriteContext.createRootContext();
    paramObjectCodec = new Segment();
    this._last = paramObjectCodec;
    this._first = paramObjectCodec;
    this._appendOffset = 0;
  }
  
  protected final void _append(JsonToken paramJsonToken)
  {
    paramJsonToken = this._last.append(this._appendOffset, paramJsonToken);
    if (paramJsonToken == null) {}
    for (this._appendOffset += 1;; this._appendOffset = 1)
    {
      return;
      this._last = paramJsonToken;
    }
  }
  
  protected final void _append(JsonToken paramJsonToken, Object paramObject)
  {
    paramJsonToken = this._last.append(this._appendOffset, paramJsonToken, paramObject);
    if (paramJsonToken == null) {}
    for (this._appendOffset += 1;; this._appendOffset = 1)
    {
      return;
      this._last = paramJsonToken;
    }
  }
  
  protected void _reportUnsupportedOperation()
  {
    throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
  }
  
  public JsonParser asParser()
  {
    return asParser(this._objectCodec);
  }
  
  public JsonParser asParser(JsonParser paramJsonParser)
  {
    Parser localParser = new Parser(this._first, paramJsonParser.getCodec());
    localParser.setLocation(paramJsonParser.getTokenLocation());
    return localParser;
  }
  
  public JsonParser asParser(ObjectCodec paramObjectCodec)
  {
    return new Parser(this._first, paramObjectCodec);
  }
  
  public void close()
    throws IOException
  {
    this._closed = true;
  }
  
  public void copyCurrentEvent(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    switch (1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()])
    {
    default: 
      throw new RuntimeException("Internal error: should never end up through this code path");
    case 1: 
      writeStartObject();
    }
    for (;;)
    {
      return;
      writeEndObject();
      continue;
      writeStartArray();
      continue;
      writeEndArray();
      continue;
      writeFieldName(paramJsonParser.getCurrentName());
      continue;
      if (paramJsonParser.hasTextCharacters())
      {
        writeString(paramJsonParser.getTextCharacters(), paramJsonParser.getTextOffset(), paramJsonParser.getTextLength());
      }
      else
      {
        writeString(paramJsonParser.getText());
        continue;
        switch (paramJsonParser.getNumberType())
        {
        default: 
          writeNumber(paramJsonParser.getLongValue());
          break;
        case ???: 
          writeNumber(paramJsonParser.getIntValue());
          break;
        case ???: 
          writeNumber(paramJsonParser.getBigIntegerValue());
          continue;
          switch (paramJsonParser.getNumberType())
          {
          default: 
            writeNumber(paramJsonParser.getDoubleValue());
            break;
          case ???: 
            writeNumber(paramJsonParser.getDecimalValue());
            break;
          case ???: 
            writeNumber(paramJsonParser.getFloatValue());
            continue;
            writeBoolean(true);
            continue;
            writeBoolean(false);
            continue;
            writeNull();
            continue;
            writeObject(paramJsonParser.getEmbeddedObject());
          }
          break;
        }
      }
    }
  }
  
  public void copyCurrentStructure(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken localJsonToken1 = localJsonToken2;
    if (localJsonToken2 == JsonToken.FIELD_NAME)
    {
      writeFieldName(paramJsonParser.getCurrentName());
      localJsonToken1 = paramJsonParser.nextToken();
    }
    switch (1.$SwitchMap$org$codehaus$jackson$JsonToken[localJsonToken1.ordinal()])
    {
    case 2: 
    default: 
      copyCurrentEvent(paramJsonParser);
    }
    for (;;)
    {
      return;
      writeStartArray();
      while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        copyCurrentStructure(paramJsonParser);
      }
      writeEndArray();
      continue;
      writeStartObject();
      while (paramJsonParser.nextToken() != JsonToken.END_OBJECT) {
        copyCurrentStructure(paramJsonParser);
      }
      writeEndObject();
    }
  }
  
  public JsonGenerator disable(JsonGenerator.Feature paramFeature)
  {
    this._generatorFeatures &= (paramFeature.getMask() ^ 0xFFFFFFFF);
    return this;
  }
  
  public JsonGenerator enable(JsonGenerator.Feature paramFeature)
  {
    this._generatorFeatures |= paramFeature.getMask();
    return this;
  }
  
  public void flush()
    throws IOException
  {}
  
  public ObjectCodec getCodec()
  {
    return this._objectCodec;
  }
  
  public final JsonWriteContext getOutputContext()
  {
    return this._writeContext;
  }
  
  public boolean isClosed()
  {
    return this._closed;
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature)
  {
    if ((this._generatorFeatures & paramFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void serialize(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    Object localObject2 = this._first;
    int i = -1;
    for (;;)
    {
      int j = i + 1;
      i = j;
      Object localObject1 = localObject2;
      if (j >= 16)
      {
        i = 0;
        localObject2 = ((Segment)localObject2).next();
        localObject1 = localObject2;
        if (localObject2 != null) {}
      }
      do
      {
        return;
        localObject2 = ((Segment)localObject1).type(i);
      } while (localObject2 == null);
      switch (1.$SwitchMap$org$codehaus$jackson$JsonToken[localObject2.ordinal()])
      {
      default: 
        throw new RuntimeException("Internal error: should never end up through this code path");
      case 1: 
        paramJsonGenerator.writeStartObject();
        localObject2 = localObject1;
        break;
      case 2: 
        paramJsonGenerator.writeEndObject();
        localObject2 = localObject1;
        break;
      case 3: 
        paramJsonGenerator.writeStartArray();
        localObject2 = localObject1;
        break;
      case 4: 
        paramJsonGenerator.writeEndArray();
        localObject2 = localObject1;
        break;
      case 5: 
        localObject2 = ((Segment)localObject1).get(i);
        if ((localObject2 instanceof SerializableString))
        {
          paramJsonGenerator.writeFieldName((SerializableString)localObject2);
          localObject2 = localObject1;
        }
        else
        {
          paramJsonGenerator.writeFieldName((String)localObject2);
          localObject2 = localObject1;
        }
        break;
      case 6: 
        localObject2 = ((Segment)localObject1).get(i);
        if ((localObject2 instanceof SerializableString))
        {
          paramJsonGenerator.writeString((SerializableString)localObject2);
          localObject2 = localObject1;
        }
        else
        {
          paramJsonGenerator.writeString((String)localObject2);
          localObject2 = localObject1;
        }
        break;
      case 7: 
        localObject2 = (Number)((Segment)localObject1).get(i);
        if ((localObject2 instanceof BigInteger))
        {
          paramJsonGenerator.writeNumber((BigInteger)localObject2);
          localObject2 = localObject1;
        }
        else if ((localObject2 instanceof Long))
        {
          paramJsonGenerator.writeNumber(((Number)localObject2).longValue());
          localObject2 = localObject1;
        }
        else
        {
          paramJsonGenerator.writeNumber(((Number)localObject2).intValue());
          localObject2 = localObject1;
        }
        break;
      case 8: 
        localObject2 = ((Segment)localObject1).get(i);
        if ((localObject2 instanceof BigDecimal))
        {
          paramJsonGenerator.writeNumber((BigDecimal)localObject2);
          localObject2 = localObject1;
        }
        else if ((localObject2 instanceof Float))
        {
          paramJsonGenerator.writeNumber(((Float)localObject2).floatValue());
          localObject2 = localObject1;
        }
        else if ((localObject2 instanceof Double))
        {
          paramJsonGenerator.writeNumber(((Double)localObject2).doubleValue());
          localObject2 = localObject1;
        }
        else if (localObject2 == null)
        {
          paramJsonGenerator.writeNull();
          localObject2 = localObject1;
        }
        else if ((localObject2 instanceof String))
        {
          paramJsonGenerator.writeNumber((String)localObject2);
          localObject2 = localObject1;
        }
        else
        {
          throw new JsonGenerationException("Unrecognized value type for VALUE_NUMBER_FLOAT: " + localObject2.getClass().getName() + ", can not serialize");
        }
        break;
      case 9: 
        paramJsonGenerator.writeBoolean(true);
        localObject2 = localObject1;
        break;
      case 10: 
        paramJsonGenerator.writeBoolean(false);
        localObject2 = localObject1;
        break;
      case 11: 
        paramJsonGenerator.writeNull();
        localObject2 = localObject1;
        break;
      case 12: 
        paramJsonGenerator.writeObject(((Segment)localObject1).get(i));
        localObject2 = localObject1;
      }
    }
  }
  
  public JsonGenerator setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[TokenBuffer: ");
    JsonParser localJsonParser = asParser();
    for (int i = 0;; i++)
    {
      try
      {
        JsonToken localJsonToken = localJsonParser.nextToken();
        if (localJsonToken == null)
        {
          if (i >= 100) {
            localStringBuilder.append(" ... (truncated ").append(i - 100).append(" entries)");
          }
          localStringBuilder.append(']');
          return localStringBuilder.toString();
        }
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
      if (i < 100)
      {
        if (i > 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append(localIOException.toString());
      }
    }
  }
  
  public JsonGenerator useDefaultPrettyPrinter()
  {
    return this;
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    paramBase64Variant = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, paramBase64Variant, 0, paramInt2);
    writeObject(paramBase64Variant);
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException, JsonGenerationException
  {
    if (paramBoolean) {}
    for (JsonToken localJsonToken = JsonToken.VALUE_TRUE;; localJsonToken = JsonToken.VALUE_FALSE)
    {
      _append(localJsonToken);
      return;
    }
  }
  
  public final void writeEndArray()
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.END_ARRAY);
    JsonWriteContext localJsonWriteContext = this._writeContext.getParent();
    if (localJsonWriteContext != null) {
      this._writeContext = localJsonWriteContext;
    }
  }
  
  public final void writeEndObject()
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.END_OBJECT);
    JsonWriteContext localJsonWriteContext = this._writeContext.getParent();
    if (localJsonWriteContext != null) {
      this._writeContext = localJsonWriteContext;
    }
  }
  
  public final void writeFieldName(String paramString)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.FIELD_NAME, paramString);
    this._writeContext.writeFieldName(paramString);
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.FIELD_NAME, paramSerializableString);
    this._writeContext.writeFieldName(paramSerializableString.getValue());
  }
  
  public void writeFieldName(SerializedString paramSerializedString)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.FIELD_NAME, paramSerializedString);
    this._writeContext.writeFieldName(paramSerializedString.getValue());
  }
  
  public void writeNull()
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.VALUE_NULL);
  }
  
  public void writeNumber(double paramDouble)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(paramDouble));
  }
  
  public void writeNumber(float paramFloat)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(paramFloat));
  }
  
  public void writeNumber(int paramInt)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(paramInt));
  }
  
  public void writeNumber(long paramLong)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(paramLong));
  }
  
  public void writeNumber(String paramString)
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.VALUE_NUMBER_FLOAT, paramString);
  }
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException, JsonGenerationException
  {
    if (paramBigDecimal == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_NUMBER_FLOAT, paramBigDecimal);
    }
  }
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException, JsonGenerationException
  {
    if (paramBigInteger == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_NUMBER_INT, paramBigInteger);
    }
  }
  
  public void writeObject(Object paramObject)
    throws IOException, JsonProcessingException
  {
    _append(JsonToken.VALUE_EMBEDDED_OBJECT, paramObject);
  }
  
  public void writeRaw(char paramChar)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(String paramString)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRawValue(String paramString)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRawValue(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  public final void writeStartArray()
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.START_ARRAY);
    this._writeContext = this._writeContext.createChildArrayContext();
  }
  
  public final void writeStartObject()
    throws IOException, JsonGenerationException
  {
    _append(JsonToken.START_OBJECT);
    this._writeContext = this._writeContext.createChildObjectContext();
  }
  
  public void writeString(String paramString)
    throws IOException, JsonGenerationException
  {
    if (paramString == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_STRING, paramString);
    }
  }
  
  public void writeString(SerializableString paramSerializableString)
    throws IOException, JsonGenerationException
  {
    if (paramSerializableString == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_STRING, paramSerializableString);
    }
  }
  
  public void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    writeString(new String(paramArrayOfChar, paramInt1, paramInt2));
  }
  
  public void writeTree(JsonNode paramJsonNode)
    throws IOException, JsonProcessingException
  {
    _append(JsonToken.VALUE_EMBEDDED_OBJECT, paramJsonNode);
  }
  
  public void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
  }
  
  protected static final class Parser
    extends JsonParserMinimalBase
  {
    protected transient ByteArrayBuilder _byteBuilder;
    protected boolean _closed;
    protected ObjectCodec _codec;
    protected JsonLocation _location = null;
    protected JsonReadContext _parsingContext;
    protected TokenBuffer.Segment _segment;
    protected int _segmentPtr;
    
    public Parser(TokenBuffer.Segment paramSegment, ObjectCodec paramObjectCodec)
    {
      super();
      this._segment = paramSegment;
      this._segmentPtr = -1;
      this._codec = paramObjectCodec;
      this._parsingContext = JsonReadContext.createRootContext(-1, -1);
    }
    
    protected final void _checkIsNumber()
      throws JsonParseException
    {
      if ((this._currToken == null) || (!this._currToken.isNumeric())) {
        throw _constructError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
      }
    }
    
    protected final Object _currentObject()
    {
      return this._segment.get(this._segmentPtr);
    }
    
    protected void _decodeBase64(String paramString, ByteArrayBuilder paramByteArrayBuilder, Base64Variant paramBase64Variant)
      throws IOException, JsonParseException
    {
      int i = 0;
      int k = paramString.length();
      if (i < k) {}
      for (;;)
      {
        int j = i + 1;
        char c = paramString.charAt(i);
        if (j >= k) {
          return;
        }
        if (c > ' ')
        {
          int m = paramBase64Variant.decodeBase64Char(c);
          if (m < 0) {
            _reportInvalidBase64(paramBase64Variant, c, 0, null);
          }
          if (j >= k) {
            _reportBase64EOF();
          }
          i = j + 1;
          c = paramString.charAt(j);
          j = paramBase64Variant.decodeBase64Char(c);
          if (j < 0) {
            _reportInvalidBase64(paramBase64Variant, c, 1, null);
          }
          m = m << 6 | j;
          if (i >= k) {
            _reportBase64EOF();
          }
          j = i + 1;
          c = paramString.charAt(i);
          i = paramBase64Variant.decodeBase64Char(c);
          if (i < 0)
          {
            if (i != -2) {
              _reportInvalidBase64(paramBase64Variant, c, 2, null);
            }
            if (j >= k) {
              _reportBase64EOF();
            }
            i = j + 1;
            c = paramString.charAt(j);
            if (!paramBase64Variant.usesPaddingChar(c)) {
              _reportInvalidBase64(paramBase64Variant, c, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
            }
            paramByteArrayBuilder.append(m >> 4);
            break;
          }
          m = m << 6 | i;
          if (j >= k) {
            _reportBase64EOF();
          }
          i = j + 1;
          c = paramString.charAt(j);
          j = paramBase64Variant.decodeBase64Char(c);
          if (j < 0)
          {
            if (j != -2) {
              _reportInvalidBase64(paramBase64Variant, c, 3, null);
            }
            paramByteArrayBuilder.appendTwoBytes(m >> 2);
            break;
          }
          paramByteArrayBuilder.appendThreeBytes(m << 6 | j);
          break;
        }
        i = j;
      }
    }
    
    protected void _handleEOF()
      throws JsonParseException
    {
      _throwInternal();
    }
    
    protected void _reportBase64EOF()
      throws JsonParseException
    {
      throw _constructError("Unexpected end-of-String in base64 content");
    }
    
    protected void _reportInvalidBase64(Base64Variant paramBase64Variant, char paramChar, int paramInt, String paramString)
      throws JsonParseException
    {
      if (paramChar <= ' ') {
        paramBase64Variant = "Illegal white space character (code 0x" + Integer.toHexString(paramChar) + ") as character #" + (paramInt + 1) + " of 4-char base64 unit: can only used between units";
      }
      for (;;)
      {
        Object localObject = paramBase64Variant;
        if (paramString != null) {
          localObject = paramBase64Variant + ": " + paramString;
        }
        throw _constructError((String)localObject);
        if (paramBase64Variant.usesPaddingChar(paramChar)) {
          paramBase64Variant = "Unexpected padding character ('" + paramBase64Variant.getPaddingChar() + "') as character #" + (paramInt + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if ((!Character.isDefined(paramChar)) || (Character.isISOControl(paramChar))) {
          paramBase64Variant = "Illegal character (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
        } else {
          paramBase64Variant = "Illegal character '" + paramChar + "' (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
        }
      }
    }
    
    public void close()
      throws IOException
    {
      if (!this._closed) {
        this._closed = true;
      }
    }
    
    public BigInteger getBigIntegerValue()
      throws IOException, JsonParseException
    {
      Object localObject = getNumberValue();
      if ((localObject instanceof BigInteger)) {
        localObject = (BigInteger)localObject;
      }
      for (;;)
      {
        return (BigInteger)localObject;
        switch (TokenBuffer.1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[getNumberType().ordinal()])
        {
        default: 
          localObject = BigInteger.valueOf(((Number)localObject).longValue());
          break;
        case 3: 
          localObject = ((BigDecimal)localObject).toBigInteger();
        }
      }
    }
    
    public byte[] getBinaryValue(Base64Variant paramBase64Variant)
      throws IOException, JsonParseException
    {
      Object localObject;
      if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT)
      {
        localObject = _currentObject();
        if ((localObject instanceof byte[])) {
          paramBase64Variant = (byte[])localObject;
        }
      }
      for (;;)
      {
        return paramBase64Variant;
        if (this._currToken != JsonToken.VALUE_STRING) {
          throw _constructError("Current token (" + this._currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
        }
        String str = getText();
        if (str == null)
        {
          paramBase64Variant = null;
        }
        else
        {
          ByteArrayBuilder localByteArrayBuilder = this._byteBuilder;
          localObject = localByteArrayBuilder;
          if (localByteArrayBuilder == null)
          {
            localObject = new ByteArrayBuilder(100);
            this._byteBuilder = ((ByteArrayBuilder)localObject);
          }
          _decodeBase64(str, (ByteArrayBuilder)localObject, paramBase64Variant);
          paramBase64Variant = ((ByteArrayBuilder)localObject).toByteArray();
        }
      }
    }
    
    public ObjectCodec getCodec()
    {
      return this._codec;
    }
    
    public JsonLocation getCurrentLocation()
    {
      if (this._location == null) {}
      for (JsonLocation localJsonLocation = JsonLocation.NA;; localJsonLocation = this._location) {
        return localJsonLocation;
      }
    }
    
    public String getCurrentName()
    {
      return this._parsingContext.getCurrentName();
    }
    
    public BigDecimal getDecimalValue()
      throws IOException, JsonParseException
    {
      Object localObject = getNumberValue();
      if ((localObject instanceof BigDecimal)) {
        localObject = (BigDecimal)localObject;
      }
      for (;;)
      {
        return (BigDecimal)localObject;
        switch (TokenBuffer.1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[getNumberType().ordinal()])
        {
        case 3: 
        case 4: 
        default: 
          localObject = BigDecimal.valueOf(((Number)localObject).doubleValue());
          break;
        case 1: 
        case 5: 
          localObject = BigDecimal.valueOf(((Number)localObject).longValue());
          break;
        case 2: 
          localObject = new BigDecimal((BigInteger)localObject);
        }
      }
    }
    
    public double getDoubleValue()
      throws IOException, JsonParseException
    {
      return getNumberValue().doubleValue();
    }
    
    public Object getEmbeddedObject()
    {
      if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {}
      for (Object localObject = _currentObject();; localObject = null) {
        return localObject;
      }
    }
    
    public float getFloatValue()
      throws IOException, JsonParseException
    {
      return getNumberValue().floatValue();
    }
    
    public int getIntValue()
      throws IOException, JsonParseException
    {
      if (this._currToken == JsonToken.VALUE_NUMBER_INT) {}
      for (int i = ((Number)_currentObject()).intValue();; i = getNumberValue().intValue()) {
        return i;
      }
    }
    
    public long getLongValue()
      throws IOException, JsonParseException
    {
      return getNumberValue().longValue();
    }
    
    public JsonParser.NumberType getNumberType()
      throws IOException, JsonParseException
    {
      Object localObject = getNumberValue();
      if ((localObject instanceof Integer)) {
        localObject = JsonParser.NumberType.INT;
      }
      for (;;)
      {
        return (JsonParser.NumberType)localObject;
        if ((localObject instanceof Long)) {
          localObject = JsonParser.NumberType.LONG;
        } else if ((localObject instanceof Double)) {
          localObject = JsonParser.NumberType.DOUBLE;
        } else if ((localObject instanceof BigDecimal)) {
          localObject = JsonParser.NumberType.BIG_DECIMAL;
        } else if ((localObject instanceof Float)) {
          localObject = JsonParser.NumberType.FLOAT;
        } else if ((localObject instanceof BigInteger)) {
          localObject = JsonParser.NumberType.BIG_INTEGER;
        } else {
          localObject = null;
        }
      }
    }
    
    public final Number getNumberValue()
      throws IOException, JsonParseException
    {
      _checkIsNumber();
      return (Number)_currentObject();
    }
    
    public JsonStreamContext getParsingContext()
    {
      return this._parsingContext;
    }
    
    public String getText()
    {
      Object localObject2 = null;
      Object localObject3;
      Object localObject1;
      if ((this._currToken == JsonToken.VALUE_STRING) || (this._currToken == JsonToken.FIELD_NAME))
      {
        localObject3 = _currentObject();
        if ((localObject3 instanceof String)) {
          localObject1 = (String)localObject3;
        }
      }
      for (;;)
      {
        return (String)localObject1;
        localObject1 = localObject2;
        if (localObject3 != null)
        {
          localObject1 = localObject3.toString();
          continue;
          localObject1 = localObject2;
          if (this._currToken != null) {
            switch (TokenBuffer.1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()])
            {
            default: 
              localObject1 = this._currToken.asString();
              break;
            case 7: 
            case 8: 
              localObject3 = _currentObject();
              localObject1 = localObject2;
              if (localObject3 != null) {
                localObject1 = localObject3.toString();
              }
              break;
            }
          }
        }
      }
    }
    
    public char[] getTextCharacters()
    {
      Object localObject = getText();
      if (localObject == null) {}
      for (localObject = null;; localObject = ((String)localObject).toCharArray()) {
        return (char[])localObject;
      }
    }
    
    public int getTextLength()
    {
      String str = getText();
      if (str == null) {}
      for (int i = 0;; i = str.length()) {
        return i;
      }
    }
    
    public int getTextOffset()
    {
      return 0;
    }
    
    public JsonLocation getTokenLocation()
    {
      return getCurrentLocation();
    }
    
    public boolean hasTextCharacters()
    {
      return false;
    }
    
    public boolean isClosed()
    {
      return this._closed;
    }
    
    public JsonToken nextToken()
      throws IOException, JsonParseException
    {
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (!this._closed)
      {
        if (this._segment != null) {
          break label22;
        }
        localObject1 = localObject2;
      }
      label22:
      do
      {
        return (JsonToken)localObject1;
        int i = this._segmentPtr + 1;
        this._segmentPtr = i;
        if (i < 16) {
          break;
        }
        this._segmentPtr = 0;
        this._segment = this._segment.next();
        localObject1 = localObject2;
      } while (this._segment == null);
      this._currToken = this._segment.type(this._segmentPtr);
      if (this._currToken == JsonToken.FIELD_NAME)
      {
        localObject1 = _currentObject();
        if ((localObject1 instanceof String))
        {
          localObject1 = (String)localObject1;
          label107:
          this._parsingContext.setCurrentName((String)localObject1);
        }
      }
      for (;;)
      {
        localObject1 = this._currToken;
        break;
        localObject1 = localObject1.toString();
        break label107;
        if (this._currToken == JsonToken.START_OBJECT)
        {
          this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
        }
        else if (this._currToken == JsonToken.START_ARRAY)
        {
          this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
        }
        else if ((this._currToken == JsonToken.END_OBJECT) || (this._currToken == JsonToken.END_ARRAY))
        {
          this._parsingContext = this._parsingContext.getParent();
          if (this._parsingContext == null) {
            this._parsingContext = JsonReadContext.createRootContext(-1, -1);
          }
        }
      }
    }
    
    public JsonToken peekNextToken()
      throws IOException, JsonParseException
    {
      JsonToken localJsonToken = null;
      if (this._closed) {}
      label60:
      label67:
      for (;;)
      {
        return localJsonToken;
        TokenBuffer.Segment localSegment2 = this._segment;
        int j = this._segmentPtr + 1;
        int i = j;
        TokenBuffer.Segment localSegment1 = localSegment2;
        if (j >= 16)
        {
          i = 0;
          if (localSegment2 != null) {
            break label60;
          }
        }
        for (localSegment1 = null;; localSegment1 = localSegment2.next())
        {
          if (localSegment1 == null) {
            break label67;
          }
          localJsonToken = localSegment1.type(i);
          break;
        }
      }
    }
    
    public void setCodec(ObjectCodec paramObjectCodec)
    {
      this._codec = paramObjectCodec;
    }
    
    public void setLocation(JsonLocation paramJsonLocation)
    {
      this._location = paramJsonLocation;
    }
  }
  
  protected static final class Segment
  {
    public static final int TOKENS_PER_SEGMENT = 16;
    private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
    protected Segment _next;
    protected long _tokenTypes;
    protected final Object[] _tokens = new Object[16];
    
    static
    {
      JsonToken[] arrayOfJsonToken = JsonToken.values();
      System.arraycopy(arrayOfJsonToken, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, arrayOfJsonToken.length - 1));
    }
    
    public Segment append(int paramInt, JsonToken paramJsonToken)
    {
      if (paramInt < 16) {
        set(paramInt, paramJsonToken);
      }
      for (paramJsonToken = null;; paramJsonToken = this._next)
      {
        return paramJsonToken;
        this._next = new Segment();
        this._next.set(0, paramJsonToken);
      }
    }
    
    public Segment append(int paramInt, JsonToken paramJsonToken, Object paramObject)
    {
      if (paramInt < 16) {
        set(paramInt, paramJsonToken, paramObject);
      }
      for (paramJsonToken = null;; paramJsonToken = this._next)
      {
        return paramJsonToken;
        this._next = new Segment();
        this._next.set(0, paramJsonToken, paramObject);
      }
    }
    
    public Object get(int paramInt)
    {
      return this._tokens[paramInt];
    }
    
    public Segment next()
    {
      return this._next;
    }
    
    public void set(int paramInt, JsonToken paramJsonToken)
    {
      long l2 = paramJsonToken.ordinal();
      long l1 = l2;
      if (paramInt > 0) {
        l1 = l2 << (paramInt << 2);
      }
      this._tokenTypes |= l1;
    }
    
    public void set(int paramInt, JsonToken paramJsonToken, Object paramObject)
    {
      this._tokens[paramInt] = paramObject;
      long l2 = paramJsonToken.ordinal();
      long l1 = l2;
      if (paramInt > 0) {
        l1 = l2 << (paramInt << 2);
      }
      this._tokenTypes |= l1;
    }
    
    public JsonToken type(int paramInt)
    {
      long l2 = this._tokenTypes;
      long l1 = l2;
      if (paramInt > 0) {
        l1 = l2 >> (paramInt << 2);
      }
      paramInt = (int)l1;
      return TOKEN_TYPES_BY_INDEX[(paramInt & 0xF)];
    }
  }
}


/* Location:              E:\soft\ApkIDE\Worksrc\com.MobileTicket\!\org\codehaus\jackson\util\TokenBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */