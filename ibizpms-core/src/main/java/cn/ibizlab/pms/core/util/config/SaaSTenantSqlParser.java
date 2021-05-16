package cn.ibizlab.pms.core.util.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

import java.util.List;

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
    @Override
    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
        //处理selectItem表达式
        processSelectItem(plainSelect);
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (!this.getTenantHandler().doTableFilter(fromTable.getName())) {
                plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable));
                if (addColumn) {
                    if (fromItem.getAlias() != null) {
                        plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(fromItem.getAlias().getName() + StringPool.DOT + this.getTenantHandler().getTenantIdColumn())));
                    }else {
                        plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(this.getTenantHandler().getTenantIdColumn())));
                    }
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
        //处理where表达式
        processExcepression(plainSelect.getWhere());
    }

    /**
     * exception 表达式处理
     */
    protected void processExcepression(Expression exception) {
        if (exception == null) {
            return;
        }else if (exception instanceof AndExpression) {
            processExcepression(((AndExpression) exception).getLeftExpression());
            processExcepression(((AndExpression) exception).getRightExpression());
        } else if (exception instanceof OrExpression) {
            processExcepression(((OrExpression) exception).getLeftExpression());
            processExcepression(((OrExpression) exception).getRightExpression());
        } else if (exception instanceof Parenthesis) {
            processExcepression(((Parenthesis) exception).getExpression());
        } else if (exception instanceof NotExpression) {
            processExcepression(((NotExpression) exception).getExpression());
        } else if (exception instanceof InExpression) {
            processExcepression(((InExpression) exception).getLeftExpression());
            processExcepression((Expression)((InExpression) exception).getRightItemsList());
        } else if (exception instanceof EqualsTo) {
            processExcepression(((EqualsTo) exception).getLeftExpression());
            processExcepression(((EqualsTo) exception).getRightExpression());
        }  else if (exception instanceof ExistsExpression) {
            processExcepression(((ExistsExpression) exception).getRightExpression());
        } else if (exception instanceof GreaterThan) {
            processExcepression(((GreaterThan) exception).getLeftExpression());
            processExcepression(((GreaterThan) exception).getRightExpression());
        } else if (exception instanceof GreaterThanEquals) {
            processExcepression(((GreaterThanEquals) exception).getLeftExpression());
            processExcepression(((GreaterThanEquals) exception).getRightExpression());
        } else if (exception instanceof MinorThan) {
            processExcepression(((MinorThan) exception).getLeftExpression());
            processExcepression(((MinorThan) exception).getRightExpression());
        } else if (exception instanceof MinorThanEquals) {
            processExcepression(((MinorThanEquals) exception).getLeftExpression());
            processExcepression(((MinorThanEquals) exception).getRightExpression());
        } else if (exception instanceof NotEqualsTo) {
            processExcepression(((NotEqualsTo) exception).getLeftExpression());
            processExcepression(((NotEqualsTo) exception).getRightExpression());
        } else if (exception instanceof IsBooleanExpression) {
            processExcepression(((IsBooleanExpression) exception).getLeftExpression());
        } else if (exception instanceof IsNullExpression) {
            processExcepression(((IsNullExpression) exception).getLeftExpression());
        } else if (exception instanceof LikeExpression) {
            processExcepression(((LikeExpression) exception).getLeftExpression());
            processExcepression(((LikeExpression) exception).getRightExpression());
        } else if (exception instanceof Between) {
            processExcepression(((Between) exception).getLeftExpression());
            processExcepression(((Between) exception).getBetweenExpressionStart());
            processExcepression(((Between) exception).getBetweenExpressionEnd());
        } else if (exception instanceof Function) {
            if(null != ((Function) exception).getParameters()) {
                for (Expression e : ((Function) exception).getParameters().getExpressions()) {
                    processExcepression(e);
                }
            }
        } else if (exception instanceof CaseExpression) {
            CaseExpression caseExpression = (CaseExpression) exception;
            processExcepression(caseExpression.getElseExpression());
            processExcepression(caseExpression.getSwitchExpression());
            for (WhenClause whenClause : caseExpression.getWhenClauses()) {
                processExcepression(whenClause.getWhenExpression());
                processExcepression(whenClause.getThenExpression());
            }
        }else if(exception instanceof Subtraction){
            processExcepression(((Subtraction) exception).getLeftExpression());
            processExcepression(((Subtraction) exception).getRightExpression());
        }else if(exception instanceof Multiplication){
            processExcepression(((Multiplication) exception).getLeftExpression());
            processExcepression(((Multiplication) exception).getRightExpression());
        }else if(exception instanceof Addition){
            processExcepression(((Addition) exception).getLeftExpression());
            processExcepression(((Addition) exception).getRightExpression());
        }else if(exception instanceof Division){
            processExcepression(((Division) exception).getLeftExpression());
            processExcepression(((Division) exception).getRightExpression());
        } else if (exception instanceof SubSelect) {
            this.processSelectBody(((SubSelect) exception).getSelectBody());
        }
    }

    /**
     * select 中包含 select、function  添加租户id
     */
    protected void processSelectItem(PlainSelect plainSelect) {
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof SelectExpressionItem) {
                Expression selectExcepression = ((SelectExpressionItem) selectItem).getExpression();
                processExcepression(selectExcepression);
            }
        }
    }
}
