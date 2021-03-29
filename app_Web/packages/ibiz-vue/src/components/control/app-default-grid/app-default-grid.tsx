import { Component } from 'vue-property-decorator';
import { AppGridBase } from '../app-common-control/app-grid-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDefaultGridColumn } from './app-grid-column/app-grid-column';
import './app-default-grid.less';

/**
 * 表格部件
 *
 * @export
 * @class AppDefaultGrid
 * @extends {AppGridBase}
 */
@Component({
    components: {
        'app-grid-column': AppDefaultGridColumn
    }
})
@VueLifeCycleProcessing()
export class AppDefaultGrid extends AppGridBase {

}