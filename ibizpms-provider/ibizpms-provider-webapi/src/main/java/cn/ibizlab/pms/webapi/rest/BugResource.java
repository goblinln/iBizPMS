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
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BugRuntime;

@Slf4j
@Api(tags = {"Bug" })
@RestController("WebApi-bug")
@RequestMapping("")
public class BugResource {

    @Autowired
    public IBugService bugService;

    @Autowired
    public BugRuntime bugRuntime;

    @Autowired
    @Lazy
    public BugMapping bugMapping;

    @PreAuthorize("@BugRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建Bug", tags = {"Bug" },  notes = "新建Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs")
    @Transactional
    public ResponseEntity<BugDTO> create(@Validated @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
		bugService.create(domain);
        if(!bugRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        BugDTO dto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "更新Bug", tags = {"Bug" },  notes = "更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}")
    @Transactional
    public ResponseEntity<BugDTO> update(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
		Bug domain  = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
		bugService.update(domain );
        if(!bugRuntime.test(bug_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		BugDTO dto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(bug_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "删除Bug", tags = {"Bug" },  notes = "删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("bug_id") Long bug_id) {
         return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "获取Bug", tags = {"Bug" },  notes = "获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}")
    public ResponseEntity<BugDTO> get(@PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(bug_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取Bug草稿", tags = {"Bug" },  notes = "获取Bug草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraft(BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'ACTIVATE')")
    @ApiOperation(value = "激活", tags = {"Bug" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activate(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.activate(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'ASSIGNTO')")
    @ApiOperation(value = "指派", tags = {"Bug" },  notes = "指派")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignTo(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @ApiOperation(value = "批量解除关联Bug", tags = {"Bug" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/batchunlinkbug")
    public ResponseEntity<BugDTO> batchUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.batchUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'FAVORITES')")
    @ApiOperation(value = "Bug收藏", tags = {"Bug" },  notes = "Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavorites(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.bugFavorites(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'NFAVORITES')")
    @ApiOperation(value = "取消收藏", tags = {"Bug" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavorites(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.bugNFavorites(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @ApiOperation(value = "版本批量解除关联Bug", tags = {"Bug" },  notes = "版本批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/buildbatchunlinkbug")
    public ResponseEntity<BugDTO> buildBatchUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.buildBatchUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'BUILDLINK')")
    @ApiOperation(value = "版本关联Bug", tags = {"Bug" },  notes = "版本关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/buildlinkbug")
    public ResponseEntity<BugDTO> buildLinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.buildLinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'BUILDLINK')")
    @ApiOperation(value = "版本解除关联Bug", tags = {"Bug" },  notes = "版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.buildUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查Bug", tags = {"Bug" },  notes = "检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody BugDTO bugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(bugMapping.toDomain(bugdto)));
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'CLOSE')")
    @ApiOperation(value = "关闭", tags = {"Bug" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/close")
    public ResponseEntity<BugDTO> close(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.close(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'CONFIRM')")
    @ApiOperation(value = "确认", tags = {"Bug" },  notes = "确认")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirm(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.confirm(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'PLANLINK')")
    @ApiOperation(value = "关联Bug", tags = {"Bug" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/linkbug")
    public ResponseEntity<BugDTO> linkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.linkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'RELEASELINK')")
    @ApiOperation(value = "批量解除关联Bug", tags = {"Bug" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaasebatchunlinkbug")
    public ResponseEntity<BugDTO> releaaseBatchUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaaseBatchUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'RELEASELINK')")
    @ApiOperation(value = "关联Bug（解决Bug）", tags = {"Bug" },  notes = "关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaselinkbugbybug")
    public ResponseEntity<BugDTO> releaseLinkBugbyBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseLinkBugbyBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'RELEASELINK')")
    @ApiOperation(value = "关联Bug（遗留Bug）", tags = {"Bug" },  notes = "关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseLinkBugbyLeftBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'RESOLVE')")
    @ApiOperation(value = "移除关联Bug（遗留Bug）", tags = {"Bug" },  notes = "移除关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseUnLinkBugbyLeftBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'RELEASELINK')")
    @ApiOperation(value = "解除关联Bug", tags = {"Bug" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'RESOLVE')")
    @ApiOperation(value = "解决", tags = {"Bug" },  notes = "解决")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolve(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.resolve(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @ApiOperation(value = "保存Bug", tags = {"Bug" },  notes = "保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/save")
    public ResponseEntity<BugDTO> save(@RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        bugService.save(domain);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "行为", tags = {"Bug" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/sendmessage")
    public ResponseEntity<BugDTO> sendMessage(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.sendMessage(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @ApiOperation(value = "发送消息前置处理", tags = {"Bug" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/sendmsgpreprocess")
    public ResponseEntity<BugDTO> sendMsgPreProcess(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.sendMsgPreProcess(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @ApiOperation(value = "TestScript", tags = {"Bug" },  notes = "TestScript")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/testscript")
    public ResponseEntity<BugDTO> testScript(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.testScript(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'TOSTORY')")
    @ApiOperation(value = "转需求", tags = {"Bug" },  notes = "转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/tostory")
    public ResponseEntity<BugDTO> toStory(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.toStory(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'PLANLINK')")
    @ApiOperation(value = "解除关联Bug", tags = {"Bug" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.unlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "更新需求版本", tags = {"Bug" },  notes = "更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/updatestoryversion")
    public ResponseEntity<BugDTO> updateStoryVersion(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.updateStoryVersion(domain);
        bugdto = bugMapping.toDto(domain);
        Map<String,Integer> opprivs = bugRuntime.getOPPrivs(domain.getId());
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }


    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取指派给我Bug", tags = {"Bug" } ,notes = "获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchassignedtomybug")
	public ResponseEntity<List<BugDTO>> fetchassignedtomybug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指派给我Bug", tags = {"Bug" } ,notes = "查询指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchassignedtomybug")
	public ResponseEntity<Page<BugDTO>> searchAssignedToMyBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取指派给我Bug（PC）", tags = {"Bug" } ,notes = "获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchassignedtomybugpc")
	public ResponseEntity<List<BugDTO>> fetchassignedtomybugpc(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指派给我Bug（PC）", tags = {"Bug" } ,notes = "查询指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchassignedtomybugpc")
	public ResponseEntity<Page<BugDTO>> searchAssignedToMyBugPc(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本关联bug(遗留的)", tags = {"Bug" } ,notes = "获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbugsbybuild")
	public ResponseEntity<List<BugDTO>> fetchbugsbybuild(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本关联bug(遗留的)", tags = {"Bug" } ,notes = "查询版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbugsbybuild")
	public ResponseEntity<Page<BugDTO>> searchBugsByBuild(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildbugs")
	public ResponseEntity<List<BugDTO>> fetchbuildbugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildbugs")
	public ResponseEntity<Page<BugDTO>> searchBuildBugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<BugDTO>> fetchbuildlinkresolvedbugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "查询版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildlinkresolvedbugs")
	public ResponseEntity<Page<BugDTO>> searchBuildLinkResolvedBugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildopenbugs")
	public ResponseEntity<List<BugDTO>> fetchbuildopenbugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildopenbugs")
	public ResponseEntity<Page<BugDTO>> searchBuildOpenBugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug", tags = {"Bug" } ,notes = "获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebug")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug", tags = {"Bug" } ,notes = "查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebug")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug", tags = {"Bug" } ,notes = "获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugmodule")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugmodule(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug", tags = {"Bug" } ,notes = "查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugmodule")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugModule(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugmodule_project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugmodule_project")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugModule_Project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugopenedby")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugopenedby(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "查询Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugopenedby")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugOpenedBy(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugopenedby_project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugopenedby_project")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugOpenedBy_Project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugres")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugres(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "查询Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugres")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugRES(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugresolvedby(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "查询Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugresolvedby")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugRESOLVEDBY(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugresolvedby_project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "查询Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugresolvedby_project")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugRESOLVEDBY_Project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugresolution_project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "查询Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugresolution_project")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugResolution_Project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugseverity_project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "查询Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugseverity_project")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugSeverity_Project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugstatus_project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "查询Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugstatus_project")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugStatus_Project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugtype_project")
	public ResponseEntity<List<BugDTO>> fetchbuildproducebugtype_project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "查询Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchbuildproducebugtype_project")
	public ResponseEntity<Page<BugDTO>> searchBuildProduceBugType_Project(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前用户解决的Bug", tags = {"Bug" } ,notes = "获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchcuruserresolve")
	public ResponseEntity<List<BugDTO>> fetchcuruserresolve(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询当前用户解决的Bug", tags = {"Bug" } ,notes = "查询当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchcuruserresolve")
	public ResponseEntity<Page<BugDTO>> searchCurUserResolve(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"Bug" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchdefault(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"Bug" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchdefault")
	public ResponseEntity<Page<BugDTO>> searchDefault(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"Bug" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchesbulk")
	public ResponseEntity<List<BugDTO>> fetchesbulk(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchESBulk(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询ES批量的导入", tags = {"Bug" } ,notes = "查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchesbulk")
	public ResponseEntity<Page<BugDTO>> searchESBulk(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我代理的Bug", tags = {"Bug" } ,notes = "获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchmyagentbug")
	public ResponseEntity<List<BugDTO>> fetchmyagentbug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我代理的Bug", tags = {"Bug" } ,notes = "查询我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchmyagentbug")
	public ResponseEntity<Page<BugDTO>> searchMyAgentBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取累计创建的Bug数", tags = {"Bug" } ,notes = "获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchmycuropenedbug")
	public ResponseEntity<List<BugDTO>> fetchmycuropenedbug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询累计创建的Bug数", tags = {"Bug" } ,notes = "查询累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchmycuropenedbug")
	public ResponseEntity<Page<BugDTO>> searchMyCurOpenedBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"Bug" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchmyfavorites")
	public ResponseEntity<List<BugDTO>> fetchmyfavorites(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"Bug" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchmyfavorites")
	public ResponseEntity<Page<BugDTO>> searchMyFavorites(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchnotcurplanlinkbug")
	public ResponseEntity<List<BugDTO>> fetchnotcurplanlinkbug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "查询计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchnotcurplanlinkbug")
	public ResponseEntity<Page<BugDTO>> searchNotCurPlanLinkBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取遗留得Bug(项目)", tags = {"Bug" } ,notes = "获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchprojectbugs")
	public ResponseEntity<List<BugDTO>> fetchprojectbugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询遗留得Bug(项目)", tags = {"Bug" } ,notes = "查询遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchprojectbugs")
	public ResponseEntity<Page<BugDTO>> searchProjectBugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleasebugs")
	public ResponseEntity<List<BugDTO>> fetchreleasebugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchreleasebugs")
	public ResponseEntity<Page<BugDTO>> searchReleaseBugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleaseleftbugs")
	public ResponseEntity<List<BugDTO>> fetchreleaseleftbugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchreleaseleftbugs")
	public ResponseEntity<Page<BugDTO>> searchReleaseLeftBugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleaselinkableleftbug")
	public ResponseEntity<List<BugDTO>> fetchreleaselinkableleftbug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "查询发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchreleaselinkableleftbug")
	public ResponseEntity<Page<BugDTO>> searchReleaseLinkableLeftBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<BugDTO>> fetchreleaselinkableresolvedbug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "查询发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchreleaselinkableresolvedbug")
	public ResponseEntity<Page<BugDTO>> searchReleaseLinkableResolvedBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布关联Bug（未解决）", tags = {"Bug" } ,notes = "获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreportbugs")
	public ResponseEntity<List<BugDTO>> fetchreportbugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReportBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询发布关联Bug（未解决）", tags = {"Bug" } ,notes = "查询发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchreportbugs")
	public ResponseEntity<Page<BugDTO>> searchReportBugs(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchReportBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取需求来源Bug", tags = {"Bug" } ,notes = "获取需求来源Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchstoryformbug")
	public ResponseEntity<List<BugDTO>> fetchstoryformbug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchStoryFormBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询需求来源Bug", tags = {"Bug" } ,notes = "查询需求来源Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchstoryformbug")
	public ResponseEntity<Page<BugDTO>> searchStoryFormBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchStoryFormBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取任务相关bug", tags = {"Bug" } ,notes = "获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchtaskrelatedbug")
	public ResponseEntity<List<BugDTO>> fetchtaskrelatedbug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "查询任务相关bug", tags = {"Bug" } ,notes = "查询任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/searchtaskrelatedbug")
	public ResponseEntity<Page<BugDTO>> searchTaskRelatedBug(@RequestBody BugSearchContext context) {
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/{action}")
    public ResponseEntity<BugDTO> dynamicCall(@PathVariable("bug_id") Long bug_id , @PathVariable("action") String action , @RequestBody BugDTO bugdto) {
        Bug domain = bugService.dynamicCall(bug_id, action, bugMapping.toDomain(bugdto));
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品建立Bug", tags = {"Bug" },  notes = "根据产品建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs")
    public ResponseEntity<BugDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品更新Bug", tags = {"Bug" },  notes = "根据产品更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品删除Bug", tags = {"Bug" },  notes = "根据产品删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取Bug", tags = {"Bug" },  notes = "根据产品获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品获取Bug草稿", tags = {"Bug" },  notes = "根据产品获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品激活Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品指派Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @ApiOperation(value = "根据产品批量解除关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/batchunlinkbug")
    public ResponseEntity<BugDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.batchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品Bug收藏Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品取消收藏Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @ApiOperation(value = "根据产品版本批量解除关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/buildbatchunlinkbug")
    public ResponseEntity<BugDTO> buildBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.buildBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品版本关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/buildlinkbug")
    public ResponseEntity<BugDTO> buildLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.buildLinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品版本解除关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品检查Bug", tags = {"Bug" },  notes = "根据产品检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(bugMapping.toDomain(bugdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品关闭Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/close")
    public ResponseEntity<BugDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.close(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品确认Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/linkbug")
    public ResponseEntity<BugDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.linkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品批量解除关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaasebatchunlinkbug")
    public ResponseEntity<BugDTO> releaaseBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.releaaseBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品关联Bug（解决Bug）Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaselinkbugbybug")
    public ResponseEntity<BugDTO> releaseLinkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.releaseLinkBugbyBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品移除关联Bug（遗留Bug）Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseUnLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品解除关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品解决Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @ApiOperation(value = "根据产品保存Bug", tags = {"Bug" },  notes = "根据产品保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/save")
    public ResponseEntity<BugDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品行为Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/sendmessage")
    public ResponseEntity<BugDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.sendMessage(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @ApiOperation(value = "根据产品发送消息前置处理Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/sendmsgpreprocess")
    public ResponseEntity<BugDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.sendMsgPreProcess(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @ApiOperation(value = "根据产品TestScriptBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/testscript")
    public ResponseEntity<BugDTO> testScriptByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.testScript(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品转需求Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/tostory")
    public ResponseEntity<BugDTO> toStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.toStory(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品解除关联BugBug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUGMANAGE')")
    @ApiOperation(value = "根据产品更新需求版本Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/updatestoryversion")
    public ResponseEntity<BugDTO> updateStoryVersionByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.updateStoryVersion(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我Bug", tags = {"Bug" } ,notes = "根据产品获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchassignedtomybug")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询指派给我Bug", tags = {"Bug" } ,notes = "根据产品查询指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchassignedtomybug")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据产品获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchassignedtomybugpc")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据产品查询指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchassignedtomybugpc")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugPcByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据产品获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbugsbybuild")
	public ResponseEntity<List<BugDTO>> fetchBugBugsByBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据产品查询版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbugsbybuild")
	public ResponseEntity<Page<BugDTO>> searchBugBugsByBuildByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据产品获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildLinkResolvedBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据产品查询版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildlinkresolvedbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildLinkResolvedBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildopenbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildOpenBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildopenbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildOpenBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebug")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebug")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugmodule")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugmodule")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModuleByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModule_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugmodule_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModule_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugopenedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedByByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugopenedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedByByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedBy_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugopenedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedBy_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugres")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugres")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBYByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugresolvedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBYByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBY_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugresolvedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBY_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugResolution_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugresolution_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugResolution_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugSeverity_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugseverity_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugSeverity_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugStatus_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugstatus_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugStatus_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据产品获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchbuildproducebugtype_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugType_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugtype_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugType_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取当前用户解决的Bug", tags = {"Bug" } ,notes = "根据产品获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchcuruserresolve")
	public ResponseEntity<List<BugDTO>> fetchBugCurUserResolveByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询当前用户解决的Bug", tags = {"Bug" } ,notes = "根据产品查询当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchcuruserresolve")
	public ResponseEntity<Page<BugDTO>> searchBugCurUserResolveByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"Bug" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchBugDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"Bug" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchdefault")
	public ResponseEntity<Page<BugDTO>> searchBugDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"Bug" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchesbulk")
	public ResponseEntity<List<BugDTO>> fetchBugESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询ES批量的导入", tags = {"Bug" } ,notes = "根据产品查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchesbulk")
	public ResponseEntity<Page<BugDTO>> searchBugESBulkByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我代理的Bug", tags = {"Bug" } ,notes = "根据产品获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchmyagentbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyAgentBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询我代理的Bug", tags = {"Bug" } ,notes = "根据产品查询我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchmyagentbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyAgentBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的Bug数", tags = {"Bug" } ,notes = "根据产品获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchmycuropenedbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyCurOpenedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询累计创建的Bug数", tags = {"Bug" } ,notes = "根据产品查询累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchmycuropenedbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyCurOpenedBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"Bug" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchmyfavorites")
	public ResponseEntity<List<BugDTO>> fetchBugMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询我的收藏", tags = {"Bug" } ,notes = "根据产品查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchmyfavorites")
	public ResponseEntity<Page<BugDTO>> searchBugMyFavoritesByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据产品获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchnotcurplanlinkbug")
	public ResponseEntity<List<BugDTO>> fetchBugNotCurPlanLinkBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据产品查询计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchnotcurplanlinkbug")
	public ResponseEntity<Page<BugDTO>> searchBugNotCurPlanLinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据产品获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchprojectbugs")
	public ResponseEntity<List<BugDTO>> fetchBugProjectBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据产品查询遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchprojectbugs")
	public ResponseEntity<Page<BugDTO>> searchBugProjectBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchreleasebugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleasebugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchreleaseleftbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLeftBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleaseleftbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLeftBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据产品获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchreleaselinkableleftbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableLeftBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据产品查询发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleaselinkableleftbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableLeftBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据产品获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableResolvedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleaselinkableresolvedbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableResolvedBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据产品获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchreportbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReportBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据产品查询发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreportbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReportBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品获取需求来源Bug", tags = {"Bug" } ,notes = "根据产品获取需求来源Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchstoryformbug")
	public ResponseEntity<List<BugDTO>> fetchBugStoryFormBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchStoryFormBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品查询需求来源Bug", tags = {"Bug" } ,notes = "根据产品查询需求来源Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchstoryformbug")
	public ResponseEntity<Page<BugDTO>> searchBugStoryFormBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchStoryFormBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务相关bug", tags = {"Bug" } ,notes = "根据产品获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/fetchtaskrelatedbug")
	public ResponseEntity<List<BugDTO>> fetchBugTaskRelatedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询任务相关bug", tags = {"Bug" } ,notes = "根据产品查询任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchtaskrelatedbug")
	public ResponseEntity<Page<BugDTO>> searchBugTaskRelatedBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

