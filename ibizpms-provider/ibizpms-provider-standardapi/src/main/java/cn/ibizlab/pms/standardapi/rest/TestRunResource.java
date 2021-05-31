package cn.ibizlab.pms.standardapi.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletRequest;
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
import cn.ibizlab.pms.core.zentao.domain.TestRun;
import cn.ibizlab.pms.core.zentao.service.ITestRunService;
import cn.ibizlab.pms.core.zentao.filter.TestRunSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestRunRuntime;

@Slf4j
@Api(tags = {"测试运行" })
@RestController("StandardAPI-testrun")
@RequestMapping("")
public class TestRunResource {

    @Autowired
    public ITestRunService testrunService;

    @Autowired
    public TestRunRuntime testrunRuntime;

    @Autowired
    @Lazy
    public TestRunMapping testrunMapping;



    @PreAuthorize("@TestRunRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品测试用例建立测试运行", tags = {"测试运行" },  notes = "根据产品测试用例建立测试运行")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testcases/{case_id}/testruns")
    public ResponseEntity<TestRunDTO> createByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody TestRunDTO testrundto) {
        TestRun domain = testrunMapping.toDomain(testrundto);
        domain.setIbizcase(case_id);
		testrunService.create(domain);
        if(!testrunRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestRunDTO dto = testrunMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

