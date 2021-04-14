import { Prop, Emit, Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppGanttViewBase } from '../app-common-view/app-ganttview-base';

/**
 * 应用实体甘特视图
 *
 * @export
 * @class AppDefaultGanttView
 * @extends {AppGanttViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultGanttView extends AppGanttViewBase {}