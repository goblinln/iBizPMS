import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTreeGridExViewBase } from '../app-common-view/app-treegridexview-base';

/**
 * 应用树表格视图
 *
 * @export
 * @class AppDefaultTreeGridExView
 * @extends {AppTreeGridExViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTreeGridExView extends AppTreeGridExViewBase {}