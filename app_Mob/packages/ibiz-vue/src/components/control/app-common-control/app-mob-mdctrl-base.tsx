import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util, ViewTool } from 'ibiz-core';
import { MobMDCtrlControlBase } from '../../../widgets';
import { IPSUIAction, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';

/**
 * 多数据部件基类
 *
 * @export
 * @class AppMobMDCtrlBase
 * @extends {MobMDCtrlControlBase}
 */
export class AppMobMDCtrlBase extends MobMDCtrlControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppMobMDCtrlBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobMDCtrlBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobMDCtrlBase
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
     * @memberof AppMobMDCtrlBase
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
     * @memberof AppMobMDCtrlBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobMDCtrlBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }


    /**
     * 绘制界面行为组
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderListItemAction(item: any) {
        return [
            this.controlInstance?.getPSDEUIActionGroup() ? this.renderActionGroup(item) : null,
            this.controlInstance?.getPSDEUIActionGroup2() ? this.renderActionGroup2(item) : null,
        ]
    }

    /**
     * 绘制左滑界面行为组
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderActionGroup(item: any) {
        const details = this.controlInstance?.getPSDEUIActionGroup()?.getPSUIActionGroupDetails?.();
        if (this.controlInstance.controlStyle != 'LISTVIEW3') {
            return <ion-item-options side="start">
                {details && details?.map((detail: IPSUIActionGroupDetail) => {
                    if (detail.getPSUIAction()) {
                        const uiaction: IPSUIAction | null = detail.getPSUIAction();
                        if (uiaction && item[uiaction.codeName]?.visabled) {
                            let iconName = item[uiaction.codeName].icon ? ViewTool.setIcon(item[uiaction.codeName]?.icon) : '';
                            return <ion-item-option disabled={item[uiaction.codeName]?.disabled} color={uiaction.uIActionTag == "Remove" ? 'danger' : 'primary'} on-click={($event: any) => this.mdctrl_click($event, detail, item)}>
                                {item[uiaction.codeName]?.icon && item[uiaction.codeName]?.isShowIcon && <ion-icon name={iconName}></ion-icon>}
                                {item[uiaction.codeName]?.isShowCaption && <ion-label >{this.$tl(uiaction.getCapPSLanguageRes()?.lanResTag,uiaction.caption)}</ion-label>}
                            </ion-item-option>
                        }
                    }
                })}
            </ion-item-options>
        }
    }

    /**
     * 绘制右滑界面行为组
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderActionGroup2(item: any) {
        const details = this.controlInstance?.getPSDEUIActionGroup2()?.getPSUIActionGroupDetails?.();
        if (this.controlInstance.controlStyle != 'LISTVIEW3') {
            return <ion-item-options side="end">
                {details?.map((detail: IPSUIActionGroupDetail) => {
                    if (detail.getPSUIAction()) {
                        const uiaction: IPSUIAction | null = detail.getPSUIAction();
                        if (uiaction && item[uiaction.codeName]?.visabled) {
                            let iconName = item[uiaction.codeName].icon ? ViewTool.setIcon(item[uiaction.codeName]?.icon) : '';
                            return <ion-item-option disabled={item[uiaction.codeName]?.disabled} color={uiaction.uIActionTag == "Remove" ? 'danger' : 'primary'} on-click={($event: any) => this.mdctrl_click($event, detail, item)}>
                                {item[uiaction.codeName]?.icon && item[uiaction.codeName]?.isShowIcon && <ion-icon name={iconName}></ion-icon>}
                                {item[uiaction.codeName]?.isShowCaption && <ion-label >{this.$tl(uiaction.getCapPSLanguageRes()?.lanResTag,uiaction.caption)}</ion-label>}
                            </ion-item-option>
                        }
                    }
                })}
            </ion-item-options>
        }
    }
    /**
     * 绘制导航列表
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderListExpBar() {
        return <van-sidebar
            v-model={this.listItem}
            on-change={this.switchView.bind(this)}>
            {this.items.map((item: any) => {
                return <van-sidebar-item class="app-mob-list-item" key={item?.srfkey} title={item?.srfmajortext} />
            })}
        </van-sidebar>
    }

    /**
     * 绘制列表项插件
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderPluginItem(item: any) {
        const plugin = this.controlInstance.getPSSysPFPlugin()
        if (plugin) {
            const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", plugin.pluginCode);
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, item, this, null);
            }
        }
    }

    /**
     * 绘制列表主体
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderMainMDCtrl() {
        const emptyText = this.controlInstance.emptyText;
        return this.items.length > 0
            ? <ion-list class="items" ref="ionlist">
                {this.controlInstance.enableGroup ? this.renderHaveGroup() : this.renderNoGroup()}
            </ion-list>
            : !this.isFirstLoad ? <div class="no-data">{emptyText || this.$t('app.commonWords.noData')}</div> : null
    }

    /**
     * 绘制触底加载
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderBottomRefresh() {
        if (this.loadStatus && !this.allLoaded && this.isNeedLoaddingText) {
            return <div class="loadding">
                <span >{this.$t('app.loadding') ? this.$t('app.loadding') : "加载中"}</span>
                <ion-spinner name="dots"></ion-spinner>
            </div>
        }
    }

    /**
     * 绘制分组
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderHaveGroup() {
        return this.group_data.map((obj: any) => {
            return <div class="item-grouped">
                <van-collapse v-model={this.activeName} on-change={this.changeCollapse.bind(this)}>
                    {obj.items && obj.items.length > 0 &&
                        <van-collapse-item name={obj.text}>
                            <template slot="title">
                                <div>{obj.text}(<label>{obj.items.length}</label>)</div>
                            </template>
                            {obj.items.map((item: any, index: any) => {
                                return <ion-item-sliding ref={item?.srfkey} class="app-mob-mdctrl-item" on-ionDrag={this.ionDrag.bind(this)} on-click={() => this.item_click(item)}>
                                    {this.renderListItemAction(item)}
                                    {
                                        this.controlInstance?.getPSSysPFPlugin()?.pluginType === 'LIST_ITEMRENDER' ? this.renderPluginItem(item) : this.controlInstance?.getItemPSLayoutPanel() ? this.renderItemPSLayoutPanel(item) : this.renderListContent(item, index)
                                    }
                                </ion-item-sliding>
                            })}
                        </van-collapse-item>}
                </van-collapse>
            </div>
        })

    }

    /**
     * 绘制无分组
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderNoGroup() {
        return this.items.length > 0
            ? this.items.map((item: any, index) => {
                return <ion-item-sliding ref={item?.srfkey} class="app-mob-mdctrl-item" on-ionDrag={this.ionDrag.bind(this)} on-click={() => this.item_click(item)}>
                    {this.renderListItemAction(item)}
                    {
                        this.controlInstance.getPSSysPFPlugin()?.pluginType === 'LIST_ITEMRENDER' ? this.renderPluginItem(item) : this.controlInstance.getItemPSLayoutPanel() ? this.renderItemPSLayoutPanel(item) : this.renderListContent(item, index)
                    }
                </ion-item-sliding>
            })
            : null
    }

    /**
     * 绘制面板部件
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderItemPSLayoutPanel(item: any) {
        if (!this.controlInstance.getItemPSLayoutPanel()) {
            return null
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.controlInstance.getItemPSLayoutPanel(),item);
        Object.assign(targetCtrlParam.dynamicProps, { inputData: item });
        return <div style="width:100%;">
            <ion-item class="ibz-ionic-item">
                {this.isChoose ? <ion-checkbox slot="start" class="iconcheck" v-show="isChoose" on-click="checkboxSelect(item)"></ion-checkbox> : null}
                {this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.controlInstance.getItemPSLayoutPanel()?.name, on: targetCtrlEvent })}
            </ion-item>
        </div>
    }


    /**
     * 绘制列表项集合
     * @return {*} 
     * @memberof AppDefaultMobMDCtrlBase
     */
    public renderListContent(item: any, index: any) {
        return <ion-item>
            <app-list-index-text item={item} index={index}></app-list-index-text>
        </ion-item>
    }


    /**
     * 绘制选择列表
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderSelectMDCtrl() {
        return this.items.length > 0
            ? (!this.isSingleSelect ?
                <ion-list class="pickUpList">
                    {this.items.map((item: any) => {
                        return <ion-item class="app-mob-mdctrl-item">
                            {/* todo项布局面板 */}
                            <ion-checkbox color="secondary" checked={item.checked} value={item.srfkey} on-ionChange={($event: any) => { this.checkboxChange($event) }} slot="end"></ion-checkbox>
                            <ion-label>{item.srfmajortext}</ion-label>
                        </ion-item>
                    })}
                </ion-list>
                : <div class="pickUpList">
                    <ion-radio-group value={this.selectedValue} >
                        {this.items.map((item: any) => {
                            return <ion-item class="app-mob-mdctrl-item" on-click={() => { this.onSimpleSelChange(item) }}>
                                <ion-label>{item.srfmajortext}</ion-label>
                                <ion-radio checked={item.checked} slot="end" value={item.srfkey}></ion-radio>
                            </ion-item>
                        })}
                    </ion-radio-group>
                </div>)
            : (!this.isFirstLoad ? <div class="no-data">{this.$t('app.commonWords.noData')}</div> : null)
    }


    /**
     * 绘制列表
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public render() {
        const { controlClassNames } = this.renderOptions;
        if (!this.controlIsLoaded) {
            return null;
        }
        return (
            <div class={{ ...controlClassNames, 'app-mob-mdctrl': true }}>
                <div class="app-mob-mdctrl-mdctrl">
                    {
                        this.listMode && this.listMode == "LISTEXPBAR" ?
                            this.renderListExpBar() :
                            this.listMode == "SELECT" ?
                                this.renderSelectMDCtrl() :
                                [this.renderMainMDCtrl(), this.renderBottomRefresh()]
                    }
                </div>
            </div>
        );
    }
}
