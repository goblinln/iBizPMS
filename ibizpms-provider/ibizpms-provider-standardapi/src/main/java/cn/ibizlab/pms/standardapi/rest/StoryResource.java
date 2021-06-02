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
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.StoryRuntime;

@Slf4j
@Api(tags = {"需求" })
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


    @PreAuthorize("@StoryRuntime.quickTest('FAVORITES')")
    @ApiOperation(value = "根据系统用户需求收藏", tags = {"需求" },  notes = "根据系统用户需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"需求" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/fetchaccount")
	public ResponseEntity<List<StoryDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchAccount(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取数据查询", tags = {"需求" } ,notes = "根据系统用户获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchParentDefaultBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('ACTIVATE')")
    @ApiOperation(value = "根据系统用户激活", tags = {"需求" },  notes = "根据系统用户激活")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('CLOSE')")
    @ApiOperation(value = "根据系统用户关闭", tags = {"需求" },  notes = "根据系统用户关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户获取需求草稿", tags = {"需求" },  notes = "根据系统用户获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
    @ApiOperation(value = "根据系统用户获取需求", tags = {"需求" },  notes = "根据系统用户获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取项目相关需求", tags = {"需求" } ,notes = "根据系统用户获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/fetchprojectstories")
	public ResponseEntity<List<StoryDTO>> fetchProjectStoriesBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('ASSIGNTO')")
    @ApiOperation(value = "根据系统用户指派", tags = {"需求" },  notes = "根据系统用户指派")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据系统用户获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchStoryRelatedBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('CHANGE')")
    @ApiOperation(value = "根据系统用户变更", tags = {"需求" },  notes = "根据系统用户变更")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户删除需求", tags = {"需求" },  notes = "根据系统用户删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("@StoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户批量删除需求", tags = {"需求" },  notes = "根据系统用户批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户建立需求", tags = {"需求" },  notes = "根据系统用户建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories")
    public ResponseEntity<StoryDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户批量建立需求", tags = {"需求" },  notes = "根据系统用户批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/batch")
    public ResponseEntity<Boolean> createBatchBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
            
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@StoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户更新需求", tags = {"需求" },  notes = "根据系统用户更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('NFAVORITES')")
    @ApiOperation(value = "根据系统用户取消收藏", tags = {"需求" },  notes = "根据系统用户取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"需求" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/fetchmy")
	public ResponseEntity<List<StoryDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMy(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取DEFAULT", tags = {"需求" } ,notes = "根据系统用户获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchDefaultBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('REVIEW')")
    @ApiOperation(value = "根据系统用户评审", tags = {"需求" },  notes = "根据系统用户评审")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取我的收藏", tags = {"需求" } ,notes = "根据系统用户获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/fetchmyfavorites")
	public ResponseEntity<List<StoryDTO>> fetchMyFavoritesBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品需求收藏", tags = {"需求" },  notes = "根据产品需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取指定用户数据", tags = {"需求" } ,notes = "根据产品获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchaccount")
	public ResponseEntity<List<StoryDTO>> fetchAccountByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAccount(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品激活", tags = {"需求" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品关闭", tags = {"需求" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品获取需求草稿", tags = {"需求" },  notes = "根据产品获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取需求", tags = {"需求" },  notes = "根据产品获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品指派", tags = {"需求" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品变更", tags = {"需求" },  notes = "根据产品变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除需求", tags = {"需求" },  notes = "根据产品删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品批量删除需求", tags = {"需求" },  notes = "根据产品批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品建立需求", tags = {"需求" },  notes = "根据产品建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories")
    public ResponseEntity<StoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
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

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新需求", tags = {"需求" },  notes = "根据产品更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"需求" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取我的数据", tags = {"需求" } ,notes = "根据产品获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchmy")
	public ResponseEntity<List<StoryDTO>> fetchMyByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMy(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品评审", tags = {"需求" },  notes = "根据产品评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"需求" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchmyfavorites")
	public ResponseEntity<List<StoryDTO>> fetchMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('FAVORITES')")
    @ApiOperation(value = "根据项目需求收藏", tags = {"需求" },  notes = "根据项目需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取指定用户数据", tags = {"需求" } ,notes = "根据项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/fetchaccount")
	public ResponseEntity<List<StoryDTO>> fetchAccountByProject(@PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchAccount(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@StoryRuntime.quickTest('ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"需求" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('CLOSE')")
    @ApiOperation(value = "根据项目关闭", tags = {"需求" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据项目获取需求草稿", tags = {"需求" },  notes = "根据项目获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取需求", tags = {"需求" },  notes = "根据项目获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@StoryRuntime.quickTest('ASSIGNTO')")
    @ApiOperation(value = "根据项目指派", tags = {"需求" },  notes = "根据项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@StoryRuntime.quickTest('CHANGE')")
    @ApiOperation(value = "根据项目变更", tags = {"需求" },  notes = "根据项目变更")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目删除需求", tags = {"需求" },  notes = "根据项目删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("@StoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据项目批量删除需求", tags = {"需求" },  notes = "根据项目批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据项目建立需求", tags = {"需求" },  notes = "根据项目建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories")
    public ResponseEntity<StoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据项目批量建立需求", tags = {"需求" },  notes = "根据项目批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
            
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@StoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据项目更新需求", tags = {"需求" },  notes = "根据项目更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('NFAVORITES')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"需求" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取我的数据", tags = {"需求" } ,notes = "根据项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/fetchmy")
	public ResponseEntity<List<StoryDTO>> fetchMyByProject(@PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMy(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@StoryRuntime.quickTest('REVIEW')")
    @ApiOperation(value = "根据项目评审", tags = {"需求" },  notes = "根据项目评审")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewByProject(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"需求" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/fetchmyfavorites")
	public ResponseEntity<List<StoryDTO>> fetchMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品需求收藏", tags = {"需求" },  notes = "根据系统用户产品需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取指定用户数据", tags = {"需求" } ,notes = "根据系统用户产品获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/fetchaccount")
	public ResponseEntity<List<StoryDTO>> fetchAccountBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAccount(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取数据查询", tags = {"需求" } ,notes = "根据系统用户产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchParentDefaultBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品激活", tags = {"需求" },  notes = "根据系统用户产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品关闭", tags = {"需求" },  notes = "根据系统用户产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品获取需求草稿", tags = {"需求" },  notes = "根据系统用户产品获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品获取需求", tags = {"需求" },  notes = "根据系统用户产品获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取项目相关需求", tags = {"需求" } ,notes = "根据系统用户产品获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/fetchprojectstories")
	public ResponseEntity<List<StoryDTO>> fetchProjectStoriesBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品指派", tags = {"需求" },  notes = "根据系统用户产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据系统用户产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchStoryRelatedBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品变更", tags = {"需求" },  notes = "根据系统用户产品变更")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户产品删除需求", tags = {"需求" },  notes = "根据系统用户产品删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户产品批量删除需求", tags = {"需求" },  notes = "根据系统用户产品批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProduct(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品建立需求", tags = {"需求" },  notes = "根据系统用户产品建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories")
    public ResponseEntity<StoryDTO> createBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品批量建立需求", tags = {"需求" },  notes = "根据系统用户产品批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/batch")
    public ResponseEntity<Boolean> createBatchBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
            domain.setProduct(product_id);
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户产品更新需求", tags = {"需求" },  notes = "根据系统用户产品更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品取消收藏", tags = {"需求" },  notes = "根据系统用户产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取我的数据", tags = {"需求" } ,notes = "根据系统用户产品获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/fetchmy")
	public ResponseEntity<List<StoryDTO>> fetchMyBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMy(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取DEFAULT", tags = {"需求" } ,notes = "根据系统用户产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchDefaultBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品评审", tags = {"需求" },  notes = "根据系统用户产品评审")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取我的收藏", tags = {"需求" } ,notes = "根据系统用户产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/fetchmyfavorites")
	public ResponseEntity<List<StoryDTO>> fetchMyFavoritesBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@StoryRuntime.quickTest('FAVORITES')")
    @ApiOperation(value = "根据系统用户项目需求收藏", tags = {"需求" },  notes = "根据系统用户项目需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取指定用户数据", tags = {"需求" } ,notes = "根据系统用户项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/fetchaccount")
	public ResponseEntity<List<StoryDTO>> fetchAccountBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchAccount(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取数据查询", tags = {"需求" } ,notes = "根据系统用户项目获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchParentDefaultBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('ACTIVATE')")
    @ApiOperation(value = "根据系统用户项目激活", tags = {"需求" },  notes = "根据系统用户项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('CLOSE')")
    @ApiOperation(value = "根据系统用户项目关闭", tags = {"需求" },  notes = "根据系统用户项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据系统用户项目获取需求草稿", tags = {"需求" },  notes = "根据系统用户项目获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目获取需求", tags = {"需求" },  notes = "根据系统用户项目获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取项目相关需求", tags = {"需求" } ,notes = "根据系统用户项目获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/fetchprojectstories")
	public ResponseEntity<List<StoryDTO>> fetchProjectStoriesBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('ASSIGNTO')")
    @ApiOperation(value = "根据系统用户项目指派", tags = {"需求" },  notes = "根据系统用户项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据系统用户项目获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchStoryRelatedBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('CHANGE')")
    @ApiOperation(value = "根据系统用户项目变更", tags = {"需求" },  notes = "根据系统用户项目变更")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户项目删除需求", tags = {"需求" },  notes = "根据系统用户项目删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("@StoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户项目批量删除需求", tags = {"需求" },  notes = "根据系统用户项目批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProject(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据系统用户项目建立需求", tags = {"需求" },  notes = "根据系统用户项目建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories")
    public ResponseEntity<StoryDTO> createBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据系统用户项目批量建立需求", tags = {"需求" },  notes = "根据系统用户项目批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/batch")
    public ResponseEntity<Boolean> createBatchBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
            
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@StoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户项目更新需求", tags = {"需求" },  notes = "根据系统用户项目更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> updateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
		storyService.update(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('NFAVORITES')")
    @ApiOperation(value = "根据系统用户项目取消收藏", tags = {"需求" },  notes = "根据系统用户项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取我的数据", tags = {"需求" } ,notes = "根据系统用户项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/fetchmy")
	public ResponseEntity<List<StoryDTO>> fetchMyBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMy(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取DEFAULT", tags = {"需求" } ,notes = "根据系统用户项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchDefaultBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.quickTest('REVIEW')")
    @ApiOperation(value = "根据系统用户项目评审", tags = {"需求" },  notes = "根据系统用户项目评审")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取我的收藏", tags = {"需求" } ,notes = "根据系统用户项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/fetchmyfavorites")
	public ResponseEntity<List<StoryDTO>> fetchMyFavoritesBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

