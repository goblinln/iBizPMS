import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { OptionViewBase } from '@/studio-core';
import TestTaskService from '@/service/test-task/test-task-service';
import TestTaskAuthService from '@/authservice/test-task/test-task-auth-service';
import OptionViewEngine from '@engine/view/option-view-engine';
import TestTaskUIService from '@/uiservice/test-task/test-task-ui-service';

/**
 * 阻塞视图基类
 *
 * @export
 * @class TestTaskBlockOptionViewBase
 * @extends {OptionViewBase}
 */
export class TestTaskBlockOptionViewBase extends OptionViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof TestTaskBlockOptionViewBase
     */
    protected appDeName: string = 'testtask';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof TestTaskBlockOptionViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof TestTaskBlockOptionViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof TestTaskBlockOptionViewBase
     */ 
    protected dataControl:string = "form";

    /**
     * 实体服务对象
     *
     * @type {TestTaskService}
     * @memberof TestTaskBlockOptionViewBase
     */
    protected appEntityService: TestTaskService = new TestTaskService;

    /**
     * 实体权限服务对象
     *
     * @type TestTaskUIService
     * @memberof TestTaskBlockOptionViewBase
     */
    public appUIService: TestTaskUIService = new TestTaskUIService(this.$store);

	/**
	 * 自定义视图导航上下文集合
	 *
     * @protected
	 * @type {*}
	 * @memberof TestTaskBlockOptionViewBase
	 */
    protected customViewNavContexts: any = {
        'OBJECTTYPE': { isRawValue: true, value: 'testtask' }
    };

    /**
     * 是否显示信息栏
     *
     * @memberof TestTaskBlockOptionViewBase
     */
    isShowDataInfoBar = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof TestTaskBlockOptionViewBase
     */
    protected model: any = {
        srfCaption: 'entities.testtask.views.blockoptionview.caption',
        srfTitle: 'entities.testtask.views.blockoptionview.title',
        srfSubTitle: 'entities.testtask.views.blockoptionview.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof TestTaskBlockOptionViewBase
     */
    protected containerModel: any = {
        view_form: { name: 'form', type: 'FORM' },
        view_okbtn: { name: 'okbtn', type: 'button', text: '确定', disabled: true },
        view_cancelbtn: { name: 'cancelbtn', type: 'button', text: '取消', disabled: false },
        view_leftbtn: { name: 'leftbtn', type: 'button', text: '左移', disabled: true },
        view_rightbtn: { name: 'rightbtn', type: 'button', text: '右移', disabled: true },
        view_allleftbtn: { name: 'allleftbtn', type: 'button', text: '全部左移', disabled: true },
        view_allrightbtn: { name: 'allrightbtn', type: 'button', text: '全部右移', disabled: true },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof TestTaskBlockOptionViewBase
     */
	protected viewtag: string = '383716f2bcf137ecae2d212f2380246a';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof TestTaskBlockOptionViewBase
     */ 
    protected viewName:string = "TestTaskBlockOptionView";


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TestTaskBlockOptionViewBase
     */
    public engine: OptionViewEngine = new OptionViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof TestTaskBlockOptionViewBase
     */    
    public counterServiceArray:Array<any> = [];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof TestTaskBlockOptionViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            form: this.$refs.form,
            p2k: '0',
            keyPSDEField: 'testtask',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * form 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskBlockOptionViewBase
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskBlockOptionViewBase
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskBlockOptionViewBase
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }


}