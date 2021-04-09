import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMPickUpViewBase } from '../app-common-view/app-mpickupview-base';

/**
 * 应用数据多项选择视图
 *
 * @export
 * @class AppDefaultMPickUpView
 * @extends {AppMPickUpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMPickUpView extends AppMPickUpViewBase {}
