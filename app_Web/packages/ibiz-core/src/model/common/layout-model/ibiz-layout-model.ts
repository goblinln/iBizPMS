import { mergeDeepLeft } from "ramda";
/**
 * 容器布局设置模型
 *
 * @export
 * @class IBizLayoutModel
 */
export class IBizLayoutModel {
    /**
     * 布局设置模型数据
     *
     * @memberof IBizLayoutModel
     */
    protected layoutModelData: any;

    /**
     * 默认模型数据
     * 
     * @memberof IBizLayoutModel
     */
    protected defaultOption: any = {};

    /**
     * 应用上下文
     *
     * @memberof IBizLayoutModel
     */
    protected context: any = {};

    /**
     * Creates an instance of IBizLayoutModel.
     * IBizLayoutModel 实例
     *
     * @param {*} [opts={}]
     * @memberof IBizLayoutModel
     */
    constructor(opts: any = {}, context: any) {
        this.layoutModelData =  mergeDeepLeft(opts, this.defaultOption);
        this.context = context ? context : {};
    }

    /**
     * 获取css样式（Flex布局需要）
     *
     * @memberof IBizLayoutModel
     */
    public getCssStyle() {
        let cssStyle: string = 'width: 100%; height: 100%; overflow: auto; display: flex;';
        cssStyle += this.dir ? `flex-direction: ${this.dir};` : '';
        cssStyle += this.align ? `justify-content: ${this.align};` : '';
        cssStyle += this.vAlign ? `align-items: ${this.vAlign};` : '';
        return cssStyle;
    }

    /**
     * 布局容器名称
     *
     * @readonly
     * @memberof IBizLayoutModel
     */
    get name(): string {
        return this.layoutModelData.name;
    }

    /**
     * 布局模式（TABLE_24COL，TABLE_12COL，FLEX）
     *
     * @readonly
     * @memberof IBizLayoutModel
     */
    get layout(): string {
        return this.layoutModelData.layout;
    }

    /**
     * 列数量（24,12）
     *
     * @readonly
     * @memberof IBizLayoutModel
     */
    get columnCount(): number {
        return this.layoutModelData.columnCount;
    }

    /**
     * 启用12列转24列布局
     *
     * @readonly
     * @memberof IBizLayoutModel
     */
    get enableCol12ToCol24(): boolean {
        return this.layoutModelData.enableCol12ToCol24 || false;
    }

    /**
     * 主轴对齐方式（justify-content）
     *
     * @readonly
     * @memberof IBizLayoutModel
     */
    get align(): string {
        return this.layoutModelData.align;
    }

    /**
     * 交叉轴上的对齐方式（align-items）
     *
     * @readonly
     * @memberof IBizLayoutModel
     */
    get vAlign(): string {
        return this.layoutModelData.vAlign;
    }

    /**
     * 主轴方向（flex-direction）
     *
     * @readonly
     * @memberof IBizLayoutModel
     */
    get dir(): string {
        return this.layoutModelData.dir;
    }
}
