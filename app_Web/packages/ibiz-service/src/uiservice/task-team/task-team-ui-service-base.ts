import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { TaskTeamService } from '../../service/task-team/task-team.service';
import TaskTeamAuthService from '../../authservice/task-team/task-team-auth-service';

/**
 * 任务团队UI服务对象基类
 *
 * @export
 * @class TaskTeamUIServiceBase
 */
export class TaskTeamUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof TaskTeamUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/TaskTeam.json";

    /**
     * Creates an instance of  TaskTeamUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskTeamUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  TaskTeamUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new TaskTeamAuthService({context:this.context});
        this.dataService = new TaskTeamService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  TaskTeamUIServiceBase
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
     * @memberof  TaskTeamUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}