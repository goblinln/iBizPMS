import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTabSearchViewBase } from '../app-common-view/app-tabsearchview-base';


/**
 * 实体分页搜索视图基类
 *
 * @export
 * @class AppDefaultTabSearchView
 * @extends {AppTabSearchViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTabSearchView extends AppTabSearchViewBase {}
