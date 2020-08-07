import { Subject } from 'rxjs';
import { EditViewBase } from '@/studio-core';
import CaseService from '@/service/case/case-service';
import CaseAuthService from '@/authservice/case/case-auth-service';
import EditViewEngine from '@engine/view/edit-view-engine';
import CaseUIService from '@/uiservice/case/case-ui-service';

/**
 * 测试用例编辑视图（执行结果）视图基类
 *
 * @export
 * @class CaseExcEditViewBase
 * @extends {EditViewBase}
 */
export class CaseExcEditViewBase extends EditViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof CaseExcEditViewBase
     */
    protected appDeName: string = 'case';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof CaseExcEditViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof CaseExcEditViewBase
     */
    protected appDeMajor: string = 'title';

    /**
     * 实体服务对象
     *
     * @type {CaseService}
     * @memberof CaseExcEditViewBase
     */
    protected appEntityService: CaseService = new CaseService;

    /**
     * 实体权限服务对象
     *
     * @type CaseUIService
     * @memberof CaseExcEditViewBase
     */
    public appUIService: CaseUIService = new CaseUIService(this.$store);


    /**
     * 计数器服务对象集合
     *
     * @protected
     * @type {Array<*>}
     * @memberof CaseExcEditViewBase
     */    
    protected counterServiceArray: Array<any> = [];

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof CaseExcEditViewBase
     */
    protected model: any = {
        srfCaption: 'entities.case.views.exceditview.caption',
        srfTitle: 'entities.case.views.exceditview.title',
        srfSubTitle: 'entities.case.views.exceditview.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof CaseExcEditViewBase
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
	protected viewtag: string = '6ca0081833718834ca4bbd9ebbf1ccce';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof CaseExcEditViewBase
     */
    public engine: EditViewEngine = new EditViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof CaseExcEditViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            form: this.$refs.form,
            p2k: '0',
            keyPSDEField: 'case',
            majorPSDEField: 'title',
            isLoadDefault: true,
        });
    }

    /**
     * form 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof CaseExcEditViewBase
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof CaseExcEditViewBase
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof CaseExcEditViewBase
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }


}