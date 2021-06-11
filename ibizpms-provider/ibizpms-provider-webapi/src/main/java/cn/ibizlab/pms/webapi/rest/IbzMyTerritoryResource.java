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
import cn.ibizlab.pms.core.ibiz.domain.IbzMyTerritory;
import cn.ibizlab.pms.core.ibiz.service.IIbzMyTerritoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzMyTerritorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzMyTerritoryRuntime;

@Slf4j
@Api(tags = {"我的地盘"})
@RestController("WebApi-ibzmyterritory")
@RequestMapping("")
public class IbzMyTerritoryResource {

    @Autowired
    public IIbzMyTerritoryService ibzmyterritoryService;

    @Autowired
    public IbzMyTerritoryRuntime ibzmyterritoryRuntime;

    @Autowired
    @Lazy
    public IbzMyTerritoryMapping ibzmyterritoryMapping;

    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'CREATE')")
    @ApiOperation(value = "新建我的地盘", tags = {"我的地盘" },  notes = "新建我的地盘")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmyterritories")
    @Transactional
    public ResponseEntity<IbzMyTerritoryDTO> create(@Validated @RequestBody IbzMyTerritoryDTO ibzmyterritorydto) {
        IbzMyTerritory domain = ibzmyterritoryMapping.toDomain(ibzmyterritorydto);
		ibzmyterritoryService.create(domain);
        if(!ibzmyterritoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzMyTerritoryDTO dto = ibzmyterritoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmyterritoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_MYTERRITORY', #ibzmyterritory_id, 'READ')")
    @ApiOperation(value = "获取我的地盘", tags = {"我的地盘" },  notes = "获取我的地盘")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmyterritories/{ibzmyterritory_id}")
    public ResponseEntity<IbzMyTerritoryDTO> get(@PathVariable("ibzmyterritory_id") Long ibzmyterritory_id) {
        IbzMyTerritory domain = ibzmyterritoryService.get(ibzmyterritory_id);
        IbzMyTerritoryDTO dto = ibzmyterritoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmyterritoryRuntime.getOPPrivs(ibzmyterritory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_MYTERRITORY', #ibzmyterritory_id, 'DELETE')")
    @ApiOperation(value = "删除我的地盘", tags = {"我的地盘" },  notes = "删除我的地盘")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzmyterritories/{ibzmyterritory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzmyterritory_id") Long ibzmyterritory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzmyterritoryService.remove(ibzmyterritory_id));
    }

    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'DELETE')")
    @ApiOperation(value = "批量删除我的地盘", tags = {"我的地盘" },  notes = "批量删除我的地盘")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzmyterritories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzmyterritoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_MYTERRITORY', #ibzmyterritory_id, 'UPDATE')")
    @ApiOperation(value = "更新我的地盘", tags = {"我的地盘" },  notes = "更新我的地盘")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzmyterritories/{ibzmyterritory_id}")
    @Transactional
    public ResponseEntity<IbzMyTerritoryDTO> update(@PathVariable("ibzmyterritory_id") Long ibzmyterritory_id, @RequestBody IbzMyTerritoryDTO ibzmyterritorydto) {
		IbzMyTerritory domain  = ibzmyterritoryMapping.toDomain(ibzmyterritorydto);
        domain.setId(ibzmyterritory_id);
		ibzmyterritoryService.update(domain );
        if(!ibzmyterritoryRuntime.test(ibzmyterritory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzMyTerritoryDTO dto = ibzmyterritoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmyterritoryRuntime.getOPPrivs(ibzmyterritory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'CREATE')")
    @ApiOperation(value = "检查我的地盘", tags = {"我的地盘" },  notes = "检查我的地盘")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmyterritories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzMyTerritoryDTO ibzmyterritorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzmyterritoryService.checkKey(ibzmyterritoryMapping.toDomain(ibzmyterritorydto)));
    }

    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'CREATE')")
    @ApiOperation(value = "获取我的地盘草稿", tags = {"我的地盘" },  notes = "获取我的地盘草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmyterritories/getdraft")
    public ResponseEntity<IbzMyTerritoryDTO> getDraft(IbzMyTerritoryDTO dto) {
        IbzMyTerritory domain = ibzmyterritoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmyterritoryMapping.toDto(ibzmyterritoryService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'DENY')")
    @ApiOperation(value = "移动端菜单计数器", tags = {"我的地盘" },  notes = "移动端菜单计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmyterritories/mobmenucount")
    public ResponseEntity<IbzMyTerritoryDTO> mobMenuCount() {
        IbzMyTerritory domain =new IbzMyTerritory();
        domain = ibzmyterritoryService.mobMenuCount(domain);
        IbzMyTerritoryDTO ibzmyterritorydto = ibzmyterritoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmyterritorydto);
    }


    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'DENY')")
    @ApiOperation(value = "我的收藏计数器", tags = {"我的地盘" },  notes = "我的收藏计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmyterritories/myfavoritecount")
    public ResponseEntity<IbzMyTerritoryDTO> myFavoriteCount() {
        IbzMyTerritory domain =new IbzMyTerritory();
        domain = ibzmyterritoryService.myFavoriteCount(domain);
        IbzMyTerritoryDTO ibzmyterritorydto = ibzmyterritoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmyterritorydto);
    }


    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'DENY')")
    @ApiOperation(value = "我的地盘移动端计数器", tags = {"我的地盘" },  notes = "我的地盘移动端计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmyterritories/myterritorycount")
    public ResponseEntity<IbzMyTerritoryDTO> myTerritoryCount() {
        IbzMyTerritory domain =new IbzMyTerritory();
        domain = ibzmyterritoryService.myTerritoryCount(domain);
        IbzMyTerritoryDTO ibzmyterritorydto = ibzmyterritoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmyterritorydto);
    }


    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'DENY')")
    @ApiOperation(value = "保存我的地盘", tags = {"我的地盘" },  notes = "保存我的地盘")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmyterritories/save")
    public ResponseEntity<IbzMyTerritoryDTO> save(@RequestBody IbzMyTerritoryDTO ibzmyterritorydto) {
        IbzMyTerritory domain = ibzmyterritoryMapping.toDomain(ibzmyterritorydto);
        ibzmyterritoryService.save(domain);
        IbzMyTerritoryDTO dto = ibzmyterritoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzmyterritoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'NONE')")
	@ApiOperation(value = "获取DEFAULT", tags = {"我的地盘" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmyterritories/fetchdefault")
	public ResponseEntity<List<IbzMyTerritoryDTO>> fetchdefault(@RequestBody IbzMyTerritorySearchContext context) {
        Page<IbzMyTerritory> domains = ibzmyterritoryService.searchDefault(context) ;
        List<IbzMyTerritoryDTO> list = ibzmyterritoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'NONE')")
	@ApiOperation(value = "获取我的工作", tags = {"我的地盘" } ,notes = "获取我的工作")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmyterritories/fetchmywork")
	public ResponseEntity<List<IbzMyTerritoryDTO>> fetchmywork(@RequestBody IbzMyTerritorySearchContext context) {
        Page<IbzMyTerritory> domains = ibzmyterritoryService.searchMyWork(context) ;
        List<IbzMyTerritoryDTO> list = ibzmyterritoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'NONE')")
	@ApiOperation(value = "获取我的工作", tags = {"我的地盘" } ,notes = "获取我的工作")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmyterritories/fetchmyworkmob")
	public ResponseEntity<List<IbzMyTerritoryDTO>> fetchmyworkmob(@RequestBody IbzMyTerritorySearchContext context) {
        Page<IbzMyTerritory> domains = ibzmyterritoryService.searchMyWorkMob(context) ;
        List<IbzMyTerritoryDTO> list = ibzmyterritoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'NONE')")
	@ApiOperation(value = "获取我的工作（项目经理）", tags = {"我的地盘" } ,notes = "获取我的工作（项目经理）")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmyterritories/fetchmyworkpm")
	public ResponseEntity<List<IbzMyTerritoryDTO>> fetchmyworkpm(@RequestBody IbzMyTerritorySearchContext context) {
        Page<IbzMyTerritory> domains = ibzmyterritoryService.searchMyWorkPm(context) ;
        List<IbzMyTerritoryDTO> list = ibzmyterritoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'NONE')")
	@ApiOperation(value = "获取个人信息-个人贡献", tags = {"我的地盘" } ,notes = "获取个人信息-个人贡献")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmyterritories/fetchpersoninfo")
	public ResponseEntity<List<IbzMyTerritoryDTO>> fetchpersoninfo(@RequestBody IbzMyTerritorySearchContext context) {
        Page<IbzMyTerritory> domains = ibzmyterritoryService.searchPersonInfo(context) ;
        List<IbzMyTerritoryDTO> list = ibzmyterritoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_MYTERRITORY', 'NONE')")
	@ApiOperation(value = "获取欢迎", tags = {"我的地盘" } ,notes = "获取欢迎")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmyterritories/fetchwelcome")
	public ResponseEntity<List<IbzMyTerritoryDTO>> fetchwelcome(@RequestBody IbzMyTerritorySearchContext context) {
        Page<IbzMyTerritory> domains = ibzmyterritoryService.searchWelcome(context) ;
        List<IbzMyTerritoryDTO> list = ibzmyterritoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成我的地盘报表", tags = {"我的地盘"}, notes = "生成我的地盘报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzmyterritories/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzMyTerritorySearchContext context, HttpServletResponse response) {
        try {
            ibzmyterritoryRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzmyterritoryRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzmyterritoryRuntime);
        }
    }

    @ApiOperation(value = "打印我的地盘", tags = {"我的地盘"}, notes = "打印我的地盘")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzmyterritories/{ibzmyterritory_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzmyterritory_ids") Set<Long> ibzmyterritory_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzmyterritoryRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzMyTerritory> domains = new ArrayList<>();
            for (Long ibzmyterritory_id : ibzmyterritory_ids) {
                domains.add(ibzmyterritoryService.get( ibzmyterritory_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzMyTerritory[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzmyterritoryRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzmyterritory_ids, e.getMessage()), Errors.INTERNALERROR, ibzmyterritoryRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzmyterritories/{ibzmyterritory_id}/{action}")
    public ResponseEntity<IbzMyTerritoryDTO> dynamicCall(@PathVariable("ibzmyterritory_id") Long ibzmyterritory_id , @PathVariable("action") String action , @RequestBody IbzMyTerritoryDTO ibzmyterritorydto) {
        IbzMyTerritory domain = ibzmyterritoryService.dynamicCall(ibzmyterritory_id, action, ibzmyterritoryMapping.toDomain(ibzmyterritorydto));
        ibzmyterritorydto = ibzmyterritoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmyterritorydto);
    }
}

