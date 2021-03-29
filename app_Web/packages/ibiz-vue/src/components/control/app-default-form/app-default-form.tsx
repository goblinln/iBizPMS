import { Component } from 'vue-property-decorator';
import { AppDefaultFormPage } from './app-default-form-page/app-default-form-page';
import { AppDefaultGroupPanel } from './app-default-group-panel/app-default-group-panel';
import { AppDefaultFormItem } from './app-default-form-item/app-default-form-item';
import { AppDefaultFormTabPage } from './app-default-form-tab-page/app-default-form-tab-page';
import { AppDefaultFormTabPanel } from './app-default-form-tab-panel/app-default-form-tab-panel';
import { AppFormBase } from '../app-common-control/app-form-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import './app-default-form.less';


/**
 * 编辑表单部件
 *
 * @export
 * @class AppFormBase
 * @extends {AppFormBase}
 */
@Component({
    components: {
        AppDefaultFormPage,
        AppDefaultGroupPanel,
        AppDefaultFormItem,
        AppDefaultFormTabPage,
        AppDefaultFormTabPanel
    },
})
@VueLifeCycleProcessing()
export class AppDefaultForm extends AppFormBase {}
