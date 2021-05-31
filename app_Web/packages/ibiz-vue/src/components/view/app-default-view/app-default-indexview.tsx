import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppIndexViewBase } from '../app-common-view/app-indexview-base';


/**
 * 应用首页视图
 *
 * @export
 * @class AppDefaultIndexView
 * @extends {AppIndexViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultIndexView extends AppIndexViewBase {}
