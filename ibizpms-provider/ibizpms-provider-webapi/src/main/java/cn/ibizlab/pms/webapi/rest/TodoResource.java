package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
@Api(tags = {"待办"})
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


    @PreAuthorize("test('ZT_TODO', #todo_id, 'ACTIVATE')")
    @ApiOperation(value = "Activate", tags = {"待办" },  notes = "Activate")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/activate")
    public ResponseEntity<TodoDTO> activate(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.activate(domain);
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'ASSIGNTO')")
    @ApiOperation(value = "AssignTo", tags = {"待办" },  notes = "AssignTo")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/assignto")
    public ResponseEntity<TodoDTO> assignTo(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.assignTo(domain);
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("quickTest('ZT_TODO', 'CREATE')")
    @ApiOperation(value = "检查待办", tags = {"待办" },  notes = "检查待办")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody TodoDTO tododto) {
        return  ResponseEntity.status(HttpStatus.OK).body(todoService.checkKey(todoMapping.toDomain(tododto)));
    }

    @PreAuthorize("test('ZT_TODO', #todo_id, 'CLOSE')")
    @ApiOperation(value = "Close", tags = {"待办" },  notes = "Close")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/close")
    public ResponseEntity<TodoDTO> close(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.close(domain);
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'READ')")
    @ApiOperation(value = "定时创建周期", tags = {"待办" },  notes = "定时创建周期")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/createcycle")
    public ResponseEntity<TodoDTO> createCycle(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.createCycle(domain);
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
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


    @PreAuthorize("quickTest('ZT_TODO', 'CREATE')")
    @ApiOperation(value = "获取待办草稿", tags = {"待办" },  notes = "获取待办草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/getdraft")
    public ResponseEntity<TodoDTO> getDraft(TodoDTO dto) {
        Todo domain = todoMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(todoMapping.toDto(todoService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_TODO', 'DENY')")
    @ApiOperation(value = "保存待办", tags = {"待办" },  notes = "保存待办")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/save")
    public ResponseEntity<TodoDTO> save(@RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        todoService.save(domain);
        TodoDTO dto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'MANAGE')")
    @ApiOperation(value = "行为", tags = {"待办" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/sendmessage")
    public ResponseEntity<TodoDTO> sendMessage(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.sendMessage(domain);
        tododto = todoMapping.toDto(domain);
        Map<String, Integer> opprivs = todoRuntime.getOPPrivs(domain.getId());
        tododto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(tododto);
    }


    @PreAuthorize("test('ZT_TODO', #todo_id, 'MANAGE')")
    @ApiOperation(value = "发送消息前置处理", tags = {"待办" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/sendmsgpreprocess")
    public ResponseEntity<TodoDTO> sendMsgPreProcess(@PathVariable("todo_id") Long todo_id, @RequestBody TodoDTO tododto) {
        Todo domain = todoMapping.toDomain(tododto);
        domain.setId(todo_id);
        domain = todoService.sendMsgPreProcess(domain);
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
    @PreAuthorize("quickTest('ZT_TODO', 'READ')")
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
    @PreAuthorize("quickTest('ZT_TODO', 'READ')")
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
    @PreAuthorize("quickTest('ZT_TODO', 'READ')")
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
    @PreAuthorize("quickTest('ZT_TODO', 'READ')")
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

    @ApiOperation(value = "生成待办报表", tags = {"待办"}, notes = "生成待办报表")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, TodoSearchContext context, HttpServletResponse response) {
        try {
            todoRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", todoRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, todoRuntime);
        }
    }

    @ApiOperation(value = "打印待办", tags = {"待办"}, notes = "打印待办")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("todo_ids") Set<Long> todo_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = todoRuntime.getDEPrintRuntime(print_id);
        try {
            List<Todo> domains = new ArrayList<>();
            for (Long todo_id : todo_ids) {
                domains.add(todoService.get( todo_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Todo[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", todoRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", todo_ids, e.getMessage()), Errors.INTERNALERROR, todoRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

}

