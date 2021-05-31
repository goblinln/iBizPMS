import { ViewEngine } from './view-engine';

/**
 * 实体分页搜索视图
 *
 * @export
 * @class TabSearchViewEngine
 * @extends {ViewEngine}
 */
export class TabSearchViewEngine extends ViewEngine {


    /**
     * Creates an instance of TabSearchViewEngine.
     * 
     * @memberof TabSearchViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof TabSearchViewEngine
     */
    public init(options: any): void {
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof TabSearchViewEngine
     */
    public load(): void {
        super.load();
        Object.values(this.view.containerModel).forEach((_item: any) => {
            if (!Object.is(_item.type, 'TABEXPPANEL')) {
                return;
            }
            if(this.view.context && this.view.context[(this.keyPSDEField as string)]){
                return;
            }
            this.setViewState2({ tag: _item.name, action: 'load', viewdata: this.view.context });
        });
    }

    /**
     * 计算按钮状态
     *
     * @memberof TabSearchViewEngine
     */
    public computeToolbarState(state:boolean,data:any){
        this.calcToolbarItemState(state);
        this.calcToolbarItemAuthState(data);
    }
}