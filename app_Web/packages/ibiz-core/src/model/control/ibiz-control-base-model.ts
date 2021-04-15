import { mergeDeepLeft } from "ramda";
import { DynamicService } from "../../service";
import { IBizEntityModel } from "../entity";
import { ControlFactory, ModelUtil, Util } from "../../utils";

/**
 * 部件基类
 */
export class IBizControlBaseModel {
    /**
     * 部件模型数据
     *
     * @memberof IBizControlBaseModel
     */
    public controlModelData: any;

    /**
     * 应用上下文
     *
     * @memberof IBizControlBaseModel
     */
    public context: any = {};

    /**
     * 默认模型数据
     * 
     * @memberof IBizControlBaseModel
     */
    protected defaultOption: any = {};

    /**
     * 视图实例对象
     *
     * @memberof IBizControlBaseModel
     */
    protected $view: any;

    /**
     * 父模型实例对象
     *
     * @memberof IBizControlBaseModel
     */
    protected $parent: any;

    /**
     * 部件模型实例集合
     * 
     * @memberof IBizControlBaseModel
     */
    protected $controlsMap: Map<string, any> = new Map();

    /**
     * 初始化IBizControlBase对象
     * @param opts 额外参数
     *
     * @memberof IBizControlBaseModel
     */
    public constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        this.defaultOption = ModelUtil.getInstance().getDefaultValueByTag(opts.controlType);
        this.controlModelData = mergeDeepLeft(Util.deepCopy(opts), this.defaultOption);
        // 不可枚举
        Object.defineProperty(this, '$view', { enumerable: false, writable: true });
        Object.defineProperty(this, '$parent', { enumerable: false, writable: true });
        Object.defineProperty(this, '$controlsMap', { enumerable: false, writable: true });
        Object.defineProperty(this, 'controlModelData', { enumerable: false, writable: true });
        if (viewRef) {
            this.$view = viewRef;
            viewRef.addControl(this);
        }
        if (parentRef) {
            this.$parent = parentRef
            parentRef.addControl(this);
        }
        this.context = (runtimeData && runtimeData.context) ? runtimeData.context : {};
    }

    /**
     * 加载数据模型（获取所有子部件）
     * 
     * @memberof IBizControlBaseModel
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
                // 实例化部件模型对象
                let ctrlInstance = ControlFactory.getInstance(control, this.$view, this);
                if (ctrlInstance) {
                    await ctrlInstance.loaded()
                }
            }
        }
    }

    /**
    * 加载数据模型（应用实体）
    * 
    * @memberof IBizControlBaseModel
    */
    public async loadedAppEntityModel(context: any, model: any) {
        if (model && model.modelref && model.path) {
            let targetAppEntity: any = await DynamicService.getInstance(context).getAppEntityModelJsonData(model.path);
            return new IBizEntityModel(targetAppEntity);
        }
    }

    /**
     * 添加部件模型实例
     *
     * @param {IBizControlBaseModel} control
     * @memberof IBizControlBaseModel
     */
    public addControl(control: IBizControlBaseModel) {
        this.$controlsMap.set(control.name, control);
    }

    /**
     * 获取对应名称的部件模型实例
     *
     * @param {string} name
     * @memberof IBizControlBaseModel
     */
    public getControl(name: string) {
        return this.$controlsMap.get(name);
    }

    /**
     * 获取视图的所有部件
     *
     * @param {IBizControlBaseModel} control
     * @memberof IBizControlBaseModel
     */
    public getAllControls() {
        return [...this.$controlsMap.values()]
    }

    /**
     * 通过子部件名称获取子部件模型JSON数据
     *
     * @param {string} name
     * @memberof IBizControlBaseModel
     */
    public getControlDataByName(name: string) {
        if (this.controls && this.controls.length > 0) {
            return this.controls.find((item: any) => {
                return item.name == name;
            })
        }
    }

    /**
     * 获取所有子部件的模型数据
     * 
     * @memberof IBizControlBaseModel
     */
    get controls() {
        return this.controlModelData.getPSControls;
    }

    /**
     * 获取视图实例
     *
     * @type {string}
     * @memberof IBizControlBaseModel
     */
    public getView() {
        return this.$view || undefined;
    }

    /**
     * 部件类型
     *
     * @type {string}
     * @memberof IBizControlBaseModel
     */
    get controlType() {
        return this.controlModelData.controlType;
    }

    /**
     * 部件样式
     *
     * @type {string}
     * @memberof IBizControlBaseModel
     */
    get controlStyle() {
        return this.controlModelData.controlStyle || 'DEFAULT';
    }

    /**
     * 部件名称
     *
     * @type {string}
     * @memberof IBizControlBaseModel
     */
    get name() {
        return this.controlModelData.name;
    }

    /**
     * 部件代码名称
     *
     * @type {string}
     * @memberof IBizControlBaseModel
     */
    get codeName() {
        return this.controlModelData.codeName;
    }

    /**
     * 部件逻辑名称
     *
     * @type {string}
     * @memberof IBizControlBaseModel
     */
    get logicName() {
        return this.controlModelData.logicName;
    }

    /**
     * 部件高度
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get height() {
        return this.controlModelData.height;
    }

    /**
     * 部件宽度
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get width() {
        return this.controlModelData.width;
    }

    /**
     * 界面样式
     *
     * @type {string}
     * @memberof IBizControlBaseModel
     */
    get getPSSysCss() {
        return this.controlModelData.getPSSysCss;
        // todo json里是索引，没有对象数据
    }

    /**
     * 动态模型路径
     *
     * @readonly
     * @memberof IBizControlBaseModel
     */
    get path() {
        return this.controlModelData.path;
    }

    /**
     * 插件
     *
     * @memberof IBizControlBaseModel
     */
    get getPSSysPFPlugin() {
        return this.controlModelData.getPSSysPFPlugin;
    }

    /**
     * 获取应用计数器引用集合
     *
     * @readonly
     * @memberof IBizControlBaseModel
     */
    get getPSAppCounterRefs() {
        return this.controlModelData.getPSAppCounterRefs;
    }

    /**
     * 应用计数器引用
     * 
     * @readonly
     * @memberof IBizControlBaseModel
     */
    get getPSAppCounterRef() {
        return this.controlModelData.getPSAppCounterRef;
    }

    /**
     * 获取界面计数器引用
     *
     * @readonly
     * @memberof IBizControlBaseModel
     */
    get getPSSysCounterRef() {
        return this.controlModelData.getPSSysCounterRef;
    }
}