package cn.ibizlab.pms.core.zentao.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Case] 服务对象接口
 */
public interface ICaseService extends IService<Case> {

    boolean create(Case et);
    void createBatch(List<Case> list);
    boolean update(Case et);
    boolean sysUpdate(Case et);
    void updateBatch(List<Case> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Case get(Long key);
    Case sysGet(Long key);
    Case getDraft(Case et);
    Case caseFavorite(Case et);
    boolean caseFavoriteBatch(List<Case> etList);
    Case caseNFavorite(Case et);
    boolean caseNFavoriteBatch(List<Case> etList);
    boolean checkKey(Case et);
    Case confirmChange(Case et);
    boolean confirmChangeBatch(List<Case> etList);
    Case confirmstorychange(Case et);
    boolean confirmstorychangeBatch(List<Case> etList);
    Case getByTestTask(Case et);
    boolean getByTestTaskBatch(List<Case> etList);
    Case getTestTaskCntRun(Case et);
    boolean getTestTaskCntRunBatch(List<Case> etList);
    Case linkCase(Case et);
    boolean linkCaseBatch(List<Case> etList);
    Case mobLinkCase(Case et);
    boolean mobLinkCaseBatch(List<Case> etList);
    Case runCase(Case et);
    boolean runCaseBatch(List<Case> etList);
    Case runCases(Case et);
    boolean runCasesBatch(List<Case> etList);
    boolean save(Case et);
    void saveBatch(List<Case> list);
    Case testRunCase(Case et);
    boolean testRunCaseBatch(List<Case> etList);
    Case testRunCases(Case et);
    boolean testRunCasesBatch(List<Case> etList);
    Case testsuitelinkCase(Case et);
    boolean testsuitelinkCaseBatch(List<Case> etList);
    Case unlinkCase(Case et);
    boolean unlinkCaseBatch(List<Case> etList);
    Case unlinkCases(Case et);
    boolean unlinkCasesBatch(List<Case> etList);
    Case unlinkSuiteCase(Case et);
    boolean unlinkSuiteCaseBatch(List<Case> etList);
    Case unlinkSuiteCases(Case et);
    boolean unlinkSuiteCasesBatch(List<Case> etList);
    List<Case> select(CaseSearchContext context);
    List<Case> selectQueryByBatchNew(CaseSearchContext context);
    List<Case> selectQueryByCurOpenedCase(CaseSearchContext context);
    List<Case> selectQueryByCurSuite(CaseSearchContext context);
    List<Case> selectQueryByCurTestTask(CaseSearchContext context);
    List<Case> selectQueryByDefault(CaseSearchContext context);
    List<Case> selectQueryByESBulk(CaseSearchContext context);
    List<Case> selectQueryByModuleRePortCase(CaseSearchContext context);
    List<Case> selectQueryByModuleRePortCaseEntry(CaseSearchContext context);
    List<Case> selectQueryByModuleRePortCase_Project(CaseSearchContext context);
    List<Case> selectQueryByMyFavorite(CaseSearchContext context);
    List<Case> selectQueryByNotCurTestSuite(CaseSearchContext context);
    List<Case> selectQueryByNotCurTestTask(CaseSearchContext context);
    List<Case> selectQueryByNotCurTestTaskProject(CaseSearchContext context);
    List<Case> selectQueryByRePortCase(CaseSearchContext context);
    List<Case> selectQueryByRePortCaseEntry(CaseSearchContext context);
    List<Case> selectQueryByRePortCase_Project(CaseSearchContext context);
    List<Case> selectQueryByRunERRePortCase(CaseSearchContext context);
    List<Case> selectQueryByRunERRePortCaseEntry(CaseSearchContext context);
    List<Case> selectQueryByRunERRePortCase_Project(CaseSearchContext context);
    List<Case> selectQueryByRunRePortCase(CaseSearchContext context);
    List<Case> selectQueryByRunRePortCaseEntry(CaseSearchContext context);
    List<Case> selectQueryByRunRePortCase_Project(CaseSearchContext context);
    List<Case> selectQueryByView(CaseSearchContext context);

    Page<Case> searchBatchNew(CaseSearchContext context);
    Page<Case> searchCurOpenedCase(CaseSearchContext context);
    Page<Case> searchCurSuite(CaseSearchContext context);
    Page<Case> searchCurTestTask(CaseSearchContext context);
    Page<Case> searchDefault(CaseSearchContext context);
    Page<Case> searchESBulk(CaseSearchContext context);
    Page<Case> searchModuleRePortCase(CaseSearchContext context);
    Page<Case> searchModuleRePortCaseEntry(CaseSearchContext context);
    Page<Case> searchModuleRePortCase_Project(CaseSearchContext context);
    Page<Case> searchMyFavorites(CaseSearchContext context);
    Page<Case> searchNotCurTestSuite(CaseSearchContext context);
    Page<Case> searchNotCurTestTask(CaseSearchContext context);
    Page<Case> searchNotCurTestTaskProject(CaseSearchContext context);
    Page<Case> searchRePortCase(CaseSearchContext context);
    Page<Case> searchRePortCaseEntry(CaseSearchContext context);
    Page<Case> searchRePortCase_Project(CaseSearchContext context);
    Page<Case> searchRunERRePortCase(CaseSearchContext context);
    Page<Case> searchRunERRePortCaseEntry(CaseSearchContext context);
    Page<Case> searchRunERRePortCase_Project(CaseSearchContext context);
    Page<Case> searchRunRePortCase(CaseSearchContext context);
    Page<Case> searchRunRePortCaseEntry(CaseSearchContext context);
    Page<Case> searchRunRePortCase_Project(CaseSearchContext context);
    List<Case> selectByBranch(Long id);
    void removeByBranch(Long id);
    List<Case> selectByFrombug(Long id);
    void removeByFrombug(Long id);
    List<Case> selectByFromcaseid(Long id);
    void removeByFromcaseid(Long id);
    List<Case> selectByModule(Long id);
    void removeByModule(Long id);
    List<Case> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<Case> selectByStory(Long id);
    void removeByStory(Long id);
    List<Case> selectByLib(Long id);
    void removeByLib(Long id);
    Case dynamicCall(Long key, String action, Case et);
    /**
     *自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     *自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

    List<Case> getCaseByIds(List<Long> ids);
    List<Case> getCaseByEntities(List<Case> entities);
}


