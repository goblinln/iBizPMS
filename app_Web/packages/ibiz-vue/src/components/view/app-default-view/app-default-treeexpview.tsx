import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTreeExpViewBase } from '../app-common-view/app-treeexpview-base';


/**
 * 应用树导航视图
 *
 * @export
 * @class AppDefaultTreeExpView
 * @extends {AppTreeExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTreeExpView extends AppTreeExpViewBase {}