import { AppDefaultIndexViewLayout } from "../../app-default-layout/app-default-indexview-layout/app-default-indexview-layout";
import { Component } from 'vue-property-decorator';
import './app-style4-indexview-layout.less';

@Component({})
export class AppStyle4IndexViewLayout extends AppDefaultIndexViewLayout{
    /**
     * 初始化
     * 
     * @memberof AppDefaultIndexViewLayout 
     */
     public created() {
        document.getElementsByTagName('html')[0].className = this.selectTheme();
        this.isFullScreen = Boolean(this.$store.getters['getCustomParamByTag']('srffullscreen'));
    }
}