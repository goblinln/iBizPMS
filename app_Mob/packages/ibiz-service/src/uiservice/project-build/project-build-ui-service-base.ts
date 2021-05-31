import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProjectBuildService } from '../../service';
import ProjectBuildAuthService from '../../authservice/project-build/project-build-auth-service';

/**
 * 版本UI服务对象基类
 *
 * @export
 * @class ProjectBuildUIServiceBase
 */
export class ProjectBuildUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProjectBuildUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectBuild.json";

    /**
     * Creates an instance of  ProjectBuildUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBuildUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProjectBuildUIServiceBase
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
        this.authService = new ProjectBuildAuthService({context:this.context});
        this.dataService = new ProjectBuildService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProjectBuildUIServiceBase
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
     * @memberof  ProjectBuildUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MOBMPICKUPVIEW:','MOBMPICKUPVIEW');
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBPICKUPVIEW:','MOBPICKUPVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
    }

}