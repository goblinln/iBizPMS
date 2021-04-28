import { Component } from 'vue-property-decorator';
import './app-default-mob-calendar.less';
import { AppMobCalendarBase } from '../app-common-control/app-mob-calendar-base';
import { VueLifeCycleProcessing } from '../../../decorators';

/**
 * 日历部件
 *
 * @export
 * @class AppDefaultMobCalendar
 * @extends {AppDefaultMobCalendarBase}
 */
@Component({
    components: {

    }
})
@VueLifeCycleProcessing()
export class AppDefaultMobCalendar extends AppMobCalendarBase{}