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
import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.service.ITodoService;
import cn.ibizlab.pms.core.zentao.filter.TodoSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TodoRuntime;

@Slf4j
@Api(tags = {"待办" })
@RestController("WebApi-todo")
@RequestMapping("")
public class TodoResource {

    @Autowired
    public ITodoService todoService;

    @Autowired
    public TodoRuntime todoRuntime;

    @Autowired
    @Lazy
    public TodoMapping todoMapping;

    @PreAuthorize("@TodoRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建待办", tags = {"待办" },  notes = "新建待办")
	@RequestMapping(method = RequestMethod.POST, value = "/todos")
    @Transactional
    public ResponseEntity<TodoDTO> create(@Validated @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
		todoService.create(domain);
        if(!todoRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id, 'UPDATE')")
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
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(todo_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'DELETE')")
    @ApiOperation(value = "删除待办", tags = {"待办" },  notes = "删除待办")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("todo_id") Long todo_id) {
         return ResponseEntity.status(HttpStatus.OK).body(todoService.remove(todo_id));
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'READ')")
    @ApiOperation(value = "获取待办", tags = {"待办" },  notes = "获取待办")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}")
    public ResponseEntity<TodoDTO> get(@PathVariable("todo_id") Long todo_id) {
        Todo domain = todoService.get(todo_id);
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(todo_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取待办草稿", tags = {"待办" },  notes = "获取待办草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/getdraft")
    public ResponseEntity<TodoDTO> getDraft(TodoDTO dto) {
        Todo domain = todoMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(todoMapping.toDto(todoService.getDraft(domain)));
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id, 'ACTIVATE')")
    @ApiOperation(value = "Activate", tags = {"待办" },  notes = "Activate")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/activate")
    public ResponseEntity<TodoDTO> activate(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.activate(domain);
        tododto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'ASSIGNTO')")
    @ApiOperation(value = "AssignTo", tags = {"待办" },  notes = "AssignTo")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/assignto")
    public ResponseEntity<TodoDTO> assignTo(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.assignTo(domain);
        tododto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("@TodoRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查待办", tags = {"待办" },  notes = "检查待办")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TodoDTO tododto) {
        return  ResponseEntity.status(HttpStatus.OK).body(todoService.checkKey(todoMapping.toDomain(tododto)));
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id, 'CLOSE')")
    @ApiOperation(value = "Close", tags = {"待办" },  notes = "Close")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/close")
    public ResponseEntity<TodoDTO> close(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.close(domain);
        tododto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'READ')")
    @ApiOperation(value = "定时创建周期", tags = {"待办" },  notes = "定时创建周期")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/createcycle")
    public ResponseEntity<TodoDTO> createCycle(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.createCycle(domain);
        tododto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'FINISH')")
    @ApiOperation(value = "Finish", tags = {"待办" },  notes = "Finish")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/finish")
    public ResponseEntity<TodoDTO> finish(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.finish(domain);
        tododto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("@TodoRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存待办", tags = {"待办" },  notes = "保存待办")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/save")
    public ResponseEntity<TodoDTO> save(@RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        todoService.save(domain);
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'MANAGE')")
    @ApiOperation(value = "行为", tags = {"待办" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/sendmessage")
    public ResponseEntity<TodoDTO> sendMessage(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.sendMessage(domain);
        tododto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'MANAGE')")
    @ApiOperation(value = "发送消息前置处理", tags = {"待办" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/sendmsgpreprocess")
    public ResponseEntity<TodoDTO> sendMsgPreProcess(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.sendMsgPreProcess(domain);
        tododto = todoMapping.toDto(domain);
        Map<String,Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("@TodoRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"待办" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/fetchdefault")
	public ResponseEntity<List<TodoDTO>> fetchdefault(@RequestBody TodoSearchContext context) {
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchDefault(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的待办", tags = {"待办" } ,notes = "获取我的待办")
    @RequestMapping(method= RequestMethod.POST , value="/todos/fetchmytodo")
	public ResponseEntity<List<TodoDTO>> fetchmytodo(@RequestBody TodoSearchContext context) {
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchMyTodo(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的待办", tags = {"待办" } ,notes = "获取我的待办")
    @RequestMapping(method= RequestMethod.POST , value="/todos/fetchmytodopc")
	public ResponseEntity<List<TodoDTO>> fetchmytodopc(@RequestBody TodoSearchContext context) {
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchMyTodoPc(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.quickTest('READ')")
	@ApiOperation(value = "获取MyUpcoming", tags = {"待办" } ,notes = "获取MyUpcoming")
    @RequestMapping(method= RequestMethod.POST , value="/todos/fetchmyupcoming")
	public ResponseEntity<List<TodoDTO>> fetchmyupcoming(@RequestBody TodoSearchContext context) {
        todoRuntime.addAuthorityConditions(context,"READ");
        Page<Todo> domains = todoService.searchMyUpcoming(context) ;
        List<TodoDTO> list = todoMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/{action}")
    public ResponseEntity<TodoDTO> dynamicCall(@PathVariable("todo_id") Long todo_id , @PathVariable("action") String action , @RequestBody TodoDTO tododto) {
        Todo domain = todoService.dynamicCall(todo_id, action, todoMapping.toDomain(tododto));
        tododto = todoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }
}

