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
import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.service.IReleaseService;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ReleaseRuntime;

@Slf4j
@Api(tags = {"发布" })
@RestController("WebApi-release")
@RequestMapping("")
public class ReleaseResource {

    @Autowired
    public IReleaseService releaseService;

    @Autowired
    public ReleaseRuntime releaseRuntime;

    @Autowired
    @Lazy
    public ReleaseMapping releaseMapping;

    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"发布" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/fetchdefault")
	public ResponseEntity<List<ReleaseDTO>> fetchdefault(@RequestBody ReleaseSearchContext context) {
        Page<Release> domains = releaseService.searchDefault(context) ;
        List<ReleaseDTO> list = releaseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"发布" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/searchdefault")
	public ResponseEntity<Page<ReleaseDTO>> searchDefault(@RequestBody ReleaseSearchContext context) {
        Page<Release> domains = releaseService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(releaseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ReleaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建发布", tags = {"发布" },  notes = "新建发布")
	@RequestMapping(method = RequestMethod.POST, value = "/releases")
    @Transactional
    public ResponseEntity<ReleaseDTO> create(@Validated @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
		releaseService.create(domain);
        if(!releaseRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ReleaseDTO dto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'DELETE')")
    @ApiOperation(value = "删除发布", tags = {"发布" },  notes = "删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/releases/{release_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("release_id") Long release_id) {
         return ResponseEntity.status(HttpStatus.OK).body(releaseService.remove(release_id));
    }


    @ApiOperation(value = "解除关联需求", tags = {"发布" },  notes = "解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/unlinkstory")
    public ResponseEntity<ReleaseDTO> unlinkStory(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.unlinkStory(domain);
        releasedto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(domain.getId());
        releasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'ACTIVATE')")
    @ApiOperation(value = "状态变更（激活）", tags = {"发布" },  notes = "状态变更（激活）")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/activate")
    public ResponseEntity<ReleaseDTO> activate(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.activate(domain);
        releasedto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(domain.getId());
        releasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "关联Bug（遗留Bug）", tags = {"发布" },  notes = "关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/linkbugbyleftbug")
    public ResponseEntity<ReleaseDTO> linkBugbyLeftBug(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.linkBugbyLeftBug(domain);
        releasedto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(domain.getId());
        releasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }


    @PreAuthorize("@ReleaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取发布草稿", tags = {"发布" },  notes = "获取发布草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/getdraft")
    public ResponseEntity<ReleaseDTO> getDraft(ReleaseDTO dto) {
        Release domain = releaseMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(releaseMapping.toDto(releaseService.getDraft(domain)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'LINKSTORY')")
    @ApiOperation(value = "关联需求", tags = {"发布" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/linkstory")
    public ResponseEntity<ReleaseDTO> linkStory(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.linkStory(domain);
        releasedto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(domain.getId());
        releasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "获取发布", tags = {"发布" },  notes = "获取发布")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}")
    public ResponseEntity<ReleaseDTO> get(@PathVariable("release_id") Long release_id) {
        Release domain = releaseService.get(release_id);
        ReleaseDTO dto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(release_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'TERMINATE')")
    @ApiOperation(value = "状态变更（停止维护）", tags = {"发布" },  notes = "状态变更（停止维护）")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/terminate")
    public ResponseEntity<ReleaseDTO> terminate(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.terminate(domain);
        releasedto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(domain.getId());
        releasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "解除关联Bug", tags = {"发布" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/unlinkbug")
    public ResponseEntity<ReleaseDTO> unlinkBug(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.unlinkBug(domain);
        releasedto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(domain.getId());
        releasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "更新发布", tags = {"发布" },  notes = "更新发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/{release_id}")
    @Transactional
    public ResponseEntity<ReleaseDTO> update(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
		Release domain  = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
		releaseService.update(domain );
        if(!releaseRuntime.test(release_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ReleaseDTO dto = releaseMapping.toDto(domain);
        Map<String,Integer> opprivs = releaseRuntime.getOPPrivs(release_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/{action}")
    public ResponseEntity<ReleaseDTO> dynamicCall(@PathVariable("release_id") Long release_id , @PathVariable("action") String action , @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseService.dynamicCall(release_id, action, releaseMapping.toDomain(releasedto));
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"发布" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/fetchdefault")
	public ResponseEntity<List<ReleaseDTO>> fetchReleaseDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Release> domains = releaseService.searchDefault(context) ;
        List<ReleaseDTO> list = releaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"发布" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/searchdefault")
	public ResponseEntity<Page<ReleaseDTO>> searchReleaseDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Release> domains = releaseService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(releaseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立发布", tags = {"发布" },  notes = "根据产品建立发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases")
    public ResponseEntity<ReleaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
		releaseService.create(domain);
        ReleaseDTO dto = releaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除发布", tags = {"发布" },  notes = "根据产品删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/releases/{release_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id) {
		return ResponseEntity.status(HttpStatus.OK).body(releaseService.remove(release_id));
    }


    @ApiOperation(value = "根据产品解除关联需求发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/unlinkstory")
    public ResponseEntity<ReleaseDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.unlinkStory(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品状态变更（激活）发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/activate")
    public ResponseEntity<ReleaseDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.activate(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/linkbugbyleftbug")
    public ResponseEntity<ReleaseDTO> linkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.linkBugbyLeftBug(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取发布草稿", tags = {"发布" },  notes = "根据产品获取发布草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/getdraft")
    public ResponseEntity<ReleaseDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ReleaseDTO dto) {
        Release domain = releaseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(releaseMapping.toDto(releaseService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品关联需求发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/linkstory")
    public ResponseEntity<ReleaseDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.linkStory(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取发布", tags = {"发布" },  notes = "根据产品获取发布")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}")
    public ResponseEntity<ReleaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id) {
        Release domain = releaseService.get(release_id);
        ReleaseDTO dto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品状态变更（停止维护）发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/terminate")
    public ResponseEntity<ReleaseDTO> terminateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.terminate(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品解除关联Bug发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/unlinkbug")
    public ResponseEntity<ReleaseDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.unlinkBug(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新发布", tags = {"发布" },  notes = "根据产品更新发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/{release_id}")
    public ResponseEntity<ReleaseDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
		releaseService.update(domain);
        ReleaseDTO dto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

