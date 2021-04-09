import { DynamicService } from '../../service';
import { IBizEntityModel } from '../entity';
import { IBizViewActionModel } from '../logic/ibiz-view-action-model';
import { IBizViewLogicModel } from '../logic/ibiz-view-logic-model';
import { IBizControlBaseModel } from './ibiz-control-base-model';

/**
 * 实体部件基类
 */
export class IBizMainControlModel extends IBizControlBaseModel {

    /**
     * 应用实体实例引用
     *
     * @private
     * @type {IBizEntityModel}
     * @memberof IBizMainControlModel
     */
    protected $appDataEntity!: IBizEntityModel

    /**
     * 初始化IBizMainControl对象
     * @param opts 额外参数
     *
     * @memberof IBizMainControlModel
     */
    public constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        super(opts, viewRef, parentRef, runtimeData);
        Object.defineProperty(this, '$appDataEntity', { enumerable: false, writable: true })
    }

    /**
     * 加载模型数据(应用实体)
     *
     * @memberof IBizMainControlModel
     */
    public async loaded() {
        await super.loaded();
        await this.loadAppDataEntity();
    }

    /**
     * 加载应用实体模型数据
     *
     * @memberof IBizMainControlModel
     */
    public async loadAppDataEntity(){
        if(this.controlModelData?.getPSAppDataEntity?.modelref && this.controlModelData.getPSAppDataEntity.path){
            let targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.controlModelData.getPSAppDataEntity.path);
            Object.assign(this.controlModelData.getPSAppDataEntity, targetAppEntity);
            delete this.controlModelData.getPSAppDataEntity.modelref;
        }
        if(this.controlModelData.getPSAppDataEntity){
            this.$appDataEntity = new IBizEntityModel(this.controlModelData.getPSAppDataEntity);
        }
    }

    /**
     * 获取应用实体
     * 
     * @memberof IBizMainControlModel
     */
    get appDataEntity() {
        return this.$appDataEntity;
    }

    /**
     * 获取应用实体代码名称
     * 
     * @memberof IBizMainControlModel
     */
    get appDeCodeName() {
        return this.appDataEntity?.codeName?.toLowerCase();
    }

    /**
     * 获取应用实体逻辑名称
     * 
     * @memberof IBizMainControlModel
     */
    get appDeLogicName() {
        return this.appDataEntity?.logicName;
    }

    /**
     * 是否显示忙提示
     *
     * @readonly
     * @memberof IBizMainControlModel
     */
    get showBusyIndicator() {
        return this.controlModelData.showBusyIndicator || true;
    }

    /**
     * 分页条数
     *
     * @readonly
     * @memberof IBizMainControlModel
     */
    get pagingSize() {
        return this.controlModelData.pagingSize;
    }

    /**
     * 排序方向
     * 
     * @memberof IBizMainControlModel
     */
    get minorSortDir() {
        return this.controlModelData.minorSortDir;
    }

    /**
     * 排序字段
     * 
     * @memberof IBizMainControlModel
     */
    get minorSortPSDEF() {
        return this.controlModelData.getMinorSortPSAppDEField;
    }

    /**
     * 视图界面行为集合
     * 
     * @memberof IBizMainControlModel
     */
    get getPSAppViewUIActions() {
        let viewUIActionsArray: Array<any> = [];
        if (this.controlModelData && this.controlModelData.getPSAppViewUIActions) {
            this.controlModelData.getPSAppViewUIActions.forEach((element: any) => {
                viewUIActionsArray.push(new IBizViewActionModel(element, this));
            });
        }
        return viewUIActionsArray;
    }

    /**
     * 视图逻辑集合
     * 
     * @memberof IBizMainControlModel
     */
    get getPSAppViewLogics() {
        let viewLogicsArray: Array<any> = [];
        if (this.controlModelData && this.controlModelData.getPSAppViewLogics) {
            this.controlModelData.getPSAppViewLogics.forEach((element: any) => {
                viewLogicsArray.push(new IBizViewLogicModel(element, this));
            });
        }
        return viewLogicsArray;
    }

    /**
     * 查找实体处理行为
     * 
     * @protected
     * @param {string} action 行为名称
     * @returns
     * @memberof IBizMainControlModel
     */
    protected findHandlerAction(action: string) {
        return this.controlModelData?.getPSControlHandler?.getPSHandlerActions?.find((item: any) => {
            return item.name === action;
        })
    }

    /**
     * 获取实体处理行为的实体方法的逻辑名称
     *
     * @protected
     * @param {string} action行为名称
     * @returns
     * @memberof IBizMainControlModel
     */
    protected findHandlerActionMethodCodeName(action: string) {
        return this.findHandlerAction(action)?.getPSAppDEMethod?.id || this.findHandlerAction(action)?.actionName;
    }

    /**
     * 获取部件自定义查询条件
     *
     * @memberof IBizMainControlModel
     */
     get customCond(){
        return this.findHandlerAction("fetch")?.customCond;
    }

    /**
     * 获取修改行为
     * 
     * @memberof IBizGridModel
     */
    get updateAction() {
        return this.findHandlerActionMethodCodeName("update");
    }

    /**
     * 获取加载草稿行为
     * 
     * @memberof IBizGridModel
     */
    get loaddraftAction() {
        return this.findHandlerActionMethodCodeName("loaddraft") || 'GetDraft';
    }

    /**
     * 获取删除行为
     * 
     * @memberof IBizGridModel
     */
    get removeAction() {
        return this.findHandlerActionMethodCodeName("remove");
    }

    /**
     * 获取加载行为
     * 
     * @memberof IBizGridModel
     */
    get loadAction() {
        return this.findHandlerActionMethodCodeName("load") || 'Get';
    }

    /**
     * 获取查询行为
     *
     * @readonly
     * @memberof IBizMainControlModel
     */
    get fetchAction() {
        return this.findHandlerActionMethodCodeName("fetch") || 'FetchDefault';
    }

    /**
     * 获取新建行为
     * 
     * @memberof IBizGridModel
     */
    get createAction() {
        return this.findHandlerActionMethodCodeName("create");
    }

    /**
     * 获取工作流提交行为
     * 
     * @memberof IBizGridModel
     */
    get WFSubmitAction() {
        return this.findHandlerActionMethodCodeName("WFSUBMIT");
    }

    /**
     * 获取工作流开始流程行为
     * 
     * @memberof IBizGridModel
     */
    get WFStartAction() {
        return this.findHandlerActionMethodCodeName("WFSTART");
    }
}
