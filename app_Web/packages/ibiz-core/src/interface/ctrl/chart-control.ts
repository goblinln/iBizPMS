import { MDControlInterface } from 'ibiz-core';

/**
 * 图表基类接口
 *
 * @interface ChartControlInterface
 */
export interface ChartControlInterface extends MDControlInterface {

    /**
     * 刷新
     *
     * @param {*} [args={}] 额外参数
     * @memberof ChartControlInterface
     */
    refresh(args?: any): void;

    /**
     * 获取图表数据
     *
     * @param {*} [opt] 额外参数
     * @memberof ChartControlInterface
     */
    load(opt?: any): void;

    /**
     * 绘制图表
     *
     * @returns {*}
     * @memberof ChartControlInterface
     */
    drawCharts(): void;
}
