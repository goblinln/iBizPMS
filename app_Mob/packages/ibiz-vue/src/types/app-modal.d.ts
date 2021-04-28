/**
 * 模态框实例
 *
 * @export
 * @interface AppModal
 */
export declare interface AppModal {
    /**
     * 打开模态视图
     *
     * @param {{ viewname: string, title: string, width?: number, height?: number }} view 视图
     * @param {*} [viewParam={}] 视图参数
     * @param {*} [data={}] 行为参数
     * @returns {Subject<any>}
     * @memberof AppModal
     */
    openModal(view: { viewname: string, title: string, width?: number, height?: number }, dynamicProps: any, staticProps: any): Promise<any>;
}