
/**
 * 界面处理逻辑返回参数对象
 *
 * @export
 * @class UIActionResult
 */
export class UIActionResult {

    /**
     * 是否成功
     *
     * @type {*}
     * @memberof UIActionResult
     */
    public ok: boolean;

    /**
     * 返回数据对象
     *
     * @type {*}
     * @memberof UIActionResult
     */
    public result: any;

    /**
     * 构造函数
     * 
     * @param {*} opts 传入数据
     * @memberof UIActionResult
     */
    constructor(opts: any) {
        this.ok = opts?.ok ? opts.ok : false;
        this.result = opts?.result ? opts?.result : null;
    }

}