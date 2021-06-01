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
import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.service.ITestReportService;
import cn.ibizlab.pms.core.zentao.filter.TestReportSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestReportRuntime;

@Slf4j
@Api(tags = {"测试报告" })
@RestController("StandardAPI-testreport")
@RequestMapping("")
public class TestReportResource {

    @Autowired
    public ITestReportService testreportService;

    @Autowired
    public TestReportRuntime testreportRuntime;

    @Autowired
    @Lazy
    public TestReportMapping testreportMapping;


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目根据起始时间获取概况信息", tags = {"测试报告" },  notes = "根据项目根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/getinfotaskovbytime")
    public ResponseEntity<TestReportDTO> getInfoTaskOvByTimeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTaskOvByTime(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除测试报告", tags = {"测试报告" },  notes = "根据项目删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testreportService.remove(testreport_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'DELETE')")
    @ApiOperation(value = "根据项目批量删除测试报告", tags = {"测试报告" },  notes = "根据项目批量删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        testreportService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）（测试）", tags = {"测试报告" },  notes = "根据项目根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/getinfotesttasks")
    public ResponseEntity<TestReportDTO> getInfoTestTaskSByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskS(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目建立测试报告", tags = {"测试报告" },  notes = "根据项目建立测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports")
    public ResponseEntity<TestReportDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
		testreportService.create(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）", tags = {"测试报告" },  notes = "根据项目根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/getinfotesttaskproject")
    public ResponseEntity<TestReportDTO> getInfoTestTaskProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskProject(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新测试报告", tags = {"测试报告" },  notes = "根据项目更新测试报告")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
		testreportService.update(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目根据测试报告获取基本信息", tags = {"测试报告" },  notes = "根据项目根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/gettestreportbasicinfo")
    public ResponseEntity<TestReportDTO> getTestReportBasicInfoByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getTestReportBasicInfo(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）（单测试）", tags = {"测试报告" },  notes = "根据项目根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/getinfotesttaskr")
    public ResponseEntity<TestReportDTO> getInfoTestTaskRByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskR(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目获取测试报告草稿", tags = {"测试报告" },  notes = "根据项目获取测试报告草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/getdraft")
    public ResponseEntity<TestReportDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TestReportDTO dto) {
        TestReport domain = testreportMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(testreportMapping.toDto(testreportService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取测试报告", tags = {"测试报告" },  notes = "根据项目获取测试报告")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id) {
        TestReport domain = testreportService.get(testreport_id);
        TestReportDTO dto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"测试报告" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/fetchdefault")
	public ResponseEntity<List<TestReportDTO>> fetchTestReportDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TestReportSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestReport> domains = testreportService.searchDefault(context) ;
        List<TestReportDTO> list = testreportMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品根据起始时间获取概况信息", tags = {"测试报告" },  notes = "根据产品根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports/{testreport_id}/getinfotaskovbytime")
    public ResponseEntity<TestReportDTO> getInfoTaskOvByTimeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTaskOvByTime(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
    @ApiOperation(value = "根据产品删除测试报告", tags = {"测试报告" },  notes = "根据产品删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testreports/{testreport_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testreportService.remove(testreport_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
    @ApiOperation(value = "根据产品批量删除测试报告", tags = {"测试报告" },  notes = "根据产品批量删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testreports/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        testreportService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（测试）", tags = {"测试报告" },  notes = "根据产品根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports/{testreport_id}/getinfotesttasks")
    public ResponseEntity<TestReportDTO> getInfoTestTaskSByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskS(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
    @ApiOperation(value = "根据产品建立测试报告", tags = {"测试报告" },  notes = "根据产品建立测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports")
    public ResponseEntity<TestReportDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
		testreportService.create(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）", tags = {"测试报告" },  notes = "根据产品根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports/{testreport_id}/getinfotesttaskproject")
    public ResponseEntity<TestReportDTO> getInfoTestTaskProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskProject(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
    @ApiOperation(value = "根据产品更新测试报告", tags = {"测试报告" },  notes = "根据产品更新测试报告")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
		testreportService.update(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品根据测试报告获取基本信息", tags = {"测试报告" },  notes = "根据产品根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports/{testreport_id}/gettestreportbasicinfo")
    public ResponseEntity<TestReportDTO> getTestReportBasicInfoByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getTestReportBasicInfo(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（单测试）", tags = {"测试报告" },  notes = "根据产品根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports/{testreport_id}/getinfotesttaskr")
    public ResponseEntity<TestReportDTO> getInfoTestTaskRByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskR(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
    @ApiOperation(value = "根据产品获取测试报告草稿", tags = {"测试报告" },  notes = "根据产品获取测试报告草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/getdraft")
    public ResponseEntity<TestReportDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestReportDTO dto) {
        TestReport domain = testreportMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testreportMapping.toDto(testreportService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试报告", tags = {"测试报告" },  notes = "根据产品获取测试报告")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id) {
        TestReport domain = testreportService.get(testreport_id);
        TestReportDTO dto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试报告" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/fetchdefault")
	public ResponseEntity<List<TestReportDTO>> fetchTestReportDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestReportSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestReport> domains = testreportService.searchDefault(context) ;
        List<TestReportDTO> list = testreportMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

