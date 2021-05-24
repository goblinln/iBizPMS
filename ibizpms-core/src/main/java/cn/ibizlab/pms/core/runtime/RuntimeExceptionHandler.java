package cn.ibizlab.pms.core.runtime;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.EntityException;
import net.ibizsys.runtime.util.EntityFieldError;
import net.ibizsys.runtime.util.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(value = EntityException.class)
    @ResponseBody
    public ResponseEntity handlerException(EntityException entityException) {
        JSONObject out = new JSONObject();
        out.put("type", "EntityException");
        out.put("code", entityException.getErrorCode());
        out.put("message", entityException.getMessage());
        JSONArray array = new JSONArray();
        out.put("details", array);
        for (EntityFieldError entityFieldError : entityException.getEntityError().getEntityFieldErrorList()) {
            JSONObject fieldError = new JSONObject();
            fieldError.put("fieldname", entityFieldError.getFieldName());
            fieldError.put("fieldlogicname", entityFieldError.getFieldLogicName());
            fieldError.put("fielderrortype", entityFieldError.getErrorType());
            fieldError.put("fielderrorinfo", entityFieldError.getErrorInfo());
            array.add(fieldError);
        }
        return ResponseEntity.badRequest().body(out);
    }

    @ExceptionHandler(value = DataEntityRuntimeException.class)
    @ResponseBody
    public ResponseEntity handlerException(DataEntityRuntimeException dataEntityRuntimeException) {
        JSONObject out = new JSONObject();
        out.put("type", "DataEntityRuntimeException");
        out.put("code", dataEntityRuntimeException.getErrorCode());
        out.put("message", dataEntityRuntimeException.getMessage());
        return ResponseEntity.badRequest().body(out);
    }

    @ExceptionHandler(value = DataEntityRuntimeException.class)
    @ResponseBody
    public ResponseEntity handlerException(RuntimeException runtimeException) {
        JSONObject out = new JSONObject();
        out.put("type", "RuntimeException");
        out.put("code", Errors.INTERNALERROR);
        out.put("message", runtimeException.getMessage());
        return ResponseEntity.badRequest().body(out);
    }

}
