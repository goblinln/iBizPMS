
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';


/**
 * PMS移动端更新日志列表插件类
 *
 * @export
 * @class MobUpdateLogList
 * @class MobUpdateLogList
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobUpdateLogList extends AppMobMDCtrlBase {

    render() {
        return this.$createElement('app-update-log-list', {
            props: { items: this.items },
		       on:{
                item_click:($event:any)=>{this.item_click($event)}
            }
        })
    }

}

