import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TreeControlBase } from '../../../widgets';

/**
 * 树视图部件基类
 *
 * @export
 * @class AppTreeViewBase
 * @extends {TreeControlBase}
 */
export class AppTreeViewBase extends TreeControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppTreeViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppTreeViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeViewBase
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
     * @memberof AppTreeViewBase
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
     * @memberof AppTreeViewBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppTreeViewBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制右击菜单
     *
     * @param {*} node
     * @returns
     * @memberof AppTreeViewBase
     */
    public renderContextMenu(node: any) {
        if (node && node.data) {
            const data: any = JSON.parse(JSON.stringify(node.data));
            this.currentselectedNode = { ...data };
            const tags: string[] = data.id.split(';');
            let treeNode = this.controlInstance.getTreeNodeByNodeType(tags[0]);
            let contextMenu = this.controlInstance.getControl(treeNode?.getPSDEContextMenu?.name);
            if(contextMenu){
                let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(contextMenu);
                targetCtrlParam.dynamicProps.contextMenuActionModel = this.copyActionModel;
                return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: contextMenu.name, on: targetCtrlEvent });
            }
        }
        return null;
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppTreeViewBase
     */
    public renderNode( {node, data} : any): any {
        // 绘制图标
        let iconElement = null;
        if(data.iconcls){
            iconElement = <i class={data.iconcls}></i>
        }else if(data.icon){
            iconElement = <img src={data.icon} />
        }else if(this.controlInstance.outputIconDefault){
            iconElement = <icon type="ios-paper-outline"></icon>
        }

        // 绘制显示文本
        let textElement = null;
        if(data.html){
            textElement = <span domPropsInnerHTML={data.html}></span>;
        }else{
            textElement = <span>{ data.isUseLangRes ? this.$t(data.text) : data.text}</span>
        }
        
        // 计数器 
        let nodeCount:any = undefined;
        if(this.controlInstance.getPSSysCounterRef?.id){
            let counterService= Util.findElementByField(this.counterServiceArray, 'id', this.controlInstance.getPSSysCounterRef.id)?.service;
            nodeCount = counterService?.counterData?.[data.counterId];
        }

        return (
            <context-menu
                ref={data.id}
                isBlocked={true}
                contextMenuStyle={{ width: '100%' }}
                data={node}
                renderContent={this.renderContextMenu.bind(this)}
                on-showContext={(e: any) => {
                    this.showContext(data, e);
                }}
            >
                <tooltip transfer style='width: 100%;' max-width={2000} placement='right'>
                    <div
                        class='tree-node'
                        on-dblclick={() => {
                            this.doDefaultAction(node);
                        }}
                    >
                        <span class='icon'>{iconElement}&nbsp;</span>
                        <span class='text'>
                            {textElement}
                        </span>
                        <badge count={nodeCount} type="primary" showZero={data.counterMode !== 1}></badge>
                    </div>
                    <template slot='content'>
                        {textElement}
                    </template>
                </tooltip>
            </context-menu>
        );
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppTreeViewBase
     */
    public render(): any {
        if(!this.controlIsLoaded || !this.inited){
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const { name } = this.controlInstance;
        return (
            <div class={{ 'design-tree-container': true, ...controlClassNames }}>
                <context-menu-container>
                    <el-tree
                        ref={name}
                        class='design-tree'
                        node-key='id'
                        lazy
                        show-checkbox={!this.isSingleSelect}
                        check-on-click-node={!this.isSingleSelect}
                        default-expanded-keys={this.expandedKeys}
                        props={{
                            label: 'text',
                            isLeaf: 'leaf',
                            children: 'children',
                        }}
                        load={this.load.bind(this)}
                        highlight-current={true}
                        expand-on-click-node={false}
                        on-check={this.onCheck.bind(this)}
                        on-current-change={this.selectionChange.bind(this)}
                        filter-node-method={this.filterNode.bind(this)}
                        empty-text='没有数据'
                        scopedSlots={{
                            default: this.renderNode.bind(this)
                        }}
                    ></el-tree>
                </context-menu-container>
            </div>
        );
    }
}
