import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzProjectMemberService } from '../../service/ibz-project-member/ibz-project-member.service';
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
     * @memberof  IbzProjectMemberUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}