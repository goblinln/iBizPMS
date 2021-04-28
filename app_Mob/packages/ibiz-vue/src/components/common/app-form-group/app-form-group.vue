<template>
        <ion-row  class="app-form-group" >
        <ion-item-divider v-if="isShowCaption" :style="{minHeight:caption?'':'10px'}"  @click="clickCollapse">
            <ion-label>
                <span :class="titleClass" class="group-title">
                    <app-mob-icon class="group-title-icon" v-if="iconName" :name="iconName"></app-mob-icon>
                {{caption}}</span>
            </ion-label>
            <app-mob-icon v-show="titleBarCloseMode !== 0" class="group-collapse" :name="collapseContant ? 'chevron-back-outline' : 'chevron-down-outline'"></app-mob-icon>
            <div :class="{'actiongroup':true,'closemode':titleBarCloseMode !== 0}" v-show="isHaveUiActionGroup">
              <div class="groupbox" v-for="item in uiActionGroup.details" :key="item.index" @click="doUIAction($event,item.name)">
                <app-mob-icon :name="item.icon" v-show="item.isShowIcon && item.visabled"/>
                <van-button plain type="info" v-show="item.isShowCaption && item.visabled">{{item.caption}}</van-button>
              </div>  
            </div>
        </ion-item-divider>
        <ion-row ref="group" v-show="!collapseContant" class="form-group-content">
            <slot></slot>
        </ion-row>
    </ion-row>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';

@Component({})
export default class AppFormGroup extends Vue {

    /**
     * 标题
     *
     * @type {string}
     * @memberof AppFormGroup
     */
    @Prop() public caption?: string;

    /**
     * 内置界面样式
     * 
     * @type {string}
     * @memberof AppFormGroup
     */
    @Prop() public uiStyle?: string;

    /**
     * 布局模式
     *
     * @type {string}
     * @memberof AppFormGroup
     */
    @Prop() public layoutType?: string;

    /**
     * 标题样式
     *
     * @type {string}
     * @memberof AppFormGroup
     */    
    @Prop() public titleStyle?:string;

    /**
     * 是否显示标题
     *
     * @type {boolean}
     * @memberof AppFormGroup
     */
    @Prop({ default: true }) public isShowCaption!: boolean;

    /**
     * 信息面板模式
     *
     * @type {boolean}
     * @memberof AppFormGroup
     */
    @Prop({ default: false }) public isInfoGroupMode!: boolean;

    /**
     * 界面行为组
     *
     * @type {*}
     * @memberof AppFormGroup
     */
    @Prop() public uiActionGroup?: any;

    /**
     * 是否有界面行为组
     *
     * @type {*}
     * @memberof AppFormGroup
     */
    get isHaveUiActionGroup(){
      if (this.uiActionGroup.details.length > 0) {
        return true;
      } else {
        return false;
      }
    }

    /**
     * 界面行为组标题
     *
     * @type {*}
     * @memberof AppFormGroup
     */
    @Prop() public groupUiAction?:string;    

    /**
     * 图标名称
     *
     * @type {string}
     * @memberof AppFormGroup
     */
    @Prop() public iconName?: string;

    /**
     * 标题栏关闭模式
     * 0: 不支持关闭
     * 1: 默认打开
     * 2： 默认关闭
     *
     * @type {(number | 0 | 1 | 2)} 
     * @memberof AppFormGroup
     */
    @Prop({ default: 0 }) public titleBarCloseMode!: number | 0 | 1 | 2;

    /**
     * 收缩内容
     *
     * @type {boolean}
     * @memberof AppFormGroup
     */
    public collapseContant: boolean = false;

    /**
     * 计算样式
     *
     * @readonly
     * @type {string[]}
     * @memberof AppFormGroup
     */
    get classes(): string[] {
        return [
            'app-form-group',
            this.isShowCaption && this.collapseContant ? 'app-group-collapse-contant' : '',
            this.isInfoGroupMode ? 'app-info-group-mode' : '',
            Object.is(this.layoutType, 'FLEX') ? 'app-group-flex': ''
        ];
    }

    /**
     * 标题样式
     *
     * @readonly
     * @type {string}
     * @memberof AppFormGroup
     */
    get titleClass():string{
        return this.titleStyle?this.titleStyle:'';
    }

    /**
     * vue 生命周期
     *
     * @memberof AppFormGroup
     */
    public created() {
        this.collapseContant = this.titleBarCloseMode === 2 ? true : false;
    }

    /**
     * 触发收缩
     *
     * @memberof AppFormGroup
     */
    public clickCollapse(): void {
        if(this.titleBarCloseMode === 0) {
            return;
        }
        this.collapseContant = !this.collapseContant;
    }

    /**
     * 执行界面行为
     *
     * @param {*} $event
     * @memberof AppFormGroup
     */
    public doUIAction($event: any, item: any): void {
      this.uiActionGroup.details.map((detail: any, i: number) => {
        if (item == detail.name) {
          this.$emit('groupuiactionclick', { event: $event, item: detail });
        }
      })
    }

    /**
     * 生命周期
     *
     * @type {string}
     * @memberof AppFormGroup
     */
    public mounted() {
        this.removeExtraLines();
    }

    public removeExtraLines(){
        // 获取到group节点，其最后一项没有下划线
        let groupList: any = this.$refs.group;
        if (groupList && groupList.lastElementChild) {
            groupList.lastElementChild.lines = "none";
        }
        for (let item of groupList.children) {
            if (item.localName && item.localName === "ion-item" && item.nextElementSibling && item.nextElementSibling.localName === "ion-row") {
                item.lines = "none";
            }
        }
    }

    /**
     * 注入的UI服务
     *
     * @type {*}
     * @memberof AppFormGroup
     */
    @Prop() public uiService!: any;

    /**
     * 注入数据
     *
     * @type {*}
     * @memberof AppFormGroup
     */
    @Prop() public data!: any;

    /**
     * 监听值变化
     *
     * @memberof AppFormGroup
     */
    @Watch('data',{ deep: true })
    onDataChange(newVal: any, oldVal: any) {
        if((newVal !== oldVal) && this.uiActionGroup.details.length >0){
            this.calcActionItemAuthState(newVal,this.uiActionGroup.details,this.uiService);
        }
    }

    /**
     * 计算界面行为项权限状态
     *
     * @param {*} [data] 传入数据
     * @param {*} [ActionModel] 界面行为模型
     * @param {*} [UIService] 界面行为服务
     * @memberof AppFormGroup
     */
    public calcActionItemAuthState(data:any,ActionModel:any,UIService:any){
        for (const key in ActionModel) {
            if (!ActionModel.hasOwnProperty(key)) {
                return;
            }
            const _item = ActionModel[key];
            if(_item && _item['dataaccaction'] && UIService){
                let dataActionResult:any;
                if(Object.is(_item['actiontarget'],"NONE") || Object.is(_item['actiontarget'],"")){
                    dataActionResult = UIService.getResourceOPPrivs(_item['dataaccaction']);
                }else{
                    if(data && Object.keys(data).length >0){
                        dataActionResult = UIService.getAllOPPrivs(data)[_item['dataaccaction']];
                    }
                }
                // 无权限:0;有权限:1
                if(dataActionResult === 0){
                    // 禁用:1;隐藏:2;隐藏且默认隐藏:6
                    if(_item.noprivdisplaymode === 1){
                        _item.disabled = true;
                    }
                    if((_item.noprivdisplaymode === 2) || (_item.noprivdisplaymode === 6)){
                        _item.visabled = false;
                    }else{
                        _item.visabled = true;
                    }
                }
                if(dataActionResult === 1){
                    _item.visabled = true;
                    _item.disabled = false;
                }
            }
        }
    } 
}
</script>
<style lang='less'>
@import './app-form-group.less';
</style>