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
import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.service.ITodoService;
import cn.ibizlab.pms.core.zentao.filter.TodoSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TodoRuntime;

@Slf4j
@Api(tags = {"待办" })
@RestController("StandardAPI-todo")
@RequestMapping("")
public class TodoResource {

    @Autowired
    public ITodoService todoService;

    @Autowired
    public TodoRuntime todoRuntime;

    @Autowired
    @Lazy
    public TodoMapping todoMapping;

    @PreAuthorize("quickTest('ZT_TODO', 'CREATE')")
    @ApiOperation(value = "新建待办", tags = {"待办" },  notes = "新建待办")
	@RequestMapping(method = RequestMethod.POST, value = "/todos")
    @Transactional
    public ResponseEntity<TodoDTO> create(@Validated @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
		todoService.create(domain);
        if(!todoRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_TODO', #todo_id, 'UPDATE')")
    @ApiOperation(value = "更新待办", tags = {"待办" },  notes = "更新待办")
	@RequestMapping(method = RequestMethod.PUT, value = "/todos/{todo_id}")
    @Transactional
    public ResponseEntity<TodoDTO> update(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
		Todo domain  = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
		todoService.update(domain );
        if(!todoRuntime.test(todo_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TodoDTO dto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(todo_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'FINISH')")
    @ApiOperation(value = "Finish", tags = {"待办" },  notes = "Finish")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/finish")
    public ResponseEntity<TodoDTO> finish(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.finish(domain);
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'START')")
    @ApiOperation(value = "开始", tags = {"待办" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/start")
    public ResponseEntity<TodoDTO> start(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.start(domain);
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("quickTest('ZT_TODO', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"待办" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/todos/fetchmy")
	public ResponseEntity<List<TodoDTO>> fetchmy(@RequestBody TodoSearchContext context) {
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchMy(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TODO', #todo_id, 'DELETE')")
    @ApiOperation(value = "删除待办", tags = {"待办" },  notes = "删除待办")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("todo_id") Long todo_id) {
         return ResponseEntity.status(HttpStatus.OK).body(todoService.remove(todo_id));
    }

    @PreAuthorize("quickTest('ZT_TODO', 'DELETE')")
    @ApiOperation(value = "批量删除待办", tags = {"待办" },  notes = "批量删除待办")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        todoService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_TODO', 'CREATE')")
    @ApiOperation(value = "获取待办草稿", tags = {"待办" },  notes = "获取待办草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/getdraft")
    public ResponseEntity<TodoDTO> getDraft(TodoDTO dto) {
        Todo domain = todoMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(todoMapping.toDto(todoService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TODO', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"待办" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/todos/fetchaccount")
	public ResponseEntity<List<TodoDTO>> fetchaccount(@RequestBody TodoSearchContext context) {
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchAccount(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TODO', #todo_id, 'READ')")
    @ApiOperation(value = "获取待办", tags = {"待办" },  notes = "获取待办")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}")
    public ResponseEntity<TodoDTO> get(@PathVariable("todo_id") Long todo_id) {
        Todo domain = todoService.get(todo_id);
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(todo_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/{action}")
    public ResponseEntity<TodoDTO> dynamicCall(@PathVariable("todo_id") Long todo_id , @PathVariable("action") String action , @RequestBody TodoDTO tododto) {
        Todo domain = todoService.dynamicCall(todo_id, action, todoMapping.toDomain(tododto));
        tododto = todoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }

    @PreAuthorize("quickTest('ZT_TODO','CREATE')")
    @ApiOperation(value = "根据系统用户建立待办", tags = {"待办" },  notes = "根据系统用户建立待办")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/todos")
    public ResponseEntity<TodoDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        
		todoService.create(domain);
        if(!todoRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户更新待办", tags = {"待办" },  notes = "根据系统用户更新待办")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}")
    public ResponseEntity<TodoDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        
        domain.setId(todo_id);
		todoService.update(domain);
        if(!todoRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'FINISH')")
    @ApiOperation(value = "根据系统用户Finish", tags = {"待办" },  notes = "根据系统用户Finish")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/finish")
    public ResponseEntity<TodoDTO> finishBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        
        domain.setId(todo_id);
        domain = todoService.finish(domain) ;
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());    
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }

    @PreAuthorize("test('ZT_TODO', #todo_id, 'START')")
    @ApiOperation(value = "根据系统用户开始", tags = {"待办" },  notes = "根据系统用户开始")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/start")
    public ResponseEntity<TodoDTO> startBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        
        domain.setId(todo_id);
        domain = todoService.start(domain) ;
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());    
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }

    @PreAuthorize("quickTest('ZT_TODO','READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"待办" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/fetchmy")
	public ResponseEntity<List<TodoDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TodoSearchContext context) {
        
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchMy(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TODO', #todo_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户删除待办", tags = {"待办" },  notes = "根据系统用户删除待办")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id) {
		return ResponseEntity.status(HttpStatus.OK).body(todoService.remove(todo_id));
    }

    @PreAuthorize("quickTest('ZT_TODO','DELETE')")
    @ApiOperation(value = "根据系统用户批量删除待办", tags = {"待办" },  notes = "根据系统用户批量删除待办")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/todos/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        todoService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_TODO','CREATE')")
    @ApiOperation(value = "根据系统用户获取待办草稿", tags = {"待办" },  notes = "根据系统用户获取待办草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/todos/getdraft")
    public ResponseEntity<TodoDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, TodoDTO dto) {
        Todo domain = todoMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(todoMapping.toDto(todoService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TODO','READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"待办" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/fetchaccount")
	public ResponseEntity<List<TodoDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody TodoSearchContext context) {
        
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchAccount(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_TODO', #todo_id, 'READ')")
    @ApiOperation(value = "根据系统用户获取待办", tags = {"待办" },  notes = "根据系统用户获取待办")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}")
    public ResponseEntity<TodoDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id) {
        Todo domain = todoService.get(todo_id);
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}

