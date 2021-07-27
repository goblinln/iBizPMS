import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { ViewPanelControlBase } from '../../../widgets';

/**
 * 面板部件基类
 *
 * @export
 * @class AppViewPanelBase
 * @extends {PanelControlBase}
 */
export class AppViewPanelBase extends ViewPanelControlBase {
    /**
     * 部件动态参数
     *
     * @memberof AppViewPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppViewPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppViewPanelBase
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
     * @memberof AppViewPanelBase
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
     * @memberof AppViewPanelBase
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
     * 渲染
     * 
     * @memberof AppViewPanelBase
     */
    render(h: any) {
        if (!this.controlIsLoaded || !this.embedViewPath) {
            return;
        }
        if (!this.cacheUUID) {
            this.cacheUUID = Util.createUUID();
        }
        let localContext = Util.deepCopy(this.context);
        let localViewParam = Util.deepCopy(this.viewparams);
        Object.assign(localContext, { viewpath: this.embedViewPath });
        return h('app-view-shell', {
            props: { 
                staticProps: {
                    viewDefaultUsage: false,
                    appDeCodeName: this.appDeCodeName,
                },
                dynamicProps:{
                    viewdata: JSON.stringify(localContext), 
                    viewparam: JSON.stringify(localViewParam), 
                }
            },
            key: this.cacheUUID,
            class: "viewcontainer2",
            on: {
            }
        })
    }
}
