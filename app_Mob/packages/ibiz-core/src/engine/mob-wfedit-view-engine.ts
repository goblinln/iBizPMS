import { MobEditViewEngine } from './mob-edit-view-engine';

/**
 * 实体移动端工作流操作视图界面引擎
 *
 * @export
 * @class MobWFEditViewEngine
 * @extends {MobEditViewEngine}
 */
export class MobWFEditViewEngine extends MobEditViewEngine {

    /**
     * 表单部件
     *
     * @protected
     * @type {*}
     * @memberof MobWFEditViewEngine
     */
    protected form: any;

    /**
     * 工具栏
     *
     * @protected
     * @type {*}
     * @memberof MobWFEditViewEngine
     */
    protected righttoolbar: any;

    /**
     * 快速工具栏
     *
     * @protected
     * @type {*}
     * @memberof MobWFEditViewEngine
     */
    protected quicktoolbar: any;

    /**
     * 初始化编辑视图引擎
     *
     * @param {*} [options={}]
     * @memberof MobWFEditViewEngine
     */
    public init(options: any = {}): void {
        super.init(options);
        this.righttoolbar = options.righttoolbar;
        this.quicktoolbar = options.quicktoolbar;
    }

}