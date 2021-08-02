import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, EditFormControlBase } from '@/studio-core';
import ProductPlanService from '@/service/product-plan/product-plan-service';
import MainDataService from './main-data-form-service';
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
 * @extends {MainDataEditFormBase}
 */
export class MainDataEditFormBase extends EditFormControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainDataEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {MainDataService}
     * @memberof MainDataEditFormBase
     */
    public service: MainDataService = new MainDataService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductPlanService}
     * @memberof MainDataEditFormBase
     */
    public appEntityService: ProductPlanService = new ProductPlanService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainDataEditFormBase
     */
    protected appDeName: string = 'productplan';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainDataEditFormBase
     */
    protected appDeLogicName: string = '产品计划';

    /**
     * 界面UI服务对象
     *
     * @type {ProductPlanUIService}
     * @memberof MainDataBase
     */  
    public appUIService: ProductPlanUIService = new ProductPlanUIService(this.$store);


    /**
     * 关系界面数量
     *
     * @protected
     * @type {number}
     * @memberof MainDataEditFormBase
     */
    protected drCount: number = 4;
    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof MainDataEditFormBase
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
        title: null,
        begin: null,
        beginstr: null,
        end: null,
        endstr: null,
        desc: null,
        statuss: null,
        id: null,
        productplan: null,
    };

    /**
     * 主信息属性映射表单项名称
     *
     * @type {*}
     * @memberof MainDataEditFormBase
     */
    public majorMessageField: string = 'title';

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainDataEditFormBase
     */
    public rules(): any{
        return {
        }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainDataBase
     */
    public deRules:any = {
    };

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof MainDataEditFormBase
     */
    public detailsModel: any = {
        druipart2: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart2', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel4: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel4', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.maindata_form', extractMode: 'ITEM', details: [] } }),

        formpage2: new FormPageModel({ caption: '需求', detailType: 'FORMPAGE', name: 'formpage2', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        druipart3: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart3', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel3: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel3', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.maindata_form', extractMode: 'ITEM', details: [] } }),

        formpage3: new FormPageModel({ caption: 'Bug', detailType: 'FORMPAGE', name: 'formpage3', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        druipart4: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart4', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel5: new FormGroupPanelModel({ caption: '子计划', detailType: 'GROUPPANEL', name: 'grouppanel5', visible: false, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.maindata_form', extractMode: 'ITEM', details: [] } }),

        druipart1: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel2: new FormGroupPanelModel({ caption: '历史记录', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.maindata_form', extractMode: 'ITEM', details: [] } }),

        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.maindata_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: '基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.productplan.maindata_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '计划详情', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

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

        title: new FormItemModel({
    caption: '名称', detailType: 'FORMITEM', name: 'title', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
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

        beginstr: new FormItemModel({
    caption: '开始日期', detailType: 'FORMITEM', name: 'beginstr', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        end: new FormItemModel({
    caption: '预计结束日期', detailType: 'FORMITEM', name: 'end', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
    required:false,
    disabled: false,
    enableCond: 3,
}),

        endstr: new FormItemModel({
    caption: '结束日期', detailType: 'FORMITEM', name: 'endstr', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
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

        statuss: new FormItemModel({
    caption: '状态', detailType: 'FORMITEM', name: 'statuss', visible: true, isShowCaption: true, form: this, showMoreMode: 0,
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

        form: new FormTabPanelModel({
            caption: 'form',
            detailType: 'TABPANEL',
            name: 'form',
            visible: true,
            isShowCaption: true,
            form: this,
            tabPages: [
                {
                    name: 'formpage2',
                    index: 0,
                    visible: true,
                },
                {
                    name: 'formpage3',
                    index: 1,
                    visible: true,
                },
                {
                    name: 'formpage1',
                    index: 2,
                    visible: true,
                },
            ]
        }),
    };

    /**
     * 表单项逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {Promise<void>}
     * @memberof MainDataEditFormBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string; newVal: any; oldVal: any }): Promise<void> {
                







        if (Object.is(name, '') || Object.is(name, 'statuss')) {
            let ret = false;
            const _statuss = this.data.statuss;
            if (this.$verify.testCond(_statuss, 'EQ', 'parent')) {
                ret = true;
            }
            this.detailsModel.grouppanel5.setVisible(ret);
        }






















    }

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前数据
     * @param {any} $event 面板事件数据
     *
     * @memberof MainDataBase
     */
    public onPanelDataChange(item:any,$event:any) {
        Object.assign(item, $event, {rowDataState:'update'});
    }
}