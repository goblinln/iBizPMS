import { Component } from 'vue-property-decorator';
import draggable from "vuedraggable";
import { AppKanbanBase } from '../app-common-control/app-kanban-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import './app-default-kanban.less';

/**
 * 实体看板部件
 *
 * @export
 * @class AppDefaultKanban
 * @extends {AppKanbanBase}
 */
@Component({    
    components: {
        draggable,
    }})
@VueLifeCycleProcessing()
export class AppDefaultKanban extends AppKanbanBase{}