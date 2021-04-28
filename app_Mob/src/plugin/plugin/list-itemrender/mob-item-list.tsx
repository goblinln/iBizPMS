
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';


/**
 * 移动端列表项插件（PMS）插件类
 *
 * @export
 * @class MobItemList
 * @class MobItemList
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobItemList extends AppMobMDCtrlBase {

    /**
     * 绘制列表主体
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
     public renderMainMDCtrl() {
        return this.items.length > 0
        ? <ion-list class="items" ref="ionlist">
            { this.items.map((item: any, index) => {
                    return <ion-item-sliding ref={item?.srfkey} class="app-mob-mdctrl-item" on-ionDrag={this.ionDrag.bind(this)} on-click={() => this.item_click(item)}>
                        {this.renderListItemAction(item)}
                        {
                             this.controlInstance.getItemPSLayoutPanel() ? this.renderItemPSLayoutPanel(item) : this.renderListContent(item, index)
                        }
                    </ion-item-sliding>
                })}
          </ion-list>
        : !this.isFirstLoad ? <div class="no-data">暂无数据</div>:null
    }

}

