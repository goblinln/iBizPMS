import { Emit, Prop, Watch } from 'vue-property-decorator';
import { TreeGridExControlBase } from '../../../widgets';
import { Util } from 'ibiz-core';

/**
 * 树表格部件基类
 *
 * @export
 * @class AppTreeGridExBase
 * @extends {TreeGridExControlBase}
 */
export class AppTreeGridExBase extends TreeGridExControlBase {
    /**
      * 部件动态参数
      *
      * @memberof AppTreeGridExBase
      */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppTreeGridExBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeGridExBase
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
     * @memberof AppTreeGridExBase
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
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppTreeGridExBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 销毁视图回调
     *
     * @memberof AppTreeGridExBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppTreeGridExBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { name } = this.controlInstance;
        const { controlClassNames } = this.renderOptions;
        return (
            <div class={{ ...controlClassNames, 'tree-grid-ex': true }} style="height:100%">
                <el-table
                    ref={name}
                    data={this.items}
                    row-key="id"
                    border
                    lazy
                    height="100%"
                    row-class-name={this.setRowClass.bind(this)} 
                    load={this.loadTreeNode.bind(this)}
                    tree-props={{ children: 'children', hasChildren: 'leaf' }}
                    select-on-indeterminate={this.isSingleSelect}
                    highlight-current-row={this.isSingleSelect}
                    on-current-change={this.select.bind(this)}>
                    <template slot="empty">
                    {this.$t('app.commonwords.nodata')}
                    </template>
                    { this.renderColumns()}
                </el-table>
            </div>
        );
    }
}
