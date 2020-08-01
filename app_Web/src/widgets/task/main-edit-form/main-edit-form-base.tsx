import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, EditFormControlBase } from '@/studio-core';
import TaskService from '@/service/task/task-service';
import MainEditService from './main-edit-form-service';
import TaskUIService from '@/uiservice/task/task-ui-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {MainEditEditFormBase}
 */
export class MainEditEditFormBase extends EditFormControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainEditEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {MainEditService}
     * @memberof MainEditEditFormBase
     */
    public service: MainEditService = new MainEditService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {TaskService}
     * @memberof MainEditEditFormBase
     */
    public appEntityService: TaskService = new TaskService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainEditEditFormBase
     */
    protected appDeName: string = 'task';

    /**
     * 界面UI服务对象
     *
     * @type {TaskUIService}
     * @memberof MainEditEditFormBase
     */  
    public appUIService:TaskUIService = new TaskUIService(this.$store);


    /**
     * 关系界面数量
     *
     * @protected
     * @type {number}
     * @memberof MainEditEditFormBase
     */
    protected drCount: number = 2;

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof MainEditEditFormBase
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
        id: null,
        name: null,
        multiple: null,
        desc: null,
        comment: null,
        files: null,
        projectname: null,
        project: null,
        module: null,
        modulename: null,
        allmodules: null,
        storyname: null,
        story: null,
        parentname: null,
        parent: null,
        assignedto: null,
        type: null,
        status: null,
        pri: null,
        mailto: null,
        eststarted: null,
        deadline: null,
        estimate: null,
        consumed: null,
        left: null,
        openedby: null,
        realstarted: null,
        finishedby: null,
        finisheddate: null,
        canceledby: null,
        canceleddate: null,
        closedby: null,
        closedreason: null,
        closeddate: null,
        task:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainEditEditFormBase
     */
    public rules: any = {
        name: [
            { required: true, type: 'string', message: '任务名称 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '任务名称 值不能为空', trigger: 'blur' },
        ],
        type: [
            { required: true, type: 'string', message: '任务类型 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '任务类型 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof MainEditEditFormBase
     */
    public detailsModel: any = {
        grouppanel6: new FormGroupPanelModel({ caption: '任务描述', detailType: 'GROUPPANEL', name: 'grouppanel6', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        grouppanel7: new FormGroupPanelModel({ caption: '备注', detailType: 'GROUPPANEL', name: 'grouppanel7', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        druipart1: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel2: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: '任务基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        druipart2: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart2', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel8: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel8', visible: false, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        grouppanel1: new FormGroupPanelModel({ caption: '基本信息', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        grouppanel4: new FormGroupPanelModel({ caption: '工时信息', detailType: 'GROUPPANEL', name: 'grouppanel4', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        grouppanel5: new FormGroupPanelModel({ caption: '任务的一生', detailType: 'GROUPPANEL', name: 'grouppanel5', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        grouppanel3: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel3', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.task.mainedit_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srfupdatedate: new FormItemModel({ caption: '最后修改日期', detailType: 'FORMITEM', name: 'srfupdatedate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        srfmajortext: new FormItemModel({ caption: '任务名称', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        id: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        name: new FormItemModel({ caption: '任务名称', detailType: 'FORMITEM', name: 'name', visible: true, isShowCaption: false, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        multiple: new FormItemModel({ caption: '多人任务', detailType: 'FORMITEM', name: 'multiple', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        desc: new FormItemModel({ caption: '任务描述', detailType: 'FORMITEM', name: 'desc', visible: true, isShowCaption: false, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        comment: new FormItemModel({ caption: '备注', detailType: 'FORMITEM', name: 'comment', visible: true, isShowCaption: false, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        files: new FormItemModel({ caption: '附件', detailType: 'FORMITEM', name: 'files', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        projectname: new FormItemModel({ caption: '所属项目', detailType: 'FORMITEM', name: 'projectname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        project: new FormItemModel({ caption: '所属项目', detailType: 'FORMITEM', name: 'project', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        module: new FormItemModel({ caption: 'id', detailType: 'FORMITEM', name: 'module', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        modulename: new FormItemModel({ caption: '所属模块', detailType: 'FORMITEM', name: 'modulename', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        allmodules: new FormItemModel({ caption: '所有模块', detailType: 'FORMITEM', name: 'allmodules', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        storyname: new FormItemModel({ caption: '相关需求', detailType: 'FORMITEM', name: 'storyname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        story: new FormItemModel({ caption: '相关需求', detailType: 'FORMITEM', name: 'story', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        parentname: new FormItemModel({ caption: '父任务', detailType: 'FORMITEM', name: 'parentname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        parent: new FormItemModel({ caption: '父任务', detailType: 'FORMITEM', name: 'parent', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        assignedto: new FormItemModel({ caption: '指派给', detailType: 'FORMITEM', name: 'assignedto', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        type: new FormItemModel({ caption: '任务类型', detailType: 'FORMITEM', name: 'type', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        status: new FormItemModel({ caption: '任务状态', detailType: 'FORMITEM', name: 'status', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        pri: new FormItemModel({ caption: '优先级', detailType: 'FORMITEM', name: 'pri', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        mailto: new FormItemModel({ caption: '抄送给', detailType: 'FORMITEM', name: 'mailto', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        eststarted: new FormItemModel({ caption: '预计开始', detailType: 'FORMITEM', name: 'eststarted', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        deadline: new FormItemModel({ caption: '截止日期', detailType: 'FORMITEM', name: 'deadline', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        estimate: new FormItemModel({ caption: '最初预计', detailType: 'FORMITEM', name: 'estimate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        consumed: new FormItemModel({ caption: '总计消耗', detailType: 'FORMITEM', name: 'consumed', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        left: new FormItemModel({ caption: '预计剩余', detailType: 'FORMITEM', name: 'left', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        openedby: new FormItemModel({ caption: '由谁创建', detailType: 'FORMITEM', name: 'openedby', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        realstarted: new FormItemModel({ caption: '实际开始', detailType: 'FORMITEM', name: 'realstarted', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        finishedby: new FormItemModel({ caption: '由谁完成', detailType: 'FORMITEM', name: 'finishedby', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        finisheddate: new FormItemModel({ caption: '实际完成', detailType: 'FORMITEM', name: 'finisheddate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        canceledby: new FormItemModel({ caption: '由谁取消', detailType: 'FORMITEM', name: 'canceledby', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        canceleddate: new FormItemModel({ caption: '取消时间', detailType: 'FORMITEM', name: 'canceleddate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        closedby: new FormItemModel({ caption: '由谁关闭', detailType: 'FORMITEM', name: 'closedby', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        closedreason: new FormItemModel({ caption: '关闭原因', detailType: 'FORMITEM', name: 'closedreason', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        closeddate: new FormItemModel({ caption: '关闭时间', detailType: 'FORMITEM', name: 'closeddate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

    };

    /**
     * 表单项逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {Promise<void>}
     * @memberof MainEditEditFormBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): Promise<void> {
                






        if (Object.is(name, '') || Object.is(name, 'multiple')) {
            let ret = false;
            const _multiple = this.data.multiple;
            if (this.$verify.testCond(_multiple, 'EQ', '1')) {
                ret = true;
            }
            this.detailsModel.grouppanel8.setVisible(ret);
        }




































        if (Object.is(name, '') || Object.is(name, 'multiple')) {
            let ret = false;
            const _multiple = this.data.multiple;
            if (this.$verify.testCond(_multiple, 'ISNULL', '') || this.$verify.testCond(_multiple, 'EQ', '0')) {
                ret = true;
            }
            this.detailsModel.estimate.setDisabled(!ret);
        }

        if (Object.is(name, '') || Object.is(name, 'multiple')) {
            let ret = false;
            const _multiple = this.data.multiple;
            if (this.$verify.testCond(_multiple, 'EQ', '0') || this.$verify.testCond(_multiple, 'ISNULL', '')) {
                ret = true;
            }
            this.detailsModel.consumed.setDisabled(!ret);
        }

        if (Object.is(name, '') || Object.is(name, 'multiple')) {
            let ret = false;
            const _multiple = this.data.multiple;
            if (this.$verify.testCond(_multiple, 'EQ', '0') || this.$verify.testCond(_multiple, 'ISNULL', '')) {
                ret = true;
            }
            this.detailsModel.left.setDisabled(!ret);
        }










    }
}