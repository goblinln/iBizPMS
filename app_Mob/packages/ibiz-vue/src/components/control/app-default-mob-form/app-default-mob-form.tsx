import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDefaultMobFormPage } from './app-default-mob-form-page/app-default-mob-form-page';
import { AppDefaultMobGroupPanel } from './app-default-mob-group-panel/app-default-mob-group-panel';
import { AppDefaultMobFormItem } from './app-default-mob-form-item/app-default-mob-form-item';
import { AppMobFormBase } from '../app-common-control/app-mob-form-base';
import './app-default-mob-form.less';
/**
 * 编辑表单
 *
 * @export
 * @class ViewToolbar
 * @extends {Vue}
 */
@Component({
    components: {
        AppDefaultMobFormPage,
        AppDefaultMobGroupPanel,
        AppDefaultMobFormItem,
    },
})
@VueLifeCycleProcessing()
export class AppDefaultMobForm extends AppMobFormBase { }
