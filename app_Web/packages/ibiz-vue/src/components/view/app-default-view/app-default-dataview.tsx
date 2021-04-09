import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDataViewBase } from '../app-common-view/app-dataview-base';


/**
 * 应用数据视图
 *
 * @export
 * @class AppDefaultDataViewView
 * @extends {AppDataViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDataViewView extends AppDataViewBase {}