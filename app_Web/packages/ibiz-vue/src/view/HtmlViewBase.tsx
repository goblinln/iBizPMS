import { IBizListModel, ListViewEngine, IBizHtmlViewModel } from 'ibiz-core';
import { MainViewBase } from "./MainViewBase";
import { Util, ViewTool, AppServiceBase } from 'ibiz-core';


/**
 * 实体html视图基类
 *
 * @export
 * @class HtmlViewBase
 * @extends {MainViewBase}
 */
export class HtmlViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof HtmlViewBase
     */
    public viewInstance!: IBizHtmlViewModel;

    /**
     * 初始化实体html视图实例
     * 
     * @memberof HtmlViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizHtmlViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
    }

    /**
     * 嵌入视图路径
     *
     * @public
     * @type string
     * @memberof HtmlViewBase
     */
    public iframeUrl: string = '';

    /**
     * 解析嵌入视图路径
     *
     * @public
     * @memberof HtmlViewBase
     */
    public parseIframeSrc(context: any,viewparams: any){
        this.iframeUrl = `${this.staticProps.modeldata.htmlUrl}`;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof HtmlViewBase
     */
    public renderMainContent() {
        if(this.iframeUrl.length > 0) {
            return  <div class="iframe-container">
                        <iframe src={this.iframeUrl}></iframe>
                    </div>
        }else {
            return  <div class="app-error-view">
                        <div class="app-error-container">
                            <img src="/assets/img/404.png" />
                            <div class="error-text">
                                <div class="error-text1">{this.$t('components.404.errorText1')}</div>
                                <div class="error-text2">{this.$t('components.404.errorText2')}</div>
                            </div>
                        </div>
                    </div>
        }
    }

}
