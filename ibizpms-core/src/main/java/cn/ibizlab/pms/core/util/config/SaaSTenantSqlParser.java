package cn.ibizlab.pms.core.util.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
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
        }else if(where instanceof Parenthesis) {
            processWhere(((Parenthesis)where).getExpression());
        }else if(where instanceof NotExpression) {
            processWhere(((NotExpression)where).getExpression());
        }else if (where instanceof InExpression) {
            processWhere(((InExpression) where).getLeftExpression());
           if(((InExpression) where).getRightItemsList() instanceof SubSelect) {
               this.processSelectBody(((SubSelect) ((InExpression) where).getRightItemsList()).getSelectBody());
           }
        }else if (where instanceof EqualsTo){
            processWhere(((EqualsTo) where).getLeftExpression());
            processWhere(((EqualsTo) where).getRightExpression());
        }else if (where instanceof CaseExpression) {
            for(WhenClause w : ((CaseExpression) where).getWhenClauses()) {
                processWhere(w.getWhenExpression());
                processWhere(w.getThenExpression());
            }
            processWhere(((CaseExpression) where).getElseExpression());
        }else if (where instanceof ExistsExpression) {
            processWhere(((ExistsExpression) where).getRightExpression());
        }else if(where instanceof GreaterThan) {
            processWhere(((GreaterThan) where).getLeftExpression());
            processWhere(((GreaterThan) where).getRightExpression());
        }else if(where instanceof GreaterThanEquals) {
            processWhere(((GreaterThanEquals) where).getLeftExpression());
            processWhere(((GreaterThanEquals) where).getRightExpression());
        }else if(where instanceof MinorThan) {
            processWhere(((MinorThan) where).getLeftExpression());
            processWhere(((MinorThan) where).getRightExpression());
        }else if(where instanceof MinorThanEquals) {
            processWhere(((MinorThanEquals) where).getLeftExpression());
            processWhere(((MinorThanEquals) where).getRightExpression());
        }else if (where instanceof NotEqualsTo){
            processWhere(((NotEqualsTo) where).getLeftExpression());
            processWhere(((NotEqualsTo) where).getRightExpression());
        }else if (where instanceof IsBooleanExpression) {
            processWhere(((IsBooleanExpression) where).getLeftExpression());
        }else if(where instanceof IsNullExpression) {
            processWhere(((IsNullExpression) where).getLeftExpression());
        }else if (where instanceof LikeExpression) {
            processWhere(((LikeExpression) where).getLeftExpression());
            processWhere(((LikeExpression) where).getRightExpression());
        }else if(where instanceof Between) {
            processWhere(((Between) where).getLeftExpression());
            processWhere(((Between) where).getBetweenExpressionStart());
            processWhere(((Between) where).getBetweenExpressionEnd());
        }
        else if (where instanceof Function) {
            if (null != ((Function) where).getParameters()){
                for (Expression e : ((Function) where).getParameters().getExpressions()) {
                    if (e instanceof SubSelect) {
                        this.processSelectBody(((SubSelect) e).getSelectBody());
                    }
                }
            }

        }else if (where instanceof SubSelect) {
            this.processSelectBody(((SubSelect) where).getSelectBody());
        }
    }

    /**
     * select 中包含 select、function  添加租户id
     */
    protected void processSelectItem(PlainSelect plainSelect) {
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof SelectExpressionItem) {
//                if(((SelectExpressionItem) selectItem).getExpression() instanceof SubSelect){
//                    this.processSelectBody(((SubSelect) ((SelectExpressionItem) selectItem).getExpression()).getSelectBody());
//                }
                processWhere(((SelectExpressionItem) selectItem).getExpression());
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
