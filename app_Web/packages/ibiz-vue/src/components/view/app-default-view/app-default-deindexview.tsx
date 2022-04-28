import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDEIndexViewBase } from '../app-common-view';

/**
 * @description 应用实体首页视图默认样式
 * @export
 * @class AppDefaultDeIndexView
 * @extends {AppDEIndexViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDeIndexView extends AppDEIndexViewBase { }