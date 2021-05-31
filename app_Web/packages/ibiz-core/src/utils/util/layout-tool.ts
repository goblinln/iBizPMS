import { Util } from "./util";

/**
 * 布局工具类
 *
 * @export
 * @class LayoutTool
 */
export class LayoutTool {
    /**
     * 获取flex布局样式（容器）
     *
     * @static
     * @param {*} layout 布局设置
     * @returns {string}
     * @memberof LayoutTool
     */
    public static getFlexStyle(layout: any): string {
        const { dir, align, vAlign } = layout;
        let cssStyle: string = 'width: 100%; height: 100%; overflow: auto; display: flex;';
        cssStyle += dir ? `flex-direction: ${dir};` : '';
        cssStyle += align ? `justify-content: ${align};` : '';
        cssStyle += vAlign ? `align-items: ${vAlign};` : '';
        return cssStyle;
    }

    /**
     * 获取flex布局样式2(子)
     *
     * @static
     * @param {*} layoutPos 布局位置
     * @returns {string}
     * @memberof LayoutTool
     */
    public static getFlexStyle2(layoutPos: any): string {
        if(layoutPos?.layout == 'FLEX'){
            let grow = !layoutPos?.grow || layoutPos.grow < 0 ? 0 : layoutPos.grow;
            let width = Util.isNumber(layoutPos.width) && layoutPos.width > 0 ? layoutPos.width + 'px' : 'auto';
            let height = Util.isNumber(layoutPos.height) && layoutPos.height > 0 ? layoutPos.height + 'px' : 'auto';
            return `flex-grow: ${grow}; width: ${width}; height: ${height}`;
        }
        return ''
    }

    /**
     * 获取栅格布局参数
     *
     * @static
     * @param {*} layoutPos
     * @memberof LayoutTool
     */
    public static getGridOptions(layoutPos: any, isTurn24: boolean = true ){
        if(layoutPos){
            let colLG = this.formatColSpan(layoutPos.colLG, layoutPos.layout);
            let colMD = this.formatColSpan(layoutPos.colMD, layoutPos.layout);
            let colSM = this.formatColSpan(layoutPos.colSM, layoutPos.layout);
            let colXS = this.formatColSpan(layoutPos.colXS, layoutPos.layout);
            let colLGOffset = Util.isNumber(layoutPos.colLGOffset) ? layoutPos.colLGOffset : 0;
            let colMDOffset = Util.isNumber(layoutPos.colMDOffset) ? layoutPos.colMDOffset : 0; 
            let colSMOffset = Util.isNumber(layoutPos.colSMOffset) ? layoutPos.colSMOffset : 0;
            let colXSOffset = Util.isNumber(layoutPos.colXSOffset) ? layoutPos.colXSOffset : 0;
            let multiplier = 1;
            if(isTurn24){
                multiplier = layoutPos.layout == 'TABLE_24COL' ? 1 : 2;
            }
            return {
                xs: { span: colXS * multiplier, offset: colXSOffset * multiplier },
                sm: { span: colSM * multiplier, offset: colSMOffset * multiplier },
                md: { span: colMD * multiplier, offset: colMDOffset * multiplier },
                lg: { span: colLG * multiplier, offset: colLGOffset * multiplier },
            }
        }
    }

    /**
     * 格式化栅格的列宽,对超出范围值的作出修改或设置默认值
     *
     * @static
     * @param {*} span 栅格列宽
     * @param {string} layoutType 栅格类型(TABLE_24COL,TABLE_12COL)
     * @returns
     * @memberof LayoutTool
     */
    public static formatColSpan(span: any, layoutType: string){
        let colDefault = layoutType == 'TABLE_24COL' ? 24 : 12;
        // 空值传默认值
        if(!Util.isNumber(span)){
            return colDefault;
        }
        // 小于0传默认值，大于默认值传默认值，其他传原值
        if (span < 0 || span > colDefault) {
            return colDefault;
        } else {
            return span
        }
    }

}