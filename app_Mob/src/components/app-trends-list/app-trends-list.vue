<template>
    <div class="app-trends-list">
        <template v-for="item in data">
            <div :key="item.id" class="app-trends-list-item">
               <span class="cin">{{item.date1}}</span> <div class="tag"><div class="tag_border"></div><div class="tag_line"></div></div> <span class="userName">{{item.userName}} </span><span class="userName">{{item.actionName}} </span><span >{{($t('codelist.'+'Action__object_type'+'.'+item.objecttype)!== ('codelist.'+'Action__object_type'+'.'+item.objecttype))?$t('codelist.'+'Action__object_type'+'.'+item.objecttype) : item.objecttype2}}</span>
            </div>
        </template>
    </div>
</template>
<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { Environment } from "@/environments/environment";
import { CodeListServiceBase } from "ibiz-core";
@Component({
})
export default class appTrendsList extends Vue {

    /**
     * 视图参数
     *
     * @memberof appTrendsList
     */
    @Prop() protected items?: any;
    @Watch('items',{immediate:true,deep:true})
    on_items_change(newVale:any,oldValue:any){
        this.on_parse_item(newVale);
        
    }

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppReportList
     */
    public codeListService: CodeListServiceBase = new CodeListServiceBase();

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppReportList
     */
    public data = [];

    /**
     * 解析数据
     *
     * @memberof AppReportList
     */
    public async on_parse_item(items:any): Promise<any>{
        this.userData = await this.codeListService.getItems("UserRealName");
        this.actionType = await this.codeListService.getStaticItems('Action__type');
        this.actionobjType = await this.codeListService.getStaticItems('Action__object_type');
        let temp_items :any= [];
        for (let index = 0; index < items.length; index++) {
            const temp = items[index];
            temp.userName = this.getUserReName(temp.actor);
            temp.actionName = this.getActionReName(temp.actions);
            temp.objecttype2 = this.getActionobjReName(temp.objecttype)
            temp_items.push(temp);
        }
        this.data = temp_items;
    }

    /**
     * 用户数据
     *
     * @type {CodeListService}
     * @memberof AppReportList
     */
    public userData :any = [];

    /**
     * 操作数组
     *
     * @type {CodeListService}
     * @memberof AppReportList
     */
    public actionType :any =[];

    /**
     * 操作信息
     *
     * @type {CodeListService}
     * @memberof AppReportList
     */
    public actionobjType :any =[];

    /**
     * 获取用户真实姓名
     *
     * @returns {string}
     * @memberof AppReportList
     */
    public getUserReName(id: string): string {
        let index = this.userData.findIndex((item:any) => { return item.value == id })
        return index > -1 ? this.userData[index].label : id;
    }

    /**
     * 获取操作名称
     *
     * @returns {string}
     * @memberof AppReportList
     */
    public getActionReName(id: string): string {
        let index = this.actionType.findIndex((item:any) => { return item.value == id })
        return index > -1 ? this.actionType[index].label : "";
    }

    /**
     * 获取操作内容名称
     *
     * @returns {string}
     * @memberof AppReportList
     */
    public getActionobjReName(id: string): string {
        let index = this.actionobjType.findIndex((item:any) => { return item.value == id })
        return index > -1 ? this.actionobjType[index].label : "";
    }

    
}
</script>
<style lang="less">
@import "./app-trends-list.less";
</style>