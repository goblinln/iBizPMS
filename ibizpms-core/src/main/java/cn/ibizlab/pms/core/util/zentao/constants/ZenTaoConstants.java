package cn.ibizlab.pms.core.util.zentao.constants;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
final public class ZenTaoConstants implements InitializingBean {
    @Value("${zentao.url:http://172.16.102.102/zentao/}")
    private String ztUrl;

    @Value("${zentao.needretry:false}")
    private boolean needRetry;

    @Value("${zentao.retrycount:0}")
    private int retryCount;


    public static String ZT_URL;
    public static boolean ZT_NEED_RETRY;
    public static int ZT_RETRY_COUNT;
    final public static String ZT_URL_EXT = ".json";

    @Override
    public void afterPropertiesSet() throws Exception {
        ZT_URL = ztUrl;
        ZT_NEED_RETRY = needRetry;
        ZT_RETRY_COUNT = retryCount;
    }
}
