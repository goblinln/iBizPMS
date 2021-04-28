import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SysTeamMemberService } from '../../service';
import SysTeamMemberAuthService from '../../authservice/sys-team-member/sys-team-member-auth-service';

/**
 * 组成员UI服务对象基类
 *
 * @export
 * @class SysTeamMemberUIServiceBase
 */
export class SysTeamMemberUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SysTeamMemberUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysTeamMember.json";

    /**
     * Creates an instance of  SysTeamMemberUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysTeamMemberUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SysTeamMemberUIServiceBase
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
        this.authService = new SysTeamMemberAuthService({context:this.context});
        this.dataService = new SysTeamMemberService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SysTeamMemberUIServiceBase
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
     * @memberof  SysTeamMemberUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}