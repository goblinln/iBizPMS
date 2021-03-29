import { mergeDeepLeft } from "ramda";
import { Util } from "../../../utils";

/**
 * 布局位置模型
 *
 * @export
 * @class IBizLayoutPosModel
 */
export class IBizLayoutPosModel {

    /**
     * 默认模型数据
     * 
     * @memberof IBizLayoutPosModel
     */
    protected defaultOption: any = {};

    /**
     * 布局位置模型数据
     *
     * @memberof IBizLayoutPosModel
     */
    protected layoutPosModelData: any;

    /**
     * 应用上下文
     *
     * @memberof IBizLayoutPosModel
     */
    protected context: any = {};

    /**
     * 布局参数存储对象
     *
     * @memberof IBizLayoutPosModel
     */
    protected layoutOptions: any = {
        colLG: null,
        colLGOffset: null,
        colMD: null,
        colMDOffset: null,
        colSM: null,
        colSMOffset: null,
        colXS: null,
        colXSOffset: null,
        grow: null,
        height: null,
        width: null,
    }

    /**
     * Creates an instance of IBizLayoutPosModel.
     * IBizLayoutPosModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof IBizLayoutPosModel
     */
    constructor(opts: any = {}, context: any) {
        this.layoutPosModelData = mergeDeepLeft(opts, this.defaultOption);
        this.context = context ? context : {};
        this.initLayoutOptions(opts);
    }

    /**
     * 计算并初始化布局参数
     *
     * @memberof IBizLayoutPosModel
     */
    public initLayoutOptions(opts: any) {
        if (opts.layout == 'FLEX') {
            this.layoutOptions.grow = !opts?.grow || opts.grow < 0 ? 0 : opts.grow;
            this.layoutOptions.width = Util.isNumber(opts.width) && opts.width > 0 ? opts.width + 'px' : 'auto';
            this.layoutOptions.height = Util.isNumber(opts.height) && opts.height > 0 ? opts.height + 'px' : 'auto';
        } else {
            this.layoutOptions.colLG = this.formatColSpan(opts.colLG);
            this.layoutOptions.colMD = this.formatColSpan(opts.colMD);
            this.layoutOptions.colSM = this.formatColSpan(opts.colSM);
            this.layoutOptions.colXS = this.formatColSpan(opts.colXS);
            this.layoutOptions.colLGOffset = Util.isNumber(opts.colLGOffset) ? opts.colLGOffset : 0;
            this.layoutOptions.colMDOffset = Util.isNumber(opts.colMDOffset) ? opts.colMDOffset : 0;
            this.layoutOptions.colSMOffset = Util.isNumber(opts.colSMOffset) ? opts.colSMOffset : 0;
            this.layoutOptions.colXSOffset = Util.isNumber(opts.colXSOffset) ? opts.colXSOffset : 0;
        }
    }

    /**
     * 格式化栅格的列宽,对超出范围值的作出修改或设置默认值
     *
     * @param {number} span
     * @memberof IBizLayoutPosModel
     */
    public formatColSpan(span: any) {
        let colDefault = this.layout == 'TABLE_24COL' ? 24 : 12;
        // 空值传默认值
        if (!Util.isNumber(span)) {
            return colDefault;
        }
        // 小于0传0，大于0传默认值，其他传原值
        if (span < 0) {
            return 0;
        } else if (span > colDefault) {
            return colDefault;
        } else {
            return span
        }
    }

    /**
     * 返回以24格计算的栅格布局参数
     *
     * @memberof IBizLayoutPosModel
     */
    public getGridOptionsIn24() {
        const multiplier = this.layout == 'TABLE_24COL' ? 1 : 2;
        return {
            xs: { span: this.colXS * multiplier, offset: this.colXSOffset * multiplier },
            sm: { span: this.colSM * multiplier, offset: this.colSMOffset * multiplier },
            md: { span: this.colMD * multiplier, offset: this.colMDOffset * multiplier },
            lg: { span: this.colLG * multiplier, offset: this.colLGOffset * multiplier },
        }
    }

    /**
     * 返回原始的栅格布局参数
     *
     * @memberof IBizLayoutPosModel
     */
    public getGridOptions() {
        return {
            xs: { span: this.colXS, offset: this.colXSOffset },
            sm: { span: this.colSM, offset: this.colSMOffset },
            md: { span: this.colMD, offset: this.colMDOffset },
            lg: { span: this.colLG, offset: this.colLGOffset },
        }
    }

    /**
     * 获取css样式（flex布局需要）
     *
     * @returns
     * @memberof IBizLayoutPosModel
     */
    public getCssStyle() {
        return `flex-grow: ${this.grow}; width: ${this.width}; height: ${this.height}`;
    }

    /**
     * 布局模式（TABLE_24COL，TABLE_12COL，FLEX）
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get layout() {
        return this.layoutPosModelData.layout;
    }

    /**
     * 大型列宽
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colLG() {
        return this.layoutOptions.colLG;
    }

    /**
     * 大型偏移
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colLGOffset() {
        return this.layoutOptions.colLGOffset;
    }

    /**
     * 中型列宽
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colMD() {
        return this.layoutOptions.colMD;
    }

    /**
     * 中型偏移
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colMDOffset() {
        return this.layoutOptions.colMDOffset;
    }

    /**
     * 小型列宽
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colSM() {
        return this.layoutOptions.colSM;
    }

    /**
     * 小型偏移
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colSMOffset() {
        return this.layoutOptions.colSMOffset;
    }

    /**
     * 超小列宽
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colXS() {
        return this.layoutOptions.colXS;
    }

    /**
     * 超小偏移
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colXSOffset() {
        return this.layoutOptions.colXSOffset;
    }

    /**
     * 剩余空间拉伸的放大比例（flex-grow）
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get grow() {
        return this.layoutOptions.grow || 0;
    }

    /**
     * 固定列宽
     * 
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get colWidth() {
        return this.layoutPosModelData.colWidth;
    }

    /**
     * 布局宽度（自身配置的宽度）
     *
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get width() {
        return this.layoutOptions.width;
    }

    /**
     * 布局高度（自身配置的高度）
     *
     * @readonly
     * @memberof IBizLayoutPosModel
     */
    get height() {
        return this.layoutOptions.height;
    }

}