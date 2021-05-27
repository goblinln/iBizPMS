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
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.StoryRuntime;

@Slf4j
@Api(tags = {"需求" })
@RestController("WebApi-story")
@RequestMapping("")
public class StoryResource {

    @Autowired
    public IStoryService storyService;

    @Autowired
    public StoryRuntime storyRuntime;

    @Autowired
    @Lazy
    public StoryMapping storyMapping;

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"需求" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchmyfavorites")
	public ResponseEntity<List<StoryDTO>> fetchmyfavorites(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"需求" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchmyfavorites")
	public ResponseEntity<Page<StoryDTO>> searchMyFavorites(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'REVIEW')")
    @ApiOperation(value = "评审", tags = {"需求" },  notes = "评审")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> review(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.review(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


	@ApiOperation(value = "获取Bug相关需求", tags = {"需求" } ,notes = "获取Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchbugstory")
	public ResponseEntity<List<StoryDTO>> fetchbugstory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBugStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询Bug相关需求", tags = {"需求" } ,notes = "查询Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchbugstory")
	public ResponseEntity<Page<StoryDTO>> searchBugStory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBugStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取指派给我的需求", tags = {"需求" } ,notes = "获取指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchassignedtomystory")
	public ResponseEntity<List<StoryDTO>> fetchassignedtomystory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指派给我的需求", tags = {"需求" } ,notes = "查询指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchassignedtomystory")
	public ResponseEntity<Page<StoryDTO>> searchAssignedToMyStory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'FAVORITES')")
    @ApiOperation(value = "需求收藏", tags = {"需求" },  notes = "需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavorites(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取相关用例需求", tags = {"需求" } ,notes = "获取相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchcasestory")
	public ResponseEntity<List<StoryDTO>> fetchcasestory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchCaseStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询相关用例需求", tags = {"需求" } ,notes = "查询相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchcasestory")
	public ResponseEntity<Page<StoryDTO>> searchCaseStory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchCaseStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本可关联的需求（产品内）", tags = {"需求" } ,notes = "获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchbuildlinkablestories")
	public ResponseEntity<List<StoryDTO>> fetchbuildlinkablestories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本可关联的需求（产品内）", tags = {"需求" } ,notes = "查询版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchbuildlinkablestories")
	public ResponseEntity<Page<StoryDTO>> searchBuildLinkableStories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取子需求（更多）", tags = {"需求" } ,notes = "获取子需求（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchchildmore")
	public ResponseEntity<List<StoryDTO>> fetchchildmore(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchChildMore(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询子需求（更多）", tags = {"需求" } ,notes = "查询子需求（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchchildmore")
	public ResponseEntity<Page<StoryDTO>> searchChildMore(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchChildMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取获取产品发布相关需求", tags = {"需求" } ,notes = "获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchreleasestories")
	public ResponseEntity<List<StoryDTO>> fetchreleasestories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchReleaseStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询获取产品发布相关需求", tags = {"需求" } ,notes = "查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchreleasestories")
	public ResponseEntity<Page<StoryDTO>> searchReleaseStories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchReleaseStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'ACTIVATE')")
    @ApiOperation(value = "激活", tags = {"需求" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activate(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.activate(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'CLOSE')")
    @ApiOperation(value = "关闭", tags = {"需求" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> close(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.close(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建需求", tags = {"需求" },  notes = "新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories")
    @Transactional
    public ResponseEntity<StoryDTO> create(@Validated @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
		storyService.create(domain);
        if(!storyRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建需求", tags = {"需求" },  notes = "批量新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<StoryDTO> storydtos) {
        storyService.createBatch(storyMapping.toDomain(storydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取获取产品发布相关需求", tags = {"需求" } ,notes = "获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchstoryrelated(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询获取产品发布相关需求", tags = {"需求" } ,notes = "查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchstoryrelated")
	public ResponseEntity<Page<StoryDTO>> searchStoryRelated(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchStoryRelated(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取需求草稿", tags = {"需求" },  notes = "获取需求草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraft(StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<StoryDTO>> fetchbuildlinkcompletedstories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "查询版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchbuildlinkcompletedstories")
	public ResponseEntity<Page<StoryDTO>> searchBuildLinkCompletedStories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "更新需求", tags = {"需求" },  notes = "更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}")
    @Transactional
    public ResponseEntity<StoryDTO> update(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
		Story domain  = storyMapping.toDomain(storydto);
        domain.setId(story_id);
		storyService.update(domain );
        if(!storyRuntime.test(story_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		StoryDTO dto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(story_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取通过模块查询", tags = {"需求" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchbymodule")
	public ResponseEntity<List<StoryDTO>> fetchbymodule(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchByModule(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询通过模块查询", tags = {"需求" } ,notes = "查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchbymodule")
	public ResponseEntity<Page<StoryDTO>> searchByModule(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "删除需求", tags = {"需求" },  notes = "删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("story_id") Long story_id) {
         return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'NFAVORITES')")
    @ApiOperation(value = "取消收藏", tags = {"需求" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavorites(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取指派给我的需求（日历）", tags = {"需求" } ,notes = "获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchassignedtomystorycalendar")
	public ResponseEntity<List<StoryDTO>> fetchassignedtomystorycalendar(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指派给我的需求（日历）", tags = {"需求" } ,notes = "查询指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchassignedtomystorycalendar")
	public ResponseEntity<Page<StoryDTO>> searchAssignedToMyStoryCalendar(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'CHANGE')")
    @ApiOperation(value = "变更", tags = {"需求" },  notes = "变更")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> change(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.change(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "获取需求", tags = {"需求" },  notes = "获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}")
    public ResponseEntity<StoryDTO> get(@PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(story_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'ASSIGNTO')")
    @ApiOperation(value = "指派", tags = {"需求" },  notes = "指派")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignTo(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.assignTo(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据查询", tags = {"需求" } ,notes = "获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchparentdefault(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据查询", tags = {"需求" } ,notes = "查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchparentdefault")
	public ResponseEntity<Page<StoryDTO>> searchParentDefault(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchParentDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目关联需求", tags = {"需求" } ,notes = "获取项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchprojectlinkstory")
	public ResponseEntity<List<StoryDTO>> fetchprojectlinkstory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目关联需求", tags = {"需求" } ,notes = "查询项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchprojectlinkstory")
	public ResponseEntity<Page<StoryDTO>> searchProjectLinkStory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取获取版本相关需求", tags = {"需求" } ,notes = "获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchbuildstories")
	public ResponseEntity<List<StoryDTO>> fetchbuildstories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBuildStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询获取版本相关需求", tags = {"需求" } ,notes = "查询获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchbuildstories")
	public ResponseEntity<Page<StoryDTO>> searchBuildStories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchBuildStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"需求" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchdefault(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"需求" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/searchdefault")
	public ResponseEntity<Page<StoryDTO>> searchDefault(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/{action}")
    public ResponseEntity<StoryDTO> dynamicCall(@PathVariable("story_id") Long story_id , @PathVariable("action") String action , @RequestBody StoryDTO storydto) {
        Story domain = storyService.dynamicCall(story_id, action, storyMapping.toDomain(storydto));
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"需求" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchmyfavorites")
	public ResponseEntity<List<StoryDTO>> fetchStoryMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询我的收藏", tags = {"需求" } ,notes = "根据产品查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchmyfavorites")
	public ResponseEntity<Page<StoryDTO>> searchStoryMyFavoritesByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品评审需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/review")
    public ResponseEntity<StoryDTO> reviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.review(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

	@ApiOperation(value = "根据产品获取Bug相关需求", tags = {"需求" } ,notes = "根据产品获取Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbugstory")
	public ResponseEntity<List<StoryDTO>> fetchStoryBugStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBugStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询Bug相关需求", tags = {"需求" } ,notes = "根据产品查询Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchbugstory")
	public ResponseEntity<Page<StoryDTO>> searchStoryBugStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBugStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求", tags = {"需求" } ,notes = "根据产品获取指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchassignedtomystory")
	public ResponseEntity<List<StoryDTO>> fetchStoryAssignedToMyStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询指派给我的需求", tags = {"需求" } ,notes = "根据产品查询指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchassignedtomystory")
	public ResponseEntity<Page<StoryDTO>> searchStoryAssignedToMyStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品需求收藏需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storyfavorites")
    public ResponseEntity<StoryDTO> storyFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取相关用例需求", tags = {"需求" } ,notes = "根据产品获取相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchcasestory")
	public ResponseEntity<List<StoryDTO>> fetchStoryCaseStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchCaseStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询相关用例需求", tags = {"需求" } ,notes = "根据产品查询相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchcasestory")
	public ResponseEntity<Page<StoryDTO>> searchStoryCaseStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchCaseStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的需求（产品内）", tags = {"需求" } ,notes = "根据产品获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbuildlinkablestories")
	public ResponseEntity<List<StoryDTO>> fetchStoryBuildLinkableStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询版本可关联的需求（产品内）", tags = {"需求" } ,notes = "根据产品查询版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchbuildlinkablestories")
	public ResponseEntity<Page<StoryDTO>> searchStoryBuildLinkableStoriesByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取子需求（更多）", tags = {"需求" } ,notes = "根据产品获取子需求（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchchildmore")
	public ResponseEntity<List<StoryDTO>> fetchStoryChildMoreByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchChildMore(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询子需求（更多）", tags = {"需求" } ,notes = "根据产品查询子需求（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchchildmore")
	public ResponseEntity<Page<StoryDTO>> searchStoryChildMoreByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchChildMore(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchreleasestories")
	public ResponseEntity<List<StoryDTO>> fetchStoryReleaseStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchReleaseStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchreleasestories")
	public ResponseEntity<Page<StoryDTO>> searchStoryReleaseStoriesByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchReleaseStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品激活需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/activate")
    public ResponseEntity<StoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.activate(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品关闭需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/close")
    public ResponseEntity<StoryDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.close(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立需求", tags = {"需求" },  notes = "根据产品建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories")
    public ResponseEntity<StoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchstoryrelated")
	public ResponseEntity<List<StoryDTO>> fetchStoryStoryRelatedByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchstoryrelated")
	public ResponseEntity<Page<StoryDTO>> searchStoryStoryRelatedByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取需求草稿", tags = {"需求" },  notes = "根据产品获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "根据产品获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<StoryDTO>> fetchStoryBuildLinkCompletedStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "根据产品查询版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchbuildlinkcompletedstories")
	public ResponseEntity<Page<StoryDTO>> searchStoryBuildLinkCompletedStoriesByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取通过模块查询", tags = {"需求" } ,notes = "根据产品获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbymodule")
	public ResponseEntity<List<StoryDTO>> fetchStoryByModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchByModule(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询通过模块查询", tags = {"需求" } ,notes = "根据产品查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchbymodule")
	public ResponseEntity<Page<StoryDTO>> searchStoryByModuleByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除需求", tags = {"需求" },  notes = "根据产品删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品取消收藏需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/storynfavorites")
    public ResponseEntity<StoryDTO> storyNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.storyNFavorites(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求（日历）", tags = {"需求" } ,notes = "根据产品获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchassignedtomystorycalendar")
	public ResponseEntity<List<StoryDTO>> fetchStoryAssignedToMyStoryCalendarByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询指派给我的需求（日历）", tags = {"需求" } ,notes = "根据产品查询指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchassignedtomystorycalendar")
	public ResponseEntity<Page<StoryDTO>> searchStoryAssignedToMyStoryCalendarByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品变更需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/change")
    public ResponseEntity<StoryDTO> changeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.change(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取需求", tags = {"需求" },  notes = "根据产品获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品指派需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/assignto")
    public ResponseEntity<StoryDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.assignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据查询", tags = {"需求" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchparentdefault")
	public ResponseEntity<List<StoryDTO>> fetchStoryParentDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询数据查询", tags = {"需求" } ,notes = "根据产品查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchparentdefault")
	public ResponseEntity<Page<StoryDTO>> searchStoryParentDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目关联需求", tags = {"需求" } ,notes = "根据产品获取项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchprojectlinkstory")
	public ResponseEntity<List<StoryDTO>> fetchStoryProjectLinkStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目关联需求", tags = {"需求" } ,notes = "根据产品查询项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchprojectlinkstory")
	public ResponseEntity<Page<StoryDTO>> searchStoryProjectLinkStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取版本相关需求", tags = {"需求" } ,notes = "根据产品获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbuildstories")
	public ResponseEntity<List<StoryDTO>> fetchStoryBuildStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询获取版本相关需求", tags = {"需求" } ,notes = "根据产品查询获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchbuildstories")
	public ResponseEntity<Page<StoryDTO>> searchStoryBuildStoriesByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"需求" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchdefault")
	public ResponseEntity<List<StoryDTO>> fetchStoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchDefault(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"需求" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/searchdefault")
	public ResponseEntity<Page<StoryDTO>> searchStoryDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(storyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

