import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SubTaskService } from '../../service/sub-task/sub-task.service';
import SubTaskAuthService from '../../authservice/sub-task/sub-task-auth-service';

/**
 * 任务UI服务对象基类
 *
 * @export
 * @class SubTaskUIServiceBase
 */
export class SubTaskUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SubTaskUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/SubTask.json";

    /**
     * Creates an instance of  SubTaskUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SubTaskUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SubTaskUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['status1','isfavorites','tasktype'];
        this.authService = new SubTaskAuthService({context:this.context});
        this.dataService = new SubTaskService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SubTaskUIServiceBase
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
     * @memberof  SubTaskUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}