import { MobMDControlInterface } from 'ibiz-core';

/**
 * 图表基类接口
 *
 * @interface MobChartControlInterface
 */
export interface MobChartControlInterface extends MobMDControlInterface {

    /**
     * 刷新
     *
     * @param {*} [args={}] 额外参数
     * @memberof MobChartControlInterface
     */
    refresh(args?: any): void;

    /**
     * 获取图表数据
     *
     * @param {*} [opt] 额外参数
     * @memberof MobChartControlInterface
     */
    load(opt?: any): void;

    /**
     * 绘制图表
     *
     * @returns {*}
     * @memberof MobChartControlInterface
     */
    drawCharts(): void;
}
