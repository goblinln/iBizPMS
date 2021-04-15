
import { Component} from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppCalendarExpViewBase } from '../app-common-view/app-calendarexpview-base';


/**
 * 应用日历导航视图
 *
 * @export
 * @class AppDefaultCalendarExpView
 * @extends {AppCalendarExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultCalendarExpView extends AppCalendarExpViewBase {}