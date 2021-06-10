import { MainControlInterface } from 'ibiz-core';

/**
 * 选择视图面板基类接口
 *
 * @interface PickUpViewPanelControlInterface
 */
export interface PickUpViewPanelControlInterface extends MainControlInterface{

    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof PickUpViewPanelControlInterface
     */
    onViewDatasChange($event: any): void;

    /**
     * 视图加载完成
     *
     * @return {*} 
     * @memberof PickUpViewPanelControlInterface
     */
    handleLoad(): void;
}