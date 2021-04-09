import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDataViewBase } from '../app-common-control/app-dataview-base';
import { AppDataViewItem } from './app-dataview-item/app-dataview-item';
import './app-default-dataview.less';

@Component({
    components: {
        'app-dataview-item': AppDataViewItem
    }
})
@VueLifeCycleProcessing()
export class AppDefaultDataView extends AppDataViewBase {}
