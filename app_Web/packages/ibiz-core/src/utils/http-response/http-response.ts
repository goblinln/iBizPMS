import { isNilOrEmpty } from 'qx-util';
import { isEmpty, isNil } from 'ramda';
import { IHttpResponse } from '../../interface';
import { HttpStatusMessage } from '../http-status-message/http-status-message';

/**
 * 返回值接口
 *
 * @interface IResponse
 */
interface IResponse {
    [key: string]: any;
    readonly body?: ReadableStream<Uint8Array> | null;
    readonly bodyUsed?: boolean;
    readonly headers?: Headers;
    readonly ok?: boolean;
    readonly redirected?: boolean;
    readonly status?: number;
    readonly statusText?: string;
    readonly trailer?: Promise<Headers>;
    readonly type?: ResponseType;
    readonly url?: string;
}

/**
 * 请求返回
 *
 * @export
 * @class HttpResponse
 * @implements {IHttpResponse}
 */
export class HttpResponse implements IHttpResponse {
    readonly data: any;
    readonly ok!: boolean;
    readonly status!: number;
    readonly statusText?: string;
    readonly headers: Record<string, string> = {};
    readonly url?: string;

    /**
     * Creates an instance of HttpResponse.
     *
     * @param {*} [data] 数据
     * @param {IResponse} [res] 结果
     * @param {number} [errorCode] 错误码
     * @memberof HttpResponse
     */
    constructor(data?: any, res?: IResponse, errorCode?: number) {
        if (res) {
            const { ok, status, url, headers } = res;
            if (!isNil(ok)) {
                this.ok = ok;
            }
            if (!isNil(status)) {
                this.status = status;
            }
            if (headers) {
                headers.forEach((val, key) => {
                    this.headers[key] = val;
                });
            }
            this.url = url;
            this.statusText = (HttpStatusMessage as any)[this.status] || res.statusText;
            if (data && !isNil(data.message) && !isEmpty(data.message)) {
                this.statusText = data.message;
            }
        }
        if (isNilOrEmpty(this.ok)) {
            this.ok = true;
        }
        if (isNilOrEmpty(this.status)) {
            this.status = errorCode || 200;
        }
        this.data = data;
    }
}