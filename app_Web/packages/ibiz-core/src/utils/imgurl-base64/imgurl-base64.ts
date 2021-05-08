
import { Http } from 'ibiz-core';

export class ImgurlBase64{
    /**
     * 单例变量声明
     *
     * @memberof ImgurlBase64
     */
    private static imgurlBase64: ImgurlBase64;

    /**
     * 获取 ImgurlBase64 单例对象
     *
     * @memberof ImgurlBase64
     */
     public static getInstance() {
        if (!this.imgurlBase64) {
            this.imgurlBase64 = new ImgurlBase64();
        }
        return this.imgurlBase64;
    }

    /**
     * 手动获取图片
     * 
     * 
     * @param url 图片url路径
     * @returns 
     */
    public async getImgURLOfBase64(url: string) {
        try {
            const response = await Http.getInstance().get(url);
            if (response && response.status === 200 && response.data) {
                return 'data:image/png;base64,' + btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''));
            } else {
                return './';
            }
        } catch (error) {
            return './';
        }
    }
}