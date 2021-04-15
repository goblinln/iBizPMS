import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppKanbanViewBase } from '../app-common-view/app-kanbanview-base';

/**
 * 应用实体看板视图
 *
 * @export
 * @class AppStyle2KanbanView
 * @extends {AppKanbanViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppStyle2KanbanView extends AppKanbanViewBase {}