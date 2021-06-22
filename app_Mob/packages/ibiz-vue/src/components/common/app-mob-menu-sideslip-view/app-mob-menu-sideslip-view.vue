<template>
    <div class="app-mob-menu-sideslip-view"> 
      <van-popup v-model="showPopup" get-container="#app" position="left" :style="{ height: '100%',width: '75%' }" duration="0.2" :close-on-popstate="true">
        <v-touch v-on:swipeleft="onSwipeLeft" style="height:100%;">
              <div class="app-menu-plugin">
                <Header></Header>
                <div class="top">
                  <div class="title">Menu</div>
                  <template v-for="item in items"  >
                    <template v-if="!item.hidden">
                      <div class="list" :key="item.index" @click="active(item)" :class="{'active':item.id == activeId}">
                        <app-mob-icon :name=" item.iconcls ? item.iconcls : 'home' "></app-mob-icon>
                        <div class="text">
                          <ion-label v-if="item.appfunctag != 'settings'">{{$t(`app.menus.${menuName}.${item.name}`)}}</ion-label>
                          <ion-label v-else>{{item.text}}</ion-label>
                          <van-tag round type="danger" size="medium" v-if="counterServide && counterServide.counterData && counterServide.counterData[item.counterid]">{{counterServide.counterData[item.counterid]}}</van-tag>
                          <!-- <ion-badge color="danger" v-if="counterServide && counterServide.counterData && counterServide.counterData[item.counterid]"><ion-label>{{counterServide.counterData[item.counterid]}}</ion-label></ion-badge> -->
                        </div>
                      </div>
                    </template>
                  </template>
                </div>
              </div>
        </v-touch>
      </van-popup>

      <v-touch v-on:panright="onSwipeRight" style="height:100%;">
        <template v-for="item in items">
                <template v-if="!item.hidden">
                        <component  :key="item.id" v-if="item.id == activeId" :is="item.componentname" viewDefaultUsage="indexView"></component>
                </template>
        </template>
      </v-touch>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Emit, Model, Provide } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import Header from './app-mob-menu-sideslip-view-header/app-mob-menu-sideslip-view-header.vue';
@Component({
    components: {
      Header
    },
    i18n: {
        messages: {
            'ZH-CN': {
                set: '设置',
            },
            'EN-US': {
                set:'Setting',
            }
        }
    }     
})
export default class AppMobMenuSideslipView extends Vue {

    /**
     * 是否显示侧滑菜单
     *
     * @type {*}
     * @memberof AppMobMenuSideslipView
     */
    public showPopup:boolean = false;

    /**
     * 使用默认菜单
     *
     * @type {*}
     * @memberof AppMobMenuSideslipView
     */
    public useDefaultMenu:boolean = Environment.useDefaultMenu;

    /**
     * 双向值绑定
     *
     * @type {*}
     * @memberof AppMobMenuSideslipView
     */
    @Model("change") readonly itemValue?: any;


    /**
     * 获取值
     *
     * @type {*}
     * @memberof AppMobMenuSideslipView
     */
    get defaultActive(): any {
        this.items.some((item: any) => {
            if (Object.is(item.name, this.itemValue)) {
                item.select = true;
                return true;
            }
            return false;
        });
        return this.itemValue;
    }

    /**
     * 设置值
     *
     * @memberof AppMobMenuSideslipView
     */
    set defaultActive(val) {
        this.$emit("change", val);
    }

    /**
     * 菜单名称
     *
     * @type {string}
     * @memberof AppMobMenuSideslipView
     */
    @Prop() public menuName!: string;

    /**
     * 菜单数据项
     *
     * @type {Array<any>}
     * @memberof AppMobMenuSideslipView
     */
    @Prop() public items!: Array<any>;

    /**
     * 菜单模型
     *
     * @type {Array<any>}
     * @memberof AppMobMenuSideslipView
     */
    @Prop() public menuModels!: Array<any>;

    /**
     * 计数器名称
     *
     * @type {string}
     * @memberof AppMobMenuSideslipView
     */
    @Prop() public counterName!: string;

    /**
     * 激活id
     *
     * @type {string}
     * @memberof AppMobMenuSideslipView
     */
    public activeId = "";

    /**
     * 默认菜单
     *
     * @type {Object}
     * @memberof AppMobMenuSideslipView
     */
    public defaultMenu =  {
        appfunctag: "settings",
        componentname: "app-setting",
        expanded: false,
        hidden: false,
        hidesidebar: false,
        icon: "",
        iconcls: "settings",
        id: "setting",
        name: "setting",
        opendefault: false,
        resourcetag: "",
        separator: false,
        text: this.$t('set'),
        textcls: "",
        tooltip: this.$t('set'),
        type: "MENUITEM",
    };

    /**
     * 计数器
     *
     * @memberof AppMobMenuSideslipView
     */
    public counterServide:any = null;

    /**
     * 计数器数据
     *
     * @type {*}
     * @memberof AppMobMenuSideslipView
     */
    public counterdata: any = {};

    /**
     * 生命周期
     *
     * @memberof AppMobMenuSideslipView
     */
    public created() {
        if(this.useDefaultMenu){
            this.items.push(this.defaultMenu);
        }
        let count = 0;
        this.items.forEach((item:any,index:number) => {
            if(item.opendefault == true){
                this.activeId = item.id;
            } else if(item.hidden == false){
              // 没有默认打开的话
                count++;
                if (count == this.items.length) {
                  this.activeId = this.items[0].id;
                }
            }
            let model = this.menuModels.find((model:any) => Object.is(model.appfunctag, item.appfunctag));
            if (model) {
                item.componentname = model.componentname;
            }
        });
        this.loadCounterData();
    }

        /**
     * 生命周期
     *
     * @memberof AppMobMenuSideslipView
     */
    public mounted() {
        let currPage = sessionStorage.getItem("currId");
        if(currPage){
            this.items.forEach((item:any,index:number) => {
                if(item.id == currPage){
                    this.activeId =  item.id       
                }
            })
        }
    }

    /**
     * 点击菜单事件
     *
     * @memberof AppMobMenuSideslipView
     */    
    public active(val:any) {
        const index :number = this.items.findIndex((item: any) => Object.is(item.id, val.id));
        this.activeId = this.items[index].id; 
        sessionStorage.setItem("currId",this.items[index].id)
        this.showPopup = false;
    }

    /**
     * vue 生命周期
     *
     * @memberof AppMobMenuSideslipView
     */
    public destroyed() {
      if(this.counterServide){
        this.counterServide.destroyCounter();
      }
    }

    /**
     * 加载计数器数据
     *
     * @returns {Promise<any>}
     * @memberof AppMobMenuSideslipView
     */
    public async loadCounterData(): Promise<any> {
        // const counterServiceConstructor = window.counterServiceConstructor;
        // this.counterServide = await counterServiceConstructor.getService(this.counterName);
    }

    /**
     * 菜单选中事件
     *
     * @param {*} val
     * @returns
     * @memberof AppMobMenuSideslipView
     */
    @Emit()
    select(val: any) {
        return val;
    }

    /**
     * 选中菜单项
     *
     * @param {*} $event
     * @returns {void}
     * @memberof AppMobMenuDefaultView
     */
    public selectItem($event: any): void {
        if (!$event) {
            return ;
        }
        let { detail } = $event;
        if (!detail) {
            return ;
        }
        let { tab }  = detail;
        if (!tab) {
            return;
        }
    }

    /**
     * 向左滑动关闭弹出层
     *
     * @type {*}
     * @memberof AppMobMenuSideslipView
     */
    public onSwipeLeft(e:any){
      this.showPopup = false;
    }

    /**
     * 向右滑动打开弹出层
     *
     * @type {*}
     * @memberof AppMobMenuSideslipView
     */
    public onSwipeRight(){
      if (this.$store.state.popupStatus) {
        this.showPopup = true;
      }
    }
}
</script>

<style lang="less">
@import "./app-mob-menu-sideslip-view.less";
</style>