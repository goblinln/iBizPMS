<template>
<ion-page :className="{ 'view-container': true, 'default-mode-view': true, 'demobeditview': true, 'app-mob-group-picker-choose-view': true }">
    <ion-header>
        <ion-toolbar v-show="titleStatus" class="ionoc-view-header">
            <ion-buttons slot="start">
                <ion-button  @click="closeView">
                    <ion-icon name="chevron-back"></ion-icon>
                    {{$t('app.button.back')}}
                </ion-button>
            </ion-buttons>
            <ion-title><label class="title-label"> {{$t('app.title.choose')}}</label></ion-title>
        </ion-toolbar>
    </ion-header>
    <div class="ibiz-mob-group-picker">
        <div class="ibiz-group-container">
          <div class="top">
              <div class="left-part" v-if="treeItems.length > 0">
                <van-sidebar v-model="activeKey">
                  <van-sidebar-item :title="item.label" v-for="(item, index) in treeItems" :key="index" @click="treeSelect(item.id)"/>
                </van-sidebar>
              </div>
              <div class="right-part">
                <div v-for="(item,index) in handledCardItems" :key="index">
                  <div class="group-header">{{ item.title }}</div>
                  <ion-list class="group-list">
                    <template v-for="(item, index) in item.value">
                      <ion-item :key="index" @click="click_node(item)" class="list-item">
                        <ion-label>{{ item.label|| item.personname }}</ion-label>
                        <app-mob-icon class="tree-icon" position="end" :name="geticon(item.status)" @onClick="icon_click(item)"></app-mob-icon>
                      </ion-item>
                    </template>
                  </ion-list>
                </div>
              </div>
          </div>
          <div class="ibiz-group-footer">
              <van-button size="large" type="primary" @click="onOK">{{$t('app.button.confirm')}}</van-button>
          </div>
        </div>
    </div>
</ion-page>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';
import { Http } from "ibiz-core";
@Component({})
export default class AppMobGroupPicker extends Vue {

    /**
     * 动态参数
     * 
     * @memberof AppGroupPicker
     */
    @Prop() dynamicProps?:any;


    /**
     * 侧边栏激活项
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public activeKey:number = 0;

    /**
     * 多选
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected multiple: boolean = false;

    /**
     * 加载树url
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected treeurl:any;

    /**
     * 加载人员url
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected url:any;

    /**
     * 树数据集
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected treeItems: any[] = [];

    /**
     * 分组表数据集
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected cardItems: any[] = [];

    /**
     * 视图上下文参数对象
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected viewData: any;

    /**
     * 视图参数对象
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected viewParam: any;

    /**
     * 树选中值
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected treeSelectVal: string = '';

    /**
     * 分组表选中集合
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected cardSelctVal: any = [];

    /**
     * 数据选中集合
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    protected selects: any[] = [];

    /**
     * 处理后的分组表数据集
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public handledCardItems: any[] = [];

     /**
     * 设置工具栏标题状态
     *
     * @memberof IbzDailyDailyCreateMobEditViewBase
     */
    public titleStatus: boolean = true;

     /**
     * 设置工具栏状态
     *
     * @memberof IbzDailyDailyCreateMobEditViewBase
     */
    public setViewTitleStatus(){
        // const thirdPartyName = this.$store.getters.getThirdPartyName();
        // if(thirdPartyName){
        //     this.titleStatus = false;
        // }
    }


    /**
     * 是否显示树
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    get showTree() {
        if(this.viewParam) {
            return this.viewParam.showtree;
        }
    }

    /**
     * 生命周期
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public created() {
        const _context = this.dynamicProps._context;
        const _viewparams = this.dynamicProps._viewparams;
        if(!_context || !_viewparams) {
            return;
        }
        this.viewData = JSON.parse(_context);
        this.viewParam = JSON.parse(_viewparams);
        this.multiple = this.viewParam.multiple;
        this.treeurl = this.viewParam.treeurl;
        this.url = this.viewParam.url;
        if (this.viewParam.selects) {
            this.viewParam.selects.forEach((select: any) => {
                this.selects.push(select);
                this.cardSelctVal.push(select.id)
            })
        }
        this.load();
        this.setViewTitleStatus();
    }

    /**
     * 加载数据
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public load() {
        if(this.showTree) {
            this.loadTree();
        } else {
            this.loadGroupData(this.viewParam.filtervalue);
        }
    }

    /**
     * 加载树数据
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public loadTree() {
        let orgid = this.viewParam.filtervalue;
        let tempTreeUrl: string = '';
        if(this.viewParam.selectType && Object.is(this.viewParam.selectType,"dept")){
            tempTreeUrl = this.treeurl.replace('${deptId}',orgid);
        }else{
            tempTreeUrl = this.treeurl.replace('${orgid}',orgid);
        }
        let get = Http.getInstance().get(tempTreeUrl, true);
        get.then((response: any) => {
            if(response.status === 200) {
                this.treeItems = response.data;
            }
        }).catch((error: any) => {
            console.log(error)
        })        
    }

    /**
     * 加载分组表数据
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public loadGroupData(key: string) {
        let tempUrl: string = '';
        if(this.viewParam.selectType && Object.is(this.viewParam.selectType,"dept")){
            tempUrl = this.url.replace('${deptId}',key);
        }else{
            tempUrl = this.url.replace('${selected-orgid}',key);
        }
        let get = Http.getInstance().get(tempUrl, true);
        get.then((response: any) => {
            if(response.status === 200) {
                this.cardItems = response.data;
                this.handleGroupData()
            }
        }).catch((error: any) => {
            console.log(error)
        })
    }

    /**
     * 处理分组表数据
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public handleGroupData(){
      const  groupBy = (array:any, f:any) => {
          let groups:any = {};
          array.forEach(function (o:any) {
              var group:any = JSON.stringify(f(o));
              groups[group] = groups[group] || [];
              groups[group].push(o);
          });
          return Object.keys(groups).map(function (group:any) {
              return groups[group];
          });
      };
      const arrayGroupBy = (list:any, groupId:any) => {
          let handled:any = groupBy(list, function (item:any) {
              return [item[groupId]];
          });

          for (let i = 0; i < handled.length; i++) {
            let array:any = handled[i];
            // 初始化图标状态
            for (let i = 0; i < array.length; i++) {
              let element = array[i];
              element.status = 0;
            //   注释
              element.label = element.label?element.label:element.personname;
              element.id = element.id?element.id:element.userid;

            }
            this.handledCardItems[i] = {}
            this.handledCardItems[i].title = array[0].group
            this.handledCardItems[i].value = array;
          }          
      }
      arrayGroupBy(this.cardItems,'group');
      this.$forceUpdate();
    }


    /**
     * 树选中
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public treeSelect(event: any) {
        this.loadGroupData(event);
    }


    /**
     * 节点点击
     */
    public click_node(item: any) {
        this.icon_click(item);
    }

    /**
     * 图标点击
     */
    public icon_click(item: any) {
      if (item.status === 0) {
        let index = this.selects.indexOf(item)
        if (index == -1) {
          if (this.multiple) {
            item.status = 1;
            this.selects.push(item);
          } else {
            this.selects[0] = item;
            // 维护图标状态
            this.handledCardItems.forEach((arr:any)=>{
                arr.value.forEach((item:any)=>{
                  item.status = 0;
                })
            })
            item.status = 1;
          }
        }
      } else {
        item.status = 0;
        let index = this.selects.indexOf(item)
        this.selects.splice(index,1)
      }
      this.geticon(item);
      this.$forceUpdate();
    }

    /**
     * 获取图标
     */
    public geticon(value: any) {
        switch (value) {
            case 0:
                return 'ellipse-outline'
            case 1:
                return 'checkmark-circle-outline'
            default: 'ellipse-outline'
                break;
        }
    }

    /**
     * 确认
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public onOK() {
        this.$emit('close', this.selects);
    }
    
    /**
     * 取消
     *
     * @type {*}
     * @memberof AppMobGroupPicker
     */  
    public onCancel() {
        this.$emit('close');
    }

    public mounted(){
      this.thirdPartyInit();
    }

     /**
     * 第三方容器初始化
     * 
     * @memberof IbzDailyDailyCreateMobEditViewBase
     */
    protected  thirdPartyInit(){
    //   this.$viewTool.setViewTitleOfThirdParty(this.$t('app.title.choose'));
    //   this.$viewTool.setThirdPartyEvent(this.closeView);
    }

    /**
     * on_ok_click
     */
    public closeView() {
      this.$emit('close');
    }

}
</script>

<style lang="less">
 @import './app-mob-group-picker.less';
</style>