import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZTaskTeamService } from '../../service';
import IBZTaskTeamAuthService from '../../authservice/ibztask-team/ibztask-team-auth-service';

/**
 * 任务团队UI服务对象基类
 *
 * @export
 * @class IBZTaskTeamUIServiceBase
 */
export class IBZTaskTeamUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZTaskTeamUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZTaskTeam.json";

    /**
     * Creates an instance of  IBZTaskTeamUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskTeamUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZTaskTeamUIServiceBase
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
        this.authService = new IBZTaskTeamAuthService({context:this.context});
        this.dataService = new IBZTaskTeamService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZTaskTeamUIServiceBase
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
     * @memberof  IBZTaskTeamUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}