import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDataViewExpBarBase } from '../app-common-control/app-dataview-exp-bar-base';
import './app-default-dataview-exp-bar.less';

/**
 * 数据视图导航栏部件基类
 *
 * @export
 * @class AppDefaultDataViewExpBar
 * @extends {AppDataViewExpBarBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDataViewExpBar extends AppDataViewExpBarBase {}