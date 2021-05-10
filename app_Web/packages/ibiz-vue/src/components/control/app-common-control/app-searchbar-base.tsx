import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { SearchBarControlBase } from '../../../widgets/searchbar-control-base';
import { IPSEditor, IPSSearchBarFilter } from '@ibiz/dynamic-model-api';

/**
 * 搜索栏部件基类
 *
 * @export
 * @class AppSearchBarBase
 * @extends {SearchBarControlBase}
 */
export class AppSearchBarBase extends SearchBarControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppSearchBarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppSearchBarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppSearchBarBase
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
     * @memberof AppSearchBarBase
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
     * @memberof AppSearchBarBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppSearchBarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制过滤栏
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppSearchBarBase
     */
    public renderBarFilters(data: any) {
        const barFilters: Array<IPSSearchBarFilter> = this.controlInstance.getPSSearchBarFilters() || [];
        if(barFilters.length === 0 || !data.editor) {
            return null;
        }
        return barFilters.map((filter: IPSSearchBarFilter) => {
            if(!filter.name || !Object.is(data.editor, filter.name)) {
                return null;
            }
            const editor: IPSEditor | null = filter.getPSEditor();
            if(editor) {
                let filterItem: any = this.filterItems.find((item: any) => {
                    return item.editor && Object.is(item.editor, editor.name);
                });
                if(filterItem) {
                    filterItem[editor.name] = data[editor.name];
                }
                return (
                    <app-default-editor
                        editorInstance={editor}
                        value={data[editor.name]}
                        contextData={data}
                        context={this.context}
                        viewparams={this.viewparams}
                        service={this.service}
                        disabled={false}
                        ignorefieldvaluechange={false}
                        on-change={this.editorChange.bind(this)}
                    />
                );
            }
        })
    }

    /**
     * 编辑器值变化
     *
     * @memberof AppSearchBarBase
     */
    public editorChange({ name, value }: any) {
        if(this.filterItems.length == 0) {
            return;
        }
        this.filterItems.forEach((item: any) => {
            if(item.editor && Object.is(name, item.editor)) {
                item[name] = value;
            }
        })
    }

    /**
     * 绘制过滤树
     *
     * @memberof AppSearchBarBase
     */
    public renderFilterTree() {
        if(!this.filterFields || this.filterFields.length==0) {
            return null;
        }
        return (
            <div class="filter-group">
                <filter-tree
                    datas={this.filterItems}
                    fields={this.filterFields}
                    scopedSlots={{
                        default: ({data}: any) => { return this.renderBarFilters(data) }
                    }}>
                </filter-tree>
            </div>
        );
    }

    /**
     * 绘制过滤存储集合
     *
     * @memberof AppSearchBarBase
     */
    public renderFilterFooter() {
        return (
            <div class="search-bar-footer">
                <div class="search-bar-action">
                    {this.historyItems?.length>0 ? 
                        <el-select
                            size="small" 
                            value={this.selectItem}
                            on-change={this.onFilterChange.bind(this)}>
                                {this.historyItems.map((item: any)=> {
                                    return (
                                        <el-option
                                            key={item.value}
                                            label={item.name}
                                            value={item.value}
                                            ></el-option>
                                    )
                                })}
                            </el-select> : null}
                    <i-button type="primary" on-click={this.onSearch.bind(this)}>搜索</i-button>
                    <i-button on-click={this.onReset.bind(this)}>重置</i-button>
                    <poptip
                        ref="propip"
                        trigger="hover"
                        transfer
                        placement="top-end"
                        title="存储自定义查询"
                        op-on-popper-show={this.openPoper.bind(this)}>
                            <i-button><i class="fa fa-floppy-o" aria-hidden="true"></i></i-button>
                            <div slot="content">
                                <i-input value={this.saveItemName} placeholder=""></i-input>
                                <div class="save-action">
                                    <i-button on-click={this.onCancel.bind(this)}>取消</i-button>
                                    <i-button type="primary" on-click={this.onOk.bind(this)}>保存</i-button>
                                </div>
                            </div>
                    </poptip>
                </div>
            </div>
        );
    }

    /**
     * 绘制搜索栏部件
     *
     * @memberof AppSearchBarBase
     */
    public render() {
        if(!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return <div class={{ ...controlClassNames, 'app-searchbar': true }}>
            {this.renderFilterTree()}
            {this.renderFilterFooter()}
        </div>
    }

}