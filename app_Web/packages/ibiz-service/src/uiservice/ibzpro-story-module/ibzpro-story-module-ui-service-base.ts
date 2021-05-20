import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZProStoryModuleService } from '../../service';
import IBZProStoryModuleAuthService from '../../authservice/ibzpro-story-module/ibzpro-story-module-auth-service';

/**
 * 需求模块UI服务对象基类
 *
 * @export
 * @class IBZProStoryModuleUIServiceBase
 */
export class IBZProStoryModuleUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZProStoryModuleUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZProStoryModule.json";

    /**
     * Creates an instance of  IBZProStoryModuleUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProStoryModuleUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZProStoryModuleUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="orgid";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IBZProStoryModuleAuthService({context:this.context});
        this.dataService = new IBZProStoryModuleService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZProStoryModuleUIServiceBase
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
     * @memberof  IBZProStoryModuleUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}