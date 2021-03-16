import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TaskestimatestatsService } from '../../service/taskestimatestats/taskestimatestats.service';
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
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions && this.entityModel.getAllPSAppDEUIActions.length > 0) {
            this.entityModel.getAllPSAppDEUIActions.forEach(async (element: any) => {
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            });
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