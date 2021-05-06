import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProjectTaskestimateService } from '../../service';
import ProjectTaskestimateAuthService from '../../authservice/project-taskestimate/project-taskestimate-auth-service';

/**
 * 项目工时统计UI服务对象基类
 *
 * @export
 * @class ProjectTaskestimateUIServiceBase
 */
export class ProjectTaskestimateUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProjectTaskestimateUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/ProjectTaskestimate.json";

    /**
     * Creates an instance of  ProjectTaskestimateUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskestimateUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProjectTaskestimateUIServiceBase
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
        this.authService = new ProjectTaskestimateAuthService({context:this.context});
        this.dataService = new ProjectTaskestimateService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProjectTaskestimateUIServiceBase
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
     * @memberof  ProjectTaskestimateUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}