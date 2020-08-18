import { Vue } from 'vue-property-decorator';

/**
 * 应用菜单基类
 */
export class ZentaoBase extends Vue {

    /**
     * 获取应用上下文
     *
     * @memberof ZentaoBase
     */
    get context(): any {
        return this.$appService.contextStore.appContext || {};
    }

    /**
     * 菜单点击
     *
     * @param {*} item 菜单数据
     * @memberof ZentaoBase
     */
    public click(item: any) {
        if (item) {
            let judge = true;
            switch (item.appfunctag) {
                case 'Auto11': 
                    this.clickAuto11(item); break;
                case 'Auto5': 
                    this.clickAuto5(item); break;
                case 'Auto8': 
                    this.clickAuto8(item); break;
                case 'Auto2': 
                    this.clickAuto2(item); break;
                case 'Auto6': 
                    this.clickAuto6(item); break;
                case 'Auto1': 
                    this.clickAuto1(item); break;
                case 'Auto10': 
                    this.clickAuto10(item); break;
                case 'Auto9': 
                    this.clickAuto9(item); break;
                default:
                    judge = false;
                    console.warn('未指定应用功能');
            }
            if (judge && this.$uiState.isStyle2()) {
                this.$appService.navHistory.reset();
            }
        }
    }
    
    /**
     * 测试边栏
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto11(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'products', parameterName: 'product' },
            { pathName: 'testleftsidebarlistview', parameterName: 'testleftsidebarlistview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }
    
    /**
     * 项目边栏
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto5(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'projects', parameterName: 'project' },
            { pathName: 'leftsidebarlistview', parameterName: 'leftsidebarlistview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }
    
    /**
     * iBiz软件生产管理
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto8(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'products', parameterName: 'product' },
            { pathName: 'htmlview', parameterName: 'htmlview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }
    
    /**
     * 左边栏产品列表视图
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto2(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'products', parameterName: 'product' },
            { pathName: 'leftsidebarlistview', parameterName: 'leftsidebarlistview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }
    
    /**
     * 打开项目主页
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto6(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'projectportalview', parameterName: 'projectportalview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }
    
    /**
     * 打开产品主页
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto1(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'productportalview', parameterName: 'productportalview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }
    
    /**
     * 我的地盘
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto10(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'ibzmyterritories', parameterName: 'ibzmyterritory' },
            { pathName: 'tabexpview', parameterName: 'tabexpview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }
    
    /**
     * 打开测试主页
     *
     * @param {*} [item={}]
     * @memberof Zentao
     */
    public clickAuto9(item: any = {}) {
        const viewparam: any = {};
        Object.assign(viewparam, {});
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'testportalview', parameterName: 'testportalview' },
        ];
        const path: string = this.$viewTool.buildUpRoutePath(this.$route, {}, deResParameters, parameters, [], viewparam);
        if(Object.is(this.$route.fullPath,path)){
            return;
        }
        this.$nextTick(function(){
            this.$router.push(path);
        })
    }

    /**
     * 绘制内容
     *
     * @private
     * @memberof ZentaoBase
     */
    public render(): any {
        return <span style="display: none;"/>
    }

}