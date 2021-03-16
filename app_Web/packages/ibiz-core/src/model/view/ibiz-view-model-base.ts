import { mergeDeepLeft } from 'ramda';
import { IBizControlBaseModel } from './../control/ibiz-control-base-model';
import { IBizViewActionModel, IBizViewLogicModel } from "..";
import { DynamicService } from '../../service';
import { ControlFactory } from '../../utils';
/**
 * 视图模型基类
 * 
 * @class IBizTreeViewModel
 */
export class IBizViewModelBase {

    /**
     * 默认模型数据
     * 
     * @memberof IBizViewModelBase
     */
    protected defaultOption: any = {};

    /**
     * 应用上下文
     * 
     * @memberof IBizViewModelBase
     */
    protected context: any = {};

    /**
     * 视图模型数据
     * 
     * @memberof IBizViewModelBase
     */
    protected viewModelData: any;

    /**
     * 部件模型实例集合
     * 
     * @memberof IBizViewModelBase
     */
    protected $controlsMap: Map<string, any> = new Map();

    /**
     * 初始化IBizViewModelBase对象
     * @param opts 额外参数
     * 
     * @memberof IBizViewModelBase
     */
    public constructor(opts: any, context: any) {
        // 不可枚举
        Object.defineProperty(this, '$controlsMap', { enumerable: false, writable: true })
        Object.defineProperty(this, 'viewModelData', { enumerable: false, writable: true })
        this.viewModelData = mergeDeepLeft(opts, this.defaultOption);
        this.context = context ? context : this.context;
    }

    /**
     * 加载数据模型（获取所有子部件）
     * 
     * @memberof IBizViewModelBase
     */
    public async loaded() {
        if (this.controls?.length > 0) {
            for (const control of this.controls) {
                // 加载模型数据
                if (control.modelref && control.path) {
                    const targetControl: any = await DynamicService.getInstance(this.context).getAppCtrlModelJsonData(control.path);
                    Object.assign(control, targetControl);
                    delete control.modelref;
                }
                const runtimeData = {
                    context: this.context
                }
                // 实例化部件模型对象
                let ctrlInstance = ControlFactory.getInstance(control, this, null, runtimeData);
                if (ctrlInstance) {
                    await ctrlInstance.loaded()
                }
            }
        }
    }

    /**
     * 加载数据模型（关系路径数据）
     * 
     * @memberof IBizViewModelBase
     */
    public async loadedAppDERSPathParam() {
        if (this.getPSAppDERSPaths && this.getPSAppDERSPaths.length > 0) {
            for (const appDeRsPath of this.getPSAppDERSPaths) {
                if (appDeRsPath && appDeRsPath.length > 0) {
                    for (const singleAppDeRsPath of appDeRsPath) {
                        if (singleAppDeRsPath.getMajorPSAppDataEntity && singleAppDeRsPath.getMajorPSAppDataEntity.modelref && singleAppDeRsPath.getMajorPSAppDataEntity.path) {
                            const targetEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(singleAppDeRsPath.getMajorPSAppDataEntity.path);
                            Object.assign(singleAppDeRsPath, { getMajorPSAppDataEntity: targetEntity });
                            delete singleAppDeRsPath.getMajorPSAppDataEntity.modelref;
                        }
                    }
                }
            }
        }
    }

    /**
     * 加载数据模型（非预置视图引用数据）
     * 
     * @memberof IBizViewModelBase
     */
    public async loadedAppViewRef() {
        let targetAppViewRefs: Array<any> = [];
        if (this.getPSAppViewRefs && this.getPSAppViewRefs.length > 0) {
            for (const appViewRef of this.getPSAppViewRefs) {
                if (appViewRef && (appViewRef.name !== "NEWDATA" && appViewRef.name !== "EDITDATA") && appViewRef.getRefPSAppView && appViewRef.getRefPSAppView.modelref && appViewRef.getRefPSAppView.path) {
                    const targetView: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(appViewRef.getRefPSAppView.path);
                    Object.assign(targetView, { name: appViewRef.name });
                    targetAppViewRefs.push(targetView);
                }
            }
        }
        return targetAppViewRefs;
    }

    /**
     * 添加部件模型实例
     *
     * @param {IBizControlBaseModel} control
     * @memberof IBizViewModelBase
     */
    public addControl(control: IBizControlBaseModel) {
        this.$controlsMap.set(control.name, control);
    }

    /**
     * 获取对应名称的部件模型实例
     *
     * @param {string} name 部件名称
     * @memberof IBizViewModelBase
     */
    public getControl(name: string) {
        return this.$controlsMap.get(name);
    }

    /**
     * 获取视图的所有部件
     *
     * @param {IBizControlBaseModel} control
     * @memberof IBizViewModelBase
     */
    public getAllControls() {
        return [...this.$controlsMap]
    }

    /**
     * 通过control名称获取部件模型json数据
     * 
     * @memberof IBizViewModelBase
     */
    protected getControlByName(name: string) {
        if (this.controls && this.controls.length > 0) {
            return this.controls.find((item: any) => {
                return item.name == name;
            })
        }
    }

    /**
     * 视图类型
     * 
     * @memberof IBizViewModelBase
     */
    get viewType() {
        return this.viewModelData.viewType;
    }

    /**
     * 视图标题
     * 
     * @memberof IBizViewModelBase
     */
    get caption() {
        return this.viewModelData.caption;
    }

    /**
     * 实体视图代码名称
     * 
     * @memberof IBizViewBase
     */
    get getPSDEViewCodeName(): any {
        return this.viewModelData?.getPSDEViewCodeName;
    }

    /**
     * 视图对象引用
     * 
     * @memberof IBizViewBase
     */
    get getPSAppViewRefs() {
        return this.viewModelData?.getPSAppViewRefs;
    }

    /**
     * 视图代码名称
     * 
     * @memberof IBizViewModelBase
     */
    get codeName() {
        return this.viewModelData.codeName;
    }
    /**
     * 视图部署id
     * 
     * @memberof IBizViewModelBase
     */
    get deployId() {
        return this.viewModelData.deployId;
    }

    /**
     * 视图动态模型文件路径
     * 
     * @memberof IBizViewModelBase
     */
    get dynaModelFilePath() {
        return this.viewModelData.dynaModelFilePath;
    }

    /**
     * 视图所有部件
     * 
     * @memberof IBizViewModelBase
     */
    get controls() {
        return this.viewModelData.getPSControls;
    }

    /**
     * 视图是否默认加载
     * 
     * @memberof IBizViewModelBase
     */
    get loadDefault() {
        return this.viewModelData.hasOwnProperty("loadDefault") ? this.viewModelData.loadDefault : true;
    }

    /**
     * 视图名称
     * 
     * @memberof IBizViewModelBase
     */
    get name() {
        return this.viewModelData.name;
    }

    /**
     * 样式
     * 
     * @memberof IBizViewModelBase
     */
    get getPSSysCss() {
        return this.viewModelData.getPSSysCss;
    }

    /**
     * 是否只读
     * 
     * @memberof IBizViewModelBase
     */
    get readOnly() {
        return this.viewModelData.readOnly;
    }

    /**
     * 是否为重定向视图
     * 
     * @memberof IBizViewModelBase
     */
    get redirectView() {
        return this.viewModelData.redirectView;
    }

    /**
     * 是否展示数据信息栏
     * 
     * @memberof IBizViewModelBase
     */
    get showDataInfoBar() {
        return this.viewModelData.showDataInfoBar;
    }

    /**
     * 视图标题
     * 
     * @memberof IBizViewModelBase
     */
    get title() {
        return this.viewModelData.title;
    }

    /**
     * 视图样式
     * 
     * @memberof IBizViewModelBase
     */
    get viewStyle() {
        return this.viewModelData.viewStyle;
    }

    /**
     * 是否展示视图标题栏
     * 
     * @memberof IBizViewModelBase
     */
    get showCaptionBar() {
        return this.viewModelData.hasOwnProperty('showCaptionBar') ? this.viewModelData.showCaptionBar : true;
    }

    /**
     * 视图图标
     * 
     * @memberof IBizViewModelBase
     */
    get viewSysImage() {
        return this.viewModelData.viewSysImage;
    }

    /**
     * 视图导航上下文
     * 
     * @memberof IBizViewModelBase
     */
    get getPSAppViewNavContexts() {
        return this.viewModelData.getPSAppViewNavContexts;
    }

    /**
     * 视图导航参数
     * 
     * @memberof IBizViewModelBase
     */
    get getPSAppViewNavParams() {
        return this.viewModelData.getPSAppViewNavParams;
    }

    /**
     * 视图打开方式
     * 
     * @memberof IBizViewModelBase
     */
    get openMode() {
        return this.viewModelData.openMode;
    }

    /**
     * 视图高度
     * 
     * @memberof IBizViewModelBase
     */
    get height() {
        return this.viewModelData.height;
    }

    /**
     * 视图宽度
     * 
     * @memberof IBizViewModelBase
     */
    get width() {
        return this.viewModelData.width;
    }

    /**
     * 视图布局面板
     * 
     * @memberof IBizViewModelBase
     */
    get getPSViewLayoutPanel() {
        return this.viewModelData.getPSViewLayoutPanel;
    }

    /**
     * 访问用户模式
     * 
     * @memberof IBizViewModelBase
     */
    get accUserMode() {
        return this.viewModelData.accUserMode;
    }

    /**
     * 是否数据选择视图
     * 
     * @memberof IBizViewModelBase
     */
    get pickupMode() {
        return this.viewModelData.pickupMode;
    }

    /**
     * 视图界面引擎集合
     * 
     * @memberof IBizViewModelBase
     */
    get getPSAppViewEngines() {
        return this.viewModelData.getPSAppViewEngines;
    }

    /**
     * 视图关系路径
     * 
     * @memberof IBizViewModelBase
     */
    get getPSAppDERSPaths() {
        return this.viewModelData.getPSAppDERSPaths;
    }

    /**
     * 视图逻辑
     * 
     * @memberof IBizViewModelBase
     */
    get getPSAppViewLogics() {
        let viewLogicsArray: Array<any> = [];
        if (this.viewModelData && this.viewModelData.getPSAppViewLogics) {
            this.viewModelData.getPSAppViewLogics.forEach((element: any) => {
                viewLogicsArray.push(new IBizViewLogicModel(element, this));
            });
        }
        return viewLogicsArray;
    }

    /**
     * 视图界面行为
     * 
     * @memberof IBizViewModelBase
     */
    get getPSAppViewUIActions() {
        let viewUIActionsArray: Array<any> = [];
        if (this.viewModelData && this.viewModelData.getPSAppViewUIActions) {
            this.viewModelData.getPSAppViewUIActions.forEach((element: any) => {
                viewUIActionsArray.push(new IBizViewActionModel(element, this));
            });
        }
        return viewUIActionsArray;
    }

    /**
     * 动态模型路径
     *
     * @readonly
     * @memberof IBizViewModelBase
     */
    get path() {
        return this.viewModelData.path;
    }

    /**
     * 视图插件
     *
     * @readonly
     * @memberof IBizViewModelBase
     */  
    get getPSSysPFPlugin(){
        return this.viewModelData.getPSSysPFPlugin;
    }

    /**
     * 获取应用计数器引用集合
     *
     * @readonly
     * @memberof IBizViewModelBase
     */
    get getPSAppCounterRefs(){
        return this.viewModelData.getPSAppCounterRefs;
    }

    /**
     * 获取系统计数器引用
     *
     * @readonly
     * @memberof IBizViewModelBase
     */
    get getPSSysCounterRef(){
        return this.viewModelData.getPSSysCounterRef;
    }

    /**
     * 获取视图消息组
     *
     * @readonly
     * @memberof IBizViewModelBase
     */
    get viewMessageGroup() {
        return this.viewModelData.getPSViewMsgGroup;
    }

    /**
     * 功能视图模式
     *
     * @readonly
     * @memberof IBizViewModelBase
     */
    get funcViewMode(){
        return this.viewModelData.funcViewMode;
    }

    /**
     * 功能视图参数
     *
     * @readonly
     * @memberof IBizViewModelBase
     */
    get funcViewParam(){
        return this.viewModelData.funcViewParam;
    }

}