
/**
 * 树导航基类接口
 *
 * @interface TreeExpBarControlInterface
 */
export interface DrtabControlInterface {


    /**
     *  选中节点
     *
     * @param {string} tabPaneName 分页name
     * @memberof DrtabControlInterface
     */
    tabPanelClick(tabPaneName: string): void;
}