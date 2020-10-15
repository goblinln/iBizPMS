import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, EditFormControlBase } from '@/studio-core';
import BugService from '@/service/bug/bug-service';
import ResolveService from './resolve-form-service';
import BugUIService from '@/uiservice/bug/bug-ui-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {ResolveEditFormBase}
 */
export class ResolveEditFormBase extends EditFormControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof ResolveEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {ResolveService}
     * @memberof ResolveEditFormBase
     */
    public service: ResolveService = new ResolveService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {BugService}
     * @memberof ResolveEditFormBase
     */
    public appEntityService: BugService = new BugService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ResolveEditFormBase
     */
    protected appDeName: string = 'bug';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof ResolveEditFormBase
     */
    protected appDeLogicName: string = 'Bug';

    /**
     * 界面UI服务对象
     *
     * @type {BugUIService}
     * @memberof ResolveBase
     */  
    public appUIService:BugUIService = new BugUIService(this.$store);


    /**
     * 关系界面数量
     *
     * @protected
     * @type {number}
     * @memberof ResolveEditFormBase
     */
    protected drCount: number = 1;

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof ResolveEditFormBase
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
        product: null,
        id: null,
        resolution: null,
        resolvedbuild: null,
        buildproject: null,
        buildname: null,
        createbuild: null,
        resolveddate: null,
        assignedto: null,
        project: null,
        files: null,
        comment: null,
        bug:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof ResolveEditFormBase
     */
    public rules: any = {
        resolution: [
            { required: true, type: 'string', message: '解决方案 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '解决方案 值不能为空', trigger: 'blur' },
        ],
        resolvedbuild: [
            { required: true, type: 'string', message: '解决版本 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '解决版本 值不能为空', trigger: 'blur' },
        ],
        buildproject: [
            { required: true, type: 'string', message: '解决版本/所属项目 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '解决版本/所属项目 值不能为空', trigger: 'blur' },
        ],
        buildname: [
            { required: true, type: 'string', message: ' 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: ' 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof ResolveBase
     */
    public deRules:any = {
    };

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof ResolveEditFormBase
     */
    public detailsModel: any = {
        grouppanel2: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.resolve_form', extractMode: 'ITEM', details: [] } }),

        grouppanel4: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel4', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.resolve_form', extractMode: 'ITEM', details: [] } }),

        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.resolve_form', extractMode: 'ITEM', details: [] } }),

        druipart1: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel6: new FormGroupPanelModel({ caption: '历史记录', detailType: 'GROUPPANEL', name: 'grouppanel6', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.resolve_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: 'Bug基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.bug.resolve_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srfupdatedate: new FormItemModel({ caption: '修改日期', detailType: 'FORMITEM', name: 'srfupdatedate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: 'Bug编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        srfmajortext: new FormItemModel({ caption: 'Bug标题', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        product: new FormItemModel({ caption: '所属产品', detailType: 'FORMITEM', name: 'product', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        id: new FormItemModel({ caption: 'Bug编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        resolution: new FormItemModel({ caption: '解决方案', detailType: 'FORMITEM', name: 'resolution', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        resolvedbuild: new FormItemModel({ caption: '解决版本', detailType: 'FORMITEM', name: 'resolvedbuild', visible: false, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        buildproject: new FormItemModel({ caption: '解决版本/所属项目', detailType: 'FORMITEM', name: 'buildproject', visible: false, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        buildname: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'buildname', visible: false, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        createbuild: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'createbuild', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        resolveddate: new FormItemModel({ caption: '解决日期', detailType: 'FORMITEM', name: 'resolveddate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        assignedto: new FormItemModel({ caption: '指派给', detailType: 'FORMITEM', name: 'assignedto', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        project: new FormItemModel({ caption: '所属项目', detailType: 'FORMITEM', name: 'project', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        files: new FormItemModel({ caption: '附件', detailType: 'FORMITEM', name: 'files', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        comment: new FormItemModel({ caption: '备注', detailType: 'FORMITEM', name: 'comment', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

    };

    /**
     * 重置表单项值
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof ResolveEditFormBase
     */
    public resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
        if (Object.is(name, 'createbuild')) {
            this.onFormItemValueChange({ name: 'resolvedbuild', value: null });
        }
        if (Object.is(name, 'createbuild')) {
            this.onFormItemValueChange({ name: 'buildproject', value: null });
        }
        if (Object.is(name, 'createbuild')) {
            this.onFormItemValueChange({ name: 'buildname', value: null });
        }
    }

    /**
     * 表单项逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {Promise<void>}
     * @memberof ResolveEditFormBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): Promise<void> {
                


















        if (Object.is(name, '') || Object.is(name, 'createbuild')) {
            let ret = true;
            const _createbuild = this.data.createbuild;
            if (this.$verify.testCond(_createbuild, 'EQ', '1')) {
                ret = false;
            }
            this.rules.resolvedbuild.some((rule: any) => {
                if (rule.hasOwnProperty('required')) {
                    rule.required = ret;
                }
                return false;
            });
        }
        if (Object.is(name, '') || Object.is(name, 'createbuild')) {
            let ret = false;
            const _createbuild = this.data.createbuild;
            if (this.$verify.testCond(_createbuild, 'ISNULL', '')) {
                ret = true;
            }
            this.detailsModel.resolvedbuild.setVisible(ret);
        }

        if (Object.is(name, '') || Object.is(name, 'createbuild')) {
            let ret = true;
            const _createbuild = this.data.createbuild;
            if (this.$verify.testCond(_createbuild, 'ISNULL', '')) {
                ret = false;
            }
            this.rules.buildproject.some((rule: any) => {
                if (rule.hasOwnProperty('required')) {
                    rule.required = ret;
                }
                return false;
            });
        }
        if (Object.is(name, '') || Object.is(name, 'createbuild')) {
            let ret = false;
            const _createbuild = this.data.createbuild;
            if (this.$verify.testCond(_createbuild, 'EQ', '1')) {
                ret = true;
            }
            this.detailsModel.buildproject.setVisible(ret);
        }

        if (Object.is(name, '') || Object.is(name, 'createbuild')) {
            let ret = true;
            const _createbuild = this.data.createbuild;
            if (this.$verify.testCond(_createbuild, 'ISNULL', '')) {
                ret = false;
            }
            this.rules.buildname.some((rule: any) => {
                if (rule.hasOwnProperty('required')) {
                    rule.required = ret;
                }
                return false;
            });
        }
        if (Object.is(name, '') || Object.is(name, 'createbuild')) {
            let ret = false;
            const _createbuild = this.data.createbuild;
            if (this.$verify.testCond(_createbuild, 'EQ', '1')) {
                ret = true;
            }
            this.detailsModel.buildname.setVisible(ret);
        }







    }

    /**
     * 更新默认值
     * @memberof ResolveEditFormBase
     */
    public updateDefault(){                    
    }
}