package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.mapper.CaseMapper;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class CaseHelper extends ZTBaseHelper<CaseMapper, Case> {

    @Autowired
    FileHelper fileHelper;

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    CaseHelper caseHelper;

    @Autowired
    CaseStepHelper caseStepHelper;

    @Autowired
    TestTaskHelper testTaskHelper;

    @Autowired
    TestSuiteHelper testSuiteHelper;

    @Autowired
    TestRunHelper testRunHelper;

    @Autowired
    SuiteCaseHelper suiteCaseHelper;

    @Autowired
    TestResultHelper testResultHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Case et) {
        List<CaseStep> caseSteps = et.getCasesteps();
        if(et.getFromcaseid()!=0){
            Case cas = caseHelper.get(et.getFromcaseid());
            et.setFromcaseversion(cas.getVersion());
        }
        if(et.getStory() != null && et.getStory() != 0) {
            et.setStoryversion(storyHelper.get(et.getStory()).getVersion());
        }
        String files = et.getFiles();
        if (!super.create(et)) {
            return false;
        }
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.CASE.getValue(), files, "");
        createCaseStep(et,caseSteps);
        actionHelper.create(StaticDict.Action__object_type.CASE.getValue(),et.getId(), StaticDict.Action__type.OPENED.getValue(), "","",null,true);

        return true;
    }

    public void createCaseStep(Case et, List<CaseStep> caseSteps) {
        if (caseSteps != null) {
            Long parent = 0L;
            for (CaseStep caseStep : caseSteps) {
                if(StringUtils.isBlank(caseStep.getDesc())) {
                    continue;
                }
                caseStep.setType(caseStep.getType() != null && StaticDict.Casestep__type.ITEM.getValue().equals(caseStep.getType()) && parent == 0 ? StaticDict.Casestep__type.STEP.getValue() : caseStep.getType());
                String type = caseStep.getType();
                if(StaticDict.Casestep__type.ITEM.getValue().equals(type)) {
                    caseStep.setParent(parent);
                }
                caseStep.setIbizcase(et.getId());
                caseStep.setVersion(et.getVersion());
                caseStepHelper.create(caseStep);
                if(StaticDict.Casestep__type.GROUP.getValue().equals(type)) {parent = caseStep.getId();}
                if(StaticDict.Casestep__type.STEP.getValue().equals(type)) {parent = 0L;}
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Case et) {
        String comment = et.getComment() == null ? "" : et.getComment();
        List<CaseStep> caseSteps = et.getCasesteps() ;
        Case old = new Case();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        et.setStoryversion(old.getStoryversion());

        List<CaseStep> list = caseStepHelper.list(new QueryWrapper<CaseStep>().eq("`case`", et.getId()).eq(FIELD_VERSION, old.getVersion()));
        boolean caseStepFlag = false;
        if(list.size() == caseSteps.size()) {
            int i = 0;
            for(CaseStep caseStep : list) {
                CaseStep oldCaseStep = new CaseStep();
                CachedBeanCopier.copy(caseStep, oldCaseStep);
                oldCaseStep.setParent(0L);
                CaseStep newCaseStep = new CaseStep();
                CachedBeanCopier.copy(caseSteps.get(i), newCaseStep);
                newCaseStep.setParent(0L);
                if(newCaseStep.getId() == null || newCaseStep.getId() ==0) {
                    caseStepFlag = true;
                    break;
                }

                List<History> changes = ChangeUtil.diff(oldCaseStep, newCaseStep);
                if(changes.size() > 0){
                    caseStepFlag = true;
                    break;
                }

                i ++;
            }
        }else {
            caseStepFlag = true;
        }
        if (caseStepFlag) {
            et.setVersion(old.getVersion() + 1);
        }
        et.setLastrunresult(old.getLastrunresult());
        et.setLastrundate(old.getLastrundate());
        et.setLastrunner(old.getLastrunner());
        String files = et.getFiles();
        boolean bOk = super.edit(et);
        if (!bOk) {
            return bOk;
        }
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.CASE.getValue(), files, et.getVersion().toString());
        if (caseStepFlag) {
            createCaseStep(et,caseSteps);
        }

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0) {
            Action action = actionHelper.create(StaticDict.Action__object_type.CASE.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), comment, "", null, true);
            actionHelper.logHistory(action.getId(), changes);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        return super.delete(key);
    }

    @Transactional(rollbackFor = Exception.class)
    public Case linkCase(Case et) {
        if (et.getTask() == null) {
            return et;
        }
        if (et.get(FIELD_IDS) == null) {
            return et;
        }
        TestTask testTask = new TestTask();
        testTask.setId(Long.parseLong(et.getTask().split(MULTIPLE_CHOICE)[0]));

        testTask.set(FIELD_CASES,et.get(FIELD_IDS));
        testTask.set(FIELD_VERSIONS, et.get(FIELD_VERSIONS));
        testTaskHelper.linkCase(testTask);
        return et ;
    }


    @Transactional(rollbackFor = Exception.class)
    public Case mobLinkCase(Case et) {
        if (et.getTask() == null) {
            return et;
        }
        if (et.get(FIELD_IDS) == null) {
            return et;
        }
        TestTask testTask = new TestTask();
        testTask.setId(Long.parseLong(et.getTask().split(MULTIPLE_CHOICE)[0]));

        String ids = "";
        String version = "";
        ArrayList<Map> list = (ArrayList) et.get(FIELD_SRFACTIONPARAM);
        for (Map data: list) {
            if (!"".equals(ids)){
                ids += MULTIPLE_CHOICE;
            }
            if (!"".equals(version)){
                version += MULTIPLE_CHOICE;
            }
            ids += data.get(FIELD_ID);
            version += data.get(FIELD_VERSION);
        }

        testTask.set(FIELD_CASES,ids);
        testTask.set(FIELD_VERSIONS, version);
        testTaskHelper.linkCase(testTask);
        return et ;
    }

    @Transactional(rollbackFor = Exception.class)
    public Case unlinkCase(Case et) {
        testRunHelper.delete(et.getId());
        return et ;
    }

    @Transactional(rollbackFor = Exception.class)
    public Case runCase(Case et) {
        createResult(et);

        return et;
    }

    public void createResult(Case et) {
        Long caseId = Long.parseLong(et.get(StaticDict.Action__object_type.CASE.getValue()).toString());
        if(caseId == null || caseId == 0L) {
            return;
        }
        Long runId = et.getId();

        Map<Integer, Object> stepResults = new HashMap<>();
        List<CaseStep> caseSteps = et.getCasesteps();
        et = this.get(caseId);
        String caseResult = StaticDict.Testcase__result.PASS.getValue();

        if(caseSteps != null) {
            for(CaseStep caseStep : caseSteps) {
                if(caseStep.getSteps() == null || "".equals(caseStep.getSteps())) {
                    if(caseStep.getReals() == null || "".equals(caseStep.getReals())) {
                        caseStep.setSteps(StaticDict.Testcase__result.PASS.getValue());
                    }else {
                        caseStep.setSteps(StaticDict.Testcase__result.FAIL.getValue());
                    }
                }
                if(!StaticDict.Testcase__result.N_A.getValue().equals(caseStep.getSteps()) && !StaticDict.Testcase__result.PASS.getValue().equals(caseStep.getSteps())) {
                    caseResult = caseStep.getSteps();
                    break;
                }
            }
            for(CaseStep caseStep : caseSteps) {
                if(caseStep.getSteps() == null || "".equals(caseStep.getSteps())) {
                    if(caseStep.getReals() == null || "".equals(caseStep.getReals())) {
                        caseStep.setSteps(StaticDict.Testcase__result.PASS.getValue());
                    }else {
                        caseStep.setSteps(StaticDict.Testcase__result.FAIL.getValue());
                    }
                }
                Map<String, String> jsonObject = new HashMap<>();
                jsonObject.put(FIELD_REAL,caseStep.getReals() != null ? caseStep.getReals() : "");
                jsonObject.put(FIELD_RESULT,caseStep.getSteps());
                stepResults.put(Integer.parseInt(String.valueOf(caseStep.getId())), jsonObject);
            }
        }
        PHPSerializerHelper phpSerializer = new PHPSerializerHelper();
        String ss = "";
        try {
            ss = new String(phpSerializer.serialize(stepResults), "UTF-8");
       } catch (Exception e) {
            e.printStackTrace();
        }
        TestResult testResult = new TestResult();
        testResult.setRun(runId);
        testResult.setCaseresult(caseResult);
        testResult.setStepresults(ss);
        testResult.setIbizcase(caseId);
        testResult.setVersion(et.getVersion());
        testResult.setLastrunner(AuthenticationUser.getAuthenticationUser().getUsername());
        testResult.setDate(ZTDateUtil.now());
        testResultHelper.create(testResult);
        Case cases = new Case();
        cases.setId(caseId);
        cases.setLastrunner(AuthenticationUser.getAuthenticationUser().getUsername());
        cases.setLastrundate(ZTDateUtil.now());
        cases.setLastrunresult(caseResult);
        this.internalUpdate(cases);
        if(runId != null && runId != 0) {
            TestRun testRun = new TestRun();
            testRun.setId(runId);
            testRun.setStatus(StaticDict.Testrun__status.BLOCKED.getValue().equals(caseResult) ? StaticDict.Testrun__status.BLOCKED.getValue() : StaticDict.Testrun__status.DONE.getValue());
            testRun.setLastrundate(ZTDateUtil.now());
            testRun.setLastrunner(AuthenticationUser.getAuthenticationUser().getUsername());
            testRun.setLastrunresult(caseResult);
            testRunHelper.internalUpdate(testRun);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Case unlinkSuiteCase(Case et) {
        suiteCaseHelper.remove(new QueryWrapper<SuiteCase>().eq(FIELD_SUITE,et.get(FIELD_SUITE)).eq("`case`",et.get(StaticDict.Action__object_type.CASE.getValue()))) ;
        return et ;
    }

    @Transactional(rollbackFor = Exception.class)
    public Case testRunCase(Case et) {
        createResult(et);
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Case testsuitelinkCase(Case et) {
        if (et.get(FIELD_SUITE) == null) {
            return et;
        }
        if (et.get(FIELD_IDS) == null) {
            return et;
        }
        TestSuite testSuite = new TestSuite();
        testSuite.setId(Long.parseLong(et.get(FIELD_SUITE).toString().split(MULTIPLE_CHOICE)[0]));
        testSuite.set(FIELD_CASES,et.get(FIELD_IDS));
        testSuite.set(FIELD_VERSIONS, et.get(FIELD_VERSIONS));
        testSuiteHelper.linkCase(testSuite);
        return et ;
    }

    @Transactional(rollbackFor = Exception.class)
    public Case confirmChange(Case et) {
        Case cas = this.getById(et.getId());
        TestRun testRun = new TestRun();
        testRun.setVersion(cas.getVersion());
        Map<String,Object> param = new HashMap<>();
        param.put("`case`",cas.getId());
        param.put(StaticDict.Action__object_type.TASK.getValue(), et.getTask());
        testRunHelper.update(testRun,(Wrapper) testRun.getUpdateWrapper(true).allEq(param));
        return et;
    }

    @Autowired
    StoryHelper storyHelper;

    @Transactional(rollbackFor = Exception.class)
    public Case confirmstorychange(Case et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Case old = new Case();
        CachedBeanCopier.copy(get(et.getId()), old);
        if (old.getStory() != 0) {
            Story story = storyHelper.getById(old.getStory());
            old.setStoryversion(story.getVersion());
        }
        caseHelper.internalUpdate(old);
        actionHelper.create(StaticDict.Action__object_type.CASE.getValue(), et.getId(), StaticDict.Action__type.CONFIRMED.getValue(), comment,"",null,true);
        return et;
    }

}
