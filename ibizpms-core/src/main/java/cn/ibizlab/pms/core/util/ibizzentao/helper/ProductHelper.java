package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.mapper.ProductMapper;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxiang
 */
@Component
public class ProductHelper extends ZTBaseHelper<ProductMapper, Product> {

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    DocLibHelper docLibHelper;

    @Autowired
    IProductService productService;

    @Autowired
    private UserHelper userHelper;

    String[] diffAttrs = {"desc"};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Product et) {

        // 校验产品名称和产品代号
        String sql = "select * from zt_product where deleted = '0' and (`name` = #{et.name} or `code` = #{et.code})";
        Map<String,Object> param = new HashMap<>();
        param.put(FIELD_NAME, et.getName());
        param.put(FIELD_CODE, et.getCode());
        List<JSONObject> nameList = productService.select(sql,param);
        JSONObject jsonObject = userHelper.getSettings();
        String[] srfmstatus = jsonObject.getString("srfmstatus").split("_");
        if(!nameList.isEmpty() && nameList.size() > 0) {
            if("project".equals(srfmstatus[0])) {
                throw new RuntimeException(String.format("[项目名称：%1$s]或[项目代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
            }else {
                throw new RuntimeException(String.format("[产品名称：%1$s]或[产品代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
            }

        }
        fileHelper.processImgURL(et, null, null);
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);

        //更新order
        et.setOrder(et.getId().intValue() * 5);
        internalUpdate(et);

        //DocLib
        DocLib docLib = new DocLib();
        docLib.setType(StaticDict.Doclib__type.PRODUCT.getValue());
        docLib.setOrgid(AuthenticationUser.getAuthenticationUser().getOrgid());
        docLib.setMdeptid(AuthenticationUser.getAuthenticationUser().getMdeptid());
        docLib.setProduct(et.getId());
        if("project".equals(srfmstatus[0])) {
            docLib.setName("项目主库");
        }else {
            docLib.setName("产品主库");
        }

        docLib.setMain(StaticDict.YesNo.ITEM_1.getValue());
        docLib.setAcl(StaticDict.Doclib__acl.DEFAULT.getValue());
        docLibHelper.create(docLib);

        //Action
        actionHelper.create(StaticDict.Action__object_type.PRODUCT.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);

        return true;
    }


    /**
     * edit 编辑
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Product et) {
        // 校验产品名称和产品代号
        String sql = "select * from zt_product where (`name` = #{et.name} or `code` = #{et.code}) and `id` <> #{et.id}";
        Map<String,Object> param = new HashMap<>();
        param.put(FIELD_NAME, et.getName());
        param.put(FIELD_CODE, et.getCode());
        param.put(FIELD_ID, et.getId());
        List<JSONObject> nameList = productService.select(sql,param);
        if(!nameList.isEmpty() && nameList.size() > 0) {
            throw new RuntimeException(String.format("[产品名称：%1$s]或[产品代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
        }
        Product old = new Product();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        fileHelper.processImgURL(et, null, null);
        if (!this.internalUpdate(et)) {
            return false;
        }

        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        if (changes.size() > 0) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCT.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), "", "", null, true);
            actionHelper.logHistory(action.getId(), changes);
        }
        return true;
    }

    /**
     * delete 删除
     *
     * @param key
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        boolean result = removeById(key);

        //删除doclib
        docLibHelper.remove(new QueryWrapper<DocLib>().eq(StaticDict.Action__object_type.PRODUCT.getValue(), key));

        return result;
    }

    /**
     * close 关闭
     *
     * @param et
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Product close(Product et) {
        String comment = et.getComment();
        Product old = this.get(et.getId());

        et.setStatus(StaticDict.Product__status.CLOSED.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCT.getValue(), et.getId(), StaticDict.Action__type.CLOSED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }

        return et;
    }
}
