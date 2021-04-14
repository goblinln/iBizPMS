import { Prop, Component, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { CalendarControlBase } from '../../../widgets/calendar-control-base';
import "./app-timeline-calendar.less";

@Component({})
export class AppTimeLineCalendar extends CalendarControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppTimeLineCalendar
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppTimeLineCalendar
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTimeLineCalendar
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
     * @memberof AppTimeLineCalendar
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
     * @memberof AppDefaultTree
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制数据项面板
     * 
     * @param item 数据项
     * @memberof AppTimeLineCalendar
     */
    public renderItemPanel(item: any,calendarItem: any) {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(calendarItem.getPSLayoutPanel);
        Object.assign(targetCtrlParam.dynamicProps,{ "inputData": item })
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref:item.id, on: targetCtrlEvent });
    }

    /**
     * 绘制数据项
     * 
     * @param item 数据项
     * @param index 下标
     * @memberof AppTimeLineCalendar
     */
    public renderTimeLineItem(item: any, index: number) {
        const calendarItem = this.controlInstance.calendarItems?.find((_item: any) => {
            return item?.itemType == _item.itemType;
        });
        return (
            <el-timeline-item
                key={`${item.title}${index}`}
                color={item.color}
                timestamp={item.start}
                placement="top">
                    <context-menu
                        contextMenuStyle={{width: '100%'}}
                        data={item}
                        renderContent={this.renderContextMenu.bind(this)}>
                            <el-card
                                native-on-click={this.onEventClick.bind(this)}
                                class={item.className}>
                                    {calendarItem && calendarItem.getPSLayoutPanel ? 
                                        this.renderItemPanel(item,calendarItem) : 
                                        <div>
                                            <h4>{item.title}</h4>
                                            <p>从 {item.start} 至 {item.end}</p>
                                        </div>}
                            </el-card>
                    </context-menu>
            </el-timeline-item>
        );
    }

    /**
     * 绘制快速工具栏
     * 
     * @memberof AppTimeLineCalendar
     */
    public renderQuickToolbar() {
        //TODO 待补充快速工具栏
        return null;
    }

    /**
     * 绘制
     * 
     * @memberof AppTimeLineCalendar
     */
    public render() {
        if(!this.controlIsLoaded) {
            return null;
        }
        if(this.events.length>0) {
            return (
                <el-timeline>
                    {this.events.map((event: any, index: number) => {
                        return this.renderTimeLineItem(event, index);
                    })}
                </el-timeline>
            );
        } else {
            return (
                <div>
                    <span class="app-data-empty">暂无数据</span>
                    <span class="quick-toobar">
                        {this.renderQuickToolbar()}
                    </span>
                </div>
            );
        }
    }

}