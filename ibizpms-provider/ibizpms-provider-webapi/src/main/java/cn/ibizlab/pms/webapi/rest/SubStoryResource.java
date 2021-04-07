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
import cn.ibizlab.pms.core.zentao.model.impl.SubStoryModelImpl;

@Slf4j
@Api(tags = {"需求" })
@RestController("WebApi-substory")
@RequestMapping("")
public class SubStoryResource {

    @Autowired
    public IStoryService storyService;

    @Autowired
    public SubStoryModelImpl substoryModelImpl;

    @Autowired
    @Lazy
    public SubStoryMapping substoryMapping;

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'CREATE')")
    @ApiOperation(value = "新建需求", tags = {"需求" },  notes = "新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories")
    public ResponseEntity<SubStoryDTO> create(@Validated @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
		storyService.create(domain);
        SubStoryDTO dto = substoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'CREATE')")
    @ApiOperation(value = "批量新建需求", tags = {"需求" },  notes = "批量新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        storyService.createBatch(substoryMapping.toDomain(substorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@StoryModelImpl.test(#substory_id,'UPDATE')")
    @ApiOperation(value = "更新需求", tags = {"需求" },  notes = "更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/substories/{substory_id}")
    public ResponseEntity<SubStoryDTO> update(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
		Story domain  = substoryMapping.toDomain(substorydto);
        domain .setId(substory_id);
		storyService.update(domain );
		SubStoryDTO dto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'UPDATE')")
    @ApiOperation(value = "批量更新需求", tags = {"需求" },  notes = "批量更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/substories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        storyService.updateBatch(substoryMapping.toDomain(substorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'DELETE')")
    @ApiOperation(value = "删除需求", tags = {"需求" },  notes = "删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/substories/{substory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("substory_id") Long substory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(substory_id));
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'DELETE')")
    @ApiOperation(value = "批量删除需求", tags = {"需求" },  notes = "批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/substories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'READ')")
    @ApiOperation(value = "获取需求", tags = {"需求" },  notes = "获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/substories/{substory_id}")
    public ResponseEntity<SubStoryDTO> get(@PathVariable("substory_id") Long substory_id) {
        Story domain = storyService.get(substory_id);
        SubStoryDTO dto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取需求草稿", tags = {"需求" },  notes = "获取需求草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/substories/getdraft")
    public ResponseEntity<SubStoryDTO> getDraft(SubStoryDTO dto) {
        Story domain = substoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(substoryMapping.toDto(storyService.getDraft(domain)));
    }

    @ApiOperation(value = "激活", tags = {"需求" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/activate")
    public ResponseEntity<SubStoryDTO> activate(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.activate(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-Activate-all')")
    @ApiOperation(value = "批量处理[激活]", tags = {"需求" },  notes = "批量处理[激活]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/activatebatch")
    public ResponseEntity<Boolean> activateBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "全部推送", tags = {"需求" },  notes = "全部推送")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/allpush")
    public ResponseEntity<SubStoryDTO> allPush(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.allPush(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-AllPush-all')")
    @ApiOperation(value = "批量处理[全部推送]", tags = {"需求" },  notes = "批量处理[全部推送]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/allpushbatch")
    public ResponseEntity<Boolean> allPushBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.allPushBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "指派", tags = {"需求" },  notes = "指派")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/assignto")
    public ResponseEntity<SubStoryDTO> assignTo(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.assignTo(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-AssignTo-all')")
    @ApiOperation(value = "批量处理[指派]", tags = {"需求" },  notes = "批量处理[指派]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/assigntobatch")
    public ResponseEntity<Boolean> assignToBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量指派", tags = {"需求" },  notes = "批量指派")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchassignto")
    public ResponseEntity<SubStoryDTO> batchAssignTo(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchAssignTo(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchAssignTo-all')")
    @ApiOperation(value = "批量处理[批量指派]", tags = {"需求" },  notes = "批量处理[批量指派]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchassigntobatch")
    public ResponseEntity<Boolean> batchAssignToBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchAssignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量变更平台/分支", tags = {"需求" },  notes = "批量变更平台/分支")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchchangebranch")
    public ResponseEntity<SubStoryDTO> batchChangeBranch(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchChangeBranch(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchChangeBranch-all')")
    @ApiOperation(value = "批量处理[批量变更平台/分支]", tags = {"需求" },  notes = "批量处理[批量变更平台/分支]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchchangebranchbatch")
    public ResponseEntity<Boolean> batchChangeBranchBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeBranchBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量变更模块", tags = {"需求" },  notes = "批量变更模块")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchchangemodule")
    public ResponseEntity<SubStoryDTO> batchChangeModule(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchChangeModule(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchChangeModule-all')")
    @ApiOperation(value = "批量处理[批量变更模块]", tags = {"需求" },  notes = "批量处理[批量变更模块]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchchangemodulebatch")
    public ResponseEntity<Boolean> batchChangeModuleBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeModuleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量关联计划", tags = {"需求" },  notes = "批量关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchchangeplan")
    public ResponseEntity<SubStoryDTO> batchChangePlan(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchChangePlan(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchChangePlan-all')")
    @ApiOperation(value = "批量处理[批量关联计划]", tags = {"需求" },  notes = "批量处理[批量关联计划]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchchangeplanbatch")
    public ResponseEntity<Boolean> batchChangePlanBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量变更阶段", tags = {"需求" },  notes = "批量变更阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchchangestage")
    public ResponseEntity<SubStoryDTO> batchChangeStage(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchChangeStage(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchChangeStage-all')")
    @ApiOperation(value = "批量处理[批量变更阶段]", tags = {"需求" },  notes = "批量处理[批量变更阶段]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchchangestagebatch")
    public ResponseEntity<Boolean> batchChangeStageBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeStageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量关闭", tags = {"需求" },  notes = "批量关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchclose")
    public ResponseEntity<SubStoryDTO> batchClose(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchClose(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchClose-all')")
    @ApiOperation(value = "批量处理[批量关闭]", tags = {"需求" },  notes = "批量处理[批量关闭]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchclosebatch")
    public ResponseEntity<Boolean> batchCloseBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchCloseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量评审", tags = {"需求" },  notes = "批量评审")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchreview")
    public ResponseEntity<SubStoryDTO> batchReview(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchReview(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchReview-all')")
    @ApiOperation(value = "批量处理[批量评审]", tags = {"需求" },  notes = "批量处理[批量评审]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchreviewbatch")
    public ResponseEntity<Boolean> batchReviewBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchReviewBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "计划批量解除关联需求", tags = {"需求" },  notes = "计划批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/batchunlinkstory")
    public ResponseEntity<SubStoryDTO> batchUnlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.batchUnlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BatchUnlinkStory-all')")
    @ApiOperation(value = "批量处理[计划批量解除关联需求]", tags = {"需求" },  notes = "批量处理[计划批量解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "bug转需求", tags = {"需求" },  notes = "bug转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/bugtostory")
    public ResponseEntity<SubStoryDTO> bugToStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.bugToStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BugToStory-all')")
    @ApiOperation(value = "批量处理[bug转需求]", tags = {"需求" },  notes = "批量处理[bug转需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/bugtostorybatch")
    public ResponseEntity<Boolean> bugToStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.bugToStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "版本批量解除关联需求", tags = {"需求" },  notes = "版本批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/buildbatchunlinkstory")
    public ResponseEntity<SubStoryDTO> buildBatchUnlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.buildBatchUnlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BuildBatchUnlinkStory-all')")
    @ApiOperation(value = "批量处理[版本批量解除关联需求]", tags = {"需求" },  notes = "批量处理[版本批量解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/buildbatchunlinkstorybatch")
    public ResponseEntity<Boolean> buildBatchUnlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "项目关联需求", tags = {"需求" },  notes = "项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/buildlinkstory")
    public ResponseEntity<SubStoryDTO> buildLinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.buildLinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BuildLinkStory-all')")
    @ApiOperation(value = "批量处理[项目关联需求]", tags = {"需求" },  notes = "批量处理[项目关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/buildlinkstorybatch")
    public ResponseEntity<Boolean> buildLinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "版本解除关联需求", tags = {"需求" },  notes = "版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/buildunlinkstory")
    public ResponseEntity<SubStoryDTO> buildUnlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.buildUnlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-BuildUnlinkStory-all')")
    @ApiOperation(value = "批量处理[版本解除关联需求]", tags = {"需求" },  notes = "批量处理[版本解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/buildunlinkstorybatch")
    public ResponseEntity<Boolean> buildUnlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "版本解除关联需求", tags = {"需求" },  notes = "版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/buildunlinkstorys")
    public ResponseEntity<SubStoryDTO> buildUnlinkStorys(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.buildUnlinkStorys(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }

    @ApiOperation(value = "变更", tags = {"需求" },  notes = "变更")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/change")
    public ResponseEntity<SubStoryDTO> change(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.change(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-Change-all')")
    @ApiOperation(value = "批量处理[变更]", tags = {"需求" },  notes = "批量处理[变更]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/changebatch")
    public ResponseEntity<Boolean> changeBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.changeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "检查需求", tags = {"需求" },  notes = "检查需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SubStoryDTO substorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyService.checkKey(substoryMapping.toDomain(substorydto)));
    }

    @ApiOperation(value = "关闭", tags = {"需求" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/close")
    public ResponseEntity<SubStoryDTO> close(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.close(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-Close-all')")
    @ApiOperation(value = "批量处理[关闭]", tags = {"需求" },  notes = "批量处理[关闭]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/closebatch")
    public ResponseEntity<Boolean> closeBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'CREATE')")
    @ApiOperation(value = "生成任务", tags = {"需求" },  notes = "生成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/createtasks")
    public ResponseEntity<SubStoryDTO> createTasks(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.createTasks(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-CreateTasks-all')")
    @ApiOperation(value = "批量处理[生成任务]", tags = {"需求" },  notes = "批量处理[生成任务]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/createtasksbatch")
    public ResponseEntity<Boolean> createTasksBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.createTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'READ')")
    @ApiOperation(value = "获取需求描述", tags = {"需求" },  notes = "获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/getstoryspec")
    public ResponseEntity<SubStoryDTO> getStorySpec(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.getStorySpec(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-GetStorySpec-all')")
    @ApiOperation(value = "批量处理[获取需求描述]", tags = {"需求" },  notes = "批量处理[获取需求描述]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/getstoryspecbatch")
    public ResponseEntity<Boolean> getStorySpecBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.getStorySpecBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@StoryModelImpl.test(#substory_id,'READ')")
    @ApiOperation(value = "获取需求描述", tags = {"需求" },  notes = "获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/substories/{substory_id}/getstoryspecs")
    public ResponseEntity<SubStoryDTO> getStorySpecs(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.getStorySpecs(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }

    @ApiOperation(value = "项目关联需求-按计划关联", tags = {"需求" },  notes = "项目关联需求-按计划关联")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/importplanstories")
    public ResponseEntity<SubStoryDTO> importPlanStories(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.importPlanStories(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ImportPlanStories-all')")
    @ApiOperation(value = "批量处理[项目关联需求-按计划关联]", tags = {"需求" },  notes = "批量处理[项目关联需求-按计划关联]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/importplanstoriesbatch")
    public ResponseEntity<Boolean> importPlanStoriesBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.importPlanStoriesBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "计划关联需求", tags = {"需求" },  notes = "计划关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/linkstory")
    public ResponseEntity<SubStoryDTO> linkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.linkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-LinkStory-all')")
    @ApiOperation(value = "批量处理[计划关联需求]", tags = {"需求" },  notes = "批量处理[计划关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "项目批量解除关联需求", tags = {"需求" },  notes = "项目批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/projectbatchunlinkstory")
    public ResponseEntity<SubStoryDTO> projectBatchUnlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.projectBatchUnlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ProjectBatchUnlinkStory-all')")
    @ApiOperation(value = "批量处理[项目批量解除关联需求]", tags = {"需求" },  notes = "批量处理[项目批量解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/projectbatchunlinkstorybatch")
    public ResponseEntity<Boolean> projectBatchUnlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "项目关联需求", tags = {"需求" },  notes = "项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/projectlinkstory")
    public ResponseEntity<SubStoryDTO> projectLinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.projectLinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ProjectLinkStory-all')")
    @ApiOperation(value = "批量处理[项目关联需求]", tags = {"需求" },  notes = "批量处理[项目关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/projectlinkstorybatch")
    public ResponseEntity<Boolean> projectLinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "项目解除关联需求", tags = {"需求" },  notes = "项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/projectunlinkstory")
    public ResponseEntity<SubStoryDTO> projectUnlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.projectUnlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ProjectUnlinkStory-all')")
    @ApiOperation(value = "批量处理[项目解除关联需求]", tags = {"需求" },  notes = "批量处理[项目解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/projectunlinkstorybatch")
    public ResponseEntity<Boolean> projectUnlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "项目解除关联需求", tags = {"需求" },  notes = "项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/projectunlinkstorys")
    public ResponseEntity<SubStoryDTO> projectUnlinkStorys(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.projectUnlinkStorys(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }

    @ApiOperation(value = "推送", tags = {"需求" },  notes = "推送")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/push")
    public ResponseEntity<SubStoryDTO> push(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.push(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-Push-all')")
    @ApiOperation(value = "批量处理[推送]", tags = {"需求" },  notes = "批量处理[推送]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/pushbatch")
    public ResponseEntity<Boolean> pushBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.pushBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发布批量解除关联需求", tags = {"需求" },  notes = "发布批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/releasebatchunlinkstory")
    public ResponseEntity<SubStoryDTO> releaseBatchUnlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.releaseBatchUnlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ReleaseBatchUnlinkStory-all')")
    @ApiOperation(value = "批量处理[发布批量解除关联需求]", tags = {"需求" },  notes = "批量处理[发布批量解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/releasebatchunlinkstorybatch")
    public ResponseEntity<Boolean> releaseBatchUnlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发布关联需求", tags = {"需求" },  notes = "发布关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/releaselinkstory")
    public ResponseEntity<SubStoryDTO> releaseLinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.releaseLinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ReleaseLinkStory-all')")
    @ApiOperation(value = "批量处理[发布关联需求]", tags = {"需求" },  notes = "批量处理[发布关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/releaselinkstorybatch")
    public ResponseEntity<Boolean> releaseLinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发布解除关联需求", tags = {"需求" },  notes = "发布解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/releaseunlinkstory")
    public ResponseEntity<SubStoryDTO> releaseUnlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.releaseUnlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ReleaseUnlinkStory-all')")
    @ApiOperation(value = "批量处理[发布解除关联需求]", tags = {"需求" },  notes = "批量处理[发布解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/releaseunlinkstorybatch")
    public ResponseEntity<Boolean> releaseUnlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "重置由谁评审", tags = {"需求" },  notes = "重置由谁评审")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/resetreviewedby")
    public ResponseEntity<SubStoryDTO> resetReviewedBy(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.resetReviewedBy(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-ResetReviewedBy-all')")
    @ApiOperation(value = "批量处理[重置由谁评审]", tags = {"需求" },  notes = "批量处理[重置由谁评审]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/resetreviewedbybatch")
    public ResponseEntity<Boolean> resetReviewedByBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.resetReviewedByBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "评审", tags = {"需求" },  notes = "评审")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/review")
    public ResponseEntity<SubStoryDTO> review(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.review(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-Review-all')")
    @ApiOperation(value = "批量处理[评审]", tags = {"需求" },  notes = "批量处理[评审]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/reviewbatch")
    public ResponseEntity<Boolean> reviewBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.reviewBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存需求", tags = {"需求" },  notes = "保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/save")
    public ResponseEntity<SubStoryDTO> save(@RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        storyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substoryMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存需求", tags = {"需求" },  notes = "批量保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        storyService.saveBatch(substoryMapping.toDomain(substorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "行为", tags = {"需求" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/sendmessage")
    public ResponseEntity<SubStoryDTO> sendMessage(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.sendMessage(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-SendMessage-all')")
    @ApiOperation(value = "批量处理[行为]", tags = {"需求" },  notes = "批量处理[行为]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送消息前置处理", tags = {"需求" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/sendmsgpreprocess")
    public ResponseEntity<SubStoryDTO> sendMsgPreProcess(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.sendMsgPreProcess(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-SendMsgPreProcess-all')")
    @ApiOperation(value = "批量处理[发送消息前置处理]", tags = {"需求" },  notes = "批量处理[发送消息前置处理]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "需求收藏", tags = {"需求" },  notes = "需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/storyfavorites")
    public ResponseEntity<SubStoryDTO> storyFavorites(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.storyFavorites(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }

    @ApiOperation(value = "取消收藏", tags = {"需求" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/storynfavorites")
    public ResponseEntity<SubStoryDTO> storyNFavorites(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.storyNFavorites(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }

    @ApiOperation(value = "同步Ibz平台实体", tags = {"需求" },  notes = "同步Ibz平台实体")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/syncfromibiz")
    public ResponseEntity<SubStoryDTO> syncFromIbiz(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.syncFromIbiz(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-SyncFromIbiz-all')")
    @ApiOperation(value = "批量处理[同步Ibz平台实体]", tags = {"需求" },  notes = "批量处理[同步Ibz平台实体]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/syncfromibizbatch")
    public ResponseEntity<Boolean> syncFromIbizBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.syncFromIbizBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "计划解除关联需求", tags = {"需求" },  notes = "计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/unlinkstory")
    public ResponseEntity<SubStoryDTO> unlinkStory(@PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setId(substory_id);
        domain = storyService.unlinkStory(domain);
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Story-UnlinkStory-all')")
    @ApiOperation(value = "批量处理[计划解除关联需求]", tags = {"需求" },  notes = "批量处理[计划解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/substories/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryBatch(@RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取指派给我的需求", tags = {"需求" } ,notes = "获取指派给我的需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchassignedtomystory")
	public ResponseEntity<List<SubStoryDTO>> fetchAssignedToMyStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询指派给我的需求", tags = {"需求" } ,notes = "查询指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchassignedtomystory")
	public ResponseEntity<Page<SubStoryDTO>> searchAssignedToMyStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取指派给我的需求（日历）", tags = {"需求" } ,notes = "获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchassignedtomystorycalendar")
	public ResponseEntity<List<SubStoryDTO>> fetchAssignedToMyStoryCalendar(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询指派给我的需求（日历）", tags = {"需求" } ,notes = "查询指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchassignedtomystorycalendar")
	public ResponseEntity<Page<SubStoryDTO>> searchAssignedToMyStoryCalendar(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取Bug相关需求", tags = {"需求" } ,notes = "获取Bug相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchbugstory")
	public ResponseEntity<List<SubStoryDTO>> fetchBugStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBugStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询Bug相关需求", tags = {"需求" } ,notes = "查询Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchbugstory")
	public ResponseEntity<Page<SubStoryDTO>> searchBugStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBugStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<SubStoryDTO>> fetchBuildLinkCompletedStories(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "查询版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchbuildlinkcompletedstories")
	public ResponseEntity<Page<SubStoryDTO>> searchBuildLinkCompletedStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取版本可关联的需求（产品内）", tags = {"需求" } ,notes = "获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchbuildlinkablestories")
	public ResponseEntity<List<SubStoryDTO>> fetchBuildLinkableStories(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询版本可关联的需求（产品内）", tags = {"需求" } ,notes = "查询版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchbuildlinkablestories")
	public ResponseEntity<Page<SubStoryDTO>> searchBuildLinkableStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取获取版本相关需求", tags = {"需求" } ,notes = "获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchbuildstories")
	public ResponseEntity<List<SubStoryDTO>> fetchBuildStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBuildStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询获取版本相关需求", tags = {"需求" } ,notes = "查询获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchbuildstories")
	public ResponseEntity<Page<SubStoryDTO>> searchBuildStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchBuildStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取通过模块查询", tags = {"需求" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchbymodule")
	public ResponseEntity<List<SubStoryDTO>> fetchByModule(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchByModule(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询通过模块查询", tags = {"需求" } ,notes = "查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchbymodule")
	public ResponseEntity<Page<SubStoryDTO>> searchByModule(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取相关用例需求", tags = {"需求" } ,notes = "获取相关用例需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchcasestory")
	public ResponseEntity<List<SubStoryDTO>> fetchCaseStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchCaseStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询相关用例需求", tags = {"需求" } ,notes = "查询相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchcasestory")
	public ResponseEntity<Page<SubStoryDTO>> searchCaseStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchCaseStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"需求" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchdefault")
	public ResponseEntity<List<SubStoryDTO>> fetchDefault(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchDefault(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"需求" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchdefault")
	public ResponseEntity<Page<SubStoryDTO>> searchDefault(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"需求" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchesbulk")
	public ResponseEntity<List<SubStoryDTO>> fetchESBulk(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchESBulk(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询ES批量的导入", tags = {"需求" } ,notes = "查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchesbulk")
	public ResponseEntity<Page<SubStoryDTO>> searchESBulk(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取获取产品需求", tags = {"需求" } ,notes = "获取获取产品需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchgetproductstories")
	public ResponseEntity<List<SubStoryDTO>> fetchGetProductStories(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchGetProductStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询获取产品需求", tags = {"需求" } ,notes = "查询获取产品需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchgetproductstories")
	public ResponseEntity<Page<SubStoryDTO>> searchGetProductStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchGetProductStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取我代理的需求", tags = {"需求" } ,notes = "获取我代理的需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchmyagentstory")
	public ResponseEntity<List<SubStoryDTO>> fetchMyAgentStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询我代理的需求", tags = {"需求" } ,notes = "查询我代理的需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchmyagentstory")
	public ResponseEntity<Page<SubStoryDTO>> searchMyAgentStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "获取所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchmycuropenedstory")
	public ResponseEntity<List<SubStoryDTO>> fetchMyCurOpenedStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "查询所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchmycuropenedstory")
	public ResponseEntity<Page<SubStoryDTO>> searchMyCurOpenedStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"需求" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchmyfavorites")
	public ResponseEntity<List<SubStoryDTO>> fetchMyFavorites(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"需求" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchmyfavorites")
	public ResponseEntity<Page<SubStoryDTO>> searchMyFavorites(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取计划关联需求(去除已关联)", tags = {"需求" } ,notes = "获取计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchnotcurplanlinkstory")
	public ResponseEntity<List<SubStoryDTO>> fetchNotCurPlanLinkStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询计划关联需求(去除已关联)", tags = {"需求" } ,notes = "查询计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchnotcurplanlinkstory")
	public ResponseEntity<Page<SubStoryDTO>> searchNotCurPlanLinkStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取数据查询", tags = {"需求" } ,notes = "获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchparentdefault")
	public ResponseEntity<List<SubStoryDTO>> fetchParentDefault(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询数据查询", tags = {"需求" } ,notes = "查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchparentdefault")
	public ResponseEntity<Page<SubStoryDTO>> searchParentDefault(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchParentDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取数据查询", tags = {"需求" } ,notes = "获取数据查询")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchparentdefaultq")
	public ResponseEntity<List<SubStoryDTO>> fetchParentDefaultQ(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询数据查询", tags = {"需求" } ,notes = "查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchparentdefaultq")
	public ResponseEntity<Page<SubStoryDTO>> searchParentDefaultQ(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取项目关联需求", tags = {"需求" } ,notes = "获取项目关联需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchprojectlinkstory")
	public ResponseEntity<List<SubStoryDTO>> fetchProjectLinkStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询项目关联需求", tags = {"需求" } ,notes = "查询项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchprojectlinkstory")
	public ResponseEntity<Page<SubStoryDTO>> searchProjectLinkStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取项目相关需求", tags = {"需求" } ,notes = "获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchprojectstories")
	public ResponseEntity<List<SubStoryDTO>> fetchProjectStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询项目相关需求", tags = {"需求" } ,notes = "查询项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchprojectstories")
	public ResponseEntity<Page<SubStoryDTO>> searchProjectStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchProjectStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取版本可关联的完成的需求", tags = {"需求" } ,notes = "获取版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchreleaselinkablestories")
	public ResponseEntity<List<SubStoryDTO>> fetchReleaseLinkableStories(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询版本可关联的完成的需求", tags = {"需求" } ,notes = "查询版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchreleaselinkablestories")
	public ResponseEntity<Page<SubStoryDTO>> searchReleaseLinkableStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取获取产品发布相关需求", tags = {"需求" } ,notes = "获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchreleasestories")
	public ResponseEntity<List<SubStoryDTO>> fetchReleaseStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchReleaseStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询获取产品发布相关需求", tags = {"需求" } ,notes = "查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchreleasestories")
	public ResponseEntity<Page<SubStoryDTO>> searchReleaseStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchReleaseStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取通过模块查询", tags = {"需求" } ,notes = "获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/substories/fetchreportstories")
	public ResponseEntity<List<SubStoryDTO>> fetchReportStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchReportStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询通过模块查询", tags = {"需求" } ,notes = "查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchreportstories")
	public ResponseEntity<Page<SubStoryDTO>> searchReportStories(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchReportStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取获取产品发布相关需求", tags = {"需求" } ,notes = "获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchstorychild")
	public ResponseEntity<List<SubStoryDTO>> fetchStoryChild(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchStoryChild(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询获取产品发布相关需求", tags = {"需求" } ,notes = "查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchstorychild")
	public ResponseEntity<Page<SubStoryDTO>> searchStoryChild(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchStoryChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取获取产品发布相关需求", tags = {"需求" } ,notes = "获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchstoryrelated")
	public ResponseEntity<List<SubStoryDTO>> fetchStoryRelated(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询获取产品发布相关需求", tags = {"需求" } ,notes = "查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchstoryrelated")
	public ResponseEntity<Page<SubStoryDTO>> searchStoryRelated(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchStoryRelated(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取需求细分", tags = {"需求" } ,notes = "获取需求细分")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchsubstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchSubStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询需求细分", tags = {"需求" } ,notes = "查询需求细分")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchsubstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchSubStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取任务相关需求", tags = {"需求" } ,notes = "获取任务相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchtaskrelatedstory")
	public ResponseEntity<List<SubStoryDTO>> fetchTaskRelatedStory(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询任务相关需求", tags = {"需求" } ,notes = "查询任务相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchtaskrelatedstory")
	public ResponseEntity<Page<SubStoryDTO>> searchTaskRelatedStory(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "获取默认（全部数据）", tags = {"需求" } ,notes = "获取默认（全部数据）")
    @RequestMapping(method= RequestMethod.GET , value="/substories/fetchview")
	public ResponseEntity<List<SubStoryDTO>> fetchView(StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchView(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryModelImpl.test('READ')")
	@ApiOperation(value = "查询默认（全部数据）", tags = {"需求" } ,notes = "查询默认（全部数据）")
    @RequestMapping(method= RequestMethod.POST , value="/substories/searchview")
	public ResponseEntity<Page<SubStoryDTO>> searchView(@RequestBody StorySearchContext context) {
        substoryModelImpl.addAuthorityConditions(context,"READ");
        Page<Story> domains = storyService.searchView(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/substories/{substory_id}/{action}")
    public ResponseEntity<SubStoryDTO> dynamicCall(@PathVariable("substory_id") Long substory_id , @PathVariable("action") String action , @RequestBody SubStoryDTO substorydto) {
        Story domain = storyService.dynamicCall(substory_id, action, substoryMapping.toDomain(substorydto));
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }

    @ApiOperation(value = "根据需求建立需求", tags = {"需求" },  notes = "根据需求建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories")
    public ResponseEntity<SubStoryDTO> createByStory(@PathVariable("story_id") Long story_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
		storyService.create(domain);
        SubStoryDTO dto = substoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求批量建立需求", tags = {"需求" },  notes = "根据需求批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batch")
    public ResponseEntity<Boolean> createBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domainlist=substoryMapping.toDomain(substorydtos);
        for(Story domain:domainlist){
            domain.setParent(story_id);
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @ApiOperation(value = "根据需求更新需求", tags = {"需求" },  notes = "根据需求更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/substories/{substory_id}")
    public ResponseEntity<SubStoryDTO> updateByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain.setId(substory_id);
		storyService.update(domain);
        SubStoryDTO dto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求批量更新需求", tags = {"需求" },  notes = "根据需求批量更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/substories/batch")
    public ResponseEntity<Boolean> updateBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domainlist=substoryMapping.toDomain(substorydtos);
        for(Story domain:domainlist){
            domain.setParent(story_id);
        }
        storyService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求删除需求", tags = {"需求" },  notes = "根据需求删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/substories/{substory_id}")
    public ResponseEntity<Boolean> removeByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(substory_id));
    }

    @ApiOperation(value = "根据需求批量删除需求", tags = {"需求" },  notes = "根据需求批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/substories/batch")
    public ResponseEntity<Boolean> removeBatchByStory(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求获取需求", tags = {"需求" },  notes = "根据需求获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/substories/{substory_id}")
    public ResponseEntity<SubStoryDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id) {
        Story domain = storyService.get(substory_id);
        SubStoryDTO dto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求获取需求草稿", tags = {"需求" },  notes = "根据需求获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/substories/getdraft")
    public ResponseEntity<SubStoryDTO> getDraftByStory(@PathVariable("story_id") Long story_id, SubStoryDTO dto) {
        Story domain = substoryMapping.toDomain(dto);
        domain.setParent(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(substoryMapping.toDto(storyService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/activate")
    public ResponseEntity<SubStoryDTO> activateByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.activate(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/activatebatch")
    public ResponseEntity<Boolean> activateByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/allpush")
    public ResponseEntity<SubStoryDTO> allPushByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.allPush(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/allpushbatch")
    public ResponseEntity<Boolean> allPushByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.allPushBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/assignto")
    public ResponseEntity<SubStoryDTO> assignToByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.assignTo(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/assigntobatch")
    public ResponseEntity<Boolean> assignToByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchassignto")
    public ResponseEntity<SubStoryDTO> batchAssignToByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchAssignTo(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchassigntobatch")
    public ResponseEntity<Boolean> batchAssignToByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchAssignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchchangebranch")
    public ResponseEntity<SubStoryDTO> batchChangeBranchByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangeBranch(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchchangebranchbatch")
    public ResponseEntity<Boolean> batchChangeBranchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeBranchBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchchangemodule")
    public ResponseEntity<SubStoryDTO> batchChangeModuleByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangeModule(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchchangemodulebatch")
    public ResponseEntity<Boolean> batchChangeModuleByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeModuleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchchangeplan")
    public ResponseEntity<SubStoryDTO> batchChangePlanByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangePlan(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchchangeplanbatch")
    public ResponseEntity<Boolean> batchChangePlanByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchchangestage")
    public ResponseEntity<SubStoryDTO> batchChangeStageByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangeStage(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchchangestagebatch")
    public ResponseEntity<Boolean> batchChangeStageByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeStageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchclose")
    public ResponseEntity<SubStoryDTO> batchCloseByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchClose(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchclosebatch")
    public ResponseEntity<Boolean> batchCloseByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchCloseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchreview")
    public ResponseEntity<SubStoryDTO> batchReviewByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchReview(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchreviewbatch")
    public ResponseEntity<Boolean> batchReviewByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchReviewBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/batchunlinkstory")
    public ResponseEntity<SubStoryDTO> batchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/bugtostory")
    public ResponseEntity<SubStoryDTO> bugToStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.bugToStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/bugtostorybatch")
    public ResponseEntity<Boolean> bugToStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.bugToStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/buildbatchunlinkstory")
    public ResponseEntity<SubStoryDTO> buildBatchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildBatchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/buildbatchunlinkstorybatch")
    public ResponseEntity<Boolean> buildBatchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/buildlinkstory")
    public ResponseEntity<SubStoryDTO> buildLinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildLinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/buildlinkstorybatch")
    public ResponseEntity<Boolean> buildLinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/buildunlinkstory")
    public ResponseEntity<SubStoryDTO> buildUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/buildunlinkstorybatch")
    public ResponseEntity<Boolean> buildUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/buildunlinkstorys")
    public ResponseEntity<SubStoryDTO> buildUnlinkStorysByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildUnlinkStorys(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/change")
    public ResponseEntity<SubStoryDTO> changeByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.change(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/changebatch")
    public ResponseEntity<Boolean> changeByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.changeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求检查需求", tags = {"需求" },  notes = "根据需求检查需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/checkkey")
    public ResponseEntity<Boolean> checkKeyByStory(@PathVariable("story_id") Long story_id, @RequestBody SubStoryDTO substorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyService.checkKey(substoryMapping.toDomain(substorydto)));
    }

    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/close")
    public ResponseEntity<SubStoryDTO> closeByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.close(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/closebatch")
    public ResponseEntity<Boolean> closeByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/createtasks")
    public ResponseEntity<SubStoryDTO> createTasksByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.createTasks(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/createtasksbatch")
    public ResponseEntity<Boolean> createTasksByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.createTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/getstoryspec")
    public ResponseEntity<SubStoryDTO> getStorySpecByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.getStorySpec(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/getstoryspecbatch")
    public ResponseEntity<Boolean> getStorySpecByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.getStorySpecBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/substories/{substory_id}/getstoryspecs")
    public ResponseEntity<SubStoryDTO> getStorySpecsByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.getStorySpecs(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/importplanstories")
    public ResponseEntity<SubStoryDTO> importPlanStoriesByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.importPlanStories(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/importplanstoriesbatch")
    public ResponseEntity<Boolean> importPlanStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.importPlanStoriesBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/linkstory")
    public ResponseEntity<SubStoryDTO> linkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.linkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/projectbatchunlinkstory")
    public ResponseEntity<SubStoryDTO> projectBatchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectBatchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/projectbatchunlinkstorybatch")
    public ResponseEntity<Boolean> projectBatchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/projectlinkstory")
    public ResponseEntity<SubStoryDTO> projectLinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectLinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/projectlinkstorybatch")
    public ResponseEntity<Boolean> projectLinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/projectunlinkstory")
    public ResponseEntity<SubStoryDTO> projectUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/projectunlinkstorybatch")
    public ResponseEntity<Boolean> projectUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/projectunlinkstorys")
    public ResponseEntity<SubStoryDTO> projectUnlinkStorysByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectUnlinkStorys(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/push")
    public ResponseEntity<SubStoryDTO> pushByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.push(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/pushbatch")
    public ResponseEntity<Boolean> pushByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.pushBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/releasebatchunlinkstory")
    public ResponseEntity<SubStoryDTO> releaseBatchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.releaseBatchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/releasebatchunlinkstorybatch")
    public ResponseEntity<Boolean> releaseBatchUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/releaselinkstory")
    public ResponseEntity<SubStoryDTO> releaseLinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.releaseLinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/releaselinkstorybatch")
    public ResponseEntity<Boolean> releaseLinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/releaseunlinkstory")
    public ResponseEntity<SubStoryDTO> releaseUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.releaseUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/releaseunlinkstorybatch")
    public ResponseEntity<Boolean> releaseUnlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/resetreviewedby")
    public ResponseEntity<SubStoryDTO> resetReviewedByByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.resetReviewedBy(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/resetreviewedbybatch")
    public ResponseEntity<Boolean> resetReviewedByByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.resetReviewedByBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/review")
    public ResponseEntity<SubStoryDTO> reviewByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.review(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/reviewbatch")
    public ResponseEntity<Boolean> reviewByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.reviewBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求保存需求", tags = {"需求" },  notes = "根据需求保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/save")
    public ResponseEntity<SubStoryDTO> saveByStory(@PathVariable("story_id") Long story_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        storyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求批量保存需求", tags = {"需求" },  notes = "根据需求批量保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/savebatch")
    public ResponseEntity<Boolean> saveBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domainlist=substoryMapping.toDomain(substorydtos);
        for(Story domain:domainlist){
             domain.setParent(story_id);
        }
        storyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/sendmessage")
    public ResponseEntity<SubStoryDTO> sendMessageByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.sendMessage(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/sendmsgpreprocess")
    public ResponseEntity<SubStoryDTO> sendMsgPreProcessByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.sendMsgPreProcess(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/storyfavorites")
    public ResponseEntity<SubStoryDTO> storyFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.storyFavorites(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/storynfavorites")
    public ResponseEntity<SubStoryDTO> storyNFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.storyNFavorites(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/syncfromibiz")
    public ResponseEntity<SubStoryDTO> syncFromIbizByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.syncFromIbiz(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/syncfromibizbatch")
    public ResponseEntity<Boolean> syncFromIbizByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.syncFromIbizBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求需求", tags = {"需求" },  notes = "根据需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/{substory_id}/unlinkstory")
    public ResponseEntity<SubStoryDTO> unlinkStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.unlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据需求需求]", tags = {"需求" },  notes = "批量处理[根据需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/substories/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	@ApiOperation(value = "根据需求获取指派给我的需求", tags = {"需求" } ,notes = "根据需求获取指派给我的需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchassignedtomystory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryAssignedToMyStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询指派给我的需求", tags = {"需求" } ,notes = "根据需求查询指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchassignedtomystory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryAssignedToMyStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取指派给我的需求（日历）", tags = {"需求" } ,notes = "根据需求获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchassignedtomystorycalendar")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryAssignedToMyStoryCalendarByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询指派给我的需求（日历）", tags = {"需求" } ,notes = "根据需求查询指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchassignedtomystorycalendar")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryAssignedToMyStoryCalendarByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Bug相关需求", tags = {"需求" } ,notes = "根据需求获取Bug相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchbugstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBugStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBugStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Bug相关需求", tags = {"需求" } ,notes = "根据需求查询Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchbugstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBugStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBugStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "根据需求获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBuildLinkCompletedStoriesByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "根据需求查询版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchbuildlinkcompletedstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBuildLinkCompletedStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本可关联的需求（产品内）", tags = {"需求" } ,notes = "根据需求获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchbuildlinkablestories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBuildLinkableStoriesByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本可关联的需求（产品内）", tags = {"需求" } ,notes = "根据需求查询版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchbuildlinkablestories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBuildLinkableStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取获取版本相关需求", tags = {"需求" } ,notes = "根据需求获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchbuildstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBuildStoriesByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询获取版本相关需求", tags = {"需求" } ,notes = "根据需求查询获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchbuildstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBuildStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取通过模块查询", tags = {"需求" } ,notes = "根据需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchbymodule")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryByModuleByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchByModule(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询通过模块查询", tags = {"需求" } ,notes = "根据需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchbymodule")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryByModuleByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取相关用例需求", tags = {"需求" } ,notes = "根据需求获取相关用例需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchcasestory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryCaseStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchCaseStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询相关用例需求", tags = {"需求" } ,notes = "根据需求查询相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchcasestory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryCaseStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchCaseStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取DEFAULT", tags = {"需求" } ,notes = "根据需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchdefault")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryDefaultByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchDefault(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询DEFAULT", tags = {"需求" } ,notes = "根据需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchdefault")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取ES批量的导入", tags = {"需求" } ,notes = "根据需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchesbulk")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryESBulkByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchESBulk(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询ES批量的导入", tags = {"需求" } ,notes = "根据需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchesbulk")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryESBulkByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取获取产品需求", tags = {"需求" } ,notes = "根据需求获取获取产品需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchgetproductstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryGetProductStoriesByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchGetProductStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询获取产品需求", tags = {"需求" } ,notes = "根据需求查询获取产品需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchgetproductstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryGetProductStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchGetProductStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取我代理的需求", tags = {"需求" } ,notes = "根据需求获取我代理的需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchmyagentstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryMyAgentStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询我代理的需求", tags = {"需求" } ,notes = "根据需求查询我代理的需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchmyagentstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryMyAgentStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "根据需求获取所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchmycuropenedstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryMyCurOpenedStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "根据需求查询所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchmycuropenedstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryMyCurOpenedStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取我的收藏", tags = {"需求" } ,notes = "根据需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchmyfavorites")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryMyFavoritesByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询我的收藏", tags = {"需求" } ,notes = "根据需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchmyfavorites")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryMyFavoritesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取计划关联需求(去除已关联)", tags = {"需求" } ,notes = "根据需求获取计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchnotcurplanlinkstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryNotCurPlanLinkStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询计划关联需求(去除已关联)", tags = {"需求" } ,notes = "根据需求查询计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchnotcurplanlinkstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryNotCurPlanLinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取数据查询", tags = {"需求" } ,notes = "根据需求获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchparentdefault")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryParentDefaultByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询数据查询", tags = {"需求" } ,notes = "根据需求查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchparentdefault")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryParentDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取数据查询", tags = {"需求" } ,notes = "根据需求获取数据查询")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchparentdefaultq")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryParentDefaultQByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询数据查询", tags = {"需求" } ,notes = "根据需求查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchparentdefaultq")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryParentDefaultQByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取项目关联需求", tags = {"需求" } ,notes = "根据需求获取项目关联需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchprojectlinkstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryProjectLinkStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询项目关联需求", tags = {"需求" } ,notes = "根据需求查询项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchprojectlinkstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryProjectLinkStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取项目相关需求", tags = {"需求" } ,notes = "根据需求获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchprojectstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryProjectStoriesByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询项目相关需求", tags = {"需求" } ,notes = "根据需求查询项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchprojectstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryProjectStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本可关联的完成的需求", tags = {"需求" } ,notes = "根据需求获取版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchreleaselinkablestories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryReleaseLinkableStoriesByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本可关联的完成的需求", tags = {"需求" } ,notes = "根据需求查询版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchreleaselinkablestories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryReleaseLinkableStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据需求获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchreleasestories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryReleaseStoriesByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据需求查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchreleasestories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryReleaseStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取通过模块查询", tags = {"需求" } ,notes = "根据需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/fetchreportstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryReportStoriesByStory(@PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReportStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询通过模块查询", tags = {"需求" } ,notes = "根据需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchreportstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryReportStoriesByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReportStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据需求获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchstorychild")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryStoryChildByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryChild(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据需求查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchstorychild")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryStoryChildByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据需求获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchstoryrelated")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryStoryRelatedByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据需求查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchstoryrelated")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryStoryRelatedByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取需求细分", tags = {"需求" } ,notes = "根据需求获取需求细分")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchsubstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStorySubStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchSubStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询需求细分", tags = {"需求" } ,notes = "根据需求查询需求细分")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchsubstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStorySubStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchSubStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取任务相关需求", tags = {"需求" } ,notes = "根据需求获取任务相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchtaskrelatedstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryTaskRelatedStoryByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询任务相关需求", tags = {"需求" } ,notes = "根据需求查询任务相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchtaskrelatedstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryTaskRelatedStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取默认（全部数据）", tags = {"需求" } ,notes = "根据需求获取默认（全部数据）")
    @RequestMapping(method= RequestMethod.GET , value="/stories/{story_id}/substories/fetchview")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryViewByStory(@PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchView(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询默认（全部数据）", tags = {"需求" } ,notes = "根据需求查询默认（全部数据）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/substories/searchview")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryViewByStory(@PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchView(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品需求建立需求", tags = {"需求" },  notes = "根据产品需求建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories")
    public ResponseEntity<SubStoryDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
		storyService.create(domain);
        SubStoryDTO dto = substoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求批量建立需求", tags = {"需求" },  notes = "根据产品需求批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batch")
    public ResponseEntity<Boolean> createBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domainlist=substoryMapping.toDomain(substorydtos);
        for(Story domain:domainlist){
            domain.setParent(story_id);
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @ApiOperation(value = "根据产品需求更新需求", tags = {"需求" },  notes = "根据产品需求更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}")
    public ResponseEntity<SubStoryDTO> updateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain.setId(substory_id);
		storyService.update(domain);
        SubStoryDTO dto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求批量更新需求", tags = {"需求" },  notes = "根据产品需求批量更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/substories/batch")
    public ResponseEntity<Boolean> updateBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domainlist=substoryMapping.toDomain(substorydtos);
        for(Story domain:domainlist){
            domain.setParent(story_id);
        }
        storyService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求删除需求", tags = {"需求" },  notes = "根据产品需求删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(substory_id));
    }

    @ApiOperation(value = "根据产品需求批量删除需求", tags = {"需求" },  notes = "根据产品需求批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/substories/batch")
    public ResponseEntity<Boolean> removeBatchByProductStory(@RequestBody List<Long> ids) {
        storyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求获取需求", tags = {"需求" },  notes = "根据产品需求获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}")
    public ResponseEntity<SubStoryDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id) {
        Story domain = storyService.get(substory_id);
        SubStoryDTO dto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求获取需求草稿", tags = {"需求" },  notes = "根据产品需求获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/substories/getdraft")
    public ResponseEntity<SubStoryDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, SubStoryDTO dto) {
        Story domain = substoryMapping.toDomain(dto);
        domain.setParent(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(substoryMapping.toDto(storyService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/activate")
    public ResponseEntity<SubStoryDTO> activateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.activate(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/activatebatch")
    public ResponseEntity<Boolean> activateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/allpush")
    public ResponseEntity<SubStoryDTO> allPushByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.allPush(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/allpushbatch")
    public ResponseEntity<Boolean> allPushByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.allPushBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/assignto")
    public ResponseEntity<SubStoryDTO> assignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.assignTo(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/assigntobatch")
    public ResponseEntity<Boolean> assignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchassignto")
    public ResponseEntity<SubStoryDTO> batchAssignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchAssignTo(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchassigntobatch")
    public ResponseEntity<Boolean> batchAssignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchAssignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangebranch")
    public ResponseEntity<SubStoryDTO> batchChangeBranchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangeBranch(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchchangebranchbatch")
    public ResponseEntity<Boolean> batchChangeBranchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeBranchBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangemodule")
    public ResponseEntity<SubStoryDTO> batchChangeModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangeModule(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchchangemodulebatch")
    public ResponseEntity<Boolean> batchChangeModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeModuleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangeplan")
    public ResponseEntity<SubStoryDTO> batchChangePlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangePlan(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchchangeplanbatch")
    public ResponseEntity<Boolean> batchChangePlanByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangePlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangestage")
    public ResponseEntity<SubStoryDTO> batchChangeStageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchChangeStage(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchchangestagebatch")
    public ResponseEntity<Boolean> batchChangeStageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchChangeStageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchclose")
    public ResponseEntity<SubStoryDTO> batchCloseByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchClose(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchclosebatch")
    public ResponseEntity<Boolean> batchCloseByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchCloseBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchreview")
    public ResponseEntity<SubStoryDTO> batchReviewByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchReview(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchreviewbatch")
    public ResponseEntity<Boolean> batchReviewByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchReviewBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchunlinkstory")
    public ResponseEntity<SubStoryDTO> batchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.batchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/bugtostory")
    public ResponseEntity<SubStoryDTO> bugToStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.bugToStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/bugtostorybatch")
    public ResponseEntity<Boolean> bugToStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.bugToStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildbatchunlinkstory")
    public ResponseEntity<SubStoryDTO> buildBatchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildBatchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/buildbatchunlinkstorybatch")
    public ResponseEntity<Boolean> buildBatchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildlinkstory")
    public ResponseEntity<SubStoryDTO> buildLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildLinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/buildlinkstorybatch")
    public ResponseEntity<Boolean> buildLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildunlinkstory")
    public ResponseEntity<SubStoryDTO> buildUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/buildunlinkstorybatch")
    public ResponseEntity<Boolean> buildUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.buildUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildunlinkstorys")
    public ResponseEntity<SubStoryDTO> buildUnlinkStorysByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.buildUnlinkStorys(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/change")
    public ResponseEntity<SubStoryDTO> changeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.change(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/changebatch")
    public ResponseEntity<Boolean> changeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.changeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求检查需求", tags = {"需求" },  notes = "根据产品需求检查需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody SubStoryDTO substorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(storyService.checkKey(substoryMapping.toDomain(substorydto)));
    }

    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/close")
    public ResponseEntity<SubStoryDTO> closeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.close(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/closebatch")
    public ResponseEntity<Boolean> closeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/createtasks")
    public ResponseEntity<SubStoryDTO> createTasksByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.createTasks(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/createtasksbatch")
    public ResponseEntity<Boolean> createTasksByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.createTasksBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/getstoryspec")
    public ResponseEntity<SubStoryDTO> getStorySpecByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.getStorySpec(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/getstoryspecbatch")
    public ResponseEntity<Boolean> getStorySpecByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.getStorySpecBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/getstoryspecs")
    public ResponseEntity<SubStoryDTO> getStorySpecsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.getStorySpecs(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/importplanstories")
    public ResponseEntity<SubStoryDTO> importPlanStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.importPlanStories(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/importplanstoriesbatch")
    public ResponseEntity<Boolean> importPlanStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.importPlanStoriesBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/linkstory")
    public ResponseEntity<SubStoryDTO> linkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.linkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectbatchunlinkstory")
    public ResponseEntity<SubStoryDTO> projectBatchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectBatchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/projectbatchunlinkstorybatch")
    public ResponseEntity<Boolean> projectBatchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectlinkstory")
    public ResponseEntity<SubStoryDTO> projectLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectLinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/projectlinkstorybatch")
    public ResponseEntity<Boolean> projectLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectunlinkstory")
    public ResponseEntity<SubStoryDTO> projectUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/projectunlinkstorybatch")
    public ResponseEntity<Boolean> projectUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.projectUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectunlinkstorys")
    public ResponseEntity<SubStoryDTO> projectUnlinkStorysByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.projectUnlinkStorys(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/push")
    public ResponseEntity<SubStoryDTO> pushByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.push(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/pushbatch")
    public ResponseEntity<Boolean> pushByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.pushBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/releasebatchunlinkstory")
    public ResponseEntity<SubStoryDTO> releaseBatchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.releaseBatchUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/releasebatchunlinkstorybatch")
    public ResponseEntity<Boolean> releaseBatchUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseBatchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/releaselinkstory")
    public ResponseEntity<SubStoryDTO> releaseLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.releaseLinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/releaselinkstorybatch")
    public ResponseEntity<Boolean> releaseLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseLinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/releaseunlinkstory")
    public ResponseEntity<SubStoryDTO> releaseUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.releaseUnlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/releaseunlinkstorybatch")
    public ResponseEntity<Boolean> releaseUnlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.releaseUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/resetreviewedby")
    public ResponseEntity<SubStoryDTO> resetReviewedByByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.resetReviewedBy(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/resetreviewedbybatch")
    public ResponseEntity<Boolean> resetReviewedByByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.resetReviewedByBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/review")
    public ResponseEntity<SubStoryDTO> reviewByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.review(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/reviewbatch")
    public ResponseEntity<Boolean> reviewByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.reviewBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求保存需求", tags = {"需求" },  notes = "根据产品需求保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/save")
    public ResponseEntity<SubStoryDTO> saveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        storyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求批量保存需求", tags = {"需求" },  notes = "根据产品需求批量保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domainlist=substoryMapping.toDomain(substorydtos);
        for(Story domain:domainlist){
             domain.setParent(story_id);
        }
        storyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/sendmessage")
    public ResponseEntity<SubStoryDTO> sendMessageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.sendMessage(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/sendmsgpreprocess")
    public ResponseEntity<SubStoryDTO> sendMsgPreProcessByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.sendMsgPreProcess(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/storyfavorites")
    public ResponseEntity<SubStoryDTO> storyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.storyFavorites(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/storynfavorites")
    public ResponseEntity<SubStoryDTO> storyNFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.storyNFavorites(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/syncfromibiz")
    public ResponseEntity<SubStoryDTO> syncFromIbizByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.syncFromIbiz(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/syncfromibizbatch")
    public ResponseEntity<Boolean> syncFromIbizByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.syncFromIbizBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求需求", tags = {"需求" },  notes = "根据产品需求需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/{substory_id}/unlinkstory")
    public ResponseEntity<SubStoryDTO> unlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("substory_id") Long substory_id, @RequestBody SubStoryDTO substorydto) {
        Story domain = substoryMapping.toDomain(substorydto);
        domain.setParent(story_id);
        domain = storyService.unlinkStory(domain) ;
        substorydto = substoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(substorydto);
    }
    @ApiOperation(value = "批量处理[根据产品需求需求]", tags = {"需求" },  notes = "批量处理[根据产品需求需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/substories/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<SubStoryDTO> substorydtos) {
        List<Story> domains = substoryMapping.toDomain(substorydtos);
        boolean result = storyService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	@ApiOperation(value = "根据产品需求获取指派给我的需求", tags = {"需求" } ,notes = "根据产品需求获取指派给我的需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchassignedtomystory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryAssignedToMyStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询指派给我的需求", tags = {"需求" } ,notes = "根据产品需求查询指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchassignedtomystory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryAssignedToMyStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取指派给我的需求（日历）", tags = {"需求" } ,notes = "根据产品需求获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchassignedtomystorycalendar")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryAssignedToMyStoryCalendarByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询指派给我的需求（日历）", tags = {"需求" } ,notes = "根据产品需求查询指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchassignedtomystorycalendar")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryAssignedToMyStoryCalendarByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchAssignedToMyStoryCalendar(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Bug相关需求", tags = {"需求" } ,notes = "根据产品需求获取Bug相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchbugstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBugStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBugStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Bug相关需求", tags = {"需求" } ,notes = "根据产品需求查询Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchbugstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBugStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBugStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "根据产品需求获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBuildLinkCompletedStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本关联已完成的需求（选择数据源）", tags = {"需求" } ,notes = "根据产品需求查询版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchbuildlinkcompletedstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBuildLinkCompletedStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkCompletedStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本可关联的需求（产品内）", tags = {"需求" } ,notes = "根据产品需求获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchbuildlinkablestories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBuildLinkableStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本可关联的需求（产品内）", tags = {"需求" } ,notes = "根据产品需求查询版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchbuildlinkablestories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBuildLinkableStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取获取版本相关需求", tags = {"需求" } ,notes = "根据产品需求获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchbuildstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryBuildStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询获取版本相关需求", tags = {"需求" } ,notes = "根据产品需求查询获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchbuildstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryBuildStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchBuildStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取通过模块查询", tags = {"需求" } ,notes = "根据产品需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchbymodule")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryByModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchByModule(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询通过模块查询", tags = {"需求" } ,notes = "根据产品需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchbymodule")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryByModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchByModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取相关用例需求", tags = {"需求" } ,notes = "根据产品需求获取相关用例需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchcasestory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryCaseStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchCaseStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询相关用例需求", tags = {"需求" } ,notes = "根据产品需求查询相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchcasestory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryCaseStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchCaseStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取DEFAULT", tags = {"需求" } ,notes = "根据产品需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchdefault")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchDefault(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询DEFAULT", tags = {"需求" } ,notes = "根据产品需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchdefault")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取ES批量的导入", tags = {"需求" } ,notes = "根据产品需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchesbulk")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchESBulk(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询ES批量的导入", tags = {"需求" } ,notes = "根据产品需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchesbulk")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取获取产品需求", tags = {"需求" } ,notes = "根据产品需求获取获取产品需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchgetproductstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryGetProductStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchGetProductStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询获取产品需求", tags = {"需求" } ,notes = "根据产品需求查询获取产品需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchgetproductstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryGetProductStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchGetProductStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取我代理的需求", tags = {"需求" } ,notes = "根据产品需求获取我代理的需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchmyagentstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryMyAgentStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询我代理的需求", tags = {"需求" } ,notes = "根据产品需求查询我代理的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchmyagentstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryMyAgentStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyAgentStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "根据产品需求获取所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchmycuropenedstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryMyCurOpenedStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询所创建需求数和对应的优先级及状态", tags = {"需求" } ,notes = "根据产品需求查询所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchmycuropenedstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryMyCurOpenedStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyCurOpenedStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取我的收藏", tags = {"需求" } ,notes = "根据产品需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchmyfavorites")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryMyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询我的收藏", tags = {"需求" } ,notes = "根据产品需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchmyfavorites")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryMyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取计划关联需求(去除已关联)", tags = {"需求" } ,notes = "根据产品需求获取计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchnotcurplanlinkstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryNotCurPlanLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询计划关联需求(去除已关联)", tags = {"需求" } ,notes = "根据产品需求查询计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchnotcurplanlinkstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryNotCurPlanLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchNotCurPlanLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取数据查询", tags = {"需求" } ,notes = "根据产品需求获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchparentdefault")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryParentDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询数据查询", tags = {"需求" } ,notes = "根据产品需求查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchparentdefault")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryParentDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取数据查询", tags = {"需求" } ,notes = "根据产品需求获取数据查询")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchparentdefaultq")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryParentDefaultQByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询数据查询", tags = {"需求" } ,notes = "根据产品需求查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchparentdefaultq")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryParentDefaultQByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchParentDefaultQ(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取项目关联需求", tags = {"需求" } ,notes = "根据产品需求获取项目关联需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchprojectlinkstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryProjectLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询项目关联需求", tags = {"需求" } ,notes = "根据产品需求查询项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchprojectlinkstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryProjectLinkStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectLinkStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取项目相关需求", tags = {"需求" } ,notes = "根据产品需求获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchprojectstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryProjectStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询项目相关需求", tags = {"需求" } ,notes = "根据产品需求查询项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchprojectstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryProjectStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchProjectStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本可关联的完成的需求", tags = {"需求" } ,notes = "根据产品需求获取版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchreleaselinkablestories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryReleaseLinkableStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本可关联的完成的需求", tags = {"需求" } ,notes = "根据产品需求查询版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchreleaselinkablestories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryReleaseLinkableStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseLinkableStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品需求获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchreleasestories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryReleaseStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品需求查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchreleasestories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryReleaseStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReleaseStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取通过模块查询", tags = {"需求" } ,notes = "根据产品需求获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/fetchreportstories")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryReportStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReportStories(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询通过模块查询", tags = {"需求" } ,notes = "根据产品需求查询通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchreportstories")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryReportStoriesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchReportStories(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品需求获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchstorychild")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryStoryChildByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryChild(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品需求查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchstorychild")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryStoryChildByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品需求获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchstoryrelated")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryStoryRelatedByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询获取产品发布相关需求", tags = {"需求" } ,notes = "根据产品需求查询获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchstoryrelated")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryStoryRelatedByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchStoryRelated(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取需求细分", tags = {"需求" } ,notes = "根据产品需求获取需求细分")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchsubstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStorySubStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchSubStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询需求细分", tags = {"需求" } ,notes = "根据产品需求查询需求细分")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchsubstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStorySubStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchSubStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取任务相关需求", tags = {"需求" } ,notes = "根据产品需求获取任务相关需求")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchtaskrelatedstory")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryTaskRelatedStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询任务相关需求", tags = {"需求" } ,notes = "根据产品需求查询任务相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchtaskrelatedstory")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryTaskRelatedStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchTaskRelatedStory(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取默认（全部数据）", tags = {"需求" } ,notes = "根据产品需求获取默认（全部数据）")
    @RequestMapping(method= RequestMethod.GET , value="/products/{product_id}/stories/{story_id}/substories/fetchview")
	public ResponseEntity<List<SubStoryDTO>> fetchSubStoryViewByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchView(context) ;
        List<SubStoryDTO> list = substoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询默认（全部数据）", tags = {"需求" } ,notes = "根据产品需求查询默认（全部数据）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/substories/searchview")
	public ResponseEntity<Page<SubStoryDTO>> searchSubStoryViewByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody StorySearchContext context) {
        context.setN_parent_eq(story_id);
        Page<Story> domains = storyService.searchView(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(substoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

