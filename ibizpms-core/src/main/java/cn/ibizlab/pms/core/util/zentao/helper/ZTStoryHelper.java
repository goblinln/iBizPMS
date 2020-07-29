package cn.ibizlab.pms.core.util.zentao.helper;

import cn.ibizlab.pms.core.util.zentao.bean.ZTCheckItem;
import cn.ibizlab.pms.core.util.zentao.bean.ZTResult;
import cn.ibizlab.pms.core.util.zentao.constants.ZenTaoConstants;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import java.util.*;

/**
 * 【禅道接口-Story】 辅助类
 */
@Slf4j
final public class ZTStoryHelper {
    // ----------
    // 接口模块
    // ----------

    /**
     * 接口模块名
     */
    private final static String MODULE_NAME = "story";

    // ----------
    // 参数日期格式
    // ----------

    private final static Map<String, String> PARAMS_DATEFORMAT = new HashMap<>();

    // ----------
    // 接口ACTION
    // ----------

    private final static String  ACTION_CREATE = "create";
    private final static String  ACTION_BATCHCREATE = "batchCreate";
    private final static String  ACTION_COMMONACTION = "commonAction";
    private final static String  ACTION_EDIT = "edit";
    private final static String  ACTION_BATCHEDIT = "batchEdit";
    private final static String  ACTION_CHANGE = "change";
    private final static String  ACTION_ACTIVATE = "activate";
    private final static String  ACTION_VIEW = "view";
    private final static String  ACTION_DELETE = "delete";
    private final static String  ACTION_REVIEW = "review";
    private final static String  ACTION_BATCHREVIEW = "batchReview";
    private final static String  ACTION_CLOSE = "close";
    private final static String  ACTION_BATCHCLOSE = "batchClose";
    private final static String  ACTION_BATCHCHANGEMODULE = "batchChangeModule";
    private final static String  ACTION_BATCHCHANGEPLAN = "batchChangePlan";
    private final static String  ACTION_BATCHCHANGEBRANCH = "batchChangeBranch";
    private final static String  ACTION_BATCHCHANGESTAGE = "batchChangeStage";
    private final static String  ACTION_ASSIGNTO = "assignTo";
    private final static String  ACTION_BATCHASSIGNTO = "batchAssignTo";
    private final static String  ACTION_TASKS = "tasks";
    private final static String  ACTION_BUGS = "bugs";
    private final static String  ACTION_CASES = "cases";
    private final static String  ACTION_ZEROCASE = "zeroCase";
    private final static String  ACTION_LINKSTORY = "linkStory";
    private final static String  ACTION_AJAXGETPROJECTSTORIES = "ajaxGetProjectStories";
    private final static String  ACTION_AJAXGETPRODUCTSTORIES = "ajaxGetProductStories";
    private final static String  ACTION_AJAXSEARCHPRODUCTSTORIES = "ajaxSearchProductStories";
    private final static String  ACTION_AJAXGETDETAIL = "ajaxGetDetail";
    private final static String  ACTION_AJAXGETINFO = "ajaxGetInfo";
    private final static String  ACTION_REPORT = "report";
    private final static String  ACTION_EXPORT = "export";
    private final static String  ACTION_AJAXGETUSERSTORYS = "ajaxGetUserStorys";
    private final static String  ACTION_AJAXGETSTATUS = "ajaxGetStatus";

    // ----------
    // 接口行为HTTP方法（GET、POST）
    // ----------

    private final static HttpMethod ACTION_HTTPMETHOD_CREATE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHCREATE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_COMMONACTION = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_EDIT = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHEDIT = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_CHANGE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_ACTIVATE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_VIEW = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_DELETE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_REVIEW = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHREVIEW = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_CLOSE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHCLOSE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHCHANGEMODULE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHCHANGEPLAN = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHCHANGEBRANCH = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHCHANGESTAGE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_ASSIGNTO = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BATCHASSIGNTO = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_TASKS = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_BUGS = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_CASES = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_ZEROCASE = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_LINKSTORY = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_AJAXGETPROJECTSTORIES = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_AJAXGETPRODUCTSTORIES = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_AJAXSEARCHPRODUCTSTORIES = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_AJAXGETDETAIL = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_AJAXGETINFO = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_REPORT = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_EXPORT = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_AJAXGETUSERSTORYS = HttpMethod.POST;
    private final static HttpMethod ACTION_HTTPMETHOD_AJAXGETSTATUS = HttpMethod.POST;

    // ----------
    // 接口行为POST参数
    // ----------

    private final static Map<String, Object> ACTION_PARAMS_CREATE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHCREATE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_COMMONACTION = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_EDIT = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHEDIT = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_CHANGE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_ACTIVATE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_VIEW = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_DELETE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_REVIEW = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHREVIEW = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_CLOSE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHCLOSE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHCHANGEMODULE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHCHANGEPLAN = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHCHANGEBRANCH = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHCHANGESTAGE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_ASSIGNTO = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BATCHASSIGNTO = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_TASKS = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_BUGS = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_CASES = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_ZEROCASE = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_LINKSTORY = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_AJAXGETPROJECTSTORIES = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_AJAXGETPRODUCTSTORIES = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_AJAXSEARCHPRODUCTSTORIES = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_AJAXGETDETAIL = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_AJAXGETINFO = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_REPORT = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_EXPORT = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_AJAXGETUSERSTORYS = new HashMap<>();
    private final static Map<String, Object> ACTION_PARAMS_AJAXGETSTATUS = new HashMap<>();

    // ----------
    // 接口行为URL参数
    // ----------

    private final static List<String> ACTION_URL_PARAMS_CREATE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHCREATE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_COMMONACTION = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_EDIT = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHEDIT = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_CHANGE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_ACTIVATE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_VIEW = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_DELETE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_REVIEW = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHREVIEW = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_CLOSE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHCLOSE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHCHANGEMODULE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHCHANGEPLAN = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHCHANGEBRANCH = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHCHANGESTAGE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_ASSIGNTO = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BATCHASSIGNTO = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_TASKS = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_BUGS = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_CASES = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_ZEROCASE = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_LINKSTORY = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_AJAXGETPROJECTSTORIES = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_AJAXGETPRODUCTSTORIES = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_AJAXSEARCHPRODUCTSTORIES = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_AJAXGETDETAIL = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_AJAXGETINFO = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_REPORT = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_EXPORT = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_AJAXGETUSERSTORYS = new ArrayList<>();
    private final static List<String> ACTION_URL_PARAMS_AJAXGETSTATUS = new ArrayList<>();

    // ----------
    // 返回结果CheckList
    // ----------
    private final static List<ZTCheckItem> ACTION_CHECKLIST_CREATE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHCREATE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_COMMONACTION = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_EDIT = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHEDIT = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_CHANGE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_ACTIVATE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_VIEW = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_DELETE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_REVIEW = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHREVIEW = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_CLOSE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHCLOSE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHCHANGEMODULE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHCHANGEPLAN = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHCHANGEBRANCH = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHCHANGESTAGE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_ASSIGNTO = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BATCHASSIGNTO = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_TASKS = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_BUGS = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_CASES = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_ZEROCASE = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_LINKSTORY = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_AJAXGETPROJECTSTORIES = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_AJAXGETPRODUCTSTORIES = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_AJAXSEARCHPRODUCTSTORIES = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_AJAXGETDETAIL = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_AJAXGETINFO = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_REPORT = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_EXPORT = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_AJAXGETUSERSTORYS = new ArrayList<>();
    private final static List<ZTCheckItem> ACTION_CHECKLIST_AJAXGETSTATUS = new ArrayList<>();

    // ----------
    // 返回URL正则
    // ----------
    private final static String ACTION_RETURNURL_CREATE = null;
    private final static String ACTION_RETURNURL_BATCHCREATE = null;
    private final static String ACTION_RETURNURL_COMMONACTION = null;
    private final static String ACTION_RETURNURL_EDIT = null;
    private final static String ACTION_RETURNURL_BATCHEDIT = null;
    private final static String ACTION_RETURNURL_CHANGE = null;
    private final static String ACTION_RETURNURL_ACTIVATE = null;
    private final static String ACTION_RETURNURL_VIEW = null;
    private final static String ACTION_RETURNURL_DELETE = null;
    private final static String ACTION_RETURNURL_REVIEW = null;
    private final static String ACTION_RETURNURL_BATCHREVIEW = null;
    private final static String ACTION_RETURNURL_CLOSE = null;
    private final static String ACTION_RETURNURL_BATCHCLOSE = null;
    private final static String ACTION_RETURNURL_BATCHCHANGEMODULE = null;
    private final static String ACTION_RETURNURL_BATCHCHANGEPLAN = null;
    private final static String ACTION_RETURNURL_BATCHCHANGEBRANCH = null;
    private final static String ACTION_RETURNURL_BATCHCHANGESTAGE = null;
    private final static String ACTION_RETURNURL_ASSIGNTO = null;
    private final static String ACTION_RETURNURL_BATCHASSIGNTO = null;
    private final static String ACTION_RETURNURL_TASKS = null;
    private final static String ACTION_RETURNURL_BUGS = null;
    private final static String ACTION_RETURNURL_CASES = null;
    private final static String ACTION_RETURNURL_ZEROCASE = null;
    private final static String ACTION_RETURNURL_LINKSTORY = null;
    private final static String ACTION_RETURNURL_AJAXGETPROJECTSTORIES = null;
    private final static String ACTION_RETURNURL_AJAXGETPRODUCTSTORIES = null;
    private final static String ACTION_RETURNURL_AJAXSEARCHPRODUCTSTORIES = null;
    private final static String ACTION_RETURNURL_AJAXGETDETAIL = null;
    private final static String ACTION_RETURNURL_AJAXGETINFO = null;
    private final static String ACTION_RETURNURL_REPORT = null;
    private final static String ACTION_RETURNURL_EXPORT = null;
    private final static String ACTION_RETURNURL_AJAXGETUSERSTORYS = null;
    private final static String ACTION_RETURNURL_AJAXGETSTATUS = null;

    // ----------
    // 接口行为POST参数设置
    // ----------

    static {
        // CREATE
        ACTION_PARAMS_CREATE.put("product", 0);
        ACTION_PARAMS_CREATE.put("branch", 0);
        ACTION_PARAMS_CREATE.put("title", null);
        ACTION_PARAMS_CREATE.put("module", 0);
        ACTION_PARAMS_CREATE.put("plan", null);
        ACTION_PARAMS_CREATE.put("source", null);
        ACTION_PARAMS_CREATE.put("sourceNote", null);
        ACTION_PARAMS_CREATE.put("reviewedBy", null);
        ACTION_PARAMS_CREATE.put("pri", 3);
        ACTION_PARAMS_CREATE.put("estimate", 0);
        ACTION_PARAMS_CREATE.put("spec", null);
        ACTION_PARAMS_CREATE.put("verify", null);
        ACTION_PARAMS_CREATE.put("color", null);
        ACTION_PARAMS_CREATE.put("mailto", null);
        ACTION_PARAMS_CREATE.put("keywords", null);
        ACTION_PARAMS_CREATE.put("type", "story");
        ACTION_PARAMS_CREATE.put("assignedTo", null);

        // EDIT
        ACTION_PARAMS_EDIT.put("product", 0);
        ACTION_PARAMS_EDIT.put("branch", 0);
        ACTION_PARAMS_EDIT.put("module", 0);
        ACTION_PARAMS_EDIT.put("plan", null);
        ACTION_PARAMS_EDIT.put("parent", 0);
        ACTION_PARAMS_EDIT.put("status", null);
        ACTION_PARAMS_EDIT.put("source", null);
        ACTION_PARAMS_EDIT.put("sourceNote", null);
        ACTION_PARAMS_EDIT.put("reviewedBy", null);
        ACTION_PARAMS_EDIT.put("pri", 3);
        ACTION_PARAMS_EDIT.put("estimate", 0);
        ACTION_PARAMS_EDIT.put("color", null);
        ACTION_PARAMS_EDIT.put("mailto", null);
        ACTION_PARAMS_EDIT.put("linkStories[]", null);
        ACTION_PARAMS_EDIT.put("assignedTo", null);
        ACTION_PARAMS_EDIT.put("keywords", null);
        ACTION_PARAMS_EDIT.put("comment", null);

        // CHANGE
        ACTION_PARAMS_CHANGE.put("status", null);
        ACTION_PARAMS_CHANGE.put("spec", null);
        ACTION_PARAMS_CHANGE.put("verify", null);
        ACTION_PARAMS_CHANGE.put("title", null);
        ACTION_PARAMS_CHANGE.put("assignedTo", null);
        ACTION_PARAMS_CHANGE.put("comment", null);

        // REVIEW
        ACTION_PARAMS_REVIEW.put("result", null);
        ACTION_PARAMS_REVIEW.put("closedReason", null);
        ACTION_PARAMS_REVIEW.put("pri", 3);
        ACTION_PARAMS_REVIEW.put("estimate", 0);
        ACTION_PARAMS_REVIEW.put("comment", null);
        ACTION_PARAMS_REVIEW.put("preVersion", null);
        ACTION_PARAMS_REVIEW.put("assignedTo", null);
        ACTION_PARAMS_REVIEW.put("status", null);
        ACTION_PARAMS_REVIEW.put("reviewedBy", null);

        // ACTIVATE
        ACTION_PARAMS_ACTIVATE.put("comment", null);
        ACTION_PARAMS_ACTIVATE.put("assignedTo", null);
        ACTION_PARAMS_ACTIVATE.put("status", "active");

        // ASSIGNTO
        ACTION_PARAMS_ASSIGNTO.put("comment", null);
        ACTION_PARAMS_ASSIGNTO.put("assignedTo", null);

        // CLOSE
        ACTION_PARAMS_CLOSE.put("comment", null);
        ACTION_PARAMS_CLOSE.put("closedReason", null);

        // BATCHCHANGESTAGE
        ACTION_PARAMS_BATCHCHANGESTAGE.put("storyIdList", null);
    }

    // ----------
    // 接口行为URL参数设置
    // ----------

    static {
        // CREATE
        ACTION_URL_PARAMS_CREATE.add("product");
        ACTION_URL_PARAMS_CREATE.add("branch");
        ACTION_URL_PARAMS_CREATE.add("module");
        ACTION_URL_PARAMS_CREATE.add("parent");
        ACTION_URL_PARAMS_CREATE.add("project");
        ACTION_URL_PARAMS_CREATE.add("fromBug");
        ACTION_URL_PARAMS_CREATE.add("plan");
        ACTION_URL_PARAMS_CREATE.add("todo");

        // EDIT
        ACTION_URL_PARAMS_EDIT.add("id");

        // DELETE
        ACTION_URL_PARAMS_DELETE.add("id");
        ACTION_URL_PARAMS_DELETE.add("confirm");

        // CHANGE
        ACTION_URL_PARAMS_CHANGE.add("id");

        // REVIEW
        ACTION_URL_PARAMS_REVIEW.add("id");

        // ACTIVATE
        ACTION_URL_PARAMS_ACTIVATE.add("id");

        // ASSIGNTO
        ACTION_URL_PARAMS_ASSIGNTO.add("id");

        // CLOSE
        ACTION_URL_PARAMS_CLOSE.add("id");

        // BATCHCHANGESTAGE
        ACTION_URL_PARAMS_BATCHCHANGESTAGE.add("stage");

        // BATCHCHANGESTAGE
        ACTION_URL_PARAMS_BATCHCHANGESTAGE.add("branch");
    }

    // ----------
    // 接口行为POST参数日期格式设置
    // ----------
    static {
        PARAMS_DATEFORMAT.put("reviewedDate", "yyyy-MM-dd");
    }

    // ----------
    // 接口实现
    // ----------

    /**
     * create 创建
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean create(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_CREATE;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_CREATE;
        Map<String, Object> actionParams = ACTION_PARAMS_CREATE;
        List<String> actionUrlParams = ACTION_URL_PARAMS_CREATE;
        String returnUrlRegexPrev = ACTION_RETURNURL_CREATE;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_CREATE;

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * edit 编辑
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean edit(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_EDIT;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_EDIT;
        Map<String, Object> actionParams = ACTION_PARAMS_EDIT;
        List<String> actionUrlParams = ACTION_URL_PARAMS_EDIT;
        String returnUrlRegexPrev = ACTION_RETURNURL_EDIT;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_EDIT;

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * delete 删除
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean delete(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_DELETE;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_DELETE;
        Map<String, Object> actionParams = ACTION_PARAMS_DELETE;
        List<String> actionUrlParams = ACTION_URL_PARAMS_DELETE;
        String returnUrlRegexPrev = ACTION_RETURNURL_DELETE;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_DELETE;

        jo.put("confirm", "yes");

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * change 变更
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean change(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_CHANGE;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_CHANGE;
        Map<String, Object> actionParams = ACTION_PARAMS_CHANGE;
        List<String> actionUrlParams = ACTION_URL_PARAMS_CHANGE;
        String returnUrlRegexPrev = ACTION_RETURNURL_CHANGE;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_CHANGE;

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * review 评审
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean review(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_REVIEW;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_REVIEW;
        Map<String, Object> actionParams = ACTION_PARAMS_REVIEW;
        List<String> actionUrlParams = ACTION_URL_PARAMS_REVIEW;
        String returnUrlRegexPrev = ACTION_RETURNURL_REVIEW;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_REVIEW;

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * activate 激活
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean activate(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_ACTIVATE;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_ACTIVATE;
        Map<String, Object> actionParams = ACTION_PARAMS_ACTIVATE;
        List<String> actionUrlParams = ACTION_URL_PARAMS_ACTIVATE;
        String returnUrlRegexPrev = ACTION_RETURNURL_ACTIVATE;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_ACTIVATE;

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * assignTo 指派
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean assignTo(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_ASSIGNTO;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_ASSIGNTO;
        Map<String, Object> actionParams = ACTION_PARAMS_ASSIGNTO;
        List<String> actionUrlParams = ACTION_URL_PARAMS_ASSIGNTO;
        String returnUrlRegexPrev = ACTION_RETURNURL_ASSIGNTO;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_ASSIGNTO;

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * close 关闭
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean close(String zentaoSid, JSONObject jo, ZTResult rst) {
        // 参数赋值
        String moduleName = MODULE_NAME;
        String urlExt = ZenTaoConstants.ZT_URL_EXT;
        String actionName = ACTION_CLOSE;
        HttpMethod actionHttpMethod = ACTION_HTTPMETHOD_CLOSE;
        Map<String, Object> actionParams = ACTION_PARAMS_CLOSE;
        List<String> actionUrlParams = ACTION_URL_PARAMS_CLOSE;
        String returnUrlRegexPrev = ACTION_RETURNURL_CLOSE;
        List<ZTCheckItem> checkList = ACTION_CHECKLIST_CLOSE;

        return ZenTaoHttpHelper.doZTRequest(jo, rst, zentaoSid, urlExt, actionHttpMethod, moduleName, actionName, actionUrlParams, actionParams, PARAMS_DATEFORMAT, returnUrlRegexPrev, checkList);
    }

    /**
     * linkStory 关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean linkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTProductPlanHelper.linkStory(zentaoSid, jo, rst);
    }

    /**
     * unlinkStory 解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean unlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTProductPlanHelper.unlinkStory(zentaoSid, jo, rst);
    }

    /**
     * batchUnlinkStory 批量解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean batchUnlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTProductPlanHelper.batchUnlinkStory(zentaoSid, jo, rst);
    }

    /**
     * projectLinkStory 项目关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean projectLinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTProjectHelper.linkStory(zentaoSid, jo, rst);
    }

    /**
     * projectUnlinkStory 项目解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean projectUnlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTProjectHelper.unlinkStory(zentaoSid, jo, rst);
    }

    /**
     * projectBatchUnlinkStory 项目批量解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean projectBatchUnlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTProjectHelper.batchUnlinkStory(zentaoSid, jo, rst);
    }

    /**
     * releaseLinkStory 项目关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean releaseLinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTReleaseHelper.linkStory(zentaoSid, jo, rst);
    }

    /**
     * releaseUnlinkStory 项目解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean releaseUnlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTReleaseHelper.unlinkStory(zentaoSid, jo, rst);
    }

    /**
     * releaseBatchUnlinkStory 项目批量解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean releaseBatchUnlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTReleaseHelper.batchUnlinkStory(zentaoSid, jo, rst);
    }

    /**
     * buildLinkStory 项目关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean buildLinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTBuildHelper.linkStory(zentaoSid, jo, rst);
    }

    /**
     * buildUnlinkStory 版本解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean buildUnlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTBuildHelper.unlinkStory(zentaoSid, jo, rst);
    }

    /**
     * buildBatchUnlinkStory 版本批量解除关联需求
     *
     * @param zentaoSid
     * @param jo
     * @param rst
     * @return
     */
    public static boolean buildBatchUnlinkStory(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTBuildHelper.batchUnlinkStory(zentaoSid, jo, rst);
    }

    public static boolean importPlanStories(String zentaoSid, JSONObject jo, ZTResult rst) {
        return ZTProjectHelper.importPlanStories(zentaoSid, jo, rst);
    }
}
