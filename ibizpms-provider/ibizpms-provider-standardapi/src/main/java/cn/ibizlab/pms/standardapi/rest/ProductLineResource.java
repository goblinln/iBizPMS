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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProductLine;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProProductLineService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProProductLineSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProProductLineRuntime;

@Slf4j
@Api(tags = {"产品线" })
@RestController("StandardAPI-productline")
@RequestMapping("")
public class ProductLineResource {

    @Autowired
    public IIBZProProductLineService ibzproproductlineService;

    @Autowired
    public IBZProProductLineRuntime ibzproproductlineRuntime;

    @Autowired
    @Lazy
    public ProductLineMapping productlineMapping;

    @PreAuthorize("@IBZProProductLineRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品线", tags = {"产品线" },  notes = "新建产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/productlines")
    @Transactional
    public ResponseEntity<ProductLineDTO> create(@Validated @RequestBody ProductLineDTO productlinedto) {
        IBZProProductLine domain = productlineMapping.toDomain(productlinedto);
		ibzproproductlineService.create(domain);
        if(!ibzproproductlineRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProductLineRuntime.test(#productline_id,'DELETE')")
    @ApiOperation(value = "删除产品线", tags = {"产品线" },  notes = "删除产品线")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productlines/{productline_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productline_id") Long productline_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproproductlineService.remove(productline_id));
    }


    @PreAuthorize("@IBZProProductLineRuntime.test(#productline_id,'READ')")
    @ApiOperation(value = "获取产品线", tags = {"产品线" },  notes = "获取产品线")
	@RequestMapping(method = RequestMethod.GET, value = "/productlines/{productline_id}")
    public ResponseEntity<ProductLineDTO> get(@PathVariable("productline_id") Long productline_id) {
        IBZProProductLine domain = ibzproproductlineService.get(productline_id);
        ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(productline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProductLineRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取产品线草稿", tags = {"产品线" },  notes = "获取产品线草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productlines/getdraft")
    public ResponseEntity<ProductLineDTO> getDraft(ProductLineDTO dto) {
        IBZProProductLine domain = productlineMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productlineMapping.toDto(ibzproproductlineService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProProductLineRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品线" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productlines/fetchdefault")
	public ResponseEntity<List<ProductLineDTO>> fetchdefault(@RequestBody IBZProProductLineSearchContext context) {
        ibzproproductlineRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProProductLine> domains = ibzproproductlineService.searchDefault(context) ;
        List<ProductLineDTO> list = productlineMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProductLineRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品线" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productlines/searchdefault")
	public ResponseEntity<Page<ProductLineDTO>> searchDefault(@RequestBody IBZProProductLineSearchContext context) {
        ibzproproductlineRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProProductLine> domains = ibzproproductlineService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productlineMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZProProductLineRuntime.test(#productline_id,'UPDATE')")
    @ApiOperation(value = "更新产品线", tags = {"产品线" },  notes = "更新产品线")
	@RequestMapping(method = RequestMethod.PUT, value = "/productlines/{productline_id}")
    @Transactional
    public ResponseEntity<ProductLineDTO> update(@PathVariable("productline_id") Long productline_id, @RequestBody ProductLineDTO productlinedto) {
		IBZProProductLine domain  = productlineMapping.toDomain(productlinedto);
        domain.setId(productline_id);
		ibzproproductlineService.update(domain );
        if(!ibzproproductlineRuntime.test(productline_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(productline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "保存产品线", tags = {"产品线" },  notes = "保存产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/productlines/save")
    public ResponseEntity<ProductLineDTO> save(@RequestBody ProductLineDTO productlinedto) {
        IBZProProductLine domain = productlineMapping.toDomain(productlinedto);
        ibzproproductlineService.save(domain);
        ProductLineDTO dto = productlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productlines/{productline_id}/{action}")
    public ResponseEntity<ProductLineDTO> dynamicCall(@PathVariable("productline_id") Long productline_id , @PathVariable("action") String action , @RequestBody ProductLineDTO productlinedto) {
        IBZProProductLine domain = ibzproproductlineService.dynamicCall(productline_id, action, productlineMapping.toDomain(productlinedto));
        productlinedto = productlineMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productlinedto);
    }
}

