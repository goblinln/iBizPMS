import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobAppMenuControlBase } from '../../../widgets';

/**
 * 应用菜单部件基类
 *
 * @export
 * @class ViewToolbar
 * @extends {Vue}
 */
export class AppMobMenuBase extends MobAppMenuControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppMobMenuBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobMenuBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobMenuBase
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
     * @memberof AppMobMenuBase
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
     * @memberof AppMobMenuBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobMenuBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制应用菜单
     *
     * @returns {*}
     * @memberof AppMobMenuBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const { codeName } = this.controlInstance;
        const allAppFunc = this.service.getAllFuncs();
        return (
            this.controlStyle === 'ICONVIEW' ?
                <app-mob-menu-ionic-view
                    class={controlClassNames}
                    menuName={codeName?.toLowerCase()}
                    items={this.menus}
                    menuModels={allAppFunc}
                    on-select={this.select.bind(this)}
                >
                </app-mob-menu-ionic-view> :
                <app-mob-menu-default-view
                    // todo 计数器
                    class={controlClassNames}
                    menuName={codeName?.toLowerCase()}
                    items={this.menus}
                    menuModels={allAppFunc}
                    on-select={this.select.bind(this)}
                >
                </app-mob-menu-default-view>
        );
    }
}
