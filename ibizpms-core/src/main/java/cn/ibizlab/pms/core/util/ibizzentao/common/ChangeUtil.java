package cn.ibizlab.pms.core.util.ibizzentao.common;

import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.javers.core.IgnoredClassesStrategy;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ChangeUtil {

    /**
     * @param old 旧数据对象
     * @param nw  新数据对象
     * @param <T>
     * @return
     */
    public static <T extends EntityMP> List<History> diff(T old, T nw) {
        return diff(old, nw, null, null, null);
    }


    /**
     * @param old     旧数据对象
     * @param nw      新数据对象
     * @param ignores 指定忽略属性
     * @param <T>
     * @return
     */
    public static <T extends EntityMP> List<History> diff(T old, T nw, String[] ignores) {
        return diff(old, nw, ignores, null, null);
    }

    /**
     * @param old          旧数据对象
     * @param nw           新数据对象
     * @param includeAttrs 指定包含属性
     * @param <T>
     * @return
     */
    public static <T extends EntityMP> List<History> diffInclude(T old, T nw, String[] includeAttrs) {
        return diff(old, nw, null, includeAttrs, null);
    }

    /**
     * @param old          旧数据对象
     * @param nw           新数据对象
     * @param ignores      指定忽略属性
     * @param includeAttrs 指定包含属性
     * @param diffAttrs    指定展示diff信息
     * @param <T>
     * @return
     */
    public static <T extends EntityMP> List<History> diff(T old, T nw, String[] ignores, String[] includeAttrs, String[] diffAttrs) {
        ArrayList<History> changes = new ArrayList<>();
        JaversBuilder javersBuilder = JaversBuilder.javers().registerIgnoredClassesStrategy(new IgnoredClassesStrategy() {
            @Override
            public boolean isIgnored(Class<?> aClass) {
                //忽略 Entity 中 MethodHandler 属性
                if (aClass == org.apache.ibatis.javassist.util.proxy.MethodHandler.class)
                    return true;
                return false;
            }
        });
        // 忽略指定属性
        if (ignores != null) {
            javersBuilder.registerValueObject(new ValueObjectDefinition(old.getClass(), Lists.newArrayList(ignores)));
        }
        Diff dif = javersBuilder.build().compare(old, nw);
        for (Change change : dif.getChanges()) {
            if (change instanceof ValueChange) {
                ValueChange vchange = (ValueChange) change;
                History history = new History();
                history.setField(vchange.getPropertyName());
                history.setOld(vchange.getLeft() == null ? "" : vchange.getLeft().toString());
                history.setIbiznew(vchange.getRight() == null ? "" : vchange.getRight().toString());
                if (StringUtils.isBlank(history.getOld()) && StringUtils.isBlank(history.getIbiznew())) {
                    continue;
                }

                //指定包含属性
                if (includeAttrs != null && !Lists.newArrayList(includeAttrs).contains(vchange.getPropertyName())) {
                    continue;
                }

                //diff 信息
                if (diffAttrs != null && Lists.newArrayList(diffAttrs).contains(vchange.getPropertyName())) {
                    String strDiff = String.format("001- <del>%s</del>\n001+ <ins>%s</ins>", history.getOld(), history.getIbiznew());
                    history.setDiff(strDiff);
                }
                changes.add(history);
            }
        }
        return changes;
    }

}
