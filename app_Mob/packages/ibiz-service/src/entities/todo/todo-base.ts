import { EntityBase } from 'ibiz-core';
import { ITodo } from '../interface';

/**
 * 待办基类
 *
 * @export
 * @abstract
 * @class TodoBase
 * @extends {EntityBase}
 * @implements {ITodo}
 */
export abstract class TodoBase extends EntityBase implements ITodo {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TodoBase
     */
    get srfdename(): string {
        return 'ZT_TODO';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.name;
    }
    set srfmajortext(val: any) {
        this.name = val;
    }
    /**
     * 编号
     */
    id?: any;
    /**
     * 所有者
     */
    account?: any;
    /**
     * 间隔天数
     */
    config_day?: any;
    /**
     * 指派给（选择）
     */
    assignedtopk?: any;
    /**
     * 日期
     */
    date1?: any;
    /**
     * 周期类型
     *
     * @type {('day' | 'week' | 'month')} day: 天, week: 周, month: 月度
     */
    config_type?: 'day' | 'week' | 'month';
    /**
     * 待办编号
     */
    todosn?: any;
    /**
     * 关闭时间
     */
    closeddate?: any;
    /**
     * 由谁关闭
     */
    closedby?: any;
    /**
     * 类型
     *
     * @type {('custom' | 'bug' | 'task' | 'story')} custom: 自定义, bug: Bug, task: 项目任务, story: 项目需求
     */
    type?: 'custom' | 'bug' | 'task' | 'story';
    /**
     * 结束
     *
     * @type {(600 | 610 | 620 | 630 | 640 | 650 | 700 | 710 | 720 | 730 | 740 | 750 | 800 | 810 | 820 | 830 | 840 | 850 | 900 | 910 | 920 | 930 | 940 | 950 | 1000 | 1010 | 1020 | 1030 | 1040 | 1050 | 1100 | 1110 | 1120 | 1130 | 1140 | 1150 | 1200 | 1210 | 1220 | 1230 | 1240 | 1250 | 1300 | 1310 | 1320 | 1330 | 1340 | 1350 | 1400 | 1410 | 1420 | 1430 | 1440 | 1450 | 1500 | 1510 | 1520 | 1530 | 1540 | 1550 | 1600 | 1610 | 1620 | 1630 | 1640 | 1650 | 1700 | 1710 | 1720 | 1730 | 1740 | 1750 | 1800 | 1810 | 1820 | 1830 | 1840 | 1850 | 1900 | 1910 | 1920 | 1930 | 1940 | 1950 | 2000 | 2010 | 2020 | 2030 | 2040 | 2050 | 2100 | 2110 | 2120 | 2130 | 2140 | 2150 | 2200 | 2210 | 2220 | 2230 | 2240 | 2250 | 2300 | 2310 | 2320 | 2330 | 2340 | 2350)} 600: 06:00, 610: 06:10, 620: 06:20, 630: 06:30, 640: 06:40, 650: 06:50, 700: 07:00, 710: 07:10, 720: 07:20, 730: 07:30, 740: 07:40, 750: 07:50, 800: 08:00, 810: 08:10, 820: 08:20, 830: 08:30, 840: 08:40, 850: 08:50, 900: 09:00, 910: 09:10, 920: 09:20, 930: 09:30, 940: 09:40, 950: 09:50, 1000: 10:00, 1010: 10:10, 1020: 10:20, 1030: 10:30, 1040: 10:40, 1050: 10:50, 1100: 11:00, 1110: 11:10, 1120: 11:20, 1130: 11:30, 1140: 11:40, 1150: 11:50, 1200: 12:00, 1210: 12:10, 1220: 12:20, 1230: 12:30, 1240: 12:40, 1250: 12:50, 1300: 13:00, 1310: 13:10, 1320: 13:20, 1330: 13:30, 1340: 13:40, 1350: 13:50, 1400: 14:00, 1410: 14:10, 1420: 14:20, 1430: 14:30, 1440: 14:40, 1450: 14:50, 1500: 15:00, 1510: 15:10, 1520: 15:20, 1530: 15:30, 1540: 15:40, 1550: 15:50, 1600: 16:00, 1610: 16:10, 1620: 16:20, 1630: 16:30, 1640: 16:40, 1650: 16:50, 1700: 17:00, 1710: 17:10, 1720: 17:20, 1730: 17:30, 1740: 17:40, 1750: 17:50, 1800: 18:00, 1810: 18:10, 1820: 18:20, 1830: 18:30, 1840: 18:40, 1850: 18:50, 1900: 19:00, 1910: 19:10, 1920: 19:20, 1930: 19:30, 1940: 19:40, 1950: 19:50, 2000: 20:00, 2010: 20:10, 2020: 20:20, 2030: 20:30, 2040: 20:40, 2050: 20:50, 2100: 21:00, 2110: 21:10, 2120: 21:20, 2130: 21:30, 2140: 21:40, 2150: 21:50, 2200: 22:00, 2210: 22:10, 2220: 22:20, 2230: 22:30, 2240: 22:40, 2250: 22:50, 2300: 23:00, 2310: 23:10, 2320: 23:20, 2330: 23:30, 2340: 23:40, 2350: 23:50
     */
    end?: 600 | 610 | 620 | 630 | 640 | 650 | 700 | 710 | 720 | 730 | 740 | 750 | 800 | 810 | 820 | 830 | 840 | 850 | 900 | 910 | 920 | 930 | 940 | 950 | 1000 | 1010 | 1020 | 1030 | 1040 | 1050 | 1100 | 1110 | 1120 | 1130 | 1140 | 1150 | 1200 | 1210 | 1220 | 1230 | 1240 | 1250 | 1300 | 1310 | 1320 | 1330 | 1340 | 1350 | 1400 | 1410 | 1420 | 1430 | 1440 | 1450 | 1500 | 1510 | 1520 | 1530 | 1540 | 1550 | 1600 | 1610 | 1620 | 1630 | 1640 | 1650 | 1700 | 1710 | 1720 | 1730 | 1740 | 1750 | 1800 | 1810 | 1820 | 1830 | 1840 | 1850 | 1900 | 1910 | 1920 | 1930 | 1940 | 1950 | 2000 | 2010 | 2020 | 2030 | 2040 | 2050 | 2100 | 2110 | 2120 | 2130 | 2140 | 2150 | 2200 | 2210 | 2220 | 2230 | 2240 | 2250 | 2300 | 2310 | 2320 | 2330 | 2340 | 2350;
    /**
     * 描述
     */
    desc?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 由谁完成
     */
    finishedby?: any;
    /**
     * 开始
     *
     * @type {(600 | 610 | 620 | 630 | 640 | 650 | 700 | 710 | 720 | 730 | 740 | 750 | 800 | 810 | 820 | 830 | 840 | 850 | 900 | 910 | 920 | 930 | 940 | 950 | 1000 | 1010 | 1020 | 1030 | 1040 | 1050 | 1100 | 1110 | 1120 | 1130 | 1140 | 1150 | 1200 | 1210 | 1220 | 1230 | 1240 | 1250 | 1300 | 1310 | 1320 | 1330 | 1340 | 1350 | 1400 | 1410 | 1420 | 1430 | 1440 | 1450 | 1500 | 1510 | 1520 | 1530 | 1540 | 1550 | 1600 | 1610 | 1620 | 1630 | 1640 | 1650 | 1700 | 1710 | 1720 | 1730 | 1740 | 1750 | 1800 | 1810 | 1820 | 1830 | 1840 | 1850 | 1900 | 1910 | 1920 | 1930 | 1940 | 1950 | 2000 | 2010 | 2020 | 2030 | 2040 | 2050 | 2100 | 2110 | 2120 | 2130 | 2140 | 2150 | 2200 | 2210 | 2220 | 2230 | 2240 | 2250 | 2300 | 2310 | 2320 | 2330 | 2340 | 2350)} 600: 06:00, 610: 06:10, 620: 06:20, 630: 06:30, 640: 06:40, 650: 06:50, 700: 07:00, 710: 07:10, 720: 07:20, 730: 07:30, 740: 07:40, 750: 07:50, 800: 08:00, 810: 08:10, 820: 08:20, 830: 08:30, 840: 08:40, 850: 08:50, 900: 09:00, 910: 09:10, 920: 09:20, 930: 09:30, 940: 09:40, 950: 09:50, 1000: 10:00, 1010: 10:10, 1020: 10:20, 1030: 10:30, 1040: 10:40, 1050: 10:50, 1100: 11:00, 1110: 11:10, 1120: 11:20, 1130: 11:30, 1140: 11:40, 1150: 11:50, 1200: 12:00, 1210: 12:10, 1220: 12:20, 1230: 12:30, 1240: 12:40, 1250: 12:50, 1300: 13:00, 1310: 13:10, 1320: 13:20, 1330: 13:30, 1340: 13:40, 1350: 13:50, 1400: 14:00, 1410: 14:10, 1420: 14:20, 1430: 14:30, 1440: 14:40, 1450: 14:50, 1500: 15:00, 1510: 15:10, 1520: 15:20, 1530: 15:30, 1540: 15:40, 1550: 15:50, 1600: 16:00, 1610: 16:10, 1620: 16:20, 1630: 16:30, 1640: 16:40, 1650: 16:50, 1700: 17:00, 1710: 17:10, 1720: 17:20, 1730: 17:30, 1740: 17:40, 1750: 17:50, 1800: 18:00, 1810: 18:10, 1820: 18:20, 1830: 18:30, 1840: 18:40, 1850: 18:50, 1900: 19:00, 1910: 19:10, 1920: 19:20, 1930: 19:30, 1940: 19:40, 1950: 19:50, 2000: 20:00, 2010: 20:10, 2020: 20:20, 2030: 20:30, 2040: 20:40, 2050: 20:50, 2100: 21:00, 2110: 21:10, 2120: 21:20, 2130: 21:30, 2140: 21:40, 2150: 21:50, 2200: 22:00, 2210: 22:10, 2220: 22:20, 2230: 22:30, 2240: 22:40, 2250: 22:50, 2300: 23:00, 2310: 23:10, 2320: 23:20, 2330: 23:30, 2340: 23:40, 2350: 23:50
     */
    begin?: 600 | 610 | 620 | 630 | 640 | 650 | 700 | 710 | 720 | 730 | 740 | 750 | 800 | 810 | 820 | 830 | 840 | 850 | 900 | 910 | 920 | 930 | 940 | 950 | 1000 | 1010 | 1020 | 1030 | 1040 | 1050 | 1100 | 1110 | 1120 | 1130 | 1140 | 1150 | 1200 | 1210 | 1220 | 1230 | 1240 | 1250 | 1300 | 1310 | 1320 | 1330 | 1340 | 1350 | 1400 | 1410 | 1420 | 1430 | 1440 | 1450 | 1500 | 1510 | 1520 | 1530 | 1540 | 1550 | 1600 | 1610 | 1620 | 1630 | 1640 | 1650 | 1700 | 1710 | 1720 | 1730 | 1740 | 1750 | 1800 | 1810 | 1820 | 1830 | 1840 | 1850 | 1900 | 1910 | 1920 | 1930 | 1940 | 1950 | 2000 | 2010 | 2020 | 2030 | 2040 | 2050 | 2100 | 2110 | 2120 | 2130 | 2140 | 2150 | 2200 | 2210 | 2220 | 2230 | 2240 | 2250 | 2300 | 2310 | 2320 | 2330 | 2340 | 2350;
    /**
     * 关联编号
     */
    idvalue?: any;
    /**
     * 由谁指派
     */
    assignedby?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 周期设置月
     *
     * @type {('1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | '13' | '14' | '15' | '16' | '17' | '18' | '19' | '20' | '21' | '22' | '23' | '24' | '25' | '26' | '27' | '28' | '29' | '30' | '31')} 1: 1号, 2: 2号, 3: 3号, 4: 4号, 5: 5号, 6: 6号, 7: 7号, 8: 8号, 9: 9号, 10: 10号, 11: 11号, 12: 12号, 13: 13号, 14: 14号, 15: 15号, 16: 16号, 17: 17号, 18: 18号, 19: 19号, 20: 20号, 21: 21号, 22: 22号, 23: 23号, 24: 24号, 25: 25号, 26: 26号, 27: 27号, 28: 28号, 29: 29号, 30: 30号, 31: 31号
     */
    config_month?: '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | '13' | '14' | '15' | '16' | '17' | '18' | '19' | '20' | '21' | '22' | '23' | '24' | '25' | '26' | '27' | '28' | '29' | '30' | '31';
    /**
     * 待办名称
     */
    task?: any;
    /**
     * 待办名称
     */
    bug?: any;
    /**
     * 完成时间
     */
    finisheddate?: any;
    /**
     * 周期
     */
    cycle?: any;
    /**
     * 待定
     */
    date_disable?: any;
    /**
     * 周期设置周几
     *
     * @type {('2' | '3' | '4' | '5' | '6' | '7' | '1')} 2: 星期一, 3: 星期二, 4: 星期三, 5: 星期四, 6: 星期五, 7: 星期六, 1: 星期日
     */
    config_week?: '2' | '3' | '4' | '5' | '6' | '7' | '1';
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 状态
     *
     * @type {('wait' | 'doing' | 'done' | 'closed')} wait: 未开始, doing: 进行中, done: 已完成, closed: 已关闭
     */
    status?: 'wait' | 'doing' | 'done' | 'closed';
    /**
     * 提前
     */
    config_beforedays?: any;
    /**
     * 待办名称
     */
    name?: any;
    /**
     * 指派日期
     */
    assigneddate?: any;
    /**
     * 过期时间
     */
    config_end?: any;
    /**
     * 费用
     */
    cost?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 一般, 2: 最高, 3: 较高, 4: 最低
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 日期
     */
    date?: any;
    /**
     * 待办名称
     */
    story?: any;
    /**
     * 私人事务
     *
     * @type {('1')} 1: 是
     */
    ibizprivate?: '1';
    /**
     * config
     */
    config?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TodoBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
