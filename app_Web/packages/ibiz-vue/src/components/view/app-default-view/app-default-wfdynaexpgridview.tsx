import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWfDynaExpGridViewBase } from '../app-common-view/app-wfdynaexpgridview-base';

/**
 * 应用实体工作流动态导航表格视图
 *
 * @export
 * @class AppDefaultWfDynaExpGridView
 * @extends {AppWfDynaExpGridViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWfDynaExpGridView extends AppWfDynaExpGridViewBase {}