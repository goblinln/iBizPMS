package cn.ibizlab.pms.util.log;

import com.alibaba.fastjson.JSON;

public enum LogMessage {
    //方法开始
    FUNCTION_START("【方法】{}启动。"),
    //方法正常结束
    FUNCTION_END("【方法】{}正常结束。"),
    //抛出检查异常
    FUNCTION_EXCEPTION_END ("【方法】{}准正常结束, 抛出了检查异常。"),


    //方法入参
    PARAMS("【传入参数】：{}"),
    //方法参数异常
    PARAMS_NOT_VALID("【方法入参】{}校验异常"),

    //方法返回值
    RETURNED_VALUE("【返回值】:{}"),

    //抛出运行时异常
    FUCNTION_ERROR_END("方法】{}发生了异常，【异常信息】{}。"),
    //抛出运行时异常对应的信息。
    EXCEPTION_MSG("【异常信息】:{}。"),
    //耗时检查
    TIME_COST("【耗时】{}毫秒。"),
    //当前时间戳
    CURRENTTIMEMILLIS("【时间戳(毫秒)】{}"),
    //循环
    FOR("进入循环, for({})"),
    WHILE("进入循环, while({})"),
    //swich语句
    SWITCH("进入switch，case({})");




    public String msg;

    LogMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static String getStr(Object params) {
        String paramStr = null;
        try {
            paramStr = JSON.toJSONString(params);
        } catch (Exception e) {
            paramStr = params + "";
        }
        return paramStr;
    }
}
