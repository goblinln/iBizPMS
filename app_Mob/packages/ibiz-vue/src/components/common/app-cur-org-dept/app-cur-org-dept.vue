<template>
  <div ref="orgDept" class="app-cur-org-dept">
    <van-collapse v-model="active">
        <van-collapse-item  :name="1">
          <div slot="title" class="org-title-content">
            <div class="org-icon"><img :src="baseUrl+'/assets/images/org-dept/team.svg'" alt=""></div>
            <div class="org-title">{{visibleTitle}}</div>
          </div>
          <div class="org-item"  v-for="item in datas" :key="item.id" @click="itemClick(item)">
              <div class="org-item-left">
                <div class="org-icon"><img :src="baseUrl+'/assets/images/org-dept/user.svg'" alt=""></div>
                <div class="user-name">{{item.label}}</div>
              </div>
              <div class="checkbox">
                <div class="org-icon-small">
                  <img v-if="item.check" :src="baseUrl+'/assets/images/org-dept/check-circle-colour.svg'" alt="">
                  <img v-else :src="baseUrl+'/assets/images/org-dept/check-circle.svg'" alt="">
                </div>
              </div>
          </div>
        </van-collapse-item>
    </van-collapse>
  </div>
</template>

<script lang="ts">
import { Environment } from "@/environments/environment";
import { LogUtil } from "ibiz-core";
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
@Component({})
export default class AppCommonMicrocom extends Vue {

  /**
   * 图片基础路径
   * @type {any}
   *  @memberof AppCommonMicrocom
   */
  public baseUrl = Environment.BaseUrl;
  /**
   * 数据接口地址
   *
   * @type {string}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public url!: string;

  /**
   * 填充属性
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public fillMap!: any;

  /**
   * 关联属性
   *
   * @type {string}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public valueitem!: string;

  /**
   * 表单项名称
   *
   * @type {string}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public name!: string;

  /**
   * 局部上下文导航参数
   *
   * @type {any}
   * @memberof AppCheckBox
   */
  @Prop() public localContext!: any;

  /**
   * 局部导航参数
   *
   * @type {any}
   * @memberof AppCheckBox
   */
  @Prop() public localParam!: any;

  /**
   * 上下文
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public context!: any;

  /**
   * 视图参数
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public viewparams!: any;

  /**
   * 表单数据对象
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public data!: any;

  /**
   * 是否启用
   *
   * @type {boolean}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public disabled?: boolean;

  /**
   * 只读模式
   *
   * @type {boolean}
   */
  @Prop({ default: false })
  public readonly?: boolean;

  /**
   * 是否多选
   *
   * @type {boolean}
   * @memberof AppCommonMicrocom
   */
  @Prop({ default: 'false' })
  public multiple?: string;

  /**
   * 是否多选
   *
   * @type {boolean}
   * @memberof AppCommonMicrocom
   */
  public relMultiple:boolean = false;

  /**
   * 地址过滤参数
   *
   * @type {boolean}
   * @memberof AppCommonMicrocom
   */
  @Prop()
  public filter!: string;

  /**
   * 请求方式
   *
   * @type {string}
   * @memberof AppCommonMicrocom
   */
  @Prop({ default: "get" })
  public requestMode!: "get" | "post" | "delete" | "put";

  /**
   * 激活项
   *
   * @type {boolean}
   * @memberof AppCommonMicrocom
   */
  public active:Array<any> = [1];

  /**
   * 请求结束
   *
   * @type {boolean}
   * @memberof AppCommonMicrocom
   */
  public overLoadding:boolean = false;

  /**
   * 表单项标题
   *
   * @type {string}
   * @memberof AppCommonMicrocom
   */
  public title:string = '';

  /**
   * 显示标题
   *
   * @type {string}
   * @memberof AppCommonMicrocom
   */
  get visibleTitle(){
    return `${this.title}${this.overLoadding?'（'+this.datas.length+'）':''}`
  }

  /**
   * 选中项集合
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public selects: any[] = [];

  /**
   * 数据集合
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public datas: any[] = [];

  /**
   * 回显值
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public selectArray: any[] = [];

  /**
   * 主键属性
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  keyFile:string = '';

  /**
   * 文本属性
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  labelFile:string = '';


  /**
   * 项点击
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  itemClick(item:any){
    const isCheck = !item.check;
    if(!this.relMultiple){
      this.datas.forEach((_item) => {
        if(item.id != _item.id){
          _item.check = false; 
        }else{
          _item.check = isCheck;
        }
      });
    }else{
      item.check = isCheck;
    }
    this.selectChange(item);
    this.$forceUpdate();
  }

  /**
   * 值变化
   *
   * @type {*}
   * @memberof AppGroupSelect
   */
  @Watch("data", {
    immediate: true,
    deep: true,
  })
  onValueChange(newVal: any, oldVal: any) {
    console.log(newVal);
    
    this.selects = [];
    if (newVal) {
      let item: any = {};
      item.label = this.data[this.name] ? this.data[this.name].split(",") : [];
      item.id = this.data[this.valueitem]
        ? this.data[this.valueitem].split(",")
        : [];
      this.selectArray = item.id;
      if (this.fillMap) {
        for (let key in this.fillMap) {
          item[this.fillMap[key]] = this.data[key]
            ? this.data[key].split(",")
            : [];
        }
      }
      if (item.label.length > 0) {
        item.label.forEach((val: string, index: number) => {
          let _item: any = {};
          for (let key in item) {
            _item[key] = item[key][index] ? item[key][index] : null;
          }
          this.selects.push(_item);
        });
      }
    }
  }

  /**
   * 生命周期
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public created() {
    this.relMultiple = this.multiple =='true'?true:false;
    const keyRep = this.fillMap?.replace("{id:'","");
    const keyRepArr = keyRep?.split("',");
    if(keyRepArr){
      this.keyFile = keyRepArr[0];
    }
   const textArr = this.fillMap?.replace("label:'","").split(",")
    if(textArr){
      this.labelFile = textArr[1]?.replace("'}","");
    }
    this.load();
  }

  /**
   * 处理特殊filter参数
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public handleFilterValue(value: string) {
    if (value && value.startsWith("%") && value.endsWith("%")) {
      const key = value.slice(1, value.length - 1);
      if (this.data && this.data.hasOwnProperty(key)) {
        return this.data[key];
      } else if (this.context && this.context[key]) {
        return this.context[key];
      } else if (this.viewparams && this.viewparams[key]) {
        return this.viewparams[key];
      }
    }
    return value;
  }

  /**
   * 解析URL
   *
   * @type {*}
   * @param context 上下文参数
   * @param viewparams 视图参数
   * @memberof AppCommonMicrocom
   */
  public parseURL(context: any, viewparams: any) {
    let url = this.url;
    const filterArr: Array<any> = this.filter ? this.filter.split("|") : [];
    const urlParm = url.match(/\${(.+?)\}/g);
    if (urlParm) {
      urlParm.forEach((item: string) => {
        const key = item.substring(2, item.length - 1).toLowerCase();
        const res = new RegExp("\\${" + key + "}");
        let value = null;
        const isFilterKey = key.match(/filter/g) ? true : false;
        if (isFilterKey) {
          const filterIndex = key.slice(6);
          if (filterIndex) {
            if (
              isNaN(parseInt(filterIndex)) ||
              filterArr.length < parseInt(filterIndex) - 1
            ) {
              LogUtil.warn(this.$t("components.microcom.filterwarn"));
            } else {
              value = this.handleFilterValue(
                filterArr[parseInt(filterIndex) - 1]
              );
            }
          } else {
            value = filterArr[0];
          }
        } else {
          if (this.data && this.data.hasOwnProperty(key)) {
            value = this.data[key];
          } else if (context && context[key]) {
            value = context[key];
          } else if (viewparams && viewparams[key]) {
            value = viewparams[key];
          }
        }
        url = url.replace(res, value);
      });
    }
    return url;
  }

  /**
   * 公共参数处理
   *
   * @param {*} arg
   * @returns
   * @memberof AppCheckBox
   */
  public handlePublicParams(arg: any) {
    // 合并表单参数
    arg.param = this.viewparams
      ? JSON.parse(JSON.stringify(this.viewparams))
      : {};
    arg.context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
    // 附加参数处理
    if (this.localContext && Object.keys(this.localContext).length > 0) {
      let _context = this.$util.computedNavData(
        this.data,
        arg.context,
        arg.param,
        this.localContext
      );
      Object.assign(arg.context, _context);
    }
    if (this.localParam && Object.keys(this.localParam).length > 0) {
      let _param = this.$util.computedNavData(
        this.data,
        arg.param,
        arg.param,
        this.localParam
      );
      Object.assign(arg.param, _param);
    }
  }

  /**
   * 加载数据
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public load() {
    let data: any = {};
    this.handlePublicParams(data);
    // 参数处理
    let context = data.context;
    let viewparam = data.param;
    const url = this.parseURL(context, viewparam);
    if (url) {
      this.datas = [];
      this.$http[this.requestMode](url)
        .then((response: any) => {
          if (response && response.status == 200) {
            this.overLoadding = true;
            if (response.data.length > 0) {
              let item: any;
              response.data.forEach((_item: any) => {
                item = _item;
                if (_item[this.labelFile]) {
                  item.label = _item[this.labelFile];
                }
                if (_item[this.keyFile]) {
                  item.id = _item[this.keyFile];
                }
                this.datas.push(item);
              });
            }
          }
          console.log(this.datas);
        })
        .catch((response: any) => {
          console.log(this.datas);
        });
    }
  }

  /**
   * 选中数据改变
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public selectChange(item: any) {
    this.selects = [];
    if (this.relMultiple) {
        this.datas.forEach((_item: any) => {
          if (_item.check) {
            this.selects.push(_item);
          }
        });
    } else {
      this.selects.push(item);
    }
    this.setValue();
  }

  /**
   * 设置值
   *
   * @type {*}
   * @memberof AppCommonMicrocom
   */
  public setValue() {
    let item: any = {};
    item[this.name] = null;
    if (this.valueitem) {
      item[this.valueitem] = null;
    }
    if (this.relMultiple == true) {
      this.selects.forEach((select: any) => {
        item[this.name] = item[this.name]
          ? `${item[this.name]},${select.label}`
          : select.label;
        if (this.valueitem) {
          item[this.valueitem] = item[this.valueitem]
            ? `${item[this.valueitem]},${select.id}`
            : select.id;
        }
      });
    } else {
      item[this.name] = this.selects.length > 0 ? this.selects[0].label : null;
      if (this.valueitem) {
        item[this.valueitem] =
          this.selects.length > 0 ? this.selects[0].id : null;
      }
    }
    for (let key in item) {
      // 抛出当前表单项与值项
      if (Object.is(key, this.name) || Object.is(key, this.valueitem)) {
        this.$emit("formitemvaluechange", { name: key, value: item[key] });
      }
    }
  }

  mounted(){
    //删除元素标题 自定义绘制
   const orgDept:any =  this.$refs.orgDept;
   const parent = orgDept?.parentNode;
   const label:any = parent?.previousElementSibling;
   if(label){
     this.title = label.textContent;
     label.style.display="none";
   }
   // 默认填满
   if(parent){
     parent.style.width= '100%'; 
   }
  }
}
</script>

<style lang='less'>
@import "./app-cur-org-dept.less";
</style>