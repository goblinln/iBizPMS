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
import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.service.IBuildService;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BuildRuntime;

@Slf4j
@Api(tags = {"版本"})
@RestController("WebApi-build")
@RequestMapping("")
public class BuildResource {

    @Autowired
    public IBuildService buildService;

    @Autowired
    public BuildRuntime buildRuntime;

    @Autowired
    @Lazy
    public BuildMapping buildMapping;

    @PreAuthorize("quickTest('ZT_BUILD', 'CREATE')")
    @ApiOperation(value = "新建版本", tags = {"版本" },  notes = "新建版本")
	@RequestMapping(method = RequestMethod.POST, value = "/builds")
    @Transactional
    public ResponseEntity<BuildDTO> create(@Validated @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
		buildService.create(domain);
        if(!buildRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUILD', #build_id, 'READ')")
    @ApiOperation(value = "获取版本", tags = {"版本" },  notes = "获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}")
    public ResponseEntity<BuildDTO> get(@PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(build_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUILD', #build_id, 'DELETE')")
    @ApiOperation(value = "删除版本", tags = {"版本" },  notes = "删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("build_id") Long build_id) {
         return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DELETE')")
    @ApiOperation(value = "批量删除版本", tags = {"版本" },  notes = "批量删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        buildService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_BUILD', #build_id, 'UPDATE')")
    @ApiOperation(value = "更新版本", tags = {"版本" },  notes = "更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}")
    @Transactional
    public ResponseEntity<BuildDTO> update(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
		Build domain  = buildMapping.toDomain(builddto);
        domain.setId(build_id);
		buildService.update(domain );
        if(!buildRuntime.test(build_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(build_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'CREATE')")
    @ApiOperation(value = "检查版本", tags = {"版本" },  notes = "检查版本")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody BuildDTO builddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(buildService.checkKey(buildMapping.toDomain(builddto)));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'CREATE')")
    @ApiOperation(value = "获取版本草稿", tags = {"版本" },  notes = "获取版本草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraft(BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "关联Bug", tags = {"版本" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBug(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.linkBug(domain);
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "关联需求", tags = {"版本" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStory(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.linkStory(domain);
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "移动端项目版本计数器", tags = {"版本" },  notes = "移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/mobprojectbuildcounter")
    public ResponseEntity<BuildDTO> mobProjectBuildCounter(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.mobProjectBuildCounter(domain);
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "一键发布", tags = {"版本" },  notes = "一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/oneclickrelease")
    public ResponseEntity<BuildDTO> oneClickRelease(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.oneClickRelease(domain);
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "保存版本", tags = {"版本" },  notes = "保存版本")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/save")
    public ResponseEntity<BuildDTO> save(@RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        buildService.save(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "移除Bug关联", tags = {"版本" },  notes = "移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/unlinkbug")
    public ResponseEntity<BuildDTO> unlinkBug(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.unlinkBug(domain);
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "移除关联需求", tags = {"版本" },  notes = "移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/unlinkstory")
    public ResponseEntity<BuildDTO> unlinkStory(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.unlinkStory(domain);
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'READ')")
	@ApiOperation(value = "获取Bug产品版本", tags = {"版本" } ,notes = "获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchbugproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchbugproductbuild(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchBugProductBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'NONE')")
	@ApiOperation(value = "获取Bug产品或者项目版本", tags = {"版本" } ,notes = "获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchbugproductorprojectbuild")
	public ResponseEntity<List<BuildDTO>> fetchbugproductorprojectbuild(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchBugProductOrProjectBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'READ')")
	@ApiOperation(value = "获取产品版本", tags = {"版本" } ,notes = "获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchcurproduct")
	public ResponseEntity<List<BuildDTO>> fetchcurproduct(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchCurProduct(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"版本" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchdefault(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'READ')")
	@ApiOperation(value = "获取产品版本", tags = {"版本" } ,notes = "获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchproductbuildds")
	public ResponseEntity<List<BuildDTO>> fetchproductbuildds(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchProductBuildDS(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'NONE')")
	@ApiOperation(value = "获取测试版本", tags = {"版本" } ,notes = "获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchtestbuild")
	public ResponseEntity<List<BuildDTO>> fetchtestbuild(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchTestBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'READ')")
	@ApiOperation(value = "获取测试轮次", tags = {"版本" } ,notes = "获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchtestrounds")
	public ResponseEntity<List<BuildDTO>> fetchtestrounds(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchTestRounds(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'READ')")
	@ApiOperation(value = "获取更新日志", tags = {"版本" } ,notes = "获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/builds/fetchupdatelog")
	public ResponseEntity<List<BuildDTO>> fetchupdatelog(@RequestBody BuildSearchContext context) {
        Page<Build> domains = buildService.searchUpdateLog(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成版本报表", tags = {"版本"}, notes = "生成版本报表")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, BuildSearchContext context, HttpServletResponse response) {
        try {
            buildRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", buildRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, buildRuntime);
        }
    }

    @ApiOperation(value = "打印版本", tags = {"版本"}, notes = "打印版本")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/{build_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("build_ids") Set<Long> build_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = buildRuntime.getDEPrintRuntime(print_id);
        try {
            List<Build> domains = new ArrayList<>();
            for (Long build_id : build_ids) {
                domains.add(buildService.get( build_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Build[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", buildRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", build_ids, e.getMessage()), Errors.INTERNALERROR, buildRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/{action}")
    public ResponseEntity<BuildDTO> dynamicCall(@PathVariable("build_id") Long build_id , @PathVariable("action") String action , @RequestBody BuildDTO builddto) {
        Build domain = buildService.dynamicCall(build_id, action, buildMapping.toDomain(builddto));
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品建立版本", tags = {"版本" },  notes = "根据产品建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds")
    public ResponseEntity<BuildDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
		buildService.create(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', #build_id, 'READ')")
    @ApiOperation(value = "根据产品获取版本", tags = {"版本" },  notes = "根据产品获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        if (domain == null || !(product_id.equals(domain.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', #build_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除版本", tags = {"版本" },  notes = "根据产品删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id) {
        Build testget = buildService.get(build_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除版本", tags = {"版本" },  notes = "根据产品批量删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        buildService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', #build_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新版本", tags = {"版本" },  notes = "根据产品更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build testget = buildService.get(build_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
		buildService.update(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品检查版本", tags = {"版本" },  notes = "根据产品检查版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody BuildDTO builddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(buildService.checkKey(buildMapping.toDomain(builddto)));
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品获取版本草稿", tags = {"版本" },  notes = "根据产品获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"版本" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.linkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"版本" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.linkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品移动端项目版本计数器", tags = {"版本" },  notes = "根据产品移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/mobprojectbuildcounter")
    public ResponseEntity<BuildDTO> mobProjectBuildCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.mobProjectBuildCounter(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品一键发布", tags = {"版本" },  notes = "根据产品一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/oneclickrelease")
    public ResponseEntity<BuildDTO> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.oneClickRelease(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品保存版本", tags = {"版本" },  notes = "根据产品保存版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/save")
    public ResponseEntity<BuildDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        buildService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品移除Bug关联", tags = {"版本" },  notes = "根据产品移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/unlinkbug")
    public ResponseEntity<BuildDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.unlinkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品移除关联需求", tags = {"版本" },  notes = "根据产品移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/unlinkstory")
    public ResponseEntity<BuildDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.unlinkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取Bug产品版本", tags = {"版本" } ,notes = "根据产品获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchbugproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchBugProductBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchBugProductBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'NONE')")
	@ApiOperation(value = "根据产品获取Bug产品或者项目版本", tags = {"版本" } ,notes = "根据产品获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchbugproductorprojectbuild")
	public ResponseEntity<List<BuildDTO>> fetchBugProductOrProjectBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchBugProductOrProjectBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取产品版本", tags = {"版本" } ,notes = "根据产品获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchcurproduct")
	public ResponseEntity<List<BuildDTO>> fetchCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchCurProduct(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"版本" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取产品版本", tags = {"版本" } ,notes = "根据产品获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchproductbuildds")
	public ResponseEntity<List<BuildDTO>> fetchProductBuildDSByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchProductBuildDS(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'NONE')")
	@ApiOperation(value = "根据产品获取测试版本", tags = {"版本" } ,notes = "根据产品获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchtestbuild")
	public ResponseEntity<List<BuildDTO>> fetchTestBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchTestBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取测试轮次", tags = {"版本" } ,notes = "根据产品获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchtestrounds")
	public ResponseEntity<List<BuildDTO>> fetchTestRoundsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchTestRounds(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取更新日志", tags = {"版本" } ,notes = "根据产品获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchupdatelog")
	public ResponseEntity<List<BuildDTO>> fetchUpdateLogByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchUpdateLog(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目建立版本", tags = {"版本" },  notes = "根据项目建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds")
    public ResponseEntity<BuildDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
		buildService.create(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', #build_id, 'READ')")
    @ApiOperation(value = "根据项目获取版本", tags = {"版本" },  notes = "根据项目获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        if (domain == null || !(project_id.equals(domain.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #build_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除版本", tags = {"版本" },  notes = "根据项目删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
        Build testget = buildService.get(build_id);
        if (testget == null || !(project_id.equals(testget.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除版本", tags = {"版本" },  notes = "根据项目批量删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        buildService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #build_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新版本", tags = {"版本" },  notes = "根据项目更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build testget = buildService.get(build_id);
        if (testget == null || !(project_id.equals(testget.getProject())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
		buildService.update(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目检查版本", tags = {"版本" },  notes = "根据项目检查版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(buildService.checkKey(buildMapping.toDomain(builddto)));
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取版本草稿", tags = {"版本" },  notes = "根据项目获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraftByProject(@PathVariable("project_id") Long project_id, BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目关联Bug", tags = {"版本" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目关联需求", tags = {"版本" },  notes = "根据项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目移动端项目版本计数器", tags = {"版本" },  notes = "根据项目移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/mobprojectbuildcounter")
    public ResponseEntity<BuildDTO> mobProjectBuildCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.mobProjectBuildCounter(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目一键发布", tags = {"版本" },  notes = "根据项目一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/oneclickrelease")
    public ResponseEntity<BuildDTO> oneClickReleaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.oneClickRelease(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目保存版本", tags = {"版本" },  notes = "根据项目保存版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/save")
    public ResponseEntity<BuildDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        buildService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目移除Bug关联", tags = {"版本" },  notes = "根据项目移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/unlinkbug")
    public ResponseEntity<BuildDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.unlinkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目移除关联需求", tags = {"版本" },  notes = "根据项目移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/unlinkstory")
    public ResponseEntity<BuildDTO> unlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.unlinkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取Bug产品版本", tags = {"版本" } ,notes = "根据项目获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchbugproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchBugProductBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchBugProductBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'NONE')")
	@ApiOperation(value = "根据项目获取Bug产品或者项目版本", tags = {"版本" } ,notes = "根据项目获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchbugproductorprojectbuild")
	public ResponseEntity<List<BuildDTO>> fetchBugProductOrProjectBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchBugProductOrProjectBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取产品版本", tags = {"版本" } ,notes = "根据项目获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchcurproduct")
	public ResponseEntity<List<BuildDTO>> fetchCurProductByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchCurProduct(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"版本" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取产品版本", tags = {"版本" } ,notes = "根据项目获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchproductbuildds")
	public ResponseEntity<List<BuildDTO>> fetchProductBuildDSByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchProductBuildDS(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BUILD', 'NONE')")
	@ApiOperation(value = "根据项目获取测试版本", tags = {"版本" } ,notes = "根据项目获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchtestbuild")
	public ResponseEntity<List<BuildDTO>> fetchTestBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchTestBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取测试轮次", tags = {"版本" } ,notes = "根据项目获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchtestrounds")
	public ResponseEntity<List<BuildDTO>> fetchTestRoundsByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchTestRounds(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取更新日志", tags = {"版本" } ,notes = "根据项目获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchupdatelog")
	public ResponseEntity<List<BuildDTO>> fetchUpdateLogByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchUpdateLog(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

