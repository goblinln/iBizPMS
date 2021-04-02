package cn.ibizlab.pms.core.util.model;

import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import java.io.Serializable;
import java.util.List;

public interface IDataEntityModel {

    /**
     * 当前
     */
    public static int CUR = 1;

    /**
     * 上级
     */
    public static int PARENT = 2;

    /**
     * 下级
     */
    public static int SUB = 4;

    /**
     * 无值
     */
    public static int NULL = 8;

    /**
     * 判断是否含有action能力
     * @param action
     * @return
     */
    boolean test(String action);

    /**
     * 判断数据key是否含有action能力
     * @param key
     * @param action
     * @return
     */
    boolean test(Serializable key, String action);

    /**
     * 判断数据keys是否含有action能力
     * @param keys
     * @param action
     * @return
     */
    boolean test(List<Serializable> keys, String action);

    /**
     * 根据父判断时候含有action能力 未设置父时，使用自身能力判断
     * @param PDEName
     * @param PKey
     * @param action
     * @return
     */
    boolean test(String PDEName, Serializable PKey, String action);

    /**
     * 根据父判断时候含有action能力 未设置父时，使用自身能力判断
     * @param PDEName
     * @param PKey
     * @param key
     * @param action
     * @return
     */
    boolean test(String PDEName, Serializable PKey, Serializable key, String action);

    /**
     * 附加权限条件
     * @param context
     * @param action
     */
    void addAuthorityConditions(QueryWrapperContext context,String action);

    /**
     * 返回对应实体对象
     * @return
     */
    String getEntity();

}
