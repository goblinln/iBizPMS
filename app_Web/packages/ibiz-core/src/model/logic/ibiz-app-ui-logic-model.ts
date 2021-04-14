import { mergeDeepLeft } from "ramda";
import { ViewFactory } from "ibiz-core/src/utils/util/view-factory";
import { DynamicService } from "../../service";

/**
 * 应用界面预置逻辑
 */
export class IBizAppUILogicModel {

    /**
     * 应用界面预置逻辑数据
     * 
     * @memberof IBizAppUILogicModel
     */
    private appUILogicData: any;

    /**
     * 应用上下文
     * 
     * @memberof IBizAppUILogicModel
     */
    protected context: any = {};

    /**
     * 默认模型数据
     * 
     * @memberof IBizAppUILogicModel
     */
    protected defaultOption: any = {};

    /**
     * 父容器对象
     * 
     * @memberof IBizAppUILogicModel
     */
    private parentContainer: any;

    /**
     * 初始化 IBizAppUILogicModel 对象
     * 
     * @param opts 应用界面预置逻辑参数
     * 
     * @memberof IBizAppUILogicModel
     */
    constructor(opts: any, otherOpts: any, context?: any) {
        this.appUILogicData = mergeDeepLeft(opts, this.defaultOption);
        this.parentContainer = otherOpts;
        this.context = context ? context : {};
    }

    /**
     * 加载模型数据(应用视图数据)
     * 
     * @memberof IBizAppUILogicModel
     */
    public async loaded() {
        let newDataPSAppView: any;
        if (this.getNewDataPSAppView && this.getNewDataPSAppView.modelref) {
            newDataPSAppView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.getNewDataPSAppView.path);
        } else {
            const targetNewView: any = this.getParentContainerViewRefs("NEWDATA");
            if(targetNewView){
                newDataPSAppView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(targetNewView.path);
            }
        }
        if(newDataPSAppView){
            this.appUILogicData.getNewDataPSAppView = ViewFactory.getInstance(newDataPSAppView, this.context);
        }

        let openDataPSAppView: any;
        if (this.getOpenDataPSAppView && this.getOpenDataPSAppView.modelref) {
            openDataPSAppView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.getOpenDataPSAppView.path);
        } else {
            const targetOpenView: any = this.getParentContainerViewRefs("EDITDATA");
            if(targetOpenView){
                openDataPSAppView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(targetOpenView.path);
            }
        }
        if(openDataPSAppView){
            this.appUILogicData.getOpenDataPSAppView = ViewFactory.getInstance(openDataPSAppView, this.context);
        }
    }

    /**
     * 加载新建向导视图模型数据
     * 
     * @memberof IBizAppUILogicModel
     */
    public async loadedWizardPSAppView() {
        if (this.getWizardPSAppView && this.getWizardPSAppView.getRefPSAppView && this.getWizardPSAppView.getRefPSAppView.modelref) {
            const targetWizardAppView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.getWizardPSAppView.getRefPSAppView.path);
            this.appUILogicData.getWizardPSAppView = ViewFactory.getInstance(targetWizardAppView, this.context);
        }
    }

    /**
     * 加载批添加新建数据视图集合
     * 
     * @memberof IBizAppUILogicModel
     */
    public async loadedBatchAddPSAppViews() {
        if (this.getBatchAddPSAppViews && this.getBatchAddPSAppViews.length > 0) {
            for (let batchAddPSAppView of this.getBatchAddPSAppViews) {
                if (batchAddPSAppView && batchAddPSAppView.getRefPSAppView && batchAddPSAppView.getRefPSAppView.modelref) {
                    let targetBatchAddPSAppView: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(batchAddPSAppView.getRefPSAppView.path);
                    let targetAppView: any = ViewFactory.getInstance(targetBatchAddPSAppView, this.context);
                    await targetAppView.loaded();
                    Object.assign(batchAddPSAppView, { view: targetAppView });
                    delete batchAddPSAppView.getRefPSAppView;
                }
            }
        }
    }

    /**
     * 加载目标视图数据
     * 
     * @memberof IBizAppUILogicModel
     */
    public async loadedTargetAppView(context: any, model: any) {
        if (model.modelref && model.path) {
            const targetAppView = await DynamicService.getInstance(context).getAppViewModelJsonData(model.path);
            return targetAppView;
        }
    }

    /**
     * 向导添加后操作
     * 
     * @memberof IBizAppUILogicModel
     */
    get actionAfterWizard() {
        return this.appUILogicData.actionAfterWizard;
    }

    /**
     * 只支持批添加
     * 
     * @memberof IBizAppUILogicModel
     */
    get batchAddOnly() {
        return this.appUILogicData.batchAddOnly;
    }

    /**
     * 内建逻辑
     * 
     * @memberof IBizAppUILogicModel
     */
    get builtinLogic() {
        return this.appUILogicData.builtinLogic;
    }

    /**
     * 动态路劲
     * 
     * @memberof IBizAppUILogicModel
     */
    get dynaModelFilePath() {
        return this.appUILogicData.dynaModelFilePath;
    }

    /**
     * 支持批添加
     * 
     * @memberof IBizAppUILogicModel
     */
    get enableBatchAdd() {
        return this.appUILogicData.enableBatchAdd;
    }

    /**
     * 支持向导添加
     * 
     * @memberof IBizAppUILogicModel
     */
    get enableWizardAdd() {
        return this.appUILogicData.enableWizardAdd;
    }

    /**
     * 默认新建数据视图
     * 
     * @memberof IBizAppUILogicModel
     */
    get getNewDataPSAppView() {
        return this.appUILogicData.getNewDataPSAppView;
    }

    /**
     * 新建向导视图
     * 
     * @memberof IBizAppUILogicModel
     */
    get getWizardPSAppView() {
        return this.appUILogicData.getWizardPSAppView;
    }

    /**
     * 新建数据视图数组
     * 
     * @memberof IBizAppUILogicModel
     */
    get getNewDataPSAppViews() {
        return this.appUILogicData.getNewDataPSAppViews;
    }

    /**
     * 应用界面逻辑引用视图集合
     * 
     * @memberof IBizAppUILogicModel
     */
    get getPSAppUILogicRefViews() {
        return this.appUILogicData.getPSAppUILogicRefViews;
    }

    /**
     * 逻辑类型
     * 
     * @memberof IBizAppUILogicModel
     */
    get logicType() {
        return this.appUILogicData.logicType;
    }

    /**
     * 名称
     * 
     * @memberof IBizAppUILogicModel
     */
    get name() {
        return this.appUILogicData.name;
    }

    /**
     * 默认打开数据视图
     * 
     * @memberof IBizAppUILogicModel
     */
    get getOpenDataPSAppView() {
        return this.appUILogicData.getOpenDataPSAppView;
    }

    /**
     * 获取父容器对象
     * 
     * @memberof IBizAppUILogicModel
     */
    get getParentContainer() {
        return this.parentContainer;
    }

    /**
     * 界面逻辑类型
     * 
     * @memberof IBizAppUILogicModel
     */
    get viewLogicType() {
        return this.appUILogicData.viewLogicType;
    }

    /**
     * 批添加新建数据视图集合
     * 
     * @memberof IBizAppUILogicModel
     */
    get getBatchAddPSAppViews() {
        return this.appUILogicData.getBatchAddPSAppViews;
    }

    /**
     * 获取父容器对象指定视图应用
     * 
     * @memberof IBizAppUILogicModel
     */
    private getParentContainerViewRefs(type: string) {
        if (this.parentContainer.getPSAppViewRefs) {
            const targetData: any = this.parentContainer.getPSAppViewRefs.find((item: any) => {
                return item.name === type;
            })
            return targetData?.getRefPSAppView;
        }
    }

}