<template>
    <div class="app-mob-alert">
        <template v-if="items && items.length > 0">
            <template v-for="(item, index) in items">
                <template v-if="item.hasContent && !Object.is('POPUP', item.position)">
                <div class="alert"
                    :key="index"
                    v-show="item.showState"
                    :type="item.type"
                    :ref="'mob-alert-'+item.codeName"
                    >
                        <div class="content">
                          <div class="title" v-if="item.title">{{item.title}} </div>
                          <template v-if="item.content">
                            <div class="html-text" v-if="item.messageType == 'HTML'" v-html="item.content"></div>
                            <div class="text" v-else>{{ item.content }}</div>
                          </template>
                        </div>
                        <div class="close-icon" v-if="item.closeable"><van-icon @click="alertClose(item)" name="cross" /></div>
                </div>
                </template>
            </template>
        </template>
    </div>
</template>

<script lang="ts">
import {Vue, Component, Prop} from 'vue-property-decorator';

@Component({})
export default class AppMobAlert extends Vue {

    /**
     * 视图消息标识
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    @Prop() tag: any;

    /**
     * 显示位置
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    @Prop() position: any;

    /**
     * 应用上下文
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    @Prop() context: any;

    /**
     * 视图参数
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    @Prop() viewparam: any;


    /**
     * 视图消息组tag
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    @Prop() infoGroup!: any;

    /**
     * 视图名称
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    @Prop() viewname!: any;

    /**
     * 消息细节
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    @Prop() messageDetails!: any[];
    
    /**
     * 视图消息对象
     * 
     * @type {any}
     * @memberof AppMobAlert
     */
    public items: any[]= [];

    /**
     * Vue生命周期
     * 
     * @memberof AppMobAlert
     */
    public created() {
        this.handleItems();
    }

    public mounted() {
        console.log(this.items);
              
    }

    /**
     * 获取视图消息对象
     * 
     * @memberof AppMobAlert
     */
    public handleItems() {
        if (this.messageDetails.length > 0) {
            this.messageDetails.forEach((detail: any) => {
                this.handleItemOption(detail);
                let flag = this.handleItemCloseMode(detail);
                this.handleItemPosition(detail, flag);
                this.items.push(detail);
            })
        }
    }

    public handleItemOption(detail: any) {
        //  是否存在内容
        detail.hasContent = true;
        if(!detail.title && !detail.content) {
            detail.hasContent = false;
        }
        //  关闭模式
        detail.closeable = detail.enableRemove;
        //  类型
        switch (detail.type) {
            case 'WARN':
                detail.type = 'warning';
                break;
            case 'SUCCESS':
                detail.type = 'success';
                break;
            case 'ERROR':
                detail.type = 'error';
                break;
            default:
                detail.type = 'info';
                break;
        }
    }

    /**
     * 处理数据关闭模式
     * 
     * @memberof AppMobAlert
     */
    public handleItemCloseMode(data: any) {
        let flag = true;
        data.showState = true; 
        if(data.removeMode || data.removeMode == 0) {
            if(data.removeMode == 1) {
                const tag = this.viewname + '_' + this.infoGroup + '_' + data.codeName;
                const codeName = localStorage.getItem(tag);
                if(codeName) {
                    data.showState = false;
                    flag = false;
                }
            }
            if(data.removeMode == 0) {
                data.closeable = false;
            }
        }
        return flag;
    }

    /**
     * 处理数据显示位置
     * 
     * @memberof AppMobAlert
     */
    public handleItemPosition(data: any, flag: boolean) {
        if(data.position) {
            if(flag && Object.is('POPUP', data.position)) {
                const h = this.$createElement;
                data.showState = false;
                if(Object.is('HTML',data.messageType)) {
                    //1.首先动态创建一个容器标签元素，如DIV
                    let temp:any = document.createElement("div");
                    //2.然后将要转换的字符串设置为这个元素的innerHTML
                    temp.innerHTML = data.content;
                    //3.最后返回这个元素的innerText，即得到经过HTML解码的字符串
                    let output = temp.innerText || temp.textContent;
                    temp = null;
                    setTimeout(() => {
                        this.$Notice.confirm(data.title,output);
                    }, 0)
                } else {
                    setTimeout(() => {
                        this.$Notice.confirm(data.title,data.content);
                    }, 0)
                }
            }
        }
    }

    /**
     * 视图消息关闭
     * 
     * @memberof AppMobAlert
     */
    public alertClose(data: any) {
        if(data.customClass) {
            let tempArr: any[] = data.customClass.toString().split(',');
            if(tempArr && tempArr.length > 0) {
                if(Object.is("1", tempArr[1])) {
                    const tag = this.viewname + '_' + this.infoGroup + '_' + tempArr[0];
                    localStorage.setItem(tag, data.customClass);
                }
            } 
        }
        if(data.removeMode && data.removeMode == 1) {
            const tag = this.viewname + '_' + this.infoGroup + '_' + data.codeName;
            localStorage.setItem(tag, data.codeName);
        }
        let alert:any = this.$refs['mob-alert-'+data.codeName];
        if (alert[0]) {
          alert[0].style.display = "none";
        }
    }

}
</script>

<style lang="less">
@import './app-mob-alert.less';
</style>