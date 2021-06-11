package cn.ibizlab.pms.standardapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.TestResult;
import cn.ibizlab.pms.core.zentao.service.ITestResultService;
import cn.ibizlab.pms.core.zentao.filter.TestResultSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestResultRuntime;

@Slf4j
@Api(tags = {"测试结果"})
@RestController("StandardAPI-testreult")
@RequestMapping("")
public class TestReultResource {

    @Autowired
    public ITestResultService testresultService;

    @Autowired
    public TestResultRuntime testresultRuntime;

    @Autowired
    @Lazy
    public TestReultMapping testreultMapping;



    @PreAuthorize("quickTest('ZT_TESTRESULT', 'READ')")
    @ApiOperation(value = "根据产品测试用例获取测试结果", tags = {"测试结果" },  notes = "根据产品测试用例获取测试结果")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testcases/{case_id}/testreults/{testreult_id}")
    public ResponseEntity<TestReultDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testreult_id") Long testreult_id) {
        TestResult domain = testresultService.get(testreult_id);
        if (domain == null || domain.getIbizcase() != case_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestReultDTO dto = testreultMapping.toDto(domain);
        Map<String, Integer> opprivs = testresultRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}

