import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, EditFormControlBase } from '@/studio-core';
import SubProductPlanService from '@/service/sub-product-plan/sub-product-plan-service';
import MainSubPlanService from './main-sub-plan-form-service';
import SubProductPlanUIService from '@/uiservice/sub-product-plan/sub-product-plan-ui-service';
import {
    FormButtonModel,
    FormPageModel,
    FormItemModel,
    FormDRUIPartModel,
    FormPartModel,
    FormGroupPanelModel,
    FormIFrameModel,
    FormRowItemModel,
    FormTabPageModel,
    FormTabPanelModel,
    FormUserControlModel,
} from '@/model/form-detail';

/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {MainSubPlanEditFormBase}
 */
export class MainSubPlanEditFormBase extends EditFormControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainSubPlanEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {MainSubPlanService}
     * @memberof MainSubPlanEditFormBase
     */
    public service: MainSubPlanService = new MainSubPlanService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {SubProductPlanService}
     * @memberof MainSubPlanEditFormBase
     */
    public appEntityService: SubProductPlanService = new SubProductPlanService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainSubPlanEditFormBase
     */
    protected appDeName: string = 'subproductplan';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainSubPlanEditFormBase
     */
    protected appDeLogicName: string = '产品计划';

    /**
     * 界面UI服务对象
     *
     * @type {SubProductPlanUIService}
     * @memberof MainSubPlanBase
     */  
    public appUIService: SubProductPlanUIService = new SubProductPlanUIService(this.$store);

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof MainSubPlanEditFormBase
     */
    public data: any = {
        srforikey: null,
        srfkey: null,
        srfmajortext: null,
        srftempmode: null,
        srfuf: null,
        srfdeid: null,
        srfsourcekey: null,
        product: null,
        parent: null,
        parentname: null,
        title: null,
        begin: null,
        future: null,
        end: null,
        delta: null,
        desc: null,
        id: null,
        subproductplan: null,
    };

    /**
     * 主信息属性映射表单项名称
     *
     * @type {*}
     * @memberof MainSubPlanEditFormBase
     */
    public majorMessageField: string = 'title';

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainSubPlanEditFormBase
     */
    public rules(): any{
        return {
            title: [
                {
                    required: this.detailsModel.title.required,
                    type: 'string',
                    message: `${this.$t('entities.subproductplan.mainsubplan_form.details.title')}  ${this.$t('app.commonWords.valueNotEmpty')}`,
                    trigger: 'change',
                },
                {
                    required: this.detailsModel.title.required,
                    type: 'string',
                    message: `${this.$t('entities.subproductplan.mainsubplan_form.details.title')}  ${this.$t('app.commonWords.valueNotEmpty')}`,
                    trigger: 'blur',
                },
        ],
        }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainSubPlanBase
     */
    public deRules:any = {
    };

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof MainSubPlanEditFormBase
     */
    public detailsModel: any = {
        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.subproductplan.mainsubplan_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: 'productplan基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.subproductplan.mainsubplan_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srforikey: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        srfkey: new FormItemModel({
    caption: '编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 0,
}),

        srfmajortext: new FormItemModel({
    caption: '名称', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        srftempmode: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        srfuf: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        srfdeid: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        srfsourcekey: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        product: new FormItemModel({
    caption: '产品', detailType: 'FORMITEM', name: 'product', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        parent: new FormItemModel({
    caption: '父计划', detailType: 'FORMITEM', name: 'parent', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        parentname: new FormItemModel({
    caption: '父计划名称', detailType: 'FORMITEM', name: 'parentname', visible: false, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        title: new FormItemModel({
    caption: '名称', detailType: 'FORMITEM', name: 'title', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:true,
    disabled: false,
    enableCond: 3,
}),

        begin: new FormItemModel({
    caption: '预计开始日期', detailType: 'FORMITEM', name: 'begin', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        future: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'future', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        end: new FormItemModel({
    caption: '预计结束日期', detailType: 'FORMITEM', name: 'end', visible: false, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        delta: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'delta', visible: false, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        desc: new FormItemModel({
    caption: '描述', detailType: 'FORMITEM', name: 'desc', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        id: new FormItemModel({
    caption: '编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 0,
}),

    };

    /**
     * 重置表单项值
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof MainSubPlanEditFormBase
     */
    public resetFormData({ name, newVal, oldVal }: { name: string; newVal: any; oldVal: any }): void {
        if (Object.is(name, 'future')) {
            this.onFormItemValueChange({ name: 'begin', value: null });
        }
        if (Object.is(name, 'future')) {
            this.onFormItemValueChange({ name: 'delta', value: null });
        }
    }

    /**
     * 表单项逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {Promise<void>}
     * @memberof MainSubPlanEditFormBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string; newVal: any; oldVal: any }): Promise<void> {
                












        if (Object.is(name, '') || Object.is(name, 'parentname')) {
            let ret = false;
            const _parentname = this.data.parentname;
            if (this.$verify.testCond(_parentname, 'ISNOTNULL', '')) {
                ret = true;
            }
            this.detailsModel.parentname.setVisible(ret);
        }


        if (Object.is(name, '') || Object.is(name, 'future')) {
            let ret = false;
            const _future = this.data.future;
            if (this.$verify.testCond(_future, 'ISNULL', '')) {
                ret = true;
            }
            this.detailsModel.begin.setDisabled(!ret);
        }


        if (Object.is(name, '') || Object.is(name, 'future')) {
            let ret = false;
            const _future = this.data.future;
            if (this.$verify.testCond(_future, 'ISNULL', '')) {
                ret = true;
            }
            this.detailsModel.end.setVisible(ret);
        }

        if (Object.is(name, '') || Object.is(name, 'future')) {
            let ret = false;
            const _future = this.data.future;
            if (this.$verify.testCond(_future, 'ISNULL', '')) {
                ret = true;
            }
            this.detailsModel.delta.setVisible(ret);
        }



        if (Object.is(name, 'begin')) {
            const details: string[] = ['end'];
            this.updateFormItems('GetPlanEnd', this.data, details, true);
        }
        if (Object.is(name, 'delta')) {
            const details: string[] = ['end'];
            this.updateFormItems('GetPlanEnd', this.data, details, true);
        }
    }

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前数据
     * @param {any} $event 面板事件数据
     *
     * @memberof MainSubPlanBase
     */
    public onPanelDataChange(item:any,$event:any) {
        Object.assign(item, $event, {rowDataState:'update'});
    }
}