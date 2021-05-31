
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';


/**
 * 移动端列表（历史记录）插件类
 *
 * @export
 * @class MobHistoryList2
 * @class MobHistoryList2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobHistoryList2 extends AppMobMDCtrlBase {

    render() {
        return this.$createElement('app-history-list', {
            props: { items: this.items }
        })
    }

}

