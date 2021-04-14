/**
 * 模型数据解析器
 *
 * @export
 * @class ModelParser
 */
export class ModelParser {
    /**
     * 获取导航上下文
     *
     * @static
     * @param {*} currentItem 当前模型对象
     * @memberof ParserTool
     */
    public static getNavigateContext(currentItem: any) {
        const result: any = {};
        if (currentItem?.getPSNavigateContexts?.length > 0) {
            currentItem.getPSNavigateContexts.forEach((navContext: any) => {
                result[navContext?.key] = navContext.rawValue ? navContext.value : `%${navContext.value}%`;
            });
        }
        return result;
    }

    /**
     * 获取导航参数
     *
     * @static
     * @param {*} currentItem 当前模型对象
     * @memberof ParserTool
     */
    public static getNavigateParams(currentItem: any) {
        const result: any = {};
        if (currentItem?.getPSNavigateParams?.length > 0) {
            currentItem.getPSNavigateParams.forEach((navParam: any) => {
                result[navParam?.key] = navParam.rawValue ? navParam.value : `%${navParam.value}%`;
            });
        }
        return result;
    }
}