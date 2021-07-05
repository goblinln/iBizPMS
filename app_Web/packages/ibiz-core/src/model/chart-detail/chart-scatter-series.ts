import { ChartSeries } from './chart-series';

/**
 * 散点图序列模型
 *
 * @export
 * @class ChartScatterSeries
 */
export class ChartScatterSeries extends ChartSeries{

    /**
     * 分类属性
     *
     * @type {string}
     * @memberof ChartScatterSeries
     */
    public categorField: string = '';

    /**
     * 值属性
     *
     * @type {string}
     * @memberof ChartScatterSeries
     */
    public valueField: string = '';

    /**
     * 分类代码表
     *
     * @type {string}
     * @memberof ChartScatterSeries
     */
    public categorCodeList: any = null;

    /**
     * 维度定义
     *
     * @type {string}
     * @memberof ChartScatterSeries
     */
    public dimensions:Array<string> = [];

    /**
     * 维度编码
     *
     * @type {*}
     * @memberof ChartScatterSeries
     */
    public encode:any = null;

    /**
     * Creates an instance of ChartScatterSeries.
     * ChartScatterSeries 实例
     * 
     * @param {*} [opts={}]
     * @memberof ChartScatterSeries
     */
    constructor(opts: any = {}) {
        super(opts);
        this.categorField = !Object.is(opts.categorField, '') ? opts.categorField : '';
        this.categorCodeList = opts.categorCodeList ? opts.categorCodeList : null;
        this.valueField = !Object.is(opts.valueField, '') ? opts.valueField : '';
        this.dimensions = opts.dimensions ? opts.dimensions :'';
        this.encode = opts.encode ? opts.encode : null;
    }

    /**
     * 设置分类属性
     *
     * @param {string} state
     * @memberof ChartScatterSeries
     */
    public setCategorField(state: string): void {
        this.categorField = state;
    }

    /**
     * 设置序列名称
     *
     * @param {string} state
     * @memberof ChartScatterSeries
     */
    public setValueField(state: string): void {
        this.valueField = state;
    }

    /**
     * 分类代码表
     *
     * @param {*} state
     * @memberof ChartScatterSeries
     */
    public setCategorCodeList(state: any): void {
        this.categorCodeList = state;
    }

    /**
     * 维度定义
     *
     * @param {*} state
     * @memberof ChartScatterSeries
     */
    public setDimensions(state: any): void {
        this.dimensions = state;
    }

    /**
     * 设置编码
     *
     * @param {*} state
     * @memberof ChartScatterSeries
     */
    public setEncode(state: any): void {
        this.encode = state;
    }

}