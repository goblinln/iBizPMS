package cn.ibizlab.pms.core.util.zentao.constants;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZenTaoMessage implements InitializingBean {
    @Value("${zentao.msg.info.0001:调取禅道接口，地址：{}，请求方法：{}，请求参数：{}。}")
    private String msg_info_0001;
    /**
     * MSG_INFO_0001: 调取禅道接口，地址：{}，请求方法：{}，请求参数：{}。
     */
    public static String MSG_INFO_0001;

    @Value("${zentao.msg.info.0002:禅道接口返回结果，{}。}")
    private String msg_info_0002;
    /**
     * MSG_INFO_0002: 禅道接口返回结果，{}。
     */
    public static String MSG_INFO_0002;

    @Value("${zentao.msg.error.0001:调用禅道接口异常}")
    private String msg_error_0001;
    /**
     * MSG_ERROR_0001: 调用禅道接口异常
     */
    public static String MSG_ERROR_0001;

    @Value("${zentao.msg.error.0002:没有请求地址}")
    private String msg_error_0002;
    /**
     * MSG_ERROR_0002: 没有请求地址
     */
    public static String MSG_ERROR_0002;

    @Value("${zentao.msg.error.0003:创建数据失败}")
    private String msg_error_0003;
    /**
     * MSG_ERROR_0003: 创建数据失败
     */
    public static String MSG_ERROR_0003;

    @Value("${zentao.msg.error.0004:系统处理异常}")
    private String msg_error_0004;
    /**
     * MSG_ERROR_0004: 系统处理异常
     */
    public static String MSG_ERROR_0004;

    @Value("${zentao.msg.error.0005:文件上传失败}")
    private String msg_error_0005;
    /**
     * MSG_ERROR_0005: 文件上传失败
     */
    public static String MSG_ERROR_0005;

    @Value("${zentao.msg.error.0006:禅道接口相应结果不正确}")
    private String msg_error_0006;
    /**
     * MSG_ERROR_0006: 禅道接口相应结果不正确
     */
    public static String MSG_ERROR_0006;

    @Value("${zentao.msg.error.0007:当前用户没有操作权限}")
    private String msg_error_0007;
    /**
     * MSG_ERROR_0007: 当前用户没有操作权限
     */
    public static String MSG_ERROR_0007;

    @Value("${zentao.msg.error.0008:用户未登录}")
    private String msg_error_0008;
    /**
     * MSG_ERROR_0008: 用户未登录
     */
    public static String MSG_ERROR_0008;

    @Value("${zentao.msg.error.0009:登录失败，用户名或密码错误}")
    private String msg_error_0009;
    /**
     * MSG_ERROR_0009: 登录失败，用户名或密码错误
     */
    public static String MSG_ERROR_0009;

    @Value("${zentao.msg.error.0010:登录失败，请联系管理员关联账号}")
    private String msg_error_0010;
    /**
     * MSG_ERROR_0010: 登录失败，请联系管理员关联账号
     */
    public static String MSG_ERROR_0010;

    @Value("${zentao.msg.error.0011:统一认证失效，请重新登录。}")
    private String msg_error_0011;
    /**
     * MSG_ERROR_0010: 统一认证失效，请重新登录。
     */
    public static String MSG_ERROR_0011;

    @Value("${zentao.msg.error.0010:关联的ZT账户不唯一，请联系管理员。}")
    private String msg_error_0012;
    /**
     * MSG_ERROR_0010: 关联的ZT账户不唯一，请联系管理员。
     */
    public static String MSG_ERROR_0012;

    @Value("${zentao.msg.error.0010:ZT API服务异常，请联系管理员}")
    private String msg_error_0013;
    /**
     * MSG_ERROR_0010: ZT API服务异常，请联系管理员
     */
    public static String MSG_ERROR_0013;

    @Value("${zentao.msg.error.0010:ZT API系统的内置密码已被修改，请联系管理员。}")
    private String msg_error_0014;
    /**
     * MSG_ERROR_0010: ZT API系统的内置密码已被修改，请联系管理员。
     */
    public static String MSG_ERROR_0014;
    @Override
    public void afterPropertiesSet() throws Exception {
        MSG_INFO_0001 = msg_info_0001;
        MSG_INFO_0002 = msg_info_0002;

        MSG_ERROR_0001 = msg_error_0001;
        MSG_ERROR_0002 = msg_error_0002;
        MSG_ERROR_0003 = msg_error_0003;
        MSG_ERROR_0004 = msg_error_0004;
        MSG_ERROR_0005 = msg_error_0005;
        MSG_ERROR_0006 = msg_error_0006;
        MSG_ERROR_0007 = msg_error_0007;
        MSG_ERROR_0008 = msg_error_0008;
        MSG_ERROR_0009 = msg_error_0009;
        MSG_ERROR_0010 = msg_error_0010;
        MSG_ERROR_0011 = msg_error_0011;
        MSG_ERROR_0012 = msg_error_0012;
        MSG_ERROR_0013 = msg_error_0013;
        MSG_ERROR_0014 = msg_error_0014;
    }
}
