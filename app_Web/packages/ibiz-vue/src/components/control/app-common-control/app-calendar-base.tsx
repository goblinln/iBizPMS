import { Prop, Watch,Emit } from 'vue-property-decorator';
import { CalendarControlBase } from '../../../widgets/calendar-control-base';
import FullCalendar from '@fullcalendar/vue';
import { debounce, Util } from 'ibiz-core';
import { IPSSysCalendar, IPSSysCalendarItem } from '@ibiz/dynamic-model-api';

/**
 * 应用日历部件基类
 *
 * @export
 * @class AppCalendarBase
 * @extends {CalendarControlBase}
 */
export class AppCalendarBase extends CalendarControlBase{

    /**
     * 部件动态参数
     *
     * @memberof AppCalendarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppCalendarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppCalendarBase
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
     * @memberof AppCalendarBase
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
     * @memberof AppCalendarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}


    /**
     * TODO绘制日历项
     * 
     * @memberof AppCalendarBase
     */
    public renderCalendarItems() {
        const calendarItems: Array<IPSSysCalendarItem> = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems.length>1) {
            return calendarItems.map((item: IPSSysCalendarItem) => {
                if (!item.itemType) {
                    return null;
                }
                const itemClass = {
                    "event-lengend" : true,
                    [item.itemType]: true,
                    "event-disabled": !this.isShowlegend[item.itemType]
                }
                return (
                    <div class={itemClass} on-click={() => { debounce(this.legendTrigger,[item.itemType as string],this);}}>
                        <div class="lengend-icon" style={"background: "+ item.bKColor}></div>
                        <span style={"color:" + item.color}>{item.name}</span>
                    </div>
                );
            })
        }
    }

    /**
     * 绘制日历内容
     * 
     * @memberof AppCalendarBase
     */
    public renderContent() {
        let _this: any = this;
        if(this.ctrlParams){
            return (
                <app-calendar-timeline
                    ref="appCalendarTimeline"
                    ctrlParams={this.ctrlParams}
                    context={Util.deepCopy(this.context)}
                    viewparams={Util.deepCopy(this.viewparams)}
                    on-eventClick={(tempEvent: any) => {
                        debounce(this.onEventClick,[tempEvent,true],this);
                    }}
                    events={this.searchEvents}>
                </app-calendar-timeline>
            )
        }
        return ([
            <FullCalendar
                ref={_this.controlInstance?.codeName}
                locale={_this.$i18n?.locale}
                height="parent"
                firstDay={1}
                eventLimit={true}
                editable={!this.isSelectFirstDefault && true}
                buttonText={this.buttonText}
                header={this.header}
                plugins={this.calendarPlugins}
                events={this.searchEvents}
                customButtons={this.customButtons}
                validRange={this.validRange}
                defaultDate={this.defaultDate}
                eventRender={this.eventRender}
                navLinks={this.quickToolbarItems?.length>0 ? true : false}
                navLinkDayClick={(...params: any[]) => debounce(this.onDayClick,params,this)}
                on-dateClick={(...params: any[]) => debounce(this.onDateClick,params,this)}
                on-eventClick={(...params: any[]) => debounce(this.onEventClick,params,this)}
                on-eventDrop={this.onEventDrop}
                defaultView={this.defaultView}
                >
            </FullCalendar>,
            <modal 
                v-model={this.modalVisible}
                width="250px"
                title={this.$t('app.calendar.dateselectmodaltitle')}
                class-name="date-select-model"
                on-on-ok={() => this.gotoDate()} >
                    <el-date-picker style="width: 200px" v-model={this.selectedGotoDate} type="date"></el-date-picker>
            </modal>
        ])
    }

    /**
     * TODO 绘制批操作工具栏
     * 
     * @memberof AppCalendarBase
     */
    public renderBatchToolbar() {

    }

    /**
     * 绘制数据项面板
     * 
     * @param item 数据项
     * @memberof AppCalendarBase
     */
     public renderItemPanel(item: any,calendarItem: any) {
      let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(calendarItem.getPSLayoutPanel());
      Object.assign(targetCtrlParam.dynamicProps,{ "inputData": item })
      return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref:item.id, on: targetCtrlEvent });
    }

    /**
     * 绘制数据项
     * 
     * @param item 数据项
     * @param index 下标
     * @memberof AppCalendarBase
     */
     public renderTimeLineItem(item: any, index: number) {
      const calendarItem = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems()?.find((_item: any) => {
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
                              native-on-click={(...params: any[]) => debounce(this.onEventClick,params,this)}
                              class={item.className}>
                                  {calendarItem && calendarItem.getPSLayoutPanel() ? 
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
     * 绘制
     * 
     * @memberof AppCalendarBase
     */
    public render() {
        if(!this.controlIsLoaded){
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        if (Object.is(this.controlInstance.calendarStyle,"TIMELINE")) {
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
                      <span class="app-data-empty">{this.$t('app.commonwords.nodata')}</span>
                      <span class="quick-toobar">
                          {this.renderQuickToolbar()}
                      </span>
                  </div>
              );
          }
        }else {
          return (
            <div class={{...controlClassNames,[this.calendarClass]: true}}>
                <context-menu-container>
                    <div class="event-legends">
                        {this.renderCalendarItems()}
                    </div>
                    {this.renderContent()}
                </context-menu-container>
                {this.renderBatchToolbar()}
            </div>
          );
        }
    }
}