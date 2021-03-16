import { ViewContainerBase } from "./ViewContainerBase";
import { Prop } from 'vue-property-decorator';
/**
 * 表格选择视图容器基类
 *
 * @export
 * @class PickupGridViewContainerBase
 * @extends {ViewContainerBase}
 */
export class PickupGridViewContainerBase extends ViewContainerBase {

    /**
     * 是否单选
     * 
     * @type {boolean}
     * @memberof PickupGridViewContainerBase
     */
    @Prop() isSingleSelect!: boolean;

    /**
     * 选中数据字符串
     * 
     * @type {string}
     * @memberof PickupGridViewContainerBase
     */
    @Prop() selectedData?: string;

    /**
     * 是否显示按钮
     * 
     * @type {boolean}
     * @memberof PickupGridViewContainerBase
     */
    @Prop() isShowButton?: boolean;

    /**
     * 初始化动态视图上下文环境参数
     *
     * @type {Array<*>}
     * @memberof PickupGridViewContainerBase
     */
    public initViewContext(opts: any) {
        if(opts) {
            Object.assign(opts, {
                isSingleSelect: this.isSingleSelect,
                selectedData: this.selectedData,
                isShowButton: this.selectedData
            });
        }
        super.initViewContext(opts);
    }
    
}