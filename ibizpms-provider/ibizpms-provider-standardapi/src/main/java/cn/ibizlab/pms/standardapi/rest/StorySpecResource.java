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
import cn.ibizlab.pms.core.zentao.domain.StorySpec;
import cn.ibizlab.pms.core.zentao.service.IStorySpecService;
import cn.ibizlab.pms.core.zentao.filter.StorySpecSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.StorySpecRuntime;

@Slf4j
@Api(tags = {"需求描述" })
@RestController("StandardAPI-storyspec")
@RequestMapping("")
public class StorySpecResource {

    @Autowired
    public IStorySpecService storyspecService;

    @Autowired
    public StorySpecRuntime storyspecRuntime;

    @Autowired
    @Lazy
    public StorySpecMapping storyspecMapping;



    @PreAuthorize("quickTest('ZT_STORYSPEC', 'READ')")
	@ApiOperation(value = "根据系统用户需求获取版本", tags = {"需求描述" } ,notes = "根据系统用户需求获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/{story_id}/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchVersionBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id,@RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_STORYSPEC', 'READ')")
	@ApiOperation(value = "根据产品需求获取版本", tags = {"需求描述" } ,notes = "根据产品需求获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchVersionByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_STORYSPEC', 'READ')")
	@ApiOperation(value = "根据项目需求获取版本", tags = {"需求描述" } ,notes = "根据项目需求获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchVersionByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_STORYSPEC', 'READ')")
	@ApiOperation(value = "根据系统用户产品需求获取版本", tags = {"需求描述" } ,notes = "根据系统用户产品需求获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchVersionBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_STORYSPEC', 'READ')")
	@ApiOperation(value = "根据系统用户项目需求获取版本", tags = {"需求描述" } ,notes = "根据系统用户项目需求获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/storyspecs/fetchversion")
	public ResponseEntity<List<StorySpecDTO>> fetchVersionBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody StorySpecSearchContext context) {
        context.setN_story_eq(story_id);
        Page<StorySpec> domains = storyspecService.searchVersion(context) ;
        List<StorySpecDTO> list = storyspecMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

