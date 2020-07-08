import { Subject } from 'rxjs';
import { TabExpViewBase } from '@/studio-core';
import ProductService from '@/service/product/product-service';
import TabExpViewEngine from '@engine/view/tab-exp-view-engine';

/**
 * 测试视图基类
 *
 * @export
 * @class ProductTestTabExpViewBase
 * @extends {TabExpViewBase}
 */
export class ProductTestTabExpViewBase extends TabExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ProductTestTabExpViewBase
     */
    protected appDeName: string = 'product';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof ProductTestTabExpViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof ProductTestTabExpViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 实体服务对象
     *
     * @type {ProductService}
     * @memberof ProductTestTabExpViewBase
     */
    protected appEntityService: ProductService = new ProductService;


    /**
     * 计数器服务对象集合
     *
     * @protected
     * @type {Array<*>}
     * @memberof ProductTestTabExpViewBase
     */    
    protected counterServiceArray: Array<any> = [];

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof ProductTestTabExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.product.views.testtabexpview.caption',
        srfTitle: 'entities.product.views.testtabexpview.title',
        srfSubTitle: 'entities.product.views.testtabexpview.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof ProductTestTabExpViewBase
     */
    protected containerModel: any = {
        view_tabexppanel: { name: 'tabexppanel', type: 'TABEXPPANEL' },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ViewBase
     */
	protected viewtag: string = '005b0ba30f9e9e982e087961bd50c681';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof ProductTestTabExpViewBase
     */
    public engine: TabExpViewEngine = new TabExpViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ProductTestTabExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            keyPSDEField: 'product',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }


}