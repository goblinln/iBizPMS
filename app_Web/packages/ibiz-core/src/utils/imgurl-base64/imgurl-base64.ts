
import axios from 'axios';

export class ImgurlBase64{
    /**
     * 单例变量声明
     *
     * @memberof ImgurlBase64
     */
    private static imgurlBase64: ImgurlBase64;

    /**
     * 图片缓存(加载中)
     *
     * @type {Map<string,any>}
     * @memberof CodeListServiceBase
     */
     public static imgCache: Map<string, any> = new Map();

     /**
      * 图片缓存(已完成)
      *
      * @type {Map<string,any>}
      * @memberof CodeListServiceBase
      */
     public static imgCached: Map<string, any> = new Map();

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
        return new Promise((resolve, reject) => {
            let img = '/';
            // 富文本CV上传图片与鼠标移出抛值冲突问题,上传成功回调还没执行时就抛值
            var reg = /^\s*data:([a-z]+\/[a-z0-9-+.]+(;[a-z-]+=[a-z0-9-]+)?)?(;base64)?,([a-z0-9!$&',()*+;=\-._~:@\/?%\s]*?)\s*$/i;
            if (reg.test(url)) {
                return resolve(url);
            }
            // 缓存中有从缓存中拿
            if (ImgurlBase64.imgCached.get(url)) {
                let img = ImgurlBase64.imgCached.get(url);
                resolve(img);
            }
            const callback: Function = (url: string, promise: Promise<any>) => {
                promise.then((response: any) => {
                    if (response && response.status === 200 && response.data) {
                        // 获取文件名
                        const disposition = response.headers['content-disposition'];
                        const filename = disposition.split('filename=')[1];
                        let type = 'image/png';
                        if (filename && filename.indexOf('.') > 0) {
                            const start = filename.lastIndexOf('.');
                            const expandedName = filename.substring(start + 1);
                            if (expandedName.match(/(bmp|jpg|jpeg|png|tif|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|WMF|webp)/gi) != null) {
                                type = 'image/' + expandedName;
                            } else {
                                resolve(img);
                            }
                        }
                        let blob = new Blob([response.data],{type: type});
                        this.blobToBase64(blob).then((res) => {
                            // 转化后的base64
                            img = `${res}`;
                            // 缓存图片
                            ImgurlBase64.imgCached.set(url, img);
                            resolve(img);
                        })
                    } else {
                        resolve(img);
                    }
                }).catch((result: any) => {
                    return resolve(img);
                })
            }
            // 加载中
            if (ImgurlBase64.imgCache.get(url)) {
                callback(url, ImgurlBase64.imgCache.get(url));
            } else {
                let _url = url;
                if (!Object.is('/', _url.substring(0,1))) {
                    _url = '/'+_url;
                }
                let result:Promise<any> = axios({method: 'get', url: _url, responseType: 'blob'});
                ImgurlBase64.imgCache.set(url, result);
                callback(url, result);
            }
        });
    }

    /**
     * 将blob转为base64
     * 
     * 
     * @param blob blob对象
     * @returns 
     */
    public blobToBase64(blob: any) {
        return new Promise((resolve, reject) => {
            const fileReader = new FileReader();
            fileReader.onload = (e: any) => {
                resolve(e.target.result);
            };
            // readAsDataURL
            fileReader.readAsDataURL(blob);
            fileReader.onerror = () => {
                reject(new Error('blobToBase64 error'));
            };
        });
    }
}