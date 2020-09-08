import { Subject } from 'rxjs';
import { ViewTool } from '@/utils';
import { EditView9Base } from '@/studio-core';
import TestTaskService from '@/service/test-task/test-task-service';
import TestTaskAuthService from '@/authservice/test-task/test-task-auth-service';
import EditView9Engine from '@engine/view/edit-view9-engine';
import TestTaskUIService from '@/uiservice/test-task/test-task-ui-service';

/**
 * 测试版本编辑视图视图基类
 *
 * @export
 * @class TestTaskEditView9_InfoBase
 * @extends {EditView9Base}
 */
export class TestTaskEditView9_InfoBase extends EditView9Base {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof TestTaskEditView9_InfoBase
     */
    protected appDeName: string = 'testtask';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof TestTaskEditView9_InfoBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof TestTaskEditView9_InfoBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 实体服务对象
     *
     * @type {TestTaskService}
     * @memberof TestTaskEditView9_InfoBase
     */
    protected appEntityService: TestTaskService = new TestTaskService;

    /**
     * 实体权限服务对象
     *
     * @type TestTaskUIService
     * @memberof TestTaskEditView9_InfoBase
     */
    public appUIService: TestTaskUIService = new TestTaskUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof TestTaskEditView9_InfoBase
     */
    protected model: any = {
        srfCaption: 'entities.testtask.views.editview9_info.caption',
        srfTitle: 'entities.testtask.views.editview9_info.title',
        srfSubTitle: 'entities.testtask.views.editview9_info.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof TestTaskEditView9_InfoBase
     */
    protected containerModel: any = {
        view_form: { name: 'form', type: 'FORM' },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ViewBase
     */
	protected viewtag: string = '706ea523d286cec9ae0c2509f20daa8e';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TestTaskEditView9_InfoBase
     */
    public engine: EditView9Engine = new EditView9Engine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof TestTaskEditView9_InfoBase
     */    
    public counterServiceArray:Array<any> = [];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof TestTaskEditView9_InfoBase
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
     * @memberof TestTaskEditView9_InfoBase
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskEditView9_InfoBase
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskEditView9_InfoBase
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }



    /**
     * 视图加载完毕
     *
     * @protected
     * @memberof TestTaskEditView9_InfoBase
     */
    protected viewMounted(): void {
        if (this.panelState) {
            this.panelState.subscribe((res:any) => {
                if (Object.is(res.tag,'meditviewpanel')) {
                    if (Object.is(res.action,'save')) {
                        this.viewState.next({ tag:'form', action: 'save', data:res.data});
                    }
                    if (Object.is(res.action,'remove')) {
                        this.viewState.next({ tag:'form', action: 'remove', data:res.data});
                    }
                }
            });
        }
    }


}