import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, EditFormControlBase } from '@/studio-core';
import ProductService from '@/service/product/product-service';
import Main_EditService from './main-edit-form-service';
import ProductUIService from '@/uiservice/product/product-ui-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {Main_EditEditFormBase}
 */
export class Main_EditEditFormBase extends EditFormControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof Main_EditEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {Main_EditService}
     * @memberof Main_EditEditFormBase
     */
    public service: Main_EditService = new Main_EditService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductService}
     * @memberof Main_EditEditFormBase
     */
    public appEntityService: ProductService = new ProductService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof Main_EditEditFormBase
     */
    protected appDeName: string = 'product';

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public form_button1_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:ProductUIService  = new ProductUIService();
        curUIService.Product_SeLine(datas,contextJO, paramJO,  $event, xData,this,"Product");
    }

    /**
     * 界面UI服务对象
     *
     * @type {ProductUIService}
     * @memberof Main_EditEditFormBase
     */  
    public appUIService:ProductUIService = new ProductUIService(this.$store);


    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof Main_EditEditFormBase
     */
    public data: any = {
        srforikey: null,
        srfkey: null,
        srfmajortext: null,
        srftempmode: null,
        srfuf: null,
        srfdeid: null,
        srfsourcekey: null,
        name: null,
        code: null,
        line: null,
        linename: null,
        po: null,
        qd: null,
        rd: null,
        type: null,
        status: null,
        desc: null,
        acl: null,
        id: null,
        product:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof Main_EditEditFormBase
     */
    public rules: any = {
        name: [
            { required: true, type: 'string', message: '产品名称 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '产品名称 值不能为空', trigger: 'blur' },
        ],
        code: [
            { required: true, type: 'string', message: '产品代号 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '产品代号 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof Main_EditEditFormBase
     */
    public detailsModel: any = {
        grouppanel2: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.product.main_edit_form', extractMode: 'ITEM', details: [] } }),

        button1: new FormButtonModel({ caption: '维护产品线', detailType: 'BUTTON', name: 'button1', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiaction: { type: 'DEUIACTION', tag: 'SeLine', actiontarget: 'NONE' } }),

        grouppanel3: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel3', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.product.main_edit_form', extractMode: 'ITEM', details: [] } }),

        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.product.main_edit_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: 'product基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.product.main_edit_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        srfmajortext: new FormItemModel({ caption: '产品名称', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        name: new FormItemModel({ caption: '产品名称', detailType: 'FORMITEM', name: 'name', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        code: new FormItemModel({ caption: '产品代号', detailType: 'FORMITEM', name: 'code', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        line: new FormItemModel({ caption: '产品线', detailType: 'FORMITEM', name: 'line', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        linename: new FormItemModel({ caption: '产品线', detailType: 'FORMITEM', name: 'linename', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        po: new FormItemModel({ caption: '产品负责人', detailType: 'FORMITEM', name: 'po', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        qd: new FormItemModel({ caption: '测试负责人', detailType: 'FORMITEM', name: 'qd', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        rd: new FormItemModel({ caption: '发布负责人', detailType: 'FORMITEM', name: 'rd', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        type: new FormItemModel({ caption: '产品类型', detailType: 'FORMITEM', name: 'type', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        status: new FormItemModel({ caption: '状态', detailType: 'FORMITEM', name: 'status', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        desc: new FormItemModel({ caption: '产品描述	', detailType: 'FORMITEM', name: 'desc', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        acl: new FormItemModel({ caption: '访问控制', detailType: 'FORMITEM', name: 'acl', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        id: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

    };

	/**
	 * 表单 维护产品线 事件
	 *
	 * @memberof @memberof Main_EditEditFormBase
	 */
    public button1_click($event: any): void {
        this.form_button1_click(null, null, $event);

    }
}