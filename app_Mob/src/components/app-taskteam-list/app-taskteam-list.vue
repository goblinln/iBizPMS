<template>
    <div class="app-taskteam-list-item">
        <div class="app-taskteam-list-item_top">
            <div v-if="item.account_img" class="account_img"><img :src="item.account_img" alt=""></div>
            <div v-else class="account_part">{{item.account_part}}</div>
            <div class="account_text"><strong>{{item.account_text}}</strong></div>
            <div class="right">
                <span class="title">消耗：</span>
                <div class="consumed">{{item.consumed}} h</div>
                <span class="title">剩余：</span>
                <div class="left">{{item.left}} h</div>
            </div>
        </div>
    </div>
</template>
<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { ImagePreview } from 'vant';
import { Environment } from "@/environments/environment";
import { CodeListService } from "ibiz-service";
@Component({
  components: { [ImagePreview.Component.name]: ImagePreview.Component, }
})
export default class appTaskTeamList extends Vue {
    /**
     * 列表项
     */
    @Prop() protected item?: any;
    @Watch('item')
    on_item_change(){
        this.parseData();
    }

    /**
     * 图片地址
     *
     * @param {*} nodes
     * @memberof EmpTreeBase
     */
    public imageUrl = 'ibizutil/download';

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
     * 解析
     */
    public parseData() {
        this.item.account_text = this.getCodeListText('UserRealName', this.item.account).label;
        if (!this.item.account_text) {
            this.item.account_text = this.item.account;
        }
        this.item.account_part = this.item.account_text.substring(0, 1);
        this.item.account_img = this.getUserImg(this.item.account);
        this.$forceUpdate();
    }

    /**
     * 获取用户头像
     */
    public getUserImg(value:string) {
        let icon = this.getCodeListText('UserRealName',value).icon;
        if (icon) {
            icon = JSON.parse(icon);
        }
        if(icon && icon[0] && icon[0].id){
            return `${this.imageUrl}/${icon[0].id}`;
        }
        return '';
    }

    /**
     * 初始化代码表
     */
    public async initCodeList():Promise<any>{
        this.UserRealName = await this.codeListService.getItems('UserRealName');
        return true
    }

    /**
     * 获取代码表文本
     */
    public getCodeListText(tag:string,id:string):any{
        let _this: any = this;
        if(!_this[tag]){
            return id;
        }
        let index = _this[tag].findIndex((item:any) => { return item.value == id })
        return index > -1 ? _this[tag][index]: id;
    }

    /**
     * 生命周期
     */
    public created(){
        this.initCodeList().then((res)=>{
            this.parseData();
        });
    }
}
</script>
<style lang="less">
@import "./app-taskteam-list";
</style>