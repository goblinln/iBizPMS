
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';
import { CodeListService } from "ibiz-service";
import '../plugin-style.less';

/**
 * 移动端汇报列表插件插件类
 *
 * @export
 * @class MobReportList2
 * @class MobReportList2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobReportList2 extends AppMobMDCtrlBase {


    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppSelect
     */

     public codeListService: CodeListService = new CodeListService({ $store: this.$store });  

    /**
     * 图片地址
     *
     * @param {*} nodes
     * @memberof EmpTreeBase
     */
     public imageUrl = 'ibizutil/download';

     /**
      * 渲染data
      *
      * @type {CodeListService}
      * @memberof AppReportList
      */
     public data:any = []; 
 
     /**
      * 用户数据
      *
      * @type {CodeListService}
      * @memberof AppReportList
      */
     public userData :any = [];
 
    /**
     * 数据加载
     * 
     * @memberof AppReportList
     */   
     public async load(data: any = {}, type: string = "", isloadding = this.showBusyIndicator){
        await super.load(data,type,isloadding);
        this.on_parse_item(this.items).then((res:any)=>{
            this.data = res;
        });
      }

     /**
      * 解析数据
      *
      * @memberof AppReportList
      */
     public async on_parse_item(items:any): Promise<any>{
        if (!items) {
          return false;
        }
        this.userData = await this.codeListService.getItems("UserRealName", this.context);
        let temp_items :any= [];
        for (let index = 0; index < items.length; index++) {
             const temp = items[index];
             temp.relname = this.getUserReName(temp.account)
             temp.reliconUrl = this.getUserReIconUrl(temp.account);
             temp.worktoday_html = this.getText(temp.worktoday);
             temp.planstomorrow_html = this.getText(temp.planstomorrow);
             temp_items.push(temp);
        }
        return this.parseDataGroup(temp_items);
     }
 
     /**
      * 获取文本
      *
      * @returns {void}
      * @memberof AppReportList
      */
     public getText(str:string) {
         if(!str){
             return "";
         }
         str = str.replace(/\<[^>]*\>(([^<])*)/g, function() {
             let mark = "";
             return arguments[1];
         });
         return str.substring(0, str.length);
 
     }
 
     /**
      * 获取用户真实姓名
      *
      * @returns {string}
      * @memberof AppReportList
      */
     public getUserReName(id: string): string {
         let index = this.userData.findIndex((item:any) => { return item.value == id })
         return index > -1 ? this.userData[index].label : "";
     }
 
     /**
      * 获取用户头像Url
      *
      * @returns {string}
      * @memberof AppReportList
      */
     public getUserReIconUrl(id: string): string {
         let index = this.userData.findIndex((item:any) => { return item.value == id });
         if(index === -1){
             return '';
         }
         if(this.userData[index].icon || (this.userData[index].icon && this.userData[index].icon[0]) ){
             let icon = JSON.parse(this.userData[index].icon)
             return `${this.imageUrl}/${icon[0].id}`
         }
         return '';
     }
 
     /**
      * 日期分组
      *
      * @returns {any}
      * @memberof AppReportList
      */
     public parseDataGroup(items:any):any{
         let temp :any= [];
         for (let index = 0; index < items.length; index++) {
             const element = items[index];
             let _index = temp.findIndex((i:any)=>{return i.date == element.date});
             if(_index != -1){
                 temp[_index].items.push(element);
             }else{
                 temp.push({date:element.date,items:[element]});
             }
         }
         temp.sort(function(now:any, next:any) {
             return  new Date(next.date).getTime() - new Date(now.date).getTime();
         });
         for (let index = 0; index < temp.length; index++) {
             const element = temp[index];
             element.items.sort(function(now:any, next:any) {
                 if(next.submittime && now.submittime){
                     return next.submittime.replace(":","") - now.submittime.replace(":","");
                 }
             });
         }
         return temp;
     }
 
     /**
      * 点击
      *
      * @returns {void}
      * @memberof AppReportList
      */
     public onClick(item:any) {
         this.item_click(item);
     }

    /**
     * 绘制
     * 
     * @memberof MobReportList
     */
     public render(): any{
        if(!this.controlIsLoaded){
          return null;
        }
        return <div class="app-report-list">
            {this.data.map((item:any)=>{
              return <div class="app-report-list-item">
                <div class="date">{item.date}</div>
                {item.items.map((_item:any)=>{
                  return <div class="item_content" key={_item.id} on-click={()=>this.onClick(_item)}>
                            <div class="img">
                              {_item.reliconUrl 
                              ? <img src={_item.reliconUrl} alt=""/> 
                              : <ion-icon name="person-outline" ></ion-icon>}
                            </div>
                            <div class="item_content_content">
                              <div class="item_content_content_header">
                                  <div class="relname">
                                    {_item.reportstatus == 0 ? <div class="print"></div> :null}
                                    {_item.relname}
                                  </div>
                                  <div class="submittime">{_item.submittime}</div>
                              </div>
                              <div class="item_content_content_text">
                                  <div class="worktoday" domPropsInnerHTML={_item.worktoday_html}></div>
                                  <div class="planstomorrow" domPropsInnerHTML={_item.planstomorrow_html}></div>
                              </div>
                            </div>
                  </div>
                })}
              </div>
            })}
        </div>
    }  


}

