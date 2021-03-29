import { IBizEditorModel } from '../../../common/ibiz-editor-model';
import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 表单项模型
 *
 * @export
 * @class IBizFormItemModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormItemModel extends IBizFormDetailModel {
    /**
     * 编辑器实例对象
     *
     * @protected
     * @type {IBizEditorModel}
     * @memberof IBizFormItemModel
     */
    protected $editor!: IBizEditorModel;

    /**
     *Creates an instance of IBizFormItemModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormItemModel
     */
    constructor({ formRef, parentRef, modelData,context }: any = {}) {
        super({ formRef, parentRef, modelData,context });
        this.form.addFormItem(this);
        // 初始化编辑器
        if(modelData.getPSEditor){
            this.$editor = new IBizEditorModel(modelData.getPSEditor,this.context,this,this.getForm);
        }
    }

    /**
     * 表单项启用条件
     * 
     * 0 不启用
     * 1 新建
     * 2 更新
     * 3 全部启用
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get enableCond():number | 0 | 1 | 2 | 3{
        return this.detailModelData.enableCond;
    }

    /**
     * 标签宽度
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get labelWidth(){
        return this.detailModelData.labelWidth;
    }

    /**
     * 标签位置
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get labelPos(){
        return this.detailModelData.labelPos;
    }

    /**
     * 内容宽度
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get contentWidth(){
        return this.detailModelData.contentWidth;
    }

    /**
     * 内容高度
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get contentHeight(){
        return this.detailModelData.contentHeight;
    }

    /**
     * 是否是隐藏表单项
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get hidden(){
        return this.detailModelData.hidden;
    }

    /**
     * 是否是空白标签
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get emptyCaption(){
        return this.detailModelData.emptyCaption;
    }

    /**
     * 是否是复合表单项
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get compositeItem(){
        return this.detailModelData.compositeItem;
    }

    /**
     * 输入提示
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get placeHolder(){
        return this.detailModelData.placeHolder;
    }

    /**
     * 更新默认值
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get updateDV(){
        return this.detailModelData.updateDV;
    }

    /**
     * 更新默认值类型
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get updateDVT(){
        return this.detailModelData.updateDVT;
    }

    /**
     * 新建默认值
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get createDV(){
        return this.detailModelData.createDV;
    }

    /**
     * 新建默认值类型
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get createDVT(){
        return this.detailModelData.createDVT;
    }

    /**
     * 重置项名称
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get resetItemName(){
        return this.detailModelData.resetItemName;
    }

    /**
     * 值项名称
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get valueItemName(){
        return this.detailModelData.valueItemName;
    }

    /**
     * 单位名称
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get unitName(){
        return this.detailModelData.unitName;
    }

    /**
     * 标签样式对象
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get getLabelPSSysCss(){
        return this.detailModelData.getLabelPSSysCss;
    }

    /**
     * 编辑器对象
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get editor(){
        return this.$editor;
    }

    /**
     * 应用实体属性
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get appDeField(){
        if(this.detailModelData?.getPSAppDEField){
            const { codeName } = this.detailModelData.getPSAppDEField;
            return this.form?.appDataEntity?.getFieldByCodeName(codeName);
        }
    }

    /**
     * 实体属性
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get deField(){
        return this.detailModelData.getPSDEField;
    }


    /**
     * 表单项成员
     *
     * @readonly
     * @memberof IBizFormItemModel
     */
    get formItems(){
        return this.detailModelData.getPSDEFormItems;
    }

} 