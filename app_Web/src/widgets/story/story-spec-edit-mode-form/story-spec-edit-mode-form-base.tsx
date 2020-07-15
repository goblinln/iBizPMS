import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, EditFormControlBase } from '@/studio-core';
import StoryService from '@/service/story/story-service';
import StorySpec_EditModeService from './story-spec-edit-mode-form-service';
import StoryUIService from '@/uiservice/story/story-ui-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


/**
 * form部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {StorySpec_EditModeEditFormBase}
 */
export class StorySpec_EditModeEditFormBase extends EditFormControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof StorySpec_EditModeEditFormBase
     */
    protected controlType: string = 'FORM';

    /**
     * 建构部件服务对象
     *
     * @type {StorySpec_EditModeService}
     * @memberof StorySpec_EditModeEditFormBase
     */
    public service: StorySpec_EditModeService = new StorySpec_EditModeService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {StoryService}
     * @memberof StorySpec_EditModeEditFormBase
     */
    public appEntityService: StoryService = new StoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof StorySpec_EditModeEditFormBase
     */
    protected appDeName: string = 'story';

    /**
     * 界面UI服务对象
     *
     * @type {StoryUIService}
     * @memberof StorySpec_EditModeEditFormBase
     */  
    public appUIService:StoryUIService = new StoryUIService(this.$store);


    /**
     * 关系界面数量
     *
     * @protected
     * @type {number}
     * @memberof StorySpec_EditModeEditFormBase
     */
    protected drCount: number = 1;

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof StorySpec_EditModeEditFormBase
     */
    public data: any = {
        srforikey: null,
        srfkey: null,
        srfmajortext: null,
        srftempmode: null,
        srfuf: null,
        srfdeid: null,
        srfsourcekey: null,
        id: null,
        status: null,
        version: null,
        reviewedby: null,
        notreview: null,
        title: null,
        spec: null,
        verify: null,
        comment: null,
        files: null,
        story:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof StorySpec_EditModeEditFormBase
     */
    public rules: any = {
        title: [
            { required: true, type: 'string', message: '需求名称 值不能为空', trigger: 'change' },
            { required: true, type: 'string', message: '需求名称 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof StorySpec_EditModeEditFormBase
     */
    public detailsModel: any = {
        grouppanel1: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.story.storyspec_editmode_form', extractMode: 'ITEM', details: [] } }),

        druipart1: new FormDRUIPartModel({ caption: '', detailType: 'DRUIPART', name: 'druipart1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        grouppanel2: new FormGroupPanelModel({ caption: '分组面板', detailType: 'GROUPPANEL', name: 'grouppanel2', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.story.storyspec_editmode_form', extractMode: 'ITEM', details: [] } }),

        group1: new FormGroupPanelModel({ caption: '需求描述信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.story.storyspec_editmode_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        srfmajortext: new FormItemModel({ caption: '需求名称', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        id: new FormItemModel({ caption: '编号', detailType: 'FORMITEM', name: 'id', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 0 }),

        status: new FormItemModel({ caption: '当前状态', detailType: 'FORMITEM', name: 'status', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        version: new FormItemModel({ caption: '版本#', detailType: 'FORMITEM', name: 'version', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        reviewedby: new FormItemModel({ caption: '由谁评审', detailType: 'FORMITEM', name: 'reviewedby', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        notreview: new FormItemModel({ caption: '不需要评审', detailType: 'FORMITEM', name: 'notreview', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        title: new FormItemModel({ caption: '需求名称', detailType: 'FORMITEM', name: 'title', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        spec: new FormItemModel({ caption: '需求描述', detailType: 'FORMITEM', name: 'spec', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        verify: new FormItemModel({ caption: '验收标准', detailType: 'FORMITEM', name: 'verify', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        comment: new FormItemModel({ caption: '备注', detailType: 'FORMITEM', name: 'comment', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

        files: new FormItemModel({ caption: '附件', detailType: 'FORMITEM', name: 'files', visible: true, isShowCaption: true, form: this, showMoreMode: 0, disabled: false, enableCond: 3 }),

    };

    /**
     * 重置表单项值
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof StorySpec_EditModeEditFormBase
     */
    public resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
        if (Object.is(name, 'notreview')) {
            this.onFormItemValueChange({ name: 'reviewedby', value: null });
        }
    }

    /**
     * 表单项逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {Promise<void>}
     * @memberof StorySpec_EditModeEditFormBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): Promise<void> {
                















        if (Object.is(name, '') || Object.is(name, 'notreview')) {
            let ret = false;
            const _notreview = this.data.notreview;
            if (this.$verify.testCond(_notreview, 'EQ', '0') || this.$verify.testCond(_notreview, 'ISNULL', '')) {
                ret = true;
            }
            this.detailsModel.reviewedby.setDisabled(!ret);
        }







        if (Object.is(name, 'version')) {
            const details: string[] = ['verify', 'spec'];
            this.updateFormItems('GetStorySpec', this.data, details, true);
        }
    }
}