import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { EditViewBase } from '@/studio-core';
import IbzDailyService from '@/service/ibz-daily/ibz-daily-service';
import IbzDailyAuthService from '@/authservice/ibz-daily/ibz-daily-auth-service';
import EditViewEngine from '@engine/view/edit-view-engine';
import IbzDailyUIService from '@/uiservice/ibz-daily/ibz-daily-ui-service';

/**
 * 日报编辑视图视图基类
 *
 * @export
 * @class IbzDailyDailyEditViewBase
 * @extends {EditViewBase}
 */
export class IbzDailyDailyEditViewBase extends EditViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof IbzDailyDailyEditViewBase
     */
    protected appDeName: string = 'ibzdaily';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof IbzDailyDailyEditViewBase
     */
    protected appDeKey: string = 'ibz_dailyid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof IbzDailyDailyEditViewBase
     */
    protected appDeMajor: string = 'ibz_dailyname';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof IbzDailyDailyEditViewBase
     */ 
    protected dataControl: string = 'form';

    /**
     * 实体服务对象
     *
     * @type {IbzDailyService}
     * @memberof IbzDailyDailyEditViewBase
     */
    protected appEntityService: IbzDailyService = new IbzDailyService;

    /**
     * 实体权限服务对象
     *
     * @type IbzDailyUIService
     * @memberof IbzDailyDailyEditViewBase
     */
    public appUIService: IbzDailyUIService = new IbzDailyUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof IbzDailyDailyEditViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof IbzDailyDailyEditViewBase
     */
    protected model: any = {
        srfCaption: 'entities.ibzdaily.views.dailyeditview.caption',
        srfTitle: 'entities.ibzdaily.views.dailyeditview.title',
        srfSubTitle: 'entities.ibzdaily.views.dailyeditview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof IbzDailyDailyEditViewBase
     */
    protected containerModel: any = {
        view_toolbar: {
            name: 'toolbar',
            type: 'TOOLBAR',
        },
        view_form: {
            name: 'form',
            type: 'FORM',
        },
    };

    /**
     * 工具栏模型
     *
     * @type {*}
     * @memberof IbzDailyDailyEditView
     */
    public toolBarModels: any = {
        deuiaction3_submit: { name: 'deuiaction3_submit', caption: '提交', 'isShowCaption': true, 'isShowIcon': true, tooltip: '提交', disabled: false, type: 'DEUIACTION', visible: true,noprivdisplaymode:2,dataaccaction: '', uiaction: { tag: 'submit', target: 'SINGLEKEY', class: '' } },

        deuiaction1: { name: 'deuiaction1', caption: '保存', 'isShowCaption': true, 'isShowIcon': true, tooltip: '保存', iconcls: 'fa fa-save', icon: '', disabled: false, type: 'DEUIACTION', visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__UNIVERSALSAVE', uiaction: { tag: 'Save', target: '', class: '' } },

    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof IbzDailyDailyEditViewBase
     */
	protected viewtag: string = '1edacc9f865e00cd6cd2ea0475e8dd26';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof IbzDailyDailyEditViewBase
     */ 
    protected viewName: string = 'IbzDailyDailyEditView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof IbzDailyDailyEditViewBase
     */
    public engine: EditViewEngine = new EditViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof IbzDailyDailyEditViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof IbzDailyDailyEditViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            form: this.$refs.form,
            p2k: '0',
            keyPSDEField: 'ibzdaily',
            majorPSDEField: 'ibzdailyname',
            isLoadDefault: true,
        });
    }

    /**
     * toolbar 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbzDailyDailyEditViewBase
     */
    public toolbar_click($event: any, $event2?: any): void {
        if (Object.is($event.tag, 'deuiaction3_submit')) {
            this.toolbar_deuiaction3_submit_click(null, '', $event2);
        }
        if (Object.is($event.tag, 'deuiaction1')) {
            this.toolbar_deuiaction1_click(null, '', $event2);
        }
    }

    /**
     * form 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbzDailyDailyEditViewBase
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbzDailyDailyEditViewBase
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbzDailyDailyEditViewBase
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public toolbar_deuiaction3_submit_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this.$refs.form;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:IbzDailyUIService  = new IbzDailyUIService();
        curUIService.IbzDaily_submit(datas,contextJO, paramJO,  $event, xData,this,"IbzDaily");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public toolbar_deuiaction1_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this.$refs.form;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        this.Save(datas, contextJO,paramJO,  $event, xData,this,"IbzDaily");
    }

    /**
     * 保存
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof IbzDailyDailyEditViewBase
     */
    public Save(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        // 界面行为容器对象 _this
        const _this: any = this;
        if (xData && xData.save instanceof Function) {
            xData.save().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                _this.$emit('viewdataschange', [{ ...response.data }]);
            });
        } else if (_this.save && _this.save instanceof Function) {
            _this.save();
        }
    }


}