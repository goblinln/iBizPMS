import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWFDynaActionViewBase } from '../app-common-view/app-wfdynaactionview-base';

/**
 * 应用实体工作流动态操作视图
 *
 * @export
 * @class AppWFDynaActionViewBase
 * @extends {AppWFDynaActionViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWFDynaActionView extends AppWFDynaActionViewBase {}