import { debounce, Util } from 'ibiz-core';
import { Prop, Watch, Emit } from 'vue-property-decorator';
import { ContextMenuBase } from '../../../widgets/contextmenu-base';
import { AppControlBase } from './app-controlbase';

/**
 * 上下文菜单部件基类
 *
 * @export
 * @class AppContextMenuBase
 * @extends {ContextMenuBase}
 */
export class AppContextMenuBase extends ContextMenuBase {

    /**
     * 部件动态参数
     *
     * @memberof AppTreeViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppTreeViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeViewBase
     */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
           super.onDynamicPropsChange(newVal,oldVal);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeViewBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppTreeViewBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppTreeViewBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 菜单项点击
     * 
     * @memberof AppContextMenuBase
     */
    public itemClick({ tag }: any) {
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "contextMenuItemClick", data: tag });
    }

    /**
     * 判断类型
     * 
     * @param {*} item
     * @memberof AppContextMenuBase
     */
    public renderToolbarItems(item: any) {
        if (item.itemType === "DEUIACTION") {
            return this.renderUIAction(item)
        }else if (item.itemType === "SEPERATOR") {
            return <divider />
        }else if (item.itemType === "ITEMS") {
            return this.renderItem(item)
        }else if (item.itemType === "RAWITEM") {
            return <span>{item.rawContent}</span>
        }
    }

    /**
     * 绘制菜单项
     * 
     * @param {*} item 
     * @memberof AppContextMenuBase
     */
    public renderItem(item: any) {
        return (
            <dropdown placement="right-start">
                <dropdown-item>
                    {this.renderItemIcon(item)}
                    {item.showCaption ? item.caption : ''}
                    <icon type="ios-arrow-forward"></icon>
	            </dropdown-item>
                <dropdown-menu slot="list">
                    { item.getPSDEToolbarItems && item.getPSDEToolbarItems.length > 0 ?  item.getPSDEToolbarItems.map((toolbarItem: any)=>{
                        return this.renderToolbarItems(toolbarItem) 
                    }): null }
	            </dropdown-menu>
            </dropdown> 
        )
    }

    /**
     * 绘制界面行为项
     * 
     * @param {*} item 
     * @memberof AppContextMenuBase
     */
    public renderUIAction(item: any) {
        let visible = this.contextMenuActionModel[item.name] ? this.contextMenuActionModel[item.name]?.visabled : true;
        let disabled = this.contextMenuActionModel[item.name] ? this.contextMenuActionModel[item.name]?.disabled : false;
        return (
            <dropdown-item name={item.name} v-show={visible} disabled={disabled}>
                {this.renderItemIcon(item)}
                {item.showCaption ? item.caption : ''}
            </dropdown-item>
        )
    }

    /**
     * 绘制图标
     * 
     * @param item 
     * @memberof AppContextMenuBase
     */
    public renderItemIcon(item: any) {
        if(item.showIcon && item.getPSSysImage){
            let img = item.getPSSysImage;
            if(img.cssClass){
                return (
                    <i class={img.cssClass}></i>
                )
            }
        }
    }

    /**
     * 绘制
     * 
     * @memberof AppContextMenuBase
     */
    public render() {
        if(!this.controlIsLoaded){
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const getPSDEToolbarItems = this.controlInstance.getPSDEToolbarItems() || [];
        return (
            <dropdown 
                class={{ 'tree-right-menu': true, ...controlClassNames }}
                trigger="custom" 
                visible={true} 
                on-on-click={($event: any) => debounce(this.itemClick,[{tag: $event}],this)}>
                <dropdown-menu slot="list">
                    {getPSDEToolbarItems?.map((item: any) => {
                        return this.renderToolbarItems(item)
                    })}
                </dropdown-menu>
            </dropdown>
        );
    }
}