import { DingTalkService } from './DingTalkService';
import { WeChatService } from './WeChatService';

/**
 * 第三方服务
 *
 * @export
 * @class ThirdPartyService
 */
export class ThirdPartyService {
    /**
     * 唯一实例
     *
     * @private
     * @static
     * @type {ThirdPartyService}
     * @memberof ThirdPartyService
     */
    private static readonly instance: ThirdPartyService = new ThirdPartyService();

    /**
     * 当前搭载平台
     *
     * @protected
     * @type {('WeChat' | 'DingTalk' | null)}
     * @memberof ThirdPartyService
     */
    public platform: 'WeChat' | 'DingTalk' | null = null;

    /**
     * 钉钉服务
     *
     * @type {DingTalkService}
     * @memberof ThirdPartyService
     */
    public dd: DingTalkService = DingTalkService.getInstance();

    /**
     * 企业微信服务
     *
     * @type {WeChatService}
     * @memberof ThirdPartyService
     */
    public weChat: WeChatService = WeChatService.getInstance();

    /**
     * 第三方导航返回事件
     *
     * @type {WeChatService}
     * @memberof ThirdPartyService
     */
    private backEvent: Array<Function> = [];

    /**
     * 第三方导航标题
     *
     * @type {WeChatService}
     * @memberof ThirdPartyService
     */
    private navTitle: Array<string> = [];

    /**
     * 是否已经初始化
     *
     * @readonly
     * @type {boolean}
     * @memberof ThirdPartyService
     */
    public get isInit(): boolean {
        return this.dd.isInit || this.weChat.isInit;
    }

    /**
     * Creates an instance of ThirdPartyService.
     * @memberof ThirdPartyService
     */
    private constructor() {
        if (ThirdPartyService.instance) {
            return ThirdPartyService.instance;
        }
        const info: string = window.navigator.userAgent.toUpperCase();
        if (info.indexOf('DINGTALK') !== -1) {
            this.platform = 'DingTalk';
        } else if (info.indexOf('WXWORK') !== -1) {
            this.platform = 'WeChat';
        }
    }

    /**
     * 登录
     *
     * @returns {Promise<any>}
     * @memberof ThirdPartyService
     */
    public async login(): Promise<any> {
        if (this.isDingTalk()) {
            return this.dd.login();
        } else if (this.isWeChat()) {
            return this.weChat.login();
        }
    }

    /**
     * 清楚登录用户信息
     *
     * @memberof WeChatService
     */
    public clearUserInfo(): void {
        if (this.isDingTalk()) {
            this.dd.clearUserInfo();
        } else if (this.isWeChat()) {
            this.weChat.clearUserInfo();
        }
    }

    /**
     * 获取用户信息
     *
     * @returns {*}
     * @memberof WeChatService
     */
    public async getUserInfo(): Promise<any> {
        if (this.isDingTalk()) {
            return this.dd.getUserInfo();
        } else if (this.isWeChat()) {
            return this.weChat.getUserInfo();
        }
    }

    /**
     * 是否为钉钉平台
     *
     * @returns {boolean}
     * @memberof ThirdPartyService
     */
    public isDingTalk(): boolean {
        return Object.is(this.platform, 'DingTalk');
    }

    /**
     * 是否为微信平台
     *
     * @returns {boolean}
     * @memberof ThirdPartyService
     */
    public isWeChat(): boolean {
        return Object.is(this.platform, 'WeChat');
    }

    /**
     * 获取实例
     *
     * @static
     * @returns {ThirdPartyService}
     * @memberof ThirdPartyService
     */
    public static getInstance(): ThirdPartyService {
        return ThirdPartyService.instance;
    }

    /**
     * 关闭
     *
     * @static
     * @returns {ThirdPartyService}
     * @memberof ThirdPartyService
     */
    public close() {
        if (this.isDingTalk()) {
            this.thirdPartyEvent('close');
        } else if (this.isWeChat()) {
            this.weChat.close();
        }
    }

    /**
     * 设置第三方标题
     *
     * @static
     * @returns {DingTalkService}
     * @memberof DingTalkService
     */
    public setTitle(title: string) {
        this.navTitle.push(title);
        if (this.isDingTalk()) {
            this.thirdPartyEvent('setTitle', title);
        } else if (this.isWeChat()) {
            this.weChat.setTitle(title);
        }
    }


    /**
     * 设置第三方容器导航栏返回事件
     *
     * @static
     * @returns {DingTalkService}
     * @memberof DingTalkService
     */
    public setBackEvent(event: Function) {
        this.backEvent.push(event);
        if (this.isDingTalk()) {
            this.thirdPartyEvent('setBackEvent', this.backEvent);
        } else if (this.isWeChat()) {
            this.weChat.setBackEvent(this.backEvent);
        }
    }

    /**
     * 第三方容器导航销毁返回事件
     */
    public destroyBackEvent() {
        this.backEvent = this.backEvent.slice(0, -1);
        this.navTitle = this.navTitle.slice(0, -1);
        if (!this.backEvent) {
            return
        }
        if (this.isDingTalk()) {
            this.thirdPartyEvent('setTitle', this.navTitle[this.navTitle.length - 1]);
            this.thirdPartyEvent('setBackEvent', this.backEvent);
        } else if (this.isWeChat()) {
            this.weChat.setTitle(this.navTitle[this.navTitle.length - 1]);
            this.weChat.setBackEvent(this.backEvent);
        }
    }

    /**
     * 第三方事件
     */
    public thirdPartyEvent(tag: string, arg: any = {}): any {
        if (!this.isInit) {
            return;
        }
        if (this.isDingTalk()) {
            return this.dd.ddEvent(tag, arg);
        }
    }
}