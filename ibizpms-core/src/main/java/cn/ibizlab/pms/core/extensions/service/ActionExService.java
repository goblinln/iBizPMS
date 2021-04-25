package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.Fixer;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.ProjectProductSearchContext;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.service.IProjectProductService;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.service.impl.ActionServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[系统日志] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ActionExService")
public class ActionExService extends ActionServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    String[] processType = new String[]{StaticDict.Action__object_type.STORY.getValue(),
            StaticDict.Action__object_type.PRODUCTPLAN.getValue(),
            StaticDict.Action__object_type.RELEASE.getValue(),
            StaticDict.Action__object_type.TASK.getValue(),
            StaticDict.Action__object_type.BUILD.getValue(),
            StaticDict.Action__object_type.BUG.getValue(),
            StaticDict.Action__object_type.CASE.getValue(),
            StaticDict.Action__object_type.TESTTASK.getValue(),
            StaticDict.Action__object_type.DOC.getValue()};

    /**
     * 只关联产品
     */
    private static String PRODUCT_IN = StaticDict.Action__object_type.STORY.getValue() +
            "," + StaticDict.Action__object_type.CASE.getValue() +
            "," + StaticDict.Action__object_type.PRODUCTPLAN.getValue();

    /**
     * 产品和项目都有关联
     */
    private static String PRODUCT_PROJECT_IN = StaticDict.Action__object_type.BUILD.getValue() +
            "," + StaticDict.Action__object_type.BUG.getValue() +
            "," + StaticDict.Action__object_type.DOC.getValue() +
            "," + StaticDict.Action__object_type.TESTTASK.getValue();

    @Autowired
    IProjectProductService projectProductService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IStoryService iStoryService;

    @Autowired
    ICaseService iCaseService;

    @Override
    public boolean create(Action et) {
        et.setComment(et.getComment() == null ? "" : et.getComment());
        String noticeusers = et.getNoticeusers();
        String files = et.getFiles();
        this.createHis(et);
        // 保存文件
        // 更新file
        File file = new File();
        file.set("files",files);
        String extra = "0";
        if(files != null) {
            if (StaticDict.Action__object_type.STORY.getValue().equals(et.getObjecttype())) {
                Story story = iStoryService.get(et.getObjectid());
                if (story != null && story.getVersion() != null) {
                    extra = String.valueOf(story.getVersion());
                }
            } else if (StaticDict.Action__object_type.CASE.getValue().equals(et.getObjecttype())) {
                Case case1 = iCaseService.get(et.getObjectid());
                if (case1 != null && case1.getVersion() != null) {
                    extra = String.valueOf(case1.getVersion());
                }
            }
        }
        file.setExtra(extra);
        iFileService.updateObjectID(file);
        return true;
    }

    /**
     * [CreateHis:创建历史日志] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Action createHis(Action et) {
        if (et.getActor() == null) {
            et.setActor(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        Long objectID = et.getObjectid();
        String objectType = et.getObjecttype().replace("`", "");
        et.setDate(ZTDateUtil.now());
        et.setRead(StaticDict.YesNo.ITEM_0.getValue());
        et.setProduct(",0,");
        et.setProject(0L);
        if (StringUtils.compare(objectType, StaticDict.Action__object_type.PRODUCT.getValue()) == 0) {
            et.setProduct("," + objectID + ",");
        } else if (StringUtils.compare(objectType, StaticDict.Action__object_type.PROJECT.getValue()) == 0) {
            ProjectProductSearchContext ctx = new ProjectProductSearchContext();
            ctx.setN_project_eq(objectID);
            List<ProjectProduct> projectProducts = projectProductService.searchDefault(ctx).getContent();
            String products = "";
            for (int i = 0; i < projectProducts.size(); i++) {
                if (i > 0) {
                    products += ",";
                }
                products += String.valueOf(projectProducts.get(i).getProduct());
            }
            et.setProduct("," + products + ",");
            et.setProject(objectID);
        } else if (Arrays.deepToString(processType).contains(objectType)) {
            JSONObject jsonObject = getProductAndProject(objectType, objectID);
            et.setProduct(jsonObject.getString(StaticDict.Action__object_type.PRODUCT.getValue()));
            et.setProject(jsonObject.getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
            log.info(processType + "product、project设置未实现");
        }
        super.create(et);
        return et;
    }

    /**
     * @param objectType
     * @param objectId
     * @return
     */
    public JSONObject getProductAndProject(String objectType, Long objectId) {
        String fields = "";
        if (PRODUCT_IN.contains(objectType)) {
            fields = "CONCAT_WS('',',',product,',') as product";
        } else if (StaticDict.Action__object_type.TASK.getValue().equals(objectType)) {
            fields = "project, story";
        } else if (PRODUCT_PROJECT_IN.contains(objectType)) {
            fields = "CONCAT_WS('',',',product,',') as product, project";
        } else if (StaticDict.Action__object_type.RELEASE.getValue().equals(objectType)) {
            fields = "CONCAT_WS('',',',product,',') as product, build";
        }

        String sql = String.format("select %1$s from zt_%2$s where id = %3$s ", fields, objectType, objectId);

        List<JSONObject> jsonObjectList = projectProductService.select(sql, null);
        JSONObject record = jsonObjectList.get(0);

        if (objectType.equals(StaticDict.Action__object_type.STORY.getValue())) {
            String storySql = String.format("select project from zt_projectstory where story = %1$s ORDER BY project desc", objectId);
            List<JSONObject> list = projectProductService.select(storySql, null);
            if (list.size() > 0) {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), list.get(0).getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
            } else {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), 0L);
            }
        } else if (objectType.equals(StaticDict.Action__object_type.RELEASE.getValue())) {
            String releaseSql = String.format("select project from zt_build where id = %1$s ", record.getLongValue(StaticDict.Action__object_type.BUILD.getValue()));
            List<JSONObject> list = projectProductService.select(releaseSql, null);
            if (list.size() > 0) {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), list.get(0).getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
            } else {
                record.put(StaticDict.Action__object_type.PROJECT.getValue(), 0L);
            }
        } else if (objectType.equals(StaticDict.Action__object_type.TASK.getValue())) {
            if (record.getLongValue(StaticDict.Action__object_type.STORY.getValue()) != 0L) {
                String storySql = String.format("select CONCAT_WS('',',',product,',') as product from zt_story where id = %1$s ", record.getLongValue(StaticDict.Action__object_type.STORY.getValue()));
                List<JSONObject> list = projectProductService.select(storySql, null);
                if (list.size() > 0) {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), list.get(0).getString(StaticDict.Action__object_type.PRODUCT.getValue()));
                } else {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), ",0,");
                }
            } else {
                String projectProductSql = String.format("select CONCAT_WS('',',',ifnull(GROUP_CONCAT(product ORDER BY product asc),0),',') as product from zt_projectproduct where project = %1$s GROUP BY project ", record.getLongValue(StaticDict.Action__object_type.PROJECT.getValue()));
                List<JSONObject> list = projectProductService.select(projectProductSql, null);
                if (list.size() > 0) {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), list.get(0).getString(StaticDict.Action__object_type.PRODUCT.getValue()));
                } else {
                    record.put(StaticDict.Action__object_type.PRODUCT.getValue(), ",0,");
                }
            }
        }

        return record;
    }
    /**
     * [EditComment:编辑备注信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Action editComment(Action et) {
        et.setDate(ZTDateUtil.now());
        this.update(et);
        return et;
    }
    /**
     * [ManagePmsEe:Pms企业专用] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Action managePmsEe(Action et) {
        return super.managePmsEe(et);
    }
}

