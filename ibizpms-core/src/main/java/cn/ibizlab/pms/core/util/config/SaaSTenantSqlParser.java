package cn.ibizlab.pms.core.util.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

import java.util.List;

public class SaaSTenantSqlParser extends TenantSqlParser {

    /**
     * 处理 PlainSelect
     *
     * @param plainSelect ignore
     * @param addColumn   是否添加租户列,insert into select语句中需要
     */
    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
        processSelectItem(plainSelect);
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (!this.getTenantHandler().doTableFilter(fromTable.getName())) {
                plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable));
                if (addColumn) {
                    if (fromItem.getAlias() != null)
                        plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(fromItem.getAlias().getName() + StringPool.DOT + this.getTenantHandler().getTenantIdColumn())));
                    else
                        plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(this.getTenantHandler().getTenantIdColumn())));
                }
            }
        } else {
            processFromItem(fromItem);
        }
        List<Join> joins = plainSelect.getJoins();
        if (joins != null && joins.size() > 0) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
        processWhere(plainSelect.getWhere());
    }

    /**
     * where 条件中包含function中select  添加租户id
     */
    protected void processWhere(Expression where) {
        if (where == null)
            return;
        if (where instanceof AndExpression) {
            processWhere(((AndExpression) where).getLeftExpression());
            processWhere(((AndExpression) where).getRightExpression());
        } else if (where instanceof OrExpression) {
            processWhere(((OrExpression) where).getLeftExpression());
            processWhere(((OrExpression) where).getRightExpression());
        } else if (where instanceof Function) {
            for (Expression e : ((Function) where).getParameters().getExpressions()) {
                if (e instanceof SubSelect) {
                    this.processSelectBody(((SubSelect) e).getSelectBody());
                }
            }
        }
    }

    /**
     * select 中包含 select、function  添加租户id
     */
    protected void processSelectItem(PlainSelect plainSelect) {
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof SelectExpressionItem) {
                if(((SelectExpressionItem) selectItem).getExpression() instanceof SubSelect){
                    this.processSelectBody(((SubSelect) ((SelectExpressionItem) selectItem).getExpression()).getSelectBody());
                }
            } else if (selectItem instanceof Function) {
                for (Expression e : ((Function) selectItem).getParameters().getExpressions()) {
                    if (e instanceof SubSelect) {
                        this.processSelectBody(((SubSelect) e).getSelectBody());
                    }
                }
            }
        }
    }

}
