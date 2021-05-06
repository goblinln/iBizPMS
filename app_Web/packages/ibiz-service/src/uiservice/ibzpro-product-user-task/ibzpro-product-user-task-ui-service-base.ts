import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzproProductUserTaskService } from '../../service';
import IbzproProductUserTaskAuthService from '../../authservice/ibzpro-product-user-task/ibzpro-product-user-task-auth-service';

/**
 * 产品汇报用户任务UI服务对象基类
 *
 * @export
 * @class IbzproProductUserTaskUIServiceBase
 */
export class IbzproProductUserTaskUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzproProductUserTaskUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzproProductUserTask.json";

    /**
     * Creates an instance of  IbzproProductUserTaskUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproProductUserTaskUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzproProductUserTaskUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzproProductUserTaskAuthService({context:this.context});
        this.dataService = new IbzproProductUserTaskService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzproProductUserTaskUIServiceBase
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
     * @memberof  IbzproProductUserTaskUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}