
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';


/**
 * 移动端测试用例表项插件插件类
 *
 * @export
 * @class MobTestList2
 * @class MobTestList2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobTestList2 extends AppMobMDCtrlBase {

        /**
     * 绘制列表项集合
     * @return {*} 
     * @memberof AppDefaultMobMDCtrlBase
     */
     public renderListContent(item: any, index: any) {
        return <ion-item>
            {
                this.$createElement('app-case-list', {
                    props: {
                        item: item
                    }
                })
            }
        </ion-item>
    }

    /**
     * 绘制列表主体
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderMainMDCtrl() {
        return this.items.length > 0
        ? <ion-list class="items" ref="ionlist">
            {this.items.map((item: any, index) => {
                    return <ion-item-sliding ref={item?.srfkey} class="app-mob-mdctrl-item" on-ionDrag={this.ionDrag.bind(this)} on-click={() => this.item_click(item)}>
                        {this.renderListItemAction(item)}
                        {
                             this.renderListContent(item, index)
                        }
                    </ion-item-sliding>
                })}
          </ion-list>
        : !this.isFirstLoad ? <div class="no-data">暂无数据</div>:null
            
    }

}

