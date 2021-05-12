
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';
import { CodeListService } from "ibiz-service";
import { ImgurlBase64 } from "ibiz-core";
import '../plugin-style.less';

/**
 * 移动端bug列表项插件插件类
 *
 * @export
 * @class MobBugItemList
 * @class MobBugItemList
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobBugItemList extends AppMobMDCtrlBase {


    /**
     * 数据加载
     */  
    public async load(data: any = {}, type: string = "", isloadding = this.showBusyIndicator){
      await super.load(data,type,isloadding);
      this.items.forEach((item:any) => {
        this.parseData(item);
      });
    }

    /**
     * bug优先级代码表
     */
    public Bug__pri: any;

    /**
    * bug状态代码表
    */
    public Bug__status: any;

    /**
    * bug严重程度代码表
    */
    public Bug__severity: any;

    /**
    * 用户真实名称
    */
    public UserRealName: any;

    /**
    * bug类型
    */
    public Bug__type: any;

    /**
    * 图片地址
    *
    * @param {*} nodes
    * @memberof EmpTreeBase
    */
    public imageUrl = 'ibizutil/download';

    /**
    * 代码表服务对象
    *
    * @type {CodeListService}
    * @memberof AppReportList
    */
    public codeListService: CodeListService = new CodeListService();

    /**
    * 解析
    */
    public parseData(item:any) {
        item.pri_text = this.getCodeListText('Bug__pri', item.pri)?.label;
        item.pri_className = this.getCodeListText('Bug__pri', item.pri)?.className;
        item.status_text = this.getCodeListText('Bug__status', item.status)?.label;
        item.status_color = this.getCodeListText('Bug__status', item.status)?.color;
        item.severity_text = this.getCodeListText('Bug__severity', item.severity)?.label;
        item.severity_color = this.getCodeListText('Bug__severity', item.severity)?.color;
        item.tasktype_text = this.getCodeListText('Bug__type', item.type)?.label;
        // 设置指派名称
        item.assignedto_text = this.getCodeListText('UserRealName', item.assignedto)?.label;
        // 设置用户头像
        this.getUserImg(item.assignedto,item);
        this.$forceUpdate();
    }

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
        this.Bug__pri = this.$store.getters.getCodeList('Bug__pri').items;
        this.Bug__status = this.$store.getters.getCodeList('Bug__status').items;
        this.Bug__type = this.$store.getters.getCodeList('Bug__type').items;
        this.Bug__severity = this.$store.getters.getCodeList('Bug__severity').items;
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
        let index = _this[tag].findIndex((item: any) => {
            return item.value == id
        })
        return index > -1 ? _this[tag][index] : id;
    }

    /**
    * 部件模型数据初始化实例
    *
    * @memberof MobBugItemList
    */      
    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit(args);
        await this.initCodeList();
    }

    /**
     * 绘制列表项集合
     * @return {*} 
     * @memberof MobBugItemList
     */  
    public renderListContent(item:any,index:any){
        return <ion-item>
          <div class="app-bug-list-item">
            <div class="app-bug-list-item_top">
              <strong>
                <div class="name" style={{'color':item.color}}>
                    <span>{item.srfmajortext}</span>
                    <span style="margin-left: 5px" class={item.pri_className}>{item.pri_text}</span>
                    <span class="severity" style={{'color':item.severity_color}}>{item.severity_text}</span>
                </div>
              </strong>
            </div>
            <div class="app-bug-list-item_bottom">
              <div class="status" style={{'color':item.status_color}}>{item.status_text}</div>
              <div class="speace">{item.tasktype_text}</div>
              <div class="deadline">{item.deadline}</div>
              <div class="delayresolve">{item.delayresolve}</div>
              <div class="assignedto">
                  {item.assignedto_img ? <div class="assignedto_item_img"><img  src={item.assignedto_img} alt=""/></div> :null}
                  {!item.assignedto_img && item.assignedto_text ? <div class="assignedto_item">{item.assignedto_text}</div> : null}
              </div>
            </div>
          </div>
        </ion-item>
    }

    /**
     * 绘制列表主体
     *
     * @returns {*}
     * @memberof MobBugItemList
     */
     public renderMainMDCtrl() {
        return this.items.length > 0
        ? <ion-list class="items" ref="ionlist">
            {this.items.map((item: any, index) => {
                    return <ion-item-sliding ref={item?.srfkey} class="app-mob-mdctrl-item" on-ionDrag={this.ionDrag.bind(this)} on-click={() => this.item_click(item)}>
                        {this.renderListItemAction(item)}
                        {this.renderListContent(item,index)}
                    </ion-item-sliding>
                })}
          </ion-list>
        : !this.isFirstLoad ? <div class="no-data">暂无数据</div>:null   
            
    }


}

