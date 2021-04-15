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
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建Bug", tags = {"Bug" },  notes = "批量新建Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<BugDTO> bugdtos) {
        bugService.createBatch(bugMapping.toDomain(bugdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新Bug", tags = {"Bug" },  notes = "批量更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<BugDTO> bugdtos) {
        bugService.updateBatch(bugMapping.toDomain(bugdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "删除Bug", tags = {"Bug" },  notes = "删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("bug_id") Long bug_id) {
         return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("@BugRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除Bug", tags = {"Bug" },  notes = "批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "获取Bug", tags = {"Bug" },  notes = "获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}")
    public ResponseEntity<BugDTO> get(@PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取Bug草稿", tags = {"Bug" },  notes = "获取Bug草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraft(BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @ApiOperation(value = "激活", tags = {"Bug" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activate(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.activate(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[激活]", tags = {"Bug" },  notes = "批量处理[激活]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/activatebatch")
    public ResponseEntity<Boolean> activateBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "指派", tags = {"Bug" },  notes = "指派")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignTo(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[指派]", tags = {"Bug" },  notes = "批量处理[指派]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/assigntobatch")
    public ResponseEntity<Boolean> assignToBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量解除关联Bug", tags = {"Bug" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/batchunlinkbug")
    public ResponseEntity<BugDTO> batchUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.batchUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[批量解除关联Bug]", tags = {"Bug" },  notes = "批量处理[批量解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Bug收藏", tags = {"Bug" },  notes = "Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavorites(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.bugFavorites(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @ApiOperation(value = "取消收藏", tags = {"Bug" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavorites(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.bugNFavorites(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @ApiOperation(value = "版本批量解除关联Bug", tags = {"Bug" },  notes = "版本批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/buildbatchunlinkbug")
    public ResponseEntity<BugDTO> buildBatchUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.buildBatchUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[版本批量解除关联Bug]", tags = {"Bug" },  notes = "批量处理[版本批量解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/buildbatchunlinkbugbatch")
    public ResponseEntity<Boolean> buildBatchUnlinkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "版本关联Bug", tags = {"Bug" },  notes = "版本关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/buildlinkbug")
    public ResponseEntity<BugDTO> buildLinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.buildLinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[版本关联Bug]", tags = {"Bug" },  notes = "批量处理[版本关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/buildlinkbugbatch")
    public ResponseEntity<Boolean> buildLinkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildLinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "版本解除关联Bug", tags = {"Bug" },  notes = "版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.buildUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[版本解除关联Bug]", tags = {"Bug" },  notes = "批量处理[版本解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/buildunlinkbugbatch")
    public ResponseEntity<Boolean> buildUnlinkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "检查Bug", tags = {"Bug" },  notes = "检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody BugDTO bugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(bugMapping.toDomain(bugdto)));
    }

    @ApiOperation(value = "关闭", tags = {"Bug" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/close")
    public ResponseEntity<BugDTO> close(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.close(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[关闭]", tags = {"Bug" },  notes = "批量处理[关闭]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/closebatch")
    public ResponseEntity<Boolean> closeBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "确认", tags = {"Bug" },  notes = "确认")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirm(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.confirm(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[确认]", tags = {"Bug" },  notes = "批量处理[确认]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/confirmbatch")
    public ResponseEntity<Boolean> confirmBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.confirmBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "关联Bug", tags = {"Bug" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/linkbug")
    public ResponseEntity<BugDTO> linkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.linkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[关联Bug]", tags = {"Bug" },  notes = "批量处理[关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/linkbugbatch")
    public ResponseEntity<Boolean> linkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "批量解除关联Bug", tags = {"Bug" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaasebatchunlinkbug")
    public ResponseEntity<BugDTO> releaaseBatchUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaaseBatchUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[批量解除关联Bug]", tags = {"Bug" },  notes = "批量处理[批量解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/releaasebatchunlinkbugbatch")
    public ResponseEntity<Boolean> releaaseBatchUnlinkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaaseBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "关联Bug（解决Bug）", tags = {"Bug" },  notes = "关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaselinkbugbybug")
    public ResponseEntity<BugDTO> releaseLinkBugbyBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseLinkBugbyBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[关联Bug（解决Bug）]", tags = {"Bug" },  notes = "批量处理[关联Bug（解决Bug）]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/releaselinkbugbybugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "关联Bug（遗留Bug）", tags = {"Bug" },  notes = "关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseLinkBugbyLeftBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[关联Bug（遗留Bug）]", tags = {"Bug" },  notes = "批量处理[关联Bug（遗留Bug）]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/releaselinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyLeftBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "移除关联Bug（遗留Bug）", tags = {"Bug" },  notes = "移除关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseUnLinkBugbyLeftBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[移除关联Bug（遗留Bug）]", tags = {"Bug" },  notes = "批量处理[移除关联Bug（遗留Bug）]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/releaseunlinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseUnLinkBugbyLeftBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "解除关联Bug", tags = {"Bug" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.releaseUnlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[解除关联Bug]", tags = {"Bug" },  notes = "批量处理[解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/releaseunlinkbugbatch")
    public ResponseEntity<Boolean> releaseUnlinkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "解决", tags = {"Bug" },  notes = "解决")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolve(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.resolve(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[解决]", tags = {"Bug" },  notes = "批量处理[解决]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/resolvebatch")
    public ResponseEntity<Boolean> resolveBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.resolveBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存Bug", tags = {"Bug" },  notes = "保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/save")
    public ResponseEntity<BugDTO> save(@RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存Bug", tags = {"Bug" },  notes = "批量保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<BugDTO> bugdtos) {
        bugService.saveBatch(bugMapping.toDomain(bugdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "行为", tags = {"Bug" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/sendmessage")
    public ResponseEntity<BugDTO> sendMessage(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.sendMessage(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[行为]", tags = {"Bug" },  notes = "批量处理[行为]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送消息前置处理", tags = {"Bug" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/sendmsgpreprocess")
    public ResponseEntity<BugDTO> sendMsgPreProcess(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.sendMsgPreProcess(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[发送消息前置处理]", tags = {"Bug" },  notes = "批量处理[发送消息前置处理]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "转需求", tags = {"Bug" },  notes = "转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/tostory")
    public ResponseEntity<BugDTO> toStory(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.toStory(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[转需求]", tags = {"Bug" },  notes = "批量处理[转需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/tostorybatch")
    public ResponseEntity<Boolean> toStoryBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.toStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "解除关联Bug", tags = {"Bug" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBug(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.unlinkBug(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[解除关联Bug]", tags = {"Bug" },  notes = "批量处理[解除关联Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugBatch(@RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "更新需求版本", tags = {"Bug" },  notes = "更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/updatestoryversion")
    public ResponseEntity<BugDTO> updateStoryVersion(@PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setId(bug_id);
        domain = bugService.updateStoryVersion(domain);
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取指派给我Bug", tags = {"Bug" } ,notes = "获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchassignedtomybug")
	public ResponseEntity<List<BugDTO>> fetchAssignedToMyBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取指派给我Bug（PC）", tags = {"Bug" } ,notes = "获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchassignedtomybugpc")
	public ResponseEntity<List<BugDTO>> fetchAssignedToMyBugPc(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本关联bug(遗留的)", tags = {"Bug" } ,notes = "获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbugsbybuild")
	public ResponseEntity<List<BugDTO>> fetchBugsByBuild(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildbugs")
	public ResponseEntity<List<BugDTO>> fetchBuildBugs(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<BugDTO>> fetchBuildLinkResolvedBugs(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildopenbugs")
	public ResponseEntity<List<BugDTO>> fetchBuildOpenBugs(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug", tags = {"Bug" } ,notes = "获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebug")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug", tags = {"Bug" } ,notes = "获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugmodule")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugModule(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugModule_Project(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugopenedby")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugOpenedBy(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugOpenedBy_Project(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugres")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugRES(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugRESOLVEDBY(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugRESOLVEDBY_Project(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugResolution_Project(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugSeverity_Project(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugStatus_Project(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchbuildproducebugtype_project")
	public ResponseEntity<List<BugDTO>> fetchBuildProduceBugType_Project(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前用户解决的Bug", tags = {"Bug" } ,notes = "获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchcuruserresolve")
	public ResponseEntity<List<BugDTO>> fetchCurUserResolve(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"Bug" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchDefault(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"Bug" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchesbulk")
	public ResponseEntity<List<BugDTO>> fetchESBulk(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我代理的Bug", tags = {"Bug" } ,notes = "获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchmyagentbug")
	public ResponseEntity<List<BugDTO>> fetchMyAgentBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取累计创建的Bug数", tags = {"Bug" } ,notes = "获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchmycuropenedbug")
	public ResponseEntity<List<BugDTO>> fetchMyCurOpenedBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"Bug" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchmyfavorites")
	public ResponseEntity<List<BugDTO>> fetchMyFavorites(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchnotcurplanlinkbug")
	public ResponseEntity<List<BugDTO>> fetchNotCurPlanLinkBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取遗留得Bug(项目)", tags = {"Bug" } ,notes = "获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchprojectbugs")
	public ResponseEntity<List<BugDTO>> fetchProjectBugs(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleasebugs")
	public ResponseEntity<List<BugDTO>> fetchReleaseBugs(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleaseleftbugs")
	public ResponseEntity<List<BugDTO>> fetchReleaseLeftBugs(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleaselinkableleftbug")
	public ResponseEntity<List<BugDTO>> fetchReleaseLinkableLeftBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<BugDTO>> fetchReleaseLinkableResolvedBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取发布关联Bug（未解决）", tags = {"Bug" } ,notes = "获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchreportbugs")
	public ResponseEntity<List<BugDTO>> fetchReportBugs(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
        Page<Bug> domains = bugService.searchReportBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "获取任务相关bug", tags = {"Bug" } ,notes = "获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/fetchtaskrelatedbug")
	public ResponseEntity<List<BugDTO>> fetchTaskRelatedBug(@RequestBody BugSearchContext context) {
        bugRuntime.addAuthorityConditions(context,"READ");
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
        bugRuntime.addAuthorityConditions(context,"READ");
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
    @ApiOperation(value = "根据产品建立Bug", tags = {"Bug" },  notes = "根据产品建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs")
    public ResponseEntity<BugDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品批量建立Bug", tags = {"Bug" },  notes = "根据产品批量建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setProduct(product_id);
        }
        bugService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
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

    @ApiOperation(value = "根据产品批量更新Bug", tags = {"Bug" },  notes = "根据产品批量更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/batch")
    public ResponseEntity<Boolean> updateBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setProduct(product_id);
        }
        bugService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品删除Bug", tags = {"Bug" },  notes = "根据产品删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @ApiOperation(value = "根据产品批量删除Bug", tags = {"Bug" },  notes = "根据产品批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品获取Bug", tags = {"Bug" },  notes = "根据产品获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品获取Bug草稿", tags = {"Bug" },  notes = "根据产品获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/activatebatch")
    public ResponseEntity<Boolean> activateByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/assigntobatch")
    public ResponseEntity<Boolean> assignToByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/batchunlinkbug")
    public ResponseEntity<BugDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.batchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/buildbatchunlinkbug")
    public ResponseEntity<BugDTO> buildBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.buildBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/buildbatchunlinkbugbatch")
    public ResponseEntity<Boolean> buildBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/buildlinkbug")
    public ResponseEntity<BugDTO> buildLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.buildLinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/buildlinkbugbatch")
    public ResponseEntity<Boolean> buildLinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildLinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/buildunlinkbugbatch")
    public ResponseEntity<Boolean> buildUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品检查Bug", tags = {"Bug" },  notes = "根据产品检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(bugMapping.toDomain(bugdto)));
    }

    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/close")
    public ResponseEntity<BugDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.close(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/closebatch")
    public ResponseEntity<Boolean> closeByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/confirmbatch")
    public ResponseEntity<Boolean> confirmByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.confirmBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/linkbug")
    public ResponseEntity<BugDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.linkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaasebatchunlinkbug")
    public ResponseEntity<BugDTO> releaaseBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.releaaseBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/releaasebatchunlinkbugbatch")
    public ResponseEntity<Boolean> releaaseBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaaseBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaselinkbugbybug")
    public ResponseEntity<BugDTO> releaseLinkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.releaseLinkBugbyBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/releaselinkbugbybugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/releaselinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseUnLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/releaseunlinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseUnLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/releaseunlinkbugbatch")
    public ResponseEntity<Boolean> releaseUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/resolvebatch")
    public ResponseEntity<Boolean> resolveByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.resolveBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品保存Bug", tags = {"Bug" },  notes = "根据产品保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/save")
    public ResponseEntity<BugDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品批量保存Bug", tags = {"Bug" },  notes = "根据产品批量保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
             domain.setProduct(product_id);
        }
        bugService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/sendmessage")
    public ResponseEntity<BugDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.sendMessage(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/sendmsgpreprocess")
    public ResponseEntity<BugDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.sendMsgPreProcess(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/tostory")
    public ResponseEntity<BugDTO> toStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.toStory(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/tostorybatch")
    public ResponseEntity<Boolean> toStoryByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.toStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品Bug]", tags = {"Bug" },  notes = "批量处理[根据产品Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug", tags = {"Bug" },  notes = "根据产品Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/updatestoryversion")
    public ResponseEntity<BugDTO> updateStoryVersionByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain = bugService.updateStoryVersion(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
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

	@ApiOperation(value = "根据产品查询指派给我Bug", tags = {"Bug" } ,notes = "根据产品查询指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchassignedtomybug")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据产品查询指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchassignedtomybugpc")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugPcByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据产品查询版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbugsbybuild")
	public ResponseEntity<Page<BugDTO>> searchBugBugsByBuildByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据产品查询版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildlinkresolvedbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildLinkResolvedBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildopenbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildOpenBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebug")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugmodule")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModuleByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugmodule_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModule_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugopenedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedByByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugopenedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedBy_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugres")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugresolvedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBYByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugresolvedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBY_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugresolution_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugResolution_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugseverity_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugSeverity_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugstatus_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugStatus_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据产品查询Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchbuildproducebugtype_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugType_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询当前用户解决的Bug", tags = {"Bug" } ,notes = "根据产品查询当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchcuruserresolve")
	public ResponseEntity<Page<BugDTO>> searchBugCurUserResolveByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"Bug" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchdefault")
	public ResponseEntity<Page<BugDTO>> searchBugDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询ES批量的导入", tags = {"Bug" } ,notes = "根据产品查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchesbulk")
	public ResponseEntity<Page<BugDTO>> searchBugESBulkByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询我代理的Bug", tags = {"Bug" } ,notes = "根据产品查询我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchmyagentbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyAgentBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询累计创建的Bug数", tags = {"Bug" } ,notes = "根据产品查询累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchmycuropenedbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyCurOpenedBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询我的收藏", tags = {"Bug" } ,notes = "根据产品查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchmyfavorites")
	public ResponseEntity<Page<BugDTO>> searchBugMyFavoritesByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据产品查询计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchnotcurplanlinkbug")
	public ResponseEntity<Page<BugDTO>> searchBugNotCurPlanLinkBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据产品查询遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchprojectbugs")
	public ResponseEntity<Page<BugDTO>> searchBugProjectBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleasebugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleaseleftbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLeftBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据产品查询发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleaselinkableleftbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableLeftBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据产品查询发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreleaselinkableresolvedbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableResolvedBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据产品查询发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchreportbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReportBugsByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
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

	@ApiOperation(value = "根据产品查询任务相关bug", tags = {"Bug" } ,notes = "根据产品查询任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/searchtaskrelatedbug")
	public ResponseEntity<Page<BugDTO>> searchBugTaskRelatedBugByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据需求建立Bug", tags = {"Bug" },  notes = "根据需求建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs")
    public ResponseEntity<BugDTO> createByStory(@PathVariable("story_id") Long story_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求批量建立Bug", tags = {"Bug" },  notes = "根据需求批量建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/batch")
    public ResponseEntity<Boolean> createBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setStory(story_id);
        }
        bugService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @ApiOperation(value = "根据需求更新Bug", tags = {"Bug" },  notes = "根据需求更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求批量更新Bug", tags = {"Bug" },  notes = "根据需求批量更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/bugs/batch")
    public ResponseEntity<Boolean> updateBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setStory(story_id);
        }
        bugService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求删除Bug", tags = {"Bug" },  notes = "根据需求删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @ApiOperation(value = "根据需求批量删除Bug", tags = {"Bug" },  notes = "根据需求批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByStory(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求获取Bug", tags = {"Bug" },  notes = "根据需求获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求获取Bug草稿", tags = {"Bug" },  notes = "根据需求获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByStory(@PathVariable("story_id") Long story_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/activatebatch")
    public ResponseEntity<Boolean> activateByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/assigntobatch")
    public ResponseEntity<Boolean> assignToByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/batchunlinkbug")
    public ResponseEntity<BugDTO> batchUnlinkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.batchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/buildbatchunlinkbug")
    public ResponseEntity<BugDTO> buildBatchUnlinkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.buildBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/buildbatchunlinkbugbatch")
    public ResponseEntity<Boolean> buildBatchUnlinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/buildlinkbug")
    public ResponseEntity<BugDTO> buildLinkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.buildLinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/buildlinkbugbatch")
    public ResponseEntity<Boolean> buildLinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildLinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/buildunlinkbugbatch")
    public ResponseEntity<Boolean> buildUnlinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求检查Bug", tags = {"Bug" },  notes = "根据需求检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/checkkey")
    public ResponseEntity<Boolean> checkKeyByStory(@PathVariable("story_id") Long story_id, @RequestBody BugDTO bugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(bugMapping.toDomain(bugdto)));
    }

    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/close")
    public ResponseEntity<BugDTO> closeByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.close(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/closebatch")
    public ResponseEntity<Boolean> closeByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/confirmbatch")
    public ResponseEntity<Boolean> confirmByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.confirmBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/linkbug")
    public ResponseEntity<BugDTO> linkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.linkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/releaasebatchunlinkbug")
    public ResponseEntity<BugDTO> releaaseBatchUnlinkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaaseBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/releaasebatchunlinkbugbatch")
    public ResponseEntity<Boolean> releaaseBatchUnlinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaaseBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/releaselinkbugbybug")
    public ResponseEntity<BugDTO> releaseLinkBugbyBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseLinkBugbyBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/releaselinkbugbybugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseLinkBugbyLeftBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/releaselinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyLeftBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseUnLinkBugbyLeftBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/releaseunlinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseUnLinkBugbyLeftBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/releaseunlinkbugbatch")
    public ResponseEntity<Boolean> releaseUnlinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/resolvebatch")
    public ResponseEntity<Boolean> resolveByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.resolveBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求保存Bug", tags = {"Bug" },  notes = "根据需求保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/save")
    public ResponseEntity<BugDTO> saveByStory(@PathVariable("story_id") Long story_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求批量保存Bug", tags = {"Bug" },  notes = "根据需求批量保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/savebatch")
    public ResponseEntity<Boolean> saveBatchByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
             domain.setStory(story_id);
        }
        bugService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/sendmessage")
    public ResponseEntity<BugDTO> sendMessageByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.sendMessage(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/sendmsgpreprocess")
    public ResponseEntity<BugDTO> sendMsgPreProcessByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.sendMsgPreProcess(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/tostory")
    public ResponseEntity<BugDTO> toStoryByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.toStory(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/tostorybatch")
    public ResponseEntity<Boolean> toStoryByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.toStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据需求Bug]", tags = {"Bug" },  notes = "批量处理[根据需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/bugs/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求Bug", tags = {"Bug" },  notes = "根据需求Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/bugs/{bug_id}/updatestoryversion")
    public ResponseEntity<BugDTO> updateStoryVersionByStory(@PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.updateStoryVersion(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
	@ApiOperation(value = "根据需求获取指派给我Bug", tags = {"Bug" } ,notes = "根据需求获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchassignedtomybug")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询指派给我Bug", tags = {"Bug" } ,notes = "根据需求查询指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchassignedtomybug")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据需求获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchassignedtomybugpc")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugPcByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据需求查询指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchassignedtomybugpc")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugPcByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据需求获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbugsbybuild")
	public ResponseEntity<List<BugDTO>> fetchBugBugsByBuildByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据需求查询版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbugsbybuild")
	public ResponseEntity<Page<BugDTO>> searchBugBugsByBuildByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildBugsByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildBugsByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据需求获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildLinkResolvedBugsByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据需求查询版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildlinkresolvedbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildLinkResolvedBugsByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildopenbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildOpenBugsByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildopenbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildOpenBugsByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebug")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebug")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugmodule")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModuleByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugmodule")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModuleByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModule_ProjectByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugmodule_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModule_ProjectByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugopenedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedByByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugopenedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedByByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedBy_ProjectByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugopenedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedBy_ProjectByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugres")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugres")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBYByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugresolvedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBYByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBY_ProjectByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugresolvedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBY_ProjectByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugResolution_ProjectByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugresolution_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugResolution_ProjectByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugSeverity_ProjectByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugseverity_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugSeverity_ProjectByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugStatus_ProjectByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugstatus_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugStatus_ProjectByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据需求获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchbuildproducebugtype_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugType_ProjectByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据需求查询Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchbuildproducebugtype_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugType_ProjectByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取当前用户解决的Bug", tags = {"Bug" } ,notes = "根据需求获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchcuruserresolve")
	public ResponseEntity<List<BugDTO>> fetchBugCurUserResolveByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询当前用户解决的Bug", tags = {"Bug" } ,notes = "根据需求查询当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchcuruserresolve")
	public ResponseEntity<Page<BugDTO>> searchBugCurUserResolveByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取DEFAULT", tags = {"Bug" } ,notes = "根据需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchBugDefaultByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询DEFAULT", tags = {"Bug" } ,notes = "根据需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchdefault")
	public ResponseEntity<Page<BugDTO>> searchBugDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取ES批量的导入", tags = {"Bug" } ,notes = "根据需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchesbulk")
	public ResponseEntity<List<BugDTO>> fetchBugESBulkByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询ES批量的导入", tags = {"Bug" } ,notes = "根据需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchesbulk")
	public ResponseEntity<Page<BugDTO>> searchBugESBulkByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取我代理的Bug", tags = {"Bug" } ,notes = "根据需求获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchmyagentbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyAgentBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询我代理的Bug", tags = {"Bug" } ,notes = "根据需求查询我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchmyagentbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyAgentBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取累计创建的Bug数", tags = {"Bug" } ,notes = "根据需求获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchmycuropenedbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyCurOpenedBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询累计创建的Bug数", tags = {"Bug" } ,notes = "根据需求查询累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchmycuropenedbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyCurOpenedBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取我的收藏", tags = {"Bug" } ,notes = "根据需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchmyfavorites")
	public ResponseEntity<List<BugDTO>> fetchBugMyFavoritesByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询我的收藏", tags = {"Bug" } ,notes = "根据需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchmyfavorites")
	public ResponseEntity<Page<BugDTO>> searchBugMyFavoritesByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据需求获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchnotcurplanlinkbug")
	public ResponseEntity<List<BugDTO>> fetchBugNotCurPlanLinkBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据需求查询计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchnotcurplanlinkbug")
	public ResponseEntity<Page<BugDTO>> searchBugNotCurPlanLinkBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据需求获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchprojectbugs")
	public ResponseEntity<List<BugDTO>> fetchBugProjectBugsByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据需求查询遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchprojectbugs")
	public ResponseEntity<Page<BugDTO>> searchBugProjectBugsByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchreleasebugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseBugsByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchreleasebugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseBugsByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchreleaseleftbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLeftBugsByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据需求查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchreleaseleftbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLeftBugsByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据需求获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchreleaselinkableleftbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableLeftBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据需求查询发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchreleaselinkableleftbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableLeftBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据需求获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableResolvedBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据需求查询发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchreleaselinkableresolvedbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableResolvedBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据需求获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchreportbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReportBugsByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据需求查询发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchreportbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReportBugsByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据需求获取任务相关bug", tags = {"Bug" } ,notes = "根据需求获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/fetchtaskrelatedbug")
	public ResponseEntity<List<BugDTO>> fetchBugTaskRelatedBugByStory(@PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据需求查询任务相关bug", tags = {"Bug" } ,notes = "根据需求查询任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/bugs/searchtaskrelatedbug")
	public ResponseEntity<Page<BugDTO>> searchBugTaskRelatedBugByStory(@PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据项目建立Bug", tags = {"Bug" },  notes = "根据项目建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs")
    public ResponseEntity<BugDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目批量建立Bug", tags = {"Bug" },  notes = "根据项目批量建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setProject(project_id);
        }
        bugService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @ApiOperation(value = "根据项目更新Bug", tags = {"Bug" },  notes = "根据项目更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目批量更新Bug", tags = {"Bug" },  notes = "根据项目批量更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/bugs/batch")
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setProject(project_id);
        }
        bugService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目删除Bug", tags = {"Bug" },  notes = "根据项目删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @ApiOperation(value = "根据项目批量删除Bug", tags = {"Bug" },  notes = "根据项目批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目获取Bug", tags = {"Bug" },  notes = "根据项目获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目获取Bug草稿", tags = {"Bug" },  notes = "根据项目获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProject(@PathVariable("project_id") Long project_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/activatebatch")
    public ResponseEntity<Boolean> activateByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/assigntobatch")
    public ResponseEntity<Boolean> assignToByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/batchunlinkbug")
    public ResponseEntity<BugDTO> batchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.batchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/buildbatchunlinkbug")
    public ResponseEntity<BugDTO> buildBatchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.buildBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/buildbatchunlinkbugbatch")
    public ResponseEntity<Boolean> buildBatchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/buildlinkbug")
    public ResponseEntity<BugDTO> buildLinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.buildLinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/buildlinkbugbatch")
    public ResponseEntity<Boolean> buildLinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildLinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/buildunlinkbugbatch")
    public ResponseEntity<Boolean> buildUnlinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目检查Bug", tags = {"Bug" },  notes = "根据项目检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody BugDTO bugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(bugMapping.toDomain(bugdto)));
    }

    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/close")
    public ResponseEntity<BugDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.close(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/closebatch")
    public ResponseEntity<Boolean> closeByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/confirmbatch")
    public ResponseEntity<Boolean> confirmByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.confirmBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/linkbug")
    public ResponseEntity<BugDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.linkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/releaasebatchunlinkbug")
    public ResponseEntity<BugDTO> releaaseBatchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.releaaseBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/releaasebatchunlinkbugbatch")
    public ResponseEntity<Boolean> releaaseBatchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaaseBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/releaselinkbugbybug")
    public ResponseEntity<BugDTO> releaseLinkBugbyBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.releaseLinkBugbyBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/releaselinkbugbybugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseLinkBugbyLeftBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/releaselinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyLeftBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseUnLinkBugbyLeftBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/releaseunlinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseUnLinkBugbyLeftBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/releaseunlinkbugbatch")
    public ResponseEntity<Boolean> releaseUnlinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/resolvebatch")
    public ResponseEntity<Boolean> resolveByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.resolveBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目保存Bug", tags = {"Bug" },  notes = "根据项目保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/save")
    public ResponseEntity<BugDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目批量保存Bug", tags = {"Bug" },  notes = "根据项目批量保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
             domain.setProject(project_id);
        }
        bugService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/sendmessage")
    public ResponseEntity<BugDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.sendMessage(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/sendmsgpreprocess")
    public ResponseEntity<BugDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.sendMsgPreProcess(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/tostory")
    public ResponseEntity<BugDTO> toStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.toStory(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/tostorybatch")
    public ResponseEntity<Boolean> toStoryByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.toStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据项目Bug]", tags = {"Bug" },  notes = "批量处理[根据项目Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目Bug", tags = {"Bug" },  notes = "根据项目Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/bugs/{bug_id}/updatestoryversion")
    public ResponseEntity<BugDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain = bugService.updateStoryVersion(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
	@ApiOperation(value = "根据项目获取指派给我Bug", tags = {"Bug" } ,notes = "根据项目获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchassignedtomybug")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询指派给我Bug", tags = {"Bug" } ,notes = "根据项目查询指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchassignedtomybug")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据项目获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchassignedtomybugpc")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugPcByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据项目查询指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchassignedtomybugpc")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugPcByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据项目获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbugsbybuild")
	public ResponseEntity<List<BugDTO>> fetchBugBugsByBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据项目查询版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbugsbybuild")
	public ResponseEntity<Page<BugDTO>> searchBugBugsByBuildByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildBugsByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildBugsByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据项目获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildLinkResolvedBugsByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据项目查询版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildlinkresolvedbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildLinkResolvedBugsByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildopenbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildOpenBugsByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildopenbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildOpenBugsByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebug")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebug")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugmodule")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugmodule")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModuleByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModule_ProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugmodule_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModule_ProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugopenedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedByByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugopenedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedByByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedBy_ProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugopenedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedBy_ProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugres")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugres")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBYByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugresolvedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBYByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBY_ProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugresolvedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBY_ProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugResolution_ProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugresolution_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugResolution_ProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugSeverity_ProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugseverity_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugSeverity_ProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugStatus_ProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugstatus_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugStatus_ProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据项目获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchbuildproducebugtype_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugType_ProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据项目查询Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchbuildproducebugtype_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugType_ProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取当前用户解决的Bug", tags = {"Bug" } ,notes = "根据项目获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchcuruserresolve")
	public ResponseEntity<List<BugDTO>> fetchBugCurUserResolveByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询当前用户解决的Bug", tags = {"Bug" } ,notes = "根据项目查询当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchcuruserresolve")
	public ResponseEntity<Page<BugDTO>> searchBugCurUserResolveByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"Bug" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchBugDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"Bug" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchdefault")
	public ResponseEntity<Page<BugDTO>> searchBugDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"Bug" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchesbulk")
	public ResponseEntity<List<BugDTO>> fetchBugESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询ES批量的导入", tags = {"Bug" } ,notes = "根据项目查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchesbulk")
	public ResponseEntity<Page<BugDTO>> searchBugESBulkByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取我代理的Bug", tags = {"Bug" } ,notes = "根据项目获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchmyagentbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyAgentBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询我代理的Bug", tags = {"Bug" } ,notes = "根据项目查询我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchmyagentbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyAgentBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取累计创建的Bug数", tags = {"Bug" } ,notes = "根据项目获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchmycuropenedbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyCurOpenedBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询累计创建的Bug数", tags = {"Bug" } ,notes = "根据项目查询累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchmycuropenedbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyCurOpenedBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"Bug" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchmyfavorites")
	public ResponseEntity<List<BugDTO>> fetchBugMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询我的收藏", tags = {"Bug" } ,notes = "根据项目查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchmyfavorites")
	public ResponseEntity<Page<BugDTO>> searchBugMyFavoritesByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据项目获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchnotcurplanlinkbug")
	public ResponseEntity<List<BugDTO>> fetchBugNotCurPlanLinkBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据项目查询计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchnotcurplanlinkbug")
	public ResponseEntity<Page<BugDTO>> searchBugNotCurPlanLinkBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据项目获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchprojectbugs")
	public ResponseEntity<List<BugDTO>> fetchBugProjectBugsByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据项目查询遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchprojectbugs")
	public ResponseEntity<Page<BugDTO>> searchBugProjectBugsByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchreleasebugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseBugsByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchreleasebugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseBugsByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchreleaseleftbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLeftBugsByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据项目查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchreleaseleftbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLeftBugsByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据项目获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchreleaselinkableleftbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableLeftBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据项目查询发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchreleaselinkableleftbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableLeftBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据项目获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableResolvedBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据项目查询发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchreleaselinkableresolvedbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableResolvedBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据项目获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchreportbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReportBugsByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据项目查询发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchreportbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReportBugsByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据项目获取任务相关bug", tags = {"Bug" } ,notes = "根据项目获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchtaskrelatedbug")
	public ResponseEntity<List<BugDTO>> fetchBugTaskRelatedBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据项目查询任务相关bug", tags = {"Bug" } ,notes = "根据项目查询任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/searchtaskrelatedbug")
	public ResponseEntity<Page<BugDTO>> searchBugTaskRelatedBugByProject(@PathVariable("project_id") Long project_id, @RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品需求建立Bug", tags = {"Bug" },  notes = "根据产品需求建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs")
    public ResponseEntity<BugDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求批量建立Bug", tags = {"Bug" },  notes = "根据产品需求批量建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/batch")
    public ResponseEntity<Boolean> createBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setStory(story_id);
        }
        bugService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @ApiOperation(value = "根据产品需求更新Bug", tags = {"Bug" },  notes = "根据产品需求更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求批量更新Bug", tags = {"Bug" },  notes = "根据产品需求批量更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/bugs/batch")
    public ResponseEntity<Boolean> updateBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
            domain.setStory(story_id);
        }
        bugService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求删除Bug", tags = {"Bug" },  notes = "根据产品需求删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @ApiOperation(value = "根据产品需求批量删除Bug", tags = {"Bug" },  notes = "根据产品需求批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProductStory(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求获取Bug", tags = {"Bug" },  notes = "根据产品需求获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求获取Bug草稿", tags = {"Bug" },  notes = "根据产品需求获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setStory(story_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/activatebatch")
    public ResponseEntity<Boolean> activateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/assigntobatch")
    public ResponseEntity<Boolean> assignToByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/batchunlinkbug")
    public ResponseEntity<BugDTO> batchUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.batchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/batchunlinkbugbatch")
    public ResponseEntity<Boolean> batchUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.batchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/buildbatchunlinkbug")
    public ResponseEntity<BugDTO> buildBatchUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.buildBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/buildbatchunlinkbugbatch")
    public ResponseEntity<Boolean> buildBatchUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/buildlinkbug")
    public ResponseEntity<BugDTO> buildLinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.buildLinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/buildlinkbugbatch")
    public ResponseEntity<Boolean> buildLinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildLinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/buildunlinkbugbatch")
    public ResponseEntity<Boolean> buildUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.buildUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求检查Bug", tags = {"Bug" },  notes = "根据产品需求检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugDTO bugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(bugMapping.toDomain(bugdto)));
    }

    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/close")
    public ResponseEntity<BugDTO> closeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.close(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/closebatch")
    public ResponseEntity<Boolean> closeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/confirmbatch")
    public ResponseEntity<Boolean> confirmByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.confirmBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/linkbug")
    public ResponseEntity<BugDTO> linkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.linkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/linkbugbatch")
    public ResponseEntity<Boolean> linkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.linkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaasebatchunlinkbug")
    public ResponseEntity<BugDTO> releaaseBatchUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaaseBatchUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/releaasebatchunlinkbugbatch")
    public ResponseEntity<Boolean> releaaseBatchUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaaseBatchUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaselinkbugbybug")
    public ResponseEntity<BugDTO> releaseLinkBugbyBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseLinkBugbyBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/releaselinkbugbybugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseLinkBugbyLeftBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/releaselinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseLinkBugbyLeftBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<BugDTO> releaseUnLinkBugbyLeftBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/releaseunlinkbugbyleftbugbatch")
    public ResponseEntity<Boolean> releaseUnLinkBugbyLeftBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnLinkBugbyLeftBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/releaseunlinkbugbatch")
    public ResponseEntity<Boolean> releaseUnlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.releaseUnlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/resolvebatch")
    public ResponseEntity<Boolean> resolveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.resolveBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求保存Bug", tags = {"Bug" },  notes = "根据产品需求保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/save")
    public ResponseEntity<BugDTO> saveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求批量保存Bug", tags = {"Bug" },  notes = "根据产品需求批量保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domainlist=bugMapping.toDomain(bugdtos);
        for(Bug domain:domainlist){
             domain.setStory(story_id);
        }
        bugService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/sendmessage")
    public ResponseEntity<BugDTO> sendMessageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.sendMessage(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/sendmsgpreprocess")
    public ResponseEntity<BugDTO> sendMsgPreProcessByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.sendMsgPreProcess(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/tostory")
    public ResponseEntity<BugDTO> toStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.toStory(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/tostorybatch")
    public ResponseEntity<Boolean> toStoryByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.toStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
    @ApiOperation(value = "批量处理[根据产品需求Bug]", tags = {"Bug" },  notes = "批量处理[根据产品需求Bug]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/bugs/unlinkbugbatch")
    public ResponseEntity<Boolean> unlinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody List<BugDTO> bugdtos) {
        List<Bug> domains = bugMapping.toDomain(bugdtos);
        boolean result = bugService.unlinkBugBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求Bug", tags = {"Bug" },  notes = "根据产品需求Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/bugs/{bug_id}/updatestoryversion")
    public ResponseEntity<BugDTO> updateStoryVersionByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setStory(story_id);
        domain = bugService.updateStoryVersion(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }
	@ApiOperation(value = "根据产品需求获取指派给我Bug", tags = {"Bug" } ,notes = "根据产品需求获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchassignedtomybug")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询指派给我Bug", tags = {"Bug" } ,notes = "根据产品需求查询指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchassignedtomybug")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据产品需求获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchassignedtomybugpc")
	public ResponseEntity<List<BugDTO>> fetchBugAssignedToMyBugPcByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询指派给我Bug（PC）", tags = {"Bug" } ,notes = "根据产品需求查询指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchassignedtomybugpc")
	public ResponseEntity<Page<BugDTO>> searchBugAssignedToMyBugPcByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchAssignedToMyBugPc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据产品需求获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbugsbybuild")
	public ResponseEntity<List<BugDTO>> fetchBugBugsByBuildByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本关联bug(遗留的)", tags = {"Bug" } ,notes = "根据产品需求查询版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbugsbybuild")
	public ResponseEntity<Page<BugDTO>> searchBugBugsByBuildByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBugsByBuild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据产品需求获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildLinkResolvedBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本可关联的已解决的Bugs集合", tags = {"Bug" } ,notes = "根据产品需求查询版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildlinkresolvedbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildLinkResolvedBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildLinkResolvedBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildopenbugs")
	public ResponseEntity<List<BugDTO>> fetchBugBuildOpenBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询版本关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求查询版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildopenbugs")
	public ResponseEntity<Page<BugDTO>> searchBugBuildOpenBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildOpenBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebug")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebug")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugmodule")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugmodule")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModuleByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugModule_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugmodule_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugModule_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugModule_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugopenedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedByByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-创建分类", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugopenedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedByByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugOpenedBy_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-创建者分布(项目)", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugopenedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugOpenedBy_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugOpenedBy_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugres")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugres")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRES(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBYByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-解决者分布", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugresolvedby")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBYByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugRESOLVEDBY_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-解决者分布(项目)", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugresolvedby_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugRESOLVEDBY_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugResolution_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-解决方案分布(项目)", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugresolution_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugResolution_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugResolution_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugSeverity_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-严重程度分布(项目)", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugseverity_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugSeverity_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugSeverity_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugStatus_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-状态分布(项目)", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugstatus_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugStatus_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugStatus_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据产品需求获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchbuildproducebugtype_project")
	public ResponseEntity<List<BugDTO>> fetchBugBuildProduceBugType_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询Build产生的Bug-类型分布(项目)", tags = {"Bug" } ,notes = "根据产品需求查询Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchbuildproducebugtype_project")
	public ResponseEntity<Page<BugDTO>> searchBugBuildProduceBugType_ProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchBuildProduceBugType_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取当前用户解决的Bug", tags = {"Bug" } ,notes = "根据产品需求获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchcuruserresolve")
	public ResponseEntity<List<BugDTO>> fetchBugCurUserResolveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询当前用户解决的Bug", tags = {"Bug" } ,notes = "根据产品需求查询当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchcuruserresolve")
	public ResponseEntity<Page<BugDTO>> searchBugCurUserResolveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchCurUserResolve(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取DEFAULT", tags = {"Bug" } ,notes = "根据产品需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchBugDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询DEFAULT", tags = {"Bug" } ,notes = "根据产品需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchdefault")
	public ResponseEntity<Page<BugDTO>> searchBugDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取ES批量的导入", tags = {"Bug" } ,notes = "根据产品需求获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchesbulk")
	public ResponseEntity<List<BugDTO>> fetchBugESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询ES批量的导入", tags = {"Bug" } ,notes = "根据产品需求查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchesbulk")
	public ResponseEntity<Page<BugDTO>> searchBugESBulkByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取我代理的Bug", tags = {"Bug" } ,notes = "根据产品需求获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchmyagentbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyAgentBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询我代理的Bug", tags = {"Bug" } ,notes = "根据产品需求查询我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchmyagentbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyAgentBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyAgentBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取累计创建的Bug数", tags = {"Bug" } ,notes = "根据产品需求获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchmycuropenedbug")
	public ResponseEntity<List<BugDTO>> fetchBugMyCurOpenedBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询累计创建的Bug数", tags = {"Bug" } ,notes = "根据产品需求查询累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchmycuropenedbug")
	public ResponseEntity<Page<BugDTO>> searchBugMyCurOpenedBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyCurOpenedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取我的收藏", tags = {"Bug" } ,notes = "根据产品需求获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchmyfavorites")
	public ResponseEntity<List<BugDTO>> fetchBugMyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询我的收藏", tags = {"Bug" } ,notes = "根据产品需求查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchmyfavorites")
	public ResponseEntity<Page<BugDTO>> searchBugMyFavoritesByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据产品需求获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchnotcurplanlinkbug")
	public ResponseEntity<List<BugDTO>> fetchBugNotCurPlanLinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询计划关联bug（去除已关联）", tags = {"Bug" } ,notes = "根据产品需求查询计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchnotcurplanlinkbug")
	public ResponseEntity<Page<BugDTO>> searchBugNotCurPlanLinkBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchNotCurPlanLinkBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据产品需求获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchprojectbugs")
	public ResponseEntity<List<BugDTO>> fetchBugProjectBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询遗留得Bug(项目)", tags = {"Bug" } ,notes = "根据产品需求查询遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchprojectbugs")
	public ResponseEntity<Page<BugDTO>> searchBugProjectBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchProjectBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchreleasebugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchreleasebugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchreleaseleftbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLeftBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询发布关联Bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求查询发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchreleaseleftbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLeftBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLeftBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据产品需求获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchreleaselinkableleftbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableLeftBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询发布可关联的bug（遗留）", tags = {"Bug" } ,notes = "根据产品需求查询发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchreleaselinkableleftbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableLeftBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableLeftBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<BugDTO>> fetchBugReleaseLinkableResolvedBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询发布可关联的bug（已解决）", tags = {"Bug" } ,notes = "根据产品需求查询发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchreleaselinkableresolvedbug")
	public ResponseEntity<Page<BugDTO>> searchBugReleaseLinkableResolvedBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReleaseLinkableResolvedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据产品需求获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchreportbugs")
	public ResponseEntity<List<BugDTO>> fetchBugReportBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询发布关联Bug（未解决）", tags = {"Bug" } ,notes = "根据产品需求查询发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchreportbugs")
	public ResponseEntity<Page<BugDTO>> searchBugReportBugsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchReportBugs(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
	@ApiOperation(value = "根据产品需求获取任务相关bug", tags = {"Bug" } ,notes = "根据产品需求获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/fetchtaskrelatedbug")
	public ResponseEntity<List<BugDTO>> fetchBugTaskRelatedBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据产品需求查询任务相关bug", tags = {"Bug" } ,notes = "根据产品需求查询任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/bugs/searchtaskrelatedbug")
	public ResponseEntity<Page<BugDTO>> searchBugTaskRelatedBugByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody BugSearchContext context) {
        context.setN_story_eq(story_id);
        Page<Bug> domains = bugService.searchTaskRelatedBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(bugMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

