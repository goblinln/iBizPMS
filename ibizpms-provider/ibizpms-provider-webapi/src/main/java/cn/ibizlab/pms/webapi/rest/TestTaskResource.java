package cn.ibizlab.pms.webapi.rest;

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
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.TestTask;
import cn.ibizlab.pms.core.zentao.service.ITestTaskService;
import cn.ibizlab.pms.core.zentao.filter.TestTaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestTaskRuntime;

@Slf4j
@Api(tags = {"测试版本"})
@RestController("WebApi-testtask")
@RequestMapping("")
public class TestTaskResource {

    @Autowired
    public ITestTaskService testtaskService;

    @Autowired
    public TestTaskRuntime testtaskRuntime;

    @Autowired
    @Lazy
    public TestTaskMapping testtaskMapping;

    @PreAuthorize("quickTest('ZT_TESTTASK', 'CREATE')")
    @ApiOperation(value = "新建测试版本", tags = {"测试版本" },  notes = "新建测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks")
    @Transactional
    public ResponseEntity<TestTaskDTO> create(@Validated @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
		testtaskService.create(domain);
        if(!testtaskRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTTASK', #testtask_id, 'READ')")
    @ApiOperation(value = "获取测试版本", tags = {"测试版本" },  notes = "获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> get(@PathVariable("testtask_id") Long testtask_id) {
        TestTask domain = testtaskService.get(testtask_id);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(testtask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTTASK', #testtask_id, 'DELETE')")
    @ApiOperation(value = "删除测试版本", tags = {"测试版本" },  notes = "删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testtask_id") Long testtask_id) {
         return ResponseEntity.status(HttpStatus.OK).body(testtaskService.remove(testtask_id));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DELETE')")
    @ApiOperation(value = "批量删除测试版本", tags = {"测试版本" },  notes = "批量删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        testtaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "testtask" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_TESTTASK', #testtask_id, 'UPDATE')")
    @ApiOperation(value = "更新测试版本", tags = {"测试版本" },  notes = "更新测试版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}")
    @Transactional
    public ResponseEntity<TestTaskDTO> update(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
		TestTask domain  = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
		testtaskService.update(domain );
        if(!testtaskRuntime.test(testtask_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(testtask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "激活", tags = {"测试版本" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/activate")
    public ResponseEntity<TestTaskDTO> activate(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
        domain = testtaskService.activate(domain);
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "阻塞", tags = {"测试版本" },  notes = "阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/block")
    public ResponseEntity<TestTaskDTO> block(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
        domain = testtaskService.block(domain);
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'CREATE')")
    @ApiOperation(value = "检查测试版本", tags = {"测试版本" },  notes = "检查测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TestTaskDTO testtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testtaskService.checkKey(testtaskMapping.toDomain(testtaskdto)));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "关闭", tags = {"测试版本" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/close")
    public ResponseEntity<TestTaskDTO> close(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
        domain = testtaskService.close(domain);
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'CREATE')")
    @ApiOperation(value = "获取测试版本草稿", tags = {"测试版本" },  notes = "获取测试版本草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/getdraft")
    public ResponseEntity<TestTaskDTO> getDraft(TestTaskDTO dto) {
        TestTask domain = testtaskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskMapping.toDto(testtaskService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "关联测试用例", tags = {"测试版本" },  notes = "关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/linkcase")
    public ResponseEntity<TestTaskDTO> linkCase(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
        domain = testtaskService.linkCase(domain);
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "移动端测试版本计数器", tags = {"测试版本" },  notes = "移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/mobtesttaskcounter")
    public ResponseEntity<TestTaskDTO> mobTestTaskCounter(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
        domain = testtaskService.mobTestTaskCounter(domain);
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "保存测试版本", tags = {"测试版本" },  notes = "保存测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/save")
    public ResponseEntity<TestTaskDTO> save(@RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        testtaskService.save(domain);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "开始", tags = {"测试版本" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/start")
    public ResponseEntity<TestTaskDTO> start(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
        domain = testtaskService.start(domain);
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "关联测试用例", tags = {"测试版本" },  notes = "关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/unlinkcase")
    public ResponseEntity<TestTaskDTO> unlinkCase(@PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setId(testtask_id);
        domain = testtaskService.unlinkCase(domain);
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"测试版本" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/fetchaccount")
	public ResponseEntity<List<TestTaskDTO>> fetchaccount(@RequestBody TestTaskSearchContext context) {
        Page<TestTask> domains = testtaskService.searchAccount(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试版本" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/fetchdefault")
	public ResponseEntity<List<TestTaskDTO>> fetchdefault(@RequestBody TestTaskSearchContext context) {
        Page<TestTask> domains = testtaskService.searchDefault(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"测试版本" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/fetchmy")
	public ResponseEntity<List<TestTaskDTO>> fetchmy(@RequestBody TestTaskSearchContext context) {
        Page<TestTask> domains = testtaskService.searchMy(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "获取我的测试单", tags = {"测试版本" } ,notes = "获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/fetchmytesttaskpc")
	public ResponseEntity<List<TestTaskDTO>> fetchmytesttaskpc(@RequestBody TestTaskSearchContext context) {
        Page<TestTask> domains = testtaskService.searchMyTestTaskPc(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "获取项目测试单", tags = {"测试版本" } ,notes = "获取项目测试单")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/fetchprojecttesttaskds")
	public ResponseEntity<List<TestTaskDTO>> fetchprojecttesttaskds(@RequestBody TestTaskSearchContext context) {
        Page<TestTask> domains = testtaskService.searchProjectTestTaskDS(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成测试版本报表", tags = {"测试版本"}, notes = "生成测试版本报表")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, TestTaskSearchContext context, HttpServletResponse response) {
        try {
            testtaskRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", testtaskRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, testtaskRuntime);
        }
    }

    @ApiOperation(value = "打印测试版本", tags = {"测试版本"}, notes = "打印测试版本")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("testtask_ids") Set<Long> testtask_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = testtaskRuntime.getDEPrintRuntime(print_id);
        try {
            List<TestTask> domains = new ArrayList<>();
            for (Long testtask_id : testtask_ids) {
                domains.add(testtaskService.get( testtask_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new TestTask[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", testtaskRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", testtask_ids, e.getMessage()), Errors.INTERNALERROR, testtaskRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/{action}")
    public ResponseEntity<TestTaskDTO> dynamicCall(@PathVariable("testtask_id") Long testtask_id , @PathVariable("action") String action , @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskService.dynamicCall(testtask_id, action, testtaskMapping.toDomain(testtaskdto));
        testtaskdto = testtaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品建立测试版本", tags = {"测试版本" },  notes = "根据产品建立测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks")
    public ResponseEntity<TestTaskDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
		testtaskService.create(domain);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', #testtask_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试版本", tags = {"测试版本" },  notes = "根据产品获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id) {
        TestTask domain = testtaskService.get(testtask_id);
        if (domain == null || !(product_id.equals(domain.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', #testtask_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除测试版本", tags = {"测试版本" },  notes = "根据产品删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id) {
        TestTask testget = testtaskService.get(testtask_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(testtaskService.remove(testtask_id));
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除测试版本", tags = {"测试版本" },  notes = "根据产品批量删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        testtaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "testtask" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', #testtask_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新测试版本", tags = {"测试版本" },  notes = "根据产品更新测试版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask testget = testtaskService.get(testtask_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
		testtaskService.update(domain);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品激活", tags = {"测试版本" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/activate")
    public ResponseEntity<TestTaskDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
        domain = testtaskService.activate(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品阻塞", tags = {"测试版本" },  notes = "根据产品阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/block")
    public ResponseEntity<TestTaskDTO> blockByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
        domain = testtaskService.block(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品检查测试版本", tags = {"测试版本" },  notes = "根据产品检查测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestTaskDTO testtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testtaskService.checkKey(testtaskMapping.toDomain(testtaskdto)));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品关闭", tags = {"测试版本" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/close")
    public ResponseEntity<TestTaskDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
        domain = testtaskService.close(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品获取测试版本草稿", tags = {"测试版本" },  notes = "根据产品获取测试版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/getdraft")
    public ResponseEntity<TestTaskDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestTaskDTO dto) {
        TestTask domain = testtaskMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskMapping.toDto(testtaskService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品关联测试用例", tags = {"测试版本" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/linkcase")
    public ResponseEntity<TestTaskDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
        domain = testtaskService.linkCase(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品移动端测试版本计数器", tags = {"测试版本" },  notes = "根据产品移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/mobtesttaskcounter")
    public ResponseEntity<TestTaskDTO> mobTestTaskCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
        domain = testtaskService.mobTestTaskCounter(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品保存测试版本", tags = {"测试版本" },  notes = "根据产品保存测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/save")
    public ResponseEntity<TestTaskDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        testtaskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品开始", tags = {"测试版本" },  notes = "根据产品开始")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/start")
    public ResponseEntity<TestTaskDTO> startByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
        domain = testtaskService.start(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品关联测试用例", tags = {"测试版本" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/unlinkcase")
    public ResponseEntity<TestTaskDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
        domain.setId(testtask_id);
        domain = testtaskService.unlinkCase(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取指定用户数据", tags = {"测试版本" } ,notes = "根据产品获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/fetchaccount")
	public ResponseEntity<List<TestTaskDTO>> fetchAccountByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestTaskSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestTask> domains = testtaskService.searchAccount(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试版本" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/fetchdefault")
	public ResponseEntity<List<TestTaskDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestTaskSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestTask> domains = testtaskService.searchDefault(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取我的数据", tags = {"测试版本" } ,notes = "根据产品获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/fetchmy")
	public ResponseEntity<List<TestTaskDTO>> fetchMyByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestTaskSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestTask> domains = testtaskService.searchMy(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取我的测试单", tags = {"测试版本" } ,notes = "根据产品获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/fetchmytesttaskpc")
	public ResponseEntity<List<TestTaskDTO>> fetchMyTestTaskPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestTaskSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestTask> domains = testtaskService.searchMyTestTaskPc(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目测试单", tags = {"测试版本" } ,notes = "根据产品获取项目测试单")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/fetchprojecttesttaskds")
	public ResponseEntity<List<TestTaskDTO>> fetchProjectTestTaskDSByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestTaskSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestTask> domains = testtaskService.searchProjectTestTaskDS(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目建立测试版本", tags = {"测试版本" },  notes = "根据项目建立测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks")
    public ResponseEntity<TestTaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
		testtaskService.create(domain);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', #testtask_id, 'READ')")
    @ApiOperation(value = "根据项目获取测试版本", tags = {"测试版本" },  notes = "根据项目获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id) {
        TestTask domain = testtaskService.get(testtask_id);
        if (domain == null || !(project_id.equals(domain.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', #testtask_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除测试版本", tags = {"测试版本" },  notes = "根据项目删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id) {
        TestTask testget = testtaskService.get(testtask_id);
        if (testget == null || !(project_id.equals(testget.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(testtaskService.remove(testtask_id));
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除测试版本", tags = {"测试版本" },  notes = "根据项目批量删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        testtaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "testtask" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', #testtask_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新测试版本", tags = {"测试版本" },  notes = "根据项目更新测试版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask testget = testtaskService.get(testtask_id);
        if (testget == null || !(project_id.equals(testget.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
		testtaskService.update(domain);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目激活", tags = {"测试版本" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/activate")
    public ResponseEntity<TestTaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
        domain = testtaskService.activate(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目阻塞", tags = {"测试版本" },  notes = "根据项目阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/block")
    public ResponseEntity<TestTaskDTO> blockByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
        domain = testtaskService.block(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目检查测试版本", tags = {"测试版本" },  notes = "根据项目检查测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody TestTaskDTO testtaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(testtaskService.checkKey(testtaskMapping.toDomain(testtaskdto)));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目关闭", tags = {"测试版本" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/close")
    public ResponseEntity<TestTaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
        domain = testtaskService.close(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取测试版本草稿", tags = {"测试版本" },  notes = "根据项目获取测试版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/getdraft")
    public ResponseEntity<TestTaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, TestTaskDTO dto) {
        TestTask domain = testtaskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskMapping.toDto(testtaskService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目关联测试用例", tags = {"测试版本" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/linkcase")
    public ResponseEntity<TestTaskDTO> linkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
        domain = testtaskService.linkCase(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目移动端测试版本计数器", tags = {"测试版本" },  notes = "根据项目移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/mobtesttaskcounter")
    public ResponseEntity<TestTaskDTO> mobTestTaskCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
        domain = testtaskService.mobTestTaskCounter(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目保存测试版本", tags = {"测试版本" },  notes = "根据项目保存测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/save")
    public ResponseEntity<TestTaskDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        testtaskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目开始", tags = {"测试版本" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/start")
    public ResponseEntity<TestTaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
        domain = testtaskService.start(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据项目关联测试用例", tags = {"测试版本" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/unlinkcase")
    public ResponseEntity<TestTaskDTO> unlinkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProject(project_id);
        domain.setId(testtask_id);
        domain = testtaskService.unlinkCase(domain) ;
        testtaskdto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        testtaskdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskdto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取指定用户数据", tags = {"测试版本" } ,notes = "根据项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/fetchaccount")
	public ResponseEntity<List<TestTaskDTO>> fetchAccountByProject(@PathVariable("project_id") Long project_id,@RequestBody TestTaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestTask> domains = testtaskService.searchAccount(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"测试版本" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/fetchdefault")
	public ResponseEntity<List<TestTaskDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TestTaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestTask> domains = testtaskService.searchDefault(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我的数据", tags = {"测试版本" } ,notes = "根据项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/fetchmy")
	public ResponseEntity<List<TestTaskDTO>> fetchMyByProject(@PathVariable("project_id") Long project_id,@RequestBody TestTaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestTask> domains = testtaskService.searchMy(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我的测试单", tags = {"测试版本" } ,notes = "根据项目获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/fetchmytesttaskpc")
	public ResponseEntity<List<TestTaskDTO>> fetchMyTestTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody TestTaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestTask> domains = testtaskService.searchMyTestTaskPc(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目测试单", tags = {"测试版本" } ,notes = "根据项目获取项目测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/fetchprojecttesttaskds")
	public ResponseEntity<List<TestTaskDTO>> fetchProjectTestTaskDSByProject(@PathVariable("project_id") Long project_id,@RequestBody TestTaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestTask> domains = testtaskService.searchProjectTestTaskDS(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @Autowired
    cn.ibizlab.pms.core.zentao.mapping.TestTaskDataImport dataimportImpMapping;
    @RequestMapping(method = RequestMethod.POST, value = "/testtasks/import")
    public ResponseEntity<JSONObject> importData(@RequestParam(value = "config") String config , @RequestBody List<TestTask> dtos){
        JSONObject rs=new JSONObject();
        if(dtos.size()==0){
            rs.put("rst", 1);
            rs.put("msg", "未传入内容");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rs);
        }
        else{
            if("DataImport".equals(config)){
                rs=testtaskService.importData(dataimportImpMapping.toDomain(dtos),1000,false);
            }
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        }
    }
}

