
import axios from 'axios';

export class ImgurlBase64{
    /**
     * 单例变量声明
     *
     * @memberof ImgurlBase64
     */
    private static imgurlBase64: ImgurlBase64;

    /**
     * 图片缓存Map
     * 
     * @memberof ImgurlBase64
     */
    protected static imgMap: Map<string, any> = new Map();

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
            let img = './';
            // 缓存中有从缓存中拿
            if (ImgurlBase64.imgMap.get(url)) {
                let img = ImgurlBase64.imgMap.get(url);
                resolve(img);
                return;
            }
            axios({
                method: 'get',
                url: url,
                responseType: 'blob'
            }).then((response: any) => {
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
                        ImgurlBase64.imgMap.set(url, img);
                        resolve(img);
                    })
                } else {
                    resolve(img);
                }
            })
            .catch((response: any) => {
                resolve(img);
            });
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