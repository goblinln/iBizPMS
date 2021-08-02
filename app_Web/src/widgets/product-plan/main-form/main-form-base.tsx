import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, EditFormControlBase } from '@/studio-core';
import ProductPlanService from '@/service/product-plan/product-plan-service';
import MainService from './main-form-service';
import ProductPlanUIService from '@/uiservice/product-plan/product-plan-ui-service';
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
 * @extends {MainEditFormBase}
 */
export class MainEditFormBase extends EditFormControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {MainService}
     * @memberof MainEditFormBase
     */
    public service: MainService = new MainService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductPlanService}
     * @memberof MainEditFormBase
     */
    public appEntityService: ProductPlanService = new ProductPlanService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainEditFormBase
     */
    protected appDeName: string = 'productplan';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainEditFormBase
     */
    protected appDeLogicName: string = '产品计划';

    /**
     * 界面UI服务对象
     *
     * @type {ProductPlanUIService}
     * @memberof MainBase
     */  
    public appUIService: ProductPlanUIService = new ProductPlanUIService(this.$store);

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof MainEditFormBase
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
        branch: null,
        title: null,
        oldtitle: null,
        begin: null,
        future: null,
        end: null,
        delta: null,
        desc: null,
        id: null,
        productplan: null,
    };

    /**
     * 主信息属性映射表单项名称
     *
     * @type {*}
     * @memberof MainEditFormBase
     */
    public majorMessageField: string = 'title';

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainEditFormBase
     */
    public rules(): any{
        return {
            title: [
                {
                    required: this.detailsModel.title.required,
                    type: 'string',
                    message: `${this.$t('entities.productplan.main_form.details.title')}  ${this.$t('app.commonWords.valueNotEmpty')}`,
                    trigger: 'change',
                },
                {
                    required: this.detailsModel.title.required,
                    type: 'string',
                    message: `${this.$t('entities.productplan.main_form.details.title')}  ${this.$t('app.commonWords.valueNotEmpty')}`,
                    trigger: 'blur',
                },
        ],
        }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainBase
     */
    public deRules:any = {
    };

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof MainEditFormBase
     */
    public detailsModel: any = {
        grouppanel2: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.main_form', extractMode: 'ITEM', details: [] } }),

        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.main_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: 'productplan基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.main_form', extractMode: 'ITEM', details: [] } }),

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

        branch: new FormItemModel({
    caption: '平台/分支', detailType: 'FORMITEM', name: 'branch', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
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

        oldtitle: new FormItemModel({
    caption: '', detailType: 'FORMITEM', name: 'oldtitle', visible: false, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
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
     * @memberof MainEditFormBase
     */
    public resetFormData({ name, newVal, oldVal }: { name: string; newVal: any; oldVal: any }): void {
        if (Object.is(name, 'future')) {
            this.onFormItemValueChange({ name: 'begin', value: null });
        }
        if (Object.is(name, 'future')) {
            this.onFormItemValueChange({ name: 'end', value: null });
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
     * @memberof MainEditFormBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string; newVal: any; oldVal: any }): Promise<void> {
                














        if (Object.is(name, '') || Object.is(name, 'oldtitle')) {
            let ret = false;
            const _oldtitle = this.data.oldtitle;
            if (this.$verify.testCond(_oldtitle, 'ISNOTNULL', '')) {
                ret = true;
            }
            this.detailsModel.oldtitle.setVisible(ret);
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
     * @memberof MainBase
     */
    public onPanelDataChange(item:any,$event:any) {
        Object.assign(item, $event, {rowDataState:'update'});
    }
}