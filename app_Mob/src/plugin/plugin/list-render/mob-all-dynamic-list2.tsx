
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import {  AppMobMDCtrlBase } from 'ibiz-vue';


/**
 * pms移动端全部动态列表插件插件类
 *
 * @export
 * @class MobAllDynamicList2
 * @class MobAllDynamicList2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobAllDynamicList2 extends AppMobMDCtrlBase {

    render() {
        return this.$createElement('app-trends-list', {
            props: { items: this.items }

        })
    }

}

