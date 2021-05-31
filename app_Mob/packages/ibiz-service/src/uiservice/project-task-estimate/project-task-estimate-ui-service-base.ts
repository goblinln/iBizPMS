import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProjectTaskEstimateService } from '../../service';
import ProjectTaskEstimateAuthService from '../../authservice/project-task-estimate/project-task-estimate-auth-service';

/**
 * 任务预计UI服务对象基类
 *
 * @export
 * @class ProjectTaskEstimateUIServiceBase
 */
export class ProjectTaskEstimateUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProjectTaskEstimateUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectTaskEstimate.json";

    /**
     * Creates an instance of  ProjectTaskEstimateUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskEstimateUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProjectTaskEstimateUIServiceBase
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
        this.authService = new ProjectTaskEstimateAuthService({context:this.context});
        this.dataService = new ProjectTaskEstimateService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProjectTaskEstimateUIServiceBase
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
     * @memberof  ProjectTaskEstimateUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}