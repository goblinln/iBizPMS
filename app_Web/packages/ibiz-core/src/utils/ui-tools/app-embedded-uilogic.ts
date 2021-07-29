/**
 * 应用内置逻辑
 */
 export class AppEmbeddedUILogic {
    /**
     * 计算表格行样式
     *
     * @memberof AppEmbeddedUILogic
     */
    public static calcRowStyle(opts: any, scriptCode: string) {
        let result: any;
        const { arg } = opts;
        const { row, rowIndex } = arg;
        eval(scriptCode);
        return result;
    }

    /**
     * 计算表格单元格样式
     *
     * @memberof AppEmbeddedUILogic
     */
    public static calcCellStyle(opts: any, scriptCode: string) {
        let result: any;
        const { arg } = opts;
        const { row, column, rowIndex, columnIndex } = arg;
        eval(scriptCode);
        return result;
    }

    /**
     * 计算表格头行样式
     *
     * @memberof AppEmbeddedUILogic
     */
    public static calcHeaderRowStyle(opts: any, scriptCode: string) {
        let result: any;
        const { arg } = opts;
        const { row, rowIndex } = arg;
        eval(scriptCode);
        return result;
    }

    /**
     * 计算表格头单元格样式
     *
     * @memberof AppEmbeddedUILogic
     */
    public static calcHeaderCellStyle(opts: any, scriptCode: string) {
        let result: any;
        const { arg } = opts;
        const { row, column, rowIndex, columnIndex } = arg;
        eval(scriptCode);
        return result;
    }
}
