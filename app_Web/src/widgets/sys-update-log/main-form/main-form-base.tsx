import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, EditFormControlBase } from '@/studio-core';
import SysUpdateLogService from '@/service/sys-update-log/sys-update-log-service';
import MainService from './main-form-service';
import SysUpdateLogUIService from '@/uiservice/sys-update-log/sys-update-log-ui-service';
import { FormButtonModel, FormPageModel, FormItemModel, FormDRUIPartModel, FormPartModel, FormGroupPanelModel, FormIFrameModel, FormRowItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel } from '@/model/form-detail';


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
     * @type {SysUpdateLogService}
     * @memberof MainEditFormBase
     */
    public appEntityService: SysUpdateLogService = new SysUpdateLogService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainEditFormBase
     */
    protected appDeName: string = 'sysupdatelog';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainEditFormBase
     */
    protected appDeLogicName: string = '系统更新日志';

    /**
     * 界面UI服务对象
     *
     * @type {SysUpdateLogUIService}
     * @memberof MainBase
     */  
    public appUIService:SysUpdateLogUIService = new SysUpdateLogUIService(this.$store);

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof MainEditFormBase
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
        sys_update_logname: null,
        update: null,
        latestupdate: null,
        updesc: null,
        createman: null,
        createdate: null,
        updateman: null,
        updatedate: null,
        sys_update_logid: null,
        sysupdatelog:null,
    };

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainEditFormBase
     */
    public rules():any{
        return {
        sys_update_logname: [
            { required: this.detailsModel.sys_update_logname.required, type: 'string', message: '更新名称 值不能为空', trigger: 'change' },
            { required: this.detailsModel.sys_update_logname.required, type: 'string', message: '更新名称 值不能为空', trigger: 'blur' },
        ],
        update: [
            { required: this.detailsModel.update.required, type: 'string', message: '更新日期 值不能为空', trigger: 'change' },
            { required: this.detailsModel.update.required, type: 'string', message: '更新日期 值不能为空', trigger: 'blur' },
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
        group1: new FormGroupPanelModel({ caption: '系统更新日志基本信息', detailType: 'GROUPPANEL', name: 'group1', visible: true, isShowCaption: false, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.sysupdatelog.main_form', extractMode: 'ITEM', details: [] } }),

        formpage1: new FormPageModel({ caption: '基本信息', detailType: 'FORMPAGE', name: 'formpage1', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        group2: new FormGroupPanelModel({ caption: '操作信息', detailType: 'GROUPPANEL', name: 'group2', visible: true, isShowCaption: true, form: this, showMoreMode: 0, uiActionGroup: { caption: '', langbase: 'entities.sysupdatelog.main_form', extractMode: 'ITEM', details: [] } }),

        formpage2: new FormPageModel({ caption: '其它', detailType: 'FORMPAGE', name: 'formpage2', visible: true, isShowCaption: true, form: this, showMoreMode: 0 }),

        srfupdatedate: new FormItemModel({ caption: '更新时间', detailType: 'FORMITEM', name: 'srfupdatedate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srforikey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srforikey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfkey: new FormItemModel({ caption: '系统更新日志标识', detailType: 'FORMITEM', name: 'srfkey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfmajortext: new FormItemModel({ caption: '更新名称', detailType: 'FORMITEM', name: 'srfmajortext', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srftempmode: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srftempmode', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfuf: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfuf', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfdeid: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfdeid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        srfsourcekey: new FormItemModel({ caption: '', detailType: 'FORMITEM', name: 'srfsourcekey', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        sys_update_logname: new FormItemModel({ caption: '更新名称', detailType: 'FORMITEM', name: 'sys_update_logname', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:true, disabled: false, enableCond: 3 }),

        update: new FormItemModel({ caption: '更新日期', detailType: 'FORMITEM', name: 'update', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:true, disabled: false, enableCond: 3 }),

        latestupdate: new FormItemModel({ caption: '最新更新', detailType: 'FORMITEM', name: 'latestupdate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        updesc: new FormItemModel({ caption: '更新说明', detailType: 'FORMITEM', name: 'updesc', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        createman: new FormItemModel({ caption: '建立人', detailType: 'FORMITEM', name: 'createman', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        createdate: new FormItemModel({ caption: '建立时间', detailType: 'FORMITEM', name: 'createdate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        updateman: new FormItemModel({ caption: '更新人', detailType: 'FORMITEM', name: 'updateman', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        updatedate: new FormItemModel({ caption: '更新时间', detailType: 'FORMITEM', name: 'updatedate', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        sys_update_logid: new FormItemModel({ caption: '系统更新日志标识', detailType: 'FORMITEM', name: 'sys_update_logid', visible: true, isShowCaption: true, form: this, showMoreMode: 0, required:false, disabled: false, enableCond: 3 }),

        form: new FormTabPanelModel({ caption: 'form', detailType: 'TABPANEL', name: 'form', visible: true, isShowCaption: true, form: this, tabPages: [{ name: 'formpage1', index: 0, visible: true }, { name: 'formpage2', index: 1, visible: true }] }),
    };
}