import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core'
import { KanbanControlBase } from '../../../widgets';
import { IPSUIActionGroup, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';

/**
 * 实体看板部件基类
 *
 * @export
 * @class AppKanbanBase
 * @extends {KanbanControlBase}
 */
export class AppKanbanBase extends KanbanControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppKanbanBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppKanbanBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppKanbanBase
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
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppKanbanBase
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
     * @memberof AppKanbanBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDefaultTree
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制itemPSLayoutPanel部件
     *
     * @returns {*}
     * @memberof AppKanbanBase
     */
    public renderItemPSLayoutPanel(item: any) {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.controlInstance.getItemPSLayoutPanel(), item);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.controlInstance?.getItemPSLayoutPanel?.name, on: targetCtrlEvent });
    }

    /**
     * 绘制折叠栏
     * 
     * @param group 分组
     * @param index 下标
     * @memberof AppKanbanBase
     */
    public renderDragbar(group: any, index: any) {
        return (
            <draggable
                list={group?.items}
                class="dragbar"
                ghostClass="dragitems"
                handle=".dataview-group-item"
                group={{ name: 'bar', put: group?.folding ? false : true }}
                on-change={($event: any) => this.onDragChange($event, group?.value)}>
                {
                    !group.folding ?
                        <div class="dataview-group-folading-kanban" on-click={() => this.onClick(group, index)}>
                            <div class="unfold-icon">
                                <i class="el-icon-s-unfold" title="展开"></i>
                            </div>
                            <div class="dataview-group-folding">
                                {this.getGroupText(group?.value)}{"(" + group?.items?.length + ")"}
                            </div>
                        </div> : null
                }
            </draggable>
        );
    }

    /**
     * 绘制分组界面行为
     * 
     * @param ActionGroup 分组界面行为组
     * @param group 分组类型
     * @memberof AppKanbanBase
     */
    public renderGroupAction(ActionGroup: IPSUIActionGroup | null, group: any) {
        return (
            <poptip trigger="hover" content="content" placement={!Object.is(this.controlInstance?.groupLayout, 'COLUMN') ? 'bottom-end' : 'right-start'} style="float: right;">
                <icon type={!Object.is(this.controlInstance?.groupLayout, 'COLUMN') ? 'md-more' : 'ios-more'} />
                <div slot="content" class="group-action">
                    {
                        ActionGroup?.getPSUIActionGroupDetails()?.map((uiActionDetail: IPSUIActionGroupDetail) => {
                            return (
                                <div class="group-action-item">
                                    <i-button long on-click={($event: any) => this.uiActionClick(uiActionDetail, $event, group)}>
                                        {uiActionDetail?.getPSUIAction()?.getPSSysImage()?.imagePath ? <img class="app-kanban-icon" src={uiActionDetail?.getPSUIAction()?.getPSSysImage()?.imagePath} /> : null}
                                        {uiActionDetail?.getPSUIAction()?.getPSSysImage()?.cssClass ? <i class={[uiActionDetail?.getPSUIAction()?.getPSSysImage()?.cssClass, "app-kanban-icon"]}></i> : null}
                                        <span class="caption">{uiActionDetail?.getPSUIAction()?.caption}</span>
                                    </i-button>
                                </div>
                            )
                        })
                    }
                </div>
            </poptip>
        )
    }

    public renderItemContent(item: any) {
        if (this.controlInstance?.getItemPSSysPFPlugin()) {
            const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", this.controlInstance?.getItemPSSysPFPlugin()?.pluginCode || '');
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, this.controlInstance, this, item);
            }
        } else {
            return item.srfmajortext
        }
    }

    /**
     * 绘制分组看板
     * 
     * @param group 分组
     * @param index 下标
     * @memberof AppKanbanBase
     */
    public renderGroup(group: any, index: any) {
        const groupStyle = {
            'flex-grow': this.controlInstance?.groupWidth || this.controlInstance.groupHeight ? false : '1'
        }
        this.controlInstance?.groupWidth ? Object.assign(groupStyle, { width: this.controlInstance?.groupWidth + 'px' }) : null;
        this.controlInstance?.groupHeight ? Object.assign(groupStyle, { height: this.controlInstance?.groupHeight + 'px' }) : null;
        return (
            group.folding ?
                <div class="dataview-group-content" style={groupStyle}>
                    <div class={["dataview-group-header", this.controlInstance?.getGroupPSSysCss()?.cssName]}>
                        <div class="fold-icon" on-click={() => this.onClick(group, index)}>
                            <i class="el-icon-s-fold" title="折叠"></i>
                        </div>
                        <span class="fold-text">
                            {this.getGroupText(group.value)}
                        </span>
                        {this.controlInstance.getGroupPSUIActionGroup() ? this.renderGroupAction(this.controlInstance.getGroupPSUIActionGroup(), group) : null}
                    </div>
                    {group.items.length > 0 ?
                        <draggable list={group.items} group={this.controlInstance?.name} class="dataview-group-items" on-change={($event: any) => this.onDragChange($event, group.value)}>
                            {
                                group.items.map((item: any) => {
                                    return (
                                        <div class={['dataview-group-item', { 'is-select': item.isselected }]} on-click={() => this.handleClick(item)} on-dblclick={() => this.handleDblClick(item)}>
                                            { this.controlInstance.getItemPSLayoutPanel() ? this.renderItemPSLayoutPanel(item) : this.renderItemContent(item)}
                                        </div>
                                    )
                                })
                            }
                        </draggable> :
                        <div class="app-data-empty">
                            <span>{"无数据"}</span>
                        </div>
                    }
                </div> : null
        );
    }

    /**
     * 绘制快速操作栏
     * 
     * @memberof AppKanbanBase
     */
    public renderQuickToolbar() {
        return (
            <div class="quick-action">
                <view-toolbar toolbarModels={this.toolbarModels} on-item-click={(data: any, $event: any) => { this.handleItemClick(data, $event) }}></view-toolbar>
            </div>
        );
    }

    /**
     * 绘制部件
     * 
     * @param h 
     * @memberof AppKanbanBase
     */
    public render(h: any) {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (
            <div class={{ ...controlClassNames, 'app-data-view-group': !Object.is(this.controlInstance?.groupLayout, 'COLUMN'), 'app-data-view-group-column': Object.is(this.controlInstance.groupLayout, 'COLUMN') }}>
                {
                    [
                        this.groups.map((item, index) => {
                            if (this.controlInstance?.getGroupPSSysPFPlugin()) {
                                const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", this.controlInstance.getGroupPSSysPFPlugin()?.pluginCode || '');
                                if (pluginInstance) {
                                    return pluginInstance.renderCtrlItem(this.$createElement, this.controlInstance, this, item);
                                }
                            } else {
                                return [
                                    this.renderDragbar(item, index),
                                    this.renderGroup(item, index),
                                ]
                            }
                        }),
                        this.renderQuickToolbar()
                    ]
                }
            </div>
        )
    }
}