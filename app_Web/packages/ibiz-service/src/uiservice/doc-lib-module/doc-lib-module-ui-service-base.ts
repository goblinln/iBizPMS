import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { DocLibModuleService } from '../../service';
import DocLibModuleAuthService from '../../authservice/doc-lib-module/doc-lib-module-auth-service';

/**
 * 文档库分类UI服务对象基类
 *
 * @export
 * @class DocLibModuleUIServiceBase
 */
export class DocLibModuleUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof DocLibModuleUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/DocLibModule.json";

    /**
     * Creates an instance of  DocLibModuleUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibModuleUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  DocLibModuleUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="orgid";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['docqtype','isfavourites'];
        this.authService = new DocLibModuleAuthService({context:this.context});
        this.dataService = new DocLibModuleService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  DocLibModuleUIServiceBase
     */
    protected async initActionMap(): Promise<void> {
        const actions = this.entityModel?.getAllPSAppDEUIActions() as IPSAppDEUIAction[];
        if (actions && actions.length > 0) {
            for (const element of actions) {
                const targetAction: any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  DocLibModuleUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('REDIRECTVIEW:','REDIRECTVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('PICKUPVIEW:','PICKUPVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MPICKUPVIEW:','MPICKUPVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}