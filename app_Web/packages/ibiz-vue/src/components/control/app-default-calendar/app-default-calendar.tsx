import { Component } from 'vue-property-decorator';
import FullCalendar from '@fullcalendar/vue';
import './app-default-calendar.less';
import { AppCalendarBase } from '../app-common-control/app-calendar-base';
import { VueLifeCycleProcessing } from '../../../decorators';

/**
 * 日历部件
 *
 * @export
 * @class AppDefaultCalendar
 * @extends {AppCalendarBase}
 */
@Component({
    components: {
        FullCalendar
    }
})
@VueLifeCycleProcessing()
export class AppDefaultCalendar extends AppCalendarBase{}