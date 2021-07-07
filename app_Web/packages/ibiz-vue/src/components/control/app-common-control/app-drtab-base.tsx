import { IPSLanguageRes } from '@ibiz/dynamic-model-api';
import { debounce, Util } from 'ibiz-core';
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { DrtabControlBase } from '../../../widgets/drtab-control-base';

/**
 * 数据关系分页部件基类
 *
 * @export
 * @class AppDrtabBase
 * @extends {TabExpPanelBase}
 */
export class AppDrtabBase extends DrtabControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppDrtabBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppDrtabBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDrtabBase
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
     * @memberof AppDrtabBase
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
     * @memberof AppDrtabBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDrtabBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制关系界面
     *
     * @param {*} tabPage
     * @return {*} 
     * @memberof AppDrtabBase
     */
    public renderPanelLabel(h: any,tabPage: any){
      if (Object.is(this.controlInstance.parentModel.viewType,'DEEDITVIEW4')) {
        return <span slot="reference">{this.$tl((tabPage.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, tabPage.caption)}</span>
      }
      return (<el-popover
          content={this.$t("components.appformdruipart.blockuitipinfo")}
          width="150"
          popper-class="app-tooltip"
          disabled={!tabPage.disabled}
          trigger="hover">
          <span slot="reference">{this.$tl((tabPage.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, tabPage.caption)}</span>
      </el-popover>);
    }

    /**
     * 绘制关系界面
     *
     * @param {*} tabPage
     * @return {*} 
     * @memberof AppDrtabBase
     */
    public renderDrView(tabPage: any){
        let viewData: any = Util.deepCopy(this.context);
        let viewParam = this.viewparams;
        if(tabPage.disabled){
            return;
        }else{
            if(tabPage.localContext){
                Object.assign(viewData,tabPage.localContext);
            }
            if(tabPage.localViewParam){
                Object.assign(viewParam,tabPage.localViewParam);
            }
        }
        return this.$createElement('app-view-shell', {
            props: { 
                staticProps: {
                    viewDefaultUsage: false,
                    viewModelData: tabPage.getPSAppView(),
                    appDeCodeName: this.appDeCodeName,
                },
                dynamicProps:{
                    viewdata: JSON.stringify(viewData), 
                    viewparam: JSON.stringify(viewParam), 
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
     * @memberof AppDrtabBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const { codeName } = this.controlInstance;
        const editItemCaption = this.$tl((this.controlInstance.getEditItemCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, this.controlInstance.editItemCaption);
        return (
            <div class={{ ...controlClassNames, 'drtab': true }} >
                <tabs animated={false} class={{"app-dr-tab": true, 'is-disabled': !this.isShowSlot && this.drtabItems.length > 0 && this.drtabItems[0].disabled }} name={codeName} on-on-click={(...params: any[]) => debounce(this.tabPanelClick,params,this)}>
                    {this.isShowSlot ? 
                        <tab-pane index={0} name='mainform' tab={codeName} label={editItemCaption}>
                            <div class='main-data'>
                                {this.$parent.$slots.mainform}
                            </div>
                        </tab-pane>
                    : null}
                    {this.drtabItems?.length > 0 && this.drtabItems.map((tabPage: any,index: number)=>{
                        return <tab-pane index={index+1} disabled={tabPage.disabled} name={tabPage.name} tab={codeName} label={(h: any) =>this.renderPanelLabel(h,tabPage)}>
                            {!this.isShowSlot && tabPage.disabled ?
                                <spin class="app-druipart-spin" fix>{this.$t("components.appformdruipart.blockuitipinfo")}</spin>
                            : this.renderDrView(tabPage)}
                        </tab-pane> 
                    })}
                </tabs>
            </div>
        );
    }
}