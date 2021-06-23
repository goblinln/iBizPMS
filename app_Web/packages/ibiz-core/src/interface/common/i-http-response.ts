/**
 * http请求返回
 *
 * @export
 * @interface IHttpResponse
 */
export interface IHttpResponse {
    /**
     * 数据
     *
     * @type {*}
     * @memberof IHttpResponse
     */
    readonly data: any;
    /**
     * 请求状态
     *
     * @type {boolean}
     * @memberof IHttpResponse
     */
    readonly ok: boolean;
    /**
     * 请求返回状态
     *
     * @type {number}
     * @memberof IHttpResponse
     */
    readonly status: number;
    /**
     * 状态错误提示信息
     *
     * @type {string}
     * @memberof IHttpResponse
     */
    readonly statusText?: string;
    /**
     * 请求头
     *
     * @type {Headers}
     * @memberof IHttpResponse
     */
    readonly headers: Record<string, string>;
    /**
     * 请求地址
     *
     * @type {string}
     * @memberof IHttpResponse
     */
    readonly url?: string;
}