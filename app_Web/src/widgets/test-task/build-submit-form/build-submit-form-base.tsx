import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, EditFormControlBase } from '@/studio-core';
import TestTaskService from '@/service/test-task/test-task-service';
import BuildSUBMITService from './build-submit-form-service';
import TestTaskUIService from '@/uiservice/test-task/test-task-ui-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {BuildSUBMITEditFormBase}
 */
export class BuildSUBMITEditFormBase extends EditFormControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof BuildSUBMITEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {BuildSUBMITService}
     * @memberof BuildSUBMITEditFormBase
     */
    public service: BuildSUBMITService = new BuildSUBMITService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {TestTaskService}
     * @memberof BuildSUBMITEditFormBase
     */
    public appEntityService: TestTaskService = new TestTaskService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof BuildSUBMITEditFormBase
     */
    protected appDeName: string = 'testtask';

    /**
     * 界面UI服务对象
     *
     * @type {TestTaskUIService}
     * @memberof BuildSUBMITEditFormBase
     */  
    public appUIService:TestTaskUIService = new TestTaskUIService(this.$store);


    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof BuildSUBMITEditFormBase
     */
    public data: any = {
        srforikey: null,
        srfkey: null,
        srfmajortext: null,
        srftempmode: null,
        srfuf: null,
        srfdeid: null,
        srfsourcekey: null,
        productname: null,
        projecttname: null,
        buildname: null,
        owner: null,
        pri: null,
        begin: null,
        end: null,
        project: null,
        build: null,
        status: null,
        product: null,
        name: null,
        desc: null,
        mailto: null,
        id: null,
        testtask:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof BuildSUBMITEditFormBase
     */
    public rules: any = {
        buildname: [
            { required: true, type: 'string', message: '版本 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '版本 值不能为空', trigger: 'blur' },
        ],
        begin: [
            { required: true, type: 'string', message: '开始日期 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '开始日期 值不能为空', trigger: 'blur' },
        ],
        end: [
            { required: true, type: 'string', message: '结束日期 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '结束日期 值不能为空', trigger: 'blur' },
        ],
        name: [
            { required: true, type: 'string', message: '名称 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '名称 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof BuildSUBMITEditFormBase
     */
    public detailsModel: any = {
        grouppanel2: new FormGroupPanelModel({ caption: '起止日期', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.testtask.buildsubmit_form', extractMode: 'ITEM', details: [] } }),

        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.testtask.buildsubmit_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: '基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.testtask.buildsubmit_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        srfmajortext: new FormItemModel({ caption: '名称', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        productname: new FormItemModel({ caption: '产品', detailType: 'FORMITEM', name: 'productname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        projecttname: new FormItemModel({ caption: '项目', detailType: 'FORMITEM', name: 'projecttname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        buildname: new FormItemModel({ caption: '版本', detailType: 'FORMITEM', name: 'buildname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        owner: new FormItemModel({ caption: '负责人', detailType: 'FORMITEM', name: 'owner', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        pri: new FormItemModel({ caption: '优先级', detailType: 'FORMITEM', name: 'pri', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        begin: new FormItemModel({ caption: '开始日期', detailType: 'FORMITEM', name: 'begin', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        end: new FormItemModel({ caption: '结束日期', detailType: 'FORMITEM', name: 'end', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        project: new FormItemModel({ caption: '所属项目', detailType: 'FORMITEM', name: 'project', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        build: new FormItemModel({ caption: '版本', detailType: 'FORMITEM', name: 'build', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        status: new FormItemModel({ caption: '当前状态', detailType: 'FORMITEM', name: 'status', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        product: new FormItemModel({ caption: '所属产品', detailType: 'FORMITEM', name: 'product', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        name: new FormItemModel({ caption: '名称', detailType: 'FORMITEM', name: 'name', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        desc: new FormItemModel({ caption: '描述', detailType: 'FORMITEM', name: 'desc', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        mailto: new FormItemModel({ caption: '抄送给', detailType: 'FORMITEM', name: 'mailto', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        id: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

    };

    /**
     * 新建默认值
     * @memberof BuildSUBMITEditFormBase
     */
    public createDefault(){                    
        if (this.data.hasOwnProperty('project')) {
            this.data['project'] = this.viewparams['project'];
        }
        if (this.data.hasOwnProperty('build')) {
            this.data['build'] = this.viewparams['build'];
        }
        if (this.data.hasOwnProperty('status')) {
            this.data['status'] = 'wait';
        }
        if (this.data.hasOwnProperty('product')) {
            this.data['product'] = this.viewparams['product'];
        }
    }
}