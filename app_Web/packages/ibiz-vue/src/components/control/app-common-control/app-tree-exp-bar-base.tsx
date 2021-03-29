import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TreeExpBarControlBase } from '../../../widgets/tree-exp-bar-control-base';

/**
 * 树视图导航栏部件基类
 *
 * @export
 * @class AppTreeExpBarBase
 * @extends {TreeExpBarControlBase}
 */
export class AppTreeExpBarBase extends TreeExpBarControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppTreeExpBarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppTreeExpBarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeExpBarBase
     */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
           super.onDynamicPropsChange(newVal,oldVal);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeExpBarBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppTreeExpBarBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppTreeExpBarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制导航视图
     * 
     * @memberof AppTreeExpBarBase
     */
    public renderNavView() {
        if(this.selection?.view?.viewname){
            return this.$createElement(this.selection.view.viewname,{
                key:Util.createUUID(),
                props: {
                    staticProps:{
                        viewDefaultUsage: false,
                    },
                    dynamicProps:{
                        viewdata: JSON.stringify(this.selection.context),
                        viewparam: JSON.stringify(this.selection.viewparam)
                    }
                },
                class: 'viewcontainer2',
                on: {
                    viewdataschange: this.onViewDatasChange.bind(this),
                    drdatasaved: this.onDrViewDatasChange.bind(this),
                    drdatasremove: this.onDrViewDatasChange.bind(this),
                    viewdatasactivated: this.viewDatasActivated.bind(this),
                    viewload: this.onViewLoad.bind(this),
                }
            })
        }
    }

    /**
     * 绘制标题栏
     *
     * @returns {*}
     * @memberof AppTreeExpBarBase
     */
    public renderTitleBar() {
        return (
            <div class="tree-exp-bar-header">
                <div class="tree-exp-bar-title">
                    <icon type="ios-home-outline"/>
                    <span>{this.controlInstance?.title}</span>
                </div>
            </div>
        );
    }

    /**
     * 绘制树视图导航栏
     *
     * @returns {*}
     * @memberof AppTreeExpBarBase
     */
    public render() {
        if(!this.controlIsLoaded) {
            return null;
        }
        const showTitleBar = this.controlInstance?.showTitleBar;
        const otherClassNames: any = {
            "tree-exp-content": showTitleBar ? true : false,
            "tree-exp-content2": !showTitleBar ? true : false,
            "treeview-exp-bar-content": false,
            "treeview-exp-bar-content2": false
        }
        return (
            <split
                id={this.controlInstance?.codeName?.toLowerCase()}
                class={[`app-tree-exp-bar`, this.renderOptions?.controlClassNames]}
                v-model={this.split}
                mode={this.sideBarlayout == 'LEFT' ? 'horizontal' : 'vertical'}
                on-on-move-end={this.onSplitChange.bind(this)}>
                    {this.renderContent(otherClassNames)}
            </split>
        );
    }
}