<template>
<ion-page :className="{ 'view-container': true, 'default-mode-view': true, 'demobeditview': true, 'ibz-daily-daily-create-mob-edit-view': true }">
    <ion-header>
        <ion-toolbar v-show="titleStatus" class="ionoc-view-header">
            <ion-buttons slot="start">
                <ion-button  @click="closeView">
                    <ion-icon name="chevron-back"></ion-icon>
                    {{$t('app.button.back')}}
                </ion-button>
            </ion-buttons>
            <ion-title><label class="title-label">{{$t('app.title.choose')}}</label></ion-title>
        </ion-toolbar>
    </ion-header>
  <div class="app-tree">
    <div class="app-mob-treeview sysemployee-tree">
      <div class="treeNav">
        <template v-for="(item, index) in treeNav">
          <span :key="item.id" class="sc-ion-label-ios-h sc-ion-label-ios-s ios hydrated" :class="index + 1 < treeNav.length ? 'treeNav-active' : ''" @click="nav_click(item)">{{ item.label }}</span>
          <span class="tree-span" :key="item.id + 'span'" v-if="index + 1 < treeNav.length">></span>
        </template>
      </div>
      <div class="tree-partition" v-if="valueNodes.length > 0"></div>
      <ion-list>
        <template v-for="(item, index) in renderData">
          <ion-item :key="index" @click="click_node(item)">
            <ion-label>{{ item.label }}<span class="node_length" v-if="item.children && item.children.length > 0"> ({{item.children.length}})</span></ion-label>
            <app-mob-icon class="tree-icon" position="end" :name="geticon(item.status)" @onClick="icon_click(item)"></app-mob-icon>
          </ion-item>
        </template>
      </ion-list>
      <div class="ok_button" @click="on_ok_click" >{{$t('app.button.confirm')}}</div>
    </div>
  </div>
</ion-page>
</template>
<script lang="ts">
import { Vue, Component, Prop, Watch, } from "vue-property-decorator";

@Component({
    components: {},
    i18n: {
        messages: {
            'ZH-CN': {
                choose: '选择',

            },
            'EN-US': {
                choose:'Choose',
            }
        }
    }      
})
export default class AppTree extends Vue {

    /**
     * 选择项
     *
     * @type {string}
     * @memberof AppTree
     */
    public selectTreeValue?: string;


    @Prop() public dynamicProps?: string;


    @Watch('dynamicProps', { immediate: true })
    on_dynamicProps_change(newVal: any, oldVal: any) {
      const _context = newVal._context;
      let _viewparams :any;
      if(newVal._viewparams){
        _viewparams = typeof newVal._viewparams == 'string'?JSON.parse(newVal._viewparams):newVal._viewparams;
      }
      const _this: any = this;
      if(!_viewparams){
        return;
      }
      for (let index = 0; index < Object.keys(_viewparams).length; index++) {
        const item = Object.keys(_viewparams)[index];
        _this[item] = _viewparams[item];
      }
    }

    @Watch('selectTreeValue', { immediate: true })
    on_selectTreeValue_change(newVale: any, oldVal: any) {
        this.cacheValue = newVale ? JSON.parse(newVale) : [];
        this.curValue = newVale ? JSON.parse(newVale) : [];
    }

    /**
     * 缓存值
     *
     * @type {any[]}
     * @memberof AppTree
     */
    public cacheValue: any = [];

    public titleStatus: boolean = true;

     /**
     * 设置工具栏状态
     *
     * @memberof IbzDailyDailyCreateMobEditViewBase
     */
    public setViewTitleStatus(){
        const thirdPartyName = this.$store.getters.getThirdPartyName();
        if(thirdPartyName){
            this.titleStatus = false;
        }
    }

    /**
     * 当前选中值 
     *
     * @type {any[]}
     * @memberof AppTree
     */
    public curValue: any[] = [];


    /**
     * 是否多选
     */
    public multiple?: boolean;

    /**
     * 父节点数据
     */
    public rootNodes: any = [];

    /**
     * 子节点数据
     */
    public valueNodes: any = [];

    /**
     * 树导航栏数据
     */
    public treeNav: any[] = [{ id: 'main', label: 'main' }];

    /**
     * 获取图标
     */
    public geticon(value: any) {
        switch (value) {
            case 0:
                return 'ellipse-outline'
            case 1:
                return 'checkmark-circle-outline'
            case 2:
                return 'remove-circle-outline'
            default: 'ellipse-outline'
                break;
        }
    }

    /**
     * 树数据
     */
    public nodesData?: any[];

    /**
     * nodesData值变化
     */
    @Watch("nodesData", { immediate: true, deep: true })
    on_nodesData_change(newVale: any, oldVal: any) {
        if(!newVale){
          return 
        }
        this.parseNodes(newVale, true);
    }

    /**
     * 渲染数据
     */
    public renderData: any = [];

    /**
     * 解析节点数据
     *
     * @param {*} nodes
     * @memberof EmpTreeBase
     */
    public parseNodes(nodes: any, isRender: boolean = false) {
        if (isRender) {
            this.renderData = []
        }
        for (let index = 0; index < nodes.length; index++) {
            const node: any = nodes[index];
            if (isRender) {
                this.renderData.push(Object.assign({ status: this.getSelectStatus(node) }, node));
            }
        }
    }

    /**
     * 设置状态属性
     * 0未选择
     * 1选择
     * 部分选择
     * @param {*} nodes
     * @memberof EmpTreeBase
     */
    public getSelectStatus(node: any): number {
        if (this.curValue.length <= 0) {
            return 0
        }
        if (node.children?.length >0) {
            let temp_arr = [];
            if(!node.children){
              return 0;
            }
            for (let index = 0; index < node.children.length; index++) {
                const node_item = node.children[index];
                temp_arr.push(this.getSelectStatus(node_item));
            }
            if (temp_arr.every((item: any) => { return Object.is(item, 1) })) {
                return 1;
            }
            if (temp_arr.every((item: any) => { return Object.is(item, 0) })) {
                return 0;
            }
            if (temp_arr.some((item: any) => { return Object.is(item, 1) }) || temp_arr.some((item: any) => { return Object.is(item, 2) })) {
                return 2;
            }
        } else {
          return this.curValue.some((item: any) => { return Object.is(item.id, node.id) }) ? 1 : 0;
        }
        return 0;
    }

    /**
     * 导航点击
     */
    public nav_click(item: any) {
        if (item.id == this.treeNav[this.treeNav.length - 1].id) {
            return
        }
        const count = this.treeNav.findIndex((i: any) => item.id === i.id) + 1;
        this.treeNav.splice(count, this.treeNav.length - count);
        if (Object.is(item.id, 'main')) {
            this.parseNodes(this.nodesData, true)
        } else {
            this.click_node(item);
            this.parseNodes(item, true)
        }
    }

    /**
     * 节点点击
     */
    public click_node(item: any) {
        if (item.children?.length > 0) {
            this.treeNav.push(item);
            this.parseNodes(item.children, true)
            return
        }
        this.icon_click(item);
    }

    /**
     * 图标点击
     */
    public icon_click(item: any) {
        if( this.multiple){
          const _this = this;
          const node_index = this.curValue.findIndex((_item: any) => { return Object.is(item.id, _item.id) });
          let temp_allNode = this.getNodeChild(item);
          if(node_index == -1) {
            item.status = 1
          }
          for (let index = 0; index < temp_allNode.length; index++) {
            const temp_node = temp_allNode[index];
            if(node_index > -1){
              const _index = _this.curValue.findIndex((_item: any) => { return Object.is(temp_node.id, _item.id) })
              _this.curValue.splice(_index, 1);
            }else{
              if (this.curValue.findIndex((_item: any) => { return Object.is(_item.id, temp_node.id) }) == -1) {
                  this.curValue.push(temp_node);
              }
            }
          }
        }else{
          this.curValue = [item];
        }
        let currNav = this.treeNav[this.treeNav.length - 1];
        this.parseNodes(Object.is(currNav.id, 'main') ? this.nodesData : currNav.children, true);
        this.$forceUpdate();
    }

    /**
     * 获取当前节点所有子节点 包括当前节点
     */
    public getNodeChild(node: any): any[] {
        let allchildren: any[] = [];
        allchildren.push(node);
        if(node.children){
          for (let index = 0; index < node.children.length; index++) {
              const element = node.children[index];
                if (element.children && element.children.length > 0) {
                    allchildren.push(this.getNodeChild(element));
                } else {
                    allchildren.push(element)
                }
            }
        }
        return allchildren;
    }

    /**
     * on_ok_click
     */
    public on_ok_click() {
      this.$emit('close',JSON.parse(JSON.stringify(this.curValue)));
    }

    public mounted(){
      this.thirdPartyInit();
    }


    public created(){
      this.setViewTitleStatus();
    }

     /**
     * 第三方容器初始化
     * 
     * @memberof IbzDailyDailyCreateMobEditViewBase
     */
    protected  thirdPartyInit(){
      // this.$viewTool.setViewTitleOfThirdParty(this.$t('choose'));
      // this.$viewTool.setThirdPartyEvent(this.closeView);
    }


    /**
     * on_ok_click
     */
    public closeView() {
      this.$emit('close');
    }
}
</script>
<style lang = "less">
@import "./app-tree.less";
</style>