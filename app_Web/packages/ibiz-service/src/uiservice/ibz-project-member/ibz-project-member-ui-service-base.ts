import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzProjectMemberService } from '../../service';
import IbzProjectMemberAuthService from '../../authservice/ibz-project-member/ibz-project-member-auth-service';

/**
 * 项目相关成员UI服务对象基类
 *
 * @export
 * @class IbzProjectMemberUIServiceBase
 */
export class IbzProjectMemberUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzProjectMemberUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzProjectMember.json";

    /**
     * Creates an instance of  IbzProjectMemberUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProjectMemberUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzProjectMemberUIServiceBase
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
        this.authService = new IbzProjectMemberAuthService({context:this.context});
        this.dataService = new IbzProjectMemberService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzProjectMemberUIServiceBase
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
     * @memberof  IbzProjectMemberUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}