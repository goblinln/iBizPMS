package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductDaily;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductDailyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductDailySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProductDailyRuntime;

@Slf4j
@Api(tags = {"产品日报"})
@RestController("StandardAPI-productdaily")
@RequestMapping("")
public class ProductDailyResource {

    @Autowired
    public IIbizproProductDailyService ibizproproductdailyService;

    @Autowired
    public IbizproProductDailyRuntime ibizproproductdailyRuntime;

    @Autowired
    @Lazy
    public ProductDailyMapping productdailyMapping;

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTDAILY', 'CREATE')")
    @ApiOperation(value = "新建产品日报", tags = {"产品日报" },  notes = "新建产品日报")
	@RequestMapping(method = RequestMethod.POST, value = "/productdailies")
    @Transactional
    public ResponseEntity<ProductDailyDTO> create(@Validated @RequestBody ProductDailyDTO productdailydto) {
        IbizproProductDaily domain = productdailyMapping.toDomain(productdailydto);
		ibizproproductdailyService.create(domain);
        if(!ibizproproductdailyRuntime.test(domain.getIbizproproductdailyid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductDailyDTO dto = productdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductdailyRuntime.getOPPrivs(domain.getIbizproproductdailyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTDAILY', #productdaily_id, 'NONE')")
    @ApiOperation(value = "获取产品日报", tags = {"产品日报" },  notes = "获取产品日报")
	@RequestMapping(method = RequestMethod.GET, value = "/productdailies/{productdaily_id}")
    public ResponseEntity<ProductDailyDTO> get(@PathVariable("productdaily_id") Long productdaily_id) {
        IbizproProductDaily domain = ibizproproductdailyService.get(productdaily_id);
        ProductDailyDTO dto = productdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductdailyRuntime.getOPPrivs(productdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibizproproductdaily" , versionfield = "updatedate")
    @PreAuthorize("test('IBIZPRO_PRODUCTDAILY', #productdaily_id, 'UPDATE')")
    @ApiOperation(value = "更新产品日报", tags = {"产品日报" },  notes = "更新产品日报")
	@RequestMapping(method = RequestMethod.PUT, value = "/productdailies/{productdaily_id}")
    @Transactional
    public ResponseEntity<ProductDailyDTO> update(@PathVariable("productdaily_id") Long productdaily_id, @RequestBody ProductDailyDTO productdailydto) {
		IbizproProductDaily domain  = productdailyMapping.toDomain(productdailydto);
        domain.setIbizproproductdailyid(productdaily_id);
		ibizproproductdailyService.update(domain );
        if(!ibizproproductdailyRuntime.test(productdaily_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductDailyDTO dto = productdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductdailyRuntime.getOPPrivs(productdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTDAILY', 'DENY')")
    @ApiOperation(value = "手动生成产品日报", tags = {"产品日报" },  notes = "手动生成产品日报")
	@RequestMapping(method = RequestMethod.POST, value = "/productdailies/{productdaily_id}/autocreate")
    public ResponseEntity<ProductDailyDTO> autoCreate(@PathVariable("productdaily_id") Long productdaily_id, @RequestBody ProductDailyDTO productdailydto) {
        IbizproProductDaily domain = productdailyMapping.toDomain(productdailydto);
        domain.setIbizproproductdailyid(productdaily_id);
        domain = ibizproproductdailyService.manualCreateDaily(domain);
        productdailydto = productdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductdailyRuntime.getOPPrivs(domain.getIbizproproductdailyid());
        productdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productdailydto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTDAILY', 'CREATE')")
    @ApiOperation(value = "获取产品日报草稿", tags = {"产品日报" },  notes = "获取产品日报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productdailies/getdraft")
    public ResponseEntity<ProductDailyDTO> getDraft(ProductDailyDTO dto) {
        IbizproProductDaily domain = productdailyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productdailyMapping.toDto(ibizproproductdailyService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTDAILY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品日报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productdailies/fetchdefault")
	public ResponseEntity<List<ProductDailyDTO>> fetchdefault(@RequestBody IbizproProductDailySearchContext context) {
        Page<IbizproProductDaily> domains = ibizproproductdailyService.searchDefault(context) ;
        List<ProductDailyDTO> list = productdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成产品日报报表", tags = {"产品日报"}, notes = "生成产品日报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/productdailies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbizproProductDailySearchContext context, HttpServletResponse response) {
        try {
            ibizproproductdailyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproproductdailyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibizproproductdailyRuntime);
        }
    }

    @ApiOperation(value = "打印产品日报", tags = {"产品日报"}, notes = "打印产品日报")
    @RequestMapping(method = RequestMethod.GET, value = "/productdailies/{productdaily_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("productdaily_ids") Set<Long> productdaily_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibizproproductdailyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbizproProductDaily> domains = new ArrayList<>();
            for (Long productdaily_id : productdaily_ids) {
                domains.add(ibizproproductdailyService.get( productdaily_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbizproProductDaily[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproproductdailyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", productdaily_ids, e.getMessage()), Errors.INTERNALERROR, ibizproproductdailyRuntime);
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

