import { Component } from "vue-property-decorator";
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppCalendarExpBarBase } from '../app-common-control/app-calendar-exp-bar';
import './app-default-calendar-exp-bar.less';

/**
 * 日历导航部件基类
 *
 * @export
 * @class AppDefaultCalendarExpBar
 * @extends {AppCalendarExpBarBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultCalendarExpBar extends AppCalendarExpBarBase{}