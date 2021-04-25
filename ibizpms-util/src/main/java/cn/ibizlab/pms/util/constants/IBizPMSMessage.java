package cn.ibizlab.pms.util.constants;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IBizPMSMessage implements InitializingBean {
    @Value("${ibiz.msg.error.0001:系统处理异常。}")
    private String msg_error_0001;
    /**
     * MSG_ERROR_0001: 系统处理异常
     */
    public static String MSG_ERROR_0001;


    @Value("${ibiz.msg.error.0201:文件（目录）类型不对，请检查是否为人为建立。}")
    private String msg_error_0201;
    /**
     * MSG_ERROR_0002: 文件（目录）类型不对，请检查是否为人为建立
     */
    public static String MSG_ERROR_0201;


    @Value("${ibiz.msg.error.0011:同时有人在操作。}")
    private String msg_error_0011;
    /**
     * MSG_ERROR_0011: 同时有人在操作
     */
    public static String MSG_ERROR_0011;


    @Value("${ibiz.msg.error.0101:初始化模板发生异常。}")
    private String msg_error_0101;
    /**
     * MSG_ERROR_0101: 初始化模板异常
     */
    public static String MSG_ERROR_0101;


    @Value("${ibiz.msg.error.0102:处理模板发生异常。}")
    private String msg_error_0102;
    /**
     * MSG_ERROR_0102: 处理模板发生异常
     */
    public static String MSG_ERROR_0102;


    @Value("${ibiz.msg.info.0201:初版。}")
    private String msg_info_0201;
    /**
     * MSG_INFO_0201: 初版。
     */
    public static String MSG_INFO_0201;


    @Value("${ibiz.msg.info.0101:模板生成完成，结果[%b]。}")
    private String msg_info_0101;
    /**
     * MSG_INFO_0101: 模板处理异常后，删除临时文件。
     */
    public static String MSG_INFO_0101;


    @Value("${ibiz.msg.warn.0101:模板处理异常后，删除临时文件[%s]。}")
    private String msg_warn_0101;
    /**
     * MSG_WARN_0101: 模板处理异常后，删除临时文件。
     */
    public static String MSG_WARN_0101;

    @Override
    public void afterPropertiesSet() throws Exception {
        MSG_INFO_0101 = msg_info_0101;

        MSG_INFO_0201 = msg_info_0201;

        MSG_ERROR_0101 = msg_error_0101;
        MSG_ERROR_0102 = msg_error_0102;

        MSG_ERROR_0201 = MSG_ERROR_0201;

        MSG_ERROR_0001 = msg_error_0001;

        MSG_ERROR_0011 = msg_error_0011;

        MSG_WARN_0101 = msg_warn_0101;
    }
}
