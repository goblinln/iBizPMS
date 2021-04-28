import { Vue, Component, Prop, Watch, Emit } from 'vue-property-decorator';
import { ToolbarItem } from 'ibiz-core/src/interface/ctrl/toolbarItem';
import { Util } from 'ibiz-core';
import "./view-toolbar.less";

/**
 * 视图工具栏
 *
 * @export
 * @class ViewToolbar
 * @extends {Vue}
 */
@Component({})
export class ViewToolbar extends Vue {

    /**
     * 工具栏模型
     *
     * @type {{ [key: string]: ToolbarItem }}
     * @memberof ViewToolbar
     */
    @Prop()
    public toolbarModels!: { [key: string]: ToolbarItem };

    /**
     * 计树器服务集合
     *
     * @type {any}
     * @memberof ViewToolbar
     */
    @Prop()
    public counterServiceArray?: any;

    /**
     * 监控工具栏模型变更
     *
     * @memberof ViewToolbar
     */
    @Watch('toolbarModels', { immediate: true })
    public watchModels(): void {
        if (this.toolbarModels) {
            this.items = [];
            this.format();
        }
    }
    /**
     *工具栏弹出或关闭状态
    *
    * @memberof ViewToolbar
    */
    public showGroup = false;

    /**
     * 所有工机具项
     *
     * @protected
     * @type {ToolbarItem[]}
     * @memberof ViewToolbar
     */
    protected items: ToolbarItem[] = [];

    /**
     * 格式化工具栏模型
     *
     * @protected
     * @param {*} [model=this.model]
     * @memberof ViewToolbar
     */
    protected format(model: any = this.toolbarModels, items: ToolbarItem[] = this.items): void {
        for (const key in model) {
            const item: any = model[key];
            items.push(item);
            if (item.model) {
                item.items = [];
                this.format(item.model, item.items);
            }
        }
    }

    /**
     * 工具栏项点击
     *
     * @param {*} uiAction
     * @param {MouseEvent} e
     * @memberof ViewToolbar
     */
    @Emit('item-click')
    public itemClick(uiAction: any, e: MouseEvent): void { }


    /**
     * 绘制菜单
     *
     * @protected
     * @param {any[]} [items]
     * @returns {*}
     * @memberof ViewToolbar
     */
    protected renderMenuItems(items?: any[]): any {
        if (!items) {
            return;
        }
        return items.map((item: any) => {
            if (item.itemType === 'SEPERATOR') {
                return <li class='ivu-dropdown-item seperator'></li>;
            }
            return <dropdown-item>{this.renderMenuItem(item)}</dropdown-item>;
        });
    }

    /**
     * 绘制菜单项
     *
     * @protected
     * @param {*} item
     * @returns {*}
     * @memberof ViewToolbar
     */
    protected renderMenuItem(item: any): any {
        return <div class='sub-item'  >
            <app-mob-button
                size="large"
                disabled={item.disabled}
                class={item.class}
                iconName={item?.getPSSysImage?.cssClass}
                on-click={(e: any) => [this.itemClick({ tag: item.name, item: item }, e), this.showGroup = false]} />
            {item.showCaption ? <span class='btn-out-text'>{item.caption}</span> : ''}
        </div>
    }

    /**
     * 绘制底部模式工具栏
     *
     * @protected
     * @returns {*}
     * @memberof ViewToolbar
     */
    public getItem(items: any) {
        const targetItems: any = []
        items?.forEach((item: any) => {
            if (item.itemType === 'DEUIACTION') {
                targetItems.push(item);
            }
            if (item.itemType === 'ITEMS') {
                targetItems.push(...this.getItem(item.items));
            }
        });
        return targetItems;
    }

    /**
     * 绘制底部模式工具栏
     *
     * @protected
     * @returns {*}
     * @memberof ViewToolbar
     */
    get getToolBarLimit() {
        return this.items.some((item: any) => { return item.visabled })
    }

    /**
     * 绘制底部模式工具栏
     *
     * @protected
     * @returns {*}
     * @memberof ViewToolbar
     */
    protected renderBottomDefault(): any {
        const items = this.getItem(this.items);
        return this.getToolBarLimit ? items.length > 1 ?
            <div>
                <app-mob-button
                    iconName="chevron-up-circle-outline"
                    class="app-view-toolbar-button"
                    on-click={() => { this.showGroup = true}} />

                <van-popup class="popup" value={this.showGroup} round position="bottom" get-container='body' on-click-overlay={() => { this.showGroup = false; }}>
                    <div class="container">
                        {
                            items.map((item: any) => {
                                return item.visabled ? this.renderMenuItem(item) : null;
                            })
                        }
                    </div>
                </van-popup>
            </div>
            : this.items.map((item: any) => {
                return item.visabled ? this.renderMenuItem(item) : null;
            }) : null
    }

    /**
     * 绘制工具栏内容
     *
     * @returns {*}
     * @memberof ViewToolbar
     */
    public render(): any {
        if (this.items.length == 0) {
            return;
        }
        let content: any = this.renderBottomDefault();
        return <div class={{ 'toolbar-container': true, 'fab_container': true }}>{content}</div>;
    }
}
