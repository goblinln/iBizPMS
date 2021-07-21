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
        if (this.pickupViewPanelInstance) {
            return this.renderPickupViewPanel();
        } else {
            return this.renderDefaultNavView();
        }
    }

    /**
     * 绘制选择视图面板
     * 
     * @memberof AppTreeExpBarBase
     */
    public renderPickupViewPanel() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.pickupViewPanelInstance);
        if (!this.cacheUUID) {
            this.cacheUUID = Util.createUUID();
        }
        Object.assign(targetCtrlParam.dynamicProps, {
            selectedData: this.dynamicProps?.selectedData,
            context: this.selection?.context,
            viewparams: this.selection?.viewparam
        })
        Object.assign(targetCtrlParam.staticProps,{
            isSingleSelect: this.staticProps?.isSingleSelect,
            isShowButton: this.staticProps?.isShowButton,
            viewMode: 1
        })
        return this.$createElement(targetCtrlName, {
            key: this.cacheUUID,
            props: targetCtrlParam,
            ref: this.pickupViewPanelInstance?.name,
            on: targetCtrlEvent 
        });
    }

    /**
     * 绘制默认导航视图
     * 
     * @memberof AppTreeExpBarBase
     */
    public renderDefaultNavView() {
        if(this.selection?.view?.viewname){
            // 如果不是在拖拽状态获取新的UUID,如果在拖拽状态则使用拖拽前的UUID，防止拖拽刷新
            if (!this.cacheUUID || this.cacheUUID.indexOf(this.selection.context.viewpath) == -1) {
                this.cacheUUID = this.selection.context.viewpath + Util.createUUID();
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
            const allTreeNodes = this.$xDataControl.getPSDETreeNodes() || [];
            let placeholders: string[] = [];
            if(allTreeNodes?.length>0 ) {
                allTreeNodes.forEach((node: IPSDETreeNode) => {
                    if (Object.is(node.treeNodeType,"DE")) {
                        const quickSearchFields: Array<IPSAppDEField> = node.getPSAppDataEntity()?.getQuickSearchPSAppDEFields() || [];
                        if (quickSearchFields.length > 0) {
                            quickSearchFields.forEach((field: IPSAppDEField) => {
                                const _field = node.getPSAppDataEntity()?.findPSAppDEField(field.codeName);
                                if (_field) {
                                    placeholders.push(this.$tl(_field.getLNPSLanguageRes()?.lanResTag, _field.logicName));
                                }
                            })
                        }
                    }
                })
            }
            placeholders = [...new Set(placeholders)];
            placeholders.forEach((_placeholder: string, index: number) => {
                placeholder += _placeholder + (index == placeholders.length-1 ? '' : '，')
            })
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
                on-on-move-end={this.onSplitChange.bind(this)}>
                    {this.renderContent(otherClassNames)}
            </split>
        );
    }
}