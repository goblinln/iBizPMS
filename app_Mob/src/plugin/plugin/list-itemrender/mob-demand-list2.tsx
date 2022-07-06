
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';
import { CodeListService } from 'ibiz-service';
import { ImgurlBase64 } from "ibiz-core";
import '../plugin-style.less';

/**
 * 移动端需求列表项插件插件类
 *
 * @export
 * @class MobDemandList2
 * @class MobDemandList2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobDemandList2 extends AppMobMDCtrlBase {

    
    /**
     * 图片地址
     *
     * @param {*} nodes
     * @memberof EmpTreeBase
     */
    public imageUrl = 'ibizutil/download';

    /**
     * 需求优先级代码表
     */
    public Story__pri: any;

    /**
     * 需求状态代码表
     */
    public Story__status: any;

    /**
     * 需求阶段代码表
     */
    public Story__stage: any;

    /**
     * 用户真实名称
     */
    public UserRealName: any;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppReportList
     */
    public codeListService: CodeListService = new CodeListService();

    /**
     * 设置用户头像
     */
    public getUserImg(value: string, data: any) {
        let icon = this.getCodeListText('UserRealName', value).icon;
        if (icon) {
            icon = JSON.parse(icon);
        }
        if (icon && icon[0] && icon[0].id) {
            ImgurlBase64.getInstance().getImgURLOfBase64(`${this.imageUrl}/${icon[0].id}`).then((res:any)=>{
                this.$set(data,'assignedto_img',res)
            });
        }
    }

    /**
     * 初始化代码表
     */
    public async initCodeList(): Promise<any> {
        this.Story__pri = this.$store.getters.getCodeList('Story__pri').items;
        this.Story__stage = this.$store.getters.getCodeList('Story__stage').items;
        this.Story__status = this.$store.getters.getCodeList('Story__status').items;
        this.UserRealName = await this.codeListService.getItems('UserRealName');
        return true
    }

    /**
     * 获取代码表文本
     */
    public getCodeListText(tag: string, id: string): any {
        let _this: any = this;
        if (!_this[tag]) {
            return id;
        }
        let index = _this[tag].findIndex((item: any) => { return item.value == id })
        return index > -1 ? _this[tag][index] : id;
    }


    /**
     * 绘制列表项集合
     * @return {*} 
     * @memberof AppDefaultMobMDCtrlBase
     */
    public renderListContent(item: any, index: any) {
        item.pri_text = this.getCodeListText('Story__pri', item.pri)?.text;
        item.pri_className = this.getCodeListText('Story__pri', item.pri)?.className;
        item.stage_text = this.getCodeListText('Story__stage', item.stage)?.label;
        item.stage_color = this.getCodeListText('Story__stage', item.stage)?.color;
        item.status_text = this.getCodeListText('Story__status', item.status)?.label;
        item.status_color = this.getCodeListText('Story__status', item.status)?.color;
        // 设置指派名称
        item.assignedto_text = this.getCodeListText('UserRealName', item.assignedto).label;
        if (item.assignedto_text) {
            item.assignedto_text = item.assignedto_text.substring(0, 1);
        }
        // 设置用户头像
        this.getUserImg(item.assignedto,item);
        const pri_className = {[item.pri_className]:true,'pri':true}
        return <ion-item>
            <div class="app-story-list-item">
                <div class="app-story-list-item_top">
                    {
                        item.parent == '-1' ? <div class="multiple">父</div> : item.isleaf == '1' ? <div class="multiple">子</div> : null
                    }
                    <strong><div class="name" style={{ 'color': item.color }}> {item.srfmajortext}<span class="estimate">(工时：{item.estimate?item.estimate:0} h)</span></div></strong>
                    <div  class={pri_className}>{item.pri_text}</div>
                </div>
                <div class="app-story-list-item_bottom">
                    <div class="status" style={{ 'color': item.status_color }}>{item.status_text}</div>
                    <div class="stage" style={{ 'color': item.stage_color }}>{item.stage_text}</div>
                    <div class="assignedto">
                        {
                            item.assignedto_img ? <div class="assignedto_item_img"><img src={item.assignedto_img} alt=""></img></div> : item.assignedto_text?<div class="assignedto_item">{item.assignedto_text}</div>:null
                        }
                    </div>
                </div>
            </div>
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
                           this.controlInstance.getItemPSLayoutPanel() ? this.renderItemPSLayoutPanel(item) : this.renderListContent(item, index)
                        }
                    </ion-item-sliding>
                })}
              </ion-list>
            : !this.isFirstLoad ? <div class="no-data">暂无数据</div>:null
            
    }

    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit(args);
        await this.initCodeList();
    }


}

