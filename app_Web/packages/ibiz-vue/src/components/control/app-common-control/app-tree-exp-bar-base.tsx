import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TreeExpBarControlBase } from '../../../widgets/tree-exp-bar-control-base';
import { IPSAppDEField, IPSDETree, IPSDETreeNode, IPSTreeExpBar } from '@ibiz/dynamic-model-api';

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
        if(this.selection.view.viewname){
            // 如果不是在拖拽状态获取新的UUID,如果在拖拽状态则使用拖拽前的UUID，防止拖拽刷新
            if (!this.dragstate) {
                this.cacheUUID = Util.createUUID();
            }
            return this.$createElement(this.selection.view.viewname,{
                key: this.cacheUUID,
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
        const title = this.$tl((this.controlInstance as IPSTreeExpBar).getTitlePSLanguageRes()?.lanResTag, this.controlInstance?.title);
        return (
            <div class="tree-exp-bar-header">
                <div class="tree-exp-bar-title">
                    <icon type="ios-home-outline"/>
                    <span>{title}</span>
                </div>
            </div>
        );
    }

     /**
     * 绘制快速搜索
     * 
     * @memberof ExpBarControlBase
     */
      public renderSearch() {
        const getQuickSearchPlaceholader = () => {
            let placeholder: any = '';
            let tree: any = this.controlInstance.getPSControls();
            if (tree && tree.length > 0) {
              tree.forEach((item: IPSDETree)=> {
                const nodes: Array<IPSDETreeNode> =  item.getPSDETreeNodes() || [];
                nodes.forEach((node: IPSDETreeNode) => {
                  if (Object.is(node.treeNodeType,"DE")) {
                    const entity = node.getPSAppDataEntity();
                    const fields: Array<IPSAppDEField> = entity?.getQuickSearchPSAppDEFields() || [];
                    fields.forEach((field: IPSAppDEField, index: number) => {
                        const _field = entity?.findPSAppDEField(field.codeName);
                        if (_field) {
                            placeholder += (this.$tl(_field.getLNPSLanguageRes()?.lanResTag, _field.logicName) + (index === fields.length-1 ? '' : ', '))
                        }
                    })
                  }
                })
              });
            }
            return placeholder;
        }
        return (
            <div class="search-container">
                <i-input
                    search={true}
                    on-on-change={($event: any) => { this.searchText = $event.target.value; }}
                    placeholder={getQuickSearchPlaceholader()}
                    on-on-search={() => this.onSearch()}>
                </i-input>
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
                ref={`${this.appDeCodeName}-${this.controlInstance.codeName?.toLowerCase()}`}
                id={this.controlInstance?.codeName?.toLowerCase()}
                class={['expbarcontrol', `app-tree-exp-bar`, this.renderOptions?.controlClassNames]}
                v-model={this.split}
                mode={this.sideBarlayout == 'LEFT' ? 'horizontal' : 'vertical'}
                on-on-move-start={()=>{this.dragstate = true}}
                on-on-move-end={this.onSplitChange.bind(this)}>
                    {this.renderContent(otherClassNames)}
            </split>
        );
    }
}