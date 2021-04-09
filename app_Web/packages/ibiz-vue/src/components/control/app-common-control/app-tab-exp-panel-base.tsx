import { Util } from 'ibiz-core';
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { TabExpPanelBase } from '../../../widgets';

/**
 * 分页导航面板部件基类
 *
 * @export
 * @class AppTabExpPanelBase
 * @extends {TabExpPanelBase}
 */
export class AppTabExpPanelBase extends TabExpPanelBase {

    /**
     * 部件静态参数
     *
     * @memberof AppTabExpPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppTabExpPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTabExpPanelBase
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
     * @memberof AppTabExpPanelBase
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
     * @memberof AppTabExpPanelBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppTabExpPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制分页导航面板Container
     *
     * @memberof AppTabExpPanelBase
     */
    public renderTabPaneContent(tabViewPanel: any) {
        let { targetCtrlParam, targetCtrlEvent, targetCtrlName } = this.computeTargetCtrlData(tabViewPanel);
        // 视图面板计数器
        let viewPanelCount: any = undefined;
        if (tabViewPanel?.getPSAppCounterRef?.id && tabViewPanel?.counterId) {
            const targetCounterService: any = Util.findElementByField(this.counterServiceArray, 'id', tabViewPanel?.getPSAppCounterRef?.id)?.service;
            viewPanelCount = targetCounterService?.counterData?.[tabViewPanel.counterId.toLowerCase()]
        }
        const tabsName = `${this.controlInstance?.appDataEntity?.codeName?.toLowerCase()}_${this.controlInstance?.controlType?.toLowerCase()}_${this.controlInstance?.codeName?.toLowerCase()}`;
        let disabled = this.authResourceObject && this.authResourceObject[tabViewPanel.name]?.disabled;
        return (
            <tab-pane lazy={true} name={tabViewPanel.name} tab={tabsName} disabled={disabled}
                label={(h: any) => {
                    return h('div', [
                        tabViewPanel.getPSSysImage ? tabViewPanel.getPSSysImage?.imagePath ?
                            h('img', {
                                src: tabViewPanel.getPSSysImage.imagePath,
                                style: {
                                    'margin-right': '2px'
                                }
                            }) :
                            h('i', {
                                class: tabViewPanel.getPSSysImage?.cssClass,
                                style: {
                                    'margin-right': '2px'
                                }
                            }) : '',
                        h('span', tabViewPanel.caption),
                        h('Badge', {
                            props: {
                                count: viewPanelCount,
                                type: 'primary'
                            }
                        })
                    ])
                }}>
                {
                    this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: tabViewPanel.name, on: targetCtrlEvent })
                }
            </tab-pane>
        )
    }


    /**
     * 绘制分页导航面板
     *
     * @returns {*}
     * @memberof AppTabExpPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const { codeName, tabViewPanels, appDataEntity, controlType } = this.controlInstance;
        const tabsName = `${appDataEntity?.codeName?.toLowerCase()}_${controlType?.toLowerCase()}_${codeName?.toLowerCase()}`;
        return (
            <div class={{ ...controlClassNames, 'tabviewpanel': true }} >
                <tabs value={this.activiedTabViewPanel} animated={false} class='tabexppanel' name={tabsName} on-on-click={($event: any) => this.tabPanelClick($event)}>
                    {
                        tabViewPanels.map((tabViewPanel: any, index: any) => {
                            return this.authResourceObject && this.authResourceObject[tabViewPanel.name]?.visabled ?
                                this.renderTabPaneContent(tabViewPanel) : null
                        })
                    }
                </tabs>
            </div>
        );
    }

}