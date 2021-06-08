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
@RestController("StandardAPI-accounttesttask")
@RequestMapping("")
public class AccountTestTaskResource {

    @Autowired
    public ITestTaskService testtaskService;

    @Autowired
    public TestTaskRuntime testtaskRuntime;

    @Autowired
    @Lazy
    public AccountTestTaskMapping accounttesttaskMapping;

    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"测试版本" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounttesttasks/fetchmy")
	public ResponseEntity<List<AccountTestTaskDTO>> fetchmy(@RequestBody TestTaskSearchContext context) {
        Page<TestTask> domains = testtaskService.searchMy(context) ;
        List<AccountTestTaskDTO> list = accounttesttaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"测试版本" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounttesttasks/fetchaccount")
	public ResponseEntity<List<AccountTestTaskDTO>> fetchaccount(@RequestBody TestTaskSearchContext context) {
        Page<TestTask> domains = testtaskService.searchAccount(context) ;
        List<AccountTestTaskDTO> list = accounttesttaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TESTTASK', #accounttesttask_id, 'READ')")
    @ApiOperation(value = "获取测试版本", tags = {"测试版本" },  notes = "获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/accounttesttasks/{accounttesttask_id}")
    public ResponseEntity<AccountTestTaskDTO> get(@PathVariable("accounttesttask_id") Long accounttesttask_id) {
        TestTask domain = testtaskService.get(accounttesttask_id);
        AccountTestTaskDTO dto = accounttesttaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(accounttesttask_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/accounttesttasks/{accounttesttask_id}/{action}")
    public ResponseEntity<AccountTestTaskDTO> dynamicCall(@PathVariable("accounttesttask_id") Long accounttesttask_id , @PathVariable("action") String action , @RequestBody AccountTestTaskDTO accounttesttaskdto) {
        TestTask domain = testtaskService.dynamicCall(accounttesttask_id, action, accounttesttaskMapping.toDomain(accounttesttaskdto));
        accounttesttaskdto = accounttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accounttesttaskdto);
    }

    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"测试版本" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttesttasks/fetchmy")
	public ResponseEntity<List<AccountTestTaskDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TestTaskSearchContext context) {
        
        Page<TestTask> domains = testtaskService.searchMy(context) ;
        List<AccountTestTaskDTO> list = accounttesttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"测试版本" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accounttesttasks/fetchaccount")
	public ResponseEntity<List<AccountTestTaskDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TestTaskSearchContext context) {
        
        Page<TestTask> domains = testtaskService.searchAccount(context) ;
        List<AccountTestTaskDTO> list = accounttesttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_TESTTASK', 'READ')")
    @ApiOperation(value = "根据系统用户获取测试版本", tags = {"测试版本" },  notes = "根据系统用户获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accounttesttasks/{accounttesttask_id}")
    public ResponseEntity<AccountTestTaskDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accounttesttask_id") Long accounttesttask_id) {
        TestTask domain = testtaskService.get(accounttesttask_id);
        AccountTestTaskDTO dto = accounttesttaskMapping.toDto(domain);
        Map<String, Integer> opprivs = testtaskRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Autowired
    cn.ibizlab.pms.core.zentao.mapping.TestTaskDataImport dataimportImpMapping;
    @RequestMapping(method = RequestMethod.POST, value = "/accounttesttasks/import")
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

