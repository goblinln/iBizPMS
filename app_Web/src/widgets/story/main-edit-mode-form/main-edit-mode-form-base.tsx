import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, EditFormControlBase } from '@/studio-core';
import StoryService from '@/service/story/story-service';
import Main_EditModeService from './main-edit-mode-form-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {Main_EditModeEditFormBase}
 */
export class Main_EditModeEditFormBase extends EditFormControlBase {

    /**
     * 建构部件服务对象
     *
     * @type {Main_EditModeService}
     * @memberof Main_EditModeEditFormBase
     */
    public service: Main_EditModeService = new Main_EditModeService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {StoryService}
     * @memberof Main_EditModeEditFormBase
     */
    public appEntityService: StoryService = new StoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof Main_EditModeEditFormBase
     */
    protected appDeName: string = 'story';

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
        this.SaveAndExit(datas, contextJO,paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public form_button2_click(params: any = {}, tag?: any, $event?: any) {
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
        this.Exit(datas, contextJO,paramJO,  $event, xData,this,"Story");
    }

    /**
     * 保存并关闭
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof StoryMainView9_EditModeBase
     */
    public SaveAndExit(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        const _this: any = this;
        if (xData && xData.saveAndExit instanceof Function) {
            xData.saveAndExit().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                if(window.parent){
                    window.parent.postMessage([{ ...response.data }],'*');
                }
            });
        } else if (_this.saveAndExit && _this.saveAndExit instanceof Function) {
            _this.saveAndExit().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                if(window.parent){
                    window.parent.postMessage([{ ...response.data }],'*');
                }
            });
        }
    }

    /**
     * 返回
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof StoryMainView9_EditModeBase
     */
    public Exit(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        this.closeView(args);
        if(window.parent){
            window.parent.postMessage([{ ...args }],'*');
        }
    }


    /**
     * 关系界面数量
     *
     * @protected
     * @type {number}
     * @memberof Main_EditModeEditFormBase
     */
    protected drCount: number = 0;

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof Main_EditModeEditFormBase
     */
    public data: any = {
        srforikey: null,
        srfkey: null,
        srfmajortext: null,
        srftempmode: null,
        srfuf: null,
        srfdeid: null,
        srfsourcekey: null,
        parent: null,
        version: null,
        spec: null,
        verify: null,
        title: null,
        prodoctname: null,
        modulename: null,
        plan: null,
        source: null,
        sourcenote: null,
        status: null,
        stage: null,
        pri: null,
        estimate: null,
        keywords: null,
        mailto: null,
        openedby: null,
        openeddate: null,
        assignedto: null,
        assigneddate: null,
        linkstories: null,
        id: null,
        product: null,
        module: null,
        story:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof Main_EditModeEditFormBase
     */
    public rules: any = {
        srforikey: [
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'blur' },
        ],
        srfkey: [
            { required: false, type: 'number', message: '编号 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '编号 值不能为空', trigger: 'blur' },
        ],
        srfmajortext: [
            { required: false, type: 'string', message: '需求名称 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '需求名称 值不能为空', trigger: 'blur' },
        ],
        srftempmode: [
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'blur' },
        ],
        srfuf: [
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'blur' },
        ],
        srfdeid: [
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'blur' },
        ],
        srfsourcekey: [
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: ' 值不能为空', trigger: 'blur' },
        ],
        parent: [
            { required: false, type: 'number', message: '父需求 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '父需求 值不能为空', trigger: 'blur' },
        ],
        version: [
            { required: false, type: 'number', message: '版本# 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '版本# 值不能为空', trigger: 'blur' },
        ],
        spec: [
            { required: false, type: 'string', message: '需求描述 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '需求描述 值不能为空', trigger: 'blur' },
        ],
        verify: [
            { required: false, type: 'string', message: '验收标准 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '验收标准 值不能为空', trigger: 'blur' },
        ],
        title: [
            { required: true, type: 'string', message: '需求名称 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '需求名称 值不能为空', trigger: 'blur' },
        ],
        prodoctname: [
            { required: false, type: 'string', message: '所属产品 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '所属产品 值不能为空', trigger: 'blur' },
        ],
        modulename: [
            { required: false, type: 'string', message: '所属模块 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '所属模块 值不能为空', trigger: 'blur' },
        ],
        plan: [
            { required: false, type: 'string', message: '所属计划 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '所属计划 值不能为空', trigger: 'blur' },
        ],
        source: [
            { required: false, type: 'string', message: '需求来源 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '需求来源 值不能为空', trigger: 'blur' },
        ],
        sourcenote: [
            { required: false, type: 'string', message: '来源备注 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '来源备注 值不能为空', trigger: 'blur' },
        ],
        status: [
            { required: false, type: 'string', message: '当前状态 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '当前状态 值不能为空', trigger: 'blur' },
        ],
        stage: [
            { required: false, type: 'string', message: '所处阶段 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '所处阶段 值不能为空', trigger: 'blur' },
        ],
        pri: [
            { required: false, type: 'number', message: '优先级 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '优先级 值不能为空', trigger: 'blur' },
        ],
        estimate: [
            { required: false, type: 'number', message: '预计工时 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '预计工时 值不能为空', trigger: 'blur' },
        ],
        keywords: [
            { required: false, type: 'string', message: '关键词 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '关键词 值不能为空', trigger: 'blur' },
        ],
        mailto: [
            { required: false, type: 'string', message: '抄送给 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '抄送给 值不能为空', trigger: 'blur' },
        ],
        openedby: [
            { required: false, type: 'string', message: '由谁创建 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '由谁创建 值不能为空', trigger: 'blur' },
        ],
        openeddate: [
            { required: false, type: 'string', message: '于 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '于 值不能为空', trigger: 'blur' },
        ],
        assignedto: [
            { required: false, type: 'string', message: '指派给 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '指派给 值不能为空', trigger: 'blur' },
        ],
        assigneddate: [
            { required: false, type: 'string', message: '于 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '于 值不能为空', trigger: 'blur' },
        ],
        linkstories: [
            { required: false, type: 'string', message: '相关需求 值不能为空', trigger: 'change' },
            { required: false, type: 'string', message: '相关需求 值不能为空', trigger: 'blur' },
        ],
        id: [
            { required: false, type: 'number', message: '编号 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '编号 值不能为空', trigger: 'blur' },
        ],
        product: [
            { required: false, type: 'number', message: '所属产品 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '所属产品 值不能为空', trigger: 'blur' },
        ],
        module: [
            { required: false, type: 'number', message: '所属模块 值不能为空', trigger: 'change' },
            { required: false, type: 'number', message: '所属模块 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof Main_EditModeEditFormBase
     */
    public detailsModel: any = {
        grouppanel11: new FormGroupPanelModel({ caption: '需求描述', detailType: 'GROUPPANEL', name: 'grouppanel11', visible: true, isShowCaption: true, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        grouppanel21: new FormGroupPanelModel({ caption: '验收标准', detailType: 'GROUPPANEL', name: 'grouppanel21', visible: true, isShowCaption: true, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        group11: new FormGroupPanelModel({ caption: '需求描述信息', detailType: 'GROUPPANEL', name: 'group11', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        druipart1: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart1', visible: false, isShowCaption: true, form: this }),

        grouppanel31: new FormGroupPanelModel({ caption: '需求描述', detailType: 'GROUPPANEL', name: 'grouppanel31', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        druipart12: new FormDRUIPartModel({ caption: '操作历史', detailType: 'DRUIPART', name: 'druipart12', visible: true, isShowCaption: true, form: this }),

        grouppanel5: new FormGroupPanelModel({ caption: '', detailType: 'GROUPPANEL', name: 'grouppanel5', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: 'story基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        grouppanel2: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        group2: new FormGroupPanelModel({ caption: '需求的一生', detailType: 'GROUPPANEL', name: 'group2', visible: true, isShowCaption: true, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        grouppanel3: new FormGroupPanelModel({ caption: '其他相关', detailType: 'GROUPPANEL', name: 'grouppanel3', visible: true, isShowCaption: true, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        button1: new FormButtonModel({ caption: '保存', detailType: 'BUTTON', name: 'button1', visible: true, isShowCaption: true, form: this, uiaction: { type: 'DEUIACTION', tag: 'SaveAndExit' } }),

        button2: new FormButtonModel({ caption: '返回', detailType: 'BUTTON', name: 'button2', visible: true, isShowCaption: true, form: this, uiaction: { type: 'DEUIACTION', tag: 'Exit' } }),

        grouppanel4: new FormGroupPanelModel({ caption: '按钮', detailType: 'GROUPPANEL', name: 'grouppanel4', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        grouppanel6: new FormGroupPanelModel({ caption: '', detailType: 'GROUPPANEL', name: 'grouppanel6', visible: true, isShowCaption: false, form: this, uiActionGroup: { caption: '', langbase: 'entities.story.main_editmode_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 0 }),

        srfmajortext: new FormItemModel({ caption: '需求名称', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        parent: new FormItemModel({ caption: '父需求', detailType: 'FORMITEM', name: 'parent', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        version: new FormItemModel({ caption: '版本#', detailType: 'FORMITEM', name: 'version', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        spec: new FormItemModel({ caption: '需求描述', detailType: 'FORMITEM', name: 'spec', visible: true, isShowCaption: false, form: this, disabled: false, enableCond: 3 }),

        verify: new FormItemModel({ caption: '验收标准', detailType: 'FORMITEM', name: 'verify', visible: true, isShowCaption: false, form: this, disabled: false, enableCond: 3 }),

        title: new FormItemModel({ caption: '需求名称', detailType: 'FORMITEM', name: 'title', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        prodoctname: new FormItemModel({ caption: '所属产品', detailType: 'FORMITEM', name: 'prodoctname', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        modulename: new FormItemModel({ caption: '所属模块', detailType: 'FORMITEM', name: 'modulename', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        plan: new FormItemModel({ caption: '所属计划', detailType: 'FORMITEM', name: 'plan', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        source: new FormItemModel({ caption: '需求来源', detailType: 'FORMITEM', name: 'source', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        sourcenote: new FormItemModel({ caption: '来源备注', detailType: 'FORMITEM', name: 'sourcenote', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        status: new FormItemModel({ caption: '当前状态', detailType: 'FORMITEM', name: 'status', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        stage: new FormItemModel({ caption: '所处阶段', detailType: 'FORMITEM', name: 'stage', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        pri: new FormItemModel({ caption: '优先级', detailType: 'FORMITEM', name: 'pri', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        estimate: new FormItemModel({ caption: '预计工时', detailType: 'FORMITEM', name: 'estimate', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        keywords: new FormItemModel({ caption: '关键词', detailType: 'FORMITEM', name: 'keywords', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        mailto: new FormItemModel({ caption: '抄送给', detailType: 'FORMITEM', name: 'mailto', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        openedby: new FormItemModel({ caption: '由谁创建', detailType: 'FORMITEM', name: 'openedby', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        openeddate: new FormItemModel({ caption: '于', detailType: 'FORMITEM', name: 'openeddate', visible: false, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        assignedto: new FormItemModel({ caption: '指派给', detailType: 'FORMITEM', name: 'assignedto', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        assigneddate: new FormItemModel({ caption: '于', detailType: 'FORMITEM', name: 'assigneddate', visible: false, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        linkstories: new FormItemModel({ caption: '相关需求', detailType: 'FORMITEM', name: 'linkstories', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        id: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 0 }),

        product: new FormItemModel({ caption: '所属产品', detailType: 'FORMITEM', name: 'product', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

        module: new FormItemModel({ caption: '所属模块', detailType: 'FORMITEM', name: 'module', visible: true, isShowCaption: true, form: this, disabled: false, enableCond: 3 }),

    };

    /**
     * 表单逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof Main_EditModeEditFormBase
     */
    public formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
                



        if (Object.is(name, '') || Object.is(name, 'parent')) {
            let ret = false;
            const _parent = this.data.parent;
            if (this.$verify.testCond(_parent, 'EQ', '-1')) {
                ret = true;
            }
            this.detailsModel.druipart1.setVisible(ret);
        }






































        if (Object.is(name, '') || Object.is(name, 'openeddate')) {
            let ret = false;
            const _openeddate = this.data.openeddate;
            if (this.$verify.testCond(_openeddate, 'ISNOTNULL', '')) {
                ret = true;
            }
            this.detailsModel.openeddate.setVisible(ret);
        }


        if (Object.is(name, '') || Object.is(name, 'assigneddate')) {
            let ret = false;
            const _assigneddate = this.data.assigneddate;
            if (this.$verify.testCond(_assigneddate, 'ISNOTNULL', '')) {
                ret = true;
            }
            this.detailsModel.assigneddate.setVisible(ret);
        }





        if (Object.is(name, 'version')) {
            const details: string[] = ['verify', 'spec'];
            this.updateFormItems('GetStorySpec', this.data, details, true);
        }
    }

	/**
	 * 表单 保存 事件
	 *
	 * @memberof @memberof Main_EditModeEditFormBase
	 */
    public button1_click($event: any): void {
        this.form_button1_click(null, null, $event);

    }

	/**
	 * 表单 返回 事件
	 *
	 * @memberof @memberof Main_EditModeEditFormBase
	 */
    public button2_click($event: any): void {
        this.form_button2_click(null, null, $event);

    }
}