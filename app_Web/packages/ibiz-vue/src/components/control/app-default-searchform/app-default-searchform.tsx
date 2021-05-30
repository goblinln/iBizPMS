import { Component } from 'vue-property-decorator';
import { AppDefaultSearchFormPage } from './app-default-searchform-page/app-default-searchform-page';
import { AppDefaultGroupPanel } from './app-default-group-panel/app-default-group-panel';
import { AppDefaultSearchFormItem } from './app-default-searchform-item/app-default-searchform-item';
import { AppDefaultSearchFormTabPanel } from './app-default-searchform-tab-panel/app-default-searchform-tab-panel';
import { AppDefaultSearchFormTabPage } from './app-default-searchform-tab-page/app-default-searchform-tab-page';
import { AppSearchFormBase } from '../app-common-control/app-searchform-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import './app-default-searchform.less'


/**
 * 搜索表单部件
 *
 * @export
 * @class AppDefaultSearchForm
 * @extends {AppSearchFormBase}
 */
@Component({
    components: {
        AppDefaultSearchFormPage,
        AppDefaultGroupPanel,
        AppDefaultSearchFormItem,
        AppDefaultSearchFormTabPanel,
        AppDefaultSearchFormTabPage,
    },
})
@VueLifeCycleProcessing()
export class AppDefaultSearchForm extends AppSearchFormBase {}
