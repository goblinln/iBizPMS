import { IPSAppCounterRef, IPSDETabViewPanel, IPSSysImage } from '@ibiz/dynamic-model-api';
import { Util } from 'ibiz-core';
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { MobDrtabControlBase } from '../../../widgets/mob-drtab-control-base';

/**
 * 数据关系分页部件基类
 *
 * @export
 * @class AppMobDrtabBase
 * @extends {TabExpPanelBase}
 */
export class AppMobDrtabBase extends MobDrtabControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobDrtabBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobDrtabBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobDrtabBase
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
     * @memberof AppMobDrtabBase
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
     * @memberof AppMobDrtabBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobDrtabBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制关系界面
     *
     * @param {*} tabPage
     * @return {*} 
     * @memberof AppMobDrtabBase
     */
    public renderDrView(tabPage: any){
        let viewData: any = Util.deepCopy(this.context);
        viewData.viewpath = tabPage?.getPSAppView?.path;
        let viewParam = this.viewparams;
        return this.$createElement('app-view-shell', {
            props: { 
                staticProps: {
                    viewDefaultUsage: 'includedView',
                    appDeCodeName: this.appDeCodeName,
                },
                dynamicProps:{
                    _context: JSON.stringify(viewData), 
                    _viewparams: JSON.stringify(viewParam), 
                }
            },
            class: "viewcontainer2",
            on: {
            }
        })
    }

    /**
     * 绘制关系分页部件
     *
     * @returns {*}
     * @memberof AppMobDrtabBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const tabPages = this.controlInstance.M.getPSDEDRTabPages;
        return (
            <div class={{ ...controlClassNames, 'drtab': true }} >
                <van-tabs 
                    class="app-dr-tab" 
                    color="var(--app-background-color)" 
                    swipeable={true} 
                    scrollspy={true} 
                    animated={true} 
                    value={this.activiedTabViewPanel} 
                    type="card" 
                    on-click={this.tabPanelClick.bind(this)}>
                    <van-tab index={0} name='mainform' title={this.controlInstance.M.editItemCaption?this.controlInstance.M.editItemCaption:'主表单'}>
                        <div class='main-data'>
                            {this.$parent.$slots.mainform}
                        </div>
                    </van-tab>
                    {tabPages?.length > 0 && tabPages.map((tabPage: any,index: number)=>{
                        return <van-tab index={index+1} name={tabPage.name} title={tabPage.caption}>
                            {this.renderDrView(tabPage)}
                        </van-tab> 
                    })}
                </van-tabs>              
            </div>
        );
    }
}