import { TreeExpViewEngine, IBizTreeExpViewModel, IBizTreeExpBarModel } from 'ibiz-core';
import { ExpViewBase } from './ExpViewBase';

/**
 * 树导航视图基类
 *
 * @export
 * @class TabexpviewBase
 * @extends {ExpViewBase}
 */
export class TreeExpViewBase extends ExpViewBase {
    /**
     * 视图实例
     *
     * @memberof TreeExpViewBase
     */
    public viewInstance!: IBizTreeExpViewModel;

    /**
     * 导航栏实例
     *
     * @memberof TreeExpViewBase
     */
    public expBarInstance!: IBizTreeExpBarModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {ChartViewEngine}
     * @memberof ChartViewBase
     */
    public engine: TreeExpViewEngine = new TreeExpViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ListExpviewBase
     */
    public engineInit(): void {
        this.engine.init({
          view: this,
          parentContainer: this.$parent,
          p2k: '0',
          treeexpbar:(this.$refs[this.expBarInstance.name] as any).ctrl,
          keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
          majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
          isLoadDefault: this.viewInstance.loadDefault,
        });
    } 

    /**
     * 初始化列表视图实例
     *
     * @memberof TreeExpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizTreeExpViewModel(this.staticProps.modeldata,this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.expBarInstance = this.viewInstance.getControl('treeexpbar');        
    }

}