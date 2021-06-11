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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProduct;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProProductService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProProductSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProProductRuntime;

@Slf4j
@Api(tags = {"平台产品"})
@RestController("WebApi-ibzproproduct")
@RequestMapping("")
public class IBZProProductResource {

    @Autowired
    public IIBZProProductService ibzproproductService;

    @Autowired
    public IBZProProductRuntime ibzproproductRuntime;

    @Autowired
    @Lazy
    public IBZProProductMapping ibzproproductMapping;

    @PreAuthorize("quickTest('IBZPRO_PRODUCT', 'CREATE')")
    @ApiOperation(value = "新建平台产品", tags = {"平台产品" },  notes = "新建平台产品")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproducts")
    @Transactional
    public ResponseEntity<IBZProProductDTO> create(@Validated @RequestBody IBZProProductDTO ibzproproductdto) {
        IBZProProduct domain = ibzproproductMapping.toDomain(ibzproproductdto);
		ibzproproductService.create(domain);
        if(!ibzproproductRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProProductDTO dto = ibzproproductMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_PRODUCT', #ibzproproduct_id, 'READ')")
    @ApiOperation(value = "获取平台产品", tags = {"平台产品" },  notes = "获取平台产品")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproducts/{ibzproproduct_id}")
    public ResponseEntity<IBZProProductDTO> get(@PathVariable("ibzproproduct_id") Long ibzproproduct_id) {
        IBZProProduct domain = ibzproproductService.get(ibzproproduct_id);
        IBZProProductDTO dto = ibzproproductMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductRuntime.getOPPrivs(ibzproproduct_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_PRODUCT', #ibzproproduct_id, 'DELETE')")
    @ApiOperation(value = "删除平台产品", tags = {"平台产品" },  notes = "删除平台产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproducts/{ibzproproduct_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproproduct_id") Long ibzproproduct_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproproductService.remove(ibzproproduct_id));
    }

    @PreAuthorize("quickTest('IBZPRO_PRODUCT', 'DELETE')")
    @ApiOperation(value = "批量删除平台产品", tags = {"平台产品" },  notes = "批量删除平台产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproducts/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproproductService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZPRO_PRODUCT', #ibzproproduct_id, 'UPDATE')")
    @ApiOperation(value = "更新平台产品", tags = {"平台产品" },  notes = "更新平台产品")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproducts/{ibzproproduct_id}")
    @Transactional
    public ResponseEntity<IBZProProductDTO> update(@PathVariable("ibzproproduct_id") Long ibzproproduct_id, @RequestBody IBZProProductDTO ibzproproductdto) {
		IBZProProduct domain  = ibzproproductMapping.toDomain(ibzproproductdto);
        domain.setId(ibzproproduct_id);
		ibzproproductService.update(domain );
        if(!ibzproproductRuntime.test(ibzproproduct_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProProductDTO dto = ibzproproductMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductRuntime.getOPPrivs(ibzproproduct_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_PRODUCT', 'CREATE')")
    @ApiOperation(value = "检查平台产品", tags = {"平台产品" },  notes = "检查平台产品")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproducts/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProProductDTO ibzproproductdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproproductService.checkKey(ibzproproductMapping.toDomain(ibzproproductdto)));
    }

    @PreAuthorize("quickTest('IBZPRO_PRODUCT', 'CREATE')")
    @ApiOperation(value = "获取平台产品草稿", tags = {"平台产品" },  notes = "获取平台产品草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproducts/getdraft")
    public ResponseEntity<IBZProProductDTO> getDraft(IBZProProductDTO dto) {
        IBZProProduct domain = ibzproproductMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductMapping.toDto(ibzproproductService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZPRO_PRODUCT', 'DENY')")
    @ApiOperation(value = "保存平台产品", tags = {"平台产品" },  notes = "保存平台产品")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproducts/save")
    public ResponseEntity<IBZProProductDTO> save(@RequestBody IBZProProductDTO ibzproproductdto) {
        IBZProProduct domain = ibzproproductMapping.toDomain(ibzproproductdto);
        ibzproproductService.save(domain);
        IBZProProductDTO dto = ibzproproductMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_PRODUCT', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"平台产品" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproducts/fetchdefault")
	public ResponseEntity<List<IBZProProductDTO>> fetchdefault(@RequestBody IBZProProductSearchContext context) {
        ibzproproductRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProProduct> domains = ibzproproductService.searchDefault(context) ;
        List<IBZProProductDTO> list = ibzproproductMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成平台产品报表", tags = {"平台产品"}, notes = "生成平台产品报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproproducts/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IBZProProductSearchContext context, HttpServletResponse response) {
        try {
            ibzproproductRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproproductRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzproproductRuntime);
        }
    }

    @ApiOperation(value = "打印平台产品", tags = {"平台产品"}, notes = "打印平台产品")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproproducts/{ibzproproduct_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzproproduct_ids") Set<Long> ibzproproduct_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzproproductRuntime.getDEPrintRuntime(print_id);
        try {
            List<IBZProProduct> domains = new ArrayList<>();
            for (Long ibzproproduct_id : ibzproproduct_ids) {
                domains.add(ibzproproductService.get( ibzproproduct_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IBZProProduct[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproproductRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzproproduct_ids, e.getMessage()), Errors.INTERNALERROR, ibzproproductRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproproducts/{ibzproproduct_id}/{action}")
    public ResponseEntity<IBZProProductDTO> dynamicCall(@PathVariable("ibzproproduct_id") Long ibzproproduct_id , @PathVariable("action") String action , @RequestBody IBZProProductDTO ibzproproductdto) {
        IBZProProduct domain = ibzproproductService.dynamicCall(ibzproproduct_id, action, ibzproproductMapping.toDomain(ibzproproductdto));
        ibzproproductdto = ibzproproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductdto);
    }
}

