package com.myproject.spring5.util.datatype;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;

public abstract class JsonUtil
{
  private static final ObjectMapper objectMapper = new ObjectMapper();
  
  static
  {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
  }
  
  public static ObjectMapper getObjectMapper()
  {
    return objectMapper;
  }
  
  public static JsonNodeFactory getNodeFactory()
  {
    return objectMapper.getNodeFactory();
  }
  
  public static ObjectNode objectNode()
  {
    return getNodeFactory().objectNode();
  }
  
  public static String toJson(Map<String, Object> map)
  {
    StringWriter out = new StringWriter();
    try
    {
      objectMapper.writeValue(out, map);
      return out.toString();
    }
    catch (Exception e)
    {
      return "{}";
    }
    finally
    {
      IOUtils.closeQuietly(out);
    }
  }
  
  public static String toJson(Object pojo)
  {
    return toJson(pojo, false);
  }
  
  public static String toJson(Object pojo, boolean prettyPrint)
  {
    StringWriter sw = new StringWriter();
    try
    {
      JsonGenerator jg = objectMapper.getJsonFactory().createJsonGenerator(sw);
      if (prettyPrint) {
        jg.useDefaultPrettyPrinter();
      }
      objectMapper.writeValue(jg, pojo);
    }
    catch (Exception e)
    {
      return "{}";
    }
    finally
    {
      IOUtils.closeQuietly(sw);
    }
    return sw.toString();
  }
  
  public static <T> T toObject(String json, Class<T> clazz)
    throws IOException
  {
    try
    {
      return (T)objectMapper.readValue(json, clazz);
    }
    catch (JsonParseException e)
    {
      throw e;
    }
    catch (JsonMappingException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }
  }
 
  
  public static ObjectNode toObjectNode(Object object)
  {
    return (ObjectNode)objectMapper.convertValue(object, ObjectNode.class);
  }
  
  public static ObjectNode putValue(ObjectNode node, String name, Object value)
  {
    if ((value instanceof String))
    {
      node.put(name, String.valueOf(value));
    }
    else if (value.getClass() == Boolean.class)
    {
      node.put(name, (Boolean)value);
    }
    else if ((value instanceof Number))
    {
      Number v = (Number)value;
      if (v.getClass() == Integer.class) {
        node.put(name, v.intValue());
      } else if (v.getClass() == Long.class) {
        node.put(name, v.longValue());
      } else if (v.getClass() == Float.class) {
        node.put(name, v.floatValue());
      } else if (v.getClass() == Double.class) {
        node.put(name, v.doubleValue());
      }
    }
    else if ((value instanceof BigDecimal))
    {
      node.put(name, (BigDecimal)value);
    }
    else if ((value instanceof byte[]))
    {
      node.put(name, (byte[])value);
    }
    else if ((value instanceof ArrayNode))
    {
      node.putArray(name).addAll((ArrayNode)value);
    }
    else if ((value instanceof ObjectNode))
    {
      node.putObject(name).putAll((ObjectNode)value);
    }
    else
    {
      node.putPOJO(name, value);
    }
    return node;
  }
  
  public static Object getValue(JsonNode node, String name)
  {
    Object obj = null;
    JsonNode valueNode = node.path(name);
    if ((valueNode == null) || (valueNode.isMissingNode()) || (valueNode.isNull())) {
      obj = "".toString();
    } else if (valueNode.isBinary()) {
      try
      {
        byte[] bytes = valueNode.binaryValue();
        obj = bytes;
      }
      catch (IOException e)
      {
        obj = "".toString();
      }
    } else if (valueNode.isTextual()) {
      obj = valueNode.textValue();
    } else if (valueNode.isNumber())
    {
      if (valueNode.isInt()) {
        obj = new Integer(valueNode.intValue());
      } else if (valueNode.isLong()) {
        obj = new Long(valueNode.longValue());
      } else if (valueNode.isDouble()) {
        obj = new Double(valueNode.doubleValue());
      }
    }
    else if (valueNode.isBoolean()) {
      obj = new Boolean(valueNode.booleanValue());
    } else if (valueNode.isArray()) {
      obj = (ArrayNode)valueNode;
    } else if (valueNode.isObject()) {
      obj = (ObjectNode)valueNode;
    } else if (valueNode.isPojo()) {
      obj = ((POJONode)valueNode).getPojo();
    }
    return obj;
  }
  
  public static JsonNode find(JsonNode baseNode, String path)
  {
    if (baseNode == null) {
      return MissingNode.getInstance();
    }
    if (baseNode.isMissingNode()) {
      return baseNode;
    }
    if (path == null) {
      return baseNode;
    }
    path.replaceAll("[/]+$", "");
    path.replaceAll("^[/]+", "");
    
    return find(baseNode, StringUtils.split(path, "/"));
  }
  
  public static JsonNode find(JsonNode baseNode, String[] path)
  {
    if (baseNode == null) {
      return MissingNode.getInstance();
    }
    if ((path == null) || (StringUtils.isEmpty(path[0])) || (baseNode.isMissingNode())) {
      return baseNode;
    }
    if (path.length < 2) {
      return baseNode.path(path[0]);
    }
    ArrayList<String> list = new ArrayList(path.length);
    for (String p : path) {
      list.add(p);
    }
    return find(baseNode, list);
  }
  
  public static JsonNode find(JsonNode baseNode, ArrayList<String> path)
  {
    try
    {
      if (baseNode == null) {
        return MissingNode.getInstance();
      }
      if ((path == null) || (path.isEmpty()) || (StringUtils.isEmpty((CharSequence)path.get(0))) || (baseNode.isMissingNode())) {
        return baseNode;
      }
      if (path.size() < 2) {
        return baseNode.path((String)path.get(0));
      }
      return find(baseNode.path((String)path.remove(0)), path);
    }
    catch (Exception e) {}
    return MissingNode.getInstance();
  }
}
