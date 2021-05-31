import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppHtmlViewBase } from '../app-common-view/app-htmlview-base';


/**
 * 实体html视图
 *
 * @export
 * @class AppDefaultHtmlView
 * @extends {AppHtmlViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultHtmlView extends AppHtmlViewBase {}

