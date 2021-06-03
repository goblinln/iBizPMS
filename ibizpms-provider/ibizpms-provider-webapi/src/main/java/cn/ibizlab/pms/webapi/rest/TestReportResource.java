package cn.ibizlab.pms.webapi.rest;

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
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.service.ITestReportService;
import cn.ibizlab.pms.core.zentao.filter.TestReportSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestReportRuntime;

@Slf4j
@Api(tags = {"测试报告" })
@RestController("WebApi-testreport")
@RequestMapping("")
public class TestReportResource {

    @Autowired
    public ITestReportService testreportService;

    @Autowired
    public TestReportRuntime testreportRuntime;

    @Autowired
    @Lazy
    public TestReportMapping testreportMapping;

    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据测试单获取相应信息", tags = {"测试报告" },  notes = "根据测试单获取相应信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/testreports/{testreport_id}/getinfotesttask")
    public ResponseEntity<TestReportDTO> getInfoTestTask(@PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTask(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("quickTest('ZT_TESTREPORT', 'CREATE')")
    @ApiOperation(value = "新建测试报告", tags = {"测试报告" },  notes = "新建测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports")
    @Transactional
    public ResponseEntity<TestReportDTO> create(@Validated @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
		testreportService.create(domain);
        if(!testreportRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestReportDTO dto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'UPDATE')")
    @ApiOperation(value = "更新测试报告", tags = {"测试报告" },  notes = "更新测试报告")
	@RequestMapping(method = RequestMethod.PUT, value = "/testreports/{testreport_id}")
    @Transactional
    public ResponseEntity<TestReportDTO> update(@PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
		TestReport domain  = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
		testreportService.update(domain );
        if(!testreportRuntime.test(testreport_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestReportDTO dto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(testreport_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'DELETE')")
    @ApiOperation(value = "删除测试报告", tags = {"测试报告" },  notes = "删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testreports/{testreport_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testreport_id") Long testreport_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testreportService.remove(testreport_id));
    }

    @PreAuthorize("quickTest('ZT_TESTREPORT', 'DELETE')")
    @ApiOperation(value = "批量删除测试报告", tags = {"测试报告" },  notes = "批量删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testreports/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        testreportService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "获取测试报告", tags = {"测试报告" },  notes = "获取测试报告")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> get(@PathVariable("testreport_id") Long testreport_id) {
        TestReport domain = testreportService.get(testreport_id);
        TestReportDTO dto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(testreport_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_TESTREPORT', 'CREATE')")
    @ApiOperation(value = "获取测试报告草稿", tags = {"测试报告" },  notes = "获取测试报告草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/getdraft")
    public ResponseEntity<TestReportDTO> getDraft(TestReportDTO dto) {
        TestReport domain = testreportMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testreportMapping.toDto(testreportService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TESTREPORT', 'CREATE')")
    @ApiOperation(value = "检查测试报告", tags = {"测试报告" },  notes = "检查测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestReportDTO testreportdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testreportService.checkKey(testreportMapping.toDomain(testreportdto)));
    }

    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据起始时间获取概况信息", tags = {"测试报告" },  notes = "根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/getinfotaskovbytime")
    public ResponseEntity<TestReportDTO> getInfoTaskOvByTime(@PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTaskOvByTime(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据测试报告概况信息（项目报告）", tags = {"测试报告" },  notes = "根据测试报告概况信息（项目报告）")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/getinfotesttaskovproject")
    public ResponseEntity<TestReportDTO> getInfoTestTaskOvProject(@PathVariable("testreport_id") Long testreport_id, TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskOvProject(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据测试单获取相应信息（项目报告）", tags = {"测试报告" },  notes = "根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/getinfotesttaskproject")
    public ResponseEntity<TestReportDTO> getInfoTestTaskProject(@PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskProject(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据测试单获取相应信息（项目报告）（单测试）", tags = {"测试报告" },  notes = "根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/getinfotesttaskr")
    public ResponseEntity<TestReportDTO> getInfoTestTaskR(@PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskR(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据测试单获取相应信息（项目报告）（测试）", tags = {"测试报告" },  notes = "根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/getinfotesttasks")
    public ResponseEntity<TestReportDTO> getInfoTestTaskS(@PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskS(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据测试报告获取基本信息", tags = {"测试报告" },  notes = "根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/gettestreportbasicinfo")
    public ResponseEntity<TestReportDTO> getTestReportBasicInfo(@PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getTestReportBasicInfo(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', #testreport_id, 'READ')")
    @ApiOperation(value = "根据测试报告获取基本信息（项目报告）", tags = {"测试报告" },  notes = "根据测试报告获取基本信息（项目报告）")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/gettestreportproject")
    public ResponseEntity<TestReportDTO> getTestReportProject(@PathVariable("testreport_id") Long testreport_id, TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setId(testreport_id);
        domain = testreportService.getTestReportProject(domain);
        testreportdto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        testreportdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }


    @PreAuthorize("@TestReportRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存测试报告", tags = {"测试报告" },  notes = "保存测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/save")
    public ResponseEntity<TestReportDTO> save(@RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        testreportService.save(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
        Map<String,Integer> opprivs = testreportRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTREPORT', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试报告" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/fetchdefault")
	public ResponseEntity<List<TestReportDTO>> fetchdefault(@RequestBody TestReportSearchContext context) {
        Page<TestReport> domains = testreportService.searchDefault(context) ;
        List<TestReportDTO> list = testreportMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/{action}")
    public ResponseEntity<TestReportDTO> dynamicCall(@PathVariable("testreport_id") Long testreport_id , @PathVariable("action") String action , @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportService.dynamicCall(testreport_id, action, testreportMapping.toDomain(testreportdto));
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息", tags = {"测试报告" },  notes = "根据产品根据测试单获取相应信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testreports/{testreport_id}/getinfotesttask")
    public ResponseEntity<TestReportDTO> getInfoTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTask(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', 'DENY')")
    @ApiOperation(value = "根据产品建立测试报告", tags = {"测试报告" },  notes = "根据产品建立测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports")
    public ResponseEntity<TestReportDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
		testreportService.create(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品更新测试报告", tags = {"测试报告" },  notes = "根据产品更新测试报告")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
		testreportService.update(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品删除测试报告", tags = {"测试报告" },  notes = "根据产品删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testreports/{testreport_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testreportService.remove(testreport_id));
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', 'DENY')")
    @ApiOperation(value = "根据产品批量删除测试报告", tags = {"测试报告" },  notes = "根据产品批量删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testreports/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        testreportService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品获取测试报告", tags = {"测试报告" },  notes = "根据产品获取测试报告")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id) {
        TestReport domain = testreportService.get(testreport_id);
        TestReportDTO dto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', 'DENY')")
    @ApiOperation(value = "根据产品获取测试报告草稿", tags = {"测试报告" },  notes = "根据产品获取测试报告草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/getdraft")
    public ResponseEntity<TestReportDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestReportDTO dto) {
        TestReport domain = testreportMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testreportMapping.toDto(testreportService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', 'DENY')")
    @ApiOperation(value = "根据产品检查测试报告", tags = {"测试报告" },  notes = "根据产品检查测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestReportDTO testreportdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testreportService.checkKey(testreportMapping.toDomain(testreportdto)));
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据起始时间获取概况信息", tags = {"测试报告" },  notes = "根据产品根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/getinfotaskovbytime")
    public ResponseEntity<TestReportDTO> getInfoTaskOvByTimeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTaskOvByTime(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据测试报告概况信息（项目报告）", tags = {"测试报告" },  notes = "根据产品根据测试报告概况信息（项目报告）")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/getinfotesttaskovproject")
    public ResponseEntity<TestReportDTO> getInfoTestTaskOvProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskOvProject(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）", tags = {"测试报告" },  notes = "根据产品根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/getinfotesttaskproject")
    public ResponseEntity<TestReportDTO> getInfoTestTaskProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskProject(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（单测试）", tags = {"测试报告" },  notes = "根据产品根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/getinfotesttaskr")
    public ResponseEntity<TestReportDTO> getInfoTestTaskRByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskR(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（测试）", tags = {"测试报告" },  notes = "根据产品根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/getinfotesttasks")
    public ResponseEntity<TestReportDTO> getInfoTestTaskSByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskS(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据测试报告获取基本信息", tags = {"测试报告" },  notes = "根据产品根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/gettestreportbasicinfo")
    public ResponseEntity<TestReportDTO> getTestReportBasicInfoByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getTestReportBasicInfo(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据产品根据测试报告获取基本信息（项目报告）", tags = {"测试报告" },  notes = "根据产品根据测试报告获取基本信息（项目报告）")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/gettestreportproject")
    public ResponseEntity<TestReportDTO> getTestReportProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        domain.setId(testreport_id);
        domain = testreportService.getTestReportProject(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@TestReportRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品保存测试报告", tags = {"测试报告" },  notes = "根据产品保存测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/save")
    public ResponseEntity<TestReportDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProduct(product_id);
        testreportService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportMapping.toDto(domain));
    }


    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PRODUCT', #product_id, 'READ', 'DENY')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试报告" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/fetchdefault")
	public ResponseEntity<List<TestReportDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestReportSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestReport> domains = testreportService.searchDefault(context) ;
        List<TestReportDTO> list = testreportMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息", tags = {"测试报告" },  notes = "根据项目根据测试单获取相应信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testreports/{testreport_id}/getinfotesttask")
    public ResponseEntity<TestReportDTO> getInfoTestTaskByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTask(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'CREATE', 'DENY')")
    @ApiOperation(value = "根据项目建立测试报告", tags = {"测试报告" },  notes = "根据项目建立测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports")
    public ResponseEntity<TestReportDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
		testreportService.create(domain);
        TestReportDTO dto = testreportMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'UPDATE', #testreport_id, 'DENY')")
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


    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'DELETE', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据项目删除测试报告", tags = {"测试报告" },  notes = "根据项目删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testreportService.remove(testreport_id));
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'DELETE', 'DENY')")
    @ApiOperation(value = "根据项目批量删除测试报告", tags = {"测试报告" },  notes = "根据项目批量删除测试报告")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        testreportService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据项目获取测试报告", tags = {"测试报告" },  notes = "根据项目获取测试报告")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}")
    public ResponseEntity<TestReportDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id) {
        TestReport domain = testreportService.get(testreport_id);
        TestReportDTO dto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'CREATE', 'DENY')")
    @ApiOperation(value = "根据项目获取测试报告草稿", tags = {"测试报告" },  notes = "根据项目获取测试报告草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/getdraft")
    public ResponseEntity<TestReportDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TestReportDTO dto) {
        TestReport domain = testreportMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(testreportMapping.toDto(testreportService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'CREATE', 'DENY')")
    @ApiOperation(value = "根据项目检查测试报告", tags = {"测试报告" },  notes = "根据项目检查测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TestReportDTO testreportdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testreportService.checkKey(testreportMapping.toDomain(testreportdto)));
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
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

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据项目根据测试报告概况信息（项目报告）", tags = {"测试报告" },  notes = "根据项目根据测试报告概况信息（项目报告）")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/getinfotesttaskovproject")
    public ResponseEntity<TestReportDTO> getInfoTestTaskOvProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getInfoTestTaskOvProject(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
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

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
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

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
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

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
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

    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', #testreport_id, 'DENY')")
    @ApiOperation(value = "根据项目根据测试报告获取基本信息（项目报告）", tags = {"测试报告" },  notes = "根据项目根据测试报告获取基本信息（项目报告）")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/gettestreportproject")
    public ResponseEntity<TestReportDTO> getTestReportProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        domain.setId(testreport_id);
        domain = testreportService.getTestReportProject(domain) ;
        testreportdto = testreportMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportdto);
    }

    @PreAuthorize("@TestReportRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目保存测试报告", tags = {"测试报告" },  notes = "根据项目保存测试报告")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/save")
    public ResponseEntity<TestReportDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TestReportDTO testreportdto) {
        TestReport domain = testreportMapping.toDomain(testreportdto);
        domain.setProject(project_id);
        testreportService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testreportMapping.toDto(domain));
    }


    @PreAuthorize("test('ZT_TESTREPORT', 'ZT_PROJECT', #project_id, 'READ', 'DENY')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"测试报告" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/fetchdefault")
	public ResponseEntity<List<TestReportDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TestReportSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestReport> domains = testreportService.searchDefault(context) ;
        List<TestReportDTO> list = testreportMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

