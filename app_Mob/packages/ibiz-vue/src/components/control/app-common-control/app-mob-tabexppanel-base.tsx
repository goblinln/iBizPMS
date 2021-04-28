
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobTabExpPanelControlBase } from '../../../widgets';
import { IPSAppDETabExplorerView, IPSTabExpPanel, IPSDETabViewPanel } from '@ibiz/dynamic-model-api';

/**
 * 分页导航栏部件基类
 *
 * @export
 * @class AppMobTabExpPanelBase
 * @extends {MobTabExpPanelControlBase}
 */
export class AppMobTabExpPanelBase extends MobTabExpPanelControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppMobTabExpPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobTabExpPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobTabExpPanelBase
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
     * @memberof AppMobTabExpPanelBase
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
     * @memberof AppMobTabExpPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobTabExpPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 视图面板
     */
    public renderTabViewPanel(modelJson: IPSDETabViewPanel) {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(modelJson);
        targetCtrlParam.dynamicProps.isActivied = this.activiedTabViewPanel === modelJson.name ? true : false;
        return this.$createElement(targetCtrlName,{ props: targetCtrlParam, ref: modelJson.name, on: targetCtrlEvent })
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppMobTabExpPanelBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const allControls = this.controlInstance.getPSControls() as IPSDETabViewPanel[];
        return (
            <span class={{ ...controlClassNames }}>
                {allControls.map((item: IPSDETabViewPanel) => {
                    return this.renderTabViewPanel(item);
                })}
            </span>
        )

    }
}
