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
    public toolbarModels?: { [key: string]: ToolbarItem };

    /**
     * 绘制模式
     *
     * @type {string}
     * @memberof ViewToolbar
     */
    @Prop({ default: 'DEFAULT' })
    public mode?: string;

    /**
     * 计树器服务集合
     *
     * @type {any}
     * @memberof ViewToolbar
     */
    @Prop()
    public counterServiceArray?:any;

    /**
     * 视图loading服务
     *
     * @type {any}
     * @memberof ViewToolbar
     */
    @Prop({default: false})
    public isViewLoading?:any;


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
     * 绘制分割线
     *
     * @protected
     * @returns {*}
     * @memberof ViewToolbar
     */
    protected renderSeperator(): any {
        return <span class='seperator'>|</span>;
    }

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
            if (item.items && item.items.length > 0) {
                return this.renderMenuGroup(item);
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
        const targetCounterService:any = Util.findElementByField(this.counterServiceArray,'path',item.uiaction?.getPSAppCounter?.path)?.service;
        if(item.visabled){
            return (
                <tooltip transfer={true} max-width='600' disabled={!item.tooltip}>
                    {item.uiaction && Object.is(item.uiaction.uIActionTag, 'ExportExcel') ? (
                        <app-export-excel
                            item={item}
                            caption={item.caption}
                            on-exportexcel={($event: any) => this.itemClick({ tag: item.name }, $event)}
                            loading={this.isViewLoading}
                        ></app-export-excel>
                    ) : (item.uiaction && item.uiaction.counterId) ? (
                        <el-badge value={targetCounterService.counterData[item.uiaction.counterId]}>
                            <i-button
                                disabled={item.disabled}
                                class={item.class}
                                on-click={(e: any) => this.itemClick({ tag: item.name }, e)}
                                loading={this.isViewLoading}
                            >
                                {item.showIcon ? <menu-icon item={item} /> : null}
                                {item.showCaption ? <span class='caption'>{item.caption}</span> : ''}
                            </i-button>
                        </el-badge>
                    ) : (
                                <i-button
                                    disabled={item.disabled}
                                    class={item.class}
                                    on-click={(e: any) => this.itemClick({ tag: item.name }, e)}
                                    loading={this.isViewLoading}
                                >
                                    {item.showIcon ? <menu-icon item={item} /> : null}
                                    {item.showCaption ? <span class='caption'>{item.caption}</span> : ''}
                                </i-button>
                            )}
                    <div slot='content'>{item.tooltip}</div>
                </tooltip>
            );
        }else{
            return null;
        }
    }

    /**
     * 绘制菜单分组
     *
     * @protected
     * @param {*} item
     * @returns {*}
     * @memberof ViewToolbar
     */
    protected renderMenuGroup(item: ToolbarItem): any {
        return (
            <dropdown class='user-menu-child' placement='left-start'>
                <dropdownItem name={item.name} title={item.tooltip}>
                    <icon type='ios-arrow-back'></icon>
                    {item.caption}
                </dropdownItem>
                <dropdownMenu slot='list'>{this.renderMenuItems(item.items)}</dropdownMenu>
            </dropdown>
        );
    }

    /**
     * 绘制模式2
     *
     * @protected
     * @returns {*}
     * @memberof ViewToolbar
     */
    protected renderStyle2(): any {
        return this.items.map((item: ToolbarItem) => {
            if (!item.visabled) {
                return;
            }
            let content: any;
            if (item.itemType === 'SEPERATOR') {
                content = this.renderSeperator();
            } else if (!item.items) {
                content = this.renderMenuItem(item);
            } else {
                content = (
                    <dropdown
                        v-show={item.visabled}
                        class="studio-dropdown toolbar-dropdown"
                        placement="bottom-start"
                        trigger="click"
                        stop-propagation
                    >
                        {
                            <i-button
                                v-show={item.visabled}
                                disabled={item.disabled}
                                title={item.tooltip}
                                class={item.class}
                                ghost
                                loading={this.isViewLoading}
                            >
                                {item.showIcon ? <menu-icon item={item} /> : null}
                                {item.showCaption ? item.caption : ''}
                                <icon type="ios-arrow-down" />
                            </i-button>
                        }
                        <dropdownMenu slot="list">{this.renderMenuItems(item.items)}</dropdownMenu>
                    </dropdown>
                );
            }
            return <div class="toolbar-item">{content}</div>;
        });
    }

    /**
     * 绘制默认模式工具栏
     *
     * @protected
     * @returns {*}
     * @memberof ViewToolbar
     */
    protected renderDefault(): any {
        return this.items.map((item: ToolbarItem) => {
            if (item.itemType === 'SEPERATOR') {
                return this.renderSeperator();
            }
            if (Object.is(item.itemType, 'ITEMS') && item.items && item.items.length > 0) {
                return (
                    <dropdown v-show={item.visabled} trigger='click'>
                        <tooltip transfer={true} max-width='600'>
                            <i-button class={item.class} loading={this.isViewLoading}>
                                {item.icon ? <menu-icon item={item} /> : null}
                                {item.caption ? <span class='caption'>{item.caption}</span> : null}
                                <icon type='ios-arrow-down'></icon>
                            </i-button>
                        </tooltip>
                        <dropdown-menu slot='list'>{this.renderMenuItems(item.items)}</dropdown-menu>
                    </dropdown>
                );
            }
            return this.renderMenuItem(item);
        });
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
        let content: any = this.mode == "STYLE2" ? this.renderStyle2() : this.renderDefault();
        return <div class={{ 'toolbar-container': true, 'style2': this.mode == "STYLE2" ? true : false }}>{content}</div>;
    }
}
