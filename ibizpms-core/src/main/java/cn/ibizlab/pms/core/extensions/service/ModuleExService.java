package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.ModuleServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[模块] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ModuleExService")
public class ModuleExService extends ModuleServiceImpl {

    @Autowired
    IStoryService iStoryService;
    @Autowired
    IBugService iBugService;
    @Autowired
    ITaskService iTaskService;
    @Autowired
    ICaseService iCaseService;
    @Autowired
    IProductService productService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Fix:重建模块路径] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Module fix(Module et) {
        return fix(et);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean create(Module et) {
        return super.create(et);
    }

    @Override
    public boolean update(Module et) {
        if(et.getParent() == null) {
            et.setParent(0L);
        }
        if (!super.update(et)) {
            return false;
        }

        StringBuffer path = new StringBuffer();
        path.append(String.format(",%s,",et.getId()));
        fillPath(et,path);
        et.setPath(path.toString());
        et.setGrade(et.getPath().length()-et.getPath().replace(MULTIPLE_CHOICE,"").length()-1);

        Module maxModule = this.getOne(new QueryWrapper<Module>().eq(FIELD_ROOT,et.getRoot()).eq(FIELD_GRADE,et.getGrade()).orderByDesc("`order`").last("limit 0,1")) ;
        if(maxModule==null && et.getOrder() == null){
            et.setOrder(10);
        }else if(et.getOrder() == null && maxModule != null){
            et.setOrder(maxModule.getOrder()+10);
        }

        super.update(et);
        List<Module> list = this.list(new QueryWrapper<Module>().like(FIELD_PATH, MULTIPLE_CHOICE + et.getId() + MULTIPLE_CHOICE).ne("id", et.getId()));
        for (Module module : list) {
            module.setPath(module.getPath().replace((MULTIPLE_CHOICE + et.getId() + MULTIPLE_CHOICE), path.toString()));
            super.update(module);
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Long key) {
        Module et = this.get(key);
        Long parent = et.getParent();
        List<Module> childList = this.list(new QueryWrapper<Module>().eq(FIELD_TYPE, et.getType()).eq(FIELD_ROOT, et.getRoot()).ne(FIELD_ID,key).last(" AND find_in_set('"+ key +"',path)"));
        String modules = String.valueOf(key);
        for(Module module : childList) {

            modules += MULTIPLE_CHOICE + module.getId();

            super.remove(module.getId());
        }
        if(StaticDict.Module__type.LINE.getValue().equals(et.getType())) {
            Product product = new Product();
            product.setLine(0L);
            productService.update(product,new QueryWrapper<Product>().eq(StaticDict.Module__type.LINE.getValue(), et.getId()));
        }
        else if(StaticDict.Module__type.STORY.getValue().equals(et.getType())) {
            // Story
            Story story = new Story();
            story.setModule(parent);
            iStoryService.update(story, new QueryWrapper<Story>().last(String.format(" AND FIND_IN_SET(module,'%1$s')", modules)));
            // Bug
            Bug bug = new Bug();
            bug.setModule(parent);
            iBugService.update(bug, new QueryWrapper<Bug>().last(String.format(" AND  FIND_IN_SET(module,'%1$s')", modules)));
            // Task
            Task task = new Task();
            task.setModule(parent);
            iTaskService.update(task, new QueryWrapper<Task>().last(String.format(" AND FIND_IN_SET(module,'%1$s')", modules)));
            // Case
            Case cases = new Case();
            cases.setModule(parent);
            iCaseService.update(cases, new QueryWrapper<Case>().last(String.format(" AND FIND_IN_SET(module,'%1$s')", modules)));
        }else if(StaticDict.Module__type.TASK.getValue().equals(et.getType())) {
            // Task
            Task task = new Task();
            task.setModule(parent);
            iTaskService.update(task, new QueryWrapper<Task>().last(String.format(" AND FIND_IN_SET(module,'%1$s')", modules)));
        }
        return super.remove(key);
    }


    private void fillPath(Module et, StringBuffer path) {
        System.out.println(path);
        if (et.getParent() != 0) {
            Module parent = this.get(et.getParent());

            path.insert(0,String.format(",%s",parent.getId())) ;
            fillPath(parent, path);
        }
    }
}

