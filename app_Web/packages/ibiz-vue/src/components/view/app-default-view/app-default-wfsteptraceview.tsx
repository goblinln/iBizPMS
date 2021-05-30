import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWfStepTraceViewBase } from '../app-common-view/app-wfsteptraceview-base';
/**
 * 应用流程跟踪视图
 *
 * @export
 * @class AppDefaultWfStepTraceView
 * @extends {AppWfStepTraceViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWfStepTraceView extends AppWfStepTraceViewBase {}