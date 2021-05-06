import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TaskestimatestatsService } from '../../service';
import TaskestimatestatsAuthService from '../../authservice/taskestimatestats/taskestimatestats-auth-service';

/**
 * 任务工时统计UI服务对象基类
 *
 * @export
 * @class TaskestimatestatsUIServiceBase
 */
export class TaskestimatestatsUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof TaskestimatestatsUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/taskestimatestats.json";

    /**
     * Creates an instance of  TaskestimatestatsUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskestimatestatsUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  TaskestimatestatsUIServiceBase
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
        this.authService = new TaskestimatestatsAuthService({context:this.context});
        this.dataService = new TaskestimatestatsService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  TaskestimatestatsUIServiceBase
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
     * @memberof  TaskestimatestatsUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}