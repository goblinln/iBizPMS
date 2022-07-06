<template>
    <van-popup v-model='isShow' :ref="refName" class="app-message-box" :class="customClass" :closable="showClose" :zIndex="zIndex" :overlay="mask" :close-on-click-overlay="maskClosable" :className="getClassName()" round >
        <div class="header">
            <van-icon :name="geticonClass()" />
            {{title}}
        </div>
        <div style="text-align:center" class="content" v-html="content"></div>
        <div ref="modelConfirmFooter" class="footer-btns">
            <template v-for="item in buttonModel">
              <van-button v-if="item.visibel" :key="item.value"  :type="item.type" @click="button_click(item)">
                  {{item.text}}
              </van-button>
            </template>
            <slot name="customFooter" @click='button_click'></slot>
      </div>
    </van-popup>
</template>
<script lang="ts">
import { VNode } from "node_modules/vue/types";
import { Subject } from "rxjs";
import { Vue, Component, Prop } from "vue-property-decorator";
import './app-message-box.less'
@Component({
    components: {}
})
export default class AppMessageBox extends Vue {
    /**
     * 对话框类型
     *
     * @type {('info' | 'success' | 'warning' | 'error' | 'confirm')}
     * @memberof AppMessageBox
     */
    @Prop({ default: 'info' })
    public type?: 'info' | 'success' | 'warning' | 'error' ;

    /**
     * 标题
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop()
    public title?: string;

    /**
     * 内容
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop()
    public content?: string;

    /**
     * 按钮类型
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop({ default: 'confirmOrCancel' })
    public buttonType?: string | 'confirmOrCancel' | 'yesOrNo' | 'yesOrNoOrCancel' | 'confirm';

    /**
     * 启用自定义底部
     *
     * @type {boolean}
     * @memberof ModalConfirmOptions
     */
    @Prop({ default: false })
    public visibleCustomFooter?: boolean;


    /**
     * 自定义底部
     *
     * @type {VNode}
     * @memberof ModalConfirmOptions
     */
    @Prop()
    public customFooter?: VNode;

    /**
     * 自定义类名
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop()
    public customClass?: string;

    /**
     * 自定义类名
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop()
    public iconClass?: string;

    /**
     * 是否显示右上角的关闭按钮
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop({ default: false })
    public showClose?: boolean;

    /**
     * 是否显示遮罩
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop({ default: true })
    public mask?: boolean;

    /**
     * 是否可以点击遮罩关闭
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop({ default: true })
    public maskClosable?: boolean;

    /**
     * 显示模式
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop({ default: 'center' })
    public showMode?: string | 'center';

    /**
     * 关闭回调
     *
     * @type {Function}
     * @memberof AppMessageBox
     */
    @Prop()
    public onClose?: Function;

    /**
     * 引用对象名称
     *
     * @type {string}
     * @memberof AppMessageBox
     */
    @Prop()
    public refName?: string;

    /**
     * 内置按钮模型
     *
     * @type {any}
     * @memberof AppMessageBox
     */
    public buttonModel = [
        // 多语言
        {text: this.$t('app.commonWords.ok') , value: 'ok' , type: 'primary',visibel: false },
        {text: this.$t('app.commonWords.yes') , value: 'yes', type:'primary',visibel: false},
        {text: this.$t('app.commonWords.no') , value: 'no', visibel: false},
        {text: this.$t('app.commonWords.cancel') , value: 'cancel', visibel: false},
    ]
    

    /**
     * 数据传递对象
     *
     * @type {any}
     * @memberof AppMessageBox
     */ 
    public subject: null | Subject<any> = new Subject<any>();

    /**
     * 层级
     *
     * @type {any}
     * @memberof AppMessageBox
     */ 
    public zIndex:any = null;

    /**
     * 是否显示
     *
     * @type {boolean}
     * @memberof AppMessageBox
     */
    public isShow: boolean = false;

    /**
     * 获取显示模式类名 居中/top
     *
     * @memberof AppMessageBox
     */ 
    public getClassName() {
        return this.showMode === 'center'?'center':'top';
    }
    
    /**
     * 根据type计算iconClass
     *
     * @memberof AppMessageBox
     */ 
    public geticonClass(){
        if(this.customClass){
            return this.customClass;
        }
        let classes = '';
        switch (this.type) {
            case 'info':
                classes = 'comment-o';
                break;
            case 'success':
                classes = 'checked-o';
                break;
            case 'warning':
                classes = 'warning-o';
                break;
            case 'error':
                classes = 'clear-o';
                break;
        }
        return classes;
    }

    /**
     * 获取数据传递对象
     *
     * @memberof AppMessageBox
     */ 
    public getSubject(){
        return this.subject;
    }

    /**
     * 按钮点击
     *
     * @memberof AppMessageBox
     */ 
    public button_click(item:any) {
        this.isShow = false;
        this.subject?.next(item.value);
        this.close();
    }

    /**
     * 初始化按钮Model
     *
     * @memberof AppMessageBox
     */ 
    public initButtonModel() {
        if(this.visibleCustomFooter){
            return;
        }
        switch(this.buttonType) {
            case 'okcancel':
                this.buttonModel[0].visibel = true;
                this.buttonModel[3].visibel = true;
                break;
            case 'yesno':
                this.buttonModel[1].visibel = true;
                this.buttonModel[2].visibel = true;
                break;
            case 'yesnocancel':
                this.buttonModel[1].visibel = true;
                this.buttonModel[2].visibel = true;
                this.buttonModel[3].visibel = true;
                break;
            case 'ok':
                this.buttonModel[0].visibel = true;
                break;
        }
    }

    /**
     * 关闭方法
     *
     * @memberof AppMessageBox
     */ 
    public close() {
        if(this.onClose){
            this.onClose(this)
        }
        setTimeout(() => {
            document.body.removeChild(this.$el);
            this.$destroy();
            this.subject = null;
        }, 500)
    }

    /**
     * Vue生命周期created
     *
     * @memberof AppMessageBox
     */ 
    public created() {
        this.initButtonModel();
    }

    /**
     * Vue生命周期mounted
     *
     * @memberof AppMessageBox
     */ 
    public mounted() {
        const zIndex = this.$store.getters.getZIndex();
        if (zIndex) {
            this.zIndex = zIndex + 100;
            this.$store.commit('updateZIndex', this.zIndex);
        }
        if(this.visibleCustomFooter && this.customFooter){
            this.$slots.customFooter = [this.customFooter];
            this.$forceUpdate();
        }
        this.isShow = true;
    }
}
</script>
<style lang="less" scoped>
</style>