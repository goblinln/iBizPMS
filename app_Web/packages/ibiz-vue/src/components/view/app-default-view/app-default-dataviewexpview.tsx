import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDataViewExpViewBase } from '../app-common-view/app-dataviewexpview-base';


/**
 * 应用数据视图导航视图
 *
 * @export
 * @class AppDefaultDataViewExpView
 * @extends {AppDataViewExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDataViewExpView extends AppDataViewExpViewBase {}