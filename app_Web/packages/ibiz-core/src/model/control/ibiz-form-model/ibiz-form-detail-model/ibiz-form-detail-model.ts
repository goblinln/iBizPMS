import { IBizFormModel } from 'ibiz-core';
import { IBizLayoutPosModel } from '../../../common/layout-model/ibiz-layout-pos-model';
/**
 * 表单成员模型
 *
 * @export
 * @class IBizFormDetailModel
 */
export class IBizFormDetailModel {

    /**
     * 应用上下文
     *
     * @protected
     * @type {*}
     * @memberof IBizFormItemModel
     */
    protected context: any = {};

    /**
     * 表单成员模型数据
     *
     * @memberof IBizFormDetailModel
     */
    protected detailModelData: any;

    /**
     * 表单实例对象
     *
     * @type {*}
     * @memberof IBizFormDetailModel
     */
    protected form!: IBizFormModel;

    /**
     * 父容器实例对象
     *
     * @type {*}
     * @memberof IBizFormDetailModel
     */
    protected parentContainer!: any;

    /**
     * 布局位置实例对象
     *
     * @type {*}
     * @memberof IBizFormDetailModel
     */
    protected layoutPos!: IBizLayoutPosModel;

    /**
     *Creates an instance of IBizFormDetailModel.
     * @param {*} [{formRef, parentRef, modelData}={}]
     * @memberof IBizFormDetailModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        this.detailModelData = modelData;
        this.form = formRef;
        this.parentContainer = parentRef;
        this.context = context?context:this.context;
        // 给表单和父容器自身的索引
        this.form.addFormDetail(this);
        this.parentContainer?.addChildFormDetail?.(this);

        // 初始化布局位置对象
        if (modelData?.getPSLayoutPos) {
            this.layoutPos = new IBizLayoutPosModel(modelData.getPSLayoutPos, this.context);
        }

        // 初始化动态逻辑
        this.initFormDetailGroupLogics();
    }

    public async loaded() { }

    /**
     * 获取表单实例对象
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get getForm(): any {
        return this.form;
    }

    /**
     * 获取父容器实例对象
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get getParentContainer(): any {
        return this.parentContainer;
    }

    /**
     * 成员类型
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get detailType() {
        return this.detailModelData.detailType;
    }

    /**
     * 成员样式
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get detailStyle() {
        return this.detailModelData.detailStyle;
    }

    /**
     * 成员标题
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get caption() {
        return this.detailModelData.caption;
    }

    /**
     * 成员名称
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get name() {
        return this.detailModelData.name;
    }

    /**
     * 成员代码名称
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get codeName() {
        return this.detailModelData.codeName;
    }

    /**
     * 是否显示标题
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get showCaption() {
        return this.detailModelData.showCaption;
    }

    /**
     * 成员自定义样式
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get getPSSysCss() {
        return this.detailModelData.getPSSysCss;
    }

    /**
     * 显示更多模式
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get showMoreMode() {
        return this.detailModelData.showMoreMode;
    }

    /**
     * 是否允许空输入
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get allowEmpty() {
        return this.detailModelData.allowEmpty;
    }

    /**
     * 标题样式表
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get getLabelPSSysCss() {
        return this.detailModelData.getLabelPSSysCss;
    }

    /**
     * 是否隐藏
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get hidden(){
        return this.detailModelData.hidden
    }

    /**
     * 刷新表单项
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get refreshItems(){
        return this.detailModelData.refreshItems;
    }

    /**
     * 获取绘制插件
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get getPSSysPFPlugin(){
        return this.detailModelData.getPSSysPFPlugin
    }

    /**
     * 布局位置（成员自身）
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get getLayoutPos() {
        return this.layoutPos;
    }

    /**
     * 获取表单项更新
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get formItemUpdate(){
        if(this.detailModelData?.getPSDEFormItemUpdate?.id){
            return this.form.getFormItemUpdate(this.detailModelData.getPSDEFormItemUpdate.id);
        }
        return undefined;
    }

    /**
     * 获取所有表单成员动态逻辑
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get allPSDEFDGroupLogics(){
        return this.detailModelData.getPSDEFDGroupLogics;
    }

    /**
     * 支持锚点
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get enableAnchor() {
        return this.detailModelData.enableAnchor;
    }

    /**
     * 显示更多模式容器
     *
     * @readonly
     * @memberof IBizFormDetailModel
     */
    get getShowMoreMgrPSDEFormDetail() {
        return this.detailModelData.getShowMoreMgrPSDEFormDetail
    }

    /**
     * 根据logicCat获取动态逻辑
     *
     * @param {string} logicCat
     * @memberof IBizFormDetailModel
     */
    public getPSDEFDGroupLogic(logicCat: string){
        if(logicCat && this.allPSDEFDGroupLogics?.length > 0){
            return this.allPSDEFDGroupLogics.find((item: any)=>{
                return item.logicCat == logicCat;
            })
        }
    }

    /**
     * 初始化表单成员动态逻辑
     *
     * @memberof IBizFormDetailModel
     */
    public initFormDetailGroupLogics(){
        this.allPSDEFDGroupLogics?.forEach((logic: any)=>{
            logic.relatedDetailNames = new Set( this.getRelatedDetailNames(logic) );
        })
    }

    /**
     * 获取关联的成员名称
     *
     * @param {*} logic
     * @returns
     * @memberof IBizFormDetailModel
     */
    public getRelatedDetailNames(logic: any){
        let names: any[] = [];
        if(logic?.dEFDName){
            names.push(logic.dEFDName.toLowerCase());
        }
        if(logic?.getPSDEFDLogics?.length > 0){
            logic.getPSDEFDLogics.forEach((childLogic: any)=>{
                let childNames: any[] = this.getRelatedDetailNames(childLogic);
                if(childNames.length > 0){
                    names = names.concat(childNames);
                }
            })
        }
        return names;
    }
}
