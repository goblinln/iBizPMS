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
import cn.ibizlab.pms.core.ibiz.domain.IbiLogin;
import cn.ibizlab.pms.core.ibiz.service.IIbiLoginService;
import cn.ibizlab.pms.core.ibiz.filter.IbiLoginSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbiLoginRuntime;

@Slf4j
@Api(tags = {"实体"})
@RestController("WebApi-ibzlogin")
@RequestMapping("")
public class IbzLoginResource {

    @Autowired
    public IIbiLoginService ibiloginService;

    @Autowired
    public IbiLoginRuntime ibiloginRuntime;

    @Autowired
    @Lazy
    public IbzLoginMapping ibzloginMapping;

    @PreAuthorize("quickTest('IBZ_LOGIN', 'DENY')")
    @ApiOperation(value = "ZT登录", tags = {"实体" },  notes = "ZT登录")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlogins/{ibzlogin_id}/ztlogin")
    public ResponseEntity<IbzLoginDTO> ztlogin(@PathVariable("ibzlogin_id") Long ibzlogin_id, @RequestBody IbzLoginDTO ibzlogindto) {
        IbiLogin domain = ibzloginMapping.toDomain(ibzlogindto);
        domain.setId(ibzlogin_id);
        domain = ibiloginService.ztlogin(domain);
        ibzlogindto = ibzloginMapping.toDto(domain);
        Map<String, Integer> opprivs = ibiloginRuntime.getOPPrivs(domain.getId());
        ibzlogindto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlogindto);
    }


    @PreAuthorize("test('IBZ_LOGIN', #ibzlogin_id, 'READ')")
    @ApiOperation(value = "获取ZT账户登录信息", tags = {"实体" },  notes = "获取ZT账户登录信息")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlogins/{ibzlogin_id}/ztusersloginname")
    public ResponseEntity<IbzLoginDTO> ztusersloginname(@PathVariable("ibzlogin_id") Long ibzlogin_id, IbzLoginDTO ibzlogindto) {
        IbiLogin domain = ibzloginMapping.toDomain(ibzlogindto);
        domain.setId(ibzlogin_id);
        domain = ibiloginService.getUser(domain);
        ibzlogindto = ibzloginMapping.toDto(domain);
        Map<String, Integer> opprivs = ibiloginRuntime.getOPPrivs(domain.getId());
        ibzlogindto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlogindto);
    }



    @ApiOperation(value = "生成实体报表", tags = {"实体"}, notes = "生成实体报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlogins/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbiLoginSearchContext context, HttpServletResponse response) {
        try {
            ibiloginRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibiloginRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibiloginRuntime);
        }
    }

    @ApiOperation(value = "打印实体", tags = {"实体"}, notes = "打印实体")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlogins/{ibzlogin_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzlogin_ids") Set<Long> ibzlogin_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibiloginRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbiLogin> domains = new ArrayList<>();
            for (Long ibzlogin_id : ibzlogin_ids) {
                domains.add(ibiloginService.get( ibzlogin_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbiLogin[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibiloginRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzlogin_ids, e.getMessage()), Errors.INTERNALERROR, ibiloginRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzlogins/{ibzlogin_id}/{action}")
    public ResponseEntity<IbzLoginDTO> dynamicCall(@PathVariable("ibzlogin_id") Long ibzlogin_id , @PathVariable("action") String action , @RequestBody IbzLoginDTO ibzlogindto) {
        IbiLogin domain = ibiloginService.dynamicCall(ibzlogin_id, action, ibzloginMapping.toDomain(ibzlogindto));
        ibzlogindto = ibzloginMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlogindto);
    }
}

