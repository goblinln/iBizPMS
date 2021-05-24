import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZStoryActionService } from '../../service';
import IBZStoryActionAuthService from '../../authservice/ibzstory-action/ibzstory-action-auth-service';

/**
 * 需求日志UI服务对象基类
 *
 * @export
 * @class IBZStoryActionUIServiceBase
 */
export class IBZStoryActionUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZStoryActionUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZStoryAction.json";

    /**
     * Creates an instance of  IBZStoryActionUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZStoryActionUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZStoryActionUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IBZStoryActionAuthService({context:this.context});
        this.dataService = new IBZStoryActionService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZStoryActionUIServiceBase
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
     * @memberof  IBZStoryActionUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}