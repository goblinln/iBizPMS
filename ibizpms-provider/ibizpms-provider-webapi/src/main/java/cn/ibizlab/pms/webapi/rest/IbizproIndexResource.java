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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproIndexService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproIndexRuntime;

@Slf4j
@Api(tags = {"索引检索"})
@RestController("WebApi-ibizproindex")
@RequestMapping("")
public class IbizproIndexResource {

    @Autowired
    public IIbizproIndexService ibizproindexService;

    @Autowired
    public IbizproIndexRuntime ibizproindexRuntime;

    @Autowired
    @Lazy
    public IbizproIndexMapping ibizproindexMapping;

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'CREATE')")
    @ApiOperation(value = "新建索引检索", tags = {"索引检索" },  notes = "新建索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices")
    @Transactional
    public ResponseEntity<IbizproIndexDTO> create(@Validated @RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(ibizproindexdto);
		ibizproindexService.create(domain);
        if(!ibizproindexRuntime.test(domain.getIndexid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproindexRuntime.getOPPrivs(domain.getIndexid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_INDEX', #ibizproindex_id, 'READ')")
    @ApiOperation(value = "获取索引检索", tags = {"索引检索" },  notes = "获取索引检索")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/{ibizproindex_id}")
    public ResponseEntity<IbizproIndexDTO> get(@PathVariable("ibizproindex_id") Long ibizproindex_id) {
        IbizproIndex domain = ibizproindexService.get(ibizproindex_id);
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproindexRuntime.getOPPrivs(ibizproindex_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_INDEX', #ibizproindex_id, 'DELETE')")
    @ApiOperation(value = "删除索引检索", tags = {"索引检索" },  notes = "删除索引检索")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproindices/{ibizproindex_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproindex_id") Long ibizproindex_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproindexService.remove(ibizproindex_id));
    }

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'DELETE')")
    @ApiOperation(value = "批量删除索引检索", tags = {"索引检索" },  notes = "批量删除索引检索")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproindices/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibizproindexService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBIZPRO_INDEX', #ibizproindex_id, 'UPDATE')")
    @ApiOperation(value = "更新索引检索", tags = {"索引检索" },  notes = "更新索引检索")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproindices/{ibizproindex_id}")
    @Transactional
    public ResponseEntity<IbizproIndexDTO> update(@PathVariable("ibizproindex_id") Long ibizproindex_id, @RequestBody IbizproIndexDTO ibizproindexdto) {
		IbizproIndex domain  = ibizproindexMapping.toDomain(ibizproindexdto);
        domain.setIndexid(ibizproindex_id);
		ibizproindexService.update(domain );
        if(!ibizproindexRuntime.test(ibizproindex_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproindexRuntime.getOPPrivs(ibizproindex_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'CREATE')")
    @ApiOperation(value = "检查索引检索", tags = {"索引检索" },  notes = "检查索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproIndexDTO ibizproindexdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproindexService.checkKey(ibizproindexMapping.toDomain(ibizproindexdto)));
    }

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'CREATE')")
    @ApiOperation(value = "获取索引检索草稿", tags = {"索引检索" },  notes = "获取索引检索草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/getdraft")
    public ResponseEntity<IbizproIndexDTO> getDraft(IbizproIndexDTO dto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproindexMapping.toDto(ibizproindexService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'DENY')")
    @ApiOperation(value = "保存索引检索", tags = {"索引检索" },  notes = "保存索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/save")
    public ResponseEntity<IbizproIndexDTO> save(@RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(ibizproindexdto);
        ibizproindexService.save(domain);
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproindexRuntime.getOPPrivs(domain.getIndexid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"索引检索" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/fetchdefault")
	public ResponseEntity<List<IbizproIndexDTO>> fetchdefault(@RequestBody IbizproIndexSearchContext context) {
        ibizproindexRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproIndex> domains = ibizproindexService.searchDefault(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'READ')")
	@ApiOperation(value = "获取全文检索", tags = {"索引检索" } ,notes = "获取全文检索")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/fetchesquery")
	public ResponseEntity<List<IbizproIndexDTO>> fetchesquery(@RequestBody IbizproIndexSearchContext context) {
        ibizproindexRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproIndex> domains = ibizproindexService.searchESquery(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'READ')")
	@ApiOperation(value = "获取数据集2", tags = {"索引检索" } ,notes = "获取数据集2")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/fetchindexder")
	public ResponseEntity<List<IbizproIndexDTO>> fetchindexder(@RequestBody IbizproIndexSearchContext context) {
        ibizproindexRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproIndex> domains = ibizproindexService.searchIndexDER(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成索引检索报表", tags = {"索引检索"}, notes = "生成索引检索报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbizproIndexSearchContext context, HttpServletResponse response) {
        try {
            ibizproindexRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproindexRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibizproindexRuntime);
        }
    }

    @ApiOperation(value = "打印索引检索", tags = {"索引检索"}, notes = "打印索引检索")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/{ibizproindex_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibizproindex_ids") Set<Long> ibizproindex_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibizproindexRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbizproIndex> domains = new ArrayList<>();
            for (Long ibizproindex_id : ibizproindex_ids) {
                domains.add(ibizproindexService.get( ibizproindex_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbizproIndex[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproindexRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibizproindex_ids, e.getMessage()), Errors.INTERNALERROR, ibizproindexRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/{ibizproindex_id}/{action}")
    public ResponseEntity<IbizproIndexDTO> dynamicCall(@PathVariable("ibizproindex_id") Long ibizproindex_id , @PathVariable("action") String action , @RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexService.dynamicCall(ibizproindex_id, action, ibizproindexMapping.toDomain(ibizproindexdto));
        ibizproindexdto = ibizproindexMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproindexdto);
    }
}

