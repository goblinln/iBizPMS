
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';



/**
 * 开始任务选项操作表单插件类
 *
 * @export
 * @class StartTaskOptForm
 * @class StartTaskOptForm
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class StartTaskOptForm extends AppControlBase {

<app-list-index-text :item="item" :index="item.srfkey" @clickItem="item_click"></app-list-index-text>

}

