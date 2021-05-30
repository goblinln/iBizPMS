import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppListExpViewBase } from '../app-common-view/app-listexpview-base';

/**
 * 应用列表导航视图
 *
 * @export
 * @class AppDefaultListExpView
 * @extends {AppListExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultListExpView extends AppListExpViewBase {}
