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
import cn.ibizlab.pms.core.ibiz.domain.IbzTop;
import cn.ibizlab.pms.core.ibiz.service.IIbzTopService;
import cn.ibizlab.pms.core.ibiz.filter.IbzTopSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzTopRuntime;

@Slf4j
@Api(tags = {"置顶"})
@RestController("WebApi-ibztop")
@RequestMapping("")
public class IbzTopResource {

    @Autowired
    public IIbzTopService ibztopService;

    @Autowired
    public IbzTopRuntime ibztopRuntime;

    @Autowired
    @Lazy
    public IbzTopMapping ibztopMapping;

    @PreAuthorize("quickTest('IBZ_TOP', 'CREATE')")
    @ApiOperation(value = "新建置顶", tags = {"置顶" },  notes = "新建置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztops")
    @Transactional
    public ResponseEntity<IbzTopDTO> create(@Validated @RequestBody IbzTopDTO ibztopdto) {
        IbzTop domain = ibztopMapping.toDomain(ibztopdto);
		ibztopService.create(domain);
        if(!ibztopRuntime.test(domain.getIbztopid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzTopDTO dto = ibztopMapping.toDto(domain);
        Map<String, Integer> opprivs = ibztopRuntime.getOPPrivs(domain.getIbztopid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_TOP', #ibztop_id, 'READ')")
    @ApiOperation(value = "获取置顶", tags = {"置顶" },  notes = "获取置顶")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztops/{ibztop_id}")
    public ResponseEntity<IbzTopDTO> get(@PathVariable("ibztop_id") String ibztop_id) {
        IbzTop domain = ibztopService.get(ibztop_id);
        IbzTopDTO dto = ibztopMapping.toDto(domain);
        Map<String, Integer> opprivs = ibztopRuntime.getOPPrivs(ibztop_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_TOP', #ibztop_id, 'DELETE')")
    @ApiOperation(value = "删除置顶", tags = {"置顶" },  notes = "删除置顶")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztops/{ibztop_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztop_id") String ibztop_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztopService.remove(ibztop_id));
    }

    @PreAuthorize("quickTest('IBZ_TOP', 'DELETE')")
    @ApiOperation(value = "批量删除置顶", tags = {"置顶" },  notes = "批量删除置顶")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztops/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibztopService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibztop" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_TOP', #ibztop_id, 'UPDATE')")
    @ApiOperation(value = "更新置顶", tags = {"置顶" },  notes = "更新置顶")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztops/{ibztop_id}")
    @Transactional
    public ResponseEntity<IbzTopDTO> update(@PathVariable("ibztop_id") String ibztop_id, @RequestBody IbzTopDTO ibztopdto) {
		IbzTop domain  = ibztopMapping.toDomain(ibztopdto);
        domain.setIbztopid(ibztop_id);
		ibztopService.update(domain );
        if(!ibztopRuntime.test(ibztop_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzTopDTO dto = ibztopMapping.toDto(domain);
        Map<String, Integer> opprivs = ibztopRuntime.getOPPrivs(ibztop_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_TOP', 'CREATE')")
    @ApiOperation(value = "检查置顶", tags = {"置顶" },  notes = "检查置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztops/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzTopDTO ibztopdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztopService.checkKey(ibztopMapping.toDomain(ibztopdto)));
    }

    @PreAuthorize("quickTest('IBZ_TOP', 'CREATE')")
    @ApiOperation(value = "获取置顶草稿", tags = {"置顶" },  notes = "获取置顶草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztops/getdraft")
    public ResponseEntity<IbzTopDTO> getDraft(IbzTopDTO dto) {
        IbzTop domain = ibztopMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztopMapping.toDto(ibztopService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_TOP', 'DENY')")
    @ApiOperation(value = "保存置顶", tags = {"置顶" },  notes = "保存置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztops/save")
    public ResponseEntity<IbzTopDTO> save(@RequestBody IbzTopDTO ibztopdto) {
        IbzTop domain = ibztopMapping.toDomain(ibztopdto);
        ibztopService.save(domain);
        IbzTopDTO dto = ibztopMapping.toDto(domain);
        Map<String, Integer> opprivs = ibztopRuntime.getOPPrivs(domain.getIbztopid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_TOP', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"置顶" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztops/fetchdefault")
	public ResponseEntity<List<IbzTopDTO>> fetchdefault(@RequestBody IbzTopSearchContext context) {
        ibztopRuntime.addAuthorityConditions(context,"READ");
        Page<IbzTop> domains = ibztopService.searchDefault(context) ;
        List<IbzTopDTO> list = ibztopMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成置顶报表", tags = {"置顶"}, notes = "生成置顶报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibztops/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzTopSearchContext context, HttpServletResponse response) {
        try {
            ibztopRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibztopRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibztopRuntime);
        }
    }

    @ApiOperation(value = "打印置顶", tags = {"置顶"}, notes = "打印置顶")
    @RequestMapping(method = RequestMethod.GET, value = "/ibztops/{ibztop_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibztop_ids") Set<String> ibztop_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibztopRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzTop> domains = new ArrayList<>();
            for (String ibztop_id : ibztop_ids) {
                domains.add(ibztopService.get( ibztop_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzTop[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibztopRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibztop_ids, e.getMessage()), Errors.INTERNALERROR, ibztopRuntime);
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

}

