import { IBizFormDetailModel } from './ibiz-form-detail-model/ibiz-form-detail-model';
import { IBizMainControlModel } from '../ibiz-main-control-model';
import {
    IBizFormPageModel,
    IBizFormGroupPanelModel,
    IBizFormButtonModel,
    IBizFormDruipartModel,
    IBizFormIFrameModel,
    IBizFormItemModel,
    IBizFormPartModel,
    IBizFormRawItemModel,
    IBizFormTabPageModel,
    IBizFormTabPanelModel,
    IBizFormUserControlModel,
} from './ibiz-form-detail-model';

/**
 * 表单部件
 */
export class IBizFormModel extends IBizMainControlModel {

    /**
     * 表单成员对象集合
     *
     * @private
     * @type {Map<string,any>}
     * @memberof IBizFormModel
     */
    protected formDetailsMap: Map<string, any> = new Map();

    /**
     * 表单分页对象集合
     *
     * @private
     * @type {Map<string,any>}
     * @memberof IBizFormModel
     */
    protected formPagesMap: Map<string, any> = new Map();

    /**
     * 表单项对象集合
     *
     * @private
     * @type {Map<string,any>}
     * @memberof IBizFormModel
     */
    protected formItemsMap: Map<string, any> = new Map();

    /**
     *Creates an instance of IBizFormModel.
     * @param {*} [opts={}] 模型数据
     * @param {*} [parentRef] 父类容器
     * @memberof IBizFormModel
     */
    public constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        super(opts, viewRef, parentRef, runtimeData);
        // 初始化表单项（主要是系统自带的隐藏表单项）
        if (opts.getPSDEFormItems?.length > 0) {
            for (const formItem of opts.getPSDEFormItems) {
                let _formItem = Object.assign({
                    name: formItem.id,
                }, formItem);
                this.formItemsMap.set(formItem.id, new IBizFormItemModel({ formRef: this, parentRef: this, modelData: _formItem, context: this.context }))
            }
            this.formDetailsMap.clear();
        }

    }

    public async loaded() {
        await super.loaded();
        // 表单分页存在时，初始化表单分页对象
        if (this.controlModelData.getPSDEFormPages?.length > 0) {
            for (const page of this.controlModelData.getPSDEFormPages) {
                const pageInstance = new IBizFormPageModel({ formRef: this, modelData: page, context: this.context });
                await pageInstance.loaded()
                this.formDetailsMap.set(page.name, pageInstance);
                this.formPagesMap.set(page.name, pageInstance);
            }
        }
    }

    /**
     * 添加表单成员
     *
     * @param {IBizFormDetailModel} detail
     * @memberof IBizFormModel
     */
    public addFormDetail(detail: IBizFormDetailModel) {
        this.formDetailsMap.set(detail.name, detail);
    }

    /**
     * 添加表单成员
     *
     * @param {IBizFormDetailModel} detail
     * @memberof IBizFormModel
     */
    public addFormItem(formItem: IBizFormItemModel) {
        this.formItemsMap.set(formItem.name, formItem);
    }

    /**
     * 表单功能模式
     *
     * @memberof IBizFormModel
     */
    get formFuncMode() {
        return this.controlModelData.formFuncMode ? this.controlModelData.formFuncMode : '';
    }

    /**
     * 是否自动加载
     *
     * @memberof IBizFormModel
     */
    get autoLoad() {
        return this.controlModelData.autoLoad;
    }

    /**
     * 是否显示处理提示
     *
     * @memberof IBizFormModel
     */
    get showBusyIndicator() {
        return this.controlModelData.showBusyIndicator;
    }

    /**
     * 是否无分页头
     *
     * @memberof IBizFormModel
     */
    get noTabHeader() {
        return this.controlModelData.noTabHeader;
    }

    /**
     * 表单样式
     *
     * @memberof IBizFormModel
     */
    get formStyle() {
        return this.controlModelData.formStyle;
    }

    /**
     * 表单布局设置
     *
     * @readonly
     * @memberof IBizFormModel
     */
    get getLayout() {
        return this.controlModelData.getPSLayout;
    }

    /**
     * 所有表单项更新
     *
     * @readonly
     * @memberof IBizFormModel
     */
    get allFormItemUpdates() {
        return this.controlModelData.getPSDEFormItemUpdates;
    }

    /**
     * 所有表单值规则
     *
     * @readonly
     * @memberof IBizFormModel
     */
    get allFormItemVRs() {
        return this.controlModelData.getPSDEFormItemVRs;
    }

    /**
     * 所有表单项更新
     *
     * @param {string} codeName 表单项更新代码标识
     * @returns
     * @memberof IBizFormModel
     */
    public getFormItemUpdate(codeName: string) {
        if(this.allFormItemUpdates?.length > 0){
            return this.allFormItemUpdates.find((item: any)=>{
                return item.codeName == codeName;
            })
        }
        return undefined;
    }

    /**
     * 获取表单分页对象
     *
     * @memberof IBizFormModel
     */
    get formPages() {
        return [...this.formPagesMap.values()];
    }

    /**
     * 获取所有表单成员对象集合
     *
     * @memberof IBizFormModel
     */
    get allFormDetails() {
        return [...this.formDetailsMap.values()];
    }

    /**
     * 通过名称获取表单成员对象
     *
     * @memberof IBizFormModel
     */
    public getFormDetailByName(name: string) {
        return this.formDetailsMap.get(name);
    }

    /**
     * 获取所有表单项成员对象集合
     *
     * @memberof IBizFormModel
     */
    get formItems(): IBizFormItemModel[] {
        return [...this.formItemsMap.values()];
    }

    /**
     * 是否自动保存
     *
     * @memberof IBizFormModel
     */
    get enableAutoSave() {
        return this.controlModelData.enableAutoSave;
    }
}
