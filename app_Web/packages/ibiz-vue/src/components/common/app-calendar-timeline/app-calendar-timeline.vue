<template>
    <div class="calendar-time-line">
        <div class="header">
            <div class="left">
                <ButtonGroup>
                    <Button v-for="(button,index) in leftButton" :key="index" size="default" type="primary" @click="buttonClick(button)">
                        <i v-if="button.icon" :class="button.icon" />
                        <Icon v-if="button.icon" :type="button.icon" />
                        <img v-if="button.imgsrc" :src="button.imgsrc"/>
                        {{button.text ? button.text : ''}}
                    </Button>
                </ButtonGroup>
                <Button v-for="(button,index) in leftCustomButton" :key="index" size="default" type="primary" :disabled="toDayDisabled(button)" @click="buttonClick(button)">
                    <i v-if="button.icon" :class="button.icon" />
                    <img v-if="button.imgsrc" :src="button.imgsrc"/>
                    {{button.text ? button.text : ''}}
                </Button>
            </div>
            <div class="center">
                <h2 class="current-date">{{currentDate}}</h2>
            </div>
            <div class="right">
                <ButtonGroup>
                    <Button v-for="(button,index) in rightButton" :key="index" :class="{'button-active-color': Object.is(defaultView, button.tag)}" size="default" type="primary" @click="buttonClick(button)">
                        <i v-if="button.icon" :class="button.icon" />
                        <img v-if="button.imgsrc" :src="button.imgsrc"/>
                        {{button.text ? button.text : ''}}
                    </Button>
                </ButtonGroup>
            </div>
        </div>
        <div class="container">
            <Split v-model="split" mode="horizontal" ref="calendar-time-line">
                <div slot="left" :class="['group',defaultView]">
                    <div class="group-header">
                        <div>{{title}}</div>
                    </div>
                    <div class="group-items">
                        <div v-for="(group, index) in currentGroupSchedule" :key="index" class="group-item" :style="group.style">
                            <span>{{group.name}}</span>
                        </div>
                    </div>
                </div>
                <div slot="right" class="main-container">
                    <div v-for="(date, index) in currentTimeQuantum" :key="index" class="time-line">
                        <div v-if="Object.is(defaultView, 'dayview')" class="dayview">
                            <div class="dayview-header">
                                <div class="date">{{date}}</div>
                            </div>
                            <div class="empty-space">
                                <div>
                                    <div v-for="(group, index) in currentGroupSchedule" :key="index" class="cell" :style="group.style"></div>
                                </div>
                                <div>
                                    <div v-for="(group, index) in currentGroupSchedule" :key="index" class="cell" :style="group.style"></div>
                                </div>
                            </div>
                        </div>
                        <div v-else-if="Object.is(defaultView, 'weekview')" class="weekview">
                            <div class="weekviewr-header">
                                <div class="date-header">
                                    <div class="date">{{date}}</div>
                                </div>
                                <div class="hours">
                                    <div v-for="(hour,_index) in hours" :key="_index" class="hour">
                                        <div class="hour-header">
                                            <div class="title">{{hour}}</div>
                                        </div>
                                        <div class="empty-space">
                                            <div>
                                                <div v-for="(group, index) in currentGroupSchedule" :key="index" class="cell" :style="group.style"></div>
                                            </div>
                                            <div>
                                                <div v-for="(group, index) in currentGroupSchedule" :key="index" class="cell" :style="group.style"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div v-else-if="Object.is(defaultView, 'monthview')" class="monthview">
                            <div class="monthview-header">
                                <div class="date">{{date}}</div>
                            </div>
                            <div class="empty-space">
                                <div>
                                    <div v-for="(group, index) in currentGroupSchedule" :key="index" class="cell" :style="group.style"></div>
                                </div>
                                <div>
                                    <div v-for="(group, index) in currentGroupSchedule" :key="index" class="cell" :style="group.style"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div :class="{'calendar-chart-day': Object.is(defaultView, 'dayview'), 'calendar-chart-week': Object.is(defaultView, 'weekview'), 'calendar-chart-month': Object.is(defaultView, 'monthview')}"
                        :style="{'width': Object.is(defaultView, 'monthview') ? monthWidth + 'px' : false}"
                        >
                        <div v-for="(group, index) in currentGroupSchedule" :key="index" class="group-chart" :style="group.style">
                            <div v-for="(item, _index) in group.items" :key="_index" class="chart" :style="item.style" @click="calendarItemClick(item)" :title="item.title">
                                {{ item.title }}
                            </div>
                        </div>
                    </div>
                </div>
            </Split>
        </div>
        <modal 
            v-model="modalVisible"
            width="250px"
            title="跳转日期"
            class-name="date-select-model"
            @on-ok="confirmJump()" >
                <el-date-picker style="width: 200px" v-model="selectedGotoDate" type="date"></el-date-picker>
        </modal>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { CodeListService } from "ibiz-service";
import { Util } from 'ibiz-core';
import moment from 'moment';

@Component({})
export default class AppClaendarTimeline extends Vue{

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppClaendarTimeline
     */  
    public codeListService: CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 部件动态参数
     * 
     * @type {any}
     * @memberof AppClaendarTimeline 
     */
    @Prop()
    public ctrlParams!: any;

    /**
     * 自定义按钮集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    @Prop()
    public customButtons?: any[];

    /**
     * 日程事件
     * 
     * 
     */
    @Prop()
    public events!: any;

    /**
     * 分组属性
     *
     * @type {any}
     * @memberof AppClaendarTimeline
     */    
    public groupField: string = 'itemType';

    /**
     * 分组模式
     *
     * @type {any}
     * @memberof AppClaendarTimeline
     */    
    public groupMode: any = 'AUTO';

    /**
     * 分组代码表
     *
     * @type {any}
     * @memberof AppClaendarTimeline
     */    
    public groupCodelist: any;

    /**
     * 所有日程事件集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    public schedule: any[] = [];

    /**
     * 筛选及格式化后的日程事件集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    private _schedule: any[] = [];
    
    /**
     * 左侧自定义按钮集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    public leftCustomButton: any[] = [];

    /**
     * 一天的时间段
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    public hours: any[] = [
        '00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00',
        '08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00',
        '16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00',
    ]

    /**
     * 左侧预定义按钮集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    public leftPredefineButton: any[] = [
        {
            text: this.$t('app.calendar.today'),
            icon: '',
            imgsrc: '',
            tag: 'today',
            disabled: false,
        },
        {
            text: this.$t('app.calendar.gotodate'),
            icon: '',
            imgsrc: '',
            tag: 'jump',
            disabled: false,
        }
    ];

    /**
     * 左侧按钮集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    public leftButton: any[] = [
        {
            text: '',
            icon: 'ios-arrow-back',
            imgsrc: '',
            tag: 'previous',
        },
        {
            text: '',
            icon: 'ios-arrow-forward',
            imgsrc: '',
            tag: 'next',
        }
    ];

    /**
     * 右侧按钮集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    public rightButton: any[] = [
        {
            text: this.$t('app.calendar.day'),
            icon: '',
            imgsrc: '',
            tag: 'dayview',
        },
        {
            text: this.$t('app.calendar.week'),
            icon: '',
            imgsrc: '',
            tag: 'weekview',
        },
        {
            text: this.$t('app.calendar.month'),
            icon: '',
            imgsrc: '',
            tag: 'monthview',
        },
    ];

    /**
     * 默认视图样式
     * 
     * @type {string}
     * @memberof AppClaendarTimeline
     */
    public defaultView: string = 'dayview';

    /**
     * 当前页面分组日程集合
     * 
     * @type {any[]}
     * @memberof AppClaendarTimeline
     */
    public currentGroupSchedule: any[] = [];

    /**
     * 分组高度
     * 
     * @type {number}
     * @memberof AppClaendarTimeline
     */
    public groupHeight: number = 0;

    /**
     * 默认面板位置
     *
     * @type {number}
     * @memberof AppClaendarTimeline
     */
    public split: number = 0.2;

    /**
     * 默认分组名称
     *
     * @type {string}
     * @memberof AppClaendarTimeline
     */    
    public title: string = '分组';

    /**
     * 模态显示控制变量
     *
     * @type {boolean}
     * @memberof AppClaendarTimeline
     */
    public modalVisible: boolean = false;

    /**
     * 当前时间
     * 
     * @type {Date}
     * @memberof AppClaendarTimeline
     */
    public currentTime: any = new Date();

    /**
     * 跳转日期
     *
     * @public
     * @type Date
     * @memberof AppClaendarTimeline
     */
    public selectedGotoDate: any = new Date();

    /**
     * 当天时间
     * 
     * @memberof AppClaendarTimeline
     */
    get currentDay() {
        return {
            start: moment(this.currentTime).startOf('day'),
            end: moment(this.currentTime).endOf('day'),
        }
    }

    /**
     * 当周时间
     * 
     * @memberof AppClaendarTimeline
     */
    get currentWeek() {
        return{
            start: moment(this.currentTime).startOf('week'),
            end: moment(this.currentTime).endOf('week'),
        }
    }

    /**
     * 当月时间
     * 
     * @memberof AppClaendarTimeline
     */
    get currentMonth() {
        return {
            start: moment(this.currentTime).startOf('month'),
            end: moment(this.currentTime).endOf('month'),
        }
    }

    /**
     * 获取当月视图宽度
     * 
     * @memberof AppClaendarTimeline
     */
    get monthWidth(){
        const startTime = this.currentMonth.start;
        const endTime = this.currentMonth.end;
        const width = (Math.abs(endTime.diff(startTime, 'day')) + 1) * 48;
        return width;
    }

    /**
     * 获取标题时间
     * 
     * @memberof AppClaendarTimeline 
     */
    get currentDate(): any {
        if (Object.is(this.defaultView, "monthview")) {
            return this.currentMonth.start.format('YYYY年MM月');
        } else if (Object.is(this.defaultView, "weekview")) {
            let endTime: any = this.currentWeek.start.get('date') < this.currentWeek.end.get('date') ? this.currentWeek.end.format('DD日') : this.currentWeek.end.format('MM月DD日')
            return this.currentWeek.start.format('YYYY年MM月DD日') + '-' + endTime;
        }
        return this.currentDay.start.format('YYYY年MM月DD日');
    }

    /**
     * 获取当前周或月的时间表
     * 
     * @memberof AppClaendarTimeline 
     */
    get currentTimeQuantum(): any[] {
        let timeQuantum: any[] = [];
        let startTime: any;
        let endTime: any;
        let diffTime: number = 0;
        if (Object.is(this.defaultView, "monthview")) {
            startTime = this.currentMonth.start;
            endTime = this.currentMonth.end;
        } else if (Object.is(this.defaultView, "weekview")) {
            startTime = this.currentWeek.start;
            endTime = this.currentWeek.end;
        } else if (Object.is(this.defaultView, "dayview")) {
            startTime = this.currentDay.start;
            endTime = this.currentDay.end;
        }
        diffTime = Math.abs(endTime.diff(startTime, 'day'));
        timeQuantum = this.calculateTimeQuantum(startTime, diffTime);
        return timeQuantum;
    }
    
    /**
     * 监听部件动态参数
     * 
     * @memberof AppClaendarTimeline  
     */
    @Watch('ctrlParams',{
        deep: true,
        immediate: true
    })
    public ctrlParamsChange(newVal: any, oldVal: any) {
        if (newVal) {
            this.parseCtrlParams(newVal);
        }
    }
    
    /**
     * Vue 生命周期
     * 
     * @memberof AppClaendarTimeline 
     */
    public created() {
        this.leftCustomButton = [];
        if (this.customButtons && this.customButtons.length > 0) {
            this.leftCustomButton = this.customButtons;
        } else {
            this.leftCustomButton = this.leftPredefineButton;
        }
        this.searchEvents();
    }

    /**
     * 搜索日程
     * 
     * @memberof AppClaendarTimeline 
     */
    public searchEvents(){
        if(this.events && this.events instanceof Function){
            this.events({},(filterEvents: any)=>{
                this.schedule = filterEvents;
                this.clearDirtyData(this.schedule);
                this.partitionTime();
            });
        }
    }

    /**
     * 解析部件动态参数
     * 
     * @param ctrlParams 部件动态参数
     * @memberof AppClaendarTimeline 
     */
    public parseCtrlParams(ctrlParams: any){
        for (let param in ctrlParams) {
            if (ctrlParams[param]) {
                if (Object.is("GROUPFIELD", param)) {
                    this.groupField = ctrlParams[param];
                } else if (Object.is("GROUPMODE", param)) {
                    this.groupMode = ctrlParams[param];
                } else if (Object.is("GROUPCODELIST", param)) {
                    this.groupCodelist = eval('(' + ctrlParams[param] + ')');
                } else if (Object.is("WIDTH", param)) {
                    this.$nextTick(() => {
                        const splitDom: any = this.$refs["calendar-time-line"];
                        const containerWidth: number = splitDom?.$el?.offsetWidth;
                        const ctrlWidth = parseInt(ctrlParams[param]);
                        if (ctrlWidth && containerWidth) {
                            this.split = ctrlWidth / containerWidth;
                        }
                    })
                } else if (Object.is("GROUPTITLE", param)) {
                    this.title = ctrlParams[param];
                }
            }    
        }
    }

    /**
     * 清除脏数据,根据图例显示控制及日期数据格式化
     * 去除(start > end 的数据)
     * 
     * @param schedule 所有日程事件集合
     * @memberof AppClaendarTimeline
     */
    public clearDirtyData(schedule: any[]) {
        let date: any[] = [];
        this._schedule = [];
        schedule.forEach((item: any) => {
            if(moment(item.start).isBefore(item.end)){
                // 放入日程事件的拷贝，防止污染源数据
                date.push(Util.deepCopy(item));
            }
        });
        date.forEach((item: any) => {
            item.start = moment(item.start).format('YYYY-MM-DD HH:mm:ss');
            item.end = moment(item.end).format('YYYY-MM-DD HH:mm:ss');
        });
        this._schedule = date;
    }

    /**
     * 日程时间分割
     * 
     * @param schedule 日程事件集合
     * @memberof AppClaendarTimeline
     */
    public partitionTime() {
        let currentSchedule: any[] = [];
        let startTime: any;
        let endTime: any;
        if (Object.is(this.defaultView, 'monthview')) {
            startTime = this.currentMonth.start;
            endTime = this.currentMonth.end;
        } else if (Object.is(this.defaultView, 'weekview')) {
            startTime = this.currentWeek.start;
            endTime = this.currentWeek.end;
        } else if (Object.is(this.defaultView, 'dayview')) {
            startTime = this.currentDay.start;
            endTime = this.currentDay.end;
        }
        // 切割时间时，传入格式化数据的拷贝，防止格式化后的数据被污染;
        currentSchedule = this.getCurrentDate(Util.deepCopy(this._schedule), startTime, endTime); 
        this.scheduleGroup(currentSchedule);
    }

    /**
     * 获取当前时间的日程事件
     * 
     * @param schedule 清除脏数据及格式化日期后的日程事件集合
     * @param startTime 当前开始时间
     * @param endTime 当前结束时间
     * @memberof AppClaendarTimeline
     */
    public getCurrentDate(schedule: any[], startTime: any, endTime: any): any[] {
        let date: any[] = [];
        schedule.forEach((item: any) => {
            if (moment(startTime).isSameOrBefore(item.start) && moment(item.end).isSameOrBefore(endTime)) {
                // 开始和结束日期都在当前时间之内
                let diffTime: number = Math.abs(startTime.diff(item.start, 'day'));
                if (Object.is(this.defaultView, 'dayview')) {
                    item.start = moment(item.start).get('hours') * 60 + moment(item.start).get('minute');
                    item.end = moment(item.end).get('hours') * 60 + moment(item.end).get('minute');
                } else if (Object.is(this.defaultView, 'weekview')) {
                    item.start =  diffTime * 1440 + moment(item.start).get('hours') * 60 + moment(item.start).get('minute');
                    item.end = diffTime * 1440 + moment(item.end).get('hours') * 60 + moment(item.end).get('minute');
                } else if (Object.is(this.defaultView, 'monthview')) {
                    item.start =  diffTime * 48 + moment(item.start).get('hours') * 2;
                    item.end = diffTime * 48 + moment(item.end).get('hours') * 2;
                }
                date.push(item);
            } else if (moment(item.start).isBefore(startTime) && moment(endTime).isBefore(item.end)) {
                // 开始在当前时间之前，结束在当前时间之后
                if (Object.is(this.defaultView, 'dayview')) {
                    item.start = 0;
                    item.end = 24 * 60;
                } else if (Object.is(this.defaultView, 'weekview')) {
                    item.start = 0;
                    item.end = 1440 * 7;
                } else if (Object.is(this.defaultView, 'monthview')) {
                    item.start = 0;
                    item.end = 48 * Math.abs(endTime.diff(startTime,'day'));
                }
                date.push(item);
            } else if (moment(startTime).isSameOrBefore(item.start) && moment(item.start).isSameOrBefore(endTime) && moment(endTime).isBefore(item.end)) {
                // 开始在当前时间之内，结束在当前时间之后
                let diffTime: number = Math.abs(startTime.diff(item.start, 'day'));
                if (Object.is(this.defaultView, 'dayview')) {
                    item.start = moment(item.start).get('hours') * 60 + moment(item.start).get('minute');
                    item.end = 24 * 60;
                } else if (Object.is(this.defaultView, 'weekview')) {
                    item.start =  diffTime * 1440 + moment(item.start).get('hours') * 60 + moment(item.start).get('minute');
                    item.end = 1440 * 7;
                } else if (Object.is(this.defaultView, 'monthview')) {
                    item.start =  diffTime * 48 + moment(item.start).get('hours') * 2;
                    item.end = 48 * Math.abs(endTime.diff(startTime,'day'));
                }
                date.push(item);
            } else if (moment(item.start).isBefore(startTime) && moment(startTime).isSameOrBefore(item.end) && moment(item.end).isSameOrBefore(endTime)) {
                // 开始在当前时间之前，结束在当前时间之内
                let diffTime: number = Math.abs(startTime.diff(item.end, 'day'));
                if (Object.is(this.defaultView, 'dayview')) {
                    item.start = 0;
                    item.end = moment(item.end).get('hours') * 60 + moment(item.end).get('minute');
                } else if (Object.is(this.defaultView, 'weekview')) {
                    item.start = 0;
                    item.end = diffTime * 1440 + moment(item.end).get('hours') * 60 + moment(item.end).get('minute');
                } else if (Object.is(this.defaultView, 'monthview')) {
                    item.start = 0;
                    item.end = diffTime * 48 + moment(item.end).get('hours') * 2;
                }
                date.push(item);
            }
        });
        return date;
    }

    /**
     * 日程分组
     * 
     * @param schedule 当前页日程集合
     * @memberof AppClaendarTimeline
     */
    public async scheduleGroup(schedule: any[]) {
        let data: any[] = [];
        if (Object.is(this.groupMode, 'AUTO')) {
            schedule.forEach((item: any) => {
                let groupField: any;
                if (item.hasOwnProperty(this.groupField)) {
                    groupField = this.groupField;
                } else if (item.hasOwnProperty('content')) {
                    groupField = 'content';
                }
                if (groupField) {
                    let group: any = data.find((group: any) => Object.is(group.name, item[groupField]));
                    if (!group) {
                        data.push({
                            name: item[groupField],
                            value: item[groupField],
                            style: {},
                            items: this.getGroupItems(item[groupField], schedule),
                        })
                    }
                }
            });
        }
        if (Object.is(this.groupMode, 'CODELIST') && this.groupCodelist) {
            let codelistItems: any = await this.codeListService.getDataItems(this.groupCodelist);
            if (codelistItems && codelistItems.length > 0) {
                codelistItems.forEach((item: any) => {
                    data.push({
                        name: item.text,
                        value: item.value,
                        style: {},
                        items: this.getGroupItems(item.value, schedule),
                    })
                });
            }
        }
        this.currentGroupSchedule = this.getGroupChart(data);
    }

    /**
     * 设置分组集合
     *
     * @param {string} value 分组值
     * @param {any[]} schedule 分组目标日程集合
     * @memberof AppClaendarTimeline
     */
    public getGroupItems(value: string, schedule: any[]) {
        let datas: any = [];
        schedule.forEach((item: any) => {
            if (item.hasOwnProperty(this.groupField)) {
                if (Object.is(item[this.groupField], value)) {
                    datas.push(item);
                }
            } else if (item.hasOwnProperty('content')) {
                if (Object.is(item['content'], value)) {
                    datas.push(item);
                }
            }
        })
        return datas;
    }

    /**
     * 获取分组日历项(样式计算)
     * 
     * @param {any[]} schedule 当前页分组日程集合
     * @memberof AppClaendarTimeline
     */
    public getGroupChart(groupSchedule: any[]) {
        if(groupSchedule && groupSchedule.length > 0){
            groupSchedule.forEach((group: any) => {
                this.groupHeight = 35;
                if(group.items && group.items.length > 0){
                    group.items.forEach((item: any, index:number) => {
                        item.style={
                            left: item.start + 'px',
                            width: (item.end - item.start) + 'px',
                            height: '20px',
                            top: this.calculateTop(group.items,index)+'px',
                            backgroundColor: item.color,
                            color: item.textColor
                        }
                    });
                }
                Object.assign(group.style,{
                    height: this.groupHeight + 'px',
                })
            });
        }
        return groupSchedule;
    }

    /**
     * 计算高度位置
     * 
     * @param {items} items 所需计算的日历分组
     * @param {any} index 所需计算的日历项编号
     * @memberof AppClaendarTimeline 
     */
    public calculateTop(items: any[], index: any) {
        let top: number = 2;
        if (index > 0) {
            let overlapTime: any[] = [];
            items.forEach((item: any, _index: number) => {
                if (_index < index) {
                    if (item.start <= items[index].end && item.end >= items[index].start) {
                        overlapTime.push(item);
                    }
                }
            })
            if (overlapTime.length > 0) {
                overlapTime = this.sort(overlapTime);
                const maxTop = overlapTime[overlapTime.length - 1].style.top; 
                top = top + 20 + parseInt(maxTop.substring(0,maxTop.length - 2));
            }
        }
        this.groupHeight = 33 + top;
        return top;
    }

    /**
     * 日历项高度排序
     * 
     * @param {any} arr 需要排序的日历项数组
     * @memberof AppClaendarTimeline 
     */
    public sort(arr: any) {
        if (arr instanceof Array && arr.length > 0) {
            for (var i = 0; i < arr.length - 1; i++) {
                for (var j = 0; j < arr.length - 1 - i; j++) {
                    const previous: any = arr[j].style.top;
                    const next: any =arr[j + 1].style.top;
                    if (parseInt(previous.substring(0,previous.length - 2)) > parseInt(next.substring(0,next.length - 2))) {
                        var temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
        return arr;
    }

    /**
     * 按钮点击
     * 
     * @param button 按钮对象
     * @memberof AppClaendarTimeline
     */
    public buttonClick(button: any) {
        switch(button.tag){
            case 'dayview':
                this.defaultView = 'dayview';
            break
            case 'weekview':
                this.defaultView = 'weekview';
            break
            case 'monthview':
                this.defaultView = 'monthview';
            break
            case 'previous':
                if (Object.is(this.defaultView, 'dayview')) {
                    this.currentTime = moment(this.currentTime).subtract(1, 'day');
                } else if (Object.is(this.defaultView, 'weekview')) {
                    this.currentTime = moment(this.currentTime).week(moment(this.currentTime).week() - 1).startOf('week');
                } else if (Object.is(this.defaultView, 'monthview')) {
                    this.currentTime = moment(this.currentTime).month(moment(this.currentTime).month() - 1).startOf('month');
                }
            break
            case 'next':
                if (Object.is(this.defaultView, 'dayview')) {
                    this.currentTime = moment(this.currentTime).add(1, 'day');
                } else if (Object.is(this.defaultView, 'weekview')) {
                    this.currentTime = moment(this.currentTime).week(moment(this.currentTime).week() + 1).startOf('week');
                } else if (Object.is(this.defaultView, 'monthview')) {
                    this.currentTime = moment(this.currentTime).month(moment(this.currentTime).month() + 1).startOf('month');
                }
            break
            case 'today':
                this.currentTime = new Date();
            break
            case 'jump':
                this.modalVisible = true;
            break
        }
        this.partitionTime();
    }

    /**
     * 计算视图呈现时间段(即从开始时间到结束时间)
     * 
     * @param startTime 开始时间
     * @param diffTime 时间差
     * @memberof AppClaendarTimeline
     */
    public calculateTimeQuantum(startTime: any, diffTime: any): any[] {
        let timeQuantum: any[] = [];
        if (Object.is(this.defaultView, 'dayview')) {
            timeQuantum = this.hours;
        } else {
            const arrs: any[] = []
            for (let i = 0; i <= diffTime; i++) {
                //每次重新初始化开始时间，深拷贝的问题
                const new_start_time = moment(startTime);
                arrs.push(new_start_time.add(i, "day"));
            }
            if (Object.is(this.defaultView, 'monthview')) {
                arrs.forEach((arr: any) => {
                    const item: any = arr.format('D') + '   ' + arr.format('dd');
                    timeQuantum.push(item);
                })
            } else if (Object.is(this.defaultView, 'weekview')) {
                arrs.forEach((arr: any) => {
                    const item: any = arr.format('dd') + '   ' + arr.format('M/DD');
                    timeQuantum.push(item);
                })
            }
        }
        return timeQuantum;
    }

    /**
     * 确认跳转
     * 
     * @memberof AppClaendarTimeline
     */
    public confirmJump() {
        this.currentTime = this.selectedGotoDate;
        this.partitionTime();
    }

    /**
     * 日历项点击
     * 
     * @param item 选中日历项
     * @memberof AppClaendarTimeline
     */
    public calendarItemClick(item: any){
        let select: any;
        this.schedule.forEach((_schedule: any) => {
            if (item.itemType && _schedule.itemType) {
                if (Object.is(item.itemType, _schedule.itemType) && Object.is(item[item.itemType], _schedule[_schedule.itemType])) {
                    select = _schedule;
                }
            }
        });
        this.$emit('eventClick',select);
    }

    /**
     * 刷新日程
     * 
     * @memberof AppClaendarTimeline 
     */
    public refetchEvents(){
        this.searchEvents();
    }

    /**
     * 获取today按钮禁用状态
     * 
     * @memberof AppClaendarTimeline
     */
    public toDayDisabled(button: any){
        if (Object.is(button.tag, 'today')) {
            return moment().isSame(this.currentTime, 'day');
        }
        return button.disabled;
    }
}
</script>

<style lang='less'>
@import './app-calendar-timeline.less';
</style>
