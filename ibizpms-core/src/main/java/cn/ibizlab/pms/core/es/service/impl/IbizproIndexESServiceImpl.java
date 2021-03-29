package cn.ibizlab.pms.core.es.service.impl;

import com.google.common.collect.Lists;
import cn.ibizlab.pms.core.es.dao.IbizproIndexESRepository;
import cn.ibizlab.pms.core.es.domain.IbizproIndex;
import cn.ibizlab.pms.core.es.service.IIbizproIndexESService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import java.util.Collection;

/**
 * 实体[索引检索] 服务对象接口实现
 */
@Service
@ConditionalOnClass(IbizproIndexESRepository.class)
public class IbizproIndexESServiceImpl implements IIbizproIndexESService {

    @Autowired
    @Lazy
    IbizproIndexESRepository repository;
    @Override
    public boolean createES(IbizproIndex et) {
        repository.save(et);
        CachedBeanCopier.copy(get(et.getDocid()),et);
        return true;
    }

    @Override
    public void createBatch(List<IbizproIndex> list) {

    }

    @Override
    public boolean updateES(IbizproIndex et) {
        repository.save(et);
        CachedBeanCopier.copy(get(et.getDocid()),et);
        return true;
    }

    @Override
    public void updateBatch(List<IbizproIndex> list) {

    }

    @Override
    public boolean removeES(String key) {
        repository.deleteById(key);
        return true;
    }

    @Override
    public void removeBatch(Collection<String> idList) {

    }
    @Override
    public IbizproIndex get(String key) {
        Optional<IbizproIndex> result = repository.findById(key);
        if(!result.isPresent()){
            IbizproIndex et=new IbizproIndex();
            et.setDocid(key);
            return et;
        }
        else{
            IbizproIndex et=result.get();
            return et;
        }
    }
    @Override
    public boolean save(IbizproIndex et) {
        repository.save(et);
        CachedBeanCopier.copy(get(et.getDocid()),et);
        return true;
    }

    @Override
    public void saveBatch(List<IbizproIndex> list) {

    }

    public Page<IbizproIndex> searchDefault(IbizproIndexSearchContext context){
        Iterable<IbizproIndex> list= repository.search(context.getEsCond());
        List<IbizproIndex> entities = Lists.newArrayList(list);
        return new PageImpl<IbizproIndex>(entities,context.getPageable(),entities.size());
    }
    public Page<IbizproIndex> searchESquery(IbizproIndexSearchContext context){
        Iterable<IbizproIndex> list= repository.search(context.getEsCond());
        List<IbizproIndex> entities = Lists.newArrayList(list);
        return new PageImpl<IbizproIndex>(entities,context.getPageable(),entities.size());
    }
    public Page<IbizproIndex> searchIndexDER(IbizproIndexSearchContext context){
        Iterable<IbizproIndex> list= repository.search(context.getEsCond());
        List<IbizproIndex> entities = Lists.newArrayList(list);
        return new PageImpl<IbizproIndex>(entities,context.getPageable(),entities.size());
    }
}
