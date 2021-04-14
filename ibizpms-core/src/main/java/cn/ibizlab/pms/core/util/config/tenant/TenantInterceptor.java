package cn.ibizlab.pms.core.util.config.tenant;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class TenantInterceptor extends TenantLineInnerInterceptor {

    @Autowired
    private TenantProperties tenantProperties;

    /**
     * 处理 insert into select
     * <p>
     * 进入这里表示需要 insert 的表启用了多租户,则 select 的表都启动了
     * 租户列使用别名
     *
     * @param selectBody SelectBody
     */
    @Override
    protected void processInsertSelect(SelectBody selectBody) {
        PlainSelect plainSelect = (PlainSelect) selectBody;
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable));

            //设置别名
            appendSelectInsertItem(plainSelect);
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            appendSelectItem(plainSelect.getSelectItems());
            processInsertSelect(subSelect.getSelectBody());
        }
    }

    protected void appendSelectInsertItem(PlainSelect plainSelect) {
        if (CollectionUtils.isEmpty(plainSelect.getSelectItems())) return;
        if (plainSelect.getSelectItems().size() == 1) {
            SelectItem item = plainSelect.getSelectItems().get(0);
            if (item instanceof AllColumns || item instanceof AllTableColumns) return;
        }
        SelectExpressionItem selectExpressionItem = null;
        if(plainSelect.getFromItem().getAlias() != null)
            selectExpressionItem = new SelectExpressionItem(new Column(plainSelect.getFromItem().getAlias() + this.getTenantLineHandler().getTenantIdColumn()));
        else
            selectExpressionItem = new SelectExpressionItem(new Column(this.getTenantLineHandler().getTenantIdColumn()));
        plainSelect.getSelectItems().add(selectExpressionItem);
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (tenantProperties.getIgnoreMappers().contains(ms.getId())) {
            return ;
        }
        super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
    }

    public void setTenantProperties(TenantProperties tenantProperties) {
        this.tenantProperties = tenantProperties;
    }
}
