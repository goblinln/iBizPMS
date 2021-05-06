import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProjectTeamService } from '../../service';
import ProjectTeamAuthService from '../../authservice/project-team/project-team-auth-service';

/**
 * 项目团队UI服务对象基类
 *
 * @export
 * @class ProjectTeamUIServiceBase
 */
export class ProjectTeamUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProjectTeamUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/ProjectTeam.json";

    /**
     * Creates an instance of  ProjectTeamUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTeamUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProjectTeamUIServiceBase
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
        this.authService = new ProjectTeamAuthService({context:this.context});
        this.dataService = new ProjectTeamService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProjectTeamUIServiceBase
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
     * @memberof  ProjectTeamUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}