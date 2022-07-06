
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';


/**
 * pms更新日志详情列表插件类
 *
 * @export
 * @class MobUpdateLogInfo
 * @class MobUpdateLogInfo
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobUpdateLogInfo extends AppMobMDCtrlBase {

        render() {
        return this.$createElement('app-pms-update-log-info', {
            props: { items: this.items }
        })
    }

}

