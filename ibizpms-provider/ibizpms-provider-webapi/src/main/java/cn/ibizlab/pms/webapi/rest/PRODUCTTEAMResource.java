package cn.ibizlab.pms.webapi.rest;

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
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.PRODUCTTEAMRuntime;

@Slf4j
@Api(tags = {"产品团队" })
@RestController("WebApi-productteam")
@RequestMapping("")
public class PRODUCTTEAMResource {

    @Autowired
    public IPRODUCTTEAMService productteamService;

    @Autowired
    public PRODUCTTEAMRuntime productteamRuntime;

    @Autowired
    @Lazy
    public PRODUCTTEAMMapping productteamMapping;

    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'CREATE')")
    @ApiOperation(value = "新建产品团队", tags = {"产品团队" },  notes = "新建产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productteams")
    @Transactional
    public ResponseEntity<PRODUCTTEAMDTO> create(@Validated @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
		productteamService.create(domain);
        if(!productteamRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', #productteam_id, 'UPDATE')")
    @ApiOperation(value = "更新产品团队", tags = {"产品团队" },  notes = "更新产品团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/productteams/{productteam_id}")
    @Transactional
    public ResponseEntity<PRODUCTTEAMDTO> update(@PathVariable("productteam_id") Long productteam_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
		PRODUCTTEAM domain  = productteamMapping.toDomain(productteamdto);
        domain.setId(productteam_id);
		productteamService.update(domain );
        if(!productteamRuntime.test(productteam_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(productteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PRODUCTTEAM', #productteam_id, 'DELETE')")
    @ApiOperation(value = "删除产品团队", tags = {"产品团队" },  notes = "删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productteams/{productteam_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productteam_id") Long productteam_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productteamService.remove(productteam_id));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'DELETE')")
    @ApiOperation(value = "批量删除产品团队", tags = {"产品团队" },  notes = "批量删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productteams/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', #productteam_id, 'READ')")
    @ApiOperation(value = "获取产品团队", tags = {"产品团队" },  notes = "获取产品团队")
	@RequestMapping(method = RequestMethod.GET, value = "/productteams/{productteam_id}")
    public ResponseEntity<PRODUCTTEAMDTO> get(@PathVariable("productteam_id") Long productteam_id) {
        PRODUCTTEAM domain = productteamService.get(productteam_id);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(productteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'CREATE')")
    @ApiOperation(value = "获取产品团队草稿", tags = {"产品团队" },  notes = "获取产品团队草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productteams/getdraft")
    public ResponseEntity<PRODUCTTEAMDTO> getDraft(PRODUCTTEAMDTO dto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productteamMapping.toDto(productteamService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'CREATE')")
    @ApiOperation(value = "检查产品团队", tags = {"产品团队" },  notes = "检查产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productteams/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody PRODUCTTEAMDTO productteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productteamService.checkKey(productteamMapping.toDomain(productteamdto)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'DENY')")
    @ApiOperation(value = "PmsEe团队管理过滤", tags = {"产品团队" },  notes = "PmsEe团队管理过滤")
	@RequestMapping(method = RequestMethod.POST, value = "/productteams/{productteam_id}/productteamguolv")
    public ResponseEntity<PRODUCTTEAMDTO> productTeamGuoLv(@PathVariable("productteam_id") Long productteam_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setId(productteam_id);
        domain = productteamService.productTeamGuoLv(domain);
        productteamdto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(domain.getId());
        productteamdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productteamdto);
    }



    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'CREATE')")
    @ApiOperation(value = "批量保存产品团队", tags = {"产品团队" },  notes = "批量保存产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productteams/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<PRODUCTTEAMDTO> productteamdtos) {
        productteamService.saveBatch(productteamMapping.toDomain(productteamdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品团队" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/fetchdefault")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchdefault(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchDefault(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'READ')")
	@ApiOperation(value = "获取产品团队成员信息", tags = {"产品团队" } ,notes = "获取产品团队成员信息")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/fetchproductteaminfo")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchproductteaminfo(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchProductTeamInfo(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'READ')")
	@ApiOperation(value = "获取项目立项", tags = {"产品团队" } ,notes = "获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/fetchprojectapp")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchprojectapp(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchProjectApp(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'READ')")
	@ApiOperation(value = "获取产品团队管理", tags = {"产品团队" } ,notes = "获取产品团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/fetchroweditdefaultproductteam")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchroweditdefaultproductteam(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchRowEditDefaultProductTeam(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'READ')")
	@ApiOperation(value = "获取指定团队部门", tags = {"产品团队" } ,notes = "获取指定团队部门")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/fetchspecifyteam")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchspecifyteam(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchSpecifyTeam(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productteams/{productteam_id}/{action}")
    public ResponseEntity<PRODUCTTEAMDTO> dynamicCall(@PathVariable("productteam_id") Long productteam_id , @PathVariable("action") String action , @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamService.dynamicCall(productteam_id, action, productteamMapping.toDomain(productteamdto));
        productteamdto = productteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productteamdto);
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立产品团队", tags = {"产品团队" },  notes = "根据产品建立产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams")
    public ResponseEntity<PRODUCTTEAMDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
		productteamService.create(domain);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String, Integer> opprivsMap = productteamRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivsMap);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'UPDATE', #productteam_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新产品团队", tags = {"产品团队" },  notes = "根据产品更新产品团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<PRODUCTTEAMDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
        domain.setId(productteam_id);
		productteamService.update(domain);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String, Integer> opprivsMap = productteamRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivsMap);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'DELETE', #productteam_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除产品团队", tags = {"产品团队" },  notes = "根据产品删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productteamService.remove(productteam_id));
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除产品团队", tags = {"产品团队" },  notes = "根据产品批量删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productteams/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        productteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'READ', #productteam_id, 'READ')")
    @ApiOperation(value = "根据产品获取产品团队", tags = {"产品团队" },  notes = "根据产品获取产品团队")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<PRODUCTTEAMDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id) {
        PRODUCTTEAM domain = productteamService.get(productteam_id);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String, Integer> opprivsMap = productteamRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivsMap);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取产品团队草稿", tags = {"产品团队" },  notes = "根据产品获取产品团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productteams/getdraft")
    public ResponseEntity<PRODUCTTEAMDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, PRODUCTTEAMDTO dto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(dto);
        domain.setRoot(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productteamMapping.toDto(productteamService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品检查产品团队", tags = {"产品团队" },  notes = "根据产品检查产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productteamService.checkKey(productteamMapping.toDomain(productteamdto)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTTEAM', 'DENY')")
    @ApiOperation(value = "根据产品PmsEe团队管理过滤", tags = {"产品团队" },  notes = "根据产品PmsEe团队管理过滤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams/{productteam_id}/productteamguolv")
    public ResponseEntity<PRODUCTTEAMDTO> productTeamGuoLvByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
        domain.setId(productteam_id);
        domain = productteamService.productTeamGuoLv(domain) ;
        productteamdto = productteamMapping.toDto(domain);
        Map<String, Integer> opprivsMap = productteamRuntime.getOPPrivs(domain.getId());    
        productteamdto.setSrfopprivs(opprivsMap);
        return ResponseEntity.status(HttpStatus.OK).body(productteamdto);
    }


    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品批量保存产品团队", tags = {"产品团队" },  notes = "根据产品批量保存产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<PRODUCTTEAMDTO> productteamdtos) {
        List<PRODUCTTEAM> domainlist=productteamMapping.toDomain(productteamdtos);
        for(PRODUCTTEAM domain:domainlist){
             domain.setRoot(product_id);
        }
        productteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取数据集", tags = {"产品团队" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchdefault")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchDefault(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取产品团队成员信息", tags = {"产品团队" } ,notes = "根据产品获取产品团队成员信息")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchproductteaminfo")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchProductTeamInfoByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchProductTeamInfo(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目立项", tags = {"产品团队" } ,notes = "根据产品获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchprojectapp")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchProjectAppByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchProjectApp(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取产品团队管理", tags = {"产品团队" } ,notes = "根据产品获取产品团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchroweditdefaultproductteam")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchRowEditDefaultProductTeamByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchRowEditDefaultProductTeam(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTTEAM', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取指定团队部门", tags = {"产品团队" } ,notes = "根据产品获取指定团队部门")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchspecifyteam")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchSpecifyTeamByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchSpecifyTeam(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

