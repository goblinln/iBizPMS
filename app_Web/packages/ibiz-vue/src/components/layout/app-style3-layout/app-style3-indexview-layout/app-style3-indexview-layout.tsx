import { AppStyle2IndexViewLayout } from "../../app-style2-layout/app-style2-indexview-layout/app-style2-indexview-layout";
import { Component } from 'vue-property-decorator';
import './app-style3-indexview-layout.less';

@Component({})
export class AppStyle3IndexViewLayout extends AppStyle2IndexViewLayout{

    /**
     * 初始化
     * 
     * @memberof AppStyle2IndexViewLayout
     */
    public created(){
        document.getElementsByTagName('html')[0].className = this.selectTheme();
        this.$uiState.changeLayoutState({
            styleMode: 'STYLE2'
        });
    }
}