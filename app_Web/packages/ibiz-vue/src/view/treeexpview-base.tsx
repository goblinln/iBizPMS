import { IPSAppDETreeExplorerView, IPSTreeExpBar } from '@ibiz/dynamic-model-api';
import { TreeExpViewEngine, ModelTool, TreeExpViewInterface } from 'ibiz-core';
import { ExpViewBase } from './expview-base';

/**
 * 树导航视图基类
 *
 * @export
 * @class TreeExpViewBase
 * @extends {ExpViewBase}
 * @implements {TreeExpViewInterface}
 */
export class TreeExpViewBase extends ExpViewBase implements TreeExpViewInterface {
    /**
     * 视图实例
     *
     * @memberof TreeExpViewBase
     */
    public viewInstance!: IPSAppDETreeExplorerView;

    /**
     * 导航栏实例
     *
     * @memberof TreeExpViewBase
     */
    public expBarInstance!: IPSTreeExpBar;

    /**
     * 视图引擎
     *
     * @public
     * @type {ChartViewEngine}
     * @memberof TreeExpViewBase
     */
    public engine: TreeExpViewEngine = new TreeExpViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof TreeExpViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
          view: this,
          parentContainer: this.$parent,
          p2k: '0',
          treeexpbar:(this.$refs[this.expBarInstance?.name] as any).ctrl,
          keyPSDEField: this.appDeCodeName.toLowerCase(),
          majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
          isLoadDefault: this.viewInstance?.loadDefault,
        });
    } 

    /**
     * 初始化列表视图实例
     *
     * @memberof TreeExpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDETreeExplorerView;
        await super.viewModelInit();
        this.expBarInstance = ModelTool.findPSControlByType('TREEEXPBAR', this.viewInstance.getPSControls() || []);
    }

}