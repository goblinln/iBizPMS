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
import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.service.IBuildService;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BuildRuntime;

@Slf4j
@Api(tags = {"版本" })
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

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "新建版本", tags = {"版本" },  notes = "新建版本")
	@RequestMapping(method = RequestMethod.POST, value = "/builds")
    @Transactional
    public ResponseEntity<BuildDTO> create(@Validated @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
		buildService.create(domain);
        if(!buildRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
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
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(build_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "删除版本", tags = {"版本" },  notes = "删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("build_id") Long build_id) {
         return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }


    @PreAuthorize("@BuildRuntime.test(#build_id, 'READ')")
    @ApiOperation(value = "获取版本", tags = {"版本" },  notes = "获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}")
    public ResponseEntity<BuildDTO> get(@PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(build_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "获取版本草稿", tags = {"版本" },  notes = "获取版本草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraft(BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "检查版本", tags = {"版本" },  notes = "检查版本")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody BuildDTO builddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(buildService.checkKey(buildMapping.toDomain(builddto)));
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "关联Bug", tags = {"版本" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBug(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.linkBug(domain);
        builddto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "关联需求", tags = {"版本" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStory(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.linkStory(domain);
        builddto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "移动端项目版本计数器", tags = {"版本" },  notes = "移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/mobprojectbuildcounter")
    public ResponseEntity<BuildDTO> mobProjectBuildCounter(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.mobProjectBuildCounter(domain);
        builddto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "一键发布", tags = {"版本" },  notes = "一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/oneclickrelease")
    public ResponseEntity<BuildDTO> oneClickRelease(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.oneClickRelease(domain);
        builddto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存版本", tags = {"版本" },  notes = "保存版本")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/save")
    public ResponseEntity<BuildDTO> save(@RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        buildService.save(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "移除Bug关联", tags = {"版本" },  notes = "移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/unlinkbug")
    public ResponseEntity<BuildDTO> unlinkBug(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.unlinkBug(domain);
        builddto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "移除关联需求", tags = {"版本" },  notes = "移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/unlinkstory")
    public ResponseEntity<BuildDTO> unlinkStory(@PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setId(build_id);
        domain = buildService.unlinkStory(domain);
        builddto = buildMapping.toDto(domain);
        Map<String,Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('READ')")
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
    @PreAuthorize("@BuildRuntime.quickTest('NONE')")
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
    @PreAuthorize("@BuildRuntime.quickTest('READ')")
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
    @PreAuthorize("@BuildRuntime.quickTest('READ')")
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
    @PreAuthorize("@BuildRuntime.quickTest('NONE')")
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
    @PreAuthorize("@BuildRuntime.quickTest('READ')")
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
    @PreAuthorize("@BuildRuntime.quickTest('READ')")
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/{action}")
    public ResponseEntity<BuildDTO> dynamicCall(@PathVariable("build_id") Long build_id , @PathVariable("action") String action , @RequestBody BuildDTO builddto) {
        Build domain = buildService.dynamicCall(build_id, action, buildMapping.toDomain(builddto));
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品建立版本", tags = {"版本" },  notes = "根据产品建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds")
    public ResponseEntity<BuildDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
		buildService.create(domain);
        BuildDTO dto = buildMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品更新版本", tags = {"版本" },  notes = "根据产品更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
		buildService.update(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品删除版本", tags = {"版本" },  notes = "根据产品删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id) {
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品获取版本", tags = {"版本" },  notes = "根据产品获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        BuildDTO dto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品获取版本草稿", tags = {"版本" },  notes = "根据产品获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品检查版本", tags = {"版本" },  notes = "根据产品检查版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody BuildDTO builddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(buildService.checkKey(buildMapping.toDomain(builddto)));
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"版本" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.linkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"版本" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.linkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品移动端项目版本计数器", tags = {"版本" },  notes = "根据产品移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/mobprojectbuildcounter")
    public ResponseEntity<BuildDTO> mobProjectBuildCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.mobProjectBuildCounter(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品一键发布", tags = {"版本" },  notes = "根据产品一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/oneclickrelease")
    public ResponseEntity<BuildDTO> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.oneClickRelease(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品保存版本", tags = {"版本" },  notes = "根据产品保存版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/save")
    public ResponseEntity<BuildDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        buildService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(domain));
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品移除Bug关联", tags = {"版本" },  notes = "根据产品移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/unlinkbug")
    public ResponseEntity<BuildDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.unlinkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品移除关联需求", tags = {"版本" },  notes = "根据产品移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/unlinkstory")
    public ResponseEntity<BuildDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.unlinkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据产品获取Bug产品版本", tags = {"版本" } ,notes = "根据产品获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchbugproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchBuildBugProductBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchBugProductBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('NONE')")
	@ApiOperation(value = "根据产品获取Bug产品或者项目版本", tags = {"版本" } ,notes = "根据产品获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchbugproductorprojectbuild")
	public ResponseEntity<List<BuildDTO>> fetchBuildBugProductOrProjectBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchBugProductOrProjectBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据产品获取产品版本", tags = {"版本" } ,notes = "根据产品获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchcurproduct")
	public ResponseEntity<List<BuildDTO>> fetchBuildCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchCurProduct(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"版本" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchBuildDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('NONE')")
	@ApiOperation(value = "根据产品获取测试版本", tags = {"版本" } ,notes = "根据产品获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchtestbuild")
	public ResponseEntity<List<BuildDTO>> fetchBuildTestBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchTestBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据产品获取测试轮次", tags = {"版本" } ,notes = "根据产品获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchtestrounds")
	public ResponseEntity<List<BuildDTO>> fetchBuildTestRoundsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchTestRounds(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据产品获取更新日志", tags = {"版本" } ,notes = "根据产品获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchupdatelog")
	public ResponseEntity<List<BuildDTO>> fetchBuildUpdateLogByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchUpdateLog(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目建立版本", tags = {"版本" },  notes = "根据项目建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds")
    public ResponseEntity<BuildDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
		buildService.create(domain);
        BuildDTO dto = buildMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目更新版本", tags = {"版本" },  notes = "根据项目更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
		buildService.update(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目删除版本", tags = {"版本" },  notes = "根据项目删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目获取版本", tags = {"版本" },  notes = "根据项目获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        BuildDTO dto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目获取版本草稿", tags = {"版本" },  notes = "根据项目获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraftByProject(@PathVariable("project_id") Long project_id, BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目检查版本", tags = {"版本" },  notes = "根据项目检查版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(buildService.checkKey(buildMapping.toDomain(builddto)));
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联Bug", tags = {"版本" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联需求", tags = {"版本" },  notes = "根据项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目移动端项目版本计数器", tags = {"版本" },  notes = "根据项目移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/mobprojectbuildcounter")
    public ResponseEntity<BuildDTO> mobProjectBuildCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.mobProjectBuildCounter(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目一键发布", tags = {"版本" },  notes = "根据项目一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/oneclickrelease")
    public ResponseEntity<BuildDTO> oneClickReleaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.oneClickRelease(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目保存版本", tags = {"版本" },  notes = "根据项目保存版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/save")
    public ResponseEntity<BuildDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        buildService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(domain));
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目移除Bug关联", tags = {"版本" },  notes = "根据项目移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/unlinkbug")
    public ResponseEntity<BuildDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.unlinkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目移除关联需求", tags = {"版本" },  notes = "根据项目移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/unlinkstory")
    public ResponseEntity<BuildDTO> unlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.unlinkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据项目获取Bug产品版本", tags = {"版本" } ,notes = "根据项目获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchbugproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchBuildBugProductBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchBugProductBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('NONE')")
	@ApiOperation(value = "根据项目获取Bug产品或者项目版本", tags = {"版本" } ,notes = "根据项目获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchbugproductorprojectbuild")
	public ResponseEntity<List<BuildDTO>> fetchBuildBugProductOrProjectBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchBugProductOrProjectBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据项目获取产品版本", tags = {"版本" } ,notes = "根据项目获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchcurproduct")
	public ResponseEntity<List<BuildDTO>> fetchBuildCurProductByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchCurProduct(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"版本" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchBuildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('NONE')")
	@ApiOperation(value = "根据项目获取测试版本", tags = {"版本" } ,notes = "根据项目获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchtestbuild")
	public ResponseEntity<List<BuildDTO>> fetchBuildTestBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchTestBuild(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据项目获取测试轮次", tags = {"版本" } ,notes = "根据项目获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchtestrounds")
	public ResponseEntity<List<BuildDTO>> fetchBuildTestRoundsByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchTestRounds(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据项目获取更新日志", tags = {"版本" } ,notes = "根据项目获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchupdatelog")
	public ResponseEntity<List<BuildDTO>> fetchBuildUpdateLogByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
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

