import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTabExpViewBase } from '../app-common-view/app-tabexpview-base';


/**
 * 应用分页导航视图基类
 *
 * @export
 * @class AppDefaultTabExpView
 * @extends {AppTabExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTabExpView extends AppTabExpViewBase {}
