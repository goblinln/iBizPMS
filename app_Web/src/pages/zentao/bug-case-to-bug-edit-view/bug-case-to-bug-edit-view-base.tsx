import { Subject } from 'rxjs';
import { ViewTool } from '@/utils';
import { EditViewBase } from '@/studio-core';
import BugService from '@/service/bug/bug-service';
import BugAuthService from '@/authservice/bug/bug-auth-service';
import EditViewEngine from '@engine/view/edit-view-engine';
import BugUIService from '@/uiservice/bug/bug-ui-service';

/**
 * Bug视图基类
 *
 * @export
 * @class BugCaseToBugEditViewBase
 * @extends {EditViewBase}
 */
export class BugCaseToBugEditViewBase extends EditViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof BugCaseToBugEditViewBase
     */
    protected appDeName: string = 'bug';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof BugCaseToBugEditViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof BugCaseToBugEditViewBase
     */
    protected appDeMajor: string = 'title';

    /**
     * 实体服务对象
     *
     * @type {BugService}
     * @memberof BugCaseToBugEditViewBase
     */
    protected appEntityService: BugService = new BugService;

    /**
     * 实体权限服务对象
     *
     * @type BugUIService
     * @memberof BugCaseToBugEditViewBase
     */
    public appUIService: BugUIService = new BugUIService(this.$store);


    /**
     * 计数器服务对象集合
     *
     * @protected
     * @type {Array<*>}
     * @memberof BugCaseToBugEditViewBase
     */    
    protected counterServiceArray: Array<any> = [];

	/**
	 * 自定义视图导航上下文集合
	 *
     * @protected
	 * @type {*}
	 * @memberof BugCaseToBugEditViewBase
	 */
    protected customViewNavContexts: any = {
        'STORY': { isRawValue: false, value: 'story' },
        'MODULE': { isRawValue: false, value: 'module' },
        'PRECONDITION': { isRawValue: false, value: 'precondition' },
        'PRODUCT': { isRawValue: false, value: 'product' },
        'TITLE': { isRawValue: false, value: 'title' },
        'CASE': { isRawValue: false, value: 'case' }
    };

	/**
	 * 自定义视图导航参数集合
	 *
     * @protected
	 * @type {*}
	 * @memberof BugCaseToBugEditViewBase
	 */
    protected customViewParams: any = {
        'product': { isRawValue: false, value: 'product' },
        'story': { isRawValue: false, value: 'story' },
        'module': { isRawValue: false, value: 'module' },
        'precondition': { isRawValue: false, value: 'precondition' },
        'title': { isRawValue: false, value: 'title' },
        'case': { isRawValue: false, value: 'case' }
    };

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof BugCaseToBugEditViewBase
     */
    protected model: any = {
        srfCaption: 'entities.bug.views.casetobugeditview.caption',
        srfTitle: 'entities.bug.views.casetobugeditview.title',
        srfSubTitle: 'entities.bug.views.casetobugeditview.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof BugCaseToBugEditViewBase
     */
    protected containerModel: any = {
        view_toolbar: { name: 'toolbar', type: 'TOOLBAR' },
        view_form: { name: 'form', type: 'FORM' },
    };

    /**
     * 工具栏模型
     *
     * @type {*}
     * @memberof BugCaseToBugEditView
     */
    public toolBarModels: any = {
        deuiaction1: { name: 'deuiaction1', caption: '保存并关闭', 'isShowCaption': true, 'isShowIcon': true, tooltip: '保存并关闭', iconcls: 'sx-tb-saveandclose', icon: '../sasrfex/images/default/icon_saveandclose.png', disabled: false, type: 'DEUIACTION', visabled: true,noprivdisplaymode:2,dataaccaction: '', uiaction: { tag: 'SaveAndExit', target: '', class: '' } },

    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ViewBase
     */
	protected viewtag: string = 'd5171a52df7b22d69c6a5ca34699233a';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof BugCaseToBugEditViewBase
     */
    public engine: EditViewEngine = new EditViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof BugCaseToBugEditViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            form: this.$refs.form,
            p2k: '0',
            keyPSDEField: 'bug',
            majorPSDEField: 'title',
            isLoadDefault: true,
        });
    }

    /**
     * toolbar 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugCaseToBugEditViewBase
     */
    public toolbar_click($event: any, $event2?: any): void {
        if (Object.is($event.tag, 'deuiaction1')) {
            this.toolbar_deuiaction1_click(null, '', $event2);
        }
    }

    /**
     * form 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugCaseToBugEditViewBase
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugCaseToBugEditViewBase
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugCaseToBugEditViewBase
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
        this.SaveAndExit(datas, contextJO,paramJO,  $event, xData,this,"Bug");
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
     * @memberof BugCaseToBugEditViewBase
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

}