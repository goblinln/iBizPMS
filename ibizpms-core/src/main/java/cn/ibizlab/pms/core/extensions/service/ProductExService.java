package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproConfigService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.IDocLibService;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.service.impl.ProductServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProductExService")
public class ProductExService extends ProductServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    IActionService iActionService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IDocLibService iDocLibService;

    @Autowired
    IIbzproConfigService iIbzproConfigService;


    String[] diffAttrs = {"desc"};

    @Override
    public boolean create(Product et) {
        // 校验产品名称和产品代号
        String sql = "select * from zt_product where deleted = '0' and (`name` = #{et.name} or `code` = #{et.code})";
        Map<String,Object> param = new HashMap<>();
        param.put("name", et.getName());
        param.put("code", et.getCode());
        List<JSONObject> nameList = this.select(sql,param);
        JSONObject jsonObject = getSettings();
        String[] srfmstatus = jsonObject.getString("srfmstatus").split("_");
        if(!nameList.isEmpty() && nameList.size() > 0) {
            if("project".equals(srfmstatus[0])) {
                throw new RuntimeException(String.format("[项目名称：%1$s]或[项目代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
            }else {
                throw new RuntimeException(String.format("[产品名称：%1$s]或[产品代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
            }

        }
        if(!super.create(et)) {
            return false;
        }
        et.setOrder(et.getId().intValue() * 5);
        super.update(et);
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
        iDocLibService.create(docLib);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PRODUCT.getValue(), null,  StaticDict.Action__type.OPENED.getValue(), "","", null, iActionService);
        return true;
    }

    public JSONObject getSettings() {
        JSONObject  jsonObject = new JSONObject();
        List<IbzproConfig> list = iIbzproConfigService.list(new QueryWrapper<IbzproConfig>().eq("createman", AuthenticationUser.getAuthenticationUser().getUserid()).eq("Scope", StaticDict.ConfigScope.USER.getValue()).eq("vaild", StaticDict.YesNo.ITEM_1.getValue()).orderByDesc("updatedate"));
        if(list.size() > 0) {
            jsonObject.put("srfmstatus", list.get(0).getManagementstatus());
        }else {
            jsonObject.put("srfmstatus", StaticDict.ConfigManagementstatus.PRODUCT_PROJECT.getValue());
        }
        return jsonObject;
    }

    @Override
    public boolean update(Product et) {
        // 校验产品名称和产品代号
        String sql = "select * from zt_product where (`name` = #{et.name} or `code` = #{et.code}) and `id` <> #{et.id}";
        Map<String,Object> param = new HashMap<>();
        param.put("name", et.getName());
        param.put("code", et.getCode());
        param.put("id", et.getId());
        List<JSONObject> nameList = this.select(sql,param);
        if(!nameList.isEmpty() && nameList.size() > 0) {
            throw new RuntimeException(String.format("[产品名称：%1$s]或[产品代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
        }
        Product old = new Product();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        if(!super.update(et)) {
            return false;
        }
        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PRODUCT.getValue(), changes,  StaticDict.Action__type.EDITED.getValue(), "","", null, iActionService);
        }
        return true;
    }

    @Override
    public boolean remove(Long key) {
        if(!removeById(key)) {
            return false;
        }
        iDocLibService.remove(new QueryWrapper<DocLib>().eq(StaticDict.Action__object_type.PRODUCT.getValue(), key));
        return true;
    }

    /**
     * [Close:关闭] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Product close(Product et) {
        String comment = et.getComment();
        Product old = this.get(et.getId());

        et.setStatus(StaticDict.Product__status.CLOSED.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old, et);
        // if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PRODUCT.getValue(), changes,  StaticDict.Action__type.CLOSED.getValue(), comment,"", null, iActionService);

        // }
        return et;
    }

    @Override
    public Product sysGet(Long key) {
        Product product = super.sysGet(key);
        String sql = "SELECT COUNT(1) AS ISTOP FROM `t_ibz_top` t WHERE t.OBJECTID = #{et.id} AND t.TYPE = 'product' AND t.ACCOUNT = #{et.account}";
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",product.getId());
        param.put("account",AuthenticationUser.getAuthenticationUser().getLoginname());
        List<JSONObject> result = this.select(sql, param);
        if (result.size()>0){
            product.setIstop(result.get(0).getInteger("ISTOP"));
        }

        return product;
    }
}

