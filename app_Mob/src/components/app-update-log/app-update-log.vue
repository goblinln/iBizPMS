<template>
  <van-overlay v-if="show"  class="app-update-log" :show="show" @click="onClick">
    <div class="app-update-log-content">
            <div class="wrapper" @click.stop>
            <div class="version">
            {{version}}
            </div>
            <div class="content">
                <template v-for="(i,ou) in data" >
                <div :key="ou" v-if="i.title && i.items.length>0">
                    <div class="title">{{i.title}}</div>
                    <ol>
                        <li v-for="(item,index) in i.items" :key="index">{{index+1}}.{{item.upfeatures}}</li>
                    </ol>
                </div>
                <div :key="ou" v-else-if="i.updesc" v-html="i.updesc" class="log_html"></div>
                </template>
                <div class="details"><div  @click="open" class="details_text">详情</div></div>
            </div>
        </div>
    </div>
  </van-overlay>
</template>
<script lang="ts">
import { GlobalService } from "ibiz-service";
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { updateLog } from './app-update-log';
@Component({
    components: {},
})
export default class AppUpdateLog extends Vue {

    /**
     * 显示
     *
     * @type {boolean}
     * @memberof AppUpdateLog
     */
    public show: boolean = false;

    public data:any = "";


    public version = "";

    public sysupdatelogid=""

    /**
     * 全局实体服务
     *
     * @protected
     * @type {GlobalService}
     * @memberof CounterService
     */
    protected appEntityService = new GlobalService();

    public async load(){
        let service =  await this.appEntityService.getService('SysUpdateLog');
        let data = await service.GetLastUpdateInfo({sysupdatelog:"sysupdatelog"});
        this.data = JSON.parse(data.data.updesc);
        this.opendatas = data.data;
        this.version = data.data.sysupdatelogname;
        this.sysupdatelogid = data.data.sysupdatelogid;
        let sysupdatelogid = localStorage.getItem("sysupdatelogid");
        if(sysupdatelogid){
            if(sysupdatelogid == this.sysupdatelogid){
                this.show = false;
            }else{
                this.show = true;
            }
        }else{
            this.show = true;
        }
    }

    public opendatas  :any;

    /**
     * 蒙层点击
     *
     * @memberof AppUpdateLog
     */
    public onClick() {
        this.show = false;
        localStorage.setItem("updateLogStatus", 'true');
        localStorage.setItem("sysupdatelogid", this.sysupdatelogid);
    }

    /**
     * 数据
     *
     * @memberof AppUpdateLog
     */
    public updateLog = updateLog;



    /**
     * 详情按钮点击事件
     *
     * @type {GlobalUiService}
     * @memberof AppUpdateLog
     */
    public open(){
        Object.assign(this.opendatas,{sysupdatelog:this.opendatas.sysupdatelogid})
        this.opendata([this.opendatas]);
        this.onClick();
    }

    /**
     * 打开log详情页
     *
     * @param {any[]} args
     * @param {*} [contextJO={}]
     * @param {*} [paramJO={}]
     * @param {*} [$event]
     * @param {*} [xData]
     * @param {*} [container]
     * @param {string} [srfParentDeName]
     * @returns {Promise<any>}
     * @memberof AppUpdateLog
     */
    public async opendata(args: any[], contextJO: any = {}, paramJO: any = {}, $event?: any, xData?: any, container?: any, srfParentDeName?: string): Promise<any> {
        // const params: any = { ...paramJO };
        // let context = { };
        // if (args.length > 0) {
        //     Object.assign(context, args[0]);
        // }
        // let response: any = null;
        // let panelNavParam = { } ;
        // let panelNavContext = { } ;
        // //导航参数处理
        // const { context: _context, param: _params } = this.$viewTool.formatNavigateParam( panelNavContext, panelNavParam, context, params, {});
        // const deResParameters: any[] = [];
        // const parameters: any[] = [
        //     { pathName: 'sysupdatelogs', parameterName: 'sysupdatelog' },
        //     { pathName: 'mobeditview', parameterName: 'mobeditview' },
        // ];
        // const routeParam: any = this.globaluiservice.openService.formatRouteParam(_context, deResParameters, parameters, args, _params);
        // response = await this.globaluiservice.openService.openView(routeParam);
        // if (response) {
        //     if (!response || !Object.is(response.ret, 'OK')) {
        //         return;
        //     }
        //     if (!xData || !(xData.refresh instanceof Function)) {
        //         return;
        //     }
        //     xData.refresh(response.datas);
        // }
    }


    /**
     * 生命周期
     *
     * @memberof AppUpdateLog
     */
    public created() {
        this.load();

    }
}
</script>
<style lang = "less">
@import "./app-update-log.less";
</style>