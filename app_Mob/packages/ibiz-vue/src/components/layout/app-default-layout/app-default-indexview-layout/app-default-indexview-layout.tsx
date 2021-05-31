import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Prop,Component } from 'vue-property-decorator';
import './app-default-indexview-layout.less';

@Component({})
export class AppDefaultIndexViewLayout extends AppDefaultViewLayout{
    
    /**
     * 当前主题
     *
     * @memberof AppDefaultIndexViewLayout
     */
    public selectTheme() {
        let _this: any = this;
        if (_this.$router.app.$store.state.selectTheme) {
            return _this.$router.app.$store.state.selectTheme;
        } else if (localStorage.getItem('theme-class')) {
            return localStorage.getItem('theme-class');
        } else {
            return 'app-dark-blue-theme';
        }
    }

    /**
     * 初始化
     * 
     * @memberof AppDefaultIndexViewLayout 
     */
    public created() {
      let className = this.selectTheme();
      if (document.getElementsByTagName('html')[0] && document.getElementsByTagName('html')[0].classList) {
        document.getElementsByTagName('html')[0].classList.add(className);
      }
    }

    /**
     * 绘制布局
     * 
     * @memberof AppDefaultIndexViewLayout
     */
    public render(h: any) {
        return (
            <div>
                 {this.$slots.default}
            </div>
        );
    }

}