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
import cn.ibizlab.pms.core.zentao.domain.StorySpec;
import cn.ibizlab.pms.core.zentao.service.IStorySpecService;
import cn.ibizlab.pms.core.zentao.filter.StorySpecSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.StorySpecRuntime;

@Slf4j
@Api(tags = {"需求描述" })
@RestController("WebApi-storyspec")
@RequestMapping("")
public class StorySpecResource {

    @Autowired
    public IStorySpecService storyspecService;

    @Autowired
    public StorySpecRuntime storyspecRuntime;

    @Autowired
    @Lazy
    public StorySpecMapping storyspecMapping;

    @PreAuthorize("@StorySpecRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建需求描述", tags = {"需求描述" },  notes = "新建需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/storyspecs")
    @Transactional
    public ResponseEntity<StorySpecDTO> create(@Validated @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
		storyspecService.create(domain);
        if(!storyspecRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        StorySpecDTO dto = storyspecMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StorySpecRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建需求描述", tags = {"需求描述" },  notes = "批量新建需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/storyspecs/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<StorySpecDTO> storyspecdtos) {
        storyspecService.createBatch(storyspecMapping.toDomain(storyspecdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StorySpecRuntime.test(#storyspec_id,'UPDATE')")
    @ApiOperation(value = "更新需求描述", tags = {"需求描述" },  notes = "更新需求描述")
	@RequestMapping(method = RequestMethod.PUT, value = "/storyspecs/{storyspec_id}")
    @Transactional
    public ResponseEntity<StorySpecDTO> update(@PathVariable("storyspec_id") String storyspec_id, @RequestBody StorySpecDTO storyspecdto) {
		StorySpec domain  = storyspecMapping.toDomain(storyspecdto);
        domain.setId(storyspec_id);
		storyspecService.update(domain );
        if(!storyspecRuntime.test(storyspec_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		StorySpecDTO dto = storyspecMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StorySpecRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新需求描述", tags = {"需求描述" },  notes = "批量更新需求描述")
	@RequestMapping(method = RequestMethod.PUT, value = "/storyspecs/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<StorySpecDTO> storyspecdtos) {
        storyspecService.updateBatch(storyspecMapping.toDomain(storyspecdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StorySpecRuntime.test(#storyspec_id,'DELETE')")
    @ApiOperation(value = "删除需求描述", tags = {"需求描述" },  notes = "删除需求描述")
	@RequestMapping(method = RequestMethod.DELETE, value = "/storyspecs/{storyspec_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("storyspec_id") String storyspec_id) {
         return ResponseEntity.status(HttpStatus.OK).body(storyspecService.remove(storyspec_id));
    }

    @PreAuthorize("@StorySpecRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除需求描述", tags = {"需求描述" },  notes = "批量删除需求描述")
	@RequestMapping(method = RequestMethod.DELETE, value = "/storyspecs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        storyspecService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StorySpecRuntime.test(#storyspec_id,'READ')")
    @ApiOperation(value = "获取需求描述", tags = {"需求描述" },  notes = "获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/storyspecs/{storyspec_id}")
    public ResponseEntity<StorySpecDTO> get(@PathVariable("storyspec_id") String storyspec_id) {
        StorySpec domain = storyspecService.get(storyspec_id);
        StorySpecDTO dto = storyspecMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取需求描述草稿", tags = {"需求描述" },  notes = "获取需求描述草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/storyspecs/getdraft")
    public ResponseEntity<StorySpecDTO> getDraft(StorySpecDTO dto) {
        StorySpec domain = storyspecMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(storyspecMapping.toDto(storyspecService.getDraft(domain)));
    }

    @ApiOperation(value = "检查需求描述", tags = {"需求描述" },  notes = "检查需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/storyspecs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody StorySpecDTO storyspecdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyspecService.checkKey(storyspecMapping.toDomain(storyspecdto)));
    }

    @ApiOperation(value = "保存需求描述", tags = {"需求描述" },  notes = "保存需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/storyspecs/save")
    public ResponseEntity<StorySpecDTO> save(@RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
        storyspecService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storyspecMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存需求描述", tags = {"需求描述" },  notes = "批量保存需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/storyspecs/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<StorySpecDTO> storyspecdtos) {
        storyspecService.saveBatch(storyspecMapping.toDomain(storyspecdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StorySpecRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"需求描述" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/storyspecs/fetchdefault")
	public ResponseEntity<List<StorySpecDTO>> fetchDefault(StorySpecSearchContext context) {
        storyspecRuntime.addAuthorityConditions(context,"READ");
        Page<StorySpec> domains = storyspecService.searchDefault(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StorySpecRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"需求描述" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/storyspecs/searchdefault")
	public ResponseEntity<Page<StorySpecDTO>> searchDefault(@RequestBody StorySpecSearchContext context) {
        storyspecRuntime.addAuthorityConditions(context,"READ");
        Page<StorySpec> domains = storyspecService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyspecMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StorySpecRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本", tags = {"需求描述" } ,notes = "获取版本")
    @RequestMapping(method= RequestMethod.GET , value="/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchVersion(StorySpecSearchContext context) {
        storyspecRuntime.addAuthorityConditions(context,"READ");
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StorySpecRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本", tags = {"需求描述" } ,notes = "查询版本")
    @RequestMapping(method= RequestMethod.POST , value="/storyspecs/searchversion")
	public ResponseEntity<Page<StorySpecDTO>> searchVersion(@RequestBody StorySpecSearchContext context) {
        storyspecRuntime.addAuthorityConditions(context,"READ");
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyspecMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/storyspecs/{storyspec_id}/{action}")
    public ResponseEntity<StorySpecDTO> dynamicCall(@PathVariable("storyspec_id") String storyspec_id , @PathVariable("action") String action , @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecService.dynamicCall(storyspec_id, action, storyspecMapping.toDomain(storyspecdto));
        storyspecdto = storyspecMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storyspecdto);
    }
    @ApiOperation(value = "根据需求建立需求描述", tags = {"需求描述" },  notes = "根据需求建立需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/storyspecs")
    public ResponseEntity<StorySpecDTO> createByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
        domain.setStory(story_id);
		storyspecService.create(domain);
        StorySpecDTO dto = storyspecMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求批量建立需求描述", tags = {"需求描述" },  notes = "根据需求批量建立需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/storyspecs/batch")
    public ResponseEntity<Boolean> createBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<StorySpecDTO> storyspecdtos) {
        List<StorySpec> domainlist=storyspecMapping.toDomain(storyspecdtos);
        for(StorySpec domain:domainlist){
            domain.setStory(story_id);
        }
        storyspecService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求更新需求描述", tags = {"需求描述" },  notes = "根据需求更新需求描述")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/storyspecs/{storyspec_id}")
    public ResponseEntity<StorySpecDTO> updateByStory(@PathVariable("story_id") Long story_id, @PathVariable("storyspec_id") String storyspec_id, @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
        domain.setStory(story_id);
        domain.setId(storyspec_id);
		storyspecService.update(domain);
        StorySpecDTO dto = storyspecMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求批量更新需求描述", tags = {"需求描述" },  notes = "根据需求批量更新需求描述")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/storyspecs/batch")
    public ResponseEntity<Boolean> updateBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<StorySpecDTO> storyspecdtos) {
        List<StorySpec> domainlist=storyspecMapping.toDomain(storyspecdtos);
        for(StorySpec domain:domainlist){
            domain.setStory(story_id);
        }
        storyspecService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求删除需求描述", tags = {"需求描述" },  notes = "根据需求删除需求描述")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/storyspecs/{storyspec_id}")
    public ResponseEntity<Boolean> removeByStory(@PathVariable("story_id") Long story_id, @PathVariable("storyspec_id") String storyspec_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyspecService.remove(storyspec_id));
    }

    @ApiOperation(value = "根据需求批量删除需求描述", tags = {"需求描述" },  notes = "根据需求批量删除需求描述")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/storyspecs/batch")
    public ResponseEntity<Boolean> removeBatchByStory(@RequestBody List<String> ids) {
        storyspecService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求获取需求描述", tags = {"需求描述" },  notes = "根据需求获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/storyspecs/{storyspec_id}")
    public ResponseEntity<StorySpecDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("storyspec_id") String storyspec_id) {
        StorySpec domain = storyspecService.get(storyspec_id);
        StorySpecDTO dto = storyspecMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求获取需求描述草稿", tags = {"需求描述" },  notes = "根据需求获取需求描述草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/storyspecs/getdraft")
    public ResponseEntity<StorySpecDTO> getDraftByStory(@PathVariable("story_id") Long story_id, StorySpecDTO dto) {
        StorySpec domain = storyspecMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(storyspecMapping.toDto(storyspecService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求检查需求描述", tags = {"需求描述" },  notes = "根据需求检查需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/storyspecs/checkkey")
    public ResponseEntity<Boolean> checkKeyByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySpecDTO storyspecdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyspecService.checkKey(storyspecMapping.toDomain(storyspecdto)));
    }

    @ApiOperation(value = "根据需求保存需求描述", tags = {"需求描述" },  notes = "根据需求保存需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/storyspecs/save")
    public ResponseEntity<StorySpecDTO> saveByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
        domain.setStory(story_id);
        storyspecService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storyspecMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求批量保存需求描述", tags = {"需求描述" },  notes = "根据需求批量保存需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/storyspecs/savebatch")
    public ResponseEntity<Boolean> saveBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<StorySpecDTO> storyspecdtos) {
        List<StorySpec> domainlist=storyspecMapping.toDomain(storyspecdtos);
        for(StorySpec domain:domainlist){
             domain.setStory(story_id);
        }
        storyspecService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "根据需求获取DEFAULT", tags = {"需求描述" } ,notes = "根据需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/storyspecs/fetchdefault")
	public ResponseEntity<List<StorySpecDTO>> fetchStorySpecDefaultByStory(@PathVariable("story_id") Long story_id,StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchDefault(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询DEFAULT", tags = {"需求描述" } ,notes = "根据需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/storyspecs/searchdefault")
	public ResponseEntity<Page<StorySpecDTO>> searchStorySpecDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyspecMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本", tags = {"需求描述" } ,notes = "根据需求获取版本")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchStorySpecVersionByStory(@PathVariable("story_id") Long story_id,StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本", tags = {"需求描述" } ,notes = "根据需求查询版本")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/storyspecs/searchversion")
	public ResponseEntity<Page<StorySpecDTO>> searchStorySpecVersionByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyspecMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品需求建立需求描述", tags = {"需求描述" },  notes = "根据产品需求建立需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyspecs")
    public ResponseEntity<StorySpecDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
        domain.setStory(story_id);
		storyspecService.create(domain);
        StorySpecDTO dto = storyspecMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求批量建立需求描述", tags = {"需求描述" },  notes = "根据产品需求批量建立需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyspecs/batch")
    public ResponseEntity<Boolean> createBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<StorySpecDTO> storyspecdtos) {
        List<StorySpec> domainlist=storyspecMapping.toDomain(storyspecdtos);
        for(StorySpec domain:domainlist){
            domain.setStory(story_id);
        }
        storyspecService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求更新需求描述", tags = {"需求描述" },  notes = "根据产品需求更新需求描述")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/storyspecs/{storyspec_id}")
    public ResponseEntity<StorySpecDTO> updateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("storyspec_id") String storyspec_id, @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
        domain.setStory(story_id);
        domain.setId(storyspec_id);
		storyspecService.update(domain);
        StorySpecDTO dto = storyspecMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求批量更新需求描述", tags = {"需求描述" },  notes = "根据产品需求批量更新需求描述")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/storyspecs/batch")
    public ResponseEntity<Boolean> updateBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<StorySpecDTO> storyspecdtos) {
        List<StorySpec> domainlist=storyspecMapping.toDomain(storyspecdtos);
        for(StorySpec domain:domainlist){
            domain.setStory(story_id);
        }
        storyspecService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求删除需求描述", tags = {"需求描述" },  notes = "根据产品需求删除需求描述")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/storyspecs/{storyspec_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("storyspec_id") String storyspec_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyspecService.remove(storyspec_id));
    }

    @ApiOperation(value = "根据产品需求批量删除需求描述", tags = {"需求描述" },  notes = "根据产品需求批量删除需求描述")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/storyspecs/batch")
    public ResponseEntity<Boolean> removeBatchByProductStory(@RequestBody List<String> ids) {
        storyspecService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求获取需求描述", tags = {"需求描述" },  notes = "根据产品需求获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/storyspecs/{storyspec_id}")
    public ResponseEntity<StorySpecDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("storyspec_id") String storyspec_id) {
        StorySpec domain = storyspecService.get(storyspec_id);
        StorySpecDTO dto = storyspecMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求获取需求描述草稿", tags = {"需求描述" },  notes = "根据产品需求获取需求描述草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/storyspecs/getdraft")
    public ResponseEntity<StorySpecDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, StorySpecDTO dto) {
        StorySpec domain = storyspecMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(storyspecMapping.toDto(storyspecService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求检查需求描述", tags = {"需求描述" },  notes = "根据产品需求检查需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyspecs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySpecDTO storyspecdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyspecService.checkKey(storyspecMapping.toDomain(storyspecdto)));
    }

    @ApiOperation(value = "根据产品需求保存需求描述", tags = {"需求描述" },  notes = "根据产品需求保存需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyspecs/save")
    public ResponseEntity<StorySpecDTO> saveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySpecDTO storyspecdto) {
        StorySpec domain = storyspecMapping.toDomain(storyspecdto);
        domain.setStory(story_id);
        storyspecService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storyspecMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求批量保存需求描述", tags = {"需求描述" },  notes = "根据产品需求批量保存需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyspecs/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<StorySpecDTO> storyspecdtos) {
        List<StorySpec> domainlist=storyspecMapping.toDomain(storyspecdtos);
        for(StorySpec domain:domainlist){
             domain.setStory(story_id);
        }
        storyspecService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "根据产品需求获取DEFAULT", tags = {"需求描述" } ,notes = "根据产品需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/storyspecs/fetchdefault")
	public ResponseEntity<List<StorySpecDTO>> fetchStorySpecDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchDefault(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询DEFAULT", tags = {"需求描述" } ,notes = "根据产品需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/storyspecs/searchdefault")
	public ResponseEntity<Page<StorySpecDTO>> searchStorySpecDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyspecMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本", tags = {"需求描述" } ,notes = "根据产品需求获取版本")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchStorySpecVersionByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本", tags = {"需求描述" } ,notes = "根据产品需求查询版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/storyspecs/searchversion")
	public ResponseEntity<Page<StorySpecDTO>> searchStorySpecVersionByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyspecMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

