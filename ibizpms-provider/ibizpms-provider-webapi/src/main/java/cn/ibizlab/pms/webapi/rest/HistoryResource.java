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
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.HistoryRuntime;

@Slf4j
@Api(tags = {"操作历史" })
@RestController("WebApi-history")
@RequestMapping("")
public class HistoryResource {

    @Autowired
    public IHistoryService historyService;

    @Autowired
    public HistoryRuntime historyRuntime;

    @Autowired
    @Lazy
    public HistoryMapping historyMapping;

    @PreAuthorize("@HistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建操作历史", tags = {"操作历史" },  notes = "新建操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories")
    @Transactional
    public ResponseEntity<HistoryDTO> create(@Validated @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
		historyService.create(domain);
        if(!historyRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@HistoryRuntime.test(#history_id,'UPDATE')")
    @ApiOperation(value = "更新操作历史", tags = {"操作历史" },  notes = "更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/histories/{history_id}")
    @Transactional
    public ResponseEntity<HistoryDTO> update(@PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
		History domain  = historyMapping.toDomain(historydto);
        domain.setId(history_id);
		historyService.update(domain );
        if(!historyRuntime.test(history_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(history_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@HistoryRuntime.test(#history_id,'DELETE')")
    @ApiOperation(value = "删除操作历史", tags = {"操作历史" },  notes = "删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/histories/{history_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("history_id") Long history_id) {
         return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@HistoryRuntime.test(#history_id,'READ')")
    @ApiOperation(value = "获取操作历史", tags = {"操作历史" },  notes = "获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/histories/{history_id}")
    public ResponseEntity<HistoryDTO> get(@PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(history_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@HistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取操作历史草稿", tags = {"操作历史" },  notes = "获取操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraft(HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@HistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查操作历史", tags = {"操作历史" },  notes = "检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "保存操作历史", tags = {"操作历史" },  notes = "保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories/save")
    public ResponseEntity<HistoryDTO> save(@RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        historyService.save(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@HistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"操作历史" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchdefault(@RequestBody HistorySearchContext context) {
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@HistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"操作历史" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchDefault(@RequestBody HistorySearchContext context) {
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/histories/{history_id}/{action}")
    public ResponseEntity<HistoryDTO> dynamicCall(@PathVariable("history_id") Long history_id , @PathVariable("action") String action , @RequestBody HistoryDTO historydto) {
        History domain = historyService.dynamicCall(history_id, action, historyMapping.toDomain(historydto));
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求系统日志建立操作历史", tags = {"操作历史" },  notes = "根据需求系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求系统日志更新操作历史", tags = {"操作历史" },  notes = "根据需求系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求系统日志删除操作历史", tags = {"操作历史" },  notes = "根据需求系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求系统日志获取操作历史", tags = {"操作历史" },  notes = "根据需求系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据需求系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求系统日志检查操作历史", tags = {"操作历史" },  notes = "根据需求系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据需求系统日志保存操作历史", tags = {"操作历史" },  notes = "根据需求系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据需求系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品需求系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品需求系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品需求系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

