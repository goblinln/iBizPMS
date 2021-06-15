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
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.StoryRuntime;

@Slf4j
@Api(tags = {"需求"})
@RestController("StandardAPI-story")
@RequestMapping("")
public class StoryResource {

    @Autowired
    public IStoryService storyService;

    @Autowired
    public StoryRuntime storyRuntime;

    @Autowired
    @Lazy
    public StoryMapping storyMapping;


    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立需求", tags = {"需求" },  notes = "根据产品建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories")
    public ResponseEntity<StoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品批量建立需求", tags = {"需求" },  notes = "根据产品批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
            domain.setProduct(product_id);
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品获取需求", tags = {"需求" },  notes = "根据产品获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        if (domain == null || !(product_id.equals(domain.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'DELETE', #story_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除需求", tags = {"需求" },  notes = "根据产品删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
        Story testget = storyService.get(story_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除需求", tags = {"需求" },  notes = "根据产品批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'UPDATE', #story_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新需求", tags = {"需求" },  notes = "根据产品更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story testget = storyService.get(story_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'ACTIVATE')")
    @ApiOperation(value = "根据产品激活", tags = {"需求" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'ASSIGNTO')")
    @ApiOperation(value = "根据产品指派", tags = {"需求" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'BUILDLINK')")
    @ApiOperation(value = "根据产品版本解除关联需求", tags = {"需求" },  notes = "根据产品版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/buildunlinkstory")
    public ResponseEntity<StoryDTO> buildUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.buildUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'CHANGE')")
    @ApiOperation(value = "根据产品变更", tags = {"需求" },  notes = "根据产品变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'CLOSE')")
    @ApiOperation(value = "根据产品关闭", tags = {"需求" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品获取需求描述", tags = {"需求" },  notes = "根据产品获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/getstoryspecs")
    public ResponseEntity<StoryDTO> getStorySpecsByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.getStorySpecs(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取需求草稿", tags = {"需求" },  notes = "根据产品获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'PLANLINK')")
    @ApiOperation(value = "根据产品计划解除关联需求", tags = {"需求" },  notes = "根据产品计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/unlinkstory")
    public ResponseEntity<StoryDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.unlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'PROJECTLINK')")
    @ApiOperation(value = "根据产品项目解除关联需求", tags = {"需求" },  notes = "根据产品项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/projectunlinkstory")
    public ResponseEntity<StoryDTO> projectUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.projectUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'RELEASELINK')")
    @ApiOperation(value = "根据产品发布解除关联需求", tags = {"需求" },  notes = "根据产品发布解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/releaseunlinkstory")
    public ResponseEntity<StoryDTO> releaseUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.releaseUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'REVIEW')")
    @ApiOperation(value = "根据产品评审", tags = {"需求" },  notes = "根据产品评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'FAVORITES')")
    @ApiOperation(value = "根据产品需求收藏", tags = {"需求" },  notes = "根据产品需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'NFAVORITES')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"需求" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"需求" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取数据查询", tags = {"需求" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchParentDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目相关需求", tags = {"需求" } ,notes = "根据产品获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchprojectstories")
	public ResponseEntity<List<StoryDTO>> fetchProjectStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchStoryRelatedByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目建立需求", tags = {"需求" },  notes = "根据项目建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories")
    public ResponseEntity<StoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目批量建立需求", tags = {"需求" },  notes = "根据项目批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
            
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据项目获取需求", tags = {"需求" },  notes = "根据项目获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'UPDATE', #story_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除需求", tags = {"需求" },  notes = "根据项目删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'UPDATE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除需求", tags = {"需求" },  notes = "根据项目批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'UPDATE', #story_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新需求", tags = {"需求" },  notes = "根据项目更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_STORY', 'ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"需求" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派", tags = {"需求" },  notes = "根据项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'BUILDLINK')")
    @ApiOperation(value = "根据项目版本解除关联需求", tags = {"需求" },  notes = "根据项目版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/buildunlinkstory")
    public ResponseEntity<StoryDTO> buildUnlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.buildUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'CHANGE')")
    @ApiOperation(value = "根据项目变更", tags = {"需求" },  notes = "根据项目变更")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'CLOSE')")
    @ApiOperation(value = "根据项目关闭", tags = {"需求" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据项目获取需求描述", tags = {"需求" },  notes = "根据项目获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/getstoryspecs")
    public ResponseEntity<StoryDTO> getStorySpecsByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.getStorySpecs(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取需求草稿", tags = {"需求" },  notes = "根据项目获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_STORY', 'PLANLINK')")
    @ApiOperation(value = "根据项目计划解除关联需求", tags = {"需求" },  notes = "根据项目计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/unlinkstory")
    public ResponseEntity<StoryDTO> unlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.unlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', #story_id, 'PROJECTLINK')")
    @ApiOperation(value = "根据项目项目解除关联需求", tags = {"需求" },  notes = "根据项目项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/projectunlinkstory")
    public ResponseEntity<StoryDTO> projectUnlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.projectUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'RELEASELINK')")
    @ApiOperation(value = "根据项目发布解除关联需求", tags = {"需求" },  notes = "根据项目发布解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/releaseunlinkstory")
    public ResponseEntity<StoryDTO> releaseUnlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.releaseUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'REVIEW')")
    @ApiOperation(value = "根据项目评审", tags = {"需求" },  notes = "根据项目评审")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'FAVORITES')")
    @ApiOperation(value = "根据项目需求收藏", tags = {"需求" },  notes = "根据项目需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'NFAVORITES')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"需求" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"需求" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取数据查询", tags = {"需求" } ,notes = "根据项目获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchParentDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目相关需求", tags = {"需求" } ,notes = "根据项目获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/fetchprojectstories")
	public ResponseEntity<List<StoryDTO>> fetchProjectStoriesByProject(@PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据项目获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchStoryRelatedByProject(@PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目建立需求", tags = {"需求" },  notes = "根据产品项目建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories")
    public ResponseEntity<StoryDTO> createByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目批量建立需求", tags = {"需求" },  notes = "根据产品项目批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> createBatchByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
            
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品项目获取需求", tags = {"需求" },  notes = "根据产品项目获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'UPDATE', #story_id, 'DELETE')")
    @ApiOperation(value = "根据产品项目删除需求", tags = {"需求" },  notes = "根据产品项目删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'UPDATE', 'DELETE')")
    @ApiOperation(value = "根据产品项目批量删除需求", tags = {"需求" },  notes = "根据产品项目批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchByProductProject(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'UPDATE', #story_id, 'UPDATE')")
    @ApiOperation(value = "根据产品项目更新需求", tags = {"需求" },  notes = "根据产品项目更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_STORY', 'ACTIVATE')")
    @ApiOperation(value = "根据产品项目激活", tags = {"需求" },  notes = "根据产品项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'ASSIGNTO')")
    @ApiOperation(value = "根据产品项目指派", tags = {"需求" },  notes = "根据产品项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'BUILDLINK')")
    @ApiOperation(value = "根据产品项目版本解除关联需求", tags = {"需求" },  notes = "根据产品项目版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/buildunlinkstory")
    public ResponseEntity<StoryDTO> buildUnlinkStoryByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.buildUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'CHANGE')")
    @ApiOperation(value = "根据产品项目变更", tags = {"需求" },  notes = "根据产品项目变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'CLOSE')")
    @ApiOperation(value = "根据产品项目关闭", tags = {"需求" },  notes = "根据产品项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品项目获取需求描述", tags = {"需求" },  notes = "根据产品项目获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/getstoryspecs")
    public ResponseEntity<StoryDTO> getStorySpecsByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.getStorySpecs(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目获取需求草稿", tags = {"需求" },  notes = "根据产品项目获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_STORY', 'PLANLINK')")
    @ApiOperation(value = "根据产品项目计划解除关联需求", tags = {"需求" },  notes = "根据产品项目计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/unlinkstory")
    public ResponseEntity<StoryDTO> unlinkStoryByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.unlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'STORYMANAGE', #story_id, 'PROJECTLINK')")
    @ApiOperation(value = "根据产品项目项目解除关联需求", tags = {"需求" },  notes = "根据产品项目项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/projectunlinkstory")
    public ResponseEntity<StoryDTO> projectUnlinkStoryByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.projectUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'RELEASELINK')")
    @ApiOperation(value = "根据产品项目发布解除关联需求", tags = {"需求" },  notes = "根据产品项目发布解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/releaseunlinkstory")
    public ResponseEntity<StoryDTO> releaseUnlinkStoryByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.releaseUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'REVIEW')")
    @ApiOperation(value = "根据产品项目评审", tags = {"需求" },  notes = "根据产品项目评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'FAVORITES')")
    @ApiOperation(value = "根据产品项目需求收藏", tags = {"需求" },  notes = "根据产品项目需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'NFAVORITES')")
    @ApiOperation(value = "根据产品项目取消收藏", tags = {"需求" },  notes = "根据产品项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取DEFAULT", tags = {"需求" } ,notes = "根据产品项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchDefaultByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取数据查询", tags = {"需求" } ,notes = "根据产品项目获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchParentDefaultByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取项目相关需求", tags = {"需求" } ,notes = "根据产品项目获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/stories/fetchprojectstories")
	public ResponseEntity<List<StoryDTO>> fetchProjectStoriesByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品项目获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchStoryRelatedByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

