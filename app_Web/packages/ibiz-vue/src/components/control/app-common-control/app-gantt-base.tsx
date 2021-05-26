import { GanttControlBase } from '../../../widgets';
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';

/**
 * 甘特部件基类
 *
 * @export
 * @class AppGanttBase
 * @extends {GanttControlBase}
 */
export class AppGanttBase extends GanttControlBase {
    /**
     * 部件动态参数
     *
     * @memberof AppGanttBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppGanttBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGanttBase
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
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGanttBase
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
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppGanttBase
     */
     @Emit('ctrl-event')
     public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }    

    /**
     * 销毁视图回调
     *
     * @memberof AppGanttBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 绘制
     * 
     * @memberof AppGanttBase
     */
    public render() {
        const { controlClassNames } = this.renderOptions;
        return (
            <div class={{...controlClassNames, "app-gantt": true}}>
                {this.tasks.length > 0 ? 
                    <gantt-elastic 
                        class="gantt"
                        tasks={this.tasks}
                        options={this.options}
                        dynamic-style={this.dynamicStyle}
                        on-taskList-item-dblclick={($event: any) => this.taskClick($event)}
                        on-task-item-expand={(task: any) => this.taskItemExpand(task)}>
                    </gantt-elastic> : 
                    <div class="app-data-empty">{this.$t('app.commonWords.noData')}</div>
                }
            </div>
        );
    }
}