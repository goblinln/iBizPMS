import { Prop, Watch, Emit } from 'vue-property-decorator';
import { throttle, ModelTool, Util } from 'ibiz-core';
import { ListControlBase } from '../../../widgets';
import { IPSDEListItem, IPSDEUIAction, IPSDEUIActionGroup, IPSUIAction, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';

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
    public renderListContent(item: any, listItem: IPSDEListItem) {
        return [
            <div class='app-list-item-content'>
                <div class='item-content-text'>
                    <span class='item-title'>{item.srfmajortext}</span>
                    {item.content && (
                        <span class='item-content'>{item.content}</span>
                    )}
                </div>
            </div>,
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
            let listItem: IPSDEListItem = this.controlInstance.getPSDEListItems() && (this.controlInstance.getPSDEListItems() as any)?.length > 0 ? (this.controlInstance.getPSDEListItems() as any)[0] : null;
            return this.controlInstance.enableGroup ? this.renderHaveGroup(item, listItem) : this.renderNoGroup(item, listItem,index);
        })
    }

    /**
     * 绘制面板部件
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public renderItemPSLayoutPanel(item: any) {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.controlInstance.getItemPSLayoutPanel(), item);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.controlInstance.getItemPSLayoutPanel()?.name, on: targetCtrlEvent });
    }


    /**
     * 绘制没有分组的情况
     *
     * @returns {*}
     * @memberof AppListBase
     */
    public renderNoGroup(item: any, listItem: IPSDEListItem,index: number) {
        return <div
            key={index}
            class={['app-list-item', item.srfchecked === 1 ? 'isSelect' : ''].join(' ')}
            on-click={() => throttle(this.handleClick,[item],this)}
            on-dblclick={() => throttle(this.handleDblClick,[item],this)}
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
    public renderHaveGroup(item: any, listItem: IPSDEListItem) {
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
                                    class={['app-list-item', { 'isSelect': item.srfchecked === 1 ? true : false }]}
                                    on-click={() => throttle(this.handleClick,[item],this)}
                                    on-dblclick={() => throttle(this.handleDblClick,[item],this)}
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
    public renderListItemAction(item: any, listItem: IPSDEListItem) {
        const UIActionGroupDetails: IPSUIActionGroupDetail[] = (listItem.getPSDEUIActionGroup() as IPSDEUIActionGroup)?.getPSUIActionGroupDetails() || [];
        return UIActionGroupDetails.map((uiactionDetail: IPSUIActionGroupDetail, index: number) => {
            const uiaction: IPSDEUIAction = uiactionDetail.getPSUIAction() as IPSDEUIAction;
            if (uiaction && item[uiaction.uIActionTag].visabled) {
                return <a key={index} style='display: inline-block;margin: 0 12px;' disabled={item[uiaction.uIActionTag].disabled} on-click={($event: any) => { throttle(this.handleActionClick,[item, $event, listItem, uiactionDetail],this) }}>
                    {uiactionDetail.showIcon ? <i class={uiaction.getPSSysImage()?.cssClass} style='margin-right:2px;'></i> : ''}
                    <span>{uiactionDetail.showCaption ? this.$tl(uiaction.getCapPSLanguageRes()?.lanResTag, uiaction.caption) : ""}</span>
                </a>;
            }
        });
    }
}