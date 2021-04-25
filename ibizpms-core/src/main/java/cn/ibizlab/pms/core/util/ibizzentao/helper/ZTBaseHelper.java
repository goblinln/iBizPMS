package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.domain.EntityMP;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @param <M>
 * @param <T>
 * @author chenxiang
 */
public class ZTBaseHelper<M extends BaseMapper<T>, T extends EntityMP> extends ServiceImpl<M, T> {

    /**
     * 多项选择分隔符
     */
    public static String MULTIPLE_CHOICE = ",";

    /**
     * in查询分隔符
     */
    public static String IN_CHOICE = ";";

    /**
     * 主键属性
     */
    public static String FIELD_ID = "id";

    /**
     * 逻辑属性
     */
    public static String FIELD_DELETED = "deleted";

    /**
     * 对象id
     */
    public static String PARAM_OBJECT_ID = "objectid";

    /**
     * 对象类型
     */
    public static String PARAM_OBJECT_TYPE = "objecttype";

    /**
     * 对象资源路径
     */
    public static String PARAM_OBJECT_SOURCE_PATH = "objectsourcepath";

    /**
     * 对象文本名称
     */
    public static String PARAM_OBJECT_TEXT_NAME = "objecttextname";

    /**
     * 行为文本名称
     */
    public static String PARAM_ACTION_TEXT_NAME = "actiontextname";

    /**
     * 影响版本主干
     */
    public static String FIELD_TRUNK = "trunk";

    /**
     * 版本列表属性名称
     */
    public static String FIELD_BUILDS = "builds";

    /**
     * 主键列表属性名称
     */
    public static String FIELD_IDS = "ids";

    /**
     * Bug列表属性名称
     */
    public static String FIELD_BUGS="bugs";

    /**
     * 级别属性名称
     */
    public static String FIELD_SRFACTIONPARAM = "srfactionparam";

    /**
     * 评审属性名称
     */
    public static String FIELD_RESOLVEDBY = "resolvedby";

    /**
     * 需求列表属性名称
     */
    public static String FIELD_STORIES = "stories";

    /**
     * 版本属性名称
     */
    public static String FIELD_VERSION = "version";

    /**
     * 版本列表属性名称
     */
    public static String FIELD_VERSIONS = "versions";

    /**
     * 测试用例列表属性名称
     */
    public static String FIELD_CASES = "cases";

    /**
     * 实际情况属性名称
     */
    public static String FIELD_REAL = "real";

    /**
     * 结果属性名称
     */
    public static String FIELD_RESULT = "result";

    /**
     * 套件属性名称
     */
    public static String FIELD_SUITE = "suite";

    /**
     * 操作属性名称
     */
    public static String FIELD_ACTION = "action";

    /**
     * 跟属性名称
     */
    public static String FIELD_ROOT = "root";

    /**
     * 级别属性名称
     */
    public static String FIELD_GRADE = "grade";

    /**
     * 路径属性名称
     */
    public static String FIELD_PATH = "path";

    /**
     * 类型属性名称
     */
    public static String FIELD_TYPE = "type";

    /**
     * 名称属性名称
     */
    public static String FIELD_NAME = "name";

    /**
     * 产品及项目代号属性名称
     */
    public static String FIELD_CODE = "code";

    /**
     * 产品列表属性名称
     */
    public static String FIELD_PRODUCTS = "products";

    /**
     * 计划列表属性名称
     */
    public static String FIELD_PLANS = "plans";

    /**
     * 平台列表属性名称
     */
    public static String FIELD_BRANCHS = "branchs";

    /**
     * 状态属性名称
     */
    public static String FIELD_STATUS = "status";

    /**
     * 计划属性名称
     */
    public static String FIELD_PLAN = "plan";

    public static String FIELD_DISTINCT_PROJECT = "distinct project";

    /**
     * 父属性名称
     */
    public static String FIELD_PARENT = "parent";

    /**
     * 最后更新人属性名称
     */
    public static String FIELD_LASTEDITEDBY = "lasteditedby";

    /**
     * 最后更新时间属性名称
     */
    public static String FIELD_LASTEDITEDDATE = "lastediteddate";

    /**
     * 需求描述属性名称
     */
    public static String FIELD_SPEC = "spec";

    /**
     * 需求验证标准属性名称
     */
    public static String FIELD_VERIFY = "verify";

    /**
     * 需求阶段属性名称
     */
    public static String FIELD_STAGE = "stage";

    /**
     * 指派给属性名称
     */
    public static String FIELD_ASSIGNEDTO = "assignedto";

    /**
     * 被谁关闭属性名称
     */
    public static String FIELD_CLOSEDBY = "closedby";

    /**
     * 关闭原因属性名称
     */
    public static String FIELD_CLOSED_REASON = "closedreason";

    /**
     * 关闭时间属性名称
     */
    public static String FIELD_CLOSED_DATE = "closeddate";

    /**
     * 版本属性名称
     */
    public static String FIELD_VERSIONC = "versionc";

    /**
     * 标题属性名称
     */
    public static String FIELD_TITLE = "title";

    /**
     * 最初预计属性名称
     */
    public static String FIELD_ASSIGNED_DATE = "assigneddate";

    /**
     * 最初预计属性名称
     */
    public static String FIELD_ESTIMATE = "estimate";

    /**
     * 登录名
     */
    public static String FIELD_ACCOUNT = "account";

    /**
     * 待办开始时间属性名称
     */
    public static String FIELD_BEGIN = "begin";

    /**
     * 待办提前天数属性名称
     */
    public static String FIELD_BEFOREDAYS = "beforeDays";

    /**
     * 待办结束时间属性名称
     */
    public static String FIELD_END = "end";

    /**
     * 待办关联对象主键属性名称
     */
    public static String FIELD_IDVALUE = "idvalue";

    /**
     * 待办日期属性名称
     */
    public static String FIELD_DATE = "date";

    /**
     * 名称属性是否更新标志
     */
    public static String FIELD_NAMEDIRTYFLAG = "namedirtyflag";

    /**
     * 类型属性是否更新标志
     */
    public static String FIELD_TYPEDIRTYFLAG = "typedirtyflag";

    /**
     * 截止日期属性是否更新标志
     */
    public static String FIELD_DEADLINEDIRTYFLAG = "deadlinedirtyflag";

    /**
     * 优先级属性是否更新标志
     */
    public static String FIELD_PRIDIRTYFLAG = "pridirtyflag";

    /**
     * 描述属性是否更新标志
     */
    public static String FIELD_DESCDIRTYFLAG = "descdirtyflag";

    /**
     * 抄送给属性是否更新标志
     */
    public static String FIELD_MAILTODIRTYFLAG = "mailtodirtyflag";

    /**
     * 预计开始时间
     */
    public static String FIELD_ESTSTARTED = "estStarted";

    /**
     * 实际开始时间
     */
    public static String FIELD_REALSTARTED = "realStarted";

    /**
     * 截止日期
     */
    public static String FIELD_DEADLINE = "deadline";

    /**
     * 时间默认值
     */
    public static String DEFAULT_TIME = "0000-00-00 00:00:00";

    /**
     * 日期默认值
     */
    public static String DEFAULT_DATE = "0000-00-00";




    public T get(Long key) {
        T et = getById(key);
        if (et == null) {
            try {
                et = ((Class<T>) et.getClass()).getDeclaredConstructor().newInstance();
                et.set(FIELD_ID, Long.valueOf(et.get(FIELD_ID).toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean create(T et) {
        if (!SqlHelper.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if (hasId()) {
            CachedBeanCopier.copy(get(Long.valueOf(et.get(FIELD_ID).toString())), et);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean internalUpdate(T et) {
        if (!update(et, (Wrapper) et.getUpdateWrapper(true).eq(FIELD_ID, Long.valueOf(et.get(FIELD_ID).toString())))) {
            return false;
        }
        if (hasId()) {
            CachedBeanCopier.copy(get(Long.valueOf(et.get(FIELD_ID).toString())), et);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean edit(T et) {
        return internalUpdate(et);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        if (hasId()) {
            if (hasDeleted()) {
                UpdateWrapper deleteWrapper = new UpdateWrapper();
                deleteWrapper.set(FIELD_DELETED, StaticDict.YesNo.ITEM_1.getValue());
                deleteWrapper.eq(FIELD_ID, key);
                return this.update(deleteWrapper);
            } else {
                return removeById(key);
            }
        }
        return true;
    }

    public boolean hasId() {
        return true;
    }

    public boolean hasDeleted() {
        return true;
    }

    public <T> T get(Object value, T t) {
        if (value == null) {
            return t;
        }
        return (T) value;
    }
}
