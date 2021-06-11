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
import cn.ibizlab.pms.core.ou.domain.SysPost;
import cn.ibizlab.pms.core.ou.service.ISysPostService;
import cn.ibizlab.pms.core.ou.filter.SysPostSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"岗位"})
@RestController("WebApi-syspost")
@RequestMapping("")
public class SysPostResource {

    @Autowired
    public ISysPostService syspostService;


    @Autowired
    @Lazy
    public SysPostMapping syspostMapping;

    @ApiOperation(value = "新建岗位", tags = {"岗位" },  notes = "新建岗位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysposts")
    @Transactional
    public ResponseEntity<SysPostDTO> create(@Validated @RequestBody SysPostDTO syspostdto) {
        SysPost domain = syspostMapping.toDomain(syspostdto);
		syspostService.create(domain);
        SysPostDTO dto = syspostMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取岗位", tags = {"岗位" },  notes = "获取岗位")
	@RequestMapping(method = RequestMethod.GET, value = "/sysposts/{syspost_id}")
    public ResponseEntity<SysPostDTO> get(@PathVariable("syspost_id") String syspost_id) {
        SysPost domain = syspostService.get(syspost_id);
        SysPostDTO dto = syspostMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "删除岗位", tags = {"岗位" },  notes = "删除岗位")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysposts/{syspost_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("syspost_id") String syspost_id) {
         return ResponseEntity.status(HttpStatus.OK).body(syspostService.remove(syspost_id));
    }

    @ApiOperation(value = "批量删除岗位", tags = {"岗位" },  notes = "批量删除岗位")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysposts/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        syspostService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "更新岗位", tags = {"岗位" },  notes = "更新岗位")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysposts/{syspost_id}")
    @Transactional
    public ResponseEntity<SysPostDTO> update(@PathVariable("syspost_id") String syspost_id, @RequestBody SysPostDTO syspostdto) {
		SysPost domain  = syspostMapping.toDomain(syspostdto);
        domain.setPostid(syspost_id);
		syspostService.update(domain );
		SysPostDTO dto = syspostMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "检查岗位", tags = {"岗位" },  notes = "检查岗位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysposts/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysPostDTO syspostdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(syspostService.checkKey(syspostMapping.toDomain(syspostdto)));
    }

    @ApiOperation(value = "获取岗位草稿", tags = {"岗位" },  notes = "获取岗位草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysposts/getdraft")
    public ResponseEntity<SysPostDTO> getDraft(SysPostDTO dto) {
        SysPost domain = syspostMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(syspostMapping.toDto(syspostService.getDraft(domain)));
    }

    @ApiOperation(value = "保存岗位", tags = {"岗位" },  notes = "保存岗位")
	@RequestMapping(method = RequestMethod.POST, value = "/sysposts/save")
    public ResponseEntity<SysPostDTO> save(@RequestBody SysPostDTO syspostdto) {
        SysPost domain = syspostMapping.toDomain(syspostdto);
        syspostService.save(domain);
        SysPostDTO dto = syspostMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"岗位" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysposts/fetchdefault")
	public ResponseEntity<List<SysPostDTO>> fetchdefault(@RequestBody SysPostSearchContext context) {
        Page<SysPost> domains = syspostService.searchDefault(context) ;
        List<SysPostDTO> list = syspostMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成岗位报表", tags = {"岗位"}, notes = "生成岗位报表")
    @RequestMapping(method = RequestMethod.GET, value = "/sysposts/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, SysPostSearchContext context, HttpServletResponse response) {
    }

    @ApiOperation(value = "打印岗位", tags = {"岗位"}, notes = "打印岗位")
    @RequestMapping(method = RequestMethod.GET, value = "/sysposts/{syspost_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("syspost_ids") Set<String> syspost_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
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
    @RequestMapping(method = RequestMethod.POST, value = "/sysposts/{syspost_id}/{action}")
    public ResponseEntity<SysPostDTO> dynamicCall(@PathVariable("syspost_id") String syspost_id , @PathVariable("action") String action , @RequestBody SysPostDTO syspostdto) {
        SysPost domain = syspostService.dynamicCall(syspost_id, action, syspostMapping.toDomain(syspostdto));
        syspostdto = syspostMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(syspostdto);
    }
}

