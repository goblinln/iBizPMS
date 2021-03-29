import Vue from 'vue';
import { Http } from '../net/http';
import { on } from '../dom/dom';
import { AppServiceBase } from '../../service';

/**
 * Studio Debug工具类
 *
 * @export
 * @class StudioActionUtil
 */
export class StudioActionUtil {
    /**
     * 唯一实例
     *
     * @private
     * @static
     * @type {StudioActionUtil}
     * @memberof StudioActionUtil
     */
    private static readonly instance: StudioActionUtil = new StudioActionUtil();

    /**
     * 是否启用
     *
     * @protected
     * @type {any}
     * @memberof StudioActionUtil
     */
    protected Environment: any;

    /**
     * 是否监听键盘事件
     *
     * @protected
     * @type {boolean}
     * @memberof StudioActionUtil
     */
    protected isWatchKeyboard: boolean = false;

    /**
     * 请求对象
     *
     * @protected
     * @type {Http}
     * @memberof StudioActionUtil
     */
    protected http: Http = Http.getInstance();

    /**
     * 配置信息
     *
     * @protected
     * @type {*}
     * @memberof StudioActionUtil
     */
    protected config: any = null;

    /**
     * 配置平台界面
     *
     * @protected
     * @type {Window}
     * @memberof StudioActionUtil
     */
    protected studioWin: Window | null = null;

    /**
     * 是否显示开发配置工具栏
     *
     * @type {boolean}
     * @memberof StudioActionUtil
     */
    public isShowTool: boolean = false;

    /**
     * Creates an instance of StudioActionUtil.
     * @memberof StudioActionUtil
     */
    constructor() {
        if (StudioActionUtil.instance) {
            return StudioActionUtil.instance;
        }
    }

    /**
     * 为当前视图建立issues
     *
     * @param {string} viewName
     * @returns {Promise<void>}
     * @memberof StudioActionUtil
     */
    public async createdIssues(viewName: string): Promise<void> {
        const config: any = await this.getConfig(viewName);
        if (config) {
            const context: string = `视图模块：${config.viewmodule}\n视图标识：${config.viewname}\n视图类型：${config.viewtype}\n`;
            window.open(`${this.Environment?.ProjectUrl}/issues/new?issue[title]=${encodeURIComponent('问题')}&issue[description]=${encodeURIComponent(context)}`, '_blank');
        }
    }

    /**
     * 打开Studio配置界面
     *
     * @param {string} viewName
     * @returns {Promise<void>}
     * @memberof StudioActionUtil
     */
    public async openStudioConfigView(viewName: string): Promise<void> {
        const config: any = await this.getConfig(viewName);
        if (config) {
            const params: any = {
                "appType": "APPSTUDIO",
                "appKey": this.Environment?.AppId,
                "dataType": "AppDesign_PSAppViewDesignRedirectView",
                "srfkey": config.viewtag
            };
            if (this.studioWin && this.studioWin.closed === false) {
                this.studioWin.postMessage({
                    type: 'OpenView',
                    params
                }, '*');
                Vue.prototype.$message.warning('请在已打开的配置平台查看!');
            } else {
                if(this.Environment?.debugOpenMode === 'sln'){
                    console.log("打开sln未支持");
                    // this.studioWin = window.open(`${Environment.StudioUrl}?ov=${encodeURIComponent(JSON.stringify(params))}#/common_slnindex/srfkeys=${Environment.SlnId}/sysdesign_psdevslnsysmodeltreeexpview/srfkey=${Environment.SysId}`, '_blank');
                }else{
                    this.studioWin = window.open(`${this.Environment?.StudioUrl}?ov=${encodeURIComponent(JSON.stringify(params))}#/common_mosindex/srfkeys=${this.Environment?.SysId}`, '_blank');
                }
            }
        }
    }

    /**
     * 获取视图配置参数
     *
     * @protected
     * @param {string} viewName
     * @returns {Promise<any>}
     * @memberof StudioActionUtil
     */
    protected async getConfig(viewName: string): Promise<any> {
        if (!this.config) {
            await this.loadConfig();
        }
        return this.config[viewName];
    }

    /**
     * 加载配置信息
     *
     * @protected
     * @returns {Promise<void>}
     * @memberof StudioActionUtil
     */
    protected async loadConfig(): Promise<void> {
        const response: any = await this.http.get('./assets/json/view-config.json');
        if (response && response.status === 200 && response.data) {
            this.config = response.data
        } else {
            console.warn('Studio操作控制器，视图参数信息加载失败!');
        }
    }

    /**
     * 展示配置工具栏状态变更
     *
     * @memberof StudioActionController
     */
    public showToolChange(): void {
        this.isShowTool = !this.isShowTool;
    }

    /**
     * 设置Environment
     *
     * @memberof StudioActionController
     */
    public setEnvironment() {
        if (!this.Environment) {
            this.Environment = AppServiceBase.getInstance().getAppEnvironment();
        }
    }

    /**
     * 获取实例
     *
     * @static
     * @returns {StudioActionUtil}
     * @memberof StudioActionUtil
     */
    public static getInstance(): StudioActionUtil {
        this.instance.setEnvironment();
        if (!this.instance.isWatchKeyboard && this.instance.Environment && this.instance.Environment.devMode) {
            this.instance.isWatchKeyboard = true;
            on(window, 'keydown', (e: KeyboardEvent) => {
                if ((e.ctrlKey || e.metaKey) && e.keyCode === 123) {
                    this.instance.showToolChange();
                }
            });
        }
        return this.instance;
    }

}