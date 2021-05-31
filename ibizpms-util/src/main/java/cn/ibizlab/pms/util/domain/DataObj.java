package cn.ibizlab.pms.util.domain;

import cn.ibizlab.pms.util.helper.DataObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataObj<K,V> extends HashMap<K,V> {

    public <T> T set(String key, V value)
    {
        this.put((K)key,value);
        return (T)this;
    }

    @Override
    public V get(Object key) {
        if(key==null)
            return null;
        V objValue=super.get(key);
        if(objValue==null)
            objValue=super.get(key.toString().toUpperCase());
        if(objValue==null)
            objValue=super.get(key.toString().toLowerCase());
        return objValue;
    }

    public JSONObject getJSONObjectValue(String strParamName)  {
        return getJSONObjectValue(strParamName,new JSONObject());
    }
    public JSONObject getJSONObjectValue(String strParamName, JSONObject jDefault)  {
         return DataObject.getJSONObjectValue(this.get(strParamName),jDefault);
    }

    public <T> List<T> getListValue(String strParamName,Class<T> clazz)
    {
        List<T> list= new ArrayList<>();
        Object val=this.get(strParamName);
        if(val != null && val instanceof List)
        {
            list= JSONArray.parseArray(JSON.toJSONString(val),clazz);
        }
        return list;
    }

    public List<String> getListValue( String strParamName)  {
        return DataObject.getListValue(strParamName);
    }

    public JSONArray getJSONArrayValue(String strParamName)  {
        return getJSONArrayValue(strParamName,new JSONArray());
    }
    public JSONArray getJSONArrayValue(String strParamName, JSONArray jDefault)  {
        return DataObject.getJSONArrayValue(this.get(strParamName),jDefault);
    }

    public Integer getIntegerValue(String objValue)  {
        return getIntegerValue(objValue, Integer.MIN_VALUE);
    }
    public int getIntegerValue( String strParamName, int nDefault)  {
        return DataObject.getIntegerValue(this.get(strParamName),nDefault);
    }

    public Float getFloatValue(String objValue)  {
        return this.getFloatValue(objValue,-9999f);
    }
    public Float getFloatValue(  String strParamName, float fDefault)  {
        return DataObject.getFloatValue(this.get(strParamName),fDefault);
    }



    public BigDecimal getBigDecimalValue(String objValue)  {
        return this.getBigDecimalValue(objValue,BigDecimal.valueOf(-9999));
    }
    public BigDecimal getBigDecimalValue(  String strParamName, BigDecimal fDefault)  {
        return DataObject.getBigDecimalValue(this.get(strParamName),fDefault);
    }




    public Long getLongValue( String strParamName)  {
        return this.getLongValue(strParamName,Long.MIN_VALUE);
    }
    public Long getLongValue( String strParamName, long nDefault)  {
        return DataObject.getLongValue(this.get(strParamName),nDefault);
    }



    public String getStringValue(String objValue)  {
        return getStringValue(objValue, "");
    }
    public String getStringValue( String strParamName, String strDefault)  {
        return DataObject.getStringValue(this.get(strParamName),strDefault);
    }




    public byte[] getBinaryValue(String objValue)  {
        return getBinaryValue(objValue, null);
    }

    public byte[] getBinaryValue(String strParamName, byte[] def)  {
        return DataObject.getBinaryValue(this.get(strParamName),def);
    }




    public Timestamp getTimestampBegin( String strParamName)  {
        return getTimestampValue(strParamName, DataObject.getBeginDate());
    }

    public Timestamp getTimestampEnd( String strParamName)  {
        Object objValue = this.get(strParamName);
        if (objValue == null) {
            return DataObject.getEndDate();
        }
        try {
            Timestamp t= DataObject.getTimestampValue(objValue, DataObject.getEndDate());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String time = df.format(t);
            Calendar cl=Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
            cl.setTime(Timestamp.valueOf(time+" 23:59:59"));
            return new Timestamp(cl.getTime().getTime());
        } catch (Exception ex) {
            return DataObject.getEndDate();
        }

    }

    public Timestamp getTimestampValue( String strParamName, Timestamp dtDefault)  {
        Object objValue = this.get(strParamName);
        if (objValue == null || objValue.equals("")) {
            return dtDefault;
        }
        try {
            return DataObject.getTimestampValue(objValue,null);
        } catch (Exception ex) {
            return dtDefault;
        }
    }


    public <T> T copyTo(T targetEntity, boolean bIncEmpty){
        if(targetEntity instanceof EntityBase){
            for(K field : this.keySet()){
                Object value=this.get(field);
                if( !ObjectUtils.isEmpty(value) ||  ObjectUtils.isEmpty(value) &&  bIncEmpty ){
                    ((EntityBase)targetEntity).set((String)field,value);
                }
            }
        }
        else if(targetEntity instanceof DTOBase){
            for(K field : this.keySet()){
                Object value=this.get(field);
                if( !ObjectUtils.isEmpty(value) ||  ObjectUtils.isEmpty(value) &&  bIncEmpty ){
                    ((DTOBase)targetEntity).set(((String)field).toLowerCase(),value);
                }
            }
        }
        else if(targetEntity instanceof DataObj){
            for(K field : this.keySet()){
                Object value=this.get(field);
                if( !ObjectUtils.isEmpty(value) ||  ObjectUtils.isEmpty(value) &&  bIncEmpty ){
                    ((DataObj) targetEntity).set((String)field,value);
                }
            }
        }
        else if(targetEntity instanceof Map){
            for(K field : this.keySet()){
                Object value=this.get(field);
                if( !ObjectUtils.isEmpty(value) ||  ObjectUtils.isEmpty(value) &&  bIncEmpty ){
                    ((Map) targetEntity).put(field,value);
                }
            }
        }
        return targetEntity;
    }




}
