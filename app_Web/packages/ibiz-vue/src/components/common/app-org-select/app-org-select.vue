<template>
  <div class="app-org-select">
    <ibiz-select-tree :NodesData="NodesData" v-model="selectTreeValue" :disabled="disabled || readonly" :multiple="multiple" @select="treeSelectChange"></ibiz-select-tree>
  </div>
</template>
<script lang = 'ts'>
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import { LogUtil  } from 'ibiz-core';
import {CodeListService} from "ibiz-service";
import axios from 'axios';
import qs from "qs";

@Component({})
export default class AppOrgSelect extends Vue {

  /**
   * 表单数据
   * 
   * @memberof AppOrgSelect
   */
  @Prop() public data!:any;

  /**
   * 上下文
   * 
   * @memberof AppOrgSelect
   */
  @Prop() public context!:any;

  /**
   * 视图参数
   * 
   * @type {*}
   * @memberof AppOrgSelect
   */
  @Prop()
  public viewparams!: any;

  /**
   * 局部上下文导航参数
   * 
   * @type {any}
   * @memberof AppOrgSelect
   */
  @Prop() public localContext!:any;

  /**
   * 局部导航参数
   * 
   * @type {any}
   * @memberof AppOrgSelect
   */
  @Prop() public localParam!:any;

  /**
   * 填充对象
   * 
   * @memberof AppOrgSelect
   */
  @Prop() public fillMap:any;

  /**
   * 过滤项
   * 
   * @memberof AppOrgSelect
   */
  @Prop() public filter?:string;

  /**
   * 代码表标识
   * 
   * @memberof AppOrgSelect
   */
  @Prop() public tag?:string;

  /**
   * 代码表类型
   * 
   * @memberof AppOrgSelect
   */
  @Prop() public codelistType?:string;

  /**
   * 是否多选
   * 
   * @memberof AppOrgSelect
   */
  @Prop({default:false}) public multiple?:boolean;

  /**
   * 是否禁用
   *
   * @type {*}
   * @memberof AppDepartmentSelect
   */
  @Prop({default:false}) public disabled?: boolean;

  /**
   * 只读模式
   * 
   * @type {boolean}
   */
  @Prop({default: false}) public readonly?: boolean;

  /**
   * 查询单位路径
   * 
   * @memberof AppOrgSelect
   */
  @Prop() public url!:string;

  /**
   * 请求方式
   *
   * @type {stinr}
   * @memberof AppOrgSelect
   */ 
  @Prop({ default: 'get'})
  public requestMode!: 'get' | 'post' | 'delete' | 'put';

  /**
   * 监听表单数据变化
   * 
   * @memberof AppOrgSelect
   */
  @Watch('data',{immediate:true,deep:true})
  onDataChange(newVal: any, oldVal: any) {
    if(newVal){
      this.computedSelectedData();
      this.computeUrlParams()
    }
  }

  /**
   * 选择值
   * 
   * @memberof AppOrgSelect
   */
  public selectTreeValue:any = "";

  /**
   * 树节点数据
   * 
   * @memberof AppOrgSelect
   */
  public NodesData:any = [];

  /**
   * 备份请求
   * 
   * @memberof AppOrgSelect
   */
  public copyActualUrl:any;

  /**
   * vue生命周期
   * 
   * @memberof AppOrgSelect
   */
  public created(){
    this.computeUrlParams();
  }

  /**
   * 加载树数据
   * 
   * @memberof AppOrgSelect
   */
  public initBasicData(){
    // 计算出过滤值
    if(this.filter){
      if(this.data && this.data[this.filter]){
        return this.data[this.filter];
      }else if(this.context && this.context[this.filter]){
        return this.context[this.filter];
      }else{
        return null;
      }
    }
  }

  /**
   * 计算选中值
   * 
   * @memberof AppOrgSelect
   */
  public computedSelectedData(){
    // 单选
    if(!this.multiple){
      if(this.fillMap && Object.keys(this.fillMap).length >0){
        let templateValue:any = {};
        Object.keys(this.fillMap).forEach((item:any) =>{
          if(this.data && this.data[this.fillMap[item]]){
            Object.assign(templateValue,{[item]:this.data[this.fillMap[item]]});
          }
        })
        if(!templateValue.label && templateValue.id && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
          this.fillLabel(templateValue,templateValue.id,(templateValue:any) =>{
            this.selectTreeValue = JSON.stringify([templateValue]);
          });
        }else{
          this.selectTreeValue = JSON.stringify([templateValue]);
        }
      }
    }else{
    // 多选
      if(this.fillMap && Object.keys(this.fillMap).length >0){
        let tempArray:Array<any> = [];
        Object.keys(this.fillMap).forEach((item:any) =>{
          if(this.data && this.data[this.fillMap[item]]){
            let tempDataArray:Array<any> = (this.data[this.fillMap[item]]).split(",");
            tempDataArray.forEach((tempData:any,index:number) =>{
              if(tempArray.length < tempDataArray.length){
                let singleData:any ={[item]:tempData};
                tempArray.push(singleData);
              }else{
                Object.assign(tempArray[index],{[item]:tempData});
              }
            })
          }
        })
        let tempflag:boolean = false;
        if(tempArray.length >0 && tempArray.length >0){
          tempArray.forEach((item:any) =>{
            if(!item.label) tempflag = true;
          })
        }
        if(tempflag && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
          let tempStatus:number = 0;
          tempArray.forEach((item:any) =>{
            if(!item.label){
              tempStatus += 1;
              this.fillLabel(item,item.id,(result:any) =>{
                item = result;
                tempStatus -= 1;
                if(tempStatus === 0){
                  this.selectTreeValue = JSON.stringify(tempArray);
                }
              })
            }
          })
        }else{
          this.selectTreeValue = JSON.stringify(tempArray);
        }
      }
    }
  }

  /**
   * 计算URL参数
   * 
   * @memberof AppOrgSelect
   */
  public computeUrlParams(){
    let requestUrl = this.url;
    if(this.filter){
        let tempFilterValue:any = this.initBasicData();
        if(tempFilterValue){
          requestUrl = this.url.replace('${orgid}',tempFilterValue);
        }
    }
    // 参数处理
    let data: any = {};
    this.handlePublicParams(data);
    let context = data.context;
    let viewparam = data.param;
    this.loadTreeData(requestUrl, viewparam);
  }

  /**
   * 加载树数据
   * 
   * @memberof AppOrgSelect
   */
  public loadTreeData(requestUrl:string, params = {}){
    // 最终请求路径，作为缓存的key值
    let tempActualUrl: string = requestUrl + "?"+ qs.stringify(params);
    if(!requestUrl || (this.copyActualUrl == tempActualUrl)){
      return;
    }
    this.copyActualUrl = tempActualUrl;

    // 全局缓存
    if(this.filter){
      const result:any = this.$store.getters.getOrgData(tempActualUrl);
      if(result){
        this.NodesData = result;
        return;
      }
    }
    this.$http[this.requestMode](requestUrl, params).then((res:any) =>{
      if(!res.status && res.status !== 200){
        this.$throw((this.$t('components.apporgselect.loadfail') as string),'loadTreeData');
        return;
      }
      this.NodesData = res.data;
      
      // 全局缓存
      if(this.filter){
        this.$store.commit('addOrgData', { srfkey: tempActualUrl, orgData: res.data });
      }
    })
  }

  /**
   * 树选择触发事件
   * 
   * @memberof AppOrgSelect
   */
  public treeSelectChange($event:any){
    // 多选
    if(this.multiple){
      if(!Object.is($event,'[]')){
        const tempValue:any = JSON.parse($event);
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            let tempResult:any ="";
            tempValue.forEach((value:any,index:number) =>{
              tempResult += index>0?`,${value[item]}`:`${value[item]}`;
            })
            setTimeout(() => {
              this.emitValue(this.fillMap[item],tempResult);
            }, 0);
          })
        }
      }else{
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            this.emitValue(this.fillMap[item],null);
          })
        }
      }
    }else{
      // 单选
      if(!Object.is($event,'[]')){
        const tempValue:any = JSON.parse($event)[0];
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            setTimeout(() => {
              this.emitValue(this.fillMap[item],tempValue[item]);
            }, 0);
          })
        }
      }else{
        if(this.fillMap && Object.keys(this.fillMap).length >0){
          Object.keys(this.fillMap).forEach((item:any) =>{
            this.emitValue(this.fillMap[item],null);
          })
        }
      }
    }
  }

  /**
   * 抛值
   * 
   * @memberof AppOrgSelect
   */
  public emitValue(name:string,value:any){
    this.$emit('select-change',{name:name,value:value});
  }

  /**
   * 填充label
   * 
   * @memberof AppOrgSelect
   */
  public fillLabel(tempObject:any,valueItem:any,callback:any){
    if(!tempObject.label && tempObject.id && this.tag && this.codelistType && Object.is(this.codelistType,"DYNAMIC")){
      let codeListService:CodeListService = new CodeListService();
      codeListService.getItems(this.tag).then((items:any) =>{
        if(items && items.length >0){
          let result:any = items.find((item:any) =>{
            return item.id === valueItem;
          })
          Object.assign(tempObject,{label:result.label});
        }
        callback(tempObject);
      }).catch((error:any) =>{
        LogUtil.log(error);
      })
    }
  }
  
  /**
   * 公共参数处理
   *
   * @param {*} arg
   * @returns
   * @memberof AppOrgSelect
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

}
</script>

<style lang="less">
@import "./app-org-select.less";
</style>