import { Subject } from 'rxjs';
import { EditView9Base } from '@/studio-core';
import TestResultService from '@/service/test-result/test-result-service';
import TestResultAuthService from '@/authservice/test-result/test-result-auth-service';
import TestResultUIService from '@/uiservice/test-result/test-result-ui-service';

/**
 * 测试结果多表单编辑视图视图基类
 *
 * @export
 * @class TestResultTestTaskMEditView9Base
 * @extends {EditView9Base}
 */
export class TestResultTestTaskMEditView9Base extends EditView9Base {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof TestResultTestTaskMEditView9Base
     */
    protected appDeName: string = 'testresult';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof TestResultTestTaskMEditView9Base
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof TestResultTestTaskMEditView9Base
     */
    protected appDeMajor: string = 'casename';

    /**
     * 实体服务对象
     *
     * @type {TestResultService}
     * @memberof TestResultTestTaskMEditView9Base
     */
    protected appEntityService: TestResultService = new TestResultService;

    /**
     * 实体权限服务对象
     *
     * @type TestResultUIService
     * @memberof TestResultTestTaskMEditView9Base
     */
    public appUIService: TestResultUIService = new TestResultUIService(this.$store);


    /**
     * 计数器服务对象集合
     *
     * @protected
     * @type {Array<*>}
     * @memberof TestResultTestTaskMEditView9Base
     */    
    protected counterServiceArray: Array<any> = [];

	/**
	 * 自定义视图导航上下文集合
	 *
     * @protected
	 * @type {*}
	 * @memberof TestResultTestTaskMEditView9Base
	 */
    protected customViewNavContexts: any = {
        'TASK': { isRawValue: false, value: 'task' }
    };

	/**
	 * 自定义视图导航参数集合
	 *
     * @protected
	 * @type {*}
	 * @memberof TestResultTestTaskMEditView9Base
	 */
    protected customViewParams: any = {
        'task': { isRawValue: false, value: 'task' }
    };

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof TestResultTestTaskMEditView9Base
     */
    protected model: any = {
        srfCaption: 'entities.testresult.views.testtaskmeditview9.caption',
        srfTitle: 'entities.testresult.views.testtaskmeditview9.title',
        srfSubTitle: 'entities.testresult.views.testtaskmeditview9.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof TestResultTestTaskMEditView9Base
     */
    protected containerModel: any = {
        view_meditviewpanel: { name: 'meditviewpanel', type: 'MULTIEDITVIEWPANEL' },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ViewBase
     */
	protected viewtag: string = '27374a7bc38f2581e433db58d85e14e4';


    /**
     * 引擎初始化
     *
     * @public
     * @memberof TestResultTestTaskMEditView9Base
     */
    public engineInit(): void {
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof TestResultTestTaskMEditView9
     */
    public newdata(args: any[],fullargs?:any[], params?: any, $event?: any, xData?: any) {
        let localContext:any = null;
        let localViewParam:any =null;
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }


    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof TestResultTestTaskMEditView9
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }




    /**
     * 视图上下文变化
     *
     * @protected
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof TestResultTestTaskMEditView9Base
     */
    protected viewDataChange(newVal: any, oldVal: any): void {
        this.viewState.next({ tag: 'meditviewpanel', action: 'load', data: this.context });
    }


}