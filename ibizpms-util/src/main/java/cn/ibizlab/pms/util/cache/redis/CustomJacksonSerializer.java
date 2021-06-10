package cn.ibizlab.pms.util.cache.redis;

import cn.ibizlab.pms.util.security.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class CustomJacksonSerializer<T> extends Jackson2JsonRedisSerializer<T>  {

    public static final String DEFAULT_PACKAGE ="[\\w+\\.]+\\.AuthenticationUser";

    public static final String CLASSNAME_EX="_$$_";

    public static final String CLASSNAME_EX_PATTEN="(_\\$\\$_)(\\w+)";

    public static final String USER_PACKAGE= AuthenticationUser.class.getName();

    private static HashMap<String, String> authorityClassMap = new HashMap<>();

    static {
        authorityClassMap.put("cn.ibizlab.util.security.UAAUniResAuthority", UAAUniResAuthority.class.getName());
        authorityClassMap.put("cn.ibizlab.util.security.UAAMenuAuthority", UAAMenuAuthority.class.getName());
        authorityClassMap.put("cn.ibizlab.util.security.UAADEAuthority", UAADEAuthority.class.getName());
        authorityClassMap.put("cn.ibizlab.util.security.UAARoleAuthority", UAARoleAuthority.class.getName());
    }

    public CustomJacksonSerializer(Class type) {
        super(type);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        String serializerContent = new String(bytes, DEFAULT_CHARSET);
        Matcher matcher = Pattern.compile(DEFAULT_PACKAGE).matcher(serializerContent);
        if(matcher.find()){
            serializerContent=serializerContent.replaceAll(DEFAULT_PACKAGE,USER_PACKAGE);
        }
        if(serializerContent.contains(CLASSNAME_EX)){
            serializerContent=serializerContent.replaceAll(CLASSNAME_EX_PATTEN,"");
        }
        for(Map.Entry<String, String> entry : authorityClassMap.entrySet()){
            if(serializerContent.contains(entry.getKey())){
                serializerContent=serializerContent.replaceAll(entry.getKey(), entry.getValue());
            }
        }
        return super.deserialize(serializerContent.getBytes());
    }
}
