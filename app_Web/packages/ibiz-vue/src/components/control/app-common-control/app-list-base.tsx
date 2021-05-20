import { Prop, Watch, Emit } from 'vue-property-decorator';
import { ModelTool, Util } from 'ibiz-core';
import { ListControlBase } from '../../../widgets';

/**
 * 实体列表部件基类
 *
 * @export
 * @class AppListBase
 * @extends {ListControlBase}
 */
export class AppListBase extends ListControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppListBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppListBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppListBase
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
     * @memberof AppListBase
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
     * @memberof AppListBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     * 
     * @param 抛出参数 
     * @memberof AppListBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string, action: string, data: any }): void {
        if (action == 'panelDataChange') {
            this.onPanelDataChange(data.item, data.data);
        }
    }

    /**
     * 绘制列表
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        let listClassName = {
            'app-list': true,
            'app-list-empty': this.items.length <= 0,
            ...this.renderOptions.controlClassNames,
        };
        return this.batchToolbarInstance
            ? <div style="overflow:auto;height: 100%;">
                <div class={listClassName} style='height:100%;'>
                    {this.items.length > 0 ? <div style="height:100%;">{this.renderHaveItems()}</div> 
                    : this.isControlLoaded ? this.renderEmptyDataTip() : this.renderLoadDataTip()}
                    {this.viewStyle == "DEFAULT" ? <el-backtop target=".content-container .app-list"></el-backtop> : null}
                </div>
                {this.batchToolbarInstance && (this.selections.length > 0 ?
                    <row class='list-pagination'>
                        {this.renderBatchToolbar()}
                    </row> : null
                )}
            </div>
            : <div class={listClassName} style='height:100%;'>
                {this.items.length > 0 ? <div style="height:100%;">{this.renderHaveItems()}</div> 
                : this.isControlLoaded ? this.renderEmptyDataTip() : this.renderLoadDataTip()}
                {this.viewStyle == "DEFAULT" ? <el-backtop target=".content-container .app-list"></el-backtop> : null}
            </div>
    }

    /**
     * 绘制列表主体内容
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public renderListContent(item: any, listItem: any) {
        return [<div class='app-list-item-content'>
            <div class='item-icon'>
                {item.srficon ? (
                    <img src={item.srficon} />
                ) : (
                    <img src='./assets/img/noimage.png' />
                )}
            </div>
            <div class='item-content-text'>
                <span class='item-text'>{item.srfmajortext}</span>
                {item.srfdescription && (
                    <span class='item-subtext'>{item.srfdescription}</span>
                )}
            </div>
        </div>,
        item.srfdate && (
            <div class='app-list-item-date'>
                <span class='date'>{item.srfdate}</span>
            </div>
        ),
        <div class='app-list-item-action'>{listItem ? this.renderListItemAction(item, listItem) : ''}</div>]
    }

    /**
     * 绘制有items的情况
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public renderHaveItems() {
        return this.items.map((item: any, index: number) => {
            let listItem: any = this.controlInstance.getPSDEListItems() && (this.controlInstance.getPSDEListItems() as any)?.length > 0 ? (this.controlInstance.getPSDEListItems() as any)[index] : null;
            return this.controlInstance.enableGroup ? this.renderHaveGroup(item, listItem) : this.renderNoGroup(item, listItem);
        })
    }

    /**
     * 绘制面板部件
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public renderItemPSLayoutPanel(item: any) {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.controlInstance.getItemPSLayoutPanel());
        Object.assign(targetCtrlParam.dynamicProps, { inputData: item });
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.controlInstance.getItemPSLayoutPanel()?.name, on: targetCtrlEvent });
    }


    /**
     * 绘制没有分组的情况
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public renderNoGroup(item: any, listItem: any) {
        return <div
            key={item.srfkey}
            class={['app-list-item', item.isselected === true ? 'isSelect' : ''].join(' ')}
            on-click={() => this.handleClick(item)}
            on-dblclick={() => this.handleDblClick(item)}
        >
            {this.controlInstance.getItemPSLayoutPanel() ? this.renderItemPSLayoutPanel(item) : this.renderListContent(item, listItem)}
        </div>
    }

    /**
     * 绘制有分组的情况
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public renderHaveGroup(item: any, listItem: any) {
        return <el-collapse>
            {this.groupData.map((item: any) => {
                return [
                    <div slot='title' style='margin: 0 0 0 12px;'>
                        <b>{item.group}</b>
                    </div>,
                    item.children.length > 0 ? (
                        <div style='margin: 0 0 0 32px;'>
                            {item.children.map((item: any) => {
                                return <div
                                    class={['app-list-item', { 'isSelect': item.isselected === true ? true : false }]}
                                    on-click={() => this.handleClick(item)}
                                    on-dblclick={() => this.handleDblClick(item)}
                                >
                                    {this.controlInstance.getItemPSLayoutPanel() ? this.renderItemPSLayoutPanel(item) : this.renderListContent(item, listItem)}
                                </div>
                            })}
                        </div>
                    ) : (
                        <div style='text-align: center;'>{this.$t('${langbase}.nodata')}</div>
                    ),
                ];
            })}
        </el-collapse>
    }

    /**
     * 绘制列表项行为
     * @param item 列数据
     * @param listItem
     * @memberof AppListBase
     */
    public renderListItemAction(item: any, listItem: any) {
        const { getPSDEUIActionGroup: UIActionGroup } = listItem;
        return UIActionGroup && UIActionGroup?.getPSUIActionGroupDetails?.map((uiactionDetail: any, index: number) => {
            const uiaction = uiactionDetail.getPSUIAction;
            if (item[uiaction.uIActionTag].visabled) {
                return <a key={index} style='display: inline-block;margin: 0 12px;' disabled={item[uiaction.uIActionTag].disabled} on-click={($event: any) => { this.handleActionClick(item, $event, listItem, uiactionDetail) }}>
                    {uiactionDetail.showIcon ? <i class={uiaction?.iconCls} style='margin-right:2px;'></i> : ''}
                    <span>{uiactionDetail.showCaption ? uiaction?.caption ? uiaction.caption : "" : ""}</span>
                </a>;
            }
        });
    }
}