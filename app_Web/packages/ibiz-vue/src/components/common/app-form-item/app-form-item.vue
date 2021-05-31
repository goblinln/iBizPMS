<template>
    <div :class="classes">
        <template v-if="this.uiStyle === 'STYLE2'">
            <app-form-item2
                :caption="caption"
                :labelStyle="labelStyle"
                :error="error"
                :labelPos="labelPos"
                :labelWidth="labelWidth"
                :isShowCaption="isShowCaption"
                :isEmptyCaption="isEmptyCaption"
                :name="name"
                :uiStyle="uiStyle"
                :itemRules="itemRules">
                <slot></slot>
            </app-form-item2>
        </template>
        <template v-if="this.uiStyle !== 'STYLE2'">
            <div
                v-if="Object.is(this.labelPos,'BOTTOM') || Object.is(this.labelPos,'NONE') || !this.labelPos"
                class="editor"
                :style="slotstyle"
            >
                <form-item :ref="name" :prop="name" :error="error"  :rules="rules">
                    <slot></slot>
                </form-item>
            </div>
            <span
                v-if="!Object.is(this.labelPos,'NONE') && this.isShowCaption && this.labelWidth > 0"
                :style="labelstyle"
                :class="labelclasses"
            >
                <span v-if="required" style="color:red;">* </span>
                    <span v-if="!isEmptyCaption">
                        <el-tooltip v-if="isShowTip" placement="top" effect="light">
                            <span v-html="caption"></span>
                            <template >
                                <span slot="content" v-html="caption" ></span>
                            </template>
                        </el-tooltip>
                        <template v-if="!isShowTip">
                            <span v-html="caption" ></span>
                        </template>
                    </span>
            </span>
            <div
                v-if="viewStyle !== 'STYLE2' && ( Object.is(this.labelPos,'TOP') || Object.is(this.labelPos,'LEFT') || Object.is(this.labelPos,'RIGHT') )"
                class="editor"
                :style="slotstyle"
            >
                <form-item :prop="name" :error="error"  :rules="rules">
                    <slot></slot>
                </form-item>
            </div>
            <!-- studio版结构 -->
            <div
                v-if="viewStyle == 'STYLE2' && ( Object.is(this.labelPos,'TOP') || Object.is(this.labelPos,'LEFT') || Object.is(this.labelPos,'RIGHT') )"
                class="editor-wrapper editor"
                :style="slotstyle"
            >
                <form-item :ref="name" :prop="name" :error="error"  :rules="rules">
                    <div class="editor-content">
                        <slot></slot>
                    </div>
                    <div v-if="itemRef && itemRef.validateState === 'error'" class="editor-error-tip">
                        <Tooltip placement="left">
                            <Icon type="ios-alert-outline" />
                            <template slot="content">
                                <span class="editor-error-tip-content">
                                    {{ itemRef.validateMessage }}
                                </span>
                            </template>
                        </Tooltip>
                    </div>
                </form-item>
            </div>
        </template>
    </div>
</template>
<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";

@Component({})
export default class AppFormItem extends Vue {

  /**
   * 表单的模型对象
   *
   * @type {*}
   * @memberof AppFormItem
   */
  @Prop() public controlInstance?: any;

  /**
   * 名称
   *
   * @type {string}
   * @memberof AppFormItem
   */
  @Prop() public caption!: string;

  /**
   * 错误信息
   *
   * @type {string}
   * @memberof AppFormItem
   */
  @Prop() public error?: string;

  /**
   * label样式
   *
   * @type {string}
   * @memberof AppFormItem
   */
  @Prop() public labelStyle?: string;

  /**
   * 标签位置
   *
   * @type {(string | 'BOTTOM' | 'LEFT' | 'NONE' | 'RIGHT' | 'TOP')}
   * @memberof AppFormItem
   */
  @Prop() public labelPos?:
    | string
    | "BOTTOM"
    | "LEFT"
    | "NONE"
    | "RIGHT"
    | "TOP";

  /**
   * 标签宽度
   *
   * @type {number}
   * @memberof AppFormItem
   */
  @Prop({}) public labelWidth!: number;

  /**
   * 是否显示标题
   *
   * @type {boolean}
   * @memberof AppFormItem
   */
  @Prop() public isShowCaption?: boolean;

  /**
   * 标签是否空白
   *
   * @type {boolean}
   * @memberof AppFormItem
   */
  @Prop() public isEmptyCaption?: boolean;

  /**
   * 表单项名称
   *
   * @type {string}
   * @memberof AppFormItem
   */
  @Prop() public name!: string;

  /**
   * 内置样式
   *
   * @type {string}
   * @memberof AppFormItem
   */
  @Prop() public uiStyle?: string;

  /**
   * 表单项值规则
   *
   * @type {string}
   * @memberof AppFormItem
   */
  @Prop() public itemRules!: any;

  /**
   * 表单项实例
   *
   * @type {*}
   * @memberof AppFormItem
   */
  public itemRef: any = null;

  /**
  * 是否显示表单项Label提示
  *
  * @memberof AppFormItem
  */
  public isShowTip:boolean = false;

  /**
   * 值规则数组
   *
   * @type {any[]}
   * @memberof AppFormItem
   */
  public rules: any[] = [];

  /**
   * 是否必填
   *
   * @type {boolean}
   * @memberof AppFormItem
   */
  @Prop({default: false}) public required!: boolean;

  /**
   * 表单项值规则监控
   *
   * @param {*} newVal
   * @param {*} oldVal
   * @memberof AppFormItem
   */
  @Watch("itemRules", { deep: true })
  onItemRulesChange(newVal: any, oldVal: any) {
    if (newVal) {
      try {
        this.rules = [];
        const _rules: any[] = newVal;
        this.rules = [..._rules];
      } catch (error) {}
    }
  }

  /**
   * 计算样式
   *
   * @readonly
   * @type {string []}
   * @memberof AppFormItem
   */
  get classes(): string[] {
    let posClass = "";
    switch (this.labelPos) {
      case "TOP":
        posClass = "label-top";
        break;
      case "LEFT":
        posClass = "label-left";
        break;
      case "BOTTOM":
        posClass = "label-bottom";
        break;
      case "RIGHT":
        posClass = "label-right";
        break;
      case "NONE":
        posClass = "label-none";
        break;
    }
    return [ "app-form-item", posClass ];
  }

  /**
   * label样式
   *
   * @readonly
   * @type {string}
   * @memberof AppFormItem
   */
  get labelclasses(): string {
    return this.labelStyle
      ? this.labelStyle + " app-form-item-label"
      : "app-form-item-label";
  }

  /**
   * label行内样式
   *
   * @readonly
   * @type {string}
   * @memberof AppFormItem
   */
  get labelstyle(): any {
    if (Object.is(this.labelPos, 'LEFT') || Object.is(this.labelPos, 'RIGHT')) {
      return { width: this.labelWidth + "px" };
    }
  }

  /**
   * slot行内样式
   *
   * @readonly
   * @type {string}
   * @memberof AppFormItem
   */
  get slotstyle(): any {
    if (Object.is(this.labelPos, "LEFT")) {
      return { marginLeft: this.labelWidth + "px" };
    } else if (Object.is(this.labelPos, "RIGHT")) {
      return { marginRight: this.labelWidth + "px" };
    }
  }

  /**
   * 视图样式
   *
   * @readonly
   * @type {string}
   * @memberof AppFormItem
   */
  get viewStyle(): any {
      return this.controlInstance?.getParentPSModelObject?.()?.viewStyle || 'DEFAULT';
  }

  /**
   * vue 生命周期
   *
   * @memberof AppFormItem
   */
  public mounted() {
    this.itemRef = this.$refs[this.name];
    if (this.itemRules) {
      try {
        const _rules: any[] = this.itemRules;
        this.rules = [..._rules];
      } catch (error) {}
    }
    this.getShowTip();
  }


  /**
  * 计算是否显示表单项Label提示
  *
  * @memberof AppFormItem
  */
  public getShowTip(){
      if(this.caption && ((this.caption.length)*14) > this.labelWidth ){
          this.isShowTip = true;
      }
  } 

}
</script>
<style lang='less'>
@import "./app-form-item.less";
</style>