import { Prop, Watch, Emit, Component } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobMeditViewPanelControlBase } from '../../../widgets';

/**
 * 多编辑面板部件基类
 *
 * @export
 * @class AppMobMeditViewPanelBase
 * @extends {MobMeditViewPanelControlBase}
 */
@Component({})
export class AppMobMeditViewPanelBase extends MobMeditViewPanelControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobMeditViewPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobMeditViewPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobMeditViewPanelBase
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
     * @memberof AppMobMeditViewPanelBase
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
     * @memberof AppMobMeditViewPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobMeditViewPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }


    /**
     * 绘制内容
     * 
     * @param item 
     * @memberof AppMEditViewPanelBase
     */
    public renderContent() {
        return this.items.map((item: any) => {
            return [
                <div class="app-medit-view-panel-card">
                    <ion-card>
                        <div class="meditviewpanel_delete_icon_container" >
                            <app-mob-icon on-onClick={() => { this.deleteItem(item) }} class="meditviewpanel_delete_icon right-common-icon"  name="close-circle-outline"></app-mob-icon> 
                        </div>
                        <ion-card-content>
                            {
                                this.$createElement("app-view-shell", {
                                    props: {
                                        staticProps: {
                                            viewDefaultUsage: 'includedView',
                                            viewModelData: this.controlInstance.getEmbeddedPSAppView(),
                                            panelState: this.panelState,
                                            showTitle: false,
                                            isChildView: true
                                        },
                                        dynamicProps: {
                                            _context: JSON.stringify(item._context),
                                            _viewparams: JSON.stringify(item.viewparam),
                                        }
                                    },
                                    on: {
                                        viewdataschange: this.viewDataChange.bind(this),
                                    }
                                })
                            }
                        </ion-card-content>
                    </ion-card>
                </div>
            ]
        })
    }

    /**
     * 绘制多编辑面板
     *
     * @returns {*}
     * @memberof AppMobMeditViewPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return;
        }
        let chartClassName = {
            ...this.renderOptions.controlClassNames,
        };
        return (
            <div class={chartClassName} >
                <div class="app-mob-medit-view-panel">
                    {this.items.length > 0 ? this.renderContent() : null}
                    <div on-click={() => { this.handleAdd() }} class="meditviewpanel_add_btn"><app-mob-icon on-onClick={() => { this.handleAdd() }}  name="add"></app-mob-icon></div>
                </div>
            </div>
        )
    }
}