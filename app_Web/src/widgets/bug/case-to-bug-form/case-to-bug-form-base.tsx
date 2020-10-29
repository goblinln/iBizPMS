import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, EditFormControlBase } from '@/studio-core';
import BugService from '@/service/bug/bug-service';
import CaseToBugService from './case-to-bug-form-service';
import BugUIService from '@/uiservice/bug/bug-ui-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {CaseToBugEditFormBase}
 */
export class CaseToBugEditFormBase extends EditFormControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof CaseToBugEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {CaseToBugService}
     * @memberof CaseToBugEditFormBase
     */
    public service: CaseToBugService = new CaseToBugService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {BugService}
     * @memberof CaseToBugEditFormBase
     */
    public appEntityService: BugService = new BugService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof CaseToBugEditFormBase
     */
    protected appDeName: string = 'bug';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof CaseToBugEditFormBase
     */
    protected appDeLogicName: string = 'Bug';

    /**
     * 界面UI服务对象
     *
     * @type {BugUIService}
     * @memberof CaseToBugBase
     */  
    public appUIService:BugUIService = new BugUIService(this.$store);

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof CaseToBugEditFormBase
     */
    public data: any = {
        srfupdatedate: null,
        srforikey: null,
        srfkey: null,
        srfmajortext: null,
        srftempmode: null,
        srfuf: null,
        srfdeid: null,
        srfsourcekey: null,
        productname: null,
        branch: null,
        product: null,
        modulename: null,
        module: null,
        projectname: null,
        project: null,
        openedbuild: null,
        assignedto: null,
        deadline: null,
        type: null,
        os: null,
        browser: null,
        title: null,
        severity: null,
        pri: null,
        steps: null,
        storyname: null,
        taskname: null,
        mailto: null,
        keywords: null,
        story: null,
        caseversion: null,
        case: null,
        id: null,
        task: null,
        bug:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof CaseToBugEditFormBase
     */
    public rules():any{
        return {
        openedbuild: [
            { required: this.detailsModel.openedbuild.required, type: 'string', message: '影响版本 值不能为空', trigger: 'change' },
            { required: this.detailsModel.openedbuild.required, type: 'string', message: '影响版本 值不能为空', trigger: 'blur' },
        ],
        title: [
            { required: this.detailsModel.title.required, type: 'string', message: 'Bug标题 值不能为空', trigger: 'change' },
            { required: this.detailsModel.title.required, type: 'string', message: 'Bug标题 值不能为空', trigger: 'blur' },
        ],
        }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof CaseToBugBase
     */
    public deRules:any = {
    };

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof CaseToBugEditFormBase
     */
    public detailsModel: any = {
        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.casetobug_form', extractMode: 'ITEM', details: [] } }),

        grouppanel2: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.casetobug_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: 'bug基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.casetobug_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srfupdatedate: new FormItemModel({ caption: '修改日期', detailType: 'FORMITEM', name: 'srfupdatedate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 0 }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: 'Bug编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 0 }),

        srfmajortext: new FormItemModel({ caption: 'Bug标题', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        productname: new FormItemModel({ caption: '产品', detailType: 'FORMITEM', name: 'productname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        branch: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'branch', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        product: new FormItemModel({ caption: '所属产品', detailType: 'FORMITEM', name: 'product', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        modulename: new FormItemModel({ caption: '所属模块', detailType: 'FORMITEM', name: 'modulename', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        module: new FormItemModel({ caption: '所属模块', detailType: 'FORMITEM', name: 'module', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        projectname: new FormItemModel({ caption: '项目', detailType: 'FORMITEM', name: 'projectname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        project: new FormItemModel({ caption: '所属项目', detailType: 'FORMITEM', name: 'project', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        openedbuild: new FormItemModel({ caption: '影响版本', detailType: 'FORMITEM', name: 'openedbuild', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:true, disabled: false, enableCond: 3 }),

        assignedto: new FormItemModel({ caption: '指派给', detailType: 'FORMITEM', name: 'assignedto', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        deadline: new FormItemModel({ caption: '截止日期', detailType: 'FORMITEM', name: 'deadline', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        type: new FormItemModel({ caption: 'Bug类型', detailType: 'FORMITEM', name: 'type', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        os: new FormItemModel({ caption: '操作系统', detailType: 'FORMITEM', name: 'os', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        browser: new FormItemModel({ caption: '浏览器', detailType: 'FORMITEM', name: 'browser', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        title: new FormItemModel({ caption: 'Bug标题', detailType: 'FORMITEM', name: 'title', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:true, disabled: false, enableCond: 3 }),

        severity: new FormItemModel({ caption: '严重程度', detailType: 'FORMITEM', name: 'severity', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        pri: new FormItemModel({ caption: '优先级', detailType: 'FORMITEM', name: 'pri', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        steps: new FormItemModel({ caption: '重现步骤', detailType: 'FORMITEM', name: 'steps', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        storyname: new FormItemModel({ caption: '相关需求', detailType: 'FORMITEM', name: 'storyname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        taskname: new FormItemModel({ caption: '相关任务', detailType: 'FORMITEM', name: 'taskname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        mailto: new FormItemModel({ caption: '抄送给', detailType: 'FORMITEM', name: 'mailto', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        keywords: new FormItemModel({ caption: '关键词', detailType: 'FORMITEM', name: 'keywords', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        story: new FormItemModel({ caption: '相关需求', detailType: 'FORMITEM', name: 'story', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        caseversion: new FormItemModel({ caption: '用例版本', detailType: 'FORMITEM', name: 'caseversion', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        case: new FormItemModel({ caption: '相关用例', detailType: 'FORMITEM', name: 'case', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        id: new FormItemModel({ caption: 'Bug编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 0 }),

        task: new FormItemModel({ caption: '相关任务', detailType: 'FORMITEM', name: 'task', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

    };

    /**
     * 重置表单项值
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof CaseToBugEditFormBase
     */
    public resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
        if (Object.is(name, 'branch')) {
            this.onFormItemValueChange({ name: 'modulename', value: null });
            this.onFormItemValueChange({ name: 'module', value: null });
        }
        if (Object.is(name, 'modulename')) {
            this.onFormItemValueChange({ name: 'storyname', value: null });
            this.onFormItemValueChange({ name: 'story', value: null });
        }
        if (Object.is(name, 'projectname')) {
            this.onFormItemValueChange({ name: 'taskname', value: null });
            this.onFormItemValueChange({ name: 'task', value: null });
        }
        if (Object.is(name, 'product')) {
            this.onFormItemValueChange({ name: 'story', value: null });
        }
        if (Object.is(name, 'project')) {
            this.onFormItemValueChange({ name: 'task', value: null });
        }
    }

    /**
     * 新建默认值
     * @memberof CaseToBugEditFormBase
     */
    public createDefault(){                    
        if (this.data.hasOwnProperty('branch')) {
            this.data['branch'] = 0;
        }
        if (this.data.hasOwnProperty('product')) {
            this.data['product'] = this.viewparams['product'];
        }
        if (this.data.hasOwnProperty('module')) {
            this.data['module'] = this.viewparams['module'];
        }
        if (this.data.hasOwnProperty('project')) {
            this.data['project'] = this.viewparams['project'];
        }
        if (this.data.hasOwnProperty('assignedto')) {
            this.data['assignedto'] = this.viewparams['srfloginname'];
        }
        if (this.data.hasOwnProperty('type')) {
            this.data['type'] = 'codeerror';
        }
        if (this.data.hasOwnProperty('title')) {
            this.data['title'] = this.viewparams['title'];
        }
        if (this.data.hasOwnProperty('steps')) {
            this.data['steps'] = this.viewparams['precondition'];
        }
        if (this.data.hasOwnProperty('story')) {
            this.data['story'] = this.viewparams['story'];
        }
        if (this.data.hasOwnProperty('case')) {
            this.data['case'] = this.viewparams['case'];
        }
    }
}