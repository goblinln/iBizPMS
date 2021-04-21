import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzproProjectUserTaskService } from '../../service';
import IbzproProjectUserTaskAuthService from '../../authservice/ibzpro-project-user-task/ibzpro-project-user-task-auth-service';

/**
 * 项目汇报用户任务UI服务对象基类
 *
 * @export
 * @class IbzproProjectUserTaskUIServiceBase
 */
export class IbzproProjectUserTaskUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzproProjectUserTaskUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzproProjectUserTask.json";

    /**
     * Creates an instance of  IbzproProjectUserTaskUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzproProjectUserTaskUIServiceBase
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
        this.authService = new IbzproProjectUserTaskAuthService({context:this.context});
        this.dataService = new IbzproProjectUserTaskService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions() && (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[]).length > 0) {
            for(let element of (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[])){
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  IbzproProjectUserTaskUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set(':','');
    }

}