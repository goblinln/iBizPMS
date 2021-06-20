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
import cn.ibizlab.pms.core.ibiz.domain.IbzAgent;
import cn.ibizlab.pms.core.ibiz.service.IIbzAgentService;
import cn.ibizlab.pms.core.ibiz.filter.IbzAgentSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzAgentRuntime;

@Slf4j
@Api(tags = {"代理"})
@RestController("WebApi-ibzagent")
@RequestMapping("")
public class IbzAgentResource {

    @Autowired
    public IIbzAgentService ibzagentService;

    @Autowired
    public IbzAgentRuntime ibzagentRuntime;

    @Autowired
    @Lazy
    public IbzAgentMapping ibzagentMapping;

    @PreAuthorize("quickTest('IBZ_AGENT', 'CREATE')")
    @ApiOperation(value = "新建代理", tags = {"代理" },  notes = "新建代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents")
    @Transactional
    public ResponseEntity<IbzAgentDTO> create(@Validated @RequestBody IbzAgentDTO ibzagentdto) {
        IbzAgent domain = ibzagentMapping.toDomain(ibzagentdto);
		ibzagentService.create(domain);
        if(!ibzagentRuntime.test(domain.getIbzagentid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzagentRuntime.getOPPrivs(domain.getIbzagentid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_AGENT', #ibzagent_id, 'READ')")
    @ApiOperation(value = "获取代理", tags = {"代理" },  notes = "获取代理")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzagents/{ibzagent_id}")
    public ResponseEntity<IbzAgentDTO> get(@PathVariable("ibzagent_id") Long ibzagent_id) {
        IbzAgent domain = ibzagentService.get(ibzagent_id);
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzagentRuntime.getOPPrivs(ibzagent_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_AGENT', #ibzagent_id, 'DELETE')")
    @ApiOperation(value = "删除代理", tags = {"代理" },  notes = "删除代理")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzagents/{ibzagent_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzagent_id") Long ibzagent_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzagentService.remove(ibzagent_id));
    }

    @PreAuthorize("quickTest('IBZ_AGENT', 'DELETE')")
    @ApiOperation(value = "批量删除代理", tags = {"代理" },  notes = "批量删除代理")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzagents/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzagentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzagent" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_AGENT', #ibzagent_id, 'UPDATE')")
    @ApiOperation(value = "更新代理", tags = {"代理" },  notes = "更新代理")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzagents/{ibzagent_id}")
    @Transactional
    public ResponseEntity<IbzAgentDTO> update(@PathVariable("ibzagent_id") Long ibzagent_id, @RequestBody IbzAgentDTO ibzagentdto) {
		IbzAgent domain  = ibzagentMapping.toDomain(ibzagentdto);
        domain.setIbzagentid(ibzagent_id);
		ibzagentService.update(domain );
        if(!ibzagentRuntime.test(ibzagent_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzagentRuntime.getOPPrivs(ibzagent_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_AGENT', 'CREATE')")
    @ApiOperation(value = "检查代理", tags = {"代理" },  notes = "检查代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzAgentDTO ibzagentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzagentService.checkKey(ibzagentMapping.toDomain(ibzagentdto)));
    }

    @PreAuthorize("quickTest('IBZ_AGENT', 'CREATE')")
    @ApiOperation(value = "获取代理草稿", tags = {"代理" },  notes = "获取代理草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzagents/getdraft")
    public ResponseEntity<IbzAgentDTO> getDraft(IbzAgentDTO dto) {
        IbzAgent domain = ibzagentMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzagentMapping.toDto(ibzagentService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_AGENT', 'DENY')")
    @ApiOperation(value = "保存代理", tags = {"代理" },  notes = "保存代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/save")
    public ResponseEntity<IbzAgentDTO> save(@RequestBody IbzAgentDTO ibzagentdto) {
        IbzAgent domain = ibzagentMapping.toDomain(ibzagentdto);
        ibzagentService.save(domain);
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzagentRuntime.getOPPrivs(domain.getIbzagentid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_AGENT', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"代理" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzagents/fetchdefault")
	public ResponseEntity<List<IbzAgentDTO>> fetchdefault(@RequestBody IbzAgentSearchContext context) {
        Page<IbzAgent> domains = ibzagentService.searchDefault(context) ;
        List<IbzAgentDTO> list = ibzagentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成代理报表", tags = {"代理"}, notes = "生成代理报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzagents/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzAgentSearchContext context, HttpServletResponse response) {
        try {
            ibzagentRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzagentRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzagentRuntime);
        }
    }

    @ApiOperation(value = "打印代理", tags = {"代理"}, notes = "打印代理")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzagents/{ibzagent_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzagent_ids") Set<Long> ibzagent_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzagentRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzAgent> domains = new ArrayList<>();
            for (Long ibzagent_id : ibzagent_ids) {
                domains.add(ibzagentService.get( ibzagent_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzAgent[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzagentRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzagent_ids, e.getMessage()), Errors.INTERNALERROR, ibzagentRuntime);
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

