import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SysDepartmentService } from '../../service';
import SysDepartmentAuthService from '../../authservice/sys-department/sys-department-auth-service';

/**
 * 部门UI服务对象基类
 *
 * @export
 * @class SysDepartmentUIServiceBase
 */
export class SysDepartmentUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SysDepartmentUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysDepartment.json";

    /**
     * Creates an instance of  SysDepartmentUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysDepartmentUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SysDepartmentUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="orgid";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new SysDepartmentAuthService({context:this.context});
        this.dataService = new SysDepartmentService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SysDepartmentUIServiceBase
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
     * @memberof  SysDepartmentUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}