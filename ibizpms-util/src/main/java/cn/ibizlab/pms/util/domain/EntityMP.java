package cn.ibizlab.pms.util.domain;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.util.StringUtils;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;
import java.lang.reflect.Field;

public class EntityMP extends EntityBase {

    public UpdateWrapper getUpdateWrapper(boolean clean) {
        UpdateWrapper wrapper=new UpdateWrapper();
        for(String nullField:getFocusNull()) {
            wrapper.set(nullField,null);
        }
        if(clean) {
            getFocusNull().clear();
        }
        return  wrapper;
    }

    @Override
    public void modify(String field,Object val) {
        if(val==null) {
            this.getFocusNull().add(field.toLowerCase());
        }
        else {
            this.getFocusNull().remove(field.toLowerCase());
        }
    }

    @Override
    public void reset(String field){
        if(!StringUtils.isEmpty(field)){
            String resetField=field.toLowerCase();
            this.set(resetField,null);
            getFocusNull().remove(resetField);
        }
    }

    @Override
    public boolean contains(String s) {
        try {
            Field field = DEFieldCacheMap.getField(this.getClass(), s);
            if (field == null)
                return false;
            field.setAccessible(true);
            Object value = field.get(this);
            if (value == null) {
                if (this.getFocusNull().contains(s.toLowerCase())) {
                    return true;
                }
                return false;
            } else {
                return true;
            }
        } catch (IllegalAccessException e) {
            return false;
        }
    }

}

