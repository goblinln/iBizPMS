import { Prop, Watch, Emit, Component } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobContextMenuControlBase } from '../../../widgets';
import { IPSDEToolbarItem } from '@ibiz/dynamic-model-api';


/**
 * 上下文菜单部件基类
 *
 * @export
 * @class AppMobContextMenuBase
 * @extends {MobContextMenuBase}
 */
@Component({})
export class AppMobContextMenuBase extends MobContextMenuControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobContextMenuBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobContextMenuBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobContextMenuBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobContextMenuBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppMobContextMenuBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobContextMenuBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制图标
     * 
     * @param item 
     * @memberof AppContextMenuBase
     */
    public renderItemIcon(item: IPSDEToolbarItem) {
        if (item?.showIcon && item.getPSSysImage()) {
            let img = item.getPSSysImage();
            if (img?.cssClass) {
                return (
                    <app-mob-icon name={img?.cssClass}></app-mob-icon>
                )
            }
        }
    }

    /**
     * 绘制界面行为项
     * 
     * @param {*} item 
     * @memberof AppContextMenuBase
     */
    public renderUIAction(item: IPSDEToolbarItem) {
        if(!item){
            return
        }
        let visible = this.contextMenuActionModel[item.name] ? this.contextMenuActionModel[item.name]?.visabled : true;
        let disabled = this.contextMenuActionModel[item.name] ? this.contextMenuActionModel[item.name]?.disabled : false;
        return (
            <div class="context-menu-item" name={item.name} v-show={visible} on-click={() => this.itemClick({ tag: item.name })}>
                {this.renderItemIcon(item)}
                {item.showCaption ? item.caption : ''}
            </div>
        )
    }

    /**
     * 判断类型
     * 
     * @param {*} item
     * @memberof AppContextMenuBase
     */
    public renderToolbarItems(item: IPSDEToolbarItem) {
        if(!item){
            return
        }
        if (item.itemType === "DEUIACTION") {
            return this.renderUIAction(item)
        } else if (item.itemType === "SEPERATOR") {
            // todo 分割线
            return
        } else if (item.itemType === "ITEMS") {
            // todo 分组
            return
        } else if (item.itemType === "RAWITEM") {
            // return <span>{item.rawContent}</span>
        }
    }

    /**
     * 绘制上下文菜单
     *
     * @returns {*}
     * @memberof AppMobContextMenuBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return;
        }
        let contextClassName = {
            'app-mob-contextmenu': true,
            ...this.renderOptions.controlClassNames,
        };
        const getPSDEToolbarItems = this.controlInstance.getPSDEToolbarItems() || [];
        if (getPSDEToolbarItems?.length > 0) {
            return <div class={contextClassName}>
                {getPSDEToolbarItems?.map((item: IPSDEToolbarItem) => {
                    return this.renderToolbarItems(item)
                })}
            </div>
        }
    }
}