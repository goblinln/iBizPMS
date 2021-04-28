import { ViewEngine } from './view-engine';

/**
 * 实体移动端分页导航视图界面引擎
 *
 * @export
 * @class MobTabExpViewEngine
 * @extends {ViewEngine}
 */
export class MobTabExpViewEngine extends ViewEngine {

    /**
     * Creates an instance of MobTabExpViewEngine.
     * 
     * 
     * @memberof MobTabExpViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof MobTabExpViewEngine
     */
    public init(options: any): void {
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof MobTabExpViewEngine
     */
    public load(): void {
        super.load();
        Object.values(this.view.containerModel).forEach((_item: any) => {
            if (!Object.is(_item.type, 'TABEXPPANEL')) {
                return;
            }
            this.setViewState2({ tag: _item.name, action: 'load', viewdata: { ...this.view.viewparams } });
        });
    }
}