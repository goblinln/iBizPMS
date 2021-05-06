<template>
  <ion-page :className="{ 'view-container': true, 'default-mode-view': true }">
    <ion-header v-if="titleStatus">
      <ion-toolbar class="ionoc-view-header">
        <ion-buttons slot="start">
          <ion-button @click="closeView">
            <ion-icon name="chevron-back"></ion-icon>
            {{$t('app.button.back')}}
          </ion-button>
        </ion-buttons>
        <ion-title>
          <label class="title-label">{{$t('app.title.customDashboard')}}</label>
        </ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <div class="drag-list">
        <div class="drag-list-item added">
          <div class="header">已经添加的卡片</div>
          <draggable v-model="selectedData" handle=".end" :animation="200" @end="dragEnd">
            <div class="content" v-for="item in selectedData" :key="item.componentName">
              <div class="start">
                <app-mob-icon  @onClick="delteItem(item.id)" name="remove-circle-outline"></app-mob-icon>
              </div>
              <div class="drag-list-pic"><img  :src="item.detailImage?item.detailImage:'assets/images/add-task-list-card.jpg'" alt=""></div>
              <div class="drag-list-text">
                <div><span v-if="item.customizeTitle">{{item.customizeTitle}}</span><span v-else>{{$t( `app.portlets.${item.portletCodeName.toLowerCase()}.caption`)}}</span></div>
                <div v-if="item.detailText">{{item.detailText}}</div>
                <div v-else>暂无描述</div>
              </div>
              <div class="end">
                <app-mob-icon name="drag-point"></app-mob-icon>
              </div>
            </div>
          </draggable>
        </div>

        <div class="drag-list-item add">
          <div class="header">可添加的卡片</div>
          <template v-for="item in selectData">
            <div class="content" v-if="item.componentName" :key="item.componentName">
              <div class="start">
                <app-mob-icon @onClick="addItem(item.id)" name="add-circle-outline"></app-mob-icon>
              </div>
              <div class="drag-list-pic"><img  :src="item.detailImage?item.detailImage:'assets/images/add-task-list-card.jpg'" alt=""></div>
              <div class="drag-list-text">
                <div>{{$t( `app.portlets.${item.portletCodeName.toLowerCase()}.caption`)}}</div>
                <div v-if="item.detailText">{{item.detailText}}</div>
                <div v-else>暂无描述</div>
              </div>
              <div class="end">
                <!-- <ion-icon v-show="false" name="drag-point"></ion-icon> -->
              </div>
            </div>
          </template>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {Vue, Component,Prop, Watch} from "vue-property-decorator";
import draggable from "vuedraggable";
import { Http } from "ibiz-core";
import { UtilServiceRegister } from "ibiz-service";
@Component({
  components: {
    draggable,
  },
})
export default class AppCustomize extends Vue {

    /**
     * 已添加数据
     *
     * @type {Array}
     * @memberof AppCustomize
     */
    public selectedData?: any = [];


    public selectMode = [];

    /**
     * 视图动态参数
     *
     * @type {any}
     * @memberof AppDrawerCompponent
     */ 
    @Prop() public dynamicProps?:any;

    /**
     * 视图静态参数
     *
     * @type {any}
     * @memberof AppDrawerCompponent
     */ 
    @Prop() public staticProps?:any;

    /**
     * 传入数据list_add
     *
     * @type {Array}
     * @memberof AppCustomize
     */
    public selectData?: any = [];

    /**
     * 减少item
     *
     * @returns {void}
     * @memberof AppCustomize
     */
    public delteItem(id: any) {
        let item: any = this.selectedData.find((v: any) => v.id === id);
        let length: number = this.selectData.length;
        item.id = length + 1;
        this.selectedData.splice(id - 1, 1);
        this.selectedData.forEach((v: any, i: any) => {
        v.id = i + 1;
        });
        this.selectData.push(item);
        this.customizeChange();
    }

    /**
     * 添加item
     *
     * @returns {void}
     * @memberof AppCustomize
     */
    public addItem(id: any) {
        let item: any = this.selectData.find((v: any) => v.id === id);
        let length: number = this.selectedData.length;
        item.id = length + 1;
        this.selectData.splice(id - 1, 1);
        this.selectData.forEach((v: any, i: any) => {
        v.id = i + 1;
        });
        this.selectedData.push(item);
        this.customizeChange();
    }

    /**
     * 标题显示状态
     *
     * @returns {void}
     * @memberof AppCustomize
     */
    public titleStatus = true;

    /**
     * 拖拽结束
     *
     * @returns {void}
     * @memberof AppCustomize
     */
    public dragEnd() {
        this.customizeChange();
    }

    /**
     * 监听selectedData
     *
     * @returns {void}
     * @memberof AppCustomize
     */
    @Watch("selectedData", { immediate: true, deep: true })
    onSelectedDataChange() {
        this.selectedData.forEach((item: any, index: number) => {
          item.id = index + 1;
          if(item.modelData){
            delete item.modelData
          }
        });
    }

    /**
     * 监听selectData
     *
     * @returns {void}
     * @memberof AppCustomize
     */
    @Watch("selectData", { immediate: true, deep: true })
    onSelectDataChange() {
        this.selectData.forEach((item: any, index: number) => {
          item.id = index + 1;
        });
    }

    /**
     * 生命周期created
     *
     * @returns {void}
     * @memberof AppCustomize
     */
    public created() {
        this.loadPortletList({}, {});
        this.thirdPartyInit();
    }

    /**
     * 获取可添加仪表盘数据
     *
     * @memberof AppCustomize
     */
    public loadPortletList(context: any, viewparams: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
        Http.getInstance()
            .get("./assets/json/portlet-data.json")
            .then((response: any) => {
            if (response && response.status === 200 && response.data) {
                let result: Array<any> = [];
                if (typeof response.data == "string") {
                const index: number = response.data.lastIndexOf(",");
                result = JSON.parse(response.data);
                } else {
                result = response.data;
                }
                this.parseData(result);
                resolve({ data: result });
            }
            })
            .catch((response: any) => {
            console.log(response);
            });
        });
    }

  /**
   *  第三方容器初始化
   *
   * @type {function}
   * @memberof AppRichTextEditor
   */
  protected  thirdPartyInit(){
        // this.$viewTool.setViewTitleOfThirdParty("自定义仪表盘");
        // this.$viewTool.setThirdPartyEvent(this.closeView);
        // const thirdPartyName = this.$store.getters.getThirdPartyName();
        // if(thirdPartyName){
        //     this.titleStatus = false;
        // }
  }

    /**
     * 解析data
     *
     * @memberof AppCustomize
     */
    public parseData(data: any) {
        this.selectMode.forEach((i: any, ins: number) => {
        data.forEach((item: any, index: number) => {
            if (i.componentName == item.componentName) {
            data.splice(index, 1);
            }
        });
        });
        this.selectData = data;
        this.selectedData = this.selectMode;
    }

    public context = {};

    /**
     * modleId
     *
     * @type {string}
     * @memberof AppCustomize
     */
    public modelId: string = "";

    /**
     * 功能服务名称
     *
     * @type {string}
     * @memberof AppCustomize
     */
    public utilServiceName: string = "";

    /**
     * customizeChange
     * 
     * @memberof AppCustomize
     */
    public customizeChange() {
        this.saveModel();
    }

    /**
     * 生命周期钩子
     * 
     * @memberof AppCustomize
     */
    public mounted() {
        const {_viewparams} = this.dynamicProps;
        let parm: any = _viewparams?JSON.parse(_viewparams):null;
        if (parm) {
          this.selectMode = parm?.customModel ?parm?.customModel: [];
          const {viewparam} = parm;
          const {_context} = parm;
          this.context = _context?_context:this.context
          const data = viewparam?JSON.parse(viewparam):null
          this.modelId =data?.modelid;
          this.utilServiceName = data?.utilServiceName;
        }

    }

    /**
     * 关闭视图
     * 
     * @memberof AppCustomize
     */
    public closeView() {
        this.$emit("close", [this.selectMode]);
    }

    /**
     * 保存模型
     *
     * @param {string} serviceName
     * @param {*} context
     * @param {*} viewparams
     * @returns
     * @memberof AppDashboardDesignService
     */
    public async saveModel() {
      const data =   {utilServiceName: this.utilServiceName,modelid: this.modelId,model: this.selectedData,}
      const service:any = await UtilServiceRegister.getInstance().getService(this.context,this.utilServiceName);
      service.saveModelData( {} ,"", data);
    }
}
</script>
<style lang = "less">
@import "./app-customize.less";
</style>