import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWFDynaStartViewBase } from '../app-common-view/app-wfdynastartview-base';

/**
 * 应用实体工作流动态启动视图
 *
 * @export
 * @class AppDefaultWFDynaStartView
 * @extends {AppWFDynaStartViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWFDynaStartView extends AppWFDynaStartViewBase {}