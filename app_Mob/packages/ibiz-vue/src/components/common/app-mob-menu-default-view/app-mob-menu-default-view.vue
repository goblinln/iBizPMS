<template>
    <ion-tabs ref="ionNav" class="app-mob-menu-default-view">
        <template v-for="item in items">
                <template v-if="!item.hidden">
                    <ion-tab :key="item.name" :tab="item.name" >
                        <app-view-shell  v-if="item.name == activeId && item.name != 'setting'"  :staticProps="{viewDefaultUsage:'indexView',viewModelData:item.viewModelData}"></app-view-shell>
                        <component v-else-if="item.name == 'setting'" :is="item.componentname"></component>
                    </ion-tab>
                </template>
        </template>
        <template>
        </template>
        <ion-tab-bar slot="bottom">
            <template v-for="item in items">
                <template v-if="!item.hidden">
                    <ion-tab-button :tab="item.name" :key="item.name" :selected="item.name == activeId" @click="active(item)">
                        <ion-icon :name="getIconName(item)"></ion-icon>
                        <ion-label v-if="item.appfunctag != 'settings'">{{item.caption}}</ion-label>
                        <ion-label v-else>{{item.text}}</ion-label>
                        <ion-badge color="danger" v-if="counterServide && counterServide.counterData && counterServide.counterData[item.counterid]"><ion-label>{{counterServide.counterData[item.counterid]}}</ion-label></ion-badge>
                    </ion-tab-button>
                </template>
            </template>
        </ion-tab-bar>
    </ion-tabs>
</template>

<script lang="ts">
import { Vue, Component, Prop, Emit, Model } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import { DynamicService, ViewTool } from "ibiz-core";
@Component({
    components: {
    }
})
export default class AppMobMenuDefaultView extends Vue {


    /**
     * 使用默认菜单
     *
     * @type {*}
     * @memberof AppMobMenuDefaultView
     */
    public useDefaultMenu:boolean = Environment.useDefaultMenu;

    /**
     * 双向值绑定
     *
     * @type {*}
     * @memberof AppMobMenuDefaultView
     */
    @Model("change") readonly itemValue?: any;


    /**
     * 获取值
     *
     * @type {*}
     * @memberof AppMobMenuDefaultView
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
     * @memberof AppMobMenuDefaultView
     */
    set defaultActive(val) {
        this.$emit("change", val);
    }

    /**
     * 菜单名称
     *
     * @type {string}
     * @memberof AppMobMenuDefaultView
     */
    @Prop() public menuName!: string;

    /**
     * 菜单数据项
     *
     * @type {Array<any>}
     * @memberof AppMobMenuDefaultView
     */
    @Prop() public items!: Array<any>;

    /**
     * 菜单模型
     *
     * @type {Array<any>}
     * @memberof AppMobMenuDefaultView
     */
    @Prop() public menuModels!: Array<any>;

    /**
     * 计数器名称
     *
     * @type {string}
     * @memberof AppMobMenuDefaultView
     */
    @Prop() public counterName!: string;

    /**
     * 激活id
     *
     * @type {string}
     * @memberof AppMobMenuDefaultView
     */
    public activeId = "";

    public getIconName(item:any){
        if (item.getPSSysImage?.cssClass) {
          return ViewTool.setIcon(item.getPSSysImage?.cssClass);
        } else {
          return 'home';
        }
    }

    public defaultMenu =  {
        appfunctag: "settings", 
        componentname: "app-setting",
        expanded: false,
        hidden: false,
        hidesidebar: false,
        icon: "",
        getPSSysImage:
          {cssClass:"settings"}
        ,
        id: "setting",
        name: "setting",
        opendefault: false,
        resourcetag: "",
        separator: false,
        text: "设置",
        textcls: "",
        tooltip: "设置",
        type: "MENUITEM",
    };

    /**
     * 解析菜单模型数据
     *
     * @memberof AppMobMenuDefaultView
     */
    public parseMenuData(){
        for (let index = 0; index < this.items.length; index++) {
            const item = this.items[index];
            let model = this.menuModels.find((model:any) => Object.is(model.appfunctag, item?.getPSAppFunc?.codeName));
            if (model) {
                const {getPSAppView:$appView,appFuncType} = model;
                if(appFuncType =='APPVIEW' && $appView){
                         item.viewModelData = $appView;
                }
            }
        }
        let index = this.items.findIndex((temp:any)=>{return temp.opendefault });
        if(index!= -1){
            this.activeId = this.items[index].name;
        }else{
            this.activeId = this.items[0].name;
        }
        this.$forceUpdate();
    }

    /**
     * 生命周期
     *
     * @memberof AppMobMenuDefaultView
     */
    public created() {
        if(this.useDefaultMenu && this.items.findIndex((item)=>{return item.id == this.defaultMenu.id}) < 0){
            this.items.push(this.defaultMenu);
        }
        this.parseMenuData();
    }

    /**
     * 点击菜单事件
     *
     * @memberof AppMobMenuDefaultView
     */    
    public active(val:any) {
        const index :number = this.items.findIndex((item: any) => Object.is(item.name, val.name));
        this.activeId = this.items[index].name; 
        sessionStorage.setItem("currId",this.items[index].name)
    }

    /**
     * 计数器数据
     *
     * @type {*}
     * @memberof AppMobMenuDefaultView
     */
    public counterdata: any = {};

    /**
     * vue 生命周期
     *
     * @memberof AppMobMenuDefaultView
     */
    public destroyed() {
        if(this.counterServide){
            this.counterServide.destroyCounter();
        }
    }

    /**
     * 计数器
     *
     * @memberof AppMobMenuDefaultView
     */
    public counterServide:any = null;

    /**
     * 加载计数器数据
     *
     * @returns {Promise<any>}
     * @memberof AppMobMenuDefaultView
     */
    public async loadCounterData(): Promise<any> {
        // const counterServiceConstructor = window.counterServiceConstructor;
        // this.counterServide = await counterServiceConstructor.getService(this.counterName);
    }

    /**
     * 生命周期
     *
     * @memberof AppMobMenuDefaultView
     */
    public mounted() {
        let ionNav:any = this.$refs.ionNav;
        let currPage = sessionStorage.getItem("currId");
        if(currPage){
            this.items.forEach((item:any,index:number) => {
                if(item.name == currPage){
                    this.activeId =  item.name       
                    ionNav.select(item.name);
                }
            })
        } else {
            let item:any =  this.items.find((item: any) => Object.is(item.name, this.activeId));
            if (ionNav && ionNav.select) {
              ionNav.select(item.name);
            }
        }
    }
}
</script>

<style lang="less">
@import "./app-mob-menu-default-view.less";
</style>