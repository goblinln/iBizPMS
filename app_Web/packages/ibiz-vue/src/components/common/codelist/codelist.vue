<template>
    <span v-if="textOnly"> {{text}} </span>
    <span v-else class='codelist'> 
        <span v-if="isEmpty">{{$t('codelist.'+codeList.codeName+'.empty')}}</span>
        <template v-if="!isEmpty">
          <template v-for="(item, index) in items">
              <span class="codelist-item" :key="index">
                    <i v-if="getIconClass(item)" :class="getIconClass(item)"></i>
                    <img v-if="isIconImage(item)" :src="item.imgUrlBase64" />
                    <span :class="getTextClass(item)" :style="{ color: item.color }">
                        {{ $t(item.text) || item.text }}
                    </span>
                    <span v-if="index != items.length-1">{{ textSeparator }}</span>
               </span>
          </template>
        </template>
    </span>
</template>

<script lang="ts">
import { Vue, Component, Prop, Model, Watch } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import { CodeListTranslator } from 'ibiz-vue';
import { ImgurlBase64 } from 'ibiz-core';

@Component({})
export default class CodeList extends Vue {
    /**
     * 代码表翻译服务
     *
     * @type {CodeListService}
     * @memberof CodeList
     */  
    public codeListTranslator:CodeListTranslator = new CodeListTranslator();

    /**
     * 代码表模型
     *
     * @type {string}
     * @memberof CodeList
     */
    @Prop() public codeList!: any;

    /**
     * 当前值
     * @type {any}
     * @memberof CodeList
     * 
     */
    @Prop() public value?: string;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof CodeList
     */
    @Prop() public data?: any;

    /**
     * 局部上下文导航参数
     * 
     * @type {any}
     * @memberof CodeList
     */
    @Prop() public localContext!:any;

    /**
     * 局部导航参数
     * 
     * @type {any}
     * @memberof CodeList
     */
    @Prop() public localParam!:any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof CodeList
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof CodeList
     */
    @Prop() public viewparams!: any;

    /**
     * 是否只显示文字
     *
     * @type {*}
     * @memberof CodeList
     */
    @Prop() public textOnly?: boolean;

    /**
     * 显示文本
     *
     * @memberof CodeList
     */
    public text: string = '';

    /**
     * 显示文本
     *
     * @memberof CodeList
     */
    public textSeparator: string = '、';

    /**
     * 显示数据集合
     *
     * @type {any[]}
     * @memberof CodeList
     */
    public items: any[] = [];

    /**
     * 是否是空
     *
     * @type {any[]}
     * @memberof CodeList
     */
    public isEmpty: boolean = false;

    /**
     * vue  生命周期
     *
     * @memberof CodeList
     */
    public created() {
        this.dataHandle();
    }

    /**
     * 数据值变化
     *
     * @param {*} newval
     * @param {*} val
     * @returns
     * @memberof CodeList
     */
    @Watch('value')
    public onValueChange(newVal: any, oldVal: any) {
        this.dataHandle();
    }

    /**
     * 监听表单数据变化
     * 
     * @memberof CodeList
     */
    @Watch('data',{immediate:true,deep:true})
    onDataChange(newVal: any, oldVal: any) {
        if(newVal){
            this.dataHandle();
        }
    }

    /**
     * 获取图标class
     * 
     * @param {*} item 代码表项
     * @memberof CodeList
     */
    public getIconClass(item: any){
        return item?.getPSSysImage?.cssClass;
    }

    /**
     * 图标是否是图片
     * 
     * @param {*} item 代码表项
     * @memberof CodeList
     */
    public isIconImage(item: any){
        return !!(item?.getPSSysImage?.imagePath);
    }

    /**
     * 获取文本class
     * 
     * @param {*} item 代码表项
     * @memberof CodeList
     */
    public getTextClass(item: any){
        return item?.textCls || item?.getPSSysCss?.cssName;
    }

    /**
     * 数据处理
     *
     * @memberof CodeList
     */
    private dataHandle(){
        if(!this.codeList){
            return;
        }
        //设置初始默认值
        this.text = '';
        this.items = [];
        this.textSeparator = this.codeList.textSeparator || this.textSeparator;

        // 公共参数处理
        let data: any = {};
        this.handlePublicParams(data);
        // 参数处理
        let _context = data.context;
        let _param = data.param;

        // 只显示文本
        if(this.textOnly){
            this.codeListTranslator.getCodeListText(this.value, this.codeList, this, _context,_param).then((response: any)=>{
                this.text = response;
            })
        }else{
            this.codeListTranslator.getSelectedCodeListItems(this.value, this.codeList, this, _context,_param).then(async (response: any)=>{
                this.items = await this.getImgURLOfBase64(response);
                this.isEmpty = !(this.items?.length > 0);
            })
        }
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof CodeList
     */
    public handlePublicParams(arg: any) {
        // 合并表单参数
        arg.param = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        arg.context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length >0) {
            let _context = this.$util.computedNavData(this.data,arg.context,arg.param,this.localContext);
            Object.assign(arg.context,_context);
        }
        if (this.localParam && Object.keys(this.localParam).length >0) {
            let _param = this.$util.computedNavData(this.data,arg.param,arg.param,this.localParam);
            Object.assign(arg.param,_param);
        }
    }

    /**
     * 获取图片路径
     *
     * @param {*} item
     * @returns
     * @memberof CodeList
     */
    public getIconImagePath(item:any){
        let path = item.getPSSysImage.imagePath;
        if(!path){
            return;
            }
        if(Object.prototype.toString.call(path)=="[object String]"){
            try {
                let tpathetData:any = JSON.parse(path);
                if(Object.prototype.toString.call(tpathetData) == "[object Array]"){
                        if(tpathetData && tpathetData.length >0){
                            let fileId:string = tpathetData[0] && tpathetData[0].id;
                            return `${Environment.ExportFile}/` + fileId;
                        }
                    }else if(Object.prototype.toString.call(tpathetData) === '[object Object]'){
                            let fileId:string = tpathetData && tpathetData.id;
                            return `${Environment.ExportFile}/` + fileId;
                    }
            } catch (error) {
                return path;
            }
        }else if(Object.prototype.toString.call(path) == "[object Array]"){
            if(path && path.length >0){
                let fileId:string = path[0] && path[0].id;
                return `${Environment.ExportFile}/` + fileId;
            }
        }else if(Object.prototype.toString.call(path) === '[object Object]'){
                let fileId:string = path && path.id;
                return `${Environment.ExportFile}/` + fileId;
        }else{
                return path;
        }
    }

    /**
     * 获取图片
     * 
     * @memberof 
     */
    public async getImgURLOfBase64(result: any[]) {
        if (result.length>0) {
            for (let item of result) {
                if (item.getPSSysImage?.imagePath) {
                    item.imgUrlBase64 = await ImgurlBase64.getInstance().getImgURLOfBase64(this.getIconImagePath(item));
                }
            }
        }
        return result;
    }
}
</script>

<style lang='less'>
  .codelist {
    white-space: nowrap;
    text-overflow: ellipsis;
    word-break: break-all;
    overflow: hidden;
    .codelist-item{
        max-height: 32px;
        padding: 0px 3px;
        > img{
            max-height: 32px;
            width: auto;
            margin-right: 6px;
            border-radius: 50%;
        }
    }
  }
</style>