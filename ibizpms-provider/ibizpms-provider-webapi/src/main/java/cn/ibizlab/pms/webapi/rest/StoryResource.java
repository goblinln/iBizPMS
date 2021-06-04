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

    @PreAuthorize("quickTest('ZT_STORY', 'CREATE')")
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

    @PreAuthorize("quickTest('ZT_STORY', 'CREATE')")
    @ApiOperation(value = "批量新建需求", tags = {"需求" },  notes = "批量新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<StoryDTO> storydtos) {
        storyService.createBatch(storyMapping.toDomain(storydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_STORY', #story_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_STORY', #story_id, 'DELETE')")
    @ApiOperation(value = "删除需求", tags = {"需求" },  notes = "删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("story_id") Long story_id) {
         return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("quickTest('ZT_STORY', 'DELETE')")
    @ApiOperation(value = "批量删除需求", tags = {"需求" },  notes = "批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_STORY', #story_id, 'READ')")
    @ApiOperation(value = "获取需求", tags = {"需求" },  notes = "获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}")
    public ResponseEntity<StoryDTO> get(@PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(story_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'CREATE')")
    @ApiOperation(value = "获取需求草稿", tags = {"需求" },  notes = "获取需求草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraft(StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_STORY', #story_id, 'ACTIVATE')")
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


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "全部推送", tags = {"需求" },  notes = "全部推送")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/allpush")
    public ResponseEntity<StoryDTO> allPush(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.allPush(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'ASSIGNTO')")
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


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "批量指派", tags = {"需求" },  notes = "批量指派")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchassignto")
    public ResponseEntity<StoryDTO> batchAssignTo(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchAssignTo(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "批量变更平台/分支", tags = {"需求" },  notes = "批量变更平台/分支")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchchangebranch")
    public ResponseEntity<StoryDTO> batchChangeBranch(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchChangeBranch(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "批量变更模块", tags = {"需求" },  notes = "批量变更模块")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchchangemodule")
    public ResponseEntity<StoryDTO> batchChangeModule(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchChangeModule(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "批量关联计划", tags = {"需求" },  notes = "批量关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchchangeplan")
    public ResponseEntity<StoryDTO> batchChangePlan(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchChangePlan(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "批量变更阶段", tags = {"需求" },  notes = "批量变更阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchchangestage")
    public ResponseEntity<StoryDTO> batchChangeStage(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchChangeStage(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "批量关闭", tags = {"需求" },  notes = "批量关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchclose")
    public ResponseEntity<StoryDTO> batchClose(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchClose(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "批量评审", tags = {"需求" },  notes = "批量评审")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchreview")
    public ResponseEntity<StoryDTO> batchReview(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchReview(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "计划批量解除关联需求", tags = {"需求" },  notes = "计划批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/batchunlinkstory")
    public ResponseEntity<StoryDTO> batchUnlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.batchUnlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'CREATE')")
    @ApiOperation(value = "bug转需求", tags = {"需求" },  notes = "bug转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugtostory")
    public ResponseEntity<StoryDTO> bugToStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.bugToStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "版本批量解除关联需求", tags = {"需求" },  notes = "版本批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/buildbatchunlinkstory")
    public ResponseEntity<StoryDTO> buildBatchUnlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.buildBatchUnlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'BUILDLINK')")
    @ApiOperation(value = "项目关联需求", tags = {"需求" },  notes = "项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/buildlinkstory")
    public ResponseEntity<StoryDTO> buildLinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.buildLinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'BUILDLINK')")
    @ApiOperation(value = "版本解除关联需求", tags = {"需求" },  notes = "版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/buildunlinkstory")
    public ResponseEntity<StoryDTO> buildUnlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.buildUnlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "版本解除关联需求", tags = {"需求" },  notes = "版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/buildunlinkstorys")
    public ResponseEntity<StoryDTO> buildUnlinkStorys(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.buildUnlinkStorys(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'CHANGE')")
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


    @PreAuthorize("quickTest('ZT_STORY', 'CREATE')")
    @ApiOperation(value = "检查需求", tags = {"需求" },  notes = "检查需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody StoryDTO storydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyService.checkKey(storyMapping.toDomain(storydto)));
    }

    @PreAuthorize("test('ZT_STORY', #story_id, 'CLOSE')")
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


    @PreAuthorize("test('ZT_STORY', #story_id, 'READ')")
    @ApiOperation(value = "生成任务", tags = {"需求" },  notes = "生成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/createtasks")
    public ResponseEntity<StoryDTO> createTasks(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.createTasks(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'READ')")
    @ApiOperation(value = "获取需求描述", tags = {"需求" },  notes = "获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/getstoryspec")
    public ResponseEntity<StoryDTO> getStorySpec(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.getStorySpec(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'READ')")
    @ApiOperation(value = "获取需求描述", tags = {"需求" },  notes = "获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/getstoryspecs")
    public ResponseEntity<StoryDTO> getStorySpecs(@PathVariable("story_id") Long story_id, StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.getStorySpecs(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "项目关联需求-按计划关联", tags = {"需求" },  notes = "项目关联需求-按计划关联")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/importplanstories")
    public ResponseEntity<StoryDTO> importPlanStories(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.importPlanStories(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'PLANLINK')")
    @ApiOperation(value = "计划关联需求", tags = {"需求" },  notes = "计划关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/linkstory")
    public ResponseEntity<StoryDTO> linkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.linkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "项目批量解除关联需求", tags = {"需求" },  notes = "项目批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/projectbatchunlinkstory")
    public ResponseEntity<StoryDTO> projectBatchUnlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.projectBatchUnlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'PROJECTLINK')")
    @ApiOperation(value = "项目关联需求", tags = {"需求" },  notes = "项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/projectlinkstory")
    public ResponseEntity<StoryDTO> projectLinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.projectLinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'PROJECTLINK')")
    @ApiOperation(value = "项目解除关联需求", tags = {"需求" },  notes = "项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/projectunlinkstory")
    public ResponseEntity<StoryDTO> projectUnlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.projectUnlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "项目解除关联需求", tags = {"需求" },  notes = "项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/projectunlinkstorys")
    public ResponseEntity<StoryDTO> projectUnlinkStorys(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.projectUnlinkStorys(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "推送", tags = {"需求" },  notes = "推送")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/push")
    public ResponseEntity<StoryDTO> push(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.push(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "发布批量解除关联需求", tags = {"需求" },  notes = "发布批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/releasebatchunlinkstory")
    public ResponseEntity<StoryDTO> releaseBatchUnlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.releaseBatchUnlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'RELEASELINK')")
    @ApiOperation(value = "发布关联需求", tags = {"需求" },  notes = "发布关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/releaselinkstory")
    public ResponseEntity<StoryDTO> releaseLinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.releaseLinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'RELEASELINK')")
    @ApiOperation(value = "发布解除关联需求", tags = {"需求" },  notes = "发布解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/releaseunlinkstory")
    public ResponseEntity<StoryDTO> releaseUnlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.releaseUnlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "重置由谁评审", tags = {"需求" },  notes = "重置由谁评审")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/resetreviewedby")
    public ResponseEntity<StoryDTO> resetReviewedBy(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.resetReviewedBy(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'REVIEW')")
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


    @PreAuthorize("quickTest('ZT_STORY', 'CREATE')")
    @ApiOperation(value = "保存需求", tags = {"需求" },  notes = "保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/save")
    public ResponseEntity<StoryDTO> save(@RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        storyService.save(domain);
        StoryDTO dto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'CREATE')")
    @ApiOperation(value = "批量保存需求", tags = {"需求" },  notes = "批量保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<StoryDTO> storydtos) {
        storyService.saveBatch(storyMapping.toDomain(storydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "行为", tags = {"需求" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/sendmessage")
    public ResponseEntity<StoryDTO> sendMessage(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.sendMessage(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'MANAGE')")
    @ApiOperation(value = "发送消息前置处理", tags = {"需求" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/sendmsgpreprocess")
    public ResponseEntity<StoryDTO> sendMsgPreProcess(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.sendMsgPreProcess(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("quickTest('ZT_STORY', 'DENY')")
    @ApiOperation(value = "设置需求阶段", tags = {"需求" },  notes = "设置需求阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/setstage")
    public ResponseEntity<StoryDTO> setStage(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.setStage(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'FAVORITES')")
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


    @PreAuthorize("test('ZT_STORY', #story_id, 'NFAVORITES')")
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


    @PreAuthorize("quickTest('ZT_STORY', 'DENY')")
    @ApiOperation(value = "同步Ibz平台实体", tags = {"需求" },  notes = "同步Ibz平台实体")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/syncfromibiz")
    public ResponseEntity<StoryDTO> syncFromIbiz(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.syncFromIbiz(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("test('ZT_STORY', #story_id, 'PLANLINK')")
    @ApiOperation(value = "计划解除关联需求", tags = {"需求" },  notes = "计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/unlinkstory")
    public ResponseEntity<StoryDTO> unlinkStory(@PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setId(story_id);
        domain = storyService.unlinkStory(domain);
        storydto = storyMapping.toDto(domain);
        Map<String,Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());
        storydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }


    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"需求" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchaccount")
	public ResponseEntity<List<StoryDTO>> fetchaccount(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchAccount(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'NONE')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"需求" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchesbulk")
	public ResponseEntity<List<StoryDTO>> fetchesbulk(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchESBulk(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取获取产品需求", tags = {"需求" } ,notes = "获取获取产品需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchgetproductstories")
	public ResponseEntity<List<StoryDTO>> fetchgetproductstories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchGetProductStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"需求" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchmy")
	public ResponseEntity<List<StoryDTO>> fetchmy(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchMy(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取我代理的需求", tags = {"需求" } ,notes = "获取我代理的需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchmyagentstory")
	public ResponseEntity<List<StoryDTO>> fetchmyagentstory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "获取所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchmycuropenedstory")
	public ResponseEntity<List<StoryDTO>> fetchmycuropenedstory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取计划关联需求(去除已关联)", tags = {"需求" } ,notes = "获取计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchnotcurplanlinkstory")
	public ResponseEntity<List<StoryDTO>> fetchnotcurplanlinkstory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取数据查询", tags = {"需求" } ,notes = "获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchparentdefaultq")
	public ResponseEntity<List<StoryDTO>> fetchparentdefaultq(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取项目相关需求", tags = {"需求" } ,notes = "获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchprojectstories")
	public ResponseEntity<List<StoryDTO>> fetchprojectstories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取版本可关联的完成的需求", tags = {"需求" } ,notes = "获取版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchreleaselinkablestories")
	public ResponseEntity<List<StoryDTO>> fetchreleaselinkablestories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取通过模块查询", tags = {"需求" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchreportstories")
	public ResponseEntity<List<StoryDTO>> fetchreportstories(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchReportStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取获取产品发布相关需求", tags = {"需求" } ,notes = "获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchstorychild")
	public ResponseEntity<List<StoryDTO>> fetchstorychild(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchStoryChild(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
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
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取需求细分", tags = {"需求" } ,notes = "获取需求细分")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchsubstory")
	public ResponseEntity<List<StoryDTO>> fetchsubstory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchSubStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取任务相关需求", tags = {"需求" } ,notes = "获取任务相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchtaskrelatedstory")
	public ResponseEntity<List<StoryDTO>> fetchtaskrelatedstory(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取默认（全部数据）", tags = {"需求" } ,notes = "获取默认（全部数据）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/fetchview")
	public ResponseEntity<List<StoryDTO>> fetchview(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchView(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/{action}")
    public ResponseEntity<StoryDTO> dynamicCall(@PathVariable("story_id") Long story_id , @PathVariable("action") String action , @RequestBody StoryDTO storydto) {
        Story domain = storyService.dynamicCall(story_id, action, storyMapping.toDomain(storydto));
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立需求", tags = {"需求" },  notes = "根据产品建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories")
    public ResponseEntity<StoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
		storyService.create(domain);
        StoryDTO dto = storyMapping.toDto(domain);
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

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'UPDATE', #story_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'DELETE', #story_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除需求", tags = {"需求" },  notes = "根据产品删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(story_id));
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除需求", tags = {"需求" },  notes = "根据产品批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品获取需求", tags = {"需求" },  notes = "根据产品获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}")
    public ResponseEntity<StoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id) {
        Story domain = storyService.get(story_id);
        StoryDTO dto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取需求草稿", tags = {"需求" },  notes = "根据产品获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/getdraft")
    public ResponseEntity<StoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, StoryDTO dto) {
        Story domain = storyMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(storyService.getDraft(domain)));
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品全部推送", tags = {"需求" },  notes = "根据产品全部推送")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/allpush")
    public ResponseEntity<StoryDTO> allPushByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.allPush(domain) ;
        storydto = storyMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品批量指派", tags = {"需求" },  notes = "根据产品批量指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchassignto")
    public ResponseEntity<StoryDTO> batchAssignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchAssignTo(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品批量变更平台/分支", tags = {"需求" },  notes = "根据产品批量变更平台/分支")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchchangebranch")
    public ResponseEntity<StoryDTO> batchChangeBranchByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchChangeBranch(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品批量变更模块", tags = {"需求" },  notes = "根据产品批量变更模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchchangemodule")
    public ResponseEntity<StoryDTO> batchChangeModuleByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchChangeModule(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品批量关联计划", tags = {"需求" },  notes = "根据产品批量关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchchangeplan")
    public ResponseEntity<StoryDTO> batchChangePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchChangePlan(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品批量变更阶段", tags = {"需求" },  notes = "根据产品批量变更阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchchangestage")
    public ResponseEntity<StoryDTO> batchChangeStageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchChangeStage(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品批量关闭", tags = {"需求" },  notes = "根据产品批量关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchclose")
    public ResponseEntity<StoryDTO> batchCloseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchClose(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品批量评审", tags = {"需求" },  notes = "根据产品批量评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchreview")
    public ResponseEntity<StoryDTO> batchReviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchReview(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品计划批量解除关联需求", tags = {"需求" },  notes = "根据产品计划批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/batchunlinkstory")
    public ResponseEntity<StoryDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.batchUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', #story_id, 'CREATE')")
    @ApiOperation(value = "根据产品bug转需求", tags = {"需求" },  notes = "根据产品bug转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugtostory")
    public ResponseEntity<StoryDTO> bugToStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.bugToStory(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品版本批量解除关联需求", tags = {"需求" },  notes = "根据产品版本批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/buildbatchunlinkstory")
    public ResponseEntity<StoryDTO> buildBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.buildBatchUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'BUILDLINK')")
    @ApiOperation(value = "根据产品项目关联需求", tags = {"需求" },  notes = "根据产品项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/buildlinkstory")
    public ResponseEntity<StoryDTO> buildLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.buildLinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品版本解除关联需求", tags = {"需求" },  notes = "根据产品版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/buildunlinkstorys")
    public ResponseEntity<StoryDTO> buildUnlinkStorysByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.buildUnlinkStorys(domain) ;
        storydto = storyMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品检查需求", tags = {"需求" },  notes = "根据产品检查需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody StoryDTO storydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyService.checkKey(storyMapping.toDomain(storydto)));
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品生成任务", tags = {"需求" },  notes = "根据产品生成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/createtasks")
    public ResponseEntity<StoryDTO> createTasksByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.createTasks(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品获取需求描述", tags = {"需求" },  notes = "根据产品获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/getstoryspec")
    public ResponseEntity<StoryDTO> getStorySpecByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.getStorySpec(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', #story_id, 'READ')")
    @ApiOperation(value = "根据产品获取需求描述", tags = {"需求" },  notes = "根据产品获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/getstoryspecs")
    public ResponseEntity<StoryDTO> getStorySpecsByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.getStorySpecs(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品项目关联需求-按计划关联", tags = {"需求" },  notes = "根据产品项目关联需求-按计划关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/importplanstories")
    public ResponseEntity<StoryDTO> importPlanStoriesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.importPlanStories(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'PLANLINK')")
    @ApiOperation(value = "根据产品计划关联需求", tags = {"需求" },  notes = "根据产品计划关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/linkstory")
    public ResponseEntity<StoryDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.linkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品项目批量解除关联需求", tags = {"需求" },  notes = "根据产品项目批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/projectbatchunlinkstory")
    public ResponseEntity<StoryDTO> projectBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.projectBatchUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'PROJECTLINK')")
    @ApiOperation(value = "根据产品项目关联需求", tags = {"需求" },  notes = "根据产品项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/projectlinkstory")
    public ResponseEntity<StoryDTO> projectLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.projectLinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品项目解除关联需求", tags = {"需求" },  notes = "根据产品项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/projectunlinkstorys")
    public ResponseEntity<StoryDTO> projectUnlinkStorysByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.projectUnlinkStorys(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品推送", tags = {"需求" },  notes = "根据产品推送")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/push")
    public ResponseEntity<StoryDTO> pushByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.push(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品发布批量解除关联需求", tags = {"需求" },  notes = "根据产品发布批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/releasebatchunlinkstory")
    public ResponseEntity<StoryDTO> releaseBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.releaseBatchUnlinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'RELEASELINK')")
    @ApiOperation(value = "根据产品发布关联需求", tags = {"需求" },  notes = "根据产品发布关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/releaselinkstory")
    public ResponseEntity<StoryDTO> releaseLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.releaseLinkStory(domain) ;
        storydto = storyMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品重置由谁评审", tags = {"需求" },  notes = "根据产品重置由谁评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/resetreviewedby")
    public ResponseEntity<StoryDTO> resetReviewedByByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.resetReviewedBy(domain) ;
        storydto = storyMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', #story_id, 'CREATE')")
    @ApiOperation(value = "根据产品保存需求", tags = {"需求" },  notes = "根据产品保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/save")
    public ResponseEntity<StoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        storyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storyMapping.toDto(domain));
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品批量保存需求", tags = {"需求" },  notes = "根据产品批量保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<StoryDTO> storydtos) {
        List<Story> domainlist=storyMapping.toDomain(storydtos);
        for(Story domain:domainlist){
             domain.setProduct(product_id);
        }
        storyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品行为", tags = {"需求" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/sendmessage")
    public ResponseEntity<StoryDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.sendMessage(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'MANAGE')")
    @ApiOperation(value = "根据产品发送消息前置处理", tags = {"需求" },  notes = "根据产品发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/sendmsgpreprocess")
    public ResponseEntity<StoryDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.sendMsgPreProcess(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'DENY')")
    @ApiOperation(value = "根据产品设置需求阶段", tags = {"需求" },  notes = "根据产品设置需求阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/setstage")
    public ResponseEntity<StoryDTO> setStageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.setStage(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'FAVORITES')")
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

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'MANAGE', #story_id, 'NFAVORITES')")
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

    @PreAuthorize("quickTest('ZT_STORY', 'DENY')")
    @ApiOperation(value = "根据产品同步Ibz平台实体", tags = {"需求" },  notes = "根据产品同步Ibz平台实体")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/syncfromibiz")
    public ResponseEntity<StoryDTO> syncFromIbizByProduct(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StoryDTO storydto) {
        Story domain = storyMapping.toDomain(storydto);
        domain.setProduct(product_id);
        domain.setId(story_id);
        domain = storyService.syncFromIbiz(domain) ;
        storydto = storyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
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
        return ResponseEntity.status(HttpStatus.OK).body(storydto);
    }

    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
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
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求", tags = {"需求" } ,notes = "根据产品获取指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchassignedtomystory")
	public ResponseEntity<List<StoryDTO>> fetchAssignedToMyStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求（日历）", tags = {"需求" } ,notes = "根据产品获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchassignedtomystorycalendar")
	public ResponseEntity<List<StoryDTO>> fetchAssignedToMyStoryCalendarByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'NONE')")
	@ApiOperation(value = "根据产品获取Bug相关需求", tags = {"需求" } ,notes = "根据产品获取Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbugstory")
	public ResponseEntity<List<StoryDTO>> fetchBugStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBugStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "根据产品获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<StoryDTO>> fetchBuildLinkCompletedStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的需求（产品内）", tags = {"需求" } ,notes = "根据产品获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbuildlinkablestories")
	public ResponseEntity<List<StoryDTO>> fetchBuildLinkableStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取获取版本相关需求", tags = {"需求" } ,notes = "根据产品获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbuildstories")
	public ResponseEntity<List<StoryDTO>> fetchBuildStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchBuildStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取通过模块查询", tags = {"需求" } ,notes = "根据产品获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchbymodule")
	public ResponseEntity<List<StoryDTO>> fetchByModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchByModule(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取相关用例需求", tags = {"需求" } ,notes = "根据产品获取相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchcasestory")
	public ResponseEntity<List<StoryDTO>> fetchCaseStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchCaseStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取子需求（更多）", tags = {"需求" } ,notes = "根据产品获取子需求（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchchildmore")
	public ResponseEntity<List<StoryDTO>> fetchChildMoreByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchChildMore(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"需求" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchesbulk")
	public ResponseEntity<List<StoryDTO>> fetchESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchESBulk(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取获取产品需求", tags = {"需求" } ,notes = "根据产品获取获取产品需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchgetproductstories")
	public ResponseEntity<List<StoryDTO>> fetchGetProductStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchGetProductStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
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
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取我代理的需求", tags = {"需求" } ,notes = "根据产品获取我代理的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchmyagentstory")
	public ResponseEntity<List<StoryDTO>> fetchMyAgentStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "根据产品获取所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchmycuropenedstory")
	public ResponseEntity<List<StoryDTO>> fetchMyCurOpenedStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
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
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取计划关联需求(去除已关联)", tags = {"需求" } ,notes = "根据产品获取计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchnotcurplanlinkstory")
	public ResponseEntity<List<StoryDTO>> fetchNotCurPlanLinkStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
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
	@ApiOperation(value = "根据产品获取数据查询", tags = {"需求" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchparentdefaultq")
	public ResponseEntity<List<StoryDTO>> fetchParentDefaultQByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目关联需求", tags = {"需求" } ,notes = "根据产品获取项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchprojectlinkstory")
	public ResponseEntity<List<StoryDTO>> fetchProjectLinkStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
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
	@ApiOperation(value = "根据产品获取版本可关联的完成的需求", tags = {"需求" } ,notes = "根据产品获取版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchreleaselinkablestories")
	public ResponseEntity<List<StoryDTO>> fetchReleaseLinkableStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchreleasestories")
	public ResponseEntity<List<StoryDTO>> fetchReleaseStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchReleaseStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取通过模块查询", tags = {"需求" } ,notes = "根据产品获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchreportstories")
	public ResponseEntity<List<StoryDTO>> fetchReportStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchReportStories(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchstorychild")
	public ResponseEntity<List<StoryDTO>> fetchStoryChildByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchStoryChild(context) ;
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
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取需求细分", tags = {"需求" } ,notes = "根据产品获取需求细分")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchsubstory")
	public ResponseEntity<List<StoryDTO>> fetchSubStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchSubStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取任务相关需求", tags = {"需求" } ,notes = "根据产品获取任务相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchtaskrelatedstory")
	public ResponseEntity<List<StoryDTO>> fetchTaskRelatedStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_STORY', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取默认（全部数据）", tags = {"需求" } ,notes = "根据产品获取默认（全部数据）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/fetchview")
	public ResponseEntity<List<StoryDTO>> fetchViewByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchView(context) ;
        List<StoryDTO> list = storyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

