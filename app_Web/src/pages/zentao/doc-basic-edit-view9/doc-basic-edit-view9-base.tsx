import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { EditView9Base } from '@/studio-core';
import DocService from '@/service/doc/doc-service';
import DocAuthService from '@/authservice/doc/doc-auth-service';
import EditView9Engine from '@engine/view/edit-view9-engine';
import DocUIService from '@/uiservice/doc/doc-ui-service';

/**
 * 文档编辑视图（基本信息）视图基类
 *
 * @export
 * @class DocBasicEditView9Base
 * @extends {EditView9Base}
 */
export class DocBasicEditView9Base extends EditView9Base {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof DocBasicEditView9Base
     */
    protected appDeName: string = 'doc';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof DocBasicEditView9Base
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof DocBasicEditView9Base
     */
    protected appDeMajor: string = 'title';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof DocBasicEditView9Base
     */ 
    protected dataControl: string = 'form';

    /**
     * 实体服务对象
     *
     * @type {DocService}
     * @memberof DocBasicEditView9Base
     */
    protected appEntityService: DocService = new DocService;

    /**
     * 实体权限服务对象
     *
     * @type DocUIService
     * @memberof DocBasicEditView9Base
     */
    public appUIService: DocUIService = new DocUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof DocBasicEditView9Base
     */
    protected model: any = {
        srfCaption: 'entities.doc.views.basiceditview9.caption',
        srfTitle: 'entities.doc.views.basiceditview9.title',
        srfSubTitle: 'entities.doc.views.basiceditview9.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof DocBasicEditView9Base
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
     * @memberof DocBasicEditView9Base
     */
	protected viewtag: string = '00d6c7c0b8629ec81c0016d542d0f097';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof DocBasicEditView9Base
     */ 
    protected viewName: string = 'DocBasicEditView9';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof DocBasicEditView9Base
     */
    public engine: EditView9Engine = new EditView9Engine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof DocBasicEditView9Base
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof DocBasicEditView9Base
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            form: this.$refs.form,
            p2k: '0',
            keyPSDEField: 'doc',
            majorPSDEField: 'title',
            isLoadDefault: true,
        });
    }

    /**
     * form 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof DocBasicEditView9Base
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof DocBasicEditView9Base
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof DocBasicEditView9Base
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }



    /**
     * 视图加载完毕
     *
     * @protected
     * @memberof DocBasicEditView9Base
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