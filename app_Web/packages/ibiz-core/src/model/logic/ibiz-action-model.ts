import { mergeDeepLeft } from "ramda";
import { DynamicService } from "../../service";
import { ViewFactory } from "ibiz-core/src/utils/util/view-factory";
import { IBizEntityModel } from "../entity/ibiz-entity-model";

/**
 * 界面行为
 */
export class IBizActionModel {

    /**
     * 界面行为数据
     * 
     * @memberof IBizActionModel
     */
    protected actionData: any;

    /**
     * 默认模型数据
     * 
     * @memberof IBizActionModel
     */
    protected defaultOption: any = {};

    /**
     * 应用上下文
     * 
     * @memberof IBizActionModel
     */
    protected context: any = {};

    /**
     * 初始化 IBizActionModel 对象
     * 
     * @param opts 额外参数
     * 
     * @memberof IBizActionModel
     */
    public constructor(opts: any, context?: any) {
        this.actionData = mergeDeepLeft(opts, this.defaultOption);
        this.context = context ? context : {};
    }

    /**
     * 加载模型数据(实体数据和打开视图)
     * 
     * @memberof IBizActionModel
     */
    public async loaded() {
        if (this.getPSAppDataEntity && this.getPSAppDataEntity.modelref) {
            const targetAppEntityData: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.getPSAppDataEntity.path);
            const targetAppEntity: IBizEntityModel = new IBizEntityModel(targetAppEntityData);
            this.actionData = targetAppEntity.getAppDEUIActionByTag(this.id);
            this.actionData.getPSAppDataEntity = targetAppEntity;
        }
        if (this.getFrontPSAppView && this.getFrontPSAppView.modelref) {
            const targetAppViewData: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.getFrontPSAppView.path);
            const targetAppView: any = ViewFactory.getInstance(targetAppViewData, this.context);
            this.actionData.getFrontPSAppView = targetAppView;
        }
    }

    /**
     * 是否为全局界面行为
     * 
     * @memberof IBizActionModel
     */
    get globalUIAction() {
        return this.actionData.globalUIAction;
    }

    /**
     * 界面行为类型
     * 
     * @memberof IBizActionModel
     */
    get uIActionType() {
        return this.actionData.uIActionType;
    }

    /**
     * 操作后关闭编辑视图
     * 
     * @memberof IBizActionModel
     */
    get closeEditView() {
        return this.actionData.closeEditView;
    }

    /**
     * 支持运行时模型
     * 
     * @memberof IBizActionModel
     */
    get enableRuntimeModel() {
        return this.actionData.enableRuntimeModel;
    }

    /**
     * 界面行为模式
     * 
     * @memberof IBizActionModel
     */
    get uIActionMode() {
        return this.actionData.uIActionMode;
    }

    /**
     * 操作后关闭弹出视图
     * 
     * @memberof IBizActionModel
     */
    get closePopupView() {
        return this.actionData.closePopupView;
    }

    /**
     * 界面行为标记
     * 
     * @memberof IBizActionModel
     */
    get uIActionTag() {
        return this.actionData.uIActionTag;
    }

    /**
     * 行为操作目标
     * 
     * @memberof IBizActionModel
     */
    get actionTarget() {
        return this.actionData.actionTarget;
    }

    /**
     * 标题
     * 
     * @memberof IBizActionModel
     */
    get caption() {
        return this.actionData.caption;
    }

    /**
     * 代码名称
     * 
     * @memberof IBizActionModel
     */
    get codeName() {
        return this.actionData.codeName;
    }

    /**
     * 完全代码名称
     * 
     * @memberof IBizActionModel
     */
    get fullCodeName() {
        return this.actionData.fullCodeName;
    }

    /**
     * 行为组
     * 
     * @memberof IBizActionModel
     */
    get group() {
        return this.actionData.group;
    }

    /**
     * 操作超时时长（毫秒）
     * 
     * @memberof IBizActionModel
     */
    get timeout() {
        return this.actionData.timeout;
    }

    /**
     * 应用实体
     * 
     * @memberof IBizActionModel
     */
    get getPSAppDataEntity() {
        return this.actionData.getPSAppDataEntity;
    }

    /**
     * 操作后刷新当前界面
     * 
     * @memberof IBizActionModel
     */
    get reloadData() {
        return this.actionData.reloadData;
    }

    /**
     * 刷新引用视图模式
     * 
     * @memberof IBizActionModel
     */
    get refreshMode() {
        return this.actionData.refreshMode;
    }

    /**
     * 启用用户操作确认
     * 
     * @memberof IBizActionModel
     */
    get enableConfirm() {
        return this.actionData.enableConfirm;
    }

    /**
     * 显示处理提示
     * 
     * @memberof IBizActionModel
     */
    get showBusyIndicator() {
        return this.actionData.hasOwnProperty('showBusyIndicator') ? this.actionData.showBusyIndicator : true;
    }

    /**
     * 导航上下文集合
     * 
     * @memberof IBizActionModel
     */
    get getPSNavigateContexts() {
        return this.actionData.getPSNavigateContexts;
    }

    /**
     * 导航参数集合
     * 
     * @memberof IBizActionModel
     */
    get getPSNavigateParams() {
        return this.actionData.getPSNavigateParams;
    }

    /**
     * 下一步界面行为
     * 
     * @memberof IBizActionModel
     */
    get getNextPSUIAction() {
        return this.actionData.getNextPSUIAction;
    }

    /**
     * 应用实体方法
     * 
     * @memberof IBizActionModel
     */
    get getPSAppDEMethod() {
        return this.actionData.getPSAppDEMethod;
    }

    /**
     * 界面行为参数对象
     * 
     * @memberof IBizActionModel
     */
    get uIActionParamJO() {
        return this.actionData.uIActionParamJO;
    }

    /**
     * 是否有视图逻辑
     * 
     * @memberof IBizActionModel
     */
    get hasViewLogic() {
        return this.actionData.hasViewLogic;
    }

    /**
     * 名称
     * 
     * @memberof IBizActionModel
     */
    get name() {
        return this.actionData.name;
    }

    /**
     * 标识
     * 
     * @memberof IBizActionModel
     */
    get id() {
        return this.actionData.id;
    }

    /**
     * 打开视图
     * 
     * @memberof IBizActionModel
     */
    get getFrontPSAppView() {
        return this.actionData.getFrontPSAppView;
    }

    /**
     * 是否先保存目标数据
     * 
     * @memberof IBizActionModel
     */
    get saveTargetFirst() {
        return this.actionData.saveTargetFirst;
    }

    /**
     * 前台处理模式(OPENHTMLPAGE:打开html;TOP|WIZARD:打开顶级视图，打开顶级视图或向导（模态）；OTHER：用户自定义)
     * 
     * @memberof IBizActionModel
     */
    get frontProcessType() {
        return this.actionData.frontProcessType;
    }

    /**
     * 打开html
     * 
     * @memberof IBizActionModel
     */
    get htmlPageUrl() {
        return this.actionData.htmlPageUrl;
    }

    /**
     * 自定义确认消息
     * 
     * @memberof IBizActionModel
     */
    get confirmMsg() {
        return this.actionData.confirmMsg;
    }

    /**
     * 提示成功信息
     * 
     * @memberof IBizActionModel
     */
    get successMsg() {
        return this.actionData.successMsg;
    }

    /**
     * 获取实体行为
     * 
     * @memberof IBizActionModel
     */
    get getPSDEAction() {
        return this.actionData.getPSDEAction;
    }

}