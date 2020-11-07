import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { TabExpViewBase } from '@/studio-core';
import DocLibService from '@/service/doc-lib/doc-lib-service';
import DocLibAuthService from '@/authservice/doc-lib/doc-lib-auth-service';
import TabExpViewEngine from '@engine/view/tab-exp-view-engine';
import DocLibUIService from '@/uiservice/doc-lib/doc-lib-ui-service';

/**
 * 文档库分页导航视图（文档）视图基类
 *
 * @export
 * @class DocLibLibTabExpViewBase
 * @extends {TabExpViewBase}
 */
export class DocLibLibTabExpViewBase extends TabExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof DocLibLibTabExpViewBase
     */
    protected appDeName: string = 'doclib';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof DocLibLibTabExpViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof DocLibLibTabExpViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 实体服务对象
     *
     * @type {DocLibService}
     * @memberof DocLibLibTabExpViewBase
     */
    protected appEntityService: DocLibService = new DocLibService;

    /**
     * 实体权限服务对象
     *
     * @type DocLibUIService
     * @memberof DocLibLibTabExpViewBase
     */
    public appUIService: DocLibUIService = new DocLibUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof DocLibLibTabExpViewBase
     */
    isShowDataInfoBar = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof DocLibLibTabExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.doclib.views.libtabexpview.caption',
        srfTitle: 'entities.doclib.views.libtabexpview.title',
        srfSubTitle: 'entities.doclib.views.libtabexpview.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof DocLibLibTabExpViewBase
     */
    protected containerModel: any = {
        view_tabexppanel: { name: 'tabexppanel', type: 'TABEXPPANEL' },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof DocLibLibTabExpViewBase
     */
	protected viewtag: string = '7f0821e7efe42bbf91090b525b05c96d';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof DocLibLibTabExpViewBase
     */ 
    protected viewName:string = "DocLibLibTabExpView";


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof DocLibLibTabExpViewBase
     */
    public engine: TabExpViewEngine = new TabExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof DocLibLibTabExpViewBase
     */    
    public counterServiceArray:Array<any> = [];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof DocLibLibTabExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            keyPSDEField: 'doclib',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }


}