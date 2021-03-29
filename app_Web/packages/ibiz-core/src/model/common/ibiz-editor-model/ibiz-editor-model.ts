import { mergeDeepLeft } from "ramda";
import { DynamicService } from '../../../service';
import { ModelUtil, Util, ViewFactory } from "../../../utils";
import { IBizEntityModel } from "../../entity/ibiz-entity-model";

/**
 * 编辑器模型
 *
 * @export
 * @class IBizEditorModel
 */
export class IBizEditorModel {

    /**
     * 默认模型数据
     * 
     * @memberof IBizEditorModel
     */
    protected defaultOption: any = {};

    /**
     * 应用上下文
     * 
     * @memberof IBizEditorModel
     */
    protected context: any = {};

    /**
     * 编辑器模型数据
     *
     * @memberof IBizEditorModel
     */
    protected editorModelData: any;

    /**
     * 选择视图
     * 
     * @memberof IBizEditorModel
     */
    protected $pickupAppView: any;

    /**
     * 链接视图
     * 
     * @memberof IBizEditorModel
     */
    protected $linkAppView: any;

    /**
     * 代码表
     * 
     * @memberof IBizEditorModel
     */
    protected $codeList: any;

    /**
     * ac参数
     * 
     * @memberof IBizEditorModel
     */
    protected $acParams: any;

    /**
     * 实体
     * 
     * @memberof IBizEditorModel
     */
    protected $appEntity: any;

    /**
     * 主信息属性
     * 
     * @memberof IBizEditorModel
     */
    protected $deMajorField: any;

    /**
     * 主键属性
     * 
     * @memberof IBizEditorModel
     */
    protected $deKeyField: any;

    /**
     * 编辑器参数集合
     * 
     * @memberof IBizEditorModel
     */
    protected $editorParams: Map<string, any> = new Map();

    /**
     * 父级表单项(列表项、表格项等)
     * 
     * @memberof IBizEditorModel
     */
    protected $parentItem: any;

    /**
     * 父级容器部件
     * 
     * @memberof IBizEditorModel
     */
    protected $containerCtrl: any;

    /**
     * Creates an instance of IBizEditorModel.
     * IBizEditorModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof IBizEditorModel
     */
    constructor(opts: any = {}, context: any, parentItem?: any, containerCtrl?: any) {
        this.defaultOption = ModelUtil.getInstance().getDefaultValueByTag(opts.editorType);
        this.editorModelData = mergeDeepLeft(opts, this.defaultOption);
        Object.defineProperty(this, '$parentItem', { enumerable: false, writable: true })
        Object.defineProperty(this, '$containerCtrl', { enumerable: false, writable: true })
        this.context = context ? context : {};
        this.initEditorParams();
        this.$parentItem = parentItem;
        this.$containerCtrl = containerCtrl;

    }

    /**
     * 编辑器名称
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get name() {
        return this.editorModelData.name;
    }

    /**
     * 父级表单项(列表项、表格项等)
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get parentItem() {
        return this.$parentItem;
    }

    /**
     * 父级容器部件
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get containerCtrl() {
        return this.$containerCtrl;
    }

    /**
     * 编辑器类型
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get editorType() {
        return this.editorModelData.editorType;
    }

    /**
     * 编辑器样式
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get editorStyle() {
        return this.editorModelData.editorStyle || 'DEFAULT';
    }

    /**
     * 编辑器值项
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get valueItemName() {
        return this.editorModelData.valueItemName;
    }

    /**
     * 编辑器宽度
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get editorWidth() {
        return this.editorModelData.editorWidth;
    }

    /**
     * 编辑器高度
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get editorHeight() {
        return this.editorModelData.editorHeight;
    }

    /**
     * 编辑器样式
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get getEditorCssStyle() {
        return this.editorModelData.getEditorCssStyle;
    }

    /**
     * 输入提示
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get placeHolder() {
        return this.editorModelData.placeHolder;
    }

    /**
     * 编辑器插件
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get plugin() {
        return this.editorModelData.getPSSysPFPlugin;
    }

    /**
     * 获取选择视图
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get pickupAppView() {
        return this.$pickupAppView;
    }

    /**
     * 获取链接视图
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get linkAppView() {
        return this.$linkAppView;
    }

    /**
     * 获取代码表
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get codeList() {
        return this.$codeList;
    }

    /**
     * 获取ac参数
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get acParams() {
        return this.$acParams || {};
    }

    /**
     * 获取编辑器导航上下文参数
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get getPSNavigateContexts() {
        return this.editorModelData.getPSNavigateContexts;
    }

    /**
     * 获取编辑器导航参数
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get getPSNavigateParams() {
        return this.editorModelData.getPSNavigateParams;
    }

    /**
     * 获取允许输入内容的值规则（一般用于正则式校验）
     * 
     * @memberof IBizEditorModel
     */
    get getPSSysValueRule() {
        return this.editorModelData.getPSSysValueRule;
    }

    /**
     * 获取允许输入内容的最大长度
     * 
     * @memberof IBizEditorModel
     */
    get maxLength() {
        return this.editorModelData.maxLength
    }

    /**
     * 获取允许输入内容的最小长度
     * 
     * @memberof IBizEditorModel
     */
    get minLength() {
        return this.editorModelData.minLength;
    }

    /**
     * 获取允许输入的浮点精度
     * 
     * @memberof IBizEditorModel
     */
    get precision() {
        if (this.editorModelData.precision) {
            return this.editorModelData.precision;
        } else if (this.$editorParams.has('precision')) {
            return this.getEditorParam('precision')
        } else if (this.getPSDEField?.dataType == 'FLOAT') {
            let fieldPrecision = this.getPSDEField.precision;
            return Util.isEmpty(fieldPrecision) ? 2 : fieldPrecision;
        } else {
            return undefined;
        }
    }

    /**
     * 获取允许输入的最大值
     * 
     * @memberof IBizEditorModel
     */
    get maxValue() {
        return this.editorModelData.maxValue;
    }

    /**
     * 获取允许输入的最小值
     * 
     * @memberof IBizEditorModel
     */
    get minValue() {
        return this.editorModelData.minValue;
    }

    /**
     * 获取主键属性
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get deKeyField() {
        return this.$deKeyField;
    }

    /**
     * 获取主信息属性
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get deMajorField() {
        return this.$deMajorField;
    }

    /**
     * 获取实体
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get appEntity() {
        return this.$appEntity;
    }

    /**
     * 获取实体属性
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get getPSDEField() {
        return this.$parentItem?.appDeField;
    }

    /**
     * 单位名称
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get unitName() {
        return this.$parentItem?.unitName;
    }

    /**
     * 获取自填模式
     *
     * @readonly
     * @memberof IBizEditorModel
     */
    get getPSAppDEACMode() {
        if (this.editorModelData.getPSAppDEACMode) {
            return this.$appEntity?.getACModeByName(this.editorModelData.getPSAppDEACMode.id?.toUpperCase());
        }
    }

    /**
     * 获取编辑器参数
     *
     * @memberof IBizEditorModel
     */
    get allEditorParams() {
        return this.$editorParams.keys();
    }

    /**
     * 获取编辑器参数
     *
     * @param {string} key 参数的key值
     * @returns
     * @memberof IBizEditorModel
     */
    public getEditorParam(key: string) {
        return this.$editorParams.get(key);
    }

    /**
     * 初始化编辑器参数
     *
     * @memberof IBizEditorModel
     */
    public initEditorParams() {
        if (!this.editorModelData.editorParams) {
            return;
        }
        const keys: Array<any> = Object.keys(this.editorModelData.editorParams);
        if (keys && keys.length > 0) {
            keys.forEach((key: any) => {
                let param: any = this.editorModelData.editorParams[key];
                if (!isNaN(Number(param))) {
                    param = Number(param);
                }
                this.$editorParams.set(key, param);
            })
        }
    }

    /**
     * 加载模型数据
     * 
     * @memberof IBizEditorModel
     */
    public async loaded() {
        const pickupView = this.editorModelData.getPickupPSAppView;
        //选择视图
        if (pickupView && pickupView.modelref && pickupView.path) {
            let view: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(pickupView.path);
            if (view) {
                Object.assign(pickupView, view);
                delete pickupView.modelref;
            }
        }
        if (pickupView) {
            this.$pickupAppView = ViewFactory.getInstance(pickupView, this.context);;
        }

        //链接视图
        const linkAppView = this.editorModelData.getLinkPSAppView;
        if (linkAppView && linkAppView.modelref && linkAppView.path) {
            let view: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(linkAppView.path);
            if (view) {
                Object.assign(linkAppView, view);
                delete linkAppView.modelref;
            }
        }
        if (linkAppView) {
            this.$linkAppView = ViewFactory.getInstance(linkAppView, this.context);
            // 链接视图实体和关系路径数据
            if (this.$linkAppView) {
                await this.$linkAppView.loadAppDataEntity();
                await this.$linkAppView.loadedAppDERSPathParam();
            }
        }

        //代码表
        const codeList = this.editorModelData?.getPSAppCodeList;
        if (codeList && codeList.modelref && codeList.path) {
            let tempCodeList: any = await DynamicService.getInstance(this.context).getAppCodeListJsonData(codeList.path);
            if (tempCodeList) {
                tempCodeList.modelref = false;
                this.$codeList = tempCodeList;
            }
        }

        // ac参数 自填模式文本属性、值属性
        let entity = this.editorModelData.getPSAppDataEntity;
        if (entity && entity.modelref && entity.path) {
            let tempEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(entity.path);
            if (tempEntity) {
                tempEntity.modelref = false;
                entity = new IBizEntityModel(tempEntity)
                this.$deMajorField = entity.majorField?.codeName?.toLowerCase();
                this.$deKeyField = entity.keyField?.codeName?.toLowerCase();
                this.$appEntity = entity;
            }
        }

        // AC自填模式
        if (this.editorModelData.getPSAppDEDataSet) {
            this.$acParams = {
                serviceName: entity.codeName,
                interfaceName: this.editorModelData?.getPSAppDEDataSet?.id,
            }
        }
        if (this.getPSAppDEACMode?.getTextPSAppDEField) {
            this.$deMajorField = this.getPSAppDEACMode.getTextPSAppDEField?.codeName?.toLowerCase();
        }
        if (this.getPSAppDEACMode?.getValuePSAppDEField) {
            this.$deKeyField = this.getPSAppDEACMode.getValuePSAppDEField?.codeName?.toLowerCase();
        }
        //todo 自填模式界面行为
    }

}