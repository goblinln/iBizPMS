import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { EditView9Base } from '@/studio-core';
import CaseService from '@/service/case/case-service';
import CaseAuthService from '@/authservice/case/case-auth-service';
import EditView9Engine from '@engine/view/edit-view9-engine';
import CaseUIService from '@/uiservice/case/case-ui-service';

/**
 * 测试用例编辑视图视图基类
 *
 * @export
 * @class CaseMainInfoEditView9Base
 * @extends {EditView9Base}
 */
export class CaseMainInfoEditView9Base extends EditView9Base {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof CaseMainInfoEditView9Base
     */
    protected appDeName: string = 'case';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof CaseMainInfoEditView9Base
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof CaseMainInfoEditView9Base
     */
    protected appDeMajor: string = 'title';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof CaseMainInfoEditView9Base
     */ 
    protected dataControl: string = 'form';

    /**
     * 实体服务对象
     *
     * @type {CaseService}
     * @memberof CaseMainInfoEditView9Base
     */
    protected appEntityService: CaseService = new CaseService;

    /**
     * 实体权限服务对象
     *
     * @type CaseUIService
     * @memberof CaseMainInfoEditView9Base
     */
    public appUIService: CaseUIService = new CaseUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof CaseMainInfoEditView9Base
     */
    protected model: any = {
        srfCaption: 'entities.case.views.maininfoeditview9.caption',
        srfTitle: 'entities.case.views.maininfoeditview9.title',
        srfSubTitle: 'entities.case.views.maininfoeditview9.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof CaseMainInfoEditView9Base
     */
    protected containerModel: any = {
        view_form: {
            name: 'form',
            type: 'FORM',
        },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof CaseMainInfoEditView9Base
     */
	protected viewtag: string = 'ce53f8a085a9a861e165f77f38528a86';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof CaseMainInfoEditView9Base
     */ 
    protected viewName: string = 'CaseMainInfoEditView9';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof CaseMainInfoEditView9Base
     */
    public engine: EditView9Engine = new EditView9Engine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof CaseMainInfoEditView9Base
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof CaseMainInfoEditView9Base
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
     * @memberof CaseMainInfoEditView9Base
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof CaseMainInfoEditView9Base
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof CaseMainInfoEditView9Base
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }



    /**
     * 视图加载完毕
     *
     * @protected
     * @memberof CaseMainInfoEditView9Base
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