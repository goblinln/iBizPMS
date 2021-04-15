import { AppGanttBase } from '../app-common-control/app-gantt-base';
import { Component } from 'vue-property-decorator';
import GanttElastic from "ibiz-gantt-elastic/src/GanttElastic.vue";
import './app-default-gantt.less';
import { VueLifeCycleProcessing } from '../../../decorators';


/**
 * 甘特部件基类
 *
 * @export
 * @class AppDefaultGantt
 * @extends {AppGanttBase}
 */
@Component({
    components: {
        GanttElastic,
    }
})
@VueLifeCycleProcessing()
export class AppDefaultGantt extends AppGanttBase {}