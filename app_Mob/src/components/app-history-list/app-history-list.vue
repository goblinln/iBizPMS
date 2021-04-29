<template>
  <div class="container">
    <div  class="onecontent" ref="onecontent">   
      <div v-for="(item,index) in items" :key="item.id" class="oneitem" ref="oneitem" >
        <template v-if="index < 3  || isShow">
              <div class="header"><span>{{item.date}}</span><span>{{item.method}}</span></div>
              <template>
                  <div  class="footer" >                
                    <span>{{$t('by')}} </span>
                    <strong >{{item.actorText}}</strong> 
                    {{item.method}} 
                  <div class="info">
                    <template v-for="_item in item.item">
                      <span v-if="!(item.actions == 'recordestimate' || item.actions == 'delayed' )" :key="_item.id" class="info_item"> 
                        修改了<strong >{{_item.fieldText}}</strong >
                        <span v-if="_item.old"> {{$t('oldvalue')}} </span>
                        <span v-html="_item.old"></span>,
                        <span v-if="_item.ibiznew"> {{$t('newvalue')}} </span>
                        <span v-html="_item.ibiznew"></span>
                      </span>
                    </template>
                    <span v-if="item.comment" v-html="item.comment"></span>
                  </div>
                  </div>
              </template>
        </template>
      </div>
    </div>
    <div class="loadMorebutton" v-if="items.length > 3" ref="loadMore">
      <div @click="loadMore"><span>{{text}}</span></div>
    </div>
    <div class="zero" v-if="items.length == 0">
      <div>{{$t('nodata')}}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { CodeListServiceBase } from "ibiz-core";
import { Environment } from '@/environments/environment';

@Component({
    components: {},
    i18n: {
        messages: {
            'ZH-CN': {
                by: '由',
                oldvalue: '旧值为',
                newvalue: '新值为',
                nodata: '暂无记录',
            },
            'EN-US': {
                by: 'By',
                oldvalue: 'Old value:',
                newvalue: 'New value:',
                nodata: 'No data',
            }
        }
    }
})
export default class APPHistoryList extends Vue {

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof APPHistoryList
     */

    public codeListService: CodeListServiceBase = new CodeListServiceBase();

    /**
     * 传入数据items
     *
     * @type {Array}
     * @memberof APPHistoryList
     */
    @Prop() public items?: any;

    /**
     * 下载文件路径
     *
     * @memberof AppMobFileUpload
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

    /**
     * 监听items
     *
     * @returns {void}
     * @memberof APPHistoryList
     */
    @Watch('items', { immediate: true, deep: true })
    itemsChange() {
        if (this.items && this.items.length !== 0) {
            this.text = '查看更多记录';
        } else {
            this.text = '暂无更多记录';
        }
        this.inint();
    }

    /**
     * 解析代码表获取数据
     *
     * @returns {void}
     * @memberof APPHistoryList
     */
    public handler() {
          for (let index = 0; index < this.items.length; index++) {
            const item = this.items[index];
            item.actorText = this.getUserReName(item.actor);
            if (item.actions)item.method = this.getActionName(item.actions);
            if(item.comment)item.comment = this.parseImgUrl(item.comment);
            if (item?.item && item.item.length > 0) {
              for (let index = 0; index < item.item.length; index++) {
                  const _item = item.item[index];
                  _item.ibiznew = this.parseImgUrl(_item.ibiznew);
                  _item.old = this.parseImgUrl(_item.old);
                  _item.fieldText = (this.$t(item.objecttype + '.fields.' + _item.field.toLowerCase()) as string);
                  if(_item.field.indexOf('time') != -1 || _item.field.indexOf('date') != -1){
                    _item.old = _item.old.substring(0,19);
                    _item.ibiznew = _item.ibiznew.substring(0,19);
                  }
              }
            }
          }
          this.$forceUpdate();
    }

    /**
     * 图片解析
     *
     * @type {String}
     * @memberof APPHistoryList
     */
    public parseImgUrl(html:any){
      let that :any = this;
       let parsehtml = html.replace(/<img [^>]*src=['"]\{([^\}'"]+)[^>]*>/gi,  (match:any, capture:any) =>{
          let parseUrl = "";
          if(match.indexOf('{') && match.indexOf('}')){
            parseUrl = `${that.downloadUrl}/${capture.split('.')[0]}`;
          }
           return `<img src="${parseUrl?parseUrl:capture}"/>`
        });
        return parsehtml
    }

    /**
     * 按钮文本
     *
     * @type {String}
     * @memberof APPHistoryList
     */
    public text: string = '查看更多记录';

    /**
     * 文本切换
     *
     * @type {Boolean}
     * @memberof APPHistoryList
     */
    public isShow: boolean = false;

    /**
     * 点击展开
     *
     * @returns {void}
     * @memberof APPHistoryList
     */
    public loadMore(): void {
        this.isShow = !this.isShow;
        this.text = this.isShow ? '收起' : '查看更多记录';
    }

    /**
     * 操作名称数据
     *
     * @returns {void}
     * @memberof APPHistoryList
     */
    public actionData :any = [];

    /**
     * 用户数据
     *
     * @returns {void}
     * @memberof APPHistoryList
     */
    public userData: any[] = [];

    /**
     * 获取操作名称
     *
     * @returns {void}
     * @memberof APPHistoryList
     */
    public getActionName(id: string) {
        let index = this.actionData.findIndex((item:any) => { return item.value == id })
        return index > -1 ? this.actionData[index].label : "";
    }
    

    /**
     * 获取用户真实姓名
     *
     * @returns {void}
     * @memberof APPHistoryList
     */
    public getUserReName(id: string): string {
        let index = this.userData.findIndex((item) => { return item.value == id })
        return index > -1 ? this.userData[index].label : id;
    }

    public inint(){
        this.codeListService.getStaticItems('Action__type').then((_res:any)=>{
            this.actionData = _res;
            this.codeListService.getItems("UserRealName").then((res:any)=>{
              this.userData = res;
              this.handler();
            });
        });
    }

}
</script>

<style lang="less">
@import "./app-history-list.less";
</style>