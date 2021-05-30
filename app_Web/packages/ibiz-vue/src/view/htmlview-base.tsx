import { StringUtil } from 'ibiz-core';
import { IPSAppDEHtmlView } from '@ibiz/dynamic-model-api';
import { MainViewBase } from './mainview-base';

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
    public viewInstance!: IPSAppDEHtmlView;

    /**
     * 初始化实体html视图实例
     *
     * @memberof HtmlViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.parseIframeSrc(this.context, this.viewparams);
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
    public parseIframeSrc(context: any, viewparams: any) {
        this.iframeUrl = StringUtil.fillStrData(this.staticProps.modeldata.htmlUrl, context, viewparams);
    }

    /**
     * 渲染视图主体内容区
     *
     * @memberof HtmlViewBase
     */
    public renderMainContent() {
        if (this.iframeUrl?.length > 0) {
            return (
                <div class='iframe-container'>
                    <iframe src={this.iframeUrl}></iframe>
                </div>
            );
        } else {
            return (
                <div class='app-error-view'>
                    <div class='app-error-container'>
                        <img src='./assets/img/404.png' />
                        <div class='error-text'>
                            <div class='error-text1'>{this.$t('components.404.errortext1')}</div>
                            <div class='error-text2'>{this.$t('components.404.errortext2')}</div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}
