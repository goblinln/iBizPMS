
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppContextStore, AppGridService } from 'ibiz-vue';
import { AppDefaultGrid } from 'ibiz-vue/src/components/control/app-default-grid/app-default-grid';
import { IPSDEGridUAColumn, IPSDEUIAction, IPSDEUIActionGroup, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';
import { ModelTool, Util } from 'ibiz-core';

export class AppTreeGridService extends AppGridService {

    constructor(opts: any = {}) {
        super(opts);
    }
    
    public handleRequestData(action: string, context: any, data: any = {}, isMerge: boolean = false) {
        let mode: any = this.getMode();
        if (!mode && mode.getDataItems instanceof Function) {
            return data;
        }
        let formItemItems: any[] = mode.getDataItems();
        formItemItems.push({
            name:'items',
            prop:'items',
            dataType: 'QUERYPARAM'
        })
        let requestData: any = {};
        if (isMerge && (data && data.viewparams)) {
            Object.assign(requestData, data.viewparams);
        }
        formItemItems.forEach((item: any) => {
            if (item && item.dataType && Object.is(item.dataType, 'FRONTKEY')) {
                if (item && item.prop) {
                    requestData[item.prop] = context[item.name];
                }
            } else {
                if (item && item.prop) {
                    requestData[item.prop] = data[item.name];
                } else {
                    if (item.dataType && Object.is(item.dataType, "FORMPART")) {
                        Object.assign(requestData, data[item.name]);
                    }
                }
            }
        });
        let tempContext: any = JSON.parse(JSON.stringify(context));
        if (tempContext && tempContext.srfsessionid) {
            tempContext.srfsessionkey = tempContext.srfsessionid;
            delete tempContext.srfsessionid;
        }
        return { context: tempContext, data: requestData };
    }

    public handleResponseData(action: string, data: any = {}, isCreate?: boolean, codelistArray?: any) {
        let model: any = this.getMode();
        if (!(model && model.getDataItems instanceof Function)) {
            return data;
        }
        let item: any = {};
        let dataItems: any[] = model.getDataItems();
        dataItems.push({
            name:'items',
            prop:'items',
            dataType: 'QUERYPARAM'
        })
        dataItems.forEach(dataitem => {
            let val = data.hasOwnProperty(dataitem.prop) ? data[dataitem.prop] : null;
            if (val === null) {
                val = data.hasOwnProperty(dataitem.name) ? data[dataitem.name] : null;
            }
            if ((isCreate === undefined || isCreate === null) && Object.is(dataitem.dataType, 'GUID') && Object.is(dataitem.name, 'srfkey') && (val && !Object.is(val, ''))) {
                isCreate = true;
            }
            item[dataitem.name] = val;
            // 转化代码表
            if (codelistArray && dataitem.codelist) {
                if (codelistArray.get(dataitem.codelist.tag) && codelistArray.get(dataitem.codelist.tag).get(val)) {
                    item[dataitem.name] = codelistArray.get(dataitem.codelist.tag).get(val);
                }
            }
        });
        item.srfuf = data.srfuf ? data.srfuf : (isCreate ? "0" : "1");
        return item;
    }
}



/**
 * 树表格展示插件类
 *
 * @export
 * @class TreeGrid
 * @class TreeGrid
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class TreeGrid extends AppDefaultGrid {

!!!!模版产生代码错误:!!!!模版产生代码错误:----
Tip: If the failing expression is known to be legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${this.controlInstance.codeName}  [in template "TEMPLCODE_zh_CN" at line 131, column 26]
----

}

