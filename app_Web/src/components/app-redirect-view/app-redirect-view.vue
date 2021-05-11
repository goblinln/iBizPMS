<template>
    <div class="app-redirect-view">
        <img src="./assets/img/500.png" />
        <div class="context">跳转中......</div>
    </div>
</template>

<script lang="ts">
import { Util } from 'ibiz-core';
import qs from 'qs';
import { Vue, Component } from 'vue-property-decorator';

@Component({
})
export default class AppRedirectView extends Vue {

    /**
     * vue生命周期
     *
     * @type {*}
     * @memberof AppRedirectView
     */
    public created(){
        let tempViewParam = this.parseViewParam(window.location.href);
       this.executeRedirectLogic(tempViewParam);
    }

    /**
     * 处理路径数据
     *
     * @param {*} [urlStr] 路径
     *
     * @memberof AppRedirectView
     */
    public parseViewParam(urlStr: string): any {
        let tempViewParam: any = {};
        const tempViewparam: any = urlStr.slice(urlStr.indexOf('?') + 1);
        const viewparamArray: Array<string> = decodeURIComponent(tempViewparam).split(';');
        if (viewparamArray.length > 0) {
            viewparamArray.forEach((item: any) => {
                Object.assign(tempViewParam, qs.parse(item));
            });
        }
        return tempViewParam;
    }

    /**
     * 执行数据重定向数据逻辑
     *
     *
     * @memberof AppRedirectView
     */
    public executeRedirectLogic(viewparam:any){
        let tempViewParam:any = Util.deepCopy(viewparam);
        const {srfdename,srfindexname}:{srfdename:string,srfindexname:string} = viewparam;
        if(!viewparam || !srfdename || !srfindexname){
            this.$throw('跳转参数不足，无法完成跳转操作！');
            return;
        }
        // 避免数据污染
        delete tempViewParam['srfdename'];
        delete tempViewParam['srfindexname'];
        delete tempViewParam[srfdename];
        // 计算路径
        let viewPath:string = `/${srfindexname}/${Util.srfpluralize(srfdename)}/${viewparam[srfdename]}/views/redirectview`;
         if (Object.keys(tempViewParam).length > 0) {
            viewPath = `${viewPath}?${qs.stringify(tempViewParam, { delimiter: ';' })}`;
        }
        this.$router.push({ path: viewPath });
    }


}
</script>

<style lang='less'>
@import './app-redirect-view.less';
</style>