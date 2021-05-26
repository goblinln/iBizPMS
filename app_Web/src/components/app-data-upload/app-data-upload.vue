<template>
    <div class="app-data-upload-view" v-loading.fullscreen="isUploading" element-loading-background="rgba(57, 57, 57, 0.2)">
        <input ref="inputUpLoad" type="file" style="display: none" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" @change="importFile"/>
        <div class="main-content">
            <div v-if="importDataArray.length == 0" class="upload-container" @click="handleUpLoad">
                <img class="icon-import" src="@/assets/img/icon-import.svg" />
                <span class="select-file-text">{{$t('components.appDataUploadView.selectfile')}}</span>
            </div>
            <div v-else-if="importDataArray.length >0" class="data-info-container" >
                <el-progress class="progress" :stroke-width="4" :show-text="false" :percentage="uploadProgress"></el-progress>
                <div v-if="hasImported === false" class="message-container">
                    <div class="success-list">
                        <ul v-if="isUploading === false">
                            <li v-for="(item,index) in importDataArray" :key="index">
                                {{(item[importUniqueItem] || `第${index+1}行`) +'：'+ $t('components.appDataUploadView.read')}}
                            </li>
                        </ul>
                    </div>
                    <div class="error-list">
                    </div>
                </div>
                <div v-else class="message-container">
                    <div class="success-list">
                        <ul>
                            <li v-for="(item,index) in successResult" :key="index">
                                {{item.rowName +'：' + $t('components.appDataUploadView.completed')}}
                            </li>
                        </ul>
                    </div>
                    <div class="error-list">
                        <ul>
                            <li v-for="(item,index) in errorResult" :key="index">
                                {{item.rowName +'：'+ item.message}}
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <el-row class="second-content">
            <el-col>
                <div class="import-template-message">{{$t('components.appDataUploadView.dataTemplateMessage')}}</div>
                <div class="import-template">
                    <img class="icon-link" src="@/assets/img/icon-link.svg"></img>
                    <span style="cursor: pointer;" @click="downloadTemp">{{this.viewparams.appDeLogicName + $t('components.appDataUploadView.datatemplate')}}</span>
                </div>
            </el-col>
        </el-row>
        <el-row class="button-container">
            <el-button type="primary" @click="handleOK">{{$t('components.appDataUploadView.cancel')}}</el-button>
            <el-button v-if="hasImported === false" :disabled="this.importDataArray.length == 0" type="primary" class="primary-button" @click="uploadServer">{{$t('components.appDataUploadView.uploadserver')}}</el-button>
            <el-button v-if="hasImported === true" type="primary" class="primary-button"  @click="handleOK">{{$t('components.appDataUploadView.confirm')}}</el-button>
        </el-row>
    </div>
</template>

<script lang="ts">
import XLSX from 'xlsx';
import { GlobalService, CodeListService } from 'ibiz-service';
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import { LogUtil } from 'ibiz-core';

@Component({
})
export default class AppDataUploadView extends Vue {

    /**
     * 传入视图参数
     *
     * @type {string}
     * @memberof AppDataUploadView
     */
    @Prop() protected dynamicProps!: string;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppDataUploadView
     */  
    public codeListService:CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @protected
     * @type {EntityService}
     * @memberof AppDataUploadView
     */
    protected entityService: GlobalService = new GlobalService();

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppDataUploadView
     */
    protected viewparams:any = {};

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppDataUploadView
     */
    protected viewdata:any = {};

    /**
     * 导入数据模型
     *
     * @type {Array<*>}
     * @memberof AppDataUploadView
     */
    protected importDataModel:Array<any> = [];

    /**
     * 导入数据集合
     *
     * @type {Array<*>}
     * @memberof AppDataUploadView
     */
    protected importDataArray:Array<any> = [];

    /**
     * 导入标识
     *
     * @type {string}
     * @memberof AppDataUploadView
     */
    protected importId:string = "";

    /**
     * 是否已经进行过上传导入数据
     *
     * @type {boolean}
     * @memberof AppDataUploadView
     */
    protected hasImported:boolean = false;

    /**
     * 导入数据识别项属性
     *
     * @type {string}
     * @memberof AppDataUploadView
     */
    protected importUniqueItem:string ="";

    /**
     * 导入状态
     *
     * @type {boolean}
     * @memberof AppDataUploadView
     */
    protected isUploading:boolean = false;

    /**
     * 导入成功数据
     *
     * @type {Array<*>}
     * @memberof AppDataUploadView
     */
    protected importSuccessData:Array<any> = [];

    /**
     * 导入结果集合
     *
     * @type {Array<*>}
     * @memberof AppDataUploadView
     */
    protected importResult:Array<any> = [];

    /**
     * 导入结果成功集合
     *
     * @type {Array<*>}
     * @memberof AppDataUploadView
     */
    get successResult(){
        return this.importResult.filter((item: any)=> item.isSuccess);
    }

    /**
     * 导入结果失败集合
     *
     * @type {Array<*>}
     * @memberof AppDataUploadView
     */
    get errorResult(){
        return this.importResult.filter((item: any)=> !item.isSuccess);
    }

    /**
     * 读取完成的数据
     *
     * @type {*}
     * @memberof AppDataUploadView
     */
    public workBookData:any;

    /**
     * 所有的代码表
     *
     * @type {*}
     * @memberof AppDataUploadView
     */
    public allCodeList:any;

    /**
     * 属性Map(用作属性转化)
     *
     * @type {*}
     * @memberof AppDataUploadView
     */
    public allFieldMap:Map<string,any> = new Map();
    
    /**
     * 上传服务器数据切片数
     *
     * @type {number}
     * @memberof AppDataUploadView
     */
    public sliceUploadCnt: number = Environment.sliceUploadCnt;

    /**
     * 上传服务器进度条百分比
     *
     * @type {number}
     * @memberof AppDataUploadView
     */
    public uploadProgress: number = 0;

    /**
     * 视图参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataUploadView
     */
    @Watch('dynamicProps',{immediate: true, deep: true})
    onParamData(newVal: any, oldVal: any) {
        if(newVal){
            this.viewparams = eval('('+newVal.viewparam+')');
            this.viewdata = eval('('+newVal.viewdata+')');
            this.initBasic();
        } 
    }

    /**
     * 初始化基础数据
     *
     * @memberof AppDataUploadView
     */
    public async initBasic(){
        if(this.viewparams.importId){
            this.importId = this.viewparams.importId;
        }
        if(this.viewparams.importData){
            this.importDataModel = Object.values(this.viewparams.importData);
            this.BubbleSort(this.importDataModel,this.importDataModel.length);
        }
        this.importDataModel.forEach((item:any) =>{
            if(item.isuniqueitem){
                this.importUniqueItem = item.headername;
            }
            this.allFieldMap.set(item.headername,item);
        });
        //获取代码表值
        this.allCodeList = await this.getChartAllCodeList();
    }

    /**
     * 冒泡排序
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataUploadView
     */
    public BubbleSort(array:Array<any>,length:number){
        for (let i = 0; i < length; i++){
		for (let j = 0; j < length -  i - 1; j++){
                if (array[j].order > array[j + 1].order){
                    let temp:any;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 下载导入数据模板
     *
     * @memberof AppDataUploadView
     */
    public downloadTemp(){
        this.importExcel(this.viewparams.appDeLogicName+this.$t('components.appDataUploadView.datatemplate'),[]);
    }

    /**
     * 选择文件
     *
     * @memberof AppDataUploadView
     */
    public handleUpLoad(){
        this.importSuccessData = [];
        (this.$refs.inputUpLoad as any).click();
    }

    /**
     * 上传服务器
     *
     * @memberof AppDataUploadView
     */
    public uploadServer(){
        if(this.importDataArray.length == 0){
            return;
        }
        let tempDataArray:Array<any> = [];
        this.transformData(this.importDataArray,tempDataArray);
        this.importResult = []
        this.hasImported = true;
        this.isUploading = true;
        this.uploadProgress = 0;
        this.sliceUploadService(tempDataArray, 0);
    }

    /**
     * 添加失败记录的信息
     * @param cnt 切片起始索引位置
     * @param error 后台返回的错误信息
     *
     * @memberof AppDataUploadView
     */
    public addErrorRsult(cnt: number, error: any){
        let endIndex: number = (cnt + this.sliceUploadCnt > this.importDataArray.length) ? this.importDataArray.length : cnt + this.sliceUploadCnt;
        let errMessage: string[] = error.msg.split('<br>');
        for (let index = cnt; index < endIndex ; index++) {
            const row = this.importDataArray[index];
            let rowName = row[this.importUniqueItem] || `第${index+1}行`;
            let errIndex = error.errorLines.indexOf(index+1);
            if(errIndex == -1){
                this.importResult.push({
                    index: index,
                    rowName: rowName,
                    isSuccess: true
                })
            }else{
                let message = errMessage[errIndex].indexOf('：') != -1 ? errMessage[errIndex].slice(errMessage[errIndex].indexOf('：')+1) : errMessage[errIndex];
                this.importResult.push({
                    index: index,
                    rowName: rowName,
                    isSuccess: false,
                    message: message
                })
            }
        }
    }

    /**
     * 添加成功记录的信息
     * @param cnt 切片起始索引位置
     *
     * @memberof AppDataUploadView
     */
    public addSuccessRsult(cnt: number){
        let endIndex = (cnt + this.sliceUploadCnt > this.importDataArray.length) ? this.importDataArray.length : cnt + this.sliceUploadCnt;
        for (let index = cnt; index < endIndex ; index++) {
            const row = this.importDataArray[index];
            let rowName = row[this.importUniqueItem] || `第${index+1}行`;
            this.importResult.push({
                index: index,
                rowName: rowName,
                isSuccess: true
            })
        }
    }

    /**
     * 数据切片上传
     * @param dataArray 导入的所有数据集合
     * @param cnt 切片起始索引位置
     *
     * @memberof AppDataUploadView
     */
    public sliceUploadService(dataArray: Array<any>, cnt: number) {
        if(cnt > dataArray.length) {
            this.isUploading = false;
            this.uploadProgress = 100;
            return;
        }
        let sliceArray: Array<any> = [];
        if(dataArray) {
            sliceArray = dataArray.slice(cnt, cnt+this.sliceUploadCnt);
        }
        // 处理错误的通用回调
        const handleError = (error: any)=>{
            this.isUploading = false;
            this.uploadProgress = 100;
            console.error(error);
            if(error?.data?.message){
                this.$throw(error.data.message)
            }
        }
        try{
            this.entityService.getService(this.viewparams.serviceName).then((service:any) =>{
                service.ImportData(this.viewdata,{name:this.importId,importData:sliceArray}).then((res:any) =>{
                    const result:any = res.data;
                    if(result && result.rst !== 0){
                        this.addErrorRsult(cnt, result)
                        handleError(result)
                        return;
                    }
                    this.importSuccessData = result.data;
                    this.addSuccessRsult(cnt);
                    this.uploadProgress = Number((cnt / dataArray.length * 100).toFixed(2));
                    this.sliceUploadService(dataArray, cnt + this.sliceUploadCnt);
                }).catch((error:any) =>{
                    handleError(error)
                })
            }).catch((error:any) =>{
               handleError(error)
            })
        }catch(error){
            handleError(error)
        };
    }
    /**
     * 导出excel
     *
     * @memberof AppDataUploadView
     */
    public async importExcel (filename:string,_data:any){
        const tHeader: Array<any> = [];
        this.importDataModel.forEach((item: any) => {
            tHeader.push(item.headername);
        });
        //const data = await this.formatExcelData(filterVal, _data);
        const data = _data?_data:[];
        this.$export.exportExcel().then((excel:any)=>{
            excel.export_json_to_excel({
                header: tHeader, //表头 必填
                data:data, //具体数据 必填
                filename: filename, //非必填
                autoWidth: true, //非必填
                bookType: "xlsx" //非必填
            });
        }); 
    };

    /**
     * 确认
     *
     * @memberof AppDataUploadView
     */
    public handleOK(){
        this.$emit('close',this.importSuccessData);
    }

    /**
     * 导入Excel
     *
     * @memberof AppDataUploadView
     */
    public importFile($event:any) {
        let obj = $event.target || $event.srcElement;
       if (!obj.files) {
           return;
       }
       let f = obj.files[0];
       let reader = new FileReader();
       reader.onload = (e:any) => {
           let data = e.target.result;
           this.workBookData = XLSX.read(data, {type: 'binary'});
           let xlsxData = XLSX.utils.sheet_to_json(this.workBookData.Sheets[this.workBookData.SheetNames[0]],{raw: false});
           let list1 = this.getFirstRow(this.workBookData);
           xlsxData = this.AddXlsxData(xlsxData, list1);
           this.importDataArray = JSON.parse(JSON.stringify(xlsxData));
           (this.$refs.inputUpLoad as any).value = '';
       };
       reader.readAsBinaryString(f);
   }

    /**
     * 获取excel第一行的内容
     *
     * @memberof AppDataUploadView
     */
   public getFirstRow(wb:any) {
       //// 读取的excel单元格内容
       let wbData = wb.Sheets[wb.SheetNames[0]];
       // 匹配excel第一行的内容
       let re = /^[A-Z]1$/; 
       let temparr = [];
       // excel第一行内容赋值给数组
       for (let key in wbData) {
           if (wbData.hasOwnProperty(key)) {
               if (re.test(key)) {
                   temparr.push(wbData[key].h);
               }
           }
       }
       return temparr;
   }

    /**
     * 增加对应字段空白内容
     *
     * @memberof AppDataUploadView
     */
   public AddXlsxData(xlsxData:any, list1:any) {
       // 空白字段替换值
       let addData = null;
       for (let i = 0; i < xlsxData.length; i++) {
           // 要被JSON的数组
           for (let j = 0; j < list1.length; j++) {
               // excel第一行内容
               if (!xlsxData[i][list1[j]]) {
                   xlsxData[i][list1[j]] = addData;
               }
           }
       }
       return xlsxData;
   }

    /**
     * 获取图表所需代码表
     * 
     * @memberof AppDataUploadView
     */
    public async getChartAllCodeList(){
        let codeListMap:Map<string,any> = new Map();
        if(Object.values(this.importDataModel).length >0){
            await Object.values(this.importDataModel).forEach(async (singleDataModel:any) =>{
                if(singleDataModel.codelist){
                    let tempCodeListMap:Map<any,any> = new Map();
                    let res:any = await this.getCodeList(singleDataModel.codelist);
                    if(res && res.length >0){
                        res.forEach((codeListItem:any) =>{
                            tempCodeListMap.set(codeListItem.value,codeListItem.text);
                        })
                    }
                    codeListMap.set(singleDataModel.codelist.tag,tempCodeListMap);
                }
            })
        }
        return codeListMap;
    }

    /**
     * 获取代码表
     * 
     * @returns {Promise<any>} 
     * @memberof AppDataUploadView
     */
    public getCodeList(codeListObject:any):Promise<any>{
        return new Promise((resolve:any,reject:any) =>{
            if(codeListObject.tag && Object.is(codeListObject.type,"STATIC")){
                this.codeListService.getStaticItems(codeListObject.tag).then((res: any) => {
                    resolve(res);
                }).catch((error: any) => {
                    LogUtil.log(`----${codeListObject.codeName}----代码表不存在`);
                });
            }else if(codeListObject.tag && Object.is(codeListObject.type,"DYNAMIC")){
                this.codeListService.getItems(codeListObject.tag).then((res: any) => {
                    resolve(res);
                }).catch((error: any) => {
                    LogUtil.log(`----${codeListObject.codeName}----代码表不存在`);
                });
            }
        })
    }

    /**
     * 转化数据
     * 
     * @memberof AppDataUploadView
     */
    public transformData(data:Array<any>,result:Array<any>){
        data.forEach((item:any) =>{
            let curObject:any = {};
            Object.keys(item).forEach((ele:any) => {
                if(this.allFieldMap.get(ele)?.codelist){
                    let codelistTag:string = this.allFieldMap.get(ele).codelist.tag;
                    let codelistIsNumber:boolean = this.allFieldMap.get(ele).codelist.isnumber;
                    let curCodeList:any = this.transCodeList(codelistTag,codelistIsNumber,true);
                   Object.defineProperty(curObject, this.allFieldMap.get(ele).name, {
                        value: curCodeList.get(item[ele]),
                        writable : true,
                        enumerable : true,
                        configurable : true
                    });
                }else{
                    Object.defineProperty(curObject, this.allFieldMap.get(ele)?.name, {
                        value: item[ele],
                        writable : true,
                        enumerable : true,
                        configurable : true
                    }); 
                }
            });
            result.push(curObject);
        })
    }

    /**
     * 翻译代码表
     * 
     * @memberof AppDataUploadView
     */
    public transCodeList(codeListTag:string,codelistIsNumber:boolean,isTransform:boolean){
        let curCodeList:any = this.allCodeList.get(codeListTag);
        if(isTransform){
            let tempCodelist:Map<string,string> = new Map();
            curCodeList.forEach((item:string,key:string) =>{
                let value:any = codelistIsNumber?Number(key):key;
                tempCodelist.set(item,value);
            })
            curCodeList = tempCodelist;
        }
        return curCodeList;
    }

}
</script>

<style lang='less'>
@import './app-data-upload.less';
</style>