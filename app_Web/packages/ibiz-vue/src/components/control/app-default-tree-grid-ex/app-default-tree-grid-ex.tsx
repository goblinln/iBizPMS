import { Component} from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTreeGridExBase } from '../app-common-control/app-tree-grid-ex-base';
import './app-default-tree-grid-ex.less';

/**
 * 树表格部件
 *
 * @export
 * @class AppDefaultTreeGridEx
 * @extends {AppTreeGridExBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTreeGridEx extends AppTreeGridExBase {}
