<template>
    <div class="app-case-list-item">
        <div class="app-case-list-item_top">
            <div class="pri" :class="item.pri_className">{{item.pri_text}}</div>
            <strong><div class="name" :style="{'color':item.color}"> {{item.srfmajortext}}</div></strong>
        </div>
        <div class="app-case-list-item_bottom">
            <div class="speace">{{item.type_text}}</div>
            <div class="status" :style="{'color':item.status_color}">{{item.status_text}}</div>
            <div class="status" :style="{'color':item.status_color}">{{item.result_text}}</div>
            <div class="openeddate">{{item.openeddate}}</div>
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
    export default class appCaseList extends Vue {

        /**
         * 列表项
         */
        @Prop() protected item?: any;
        @Watch('item')
        on_item_change(){
            this.parseData();
        }

        //public assignedtoArr :string[] = [];

        /**
         * 任务优先级代码表
         */
        public testcase__pri: any;

        /**
         * 任务状态代码表
         */
        public testcase__status: any;

        /**
         * 用户真实名称
         */
        public UserRealName: any;

        /**
         * 用例结果
         */
        public testcase__result : any;

        /**
         * 用例结果
         */
        public testcase__type : any;


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
        public parseData(){
            this.item.pri_text = this.getCodeListText('testcase__pri',this.item.pri).label;
            this.item.pri_className = this.getCodeListText('testcase__pri',this.item.pri).className;
            this.item.status_text = this.getCodeListText('testcase__status',this.item.status).label;
            this.item.status_color = this.getCodeListText('testcase__status',this.item.status).color;
            this.item.result_text = this.getCodeListText('testcase__result',this.item.lastrunresult).label;
            this.item.type_text = this.getCodeListText('testcase__type',this.item.type).label;
            this.item.openeddate = this.item.openeddate.substring(5,10);
            this.$forceUpdate();
        }

        /**
         * 初始化代码表
         */
        public async initCodeList():Promise<any>{
            this.testcase__pri = this.$store.getters.getCodeList('Testcase__pri').items;
            this.testcase__status = this.$store.getters.getCodeList('Testcase__status').items;
            this.testcase__result =this.$store.getters.getCodeList('Testcase__result').items;
            this.UserRealName = await this.codeListService.getItems('UserRealName');
            this.testcase__type = this.$store.getters.getCodeList('Testcase__type').items;
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
    @import "./app-case-list";
</style>