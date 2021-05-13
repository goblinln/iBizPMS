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
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建发布", tags = {"发布" },  notes = "批量新建发布")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        releaseService.createBatch(releaseMapping.toDomain(releasedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新发布", tags = {"发布" },  notes = "批量更新发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        releaseService.updateBatch(releaseMapping.toDomain(releasedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'DELETE')")
    @ApiOperation(value = "删除发布", tags = {"发布" },  notes = "删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/releases/{release_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("release_id") Long release_id) {
         return ResponseEntity.status(HttpStatus.OK).body(releaseService.remove(release_id));
    }

    @PreAuthorize("@ReleaseRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除发布", tags = {"发布" },  notes = "批量删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/releases/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        releaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "获取发布", tags = {"发布" },  notes = "获取发布")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}")
    public ResponseEntity<ReleaseDTO> get(@PathVariable("release_id") Long release_id) {
        Release domain = releaseService.get(release_id);
        ReleaseDTO dto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取发布草稿", tags = {"发布" },  notes = "获取发布草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/getdraft")
    public ResponseEntity<ReleaseDTO> getDraft(ReleaseDTO dto) {
        Release domain = releaseMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(releaseMapping.toDto(releaseService.getDraft(domain)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "状态变更（激活）", tags = {"发布" },  notes = "状态变更（激活）")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/activate")
    public ResponseEntity<ReleaseDTO> activate(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.activate(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('READ')")
    @ApiOperation(value = "批量处理[状态变更（激活）]", tags = {"发布" },  notes = "批量处理[状态变更（激活）]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/activatebatch")
    public ResponseEntity<Boolean> activateBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "批量解除关联Bug", tags = {"发布" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/batchunlinkbug")
    public ResponseEntity<ReleaseDTO> batchUnlinkBug(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.batchUnlinkBug(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('READ')")
    @ApiOperation(value = "批量处理[批量解除关联Bug]", tags = {"发布" },  notes = "批量处理[批量解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "状态变更", tags = {"发布" },  notes = "状态变更")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/changestatus")
    public ResponseEntity<ReleaseDTO> changeStatus(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.changeStatus(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('UPDATE')")
    @ApiOperation(value = "批量处理[状态变更]", tags = {"发布" },  notes = "批量处理[状态变更]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/changestatusbatch")
    public ResponseEntity<Boolean> changeStatusBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.changeStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "检查发布", tags = {"发布" },  notes = "检查发布")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ReleaseDTO releasedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(releaseService.checkKey(releaseMapping.toDomain(releasedto)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "关联Bug", tags = {"发布" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/linkbug")
    public ResponseEntity<ReleaseDTO> linkBug(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.linkBug(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('READ')")
    @ApiOperation(value = "批量处理[关联Bug]", tags = {"发布" },  notes = "批量处理[关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/linkbugbatch")
    public ResponseEntity<Boolean> linkBugBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "关联Bug（解决Bug）", tags = {"发布" },  notes = "关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/linkbugbybug")
    public ResponseEntity<ReleaseDTO> linkBugbyBug(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.linkBugbyBug(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('READ')")
    @ApiOperation(value = "批量处理[关联Bug（解决Bug）]", tags = {"发布" },  notes = "批量处理[关联Bug（解决Bug）]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/linkbugbybugbatch")
    public ResponseEntity<Boolean> linkBugbyBugBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkBugbyBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "关联Bug（遗留Bug）", tags = {"发布" },  notes = "关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/linkbugbyleftbug")
    public ResponseEntity<ReleaseDTO> linkBugbyLeftBug(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.linkBugbyLeftBug(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('READ')")
    @ApiOperation(value = "批量处理[关联Bug（遗留Bug）]", tags = {"发布" },  notes = "批量处理[关联Bug（遗留Bug）]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/linkbugbyleftbugbatch")
    public ResponseEntity<Boolean> linkBugbyLeftBugBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "关联需求", tags = {"发布" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/linkstory")
    public ResponseEntity<ReleaseDTO> linkStory(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.linkStory(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('READ')")
    @ApiOperation(value = "批量处理[关联需求]", tags = {"发布" },  notes = "批量处理[关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "移动端发布计数器", tags = {"发布" },  notes = "移动端发布计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/{release_id}/mobreleasecounter")
    public ResponseEntity<ReleaseDTO> mobReleaseCounter(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.mobReleaseCounter(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }

    @ApiOperation(value = "一键发布", tags = {"发布" },  notes = "一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/oneclickrelease")
    public ResponseEntity<ReleaseDTO> oneClickRelease(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.oneClickRelease(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[一键发布]", tags = {"发布" },  notes = "批量处理[一键发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/oneclickreleasebatch")
    public ResponseEntity<Boolean> oneClickReleaseBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.oneClickReleaseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存发布", tags = {"发布" },  notes = "保存发布")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/save")
    public ResponseEntity<ReleaseDTO> save(@RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        releaseService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releaseMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存发布", tags = {"发布" },  notes = "批量保存发布")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        releaseService.saveBatch(releaseMapping.toDomain(releasedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "状态变更（停止维护）", tags = {"发布" },  notes = "状态变更（停止维护）")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/terminate")
    public ResponseEntity<ReleaseDTO> terminate(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.terminate(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('UPDATE')")
    @ApiOperation(value = "批量处理[状态变更（停止维护）]", tags = {"发布" },  notes = "批量处理[状态变更（停止维护）]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/terminatebatch")
    public ResponseEntity<Boolean> terminateBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.terminateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "解除关联Bug", tags = {"发布" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/unlinkbug")
    public ResponseEntity<ReleaseDTO> unlinkBug(@PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setId(release_id);
        domain = releaseService.unlinkBug(domain);
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.test('READ')")
    @ApiOperation(value = "批量处理[解除关联Bug]", tags = {"发布" },  notes = "批量处理[解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugBatch(@RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"发布" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/fetchdefault")
	public ResponseEntity<List<ReleaseDTO>> fetchdefault(@RequestBody ReleaseSearchContext context) {
        releaseRuntime.addAuthorityConditions(context,"READ");
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
        releaseRuntime.addAuthorityConditions(context,"READ");
        Page<Release> domains = releaseService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(releaseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联发布", tags = {"发布" } ,notes = "获取测试报告关联发布")
    @RequestMapping(method= RequestMethod.POST , value="/releases/fetchreportrelease")
	public ResponseEntity<List<ReleaseDTO>> fetchreportrelease(@RequestBody ReleaseSearchContext context) {
        releaseRuntime.addAuthorityConditions(context,"READ");
        Page<Release> domains = releaseService.searchReportRelease(context) ;
        List<ReleaseDTO> list = releaseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联发布", tags = {"发布" } ,notes = "查询测试报告关联发布")
    @RequestMapping(method= RequestMethod.POST , value="/releases/searchreportrelease")
	public ResponseEntity<Page<ReleaseDTO>> searchReportRelease(@RequestBody ReleaseSearchContext context) {
        releaseRuntime.addAuthorityConditions(context,"READ");
        Page<Release> domains = releaseService.searchReportRelease(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(releaseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/{action}")
    public ResponseEntity<ReleaseDTO> dynamicCall(@PathVariable("release_id") Long release_id , @PathVariable("action") String action , @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseService.dynamicCall(release_id, action, releaseMapping.toDomain(releasedto));
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @PreAuthorize("@ReleaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品建立发布", tags = {"发布" },  notes = "根据产品建立发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases")
    public ResponseEntity<ReleaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
		releaseService.create(domain);
        ReleaseDTO dto = releaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品批量建立发布", tags = {"发布" },  notes = "根据产品批量建立发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domainlist=releaseMapping.toDomain(releasedtos);
        for(Release domain:domainlist){
            domain.setProduct(product_id);
        }
        releaseService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
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

    @PreAuthorize("@ReleaseRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据产品批量更新发布", tags = {"发布" },  notes = "根据产品批量更新发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/batch")
    public ResponseEntity<Boolean> updateBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domainlist=releaseMapping.toDomain(releasedtos);
        for(Release domain:domainlist){
            domain.setProduct(product_id);
        }
        releaseService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'DELETE')")
    @ApiOperation(value = "根据产品删除发布", tags = {"发布" },  notes = "根据产品删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/releases/{release_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id) {
		return ResponseEntity.status(HttpStatus.OK).body(releaseService.remove(release_id));
    }

    @PreAuthorize("@ReleaseRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品批量删除发布", tags = {"发布" },  notes = "根据产品批量删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/releases/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        releaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品获取发布", tags = {"发布" },  notes = "根据产品获取发布")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}")
    public ResponseEntity<ReleaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id) {
        Release domain = releaseService.get(release_id);
        ReleaseDTO dto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品获取发布草稿", tags = {"发布" },  notes = "根据产品获取发布草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/getdraft")
    public ResponseEntity<ReleaseDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ReleaseDTO dto) {
        Release domain = releaseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(releaseMapping.toDto(releaseService.getDraft(domain)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/activate")
    public ResponseEntity<ReleaseDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.activate(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/activatebatch")
    public ResponseEntity<Boolean> activateByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/batchunlinkbug")
    public ResponseEntity<ReleaseDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.batchUnlinkBug(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/changestatus")
    public ResponseEntity<ReleaseDTO> changeStatusByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.changeStatus(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/changestatusbatch")
    public ResponseEntity<Boolean> changeStatusByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.changeStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品检查发布", tags = {"发布" },  notes = "根据产品检查发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ReleaseDTO releasedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(releaseService.checkKey(releaseMapping.toDomain(releasedto)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/linkbug")
    public ResponseEntity<ReleaseDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.linkBug(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/linkbugbybug")
    public ResponseEntity<ReleaseDTO> linkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.linkBugbyBug(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/linkbugbybugbatch")
    public ResponseEntity<Boolean> linkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkBugbyBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/linkbugbyleftbug")
    public ResponseEntity<ReleaseDTO> linkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.linkBugbyLeftBug(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/linkbugbyleftbugbatch")
    public ResponseEntity<Boolean> linkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/linkstory")
    public ResponseEntity<ReleaseDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.linkStory(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/{release_id}/mobreleasecounter")
    public ResponseEntity<ReleaseDTO> mobReleaseCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.mobReleaseCounter(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/oneclickrelease")
    public ResponseEntity<ReleaseDTO> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.oneClickRelease(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/oneclickreleasebatch")
    public ResponseEntity<Boolean> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.oneClickReleaseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品保存发布", tags = {"发布" },  notes = "根据产品保存发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/save")
    public ResponseEntity<ReleaseDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        releaseService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releaseMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品批量保存发布", tags = {"发布" },  notes = "根据产品批量保存发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domainlist=releaseMapping.toDomain(releasedtos);
        for(Release domain:domainlist){
             domain.setProduct(product_id);
        }
        releaseService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/terminate")
    public ResponseEntity<ReleaseDTO> terminateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.terminate(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/terminatebatch")
    public ResponseEntity<Boolean> terminateByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.terminateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据产品发布", tags = {"发布" },  notes = "根据产品发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/unlinkbug")
    public ResponseEntity<ReleaseDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ReleaseDTO releasedto) {
        Release domain = releaseMapping.toDomain(releasedto);
        domain.setProduct(product_id);
        domain.setId(release_id);
        domain = releaseService.unlinkBug(domain) ;
        releasedto = releaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(releasedto);
    }
    @ApiOperation(value = "批量处理[根据产品发布]", tags = {"发布" },  notes = "批量处理[根据产品发布]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ReleaseDTO> releasedtos) {
        List<Release> domains = releaseMapping.toDomain(releasedtos);
        boolean result = releaseService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"发布" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/fetchdefault")
	public ResponseEntity<List<ReleaseDTO>> fetchReleaseDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        releaseRuntime.addAuthorityConditions(context,"READ");
        Page<Release> domains = releaseService.searchDefault(context) ;
        List<ReleaseDTO> list = releaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"发布" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/searchdefault")
	public ResponseEntity<Page<ReleaseDTO>> searchReleaseDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        releaseRuntime.addAuthorityConditions(context,"READ");
        Page<Release> domains = releaseService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(releaseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品获取测试报告关联发布", tags = {"发布" } ,notes = "根据产品获取测试报告关联发布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/fetchreportrelease")
	public ResponseEntity<List<ReleaseDTO>> fetchReleaseReportReleaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        releaseRuntime.addAuthorityConditions(context,"READ");
        Page<Release> domains = releaseService.searchReportRelease(context) ;
        List<ReleaseDTO> list = releaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ReleaseRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品查询测试报告关联发布", tags = {"发布" } ,notes = "根据产品查询测试报告关联发布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/searchreportrelease")
	public ResponseEntity<Page<ReleaseDTO>> searchReleaseReportReleaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        releaseRuntime.addAuthorityConditions(context,"READ");
        Page<Release> domains = releaseService.searchReportRelease(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(releaseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

