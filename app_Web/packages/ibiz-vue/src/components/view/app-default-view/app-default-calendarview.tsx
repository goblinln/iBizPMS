
import { Component} from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppCalendarViewBase } from '../app-common-view/app-calendarview-base';

/**
 * 应用日历视图
 *
 * @export
 * @class AppDefaultCalendarView
 * @extends {AppCalendarViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultCalendarView extends AppCalendarViewBase { }