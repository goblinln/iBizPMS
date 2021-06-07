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
import cn.ibizlab.pms.core.zentao.domain.TestTask;
import cn.ibizlab.pms.core.zentao.service.ITestTaskService;
import cn.ibizlab.pms.core.zentao.filter.TestTaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestTaskRuntime;

@Slf4j
@Api(tags = {"测试版本" })
@RestController("StandardAPI-testtask")
@RequestMapping("")
public class TestTaskResource {

    @Autowired
    public ITestTaskService testtaskService;

    @Autowired
    public TestTaskRuntime testtaskRuntime;

    @Autowired
    @Lazy
    public TestTaskMapping testtaskMapping;


    @VersionCheck(entity = "testtask" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', #testtask_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新测试版本", tags = {"测试版本" },  notes = "根据产品更新测试版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
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
    @ApiOperation(value = "根据产品关联测试用例", tags = {"测试版本" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testtasks/{testtask_id}/unlinkcase")
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

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', #testtask_id, 'READ')")
    @ApiOperation(value = "根据产品获取测试版本", tags = {"测试版本" },  notes = "根据产品获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id) {
        TestTask domain = testtaskService.get(testtask_id);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试版本" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testtasks/fetchdefault")
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
    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品关闭", tags = {"测试版本" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testtasks/{testtask_id}/close")
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
    @ApiOperation(value = "根据产品建立测试版本", tags = {"测试版本" },  notes = "根据产品建立测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testtasks")
    public ResponseEntity<TestTaskDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody TestTaskDTO testtaskdto) {
        TestTask domain = testtaskMapping.toDomain(testtaskdto);
        domain.setProduct(product_id);
		testtaskService.create(domain);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', #testtask_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除测试版本", tags = {"测试版本" },  notes = "根据产品删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testtasks/{testtask_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testtaskService.remove(testtask_id));
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除测试版本", tags = {"测试版本" },  notes = "根据产品批量删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testtasks/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        testtaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品获取测试版本草稿", tags = {"测试版本" },  notes = "根据产品获取测试版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testtasks/getdraft")
    public ResponseEntity<TestTaskDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, TestTaskDTO dto) {
        TestTask domain = testtaskMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(testtaskMapping.toDto(testtaskService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品激活", tags = {"测试版本" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testtasks/{testtask_id}/activate")
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
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testtasks/{testtask_id}/block")
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

    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品开始", tags = {"测试版本" },  notes = "根据产品开始")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testtasks/{testtask_id}/start")
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

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目测试单", tags = {"测试版本" } ,notes = "根据产品获取项目测试单")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testtasks/fetchprojecttesttask")
	public ResponseEntity<List<TestTaskDTO>> fetchProjectTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody TestTaskSearchContext context) {
        context.setN_product_eq(product_id);
        Page<TestTask> domains = testtaskService.searchProjectTestTaskDS(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'DENY')")
    @ApiOperation(value = "根据产品关联测试用例", tags = {"测试版本" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testtasks/{testtask_id}/linkcase")
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


    @VersionCheck(entity = "testtask" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', #testtask_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新测试版本", tags = {"测试版本" },  notes = "根据项目更新测试版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody TestTaskDTO testtaskdto) {
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

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', #testtask_id, 'READ')")
    @ApiOperation(value = "根据项目获取测试版本", tags = {"测试版本" },  notes = "根据项目获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}")
    public ResponseEntity<TestTaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id) {
        TestTask domain = testtaskService.get(testtask_id);
        TestTaskDTO dto = testtaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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


    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', #testtask_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除测试版本", tags = {"测试版本" },  notes = "根据项目删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testtaskService.remove(testtask_id));
    }

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除测试版本", tags = {"测试版本" },  notes = "根据项目批量删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        testtaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("test('ZT_TESTTASK', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目测试单", tags = {"测试版本" } ,notes = "根据项目获取项目测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/fetchprojecttesttask")
	public ResponseEntity<List<TestTaskDTO>> fetchProjectTestTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody TestTaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestTask> domains = testtaskService.searchProjectTestTaskDS(context) ;
        List<TestTaskDTO> list = testtaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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

